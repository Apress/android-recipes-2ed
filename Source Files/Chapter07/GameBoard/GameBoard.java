package ca.tutortutor.gameboard;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Paint;

import android.view.View;

public class GameBoard extends View
{
   private int nSquares, colorA, colorB;

   private Paint paint;
   private int squareDim; 

   public GameBoard(Context context, int nSquares, int colorA, int colorB)
   {
      super(context);
      this.nSquares = nSquares;
      this.colorA = colorA;
      this.colorB = colorB;
      paint = new Paint();
   }

   @Override
   protected void onDraw(Canvas canvas)
   {
      for (int row = 0; row < nSquares; row++)
      {
         paint.setColor(((row & 1) == 0) ? colorA : colorB);
         for (int col = 0; col < nSquares; col++)
         {
            int a = col*squareDim;
            int b = row*squareDim;
            canvas.drawRect(a, b, a+squareDim, b+squareDim, paint);
            paint.setColor((paint.getColor() == colorA) ? colorB : colorA);
         }
      }   
   }

   @Override
   protected void onMeasure(int widthMeasuredSpec, int heightMeasuredSpec)
   {
      // keep the view squared
      int width = MeasureSpec.getSize(widthMeasuredSpec);
      int height = MeasureSpec.getSize(heightMeasuredSpec);
      int d = (width == 0) ? height : (height == 0) ? width :
              (width < height) ? width : height;
      setMeasuredDimension(d, d);
      squareDim = width/nSquares;
   }
}
