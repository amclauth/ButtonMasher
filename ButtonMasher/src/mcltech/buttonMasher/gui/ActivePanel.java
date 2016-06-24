package mcltech.buttonMasher.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mcltech.buttonMasher.ClickBot;

public class ActivePanel extends JPanel implements MouseListener
{
   private enum STATE
   {
      READY, WAITING, RUNNING
   }

   private static final long serialVersionUID = 1L;
   private final static Color bgColor = Color.BLACK;
   private final static Font buttonFont = new Font("Serif", Font.BOLD, 30);
   private final static String readyString = "READY";
   private final static String steadyString = "STEADY";
   private final static String activeString = "GO";

   private STATE state;
   int divisions = 100;
   int timerCount = 0;
   private int size = 100;
   private List<Integer> xPoints;
   private List<Integer> yPoints;
   private ClickBot bot;
   int distance = 0;
   Timer timer;

   private JButton linkButton;
   final URI uri;

   public ActivePanel() throws URISyntaxException
   {
      this.setBackground(bgColor);
      setPreferredSize(new Dimension(300, 100));
      addMouseListener(this);
      makeCirclePoints();
      bot = new ClickBot();
      state = STATE.READY;

      uri = new URI("http://McLauthlinTech.com");

      linkButton = new JButton();
      linkButton.setText("<HTML><u><i>McLauthlinTech.com</i></u></HTML>");
      linkButton.setHorizontalAlignment(SwingConstants.RIGHT);
      linkButton.setBorderPainted(false);
      linkButton.setOpaque(false);
      linkButton.setBackground(Color.BLACK);
      linkButton.setToolTipText(uri.toString());
      linkButton.setForeground(Color.WHITE);
      linkButton.addActionListener(new OpenUrlAction());

      setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      c.anchor = GridBagConstraints.LAST_LINE_END;
      c.weighty = 1;
      c.weightx = 1;
      add(linkButton, c);
   }

   class OpenUrlAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         if (Desktop.isDesktopSupported())
         {
            try
            {
               Desktop.getDesktop().browse(uri);
            }
            catch (IOException e1)
            { /* TODO: error handling */
            }
         }
         else
         { /* TODO: error handling */
         }
      }
   }

   private void makeCirclePoints()
   {
      xPoints = new ArrayList<>();
      yPoints = new ArrayList<>();
      for (int ii = 0; ii < divisions; ii++)
      {
         xPoints.add(Integer.valueOf((int) (Math.sin(ii * 2.0 * Math.PI / divisions) * size / 2)));
         yPoints.add(Integer.valueOf((int) (Math.cos(ii * 2.0 * Math.PI / divisions) * size / 2)));
      }
      xPoints.add(Integer.valueOf(0));
      yPoints.add(Integer.valueOf(size / 2));
   }

   private void drawCircularButton(Graphics2D g, int x, int y)
   {
      Color buttonColor = null;
      if (state == STATE.READY || state == STATE.WAITING)
         buttonColor = Color.green;
      else
         if (state == STATE.RUNNING)
            buttonColor = Color.red;

      for (int ii = 0; ii < divisions; ii++)
      {
         Color stateColor = buttonColor;
         if (ii < timerCount)
            stateColor = Color.cyan;

         if (ii % 2 == 0)
            g.setColor(stateColor.brighter());
         else
            g.setColor(stateColor.darker());
         int xCen = size / 2 + x;
         int yCen = size / 2 + y;
         int[] xArr =
         { xCen, xPoints.get(ii).intValue() + xCen, xPoints.get(ii + 1).intValue() + xCen };
         int[] yArr =
         { yCen, yPoints.get(ii).intValue() + yCen, yPoints.get(ii + 1).intValue() + yCen };
         g.fillPolygon(xArr, yArr, 3);
      }
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      Graphics2D g2 = (Graphics2D) g;

      drawCircularButton(g2, 0, 0);

      // draw the text in the buttons
      FontRenderContext frc = g2.getFontRenderContext();
      g.setFont(buttonFont);

      String curString = "";
      switch (state)
      {
         case READY:
            curString = readyString;
            break;
         case RUNNING:
            curString = activeString;
            break;
         case WAITING:
            curString = steadyString;
            break;
         default:
            break;
      }
      Rectangle2D bounds = buttonFont.getStringBounds(curString, frc);
      g.setColor(Color.RED);
      g.drawString(curString, size+10, size / 2 - (int) bounds.getCenterY());
   }
   
   private void startWaiting()
   {
      state = STATE.WAITING;
      repaint();
      
      timerCount = 0;

      timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask()
      {
         @Override
         public void run()
         {
            timerCount++;
            if (timerCount >= divisions)
            {
               runRobot();
            }
            repaint();
         }
      }, 0, 2000 / divisions); // hard coded for now
   }
   
   void runRobot()
   {
      state = STATE.RUNNING;
      timer.cancel();
      timerCount = 0;
      repaint();

      bot.startRobot();
   }

   @Override
   public void mouseClicked(MouseEvent arg0)
   {
      // changing states
      if (state == STATE.READY)
      {
         startWaiting();
      }
      else
      {
         state = STATE.READY;
         bot.stopRobot();
         if (timer != null)
         {
            timer.cancel();
            timerCount = 0;
         }
      }

      repaint();
   }

   @Override
   public void mouseEntered(MouseEvent arg0)
   {
   }

   @Override
   public void mouseExited(MouseEvent arg0)
   {
   }

   @Override
   public void mousePressed(MouseEvent arg0)
   {
   }

   @Override
   public void mouseReleased(MouseEvent arg0)
   {
   }
}