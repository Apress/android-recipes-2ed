package ca.tutortutor.usegridlayout;

import android.app.Activity;

import android.os.Bundle;

import android.support.v7.widget.GridLayout;

import android.widget.Button;

public class UseGridLayout extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GridLayout gl = new GridLayout(this);
        gl.setRowCount(2);
        gl.setColumnCount(2);
        Button btn = new Button(this);
        btn.setText("1");
        gl.addView(btn);
        btn = new Button(this);
        btn.setText("2");
        gl.addView(btn);
        btn = new Button(this);
        btn.setText("3");
        gl.addView(btn);
        btn = new Button(this);
        btn.setText("4");
        gl.addView(btn);
        setContentView(gl);
    }
}
