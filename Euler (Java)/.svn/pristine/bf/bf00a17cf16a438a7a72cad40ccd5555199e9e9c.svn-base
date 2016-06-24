package problems.P051xP075;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import util.IO;
import base.Problem;

/**
 * P067<br>
 * By starting at the top of the triangle below and moving to adjacent numbers
 * on the row below, the maximum total from top to bottom is 23.
 * 
 * 3
 * 7 4
 * 2 4 6
 * 8 5 9 3
 * 
 * That is, 3 + 7 + 4 + 9 = 23.
 * 
 * Find the maximum total from top to bottom in triangle.txt (right click and
 * 'Save Link/Target As...'), a 15K text file containing a triangle with
 * one-hundred rows.
 * 
 * NOTE: This is a much more difficult version of Problem 18. It is not possible
 * to try every route to solve this problem, as there are 2^99 altogether! If
 * you
 * could check one trillion (1012) routes every second it would take over twenty
 * billion years to check them all. There is an efficient algorithm to solve it.
 * ;o)
 * Ans:7273
 * 
 * @return
 */
public class P067 extends Problem
{
   public P067()
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
    * 
    * @return
    */
   public long attempt()
   {

      int[][] triangle = new int[100][100];
      try
      {
         BufferedReader buffer = new BufferedReader(new FileReader("data/triangle.txt"));
         String line = "";
         int idx = 0;
         while ((line = buffer.readLine()) != null)
         {
            int[] trow = IO.readIntoArray(line, " ");
            for (int ii = 0; ii < trow.length; ii++)
               triangle[idx][ii] = trow[ii];
            idx++;
         }
         buffer.close();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      
      for (int row = 98; row >= 0; row--)
      {
         for (int col = 0; col <= row; col++)
         {
            // this should add the higher of the two lower options
            triangle[row][col] += (triangle[row+1][col] > triangle[row+1][col+1] ? triangle[row+1][col] : triangle[row+1][col+1]);
         }
      }
      return triangle[0][0];
   }
   
}
