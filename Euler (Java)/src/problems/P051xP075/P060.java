package problems.P051xP075;

import java.util.ArrayList;
import java.util.List;

import util.Primes;
import base.Problem;

/**
 * P060<br>
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes
 * and concatenating them in any order the result will always be prime. For
 * example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these
 * four primes, 792, represents the lowest sum for a set of four primes with
 * this property.
 * 
 * Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 * Ans:26033
 * 
 * @return
 */
public class P060 extends Problem
{
   public P060()
   {
      problemNumber = 60;
      variations = 3;
   }
   
   @Override
   protected String execute(int variation)
   {
      switch (variation)
      {
         case 0:
            return attempt() + "";
         case 1:
            return attempt2() + "";
         case 2:
         case -1:
            return attempt3() + "";
      }
      return null;
   }
   
   /**
    * Just go through all of them, testing each combination to see if it works. Heavy. Picked 
    * 10k as a limit which works (and tested higher to see if it's the smallest). I constantly 
    * check to see if we're beyond where the next set of primes could work, though that didn't
    * save a lot of time (testing testsum).
    * @return
    */
   public int attempt()
   {
      List<Integer> primes = Primes.EratosthenesPrimesUpTo(10000);
      List<Integer> known = new ArrayList<Integer>(5);
      int sum = Integer.MAX_VALUE;
      boolean found;
      for (int a = 0; a < primes.size()-4; a++)
      {
         int pa = primes.get(a);
         int testsum = pa;
         known.add(pa);
         for (int b = a+1; b < primes.size()-3; b++)
         {
            int pb = primes.get(b);
            found = false;
            for (int p : known)
               if (testsum + 4*pb >= sum || !Primes.isPrime(concat(p,pb)) || !Primes.isPrime(concat(pb,p)))
               {
                  found = true;
                  break;
               }
            if (found)
               continue;
            
            known.add(pb);
            testsum += pb;
            
            for (int c = b+1; c < primes.size()-2; c++)
            {
               int pc = primes.get(c);
               found = false;
               for (int p : known)
                  if (testsum + 3*pc >= sum || !Primes.isPrime(concat(p,pc)) || !Primes.isPrime(concat(pc,p)))
                  {
                     found = true;
                     break;
                  }
               if (found)
                  continue;
               
               known.add(pc);
               testsum += pc;
               
               for (int d = c+1; d < primes.size()-1; d++)
               {
                  int pd = primes.get(d);
                  found = false;
                  for (int p : known)
                     if (testsum + 2*pd >= sum || !Primes.isPrime(concat(p,pd)) || !Primes.isPrime(concat(pd,p)))
                     {
                        found = true;
                        break;
                     }
                  if (found)
                     continue;
                  
                  known.add(pd);
                  testsum += pd;
                  
                  for (int e = d+1; e < primes.size(); e++)
                  {
                     int pe = primes.get(e);
                     found = false;
                     for (int p : known)
                        if (testsum + pe >= sum || !Primes.isPrime(concat(p,pe)) || !Primes.isPrime(concat(pe,p)))
                        {
                           found = true;
                           break;
                        }
                     if (found)
                        continue;
                     
                     known.add(pe);
                     testsum += pe;
                     
                     // otherwise, we have a set that's prime!
//                     System.out.println(testSum + " ::: " + Arrays.toString(known.toArray()));
                     sum = testsum;
                     
                     known.remove(4);
                     testsum -= pe;
                  }
                  known.remove(3);
                  testsum -= pd;
               }
               known.remove(2);
               testsum -= pc;
            }
            known.remove(1);
            testsum -= pb;
         }
         known.remove(0);
      }
      return sum;
   }
   
   /**
    * Make an array of the combinations that are valid first. Prime testing is slow.
    * @return
    */
   public int attempt2()
   {
      List<Integer> primes = Primes.EratosthenesPrimesUpTo(10000);
      int sum = Integer.MAX_VALUE;
      boolean[][] test = new boolean[primes.size()][primes.size()];
      for (int ii = 0; ii < primes.size()-1; ii++)
      {
         for (int jj = ii+1; jj < primes.size(); jj++)
         {
            if (Primes.isPrime(concat(primes.get(ii), primes.get(jj))) && Primes.isPrime(concat(primes.get(jj), primes.get(ii))))
            {
               test[ii][jj] = true;
            }
            else
            {
               test[ii][jj] = false;
            }
         }
      }
      
      for (int a = 0; a < primes.size()-4; a++)
      {
         int pa = primes.get(a);
         int testsum = pa;
         for (int b = a+1; b < primes.size()-3; b++)
         {
            int pb = primes.get(b);
            if (!test[a][b] || testsum + 4*pb >= sum)
               continue;
            testsum += pb;
            for (int c = b+1; c < primes.size()-2; c++)
            {
               int pc = primes.get(c);
               if (!test[a][c] || !test[b][c] || testsum + 3*pc >= sum)
                  continue;
               testsum += pc;
               for (int d = c+1; d < primes.size()-1; d++)
               {
                  int pd = primes.get(d);
                  if (!test[a][d] || !test[b][d] || !test[c][d] || testsum + 2*pd >= sum)
                     continue;
                  testsum += pd;
                  for (int e = d+1; e < primes.size(); e++)
                  {
                     int pe = primes.get(e);
                     if (!test[a][e] || !test[b][e] || !test[c][e] || !test[d][e] || testsum + pe >= sum)
                        continue;
                     testsum += pe;
                     
                     // found it!
                     sum = testsum;
                     
                     testsum -= pe;
                  }
                  testsum -= pd;
               }
               testsum -= pc;
            }
            testsum -= pb;
         }
      }
      
      return sum;
   }
   
   /**
    * Try splitting out the tests into primes that are %3=2 and %3=1 because if you combine one that's
    * %3=2 with one that's %3=1, you'll get a %3=3, which won't be prime. We can divide the
    * size of the search in half this way. Doesn't end up helping hardly at all.
    * @return
    */
   public int attempt3()
   {
      List<Integer> primes = Primes.EratosthenesPrimesUpTo(10000);
      int sum = Integer.MAX_VALUE;
      
      List<Integer> m2 = new ArrayList<Integer>();
      List<Integer> m1 = new ArrayList<Integer>();
      for (int p : primes)
      {
         if (p % 3 == 1)
            m1.add(p);
         else
            m2.add(p);   
      }
      
      boolean[][] testm1 = new boolean[m1.size()][m1.size()];
      for (int ii = 0; ii < m1.size()-1; ii++)
      {
         for (int jj = ii+1; jj < m1.size(); jj++)
         {
            if (Primes.isPrime(concat(m1.get(ii), m1.get(jj))) && Primes.isPrime(concat(m1.get(jj), m1.get(ii))))
            {
               testm1[ii][jj] = true;
            }
            else
            {
               testm1[ii][jj] = false;
            }
         }
      }
      
      boolean[][] testm2 = new boolean[m2.size()][m2.size()];
      for (int ii = 0; ii < m2.size()-1; ii++)
      {
         for (int jj = ii+1; jj < m2.size(); jj++)
         {
            if (Primes.isPrime(concat(m2.get(ii), m2.get(jj))) && Primes.isPrime(concat(m2.get(jj), m2.get(ii))))
            {
               testm2[ii][jj] = true;
            }
            else
            {
               testm2[ii][jj] = false;
            }
         }
      }
      
      for (int a = 0; a < m1.size()-4; a++)
      {
         int pa = m1.get(a);
         int testsum = pa;
         for (int b = a+1; b < m1.size()-3; b++)
         {
            int pb = m1.get(b);
            if (!testm1[a][b] || testsum + 4*pb >= sum)
               continue;
            testsum += pb;
            for (int c = b+1; c < m1.size()-2; c++)
            {
               int pc = m1.get(c);
               if (!testm1[a][c] || !testm1[b][c] || testsum + 3*pc >= sum)
                  continue;
               testsum += pc;
               for (int d = c+1; d < m1.size()-1; d++)
               {
                  int pd = m1.get(d);
                  if (!testm1[a][d] || !testm1[b][d] || !testm1[c][d] || testsum + 2*pd >= sum)
                     continue;
                  testsum += pd;
                  for (int e = d+1; e < m1.size(); e++)
                  {
                     int pe = m1.get(e);
                     if (!testm1[a][e] || !testm1[b][e] || !testm1[c][e] || !testm1[d][e] || testsum + pe >= sum)
                        continue;
                     testsum += pe;
                     
                     // found it!
                     sum = testsum;
                     
                     testsum -= pe;
                  }
                  testsum -= pd;
               }
               testsum -= pc;
            }
            testsum -= pb;
         }
      }
      
      for (int a = 0; a < m2.size()-4; a++)
      {
         int pa = m2.get(a);
         int testsum = pa;
         for (int b = a+1; b < m2.size()-3; b++)
         {
            int pb = m2.get(b);
            if (!testm2[a][b] || testsum + 4*pb >= sum)
               continue;
            testsum += pb;
            for (int c = b+1; c < m2.size()-2; c++)
            {
               int pc = m2.get(c);
               if (!testm2[a][c] || !testm2[b][c] || testsum + 3*pc >= sum)
                  continue;
               testsum += pc;
               for (int d = c+1; d < m2.size()-1; d++)
               {
                  int pd = m2.get(d);
                  if (!testm2[a][d] || !testm2[b][d] || !testm2[c][d] || testsum + 2*pd >= sum)
                     continue;
                  testsum += pd;
                  for (int e = d+1; e < m2.size(); e++)
                  {
                     int pe = m2.get(e);
                     if (!testm2[a][e] || !testm2[b][e] || !testm2[c][e] || !testm2[d][e] || testsum + pe >= sum)
                        continue;
                     testsum += pe;
                     
                     // found it!
                     sum = testsum;
                     
                     testsum -= pe;
                  }
                  testsum -= pd;
               }
               testsum -= pc;
            }
            testsum -= pb;
         }
      }
      
      return sum;
   }
   
   private int concat(int a, int b)
   {
      int m = 1;
      while (b/m != 0)
         m *= 10;
      return a*m+b;
   }
}
