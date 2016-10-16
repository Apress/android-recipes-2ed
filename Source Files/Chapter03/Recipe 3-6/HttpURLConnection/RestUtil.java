package com.examples.restnew;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.NameValuePair;

import android.util.Base64;

public class RestUtil {
    
    public static final RestTask obtainGetTask(String url)
            throws MalformedURLException, IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(url))
                .openConnection();

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setDoInput(true);

        RestTask task = new RestTask(connection);
        return task;
    }

    public static final RestTask obtainAuthenticatedGetTask(String url,
            String username, String password) throws MalformedURLException, IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(url))
                .openConnection();

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setDoInput(true);
        
        attachBasicAuthentication(connection, username, password);
        
        RestTask task = new RestTask(connection);
        return task;
    }
    
    public static final RestTask obtainFormPostTask(String url,
            List<NameValuePair> formData) throws MalformedURLException,
            IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(url))
                .openConnection();

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setDoOutput(true);

        RestTask task = new RestTask(connection);
        task.setFormBody(formData);

        return task;
    }
    
    public static final RestTask obtainAuthenticatedFormPostTask(String url,
            List<NameValuePair> formData, String username, String password) throws MalformedURLException,
            IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(url))
                .openConnection();

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setDoOutput(true);
        
        attachBasicAuthentication(connection, username, password);

        RestTask task = new RestTask(connection);
        task.setFormBody(formData);

        return task;
    }

    public static final RestTask obtainMultipartPostTask(String url,
            List<NameValuePair> formPart, File file, String fileName)
            throws MalformedURLException, IOException {
        HttpURLConnection connection = (HttpURLConnection) (new URL(url))
                .openConnection();

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setDoOutput(true);

        RestTask task = new RestTask(connection);
        task.setFormBody(formPart);
        task.setUploadFile(file, fileName);

        return task;
    }

    private static void attachBasicAuthentication(URLConnection connection, String username, String password) {
        //Add Basic Authentication Headers
        String userpassword = username + ":" + password;
        String encodedAuthorization = Base64.encodeToString(userpassword.getBytes(), Base64.NO_WRAP);
        connection.setRequestProperty("Authorization", "Basic "+
              encodedAuthorization);
    }

}
