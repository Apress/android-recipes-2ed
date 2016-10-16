package ca.tutortutor.chartdemo;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class ChartDemo extends Activity 
{
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      Button btnViewBC = (Button) findViewById(R.id.viewbc);
      AdapterView.OnClickListener ocl;
      ocl = new AdapterView.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            final float[] data2010 = new float[4];
            int[] ids = { R.id.data2010_1, R.id.data2010_2, R.id.data2010_3,
                          R.id.data2010_4 };
            for (int i = 0; i < ids.length; i++)
            {
               EditText et = (EditText) findViewById(ids[i]);
               String s = et.getText().toString();
               try
               {
                  float input = Float.parseFloat(s);
                  data2010[i] = input;
               }
               catch (NumberFormatException nfe)
               {
                  data2010[i] = 0;
               }
            }
            final float[] data2011 = new float[4];
            ids = new int[] { R.id.data2011_1, R.id.data2011_2,
                              R.id.data2011_3, R.id.data2011_4 };
            for (int i = 0; i < ids.length; i++)
            {
               EditText et = (EditText) findViewById(ids[i]);
               String s = et.getText().toString();
               try
               {
                  float input = Float.parseFloat(s);
                  data2011[i] = input;
               }
               catch (NumberFormatException nfe)
               {
                  data2011[i] = 0;
               }
            }
            Intent intent = new Intent(ChartDemo.this, BarChart.class);
            intent.putExtra("2010", data2010);
            intent.putExtra("2011", data2011);
            startActivity(intent);
         }
      };
      btnViewBC.setOnClickListener(ocl);

      Button btnViewLC = (Button) findViewById(R.id.viewlc);
      ocl = new AdapterView.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            final float[] data2010 = new float[4];
            int[] ids = { R.id.data2010_1, R.id.data2010_2, R.id.data2010_3,
                          R.id.data2010_4 };
            for (int i = 0; i < ids.length; i++)
            {
               EditText et = (EditText) findViewById(ids[i]);
               String s = et.getText().toString();
               try
               {
                  float input = Float.parseFloat(s);
                  data2010[i] = input;
               }
               catch (NumberFormatException nfe)
               {
                  data2010[i] = 0;
               }
            }
            final float[] data2011 = new float[4];
            ids = new int[] { R.id.data2011_1, R.id.data2011_2,
                              R.id.data2011_3, R.id.data2011_4 };
            for (int i = 0; i < ids.length; i++)
            {
               EditText et = (EditText) findViewById(ids[i]);
               String s = et.getText().toString();
               try
               {
                  float input = Float.parseFloat(s);
                  data2011[i] = input;
               }
               catch (NumberFormatException nfe)
               {
                  data2011[i] = 0;
               }
            }
            Intent intent = new Intent(ChartDemo.this, LineChart.class);
            intent.putExtra("2010", data2010);
            intent.putExtra("2011", data2011);
            startActivity(intent);
         }
      };
      btnViewLC.setOnClickListener(ocl);

      Button btnViewPC = (Button) findViewById(R.id.viewpc);
      ocl = new AdapterView.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            final float[] data2010 = new float[4];
            int[] ids = { R.id.data2010_1, R.id.data2010_2, R.id.data2010_3,
                          R.id.data2010_4 };
            for (int i = 0; i < ids.length; i++)
            {
               EditText et = (EditText) findViewById(ids[i]);
               String s = et.getText().toString();
               try
               {
                  float input = Float.parseFloat(s);
                  data2010[i] = input;
               }
               catch (NumberFormatException nfe)
               {
                  data2010[i] = 0;
               }
            }
            final float[] data2011 = new float[4];
            ids = new int[] { R.id.data2011_1, R.id.data2011_2,
                              R.id.data2011_3, R.id.data2011_4 };
            for (int i = 0; i < ids.length; i++)
            {
               EditText et = (EditText) findViewById(ids[i]);
               String s = et.getText().toString();
               try
               {
                  float input = Float.parseFloat(s);
                  data2011[i] = input;
               }
               catch (NumberFormatException nfe)
               {
                  data2011[i] = 0;
               }
            }
            Intent intent = new Intent(ChartDemo.this, PieChart.class);
            intent.putExtra("2010", data2010);
            intent.putExtra("2011", data2011);
            startActivity(intent);
         }
      };
      btnViewPC.setOnClickListener(ocl);
   }
}
