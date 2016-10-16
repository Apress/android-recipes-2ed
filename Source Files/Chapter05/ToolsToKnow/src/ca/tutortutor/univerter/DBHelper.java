package ca.tutortutor.univerter;

import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class DBHelper extends SQLiteOpenHelper
{
   private final static String DB_PATH = 
      "data/data/ca.tutortutor.univerter/databases/";
   private final static String DB_NAME = "conversions.db";

   private final static int CATEGORIES_ID_COLUMN_ID = 0;
   private final static int CATEGORIES_NAME_EN_COLUMN_ID = 1;
   
   private final static int CATTABLE_NAME_EN_COLUMN_ID = 0;
   private final static int CATTABLE_MULTIPLIER_COLUMN_ID = 1;
 
   private Context ctx;
   private SQLiteDatabase db;
      
   public DBHelper(Context ctx)
   {
      super(ctx, DB_NAME, null, 1);
      this.ctx = ctx; 
   }

   @Override	  
   public void onCreate(SQLiteDatabase db)
   {
      // Do nothing ... we don't create a new database.
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldver, int newver)
   {
      // Do nothing ... we don't upgrade a database.
   }
      
   public Category[] updateCategories(Category[] categories)
   {
      try
      {
         String path = DB_PATH+DB_NAME;
         db = SQLiteDatabase.openDatabase(path, null, 
                                          SQLiteDatabase.OPEN_READONLY|
                                          SQLiteDatabase.NO_LOCALIZED_COLLATORS);
         Cursor cur = db.query("categories", null, null, null, null, null, null);
      	 if (cur.getCount() == 0)
            return categories;
         Comparator<Category> cmpCat;
         cmpCat = new Comparator<Category>()
                  {
                     @Override
                     public int compare(Category c1, Category c2)
                     {
                        return c1.getName(ctx).compareTo(c2.getName(ctx));
                     }
                  };
         Set<Category> catSet = new TreeSet<Category>(cmpCat);
         Comparator<Conversion> cmpCon;
         cmpCon = new Comparator<Conversion>()
                  {
                     @Override
                     public int compare(Conversion c1, Conversion c2)
                     {
                        return c1.getName(ctx).compareTo(c2.getName(ctx));
                     }
                  };
         Set<Conversion> conSet = new TreeSet<Conversion>(cmpCon);
         while (cur.moveToNext())
      	 {
            String catID = cur.getString(CATEGORIES_ID_COLUMN_ID);
            String catEn = cur.getString(CATEGORIES_NAME_EN_COLUMN_ID);
            Conversion[] conversions = getConversions(catID);
            for (int i = 0; i < categories.length; i++)
            {
               Category cat = categories[i];
               catSet.add(cat);
               if (catEn.equals(cat.getName(ctx)))
               {
                  int numCon = cat.getNumConversions();
                  for (int j = 0; j < numCon; j++)
                     conSet.add(cat.getConversion(j));
                  for (int j = 0; j < conversions.length; j++)
                     conSet.add(conversions[j]);
                  cat.setConversions(conSet.toArray(new Conversion[0]));
                  conSet.clear();
               }
               if (i == categories.length-1)
                  catSet.add(new Category(catEn, conversions));
            } 
      	 }
         return catSet.toArray(new Category[0]);
      }
      catch (SQLException sqle)
      {
      }
      finally
      {
         if (db != null)
            db.close();
      }
      return categories;
   }

   private Conversion[] getConversions(String catID)
   {
      try
      {
         Cursor cur = db.query(catID, null, null, null, null, null, null);
         if (cur.getCount() == 0)
            return new Conversion[0];
         List<Conversion> conList = new ArrayList<Conversion>();
         while (cur.moveToNext())
         {
            String name_en = cur.getString(CATTABLE_NAME_EN_COLUMN_ID);
            double multiplier = cur.getDouble(CATTABLE_MULTIPLIER_COLUMN_ID);
            Conversion con = new Conversion(name_en, multiplier);
            conList.add(con); 
         }
         return conList.toArray(new Conversion[0]);
      }
      catch (SQLException sqle)
      {
      }
      return null;
   }
}