package problems.P051xP075;

import base.Problem;

/**
 * P063<br>
 * The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit
 * number, 134217728=8^9, is a ninth power.
 * 
 * How many n-digit positive integers exist which are also an nth power?
 * Ans:
 * 
 * @return
 */
public class P063 extends Problem
{
   public P063()
   {
      problemNumber = 63;
      variations = 1;
   }
   
   @Override
   protected String execute(int variation)
   {
      switch (variation)
      {
         case 0:
         case -1:
            return attempt() + "";
      }
      return null;
   }
   
   /**
    * 0->9^1 should all count. We're looking for numbers where:
    * a^n=p
    * floor(log10(p)) = n or 10^(n-1) <= p < 10^(n)
    * 
    * So we want to know the nth root of 10^(n-1) and every integer between that and 10 (exclusive)
    * will be an answer. Some quick trial and error shows that 21 is the highest we can go, since
    * the 22nd root of 10^21 is greater than 9, so no integer value exists between that value and 10.
    * 
    * Long only goes up to 19 digits, though, so we'll have to use float or double
    * @return
    */
   public long attempt()
   {
      double tens = 1;
      int n = 1;
      double low = 0;
      int diff = 1;
      int count = 0; // 0^1 does not count
      while (diff > 0)
      {
         low = Math.pow(tens, 1.0/n);
         diff = 10 - (int)(Math.ceil(low));
         count += diff;
         n++;
         tens *= 10;
      }
      
      return count;
   }
   
}
