package problems.P051xP075;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import base.Problem;

/**
 * P054<br>
 * In the card game poker, a hand consists of five cards and are ranked, from lowest to highest, in the following way:
 * High Card: Highest value card.
 * One Pair: Two cards of the same value.
 * Two Pairs: Two different pairs.
 * Three of a Kind: Three cards of the same value.
 * Straight: All cards are consecutive values.
 * Flush: All cards of the same suit.
 * Full House: Three of a kind and a pair.
 * Four of a Kind: Four cards of the same value.
 * Straight Flush: All cards are consecutive values of same suit.
 * Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
 * The cards are valued in the order:
 * 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
 * If two players have the same ranked hands then the rank made up of the highest value wins; for example, a pair of eights 
 * beats a pair of fives (see example 1 below). But if two ranks tie, for example, both players have a pair of queens, then 
 * highest cards in each hand are compared (see example 4 below); if the highest cards tie then the next highest cards are compared, and so on.
 * 
 * Consider the following five hands dealt to two players:
 * 
 * Hand     Player 1    Player 2    Winner
 * 1     5H 5C 6S 7S KD
 * Pair of Fives
 *    2C 3S 8S 8D TD
 *  Pair of Eights
 *    Player 2
 *   2      5D 8C 9S JS AC
 *   Highest card Ace
 *       2C 5C 7D 8S QH
 *    Highest card Queen
 *       Player 1
 *     3    2D 9C AS AH AC
 *     Three Aces
 *       3D 6D 7D TD QD
 *      Flush with Diamonds
 *          Player 2
 *       4     4D 6S 9H QH QC
 *       Pair of Queens
 *       Highest card Nine
 *          3D 6D 7H QD QS
 *        Pair of Queens
 *        Highest card Seven
 *          Player 1
 *         5      2H 2D 4C 4D 4S
 *         Full House
 *         With Three Fours
 *             3C 3D 3S 9S 9D
 *          Full House
 *          with Three Threes
 *             Player 1
 * The file, poker.txt, contains one-thousand random hands dealt to two players. Each line of the file contains ten cards 
 * (separated by a single space): the first five are Player 1's cards and the last five are Player 2's cards. You can assume 
 * that all hands are valid (no invalid characters or repeated cards), each player's hand is in no specific order, and in each 
 * hand there is a clear winner.
 * 
 * How many hands does Player 1 win?
 * Ans: 376
 * 
 * @return
 */
public class P054 extends Problem
{
   public P054()
   {
      problemNumber = 54;
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
   public int attempt()
   {
      int p1 = 0;
      try {
         BufferedReader buffer = new BufferedReader(new FileReader("data/poker.txt"));
         String line = "";
         while ((line = buffer.readLine()) != null)
         {
            // this is one hand
            String[] cardStrings = line.split("\\s");
            Hand hand1 = new Hand(cardStrings[0],cardStrings[1],cardStrings[2],cardStrings[3],cardStrings[4]);
            Hand hand2 = new Hand(cardStrings[5],cardStrings[6],cardStrings[7],cardStrings[8],cardStrings[9]);
            int score1 = score(hand1);
            int score2 = score(hand2);
            // there's a possibility of two two-pairs or two full-house scores conflicting, where the next highest
            // card is /not/ the right tie breaker, but I didn't see any of those
            if (score1 == score2)
            {
               int ii = 4;
               while (ii >= 0 && hand1.cards[ii].number == hand2.cards[ii].number)
               {
                  ii--;
               }
               if (hand1.cards[ii].number > hand2.cards[ii].number)
               {
                  p1++;
               } else {
               }
            } else {
               if (score1 > score2)
               {
                  p1++;
               } else {
               }
            }
         }
         buffer.close();
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      return p1;
   }
   
   private int score(Hand h)
   {
      /* Taking a base "score" plus the highest card value within that score
       * 000 - High Card: Highest value card.
       * 100 - One Pair: Two cards of the same value.
       * 200 - Two Pairs: Two different pairs.
       * 300 - Three of a Kind: Three cards of the same value.
       * 400 - Straight: All cards are consecutive values.
       * 500 - Flush: All cards of the same suit.
       * 600 - Full House: Three of a kind and a pair.
       * 700 - Four of a Kind: Four cards of the same value.
       * 800 - Straight Flush: All cards are consecutive values of same suit.
       * 900 - Royal Flush: Ten, Jack, Queen, King, Ace, in same suit. 
       */
      boolean isFlush = isFlush(h);
      boolean isStraight = isStraight(h);
      if (isFlush)
      {
         int score = 500;
         if (isStraight)
         {
            score = 800;
            if (h.cards[0].number == 10)
            {
               score = 900;
            }
         }
         // can't be a full house or 4 of a kind at this point.
         return score;
      } else if (isStraight) {
         return 400;
      }
      int streak = highestStreak(h);
      if (streak >= 80) // 4 of a kind
      {
         return 700 + streak - 80;
      } else if (streak >= 60) // three of a kind
      {
         if (h.cards[0].number == h.cards[1].number && h.cards[3].number == h.cards[4].number)
         {
            return 600 + streak - 60;
         }
         return 300 + streak - 60;
      } else if (streak >= 40) // two of a kind
      {
         // two of a kind ... another pair might exist
         int count = 0;
         if (h.cards[0].number == h.cards[1].number)
         {
            count++;
         }
         if (h.cards[1].number == h.cards[2].number)
         {
            count++;
         }
         if (h.cards[2].number == h.cards[3].number)
         {
            count++;
         }
         if (h.cards[3].number == h.cards[4].number)
         {
            count++;
         }
         if (count == 2)
            return 200 + streak - 40;
         return 100 + streak - 40;
      }
      return h.cards[4].number;
   }
   
   private int highestStreak(Hand h)
   {
      int max = 0;
      int highest = 0;
      for (int ii = 0; ii < h.cards.length - 1 && 5 - ii >= max; ii++)
      {
         int count = 1;
         int myHigh = 0;
         for (int jj = ii+1; jj < h.cards.length; jj++)
         {
            if (h.cards[jj].number == h.cards[ii].number)
            {
               myHigh = h.cards[jj].number;
               count++;
            }
         }
         if (count > max || (count == max && myHigh > highest))
         {
            max = count;
            highest = myHigh;
         }
      }
      return max*20 + highest;
   }
   
   private boolean isFlush(Hand h)
   {
      return h.cards[0].suit == h.cards[1].suit && 
               h.cards[0].suit == h.cards[2].suit &&
               h.cards[0].suit == h.cards[3].suit &&
               h.cards[0].suit == h.cards[4].suit;
   }
   
   private boolean isStraight(Hand h)
   {
      return ( (h.cards[1].number - h.cards[0].number == 1) || (h.cards[1].number == 2 && h.cards[0].number == 14) ) &&
               (h.cards[2].number - h.cards[1].number == 1) &&
               (h.cards[3].number - h.cards[2].number == 1) &&
               (h.cards[4].number - h.cards[3].number == 1);
   }
   
   private class Hand
   {
      public Card[] cards = new Card[5];
      
      public Hand(String c1, String c2, String c3, String c4, String c5)
      {
         cards[0] = new Card(c1);
         cards[1] = new Card(c2);
         cards[2] = new Card(c3);
         cards[3] = new Card(c4);
         cards[4] = new Card(c5);
         Arrays.sort(cards);
      }
      
      public String toString()
      {
         return cards[0] + ", " + cards[1] + ", " + cards[2] + ", " + cards[3] + ", " + cards[4];
      }
   }
   
   private class Card implements Comparable<Card>
   {
      public int suit;
      public int number;
      
      public Card(String s)
      {
         convert(s);
      }
      
      private void convert(String s)
      {
         // first character is the number / name of card, second is suit
         switch (s.charAt(0))
         {
            case '2' : number = 2; break;
            case '3' : number = 3; break;
            case '4' : number = 4; break;
            case '5' : number = 5; break;
            case '6' : number = 6; break;
            case '7' : number = 7; break;
            case '8' : number = 8; break;
            case '9' : number = 9; break;
            case 'T' : number = 10; break;
            case 'J' : number = 11; break;
            case 'Q' : number = 12; break;
            case 'K' : number = 13; break;
            case 'A' : number = 14; break;
         }
         switch (s.charAt(1))
         {
            case 'D' : suit = 1; break;
            case 'H' : suit = 2; break;
            case 'S' : suit = 3; break;
            case 'C' : suit = 4; break;
         }
      }

      @Override
      public int compareTo(Card c)
      {
         return this.number - c.number;
      }
      
      public String toString()
      {
         return (number < 10 ? " " : "") + number + "-" + suit;
      }
   }
   
}
