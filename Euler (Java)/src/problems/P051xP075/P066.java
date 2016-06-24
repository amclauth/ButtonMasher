package problems.P051xP075;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.Basic;
import base.Problem;

/**
 * P066<br>
 * Consider quadratic Diophantine equations of the form:
 * 
 * x^2 – Dy^2 = 1
 * 
 * For example, when D=13, the minimal solution in x is 649^2 – 13×180^2 = 1.
 * 
 * It can be assumed that there are no solutions in positive integers when D is
 * square.
 * 
 * By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the
 * following:
 * 
 * 3^2 – 2×2^2 = 1
 * 2^2 – 3×1^2 = 1
 * 9^2 – 5×4^2 = 1
 * 5^2 – 6×2^2 = 1
 * 8^2 – 7×3^2 = 1
 * 
 * Hence, by considering minimal solutions in x for D ≤ 7, the largest x is
 * obtained when D=5.
 * 
 * Find the value of D ≤ 1000 in minimal solutions of x for which the largest
 * value of x is obtained.
 * Ans:661
 * 
 * @return
 */
public class P066 extends Problem
{
   public P066()
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
    * Need to find the fundamental solution of Pell's equation, which is the truncated
    * continued fraction of the square root of D
    * 
    * http://www.had2know.com/academics/pell-equation-calculator.html
    * http://mathworld.wolfram.com/PellEquation.html
    * http://mathworld.wolfram.com/PeriodicContinuedFraction.html
    * 
    * Solutions are /huge/
    * @return
    */
   public long attempt()
   {
      BigInteger maxX = BigInteger.ZERO;
      int maxD = 0;
      int next = 4;
      int idx = 1;
      
      for (int D = 2; D <= 1000; D++)
      {
         // skip perfect squares
         if (D == next)
         {
            idx++;
            next = (idx+1)*(idx+1);
            continue;
         }
         
         BigInteger d = BigInteger.ONE;
         BigInteger m = BigInteger.ZERO;
         BigInteger a = BigInteger.valueOf(idx);
         
         BigInteger n1 = BigInteger.ONE;
         BigInteger n0 = a;
         BigInteger d1 = BigInteger.ZERO;
         BigInteger d0 = BigInteger.ONE;
         
         while (!n0.multiply(n0).subtract(d0.multiply(d0).multiply(BigInteger.valueOf(D))).equals(BigInteger.ONE))
         {
            m = (d.multiply(a)).subtract(m);
            d = (BigInteger.valueOf(D).subtract(m.multiply(m))).divide(d);
            a = (BigInteger.valueOf(idx).add(m)).divide(d);
            
            BigInteger n2 = n1;
            n1 = n0;
            BigInteger d2 = d1;
            d1 = d0;
            
            n0 = a.multiply(n1).add(n2);
            d0 = a.multiply(d1).add(d2);
         }
         
         if (n0.compareTo(maxX) == 1)
         {
            maxX = n0;
            maxD = D;
         }
      }
      return maxD;
   }
   
}
