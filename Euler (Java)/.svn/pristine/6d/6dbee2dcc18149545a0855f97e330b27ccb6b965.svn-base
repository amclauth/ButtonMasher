package problems.P026xP050;

import java.util.List;

import util.Primes;
import base.Problem;

/**
 * P047<br>
 * The first two consecutive numbers to have two distinct prime factors are:
 * 14 = 2 x 7
 * 15 = 3 x 5
 * The first three consecutive numbers to have three distinct prime factors are:
 * 644 = 2² x 7 x 23
 * 645 = 3 x 5 x 43
 * 646 = 2 x 17 x 19.
 * Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?
 * Ans: 134043
 * @return
 */
public class P047 extends Problem
{
   public P047()
   {
      problemNumber = 47;
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
   private long attempt()
   {
      int n = 10;
      List<Integer> primes = Primes.EratosthenesPrimesUpTo(500000);
      while (true)
      {
         while (primes.get(primes.size()-1) < n)
            Primes.addNextPrime(primes);
         
         int count = 0;
         int ii = 0;
         for (; ii < 4; ii++)
         {
            if (fourPrimes(n + ii, primes))
               count++;
            else
               break;
         }
         if (count == 4)
            return n;
         n += ii+1;
      }
   }
   
   private boolean fourPrimes(int num, List<Integer> primes)
   {
      int count = 0;
      int pmult = 1;
      for (int ii = 0; ii < primes.size(); ii++)
      {
         int p = primes.get(ii);
         // 2*3*5*7 is 210, so at minimum, if a prime is 105 times less, it can't be included
         if (p*105 > num || p*pmult > num || count == 4)
            break;
         if (num % p == 0)
         {
            pmult *= p;
            count++;
         }
      }
      if (count == 4)
         return true;
      return false;
   }
   
}
