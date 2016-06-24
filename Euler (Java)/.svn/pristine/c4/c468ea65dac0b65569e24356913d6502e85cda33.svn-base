package problems.P051xP075;

import java.math.BigInteger;

import base.Problem;

/**
 * P053<br>
 * There are exactly ten ways of selecting three from five, 12345:
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 * In combinatorics, we use the notation, 5C3 = 10.
 * In general,
 * nCr = n!/r!(nr)! ,where r  n, n! = n(n1)...321, and 0! = 1.
 * It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
 * How many, not necessarily distinct, values of  nCr, for 1  n  100, are greater than one-million?
 * Ans: 4075
 * 
 * @return
 */
public class P053 extends Problem
{
   public P053()
   {
      problemNumber = 53;
      variations = 2;
   }
   
   @Override
   protected String execute(int variation)
   {
      switch (variation)
      {
         case 0:
            return attempt() + "";
         case -1:
         case 1:
            return attempt2() + "";
      }
      return null;
   }
   
   /**
    * 
    * @return
    */
   public int attempt()
   {
      BigInteger[] facs = new BigInteger[101];
      int count = 0;
      BigInteger limit = BigInteger.valueOf(1000000);
      // create a list of factorials
      facs[0] = BigInteger.ONE;
      facs[1] = BigInteger.ONE;
      for (int ii = 2; ii <= 100; ii++)
      {
         facs[ii] = facs[ii-1].multiply(BigInteger.valueOf(ii));
      }
      for (int n = 23; n <= 100; n++)
      {
         for (int r = 1; r <= n; r++)
         {
            if (combinatoric(n,r,facs).compareTo(limit) > 0)
            {
               count++;
            }
         }
      }
      return count;
   }
   
   /**
    * We can use pascall's triangle to find these!
    * @return
    */
   public int attempt2()
   {
      int count = 0;
      int[][] triangle = new int[101][101];
      for (int ii = 0; ii < 101; ii++)
      {
         triangle[0][ii] = 1;
         triangle[ii][ii] = 1;
      }
      for (int jj = 2; jj < 101; jj++)
      {
         for (int ii = 1; ii < jj; ii++)
         {
            triangle[ii][jj] = triangle[ii][jj-1] + triangle[ii-1][jj-1]; // to the left and above and directly above
            if (triangle[ii][jj] > 1000000)
            {
               triangle[ii][jj] = 1000000; // who cares what the value is ... just make sure it's "bigger"
               count++;
            }
         }
      }
      return count;
   }
   
   private BigInteger combinatoric(int n, int r, BigInteger[] facs)
   {
      return facs[n].divide((facs[r].multiply(facs[n-r])));
   }
   
}
