package ca.tutortutor.univerter;

import android.content.Context;

class Category
{
   private int nameID;
   private Conversion[] conversions;
   private String[] conversionNames;

   Category(int nameID, Conversion[] conversions)
   {
      this.nameID = nameID;
      this.conversions = conversions;
   }

   Conversion getConversion(int index)
   {
      return conversions[index];
   }

   String[] getConversionNames(Context ctx)
   {
      if (conversionNames == null)
      {
         conversionNames = new String[conversions.length];
         for (int i = 0; i < conversionNames.length; i++)
            conversionNames[i] = conversions[i].getName(ctx);
      }
      return conversionNames;   
   }

   String getName(Context ctx)
   {
      return ctx.getString(nameID);
   }
}