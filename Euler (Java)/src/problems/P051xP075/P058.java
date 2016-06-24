package problems.P051xP075;

import util.Primes;
import base.Problem;

/**
 * P058<br>
 * Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.

37 36 35 34 33 32 31
38 17 16 15 14 13 30
39 18  5  4  3 12 29
40 19  6  1  2 11 28
41 20  7  8  9 10 27
42 21 22 23 24 25 26
43 44 45 46 47 48 49

It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is 
that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.

If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed. If 
this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals 
first falls below 10%?
 * Ans:26241
 * 
 * @return
 */
public class P058 extends Problem
{
   public P058()
   {
      problemNumber = 58;
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
    * Another square problem. We can follow a progression out for each diagonal and check which are prime
    * @return
    */
   public int attempt()
   {
      int d = 1;
      int num = 0;
      int den = 1;
      int diff = 2;
      // first we add 2 4 times, then 4 4 times, then 6 4 times, etc
      while (true)
      {
         for (int ii = 0; ii < 4; ii++)
         {
            d += diff;
            if (Primes.isPrime(d))
               num++;
         }
         den += 4;
         diff += 2;
         if (num * 10 < den)
         {
            return diff-1;
         }
      }
   }
   
   
}
