package problems.P026xP050;

import base.Problem;

/**
 * P040<br>
 * An irrational decimal fraction is created by concatenating the positive integers:
 * 0.123456789101112131415161718192021...
 * It can be seen that the 12th digit of the fractional part is 1.
 * If dn represents the nth digit of the fractional part, find the value of the following expression.
 * d1 x d10 x d100 x d1000 x d10000 x d100000 x d1000000
 * Ans: 210
 * @return
 */
public class P040 extends Problem
{
   public P040()
   {
      problemNumber = 40;
      variations = 2;
   }

   @Override
   protected String execute(int variation)
   {
      switch (variation)
      {
         case 0:
            return bruteForce() + "";
         case -1:
         case 1:
            return faster() + "";
      }
      return null;
   }

   /**
    * Just run through all the numbers and figure out which digits are which
    * @return
    */
   private long bruteForce()
   {
   	long result = 1;
   	int[] digits = new int[]{1,100,1000,10000,100000,1000000};
   	int num = 1;
   	int index = 1;
   	int level = 1;
   	while (index < digits.length)
   	{
   		num++;
   		// add the length of the next number
   		level += (int)Math.log10(num) + 1;
   		if (level > digits[index])
   		{
   			// how far back do we need to go?
   			int dist = level - digits[index];
   			int pow = 1;
   			for (int ii = 0; ii < dist; ii++)
   			{
   				pow *= 10;
   			}
   			result *= (num / pow) % 10;
   			index++;
   		}
   	}
   	
   	return result;
   }
   
   /**
    * Figure out the answer numerically to each of the digit locations
    * @return
    */
   private long faster()
   {
   	int[] digits = new int[] {9, 90*2, 900*3, 9000*4, 90000*5, 900000*6};
   	int answer = 1;
   	for (int ii = 1; ii <= 1000000; ii *= 10)
   	{
   		answer *= digit(ii,digits);
   	}
   	return answer;
   }
   
   /**
    * Provided the nth number we're searching for, and how many digits are
    * in each "cluster" (how many in the single digit numbers (9*1), how many 2 
    * total (90*2 digits), etc.
	 * We then take our number, and subtract away digits until we're in the 
	 * right "decade" of numbers. We can then find out which number it is in 
	 * that series, and which digit of those numbers. Then we simply calculate
	 * the number itself by getting the base plus the numberOrder and get the
	 * digit from that number.
    * @param n
    * @param digits
    * @return
    */
   private int digit(int n, int[] digits)
   {
   	int ii = 0;
   	int base = 1;
   	int digitsPerNumber = 1;
   	while (n > digits[ii])
   	{
   		n -= digits[ii++];
   		base *= 10;
   		digitsPerNumber++;
   	}
   	int numberOrder = (n-1) / digitsPerNumber + 1;
   	int digitOrder = digitsPerNumber - (n-1) % digitsPerNumber - 1;
   	int number = base + numberOrder - 1;
   	for (int jj = 0; jj < digitOrder; jj++)
   		number = number / 10;
   	return number % 10;
   }
}
