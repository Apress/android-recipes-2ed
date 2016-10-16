package ca.tutortutor.mathutils;

public class MathUtils
{
   public static long factorial(long n)
   {
      if (n <= 0)
         return 1;
      else
         return n*factorial(n-1);
   }
}
