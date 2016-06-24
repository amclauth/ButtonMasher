package problems.P051xP075;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Combinatorics;


/**
 * P068<br>
 * Consider the following "magic" 3-gon ring, filled with the numbers 1 to 6,
 * and each line adding to nine.
 * 
 * 
 * Working clockwise, and starting from the group of three with the numerically
 * lowest external node (4,3,2 in this example), each solution can be described
 * uniquely. For example, the above solution can be described by the set: 4,3,2;
 * 6,2,1; 5,1,3.
 * 
 * It is possible to complete the ring with four different totals: 9, 10, 11,
 * and 12. There are eight solutions in total.
 * 
 * Total Solution Set
 * 9 4,2,3; 5,3,1; 6,1,2
 * 9 4,3,2; 6,2,1; 5,1,3
 * 10 2,3,5; 4,5,1; 6,1,3
 * 10 2,5,3; 6,3,1; 4,1,5
 * 11 1,4,6; 3,6,2; 5,2,4
 * 11 1,6,4; 5,4,2; 3,2,6
 * 12 1,5,6; 2,6,4; 3,4,5
 * 12 1,6,5; 3,5,4; 2,4,6
 * By concatenating each group it is possible to form 9-digit strings; the
 * maximum string for a 3-gon ring is 432621513.
 * 
 * Using the numbers 1 to 10, and depending on arrangements, it is possible to
 * form 16- and 17-digit strings. What is the maximum 16-digit string for a
 * "magic" 5-gon ring?
 * Ans:6531031914842725
 * 
 * @return
 */
public class P068 extends Combinatorics<Integer>
{
   public P068()
   {
      problemNumber = Integer.valueOf(this.getClass().getSimpleName().substring(1));
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
   
   /**
    * Try to iterate only over the possible numbers. Start with 6 in the outer position. That's
    * the highest it could be (if it were 6 through 10 in the outer numbers, we'd still pick
    * 6 in the lowest position). Technically, we should try the others as well, but see if there's
    * a solution here first. 
    * @return
    */
   public String attempt2()
   {
      List<Integer> nums = new ArrayList<Integer>();
      List<Integer> used = new ArrayList<Integer>();
      for (int ii = 1; ii <= 10; ii++)
      {
         nums.add(ii);
      }
      
      List<String> answers = new ArrayList<String>();
      
      int p5 = 6;
      used.add(p5);
      
      for (int p0 : nums)
      {
         if (used.contains(p0))
            continue;
         used.add(p0);
         
         for (int p1 : nums)
         {
            if (used.contains(p1))
               continue;
            used.add(p1);
            
            int sum = p5 + p0 + p1;
            
            for (int p6 : nums)
            {
               if (p6 < p5)
                  continue;
               
               if (used.contains(p6))
                  continue;

               int p2 = sum - p6 - p1;
               
               if (p2 == p6)
                  continue;
               
               if (used.contains(p2))
                  continue;
               
               if (p2 > 10 || p2 < 1)
                  continue;

               used.add(p6);
               used.add(p2); // in p2
               
               for (int p7 : nums)
               {
                  if (p7 < p5)
                     continue;
                  
                  if (used.contains(p7))
                     continue;

                  int p3 = sum - p7 - p2;
                  
                  if (p3 == p7)
                     continue;
                  
                  if (used.contains(p3))
                     continue;
                  
                  if (p3 > 10 || p3 < 1)
                     continue;
                  
                  used.add(p7);
                  used.add(p3);
                  
                  for (int p8 : nums)
                  {
                     if (p8 < p5)
                        continue;
                     
                     if (used.contains(p8))
                        continue;

                     int p4 = sum - p8 - p3;
                     int p9 = sum - p0 - p4;
                     
                     if (p4 == p8 || p9 == p8 || p4 == p9)
                        continue;
                     
                     if (p9 < p5)
                        continue;
                     
                     if (p4 > 10 || p4 < 1)
                        continue;
                     
                     if (p9 > 10 || p9 < 1)
                        continue;
                     
                     if (used.contains(p4) || used.contains(p9))
                        continue;
                     
                     // have a match
                     String t = 
                           p5 + "" + p0 + "" + p1 + "" + 
                           p6 + "" + p1 + "" + p2 + "" + 
                           p7 + "" + p2 + "" + p3 + "" + 
                           p8 + "" + p3 + "" + p4 + "" + 
                           p9 + "" + p4 + "" + p0 + "";
                     if (t.length() == 16)
                     {
                        answers.add(t);
                     }
                  }
                  
                  used.remove(Integer.valueOf(p7));
                  used.remove(Integer.valueOf(p3));
               }
               
               used.remove(Integer.valueOf(p6));
               used.remove(Integer.valueOf(p2));
            }
            
            used.remove(Integer.valueOf(p1));
         }
         
         used.remove(Integer.valueOf(p0));
      }
      
      Collections.sort(answers);
      
      return answers.get(answers.size()-1);
      
   }
   
   Set<String> solutions;
   
   /**
    * Try all permutations of all possible numbers
    * @return
    */
   public String attempt()
   {
      List<Integer> nums = new ArrayList<Integer>();
      for (int ii = 0; ii <= 9; ii++) // so we don't have sums messed up
         nums.add(ii);
      
      solutions = new HashSet<String>();
      
      permute(nums);
      
      List<String> answers = new ArrayList<String>();
      answers.addAll(solutions);
      Collections.sort(answers);
      
      return answers.get(answers.size()-1);
   }
   
   @Override
   protected void process(List<Integer> nums)
   {
      int[] sums = new int[5];
      boolean found = true;
      int lowestIdx = 0;
      int lowest = 999;
      for (int ii = 0; ii < 5; ii++)
      {
         sums[ii] = nums.get(5+ii) + nums.get(ii) + nums.get((ii+1)%5);
         if (ii > 0 && sums[ii] != sums[ii-1])
         {
            found = false;
            break;
         }
         int mult = nums.get(5+ii)*100 + nums.get(ii)*10 + nums.get((ii+1)%5);
         if (mult < lowest)
         {
            lowest = mult;
            lowestIdx = ii;
         }
      }
      
      if (!found)
         return;
      
      // have a magic set!
      String t = "";
      for (int ii = 0; ii < 5; ii++)
      {
         String s = (nums.get(5+(ii+lowestIdx)%5)+1) + "" + 
                     (nums.get((ii+lowestIdx)%5)+1) + "" + 
                     (nums.get((ii+1+lowestIdx)%5)+1);
         t += s;
      }
      if (t.length() == 16)
         solutions.add(t);
   }
   
}
