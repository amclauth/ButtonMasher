package mcltech.buttonMasher;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ClickBot
{
   private Robot robot;
   private final String filename = "config/config.txt";
   private final String separator = ":::";
   private List<Timer> timers;

   public ClickBot()
   {
      robot = null;
      try
      {
         robot = new Robot();
      }
      catch (AWTException e)
      {
         System.out.println("ERROR: Could not initialize Robot");
         e.printStackTrace();
      }
   }

   /**
    * Stop button mashing and clear the config out of memory.
    */
   public void stopRobot()
   {
      System.out.println("Stopping Robot");
      cleanTimers();
   }
   
   /**
    * Load the config file and start executing button mashing. The config re-loads
    * on every start, so it can be updated between start / stop cycles.
    */
   public void startRobot()
   {
      System.out.println("Starting Robot");
      cleanTimers(); // just in case something went wrong and we still have timers
      
      // read the config and load values into char/double map for character / seconds
      Map<Character, Double> charMap = readConfig();
      
      timers = new ArrayList<Timer>();
      for (Character c : charMap.keySet())
      {
         int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
         pressKey(c, keyCode);
         addTimer(keyCode, c, (long)(charMap.get(c)*1000));
      }
   }
   
   private Map<Character, Double> readConfig()
   {
      Map<Character,Double> charMap = new HashMap<>();
      try (BufferedReader br = new BufferedReader(new FileReader(filename)))
      {
         String line;
         while ((line = br.readLine()) != null)
         {
            if (!line.contains(separator))
            {
               continue;
            }
            String[] arr = line.split(separator,2);
            if (arr.length != 2)
            {
               System.out.println("ERROR: Config line [" + line + "] is incorrect.");
               System.out.println("       line should be character:::seconds ... for example, q:::33.5");
            }
            if (arr[0].length() != 1)
            {
               System.out.println("ERROR: Config line [" + line + "] needs a single character as the first element.");
               System.out.println("       line should be character:::seconds ... for example, q:::33.5");
            }
            double seconds = Double.valueOf(arr[1]);
            charMap.put(arr[0].charAt(0), seconds);
         }
      }
      catch (IOException e)
      {
         System.out.println("Couldn't read file " + filename);
         e.printStackTrace();
         return charMap;
      }
      catch (NumberFormatException e)
      {
         System.out.println("ERROR: Failed converting a number in the config");
         e.printStackTrace();
         return charMap;
      }
      
      if (charMap.size() == 0)
      {
         System.out.println("ERROR: No config loaded.");
         System.out.println("Config lines should be character:::seconds ... for example, q:::33.5");
         System.out.println("Save in " + filename);
         return charMap;
      }
      
      return charMap;
   }
   
   private void addTimer(int keyCode, Character c, long msDelay)
   {
      int randomness = (int) (Math.random()*3000);
      Timer t = new Timer();
      t.schedule(new TimerTask() {
         @Override
         public void run()
         {
            pressKey(c, keyCode);
            addTimer(keyCode, c, msDelay);
            timers.remove(this);
         }
      }, msDelay+randomness);
      timers.add(t);
   }
   
   private void pressKey(Character c, int keyCode)
   {
      System.out.println("Clicking " + c + " [" + keyCode + "]");
      robot.keyPress(keyCode);
      robot.delay(5+(int)(Math.random()*5));
      robot.keyRelease(keyCode);
   }
   
   private void cleanTimers()
   {
      if (timers != null)
      {
         for (Timer t : timers)
         {
            t.cancel();
         }
      }
      timers = new ArrayList<Timer>();
   }
}
