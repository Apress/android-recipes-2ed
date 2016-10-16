package ca.tutortutor.univerter;

import android.content.Context;

class Conversion
{
   private int nameID;
   private Converter converter;
   private boolean canBeNegative;

   Conversion(int nameID, final double multiplier)
   {
      this(nameID,
           new Converter()
           {
              @Override
              public double convert(Context ctx, double value)
              {
                 return value*multiplier;
              }
           },
           false);
   }

   Conversion(int nameID, Converter converter, boolean canBeNegative)
   {
      this.nameID = nameID;
      this.converter = converter;
      this.canBeNegative = canBeNegative;
   }

   boolean canBeNegative()
   {
      return canBeNegative;
   }

   Converter getConverter()
   {
      return converter;
   }

   String getName(Context ctx)
   {
      return ctx.getString(nameID);
   }
}