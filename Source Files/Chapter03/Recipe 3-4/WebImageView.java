package com.examples.webimage;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

public class WebImageView extends ImageView {
	
	private Drawable mPlaceholder, mImage;
	
	public WebImageView(Context context) {
		this(context, null);
	}
	
	public WebImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public WebImageView(Context context, AttributeSet attrs, int defaultStyle) {
		super(context, attrs, defaultStyle);
	}
	
	public void setPlaceholderImage(Drawable drawable) {
		mPlaceholder = drawable;
		if(mImage == null) {
			setImageDrawable(mPlaceholder);
		}
	}
	public void setPlaceholderImage(int resid) {
		mPlaceholder = getResources().getDrawable(resid);
		if(mImage == null) {
			setImageDrawable(mPlaceholder);
		}
	}
	
	public void setImageUrl(String url) {
		DownloadTask task = new DownloadTask();
		task.execute(url);
	}
	
	private class DownloadTask extends AsyncTask<String, Void, Bitmap> {
		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			try {
				URLConnection connection = (new URL(url)).openConnection();
				InputStream is = connection.getInputStream();
	            BufferedInputStream bis = new BufferedInputStream(is);
	
	            ByteArrayBuffer baf = new ByteArrayBuffer(50);
	            int current = 0;
	            while ((current = bis.read()) != -1) {
	            	baf.append((byte)current);
	            }
	            byte[] imageData = baf.toByteArray();
				return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
			} catch (Exception exc) {
				return null;
			}
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			mImage = new BitmapDrawable(result);
			if(mImage != null) {
				setImageDrawable(mImage);
			}
		}
	};
}
