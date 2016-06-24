package problems.P026xP050;

import java.util.ArrayList;
import java.util.List;

import util.Primes;
import base.Problem;

/**
 * P046<br>
 * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.
 * 9 = 7 + 2×1^2
 * 15 = 7 + 2×2^2
 * 21 = 3 + 2×3^2
 * 25 = 7 + 2×3^2
 * 27 = 19 + 2×2^2
 * 33 = 31 + 2×1^2
 * 
 * It turns out that the conjecture was false.
 * 
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 * Ans: 5777
 * @return
 */
public class P046 extends Problem
{
   public P046()
   {
      problemNumber = 46;
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
    * Just check!
    * @return
    */
   private long attempt()
   {
   	List<Integer> primes = Primes.EratosthenesPrimesUpTo(1000);
   	List<Integer> squares = new ArrayList<Integer>();
   	int squareIdx = 1;
   	for (; squareIdx < 50; squareIdx++)
   	   squares.add(squareIdx * squareIdx * 2);
   	
   	// composites start at 4, but only looking at odds
   	int n = 3;
   	while (true)
   	{
   	   n+=2;
   	   if (Primes.isPrime(n)) // faster than searching the list
   	      continue; // not composite
   	   
   	   // make sure we have enough primes and squares
   	   while (primes.get(primes.size()-1) < n)
   	   {
   	      Primes.addNextPrime(primes);
   	   }
   	   while (squares.get(squares.size()-1) < n)
   	   {
   	      squares.add(squareIdx * squareIdx * 2);
   	      squareIdx++;
   	   }
   	   
   	   // now just check
   	   boolean found = false;
   	   for (int p : primes)
   	   {
   	      found = false;
   	      if (p > n)
   	         break;
   	      for (int s : squares)
   	      {
   	         if (s > n-p)
   	            break;
   	         if (p + s == n)
   	         {
   	            found = true;
   	            break;
   	         }
   	      }
   	      if (found)
   	         break;
   	   }
   	   if (!found)
   	      return n;
   	}
   }
   
   /**
    * Is it faster to calculate?
    * @return
    */
   private int attempt2()
   {
      int n = 7; // first odd composite number will be 9
      while (true)
      {
         n += 2;
         if (Primes.isPrime(n))
            continue;
         
         // squares should be less dense than primes
         boolean found = false;
         for (int ii = 1; ii*ii*2 < n; ii++)
         {
            if (Primes.isPrime(n - ii*ii*2))
            {
               found = true;
               break;
            }
         }
         if (!found)
         {
            return n;
         }
      }
   }
   
}
