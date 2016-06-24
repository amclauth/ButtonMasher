package problems.P026xP050;

import java.util.ArrayList;
import java.util.List;

import util.Combinatorics;
import util.Primes;

/**
 * P041<br>
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. 
 * For example, 2143 is a 4-digit pandigital and is also prime.
 * What is the largest n-digit pandigital prime that exists?
 * Ans: 7652413
 * @return
 */
public class P041 extends Combinatorics<Integer>
{
   public P041()
   {
      problemNumber = 41;
      variations = 1;
   }

   @Override
   protected String execute(int variation)
   {
      switch (variation)
      {
         case 0:
         case -1:
            return attempt1() + "";
      }
      return null;
   }
   
   private int result;

   /**
    * Just run through all the numbers and figure out which digits are which
    * @return
    */
   private int attempt1()
   {
   	result = 0;
   	
   	List<Integer> nums = new ArrayList<Integer>();
   	nums.add(1);
   	// must be less than 7 digits since 1..8 is divisible by 3 as is 1..9
   	for (int ii = 2; ii <= 7; ii++)
   	{
   	   nums.add(ii);
   	   permute(nums);
   	}
   	
   	return result;
   }
   
   @Override
   protected void process(List<Integer> arr)
   {
      int num = 0;
      for (int ii = 0; ii < arr.size(); ii++)
      {
         num *= 10;
         num += arr.get(ii);
      }
      if (Primes.isPrime(num) && num > result)
         result = num;
   }
}
