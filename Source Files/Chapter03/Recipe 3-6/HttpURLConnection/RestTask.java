package com.examples.restnew;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpResponseException;

import android.os.AsyncTask;
import android.util.Log;

public class RestTask extends AsyncTask<Void, Integer, Object> {
    private static final String TAG = "RestTask";

    public interface ResponseCallback {
        public void onRequestSuccess(String response);

        public void onRequestError(Exception error);
    }

    public interface ProgressCallback {
        public void onProgressUpdate(int progress);
    }
    
    private HttpURLConnection mConnection;
    private String mFormBody;
    private File mUploadFile;
    private String mUploadFileName;

    // Activity callbacks. Use WeakReferences to avoid
    // blocking operations causing linked objects to stay in memory
    private WeakReference<ResponseCallback> mResponseCallback;
    private WeakReference<ProgressCallback> mProgressCallback;

    public RestTask(HttpURLConnection connection) {
        mConnection = connection;
    }

    public void setFormBody(List<NameValuePair> formData) {
        if (formData == null) {
            mFormBody = null;
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < formData.size(); i++) {
            NameValuePair item = formData.get(i);
            sb.append( URLEncoder.encode(item.getName()) );
            sb.append("=");
            sb.append( URLEncoder.encode(item.getValue()) );
            if (i != (formData.size() - 1)) {
                sb.append("&");
            }
        }

        mFormBody = sb.toString();
    }

    public void setUploadFile(File file, String fileName) {
        mUploadFile = file;
        mUploadFileName = fileName;
    }

    public void setResponseCallback(ResponseCallback callback) {
        mResponseCallback = new WeakReference<ResponseCallback>(callback);
    }

    public void setProgressCallback(ProgressCallback callback) {
        mProgressCallback = new WeakReference<ProgressCallback>(callback);
    }

    private void writeMultipart(String boundary, String charset, OutputStream output, boolean writeContent) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(output, Charset.forName(charset)), 8192);
            // Post Form Data Component
            if (mFormBody != null) {
                writer.write("--" + boundary);
                writer.write("\r\n");
                writer.write("Content-Disposition: form-data; name=\"parameters\"");
                writer.write("\r\n");
                writer.write("Content-Type: text/plain; charset=" + charset);
                writer.write("\r\n");
                writer.write("\r\n");
                if (writeContent) {
                    writer.write(mFormBody);
                }
                writer.write("\r\n");
                writer.flush();
            }
            // Send binary file.
            writer.write("--" + boundary);
            writer.write("\r\n");
            writer.write("Content-Disposition: form-data; name=\"" + mUploadFileName
                            + "\"; filename=\"" + mUploadFile.getName() + "\"");
            writer.write("\r\n");
            writer.write("Content-Type: "
                            + URLConnection.guessContentTypeFromName(mUploadFile
                                            .getName()));
            writer.write("\r\n");
            writer.write("Content-Transfer-Encoding: binary");
            writer.write("\r\n");
            writer.write("\r\n");
            writer.flush();
            if (writeContent) {
                InputStream input = null;
                try {
                    input = new FileInputStream(mUploadFile);
                    byte[] buffer = new byte[1024];
                    for (int length = 0; (length = input.read(buffer)) > 0;) {
                        output.write(buffer, 0, length);
                    }
                    // Don't close the OutputStream yet
                    output.flush();
                } catch (IOException e) {
                    Log.w(TAG, e);
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
            // This CRLF signifies the end of the binary data chunk
            writer.write("\r\n");
            writer.flush();

            // End of multipart/form-data.
            writer.write("--" + boundary + "--");
            writer.write("\r\n");
            writer.flush();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void writeFormData(String charset, OutputStream output) throws IOException {
        try {
            output.write(mFormBody.getBytes(charset));
            output.flush();
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    @Override
    protected Object doInBackground(Void... params) {
        //Generate random string for boundary
        String boundary = Long.toHexString(System.currentTimeMillis());
        String charset = Charset.defaultCharset().displayName();
        
        try {
            // Set up output if applicable
            if (mUploadFile != null) {
                //We must do a multipart request
                mConnection.setRequestProperty("Content-Type",
                        "multipart/form-data; boundary=" + boundary);
                //Calculate the size of the extra metadata
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                writeMultipart(boundary, charset, bos, false);
                byte[] extra = bos.toByteArray();
                int contentLength = extra.length;
                //Add the file size to the length
                contentLength += mUploadFile.length();
                //Add the form body, if it exists
                if (mFormBody != null) {
                    contentLength += mFormBody.length();
                }
                mConnection.setFixedLengthStreamingMode(contentLength);
            } else if (mFormBody != null) {
                //In this case, it is just form data to post
                mConnection.setRequestProperty("Content-Type",
                                "application/x-www-form-urlencoded; charset=" + charset);
                mConnection.setFixedLengthStreamingMode(mFormBody.length());
            }
            
            //This is the first call on URLConnection that actually
            // does Network IO.  Even openConnection() is still just
            // doing local operations.
            mConnection.connect();

            // Do output if applicable (for a POST)
            if (mUploadFile != null) {
                OutputStream out = mConnection.getOutputStream();
                writeMultipart(boundary, charset, out, true);
            } else if (mFormBody != null) {
                OutputStream out = mConnection.getOutputStream();
                writeFormData(charset, out);
            }

            // Get response data
            int status = mConnection.getResponseCode();
            if (status >= 300) {
                String message = mConnection.getResponseMessage();
                return new HttpResponseException(status, message);
            }

            InputStream in = mConnection.getInputStream();
            String encoding = mConnection.getContentEncoding();
            int contentLength = mConnection.getContentLength();
            if (encoding == null) {
                encoding = "UTF-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    in, encoding));
            char[] buffer = new char[128];

            StringBuilder sb = new StringBuilder();
            int downloadedBytes = 0;
            int len1 = 0;
            while ((len1 = reader.read(buffer)) > 0) {
                downloadedBytes += len1;
                publishProgress((downloadedBytes * 100) / contentLength);
                sb.append(buffer);
            }
            
            return sb.toString();
        } catch (Exception e) {
            Log.w(TAG, e);
            return e;
        } finally {
            if (mConnection != null) {
                mConnection.disconnect();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // Update progress UI
        if (mProgressCallback != null && mProgressCallback.get() != null) {
            mProgressCallback.get().onProgressUpdate(values[0]);
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        if (mResponseCallback != null && mResponseCallback.get() != null) {
            if (result instanceof String) {
                mResponseCallback.get().onRequestSuccess((String) result);
            } else if (result instanceof Exception) {
                mResponseCallback.get().onRequestError((Exception) result);
            } else {
                mResponseCallback.get().onRequestError(new IOException("Unknown Error Contacting Host"));
            }
        }
    }
}
