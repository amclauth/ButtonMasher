package problems.P051xP075;

import java.util.List;

import util.Primes;
import base.Problem;

/**
 * P070<br>
 * Euler's Totient function, φ(n) [sometimes called the phi function], is used
 * to determine the number of positive numbers less than or equal to n which are
 * relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less
 * than nine and relatively prime to nine, φ(9)=6.
 * The number 1 is considered to be relatively prime to every positive number,
 * so φ(1)=1.
 * 
 * Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation
 * of 79180.
 * 
 * Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation of n and
 * the ratio n/φ(n) produces a minimum.
 * Ans:
 * 
 * @return
 */
public class P070 extends Problem
{
   public P070()
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
   
   private List<Integer> primes;
   private int           idx;
   
   /**
    * 
    * @return
    */
   public long attempt()
   {
      primes = Primes.EratosthenesPrimesUpTo(10000000);
      idx = 0;
      for (int n = 2; n < 100000; n++)
      {
//         System.out.println(n + " ::: " + phi(n));
         int phi = phi(n);
      }
      return 0;
   }
   
   private int gcd(int p, int q)
   {
      int c;
      while (q != 0)
      {
         c = p % q;
         p = q;
         q = c;
      }
      return p;
   }
   
   private int phi(int n)
   {
      if (n < 2)
         return 0;
      
      if (n == primes.get(idx))
      {
         idx++;
         return n - 1;
      }
      
      int c = 0;
      for (int i = 1; i < n; i += n % 2 < 1 ? 2 : 1)
         if (gcd(n, i) == 1)
            c++;
      return c;
   }
}
