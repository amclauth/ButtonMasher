package problems.P051xP075;

import java.util.HashSet;
import java.util.Set;

import base.Problem;

/**
 * P052<br>
 * It can be seen that the number, 125874, and its double, 251748, contain
 * exactly the same digits, but in a different order.
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
 * contain the same digits.
 * Ans: 142857
 * 
 * @return
 */
public class P052 extends Problem
{
   public P052()
   {
      problemNumber = 52;
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
    * This number can't have two duplicate numbers. It'd need to contain duplicates of all numbers when multiplied
    * by 2, 3, 4, 5, and 6 as well.
    * @return
    */
   public int attempt()
   {
      int num = 0;
      int max = 10;
      
      while (true)
      {
         num++;
         if (num * 6 > max)
         {
            num = max-1;
            max *= 10;
            continue;
         }
         
         Set<Integer> nums = split(num);
         boolean failed = false;
         for (int ii = 2; ii <= 6; ii++)
         {
            failed = false;
            int n = num * ii;
            while (n != 0)
            {
               if (!nums.contains(n % 10))
               {
                  failed = true;
                  break;
               }
               n = n / 10;
            }
            if (failed)
               break;
         }
         if (!failed)
            return num;
      }
   }
   
   private Set<Integer> split(int num)
   {
      Set<Integer> nums = new HashSet<Integer>();
      while (num != 0)
      {
         nums.add(num % 10);
         num = num / 10;
      }
      return nums;
   }
   
}
