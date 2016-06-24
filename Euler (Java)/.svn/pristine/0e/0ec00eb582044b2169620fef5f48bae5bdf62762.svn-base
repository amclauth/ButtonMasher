package problems.P051xP075;

import java.math.BigInteger;

import base.Problem;

/**
 * P056<br>
 * A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably large: 
 * one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.
 * 
 * Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?
 * Ans: 972
 * 
 * @return
 */
public class P056 extends Problem
{
   public P056()
   {
      problemNumber = 56;
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
    * 
    * @return
    */
   public int attempt()
   {
      int max = 0;
      for (int ii = 99; ii > 0; ii--)
      {
         BigInteger base = BigInteger.valueOf(ii);
         BigInteger n = BigInteger.valueOf(ii);
         for (int jj = 99; jj > 0; jj--)
         {
            n = n.multiply(base);
            String s = n.toString();
            if (s.length()*9 < max)
               continue;
            char[] c = s.toCharArray();
            int sum = 0;
            for (int kk = 0; kk < c.length; kk++)
            {
               sum += c[kk] - 48;   
            }
            if (sum > max)
            {
               max = sum;
            }
         }
      }
      return max;
   }
   
}
