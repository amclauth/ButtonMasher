package problems.P051xP075;

import java.math.BigInteger;

import base.Problem;

/**
 * P065<br>
 * The square root of 2 can be written as an infinite continued fraction.
 * 
 * ...
 * 
 * The infinite continued fraction can be written, √2 = [1;(2)], (2) indicates
 * that 2 repeats ad infinitum. In a similar way, √23 = [4;(1,3,1,8)].
 * 
 * It turns out that the sequence of partial values of continued fractions for
 * square roots provide the best rational approximations. Let us consider the
 * convergents for √2.
 * 
 * ...
 * 
 * Hence the sequence of the first ten convergents for √2 are:
 * 
 * 1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...
 * What is most surprising is that the important mathematical constant,
 * e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].
 * 
 * The first ten terms in the sequence of convergents for e are:
 * 
 * 2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
 * The sum of digits in the numerator of the 10th convergent is 1+4+5+7=17.
 * 
 * Find the sum of digits in the numerator of the 100th convergent of the
 * continued fraction for e.
 * Ans:272
 * 
 * @return
 */
public class P065 extends Problem
{
   public P065()
   {
      problemNumber = Integer.valueOf(this.getClass().getSimpleName().substring(1));
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
    * I again didn't know what was going on with the continued fraction expansion, but
    * in exploring possibilities, noted the following:
    * The continued expansion has the pattern 1 1 2 1 1 4 1 1 6 1 1 8 1 1 10 1 1 12 ...
    * The numerators (or integers) have the pattern 2 3 8 11 19 87 106 193 1264 ....
    * 
    * These are n = expansion * (n-1) + n-2
    * 
    * @return
    */
   public long attempt()
   {
      int idx = 3;
      int mult = 0;
      BigInteger n1 = BigInteger.valueOf(2);
      BigInteger n2 = BigInteger.valueOf(3);
      for (; idx <= 100; idx++)
      {
         BigInteger n = n2;
         if (idx % 3 == 0)
         {
            mult += 2;
            n = n.multiply(BigInteger.valueOf(mult));
         }
         n = n.add(n1);
         
         n1 = n2;
         n2 = n;
      }
      
      int sum = 0;
      for (char c : n2.toString().toCharArray())
      {
         sum += (c - '0');
      }
      return sum;
   }
   
}
