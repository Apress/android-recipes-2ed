package ca.tutortutor.chartdemo;

import com.kidroid.kichart.ChartActivity;

import com.kidroid.kichart.model.Bitem;

import com.kidroid.kichart.view.PieView;

import android.graphics.Color;

import android.os.Bundle;

public class PieChart extends ChartActivity 
{
   @Override
   public void onCreate(Bundle savedInstanceState) 
   {
      super.onCreate(savedInstanceState);
      Bundle bundle = getIntent().getExtras();
      float[] data2010 = bundle.getFloatArray("2010");
      float[] data2011 = bundle.getFloatArray("2011");
      Bitem[] items = new Bitem[data2010.length];
      items[0] = new Bitem(Color.RED, "2010.1", data2010[0]);
      items[1] = new Bitem(Color.GREEN, "2010.2", data2010[1]);
      items[2] = new Bitem(Color.BLUE, "2010.3", data2010[2]);
      items[3] = new Bitem(Color.MAGENTA, "2010.4", data2010[3]);
      PieView pv = new PieView(this);
      pv.setTitle("Quarterly Sales (Billions)");
      pv.setItems(items);
      setContentView(pv);
   }
}
