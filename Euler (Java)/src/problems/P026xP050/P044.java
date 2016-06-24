package problems.P026xP050;

import java.util.ArrayList;
import java.util.List;

import util.Basic;
import base.Problem;

/**
 * P044<br>
 * Pentagonal numbers are generated by the formula, Pn=n(3n−1)/2. The first ten pentagonal numbers are:
 * 
 * 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
 * 
 * It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference, 70 − 22 = 48, is not pentagonal.
 * 
 * Find the pair of pentagonal numbers, Pj and Pk, for which their sum and difference are pentagonal and 
 * D = |Pk − Pj| is minimised; what is the value of D?
 * Ans: 5482660
 * @return
 */
public class P044 extends Problem
{
   public P044()
   {
      problemNumber = 44;
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
    * This assumes that the first one we find is it. Not a great assumption, but these are rare, so seems to work.
    * @return
    */
   private long attempt()
   {
   	long result = Long.MAX_VALUE;
   	
   	List<Long> pentagonals = new ArrayList<Long>();
   	
		for (int idx = 1;; idx++)
   	{
		   long a = Basic.getPentagonalNumber(idx);
   	   pentagonals.add(a);
   	
      	for (int ii = 0; ii < pentagonals.size() - 1; ii++)
      	{
      	   long b = pentagonals.get(ii);
   	      if (Basic.isPentagonalNumber(a+b) && Basic.isPentagonalNumber(a-b) && a-b < result)
   	      {
   	         result = a-b;
   	         return result;
   	      }
   	   }
   	}
   }
   
}
