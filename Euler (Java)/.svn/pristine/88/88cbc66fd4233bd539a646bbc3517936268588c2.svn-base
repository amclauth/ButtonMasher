package problems.P051xP075;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.Problem;

/**
 * P062<br>
 * The cube, 41063625 (345^3), can be permuted to produce two other cubes:
 * 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube
 * which has exactly three permutations of its digits which are also cube.
 * 
 * Find the smallest cube for which exactly five permutations of its digits are
 * cube.
 * Ans:127035954683
 * 
 * @return
 */
public class P062 extends Problem
{
   public P062()
   {
      problemNumber = 62;
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
    * instead of list of cubes, make a list of numbers size 10. Each list is the number of 
    * each decimal digit, so 41063625 would be {1,1,1,1,1,1,2,0,0,0}. We only care that 
    * a permutation can get us there. This works for 5 since there's only one. Otherwise,
    * should check that the log10 of the numbers are the same (and can reset the hashmap
    * at each log10 increase)
    * @return
    */
   public long attempt()
   {
      Map<Long,List<Long>> cubes = new HashMap<Long,List<Long>>();
      
      long max = Long.MAX_VALUE;
      max = (long)Math.cbrt(max);
      for (long ii = 3; ii <= max; ii++)
      {
         long c = ii*ii*ii;
         long p = process(c);
         if (!cubes.containsKey(p))
            cubes.put(p, new ArrayList<Long>());
         cubes.get(p).add(c);
         if (cubes.get(p).size() == 5)
            return Collections.min(cubes.get(p));
      }
      
      return 0;
   }
   
   // 
   static final long[] powers = {10000000000L,1000000000,100000000,10000000,1000000,100000,10000,1000,100,10,1};
   
   private long process(long n)
   {
      long p = 0;
      while (n != 0)
      {
         p += powers[(int)(n%10L)];
         n = n/10;
      }
      return p;
   }
}
