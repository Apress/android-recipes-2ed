package ca.tutortutor.univerter;

import android.content.Context;

class Conversion
{
   private int nameID;
   private String name;
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

   Conversion(String name, final double multiplier)
   {
      this(name,
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

   Conversion(String name, Converter converter, boolean canBeNegative)
   {
      this.name = name;
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
      return (name == null) ? ctx.getString(nameID) : name;
   }
}