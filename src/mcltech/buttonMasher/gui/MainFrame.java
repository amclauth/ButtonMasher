package mcltech.buttonMasher.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
   private static final long serialVersionUID = 1L;

   private String guiTitle = "ButtonMasher 1.0";
   private int windowHeight;
   private int windowWidth;
   private int windowXPos;
   private int windowYPos;

   public MainFrame()
   {
      initComponents();
      setVisible(true);
   }

   private void initComponents()
   {
      getSettings();

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocation(windowXPos, windowYPos);
      setTitle(guiTitle);

      JPanel activePanel = null;
      try
      {
         activePanel = new ActivePanel();
      }
      catch (URISyntaxException e)
      {
         e.printStackTrace();
      }
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(activePanel, BorderLayout.CENTER);

      pack();
   }

   private void getSettings()
   {
      // Figure out some default values
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      windowWidth = 400;

      windowHeight = 100;
      windowXPos = (dim.width - windowWidth) / 2;
      windowYPos = (dim.height - windowHeight) / 2;
   }
}