package problems.P051xP075;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import util.Basic;
import base.Problem;

/**
 * P055<br>
 * If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
 * Not all numbers produce palindromes so quickly. For example,
 * 349 + 943 = 1292,
 * 292 + 2921 = 4213
 * 213 + 3124 = 7337
 * That is, 349 took three iterations to arrive at a palindrome.
 * Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome. A number that never forms a 
 * palindrome through the reverse and add process is called a Lychrel number. Due to the theoretical nature of these numbers, and for the 
 * purpose of this problem, we shall assume that a number is Lychrel until proven otherwise. In addition you are given that for every number
 *  below ten-thousand, it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing power 
 *  that exists, has managed so far to map it to a palindrome. In fact, 10677 is the first number to be shown to require over fifty 
 *  iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).
 *  
 *  Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.
 *  
 *  How many Lychrel numbers are there below ten-thousand?
 * 
 * NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.
 * Ans: 249
 * 
 * @return
 */
public class P055 extends Problem
{
   public P055()
   {
      problemNumber = 55;
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
   public int attempt()
   {
      int count = 0;
      Set<Integer> lychrel = new HashSet<Integer>(); // barely helps
      for (int ii = 0; ii < 10000; ii++)
      {
         if (lychrel.contains(ii))
         {
            count++;
            continue;
         }
         BigInteger number = BigInteger.valueOf(ii);
         boolean found = false;
         for (int jj = 0; jj < 50; jj++)
         {
            // reverse and add
            number = number.add(Basic.reverse(number));
            if (Basic.isPalindrome(number))
            {
               found = true;
               break;
            }
         }
         if (!found)
         {
            lychrel.add(Basic.reverse(ii));
            count++;
         }
      }
      return count;
   }
   
}
