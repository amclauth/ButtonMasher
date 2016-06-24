package problems.P026xP050;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Primes;
import base.Problem;

/**
 * P049<br>
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways:
 *  (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 *  There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.
 *  What 12-digit number do you form by concatenating the three terms in this sequence?
 * Ans: 296962999629
 * @return
 */
public class P049 extends Problem
{
   public P049()
   {
      problemNumber = 49;
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
      List<Integer> primes = Primes.EratosthenesPrimesUpTo(10000);
      primes.removeAll(Primes.EratosthenesPrimesUpTo(1000));
      
      for (int p : primes)
      {
         // a 4 digit prime
         int a = p % 10;
         int b = (p/10) % 10;
         int c = (p/100) % 10;
         int d = (p/1000);
         
         // there are only 24 permutations, so we'll just hard code them
         int[] possibles = new int[24];
         int ii = 0;
         possibles[ii++] = p;
         possibles[ii++] = a * 1000 + b * 100 + d * 10 + c;
         possibles[ii++] = a * 1000 + c * 100 + d * 10 + b;
         possibles[ii++] = a * 1000 + c * 100 + b * 10 + d;
         possibles[ii++] = a * 1000 + d * 100 + b * 10 + c;
         possibles[ii++] = a * 1000 + d * 100 + c * 10 + b;
         possibles[ii++] = b * 1000 + a * 100 + c * 10 + d;
         possibles[ii++] = b * 1000 + a * 100 + d * 10 + c;
         possibles[ii++] = b * 1000 + c * 100 + d * 10 + a;
         possibles[ii++] = b * 1000 + c * 100 + a * 10 + d;
         possibles[ii++] = b * 1000 + d * 100 + a * 10 + c;
         possibles[ii++] = b * 1000 + d * 100 + c * 10 + a;
         possibles[ii++] = c * 1000 + a * 100 + b * 10 + d;
         possibles[ii++] = c * 1000 + a * 100 + d * 10 + b;
         possibles[ii++] = c * 1000 + b * 100 + a * 10 + d;
         possibles[ii++] = c * 1000 + b * 100 + d * 10 + a;
         possibles[ii++] = c * 1000 + d * 100 + a * 10 + b;
         possibles[ii++] = c * 1000 + d * 100 + b * 10 + a;
         possibles[ii++] = d * 1000 + a * 100 + b * 10 + c;
         possibles[ii++] = d * 1000 + a * 100 + c * 10 + b;
         possibles[ii++] = d * 1000 + b * 100 + a * 10 + c;
         possibles[ii++] = d * 1000 + b * 100 + c * 10 + a;
         possibles[ii++] = d * 1000 + c * 100 + a * 10 + b;
         possibles[ii++] = d * 1000 + c * 100 + b * 10 + a;
         
         // see if at least three are prime
         List<Integer> found = new ArrayList<Integer>();
         for (ii = 0; ii < 24; ii++)
         {
            if (Primes.isPrime(possibles[ii]) && !found.contains(possibles[ii]) && possibles[ii] > 1000)
            {
               found.add(possibles[ii]);
            }
         }
         
         if (found.size() >= 3)
         {
            Collections.sort(found);
            
            for (int jj = 0; jj < found.size() - 2; jj++)
            {
               if (found.get(jj) == 1487)
                  continue; // we already know this one
               for (int kk = jj+1; kk < found.size() - 1; kk++)
               {
                  int diff = found.get(kk) - found.get(jj);
                  if (found.contains(found.get(jj) + diff) && found.contains(found.get(jj) + 2*diff))
                  {
                     return (long)found.get(jj) * 100000000L + (long)(found.get(jj) + diff) * 10000L + found.get(jj) + diff*2;
                  }
               }
            }
         }
         
      }
   	return 0;
   }
   
}
