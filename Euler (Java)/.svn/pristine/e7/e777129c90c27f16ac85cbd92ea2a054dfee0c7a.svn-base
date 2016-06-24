package problems.P026xP050;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import util.Basic;
import base.Problem;

/**
 * P042<br>
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
 * 
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * 
 * By converting each letter in a word to a number corresponding to its alphabetical position and adding these values 
 * we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word value is a triangle 
 * number then we shall call the word a triangle word.
 * 
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common 
 * English words, how many are triangle words?
 * Ans: 162
 * @return
 */
public class P042 extends Problem
{
   public P042()
   {
      problemNumber = 42;
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
   
   /**
    * Just run through all the numbers and figure out which digits are which
    * @return
    */
   private int attempt1()
   {
   	int result = 0;
   	
      try {
         InputStream in = new FileInputStream("data/words.txt");
         Reader reader = new InputStreamReader(in, Charset.defaultCharset());
         Reader buffer = new BufferedReader(reader);
         int r = 0;
         int val = 0;
         while ((r = buffer.read()) != -1) {
            char c = (char) r;
            if (c == '"') {
               continue;
            }
            if (c == ',') {
               if (Basic.isTriangleNumber(val))
                  result++;
               val = 0;
            } else {
               val += c - 'A' + 1;
            }
         }
         if (Basic.isTriangleNumber(val))
            result++;
         in.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   	
   	return result;
   }
}
