package ca.tutortutor.univerter;

import android.content.Context;

class Category
{
   private int nameID;
   private String name;
   private Conversion[] conversions;
   private String[] conversionNames;

   Category(int nameID, Conversion[] conversions)
   {
      this.nameID = nameID;
      this.conversions = conversions;
   }

   Category(String name, Conversion[] conversions)
   {
      this.name = name;
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
      return (name == null) ? ctx.getString(nameID) : name;
   }

   int getNumConversions()
   {
      return conversions.length;
   }

   void setConversions(Conversion[] conversions)
   {
      this.conversions = conversions;
   }
}