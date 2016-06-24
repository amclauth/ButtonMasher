package problems.P051xP075;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Basic;
import util.Primes;
import base.Problem;

/**
 * P069<br>
 * Euler's Totient function, φ(n) [sometimes called the phi function], is used
 * to determine the number of numbers less than n which are relatively prime to
 * n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and
 * relatively prime to nine, φ(9)=6.
 * 
 * n Relatively Prime φ(n) n/φ(n)
 * 2 1 1 2
 * 3 1,2 2 1.5
 * 4 1,3 2 2
 * 5 1,2,3,4 4 1.25
 * 6 1,5 2 3
 * 7 1,2,3,4,5,6 6 1.1666...
 * 8 1,3,5,7 4 2
 * 9 1,2,4,5,7,8 6 1.5
 * 10 1,3,7,9 4 2.5
 * It can be seen that n=6 produces a maximum n/φ(n) for n ≤ 10.
 * 
 * Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum.
 * 
 * Ans:510510
 * 
 * @return
 */
public class P069 extends Problem
{
   public P069()
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
            return attempt() + "";
         case 1:
         case -1:
            return attempt2() + "";
      }
      return null;
   }
   
   /**
    * 
    * @return
    */
   public long attempt()
   {
      int maxIdx = 0;
      double maxVal = 0;
      List<Set<Integer>> factors = new ArrayList<Set<Integer>>();
      List<Integer> primes = Primes.EratosthenesPrimesUpTo(1000000);
      factors.add(new HashSet<Integer>());// 0 and 1 so we don't have to track indexes separately
      factors.add(new HashSet<Integer>());
      for (int ii = 2; ii <= 1000000; ii++)
      {
         Set<Integer> divisors = new HashSet<Integer>();
         List<Long> divisorList = Basic.getDivisors(ii);
         for (Long l : divisorList)
            divisors.add(l.intValue());
         divisors.remove(1L);
         factors.add(divisors);

         boolean[] sieve = new boolean[ii]; // defaults to false
         sieve[0] = true;
         sieve[1] = false; // 1 is counted, false represents untested or left in
               
         for (int jj = 0; primes.get(jj)*primes.get(jj) < ii; jj++)
         {
            int p = primes.get(jj);
            
            if (sieve[p])
               continue;
            
            if (factors.get(ii).contains(p))
            {
               for (int kk = 1; kk*p < ii; kk++)
               {
                  sieve[kk*p] = true;
               }
            }
         }
         
         int count = 0;
         for (boolean b : sieve)
            if (!b)
               count++;
         double val = ii * 1.0 / count;
         if (val > maxVal)
         {
            maxVal = val;
            maxIdx = ii;
            System.out.println(ii + " : " + val);
         }
      }
      return maxIdx;
   }
   
   /**
    * The minimal number will simply be the largest number that is a product of the
    * smallest primes!
    * @return
    */
   public int attempt2()
   {
      int answer = 1;
      List<Integer> primes = Primes.EratosthenesPrimesUpTo(100);
      int idx = 0;
      while (answer * primes.get(idx) < 1000000)
      {
         answer = answer * primes.get(idx++);
      }
      return answer;
   }
}
