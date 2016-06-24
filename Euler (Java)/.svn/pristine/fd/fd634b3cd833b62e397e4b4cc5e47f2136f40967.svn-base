package problems.P026xP050;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import base.Problem;

/**
 * P048<br>
 * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 * Ans: 9110846700
 * @return
 */
public class P048 extends Problem
{
   public P048()
   {
      problemNumber = 48;
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
   private String attempt()
   {
      BigInteger answer = BigInteger.ZERO;
      for (int num = 1; num <= 1000; num++)
      {
         BigInteger myVal = BigInteger.ONE;
         // generate a map of the powers of 2 for this number
         Map<Integer,BigInteger> multMap = new HashMap<Integer,BigInteger>();
         multMap.put(1, BigInteger.valueOf(num));
         for (int ii = 2; ii <= num; ii*=2)
         {
            multMap.put(ii, multMap.get(ii/2).multiply(multMap.get(ii/2)));
         }
         
         // now find the correct sum of those powers of 2 to multiply ... eg:
         // 3^10 = 3^8 + 3^2; 10 -> binary = 1010, use that as a mask
         for (int ii = 1; ii <= num; ii *= 2)
         {
            int n = (num & ii);
            if (n == ii) // this bit is set
            {
               myVal = myVal.multiply(multMap.get(ii));
            }
         }
         answer = answer.add(myVal);
      }
      
      String retVal = "" + answer.mod(BigInteger.valueOf(10000000000L));
      while (retVal.length() < 10)
         retVal = "0" + retVal;
      
      return retVal;
   }
   
}
