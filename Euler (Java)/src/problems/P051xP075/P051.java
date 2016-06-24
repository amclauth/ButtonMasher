package problems.P051xP075;

import java.util.ArrayList;
import java.util.List;

import util.Primes;
import base.Problem;

/**
 * P051<br>
 * By replacing the 1st digit of *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes 
 * among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003, 
 * being the first member of this family, is the smallest prime with this property.
 * Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part 
 * of an eight prime value family.
 * Ans: 121313
 * @return
 */
public class P051 extends Problem
{
   public P051()
   {
      problemNumber = 51;
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
   // Let's look at what happens when digits get replaced. If we replace only one digit with 0->9, one of those is
   // guaranteed to be prime. However, since any number whose sum of digits is divisible by 3 is also divisible by
   // 3, we also know that at least 3 of those new numbers will be divisible by 3, which means it can't make a 
   // family of 8. How about if two numbers are replaced? We'll add (or subtract ... we only care about the mod 
   // value, not the total) 0,2,4,6,8,10,12,14,16,18. If we assume that our current value is %3 != 0, then we know
   // either +1*3n or -1*3n is %3==0. So 0,6,12,and 18 are ok. 2,8, and 14 are -1, and 4,10,12, and 16 are +1. So
   // again, either way, it can't be two numbers replaced. If we replace 3 numbers (0,3,6,9...) all the numbers stay
   // the same %3 ... this won't be divisible by 3 at all!. How about replacing 4 numbers? It's the same as 2 (not
   // surprising). 5 numbers? 0,5,10,15,20,25,30,35,40,45,50 ... we have 0,15,30,45 safe. -1 is 5,20,35, leaving 3 in
   // the +1 category. So again, no family of 8 possible. We must replace 3 numbers.
   //
   // We also know that we can't replace the last number. At some point, it would equal 2,4,5,8, and 0, none of which
   // can be prime.
   //
   // So we know we have to replace 3 numbers, and it can't be the last number. We need a 4 digit or higher prime. 
   // The first numbers, 4 digit, 5 digit, and 6 digit have realistic numbers of discreet tests we can perform ... or
   // "masks" we can apply, and it's likely to be one of those, so we'll start there. We could also test all combinations
   // of three "true" masks for any "n" digits, but we'll write them by hand for now. Also, after 6 digits, the 
   // possibility of 6 true masks for a 7 digit number comes up, so we'd need multiples of 3 where mMask*3x < n.
   public long attempt()
   {
      // create these by hand
      List<Boolean[]> mask4 = new ArrayList<Boolean[]>();
      mask4.add(new Boolean[]{true,true,true,false});
      List<Boolean[]> mask5 = new ArrayList<Boolean[]>();
      mask5.add(new Boolean[]{true,true,true,false,false});
      mask5.add(new Boolean[]{true,true,false,true,false});
      mask5.add(new Boolean[]{true,false,true,true,false});
      mask5.add(new Boolean[]{false,true,true,true,false});
      List<Boolean[]> mask6 = new ArrayList<Boolean[]>();
      mask6.add(new Boolean[]{true,false,true,false,true,false});
      mask6.add(new Boolean[]{false,false,true,true,true,false});
      mask6.add(new Boolean[]{false,true,true,true,false,false});
      mask6.add(new Boolean[]{false,true,true,false,true,false});
      mask6.add(new Boolean[]{false,true,false,true,true,false});
      mask6.add(new Boolean[]{true,true,true,false,false,false});
      mask6.add(new Boolean[]{true,true,false,true,false,false});
      mask6.add(new Boolean[]{true,true,false,false,true,false});
      mask6.add(new Boolean[]{true,false,true,true,false,false});
      mask6.add(new Boolean[]{true,false,false,true,true,false});
      
      int[] finalDigit = new int[]{1,3,7,9};
      int[] intermediateDigit = new int[]{0,1,2,3,4,5,6,7,8,9};
      
      // 4 digit
      List<Integer> nums = new ArrayList<Integer>();
      nums.add(0);
      nums.add(0);
      nums.add(0);
      nums.add(0);
      for (int d : finalDigit)
      {
         int count = 0;
         int lowest = 0;
         for (int ii = 0; ii <= 9; ii++)
         {
            if (ii - count > 2)
               break;
            List<Integer> num = mask(mask4.get(0),ii,nums,d);
            if (num != null) {
               int nprime = 0;
               for (int jj = 0; jj < nums.size(); jj++)
               {
                  nprime *= 10;
                  nprime += num.get(jj);
               }
               if (Primes.isPrime(nprime))
               {
                  if (lowest == 0)
                     lowest = nprime;
                  count++;
               }  
            }
         }
         if (count >= 8)
            return lowest;
      }
      
      // 5 digit
      nums.add(0);
      for (int d : finalDigit)
      {
         for (int e1 : intermediateDigit)
         {
            for (Boolean[] mask : mask5)
            {
               int count = 0;
               int lowest = 0;
               for (int ii = 0; ii <= 9; ii++)
               {
                  if (ii - count > 2)
                     break;
                  List<Integer> num = mask(mask,ii,nums,e1,d);
                  if (num != null) {
                     int nprime = 0;
                     for (int jj = 0; jj < nums.size(); jj++)
                     {
                        nprime *= 10;
                        nprime += num.get(jj);
                     }
                     if (Primes.isPrime(nprime))
                     {
                        if (lowest == 0)
                           lowest = nprime;
                        count++;
                     }  
                  }
               }
               if (count >= 8)
                  return lowest;
            }
         }
      }
      
      // 6 digit
      nums.add(0);
      for (int d : finalDigit)
      {
         for (int e1 : intermediateDigit)
         {
            for (int e2 : intermediateDigit)
            {
               for (Boolean[] mask : mask6)
               {
                  int count = 0;
                  int lowest = 0;
                  for (int ii = 0; ii <= 9; ii++)
                  {
                     if (ii - count > 2)
                        break;
                     List<Integer> num = mask(mask,ii,nums,e1,e2,d);
                     if (num != null) {
                        int nprime = 0;
                        for (int jj = 0; jj < nums.size(); jj++)
                        {
                           nprime *= 10;
                           nprime += num.get(jj);
                        }
                        if (Primes.isPrime(nprime))
                        {
                           if (lowest == 0)
                              lowest = nprime;
                           count++;
                        }
                     }
                  }
                  if (count >= 8)
                     return lowest;
               }
            }
         }
      }
         
      
      return 0;
   }
   
   private List<Integer> mask(Boolean[] mask, int n, List<Integer> nums, int... digits)
   {
      List<Integer> num = new ArrayList<Integer>(nums);
      int ii = 0;
      if ((n == 0 && mask[0]) || (digits[0] == 0 && !mask[0]))
         return null; // no leading 0
      for (int jj = 0; jj < mask.length; jj++)
         if (mask[jj])
            num.set(jj,n);
         else
            num.set(jj,digits[ii++]);
      return num;
   }
   
}
