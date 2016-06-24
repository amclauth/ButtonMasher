package problems.P051xP075;

import base.Problem;

/**
 * P064<br>
 * All square roots are periodic when written as continued fractions and can be
 * written in the form:
 * 
 *  ...
 *  
 * It can be seen that the sequence is repeating. For conciseness, we use the
 * notation √23 = [4;(1,3,1,8)], to indicate that the block (1,3,1,8) repeats
 * indefinitely.
 * 
 * The first ten continued fraction representations of (irrational) square roots
 * are:
 * 
 * √2=[1;(2)], period=1
 * √3=[1;(1,2)], period=2
 * √5=[2;(4)], period=1
 * √6=[2;(2,4)], period=2
 * √7=[2;(1,1,1,4)], period=4
 * √8=[2;(1,4)], period=2
 * √10=[3;(6)], period=1
 * √11=[3;(3,6)], period=2
 * √12= [3;(2,6)], period=2
 * √13=[3;(1,1,1,1,6)], period=5
 * 
 * Exactly four continued fractions, for N ≤ 13, have an odd period.
 * 
 * How many continued fractions for N ≤ 10000 have an odd period?
 * Ans:1322
 * 
 * @return
 */
public class P064 extends Problem
{
   public P064()
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
    * This made no sense to me, so I looked at wikipedia:
    * http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
    * 
    * @return
    */
   public long attempt()
   {
      int next = 4;
      int idx = 1;
      int count = 0;
      
      for (int n = 2; n <= 10000; n++)
      {
         // skip perfect squares
         if (n == next)
         {
            idx++;
            next = (idx+1)*(idx+1);
            continue;
         }
         
         int length = 0;
         int d = 1;
         int m = 0;
         int a = idx;
         
         while (a != 2*idx)
         {
            m = d*a - m;
            d = (n - m*m) / d;
            a = (idx + m) / d;
            length++;
         }
         

         if (length % 2 == 1)
         {
            count++;
         }
      }
      return count;
   }
   
}
