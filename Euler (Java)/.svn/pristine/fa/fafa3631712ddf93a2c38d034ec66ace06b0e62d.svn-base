package problems.P051xP075;

import java.math.BigInteger;

import base.Problem;

/**
 * P057<br>
 * It is possible to show that the square root of two can be expressed as an infinite continued fraction.
 * 
 * √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
 * 
 * By expanding this for the first four iterations, we get:
 * 
 * 1 + 1/2 = 3/2 = 1.5
 * 1 + 1/(2 + 1/2) = 7/5 = 1.4
 * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
 * 
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example
 * where the number of digits in the numerator exceeds the number of digits in the denominator.
 * 
 * In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
 * Ans:153
 * 
 * @return
 */
public class P057 extends Problem
{
   public P057()
   {
      problemNumber = 57;
      variations = 2;
   }
   
   @Override
   protected String execute(int variation)
   {
      switch (variation)
      {
         case 0:
            return attempt() + "";
         case 1:
         case -1:
            return attempt2() + "";
      }
      return null;
   }
   
   /**
    * first is 1/1, then 3/2, then 7/5, etc. From 1/1 we add 1/2, so we multiply 1 * 2 and add 1, then put it over 2. 
    * Next, we have 1 + 1/(2+1/2), so the denominator becomes that (2*2+1)/2 which gets flipped into 2/5, and adding 1 to it, 
    * we get 7/5. So the denominator ends up being this series of 1,2,5,12,29, etc. The differences are 1,3,7,17, etc. The
    * differences are the sum of the last two numbers! So if we know 1 and 2, the next is 2+(1+2)=5. The next is 5+(5+2)=12. 
    * The next is 12+(12+5)=29. So we have a solid series for the denominator. 
    * The numerator is the difference we already found, 3,7,17, etc. So the numerator is just the sum of the current denominator
    * and the previous one.
    * That means that the difference is the numerator of the previous ratio!
    * So if we have an existing n/d, the next d is just n+d. The new numerator is the sum of the previous two denominators.
    * That's the new denominator plus the last denominator. Just (n + d) + d, or n + 2d.
    * 
    * @return
    */
   public int attempt()
   {
      BigInteger n = BigInteger.ONE;
      BigInteger d = BigInteger.ONE;
      BigInteger two = BigInteger.valueOf(2);
      int count = 0;
      for (int idx = 2; idx <= 1000; idx++)
      {
//         d = n + d;
//         n = n + 2*(d-n); // instead of tracking the previous number
         d = d.add(n);
         n = n.add(two.multiply(d.subtract(n)));
         if (n.toString().length() > d.toString().length())
         {
            count++;
         }
      }
      return count;
   }
   
   /**
    * I thought there'd have to be a way to do this without bigint and realized I could use
    * double or float, but I just needed to reduce the fraction since I don't actually care
    * about the value itself, only the relative lengths!
    * @return
    */
   public int attempt2()
   {
      float n = 1;
      float d = 1;
      int count = 0;
      for (int idx = 2; idx <= 1000; idx++)
      {
         d = n+d;
         n = n + 2*(d-n);
         if (Math.floor(Math.log10(n)) > Math.floor((int)Math.log10(d)))
         {
            count++;
            if (d > 10000000) // only care about their relative lengths!
            {
               d = d/10000000;
               n = n/10000000;
            }
         }
      }
      return count;
   }
   
}
