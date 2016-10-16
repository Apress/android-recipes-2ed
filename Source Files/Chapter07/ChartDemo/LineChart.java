package ca.tutortutor.chartdemo;

import com.kidroid.kichart.ChartActivity;

import com.kidroid.kichart.model.Aitem;

import com.kidroid.kichart.view.LineView;

import android.graphics.Color;

import android.os.Bundle;

public class LineChart extends ChartActivity 
{
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      Bundle bundle = getIntent().getExtras();
      float[] data2010 = bundle.getFloatArray("2010");
      float[] data2011 = bundle.getFloatArray("2011");      
      String[] arrX = new String[4];
      arrX[0] = "2010.1";
      arrX[1] = "2010.2";
      arrX[2] = "2010.3";
      arrX[3] = "2010.4";
      Aitem[] items = new Aitem[2];
      items[0] = new Aitem(Color.RED, "2010", data2010);
      items[1] = new Aitem(Color.GREEN, "2011", data2011);
      LineView lv = new LineView(this);
      lv.setTitle("Quarterly Sales (Billions)");
      lv.setAxisValueX(arrX);
      lv.setItems(items);
      setContentView(lv);
   }
}
