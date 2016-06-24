package problems.P026xP050;

import java.util.List;

import util.Primes;
import base.Problem;

/**
 * P050<br>
 * The prime 41, can be written as the sum of six consecutive primes:
 * 41 = 2 + 3 + 5 + 7 + 11 + 13
 * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
 * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.
 * Which prime, below one-million, can be written as the sum of the most consecutive primes?
 * Ans: 997651
 * @return
 */
public class P050 extends Problem
{
   public P050()
   {
      problemNumber = 50;
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
      List<Integer> primes = Primes.EratosthenesPrimesUpTo(1000000);
      
      int maxCount = 21;
      int prime = 0;
      for (int ii = 0; ii < primes.size(); ii++)
      {
         // could check for "done" by primes.size() - ii < maxcount, but is slower to check than do
         int count = 1;
         int p = primes.get(ii);
         for (int jj = ii+1; jj < primes.size(); jj++)
         {
            count++;
            p += primes.get(jj);
            if (p > 1000000)
               break;
            if (count <= maxCount)
               continue;
            if (Primes.isPrime(p))
            {
               maxCount = count;
               prime = p;
            }
         }
      }
   	return prime;
   }
   
}
