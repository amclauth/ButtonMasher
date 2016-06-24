package problems.P026xP050;

import java.util.ArrayList;
import java.util.List;

import util.Combinatorics;

/**
 * P043<br>
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, 
 * but it also has a rather interesting sub-string divisibility property.
 * 
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
 * 
 * d2d3d4=406 is divisible by 2
 * d3d4d5=063 is divisible by 3
 * d4d5d6=635 is divisible by 5
 * d5d6d7=357 is divisible by 7
 * d6d7d8=572 is divisible by 11
 * d7d8d9=728 is divisible by 13
 * d8d9d10=289 is divisible by 17
 * 
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 * Ans: 16695334890
 * @return
 */
public class P043 extends Combinatorics<Integer>
{
   public P043()
   {
      problemNumber = 43;
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
   
   long result;
   
   /**
    * Just run through all the numbers and figure out which digits are which
    * @return
    */
   private long attempt()
   {
   	result = 0;
   	
   	List<Integer> nums = new ArrayList<Integer>();
   	for (int ii = 0; ii <= 9; ii++)
   	   nums.add(ii);
   	
   	permute(nums);
   	
   	return result;
   }
   
   private long attempt2()
   {
      long value = 0;
      
      
      // find all three digit multiples of 17 with unique digits
      for (int start = 102; start < 1000; start += 17)
      {
         List<Integer> available = new ArrayList<Integer>();
         for (int ii = 0; ii < 10; ii++)
            available.add(ii);
         
         int[] nums = new int[10];
         nums[9] = (start % 10);
         nums[8] = ((start/10) % 10);
         nums[7] = ((start/100) % 10);
         if (nums[9] == nums[8] || nums[9] == nums[7] || nums[8] == nums[7])
            continue;
         for (int ii = 7; ii < 10; ii++)
            available.remove(Integer.valueOf(nums[ii]));
         for (int i6 = 0; i6 < available.size(); i6++)
         {
            nums[6] = available.remove(0);
            if ((nums[6]*100 + nums[7]*10 + nums[8]) % 13 == 0)
            {
               for (int i5 = 0; i5 < available.size(); i5++)
               {
                  nums[5] = available.remove(0);
                  if ((nums[5] * 100 + nums[6] * 10 + nums[7]) % 11 == 0)
                  {
                     for (int i4 = 0; i4 < available.size(); i4++)
                     {
                        nums[4] = available.remove(0);
                        if ((nums[4] * 100 + nums[5] * 10 + nums[6]) % 7 == 0)
                        {
                           for (int i3 = 0; i3 < available.size(); i3++)
                           {
                              nums[3] = available.remove(0);
                              if ((nums[3] * 100 + nums[4] * 10 + nums[5]) % 5 == 0)
                              {
                                 for (int i2 = 0; i2 < available.size(); i2++)
                                 {
                                    nums[2] = available.remove(0);
                                    if ((nums[2] * 100 + nums[3] * 10 + nums[4]) % 3 == 0)
                                    {
                                       for (int i1 = 0; i1 < available.size(); i1++)
                                       {
                                          nums[1] = available.remove(0);
                                          if (nums[3] % 2 == 0)
                                          {
                                             nums[0] = available.remove(0); // found one!
                                             long num = 0;
                                             for (int ii = 0; ii < 10; ii++)
                                             {
                                                num *= 10;
                                                num += nums[ii];
                                             }
                                             value += num;
                                             available.add(nums[0]);
                                          }
                                          available.add(nums[1]);
                                       }
                                    }
                                    available.add(nums[2]);
                                 }
                              }
                              available.add(nums[3]);
                           }
                        }
                        available.add(nums[4]);
                     }
                  }
                  available.add(nums[5]);
               }
            }
            available.add(nums[6]);
         }
      }
      return value;
   }
   
   private int[] primes = new int[]{2,3,5,7,11,13,17};

   @Override
   protected void process(List<Integer> arr)
   {
      long bigNum = arr.get(0);;
      
      for (int ii = 1; ii <= 7; ii++)
      {
         int num = 0;
         bigNum *= 10;
         bigNum += arr.get(ii);
         for (int jj = 0; jj < 3; jj++)
         {
            num *= 10;
            num += arr.get(ii+jj);
         }
         if (num%primes[ii-1] != 0)
         {
            return;
         }
      }
      for (int ii = 8; ii <= 9; ii++)
      {
         bigNum *= 10;
         bigNum += arr.get(ii);
      }
      result += bigNum;
   }
}
