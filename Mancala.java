import java.io.*;
import java.util.Scanner;

public class Mancala
{
   public static void main(String args[]) throws IOException
   {
      int columns = 0;
      int stones = 0;
      int enteredNum = 0;
      Agent playerOne = new Agent();
      boolean human1 = false;
      boolean human2 = false;
      int plyOne = 0;
      Agent playerTwo = new Agent();
      int plyTwo = 0;
      GameBoard game;
      boolean step = false;
      boolean playerOneTurn = true;
      Scanner scan = new Scanner(System.in);
      
      //Get user input for game parameters 
      
      //Number of squares ont he board for each player     	
      while (columns <= 0 || columns > 10)
      {
         System.out.print("Squares per player: ");
         columns = scan.nextInt();
         System.out.println();
      }
      
      //number of pebbles starting in each square
      while (stones <= 0)
      {
         System.out.print("Initial number of stones per square: ");
         stones = scan.nextInt();
         System.out.println();
      }
      //create a game with the above user parameters
      game = new GameBoard(columns, stones);
   	
      //select player type for Player 1
      while (enteredNum <= 0 || enteredNum > 3)
      {
         System.out.println("Player one options: ");
         System.out.println("1 - Human");
         System.out.println("2 - Computer, AndOrSearch");
         System.out.println("3 - Computer, AlphaBetaSearch");
         System.out.print("Enter choice: ");
         enteredNum = scan.nextInt();
      }
      if(enteredNum == 1)//human player
      {
         human1 = true;
      }  
      if (enteredNum == 2)//AndOrSearch
      {
         playerOne = new Agent(1, true);
			while (plyOne <= 0)
         {
            System.out.print("Ply: ");
            plyOne = scan.nextInt();
            System.out.println();
         }
      }
      else if (enteredNum == 3)//AlphaBeta
      {
         playerOne = new Agent(1, false);
         while (plyOne <= 0)
         {
            System.out.print("Ply: ");
            plyOne = scan.nextInt();
            System.out.println();
         }
      }
      enteredNum = 0;
   	//Select player type  for Player 2
      while (enteredNum <= 0 || enteredNum > 3)
      {
         System.out.println("Player two options: ");
         System.out.println("1 - Human");
         System.out.println("2 - Computer, AndOrSearch");
         System.out.println("3 - Computer, AlphaBetaSearch");
         System.out.print("Enter choice: ");
         enteredNum = scan.nextInt();
      }
      if(enteredNum == 1)
      {
         human2 = true;
      }
      if (enteredNum == 2)
      {
         playerOne = new Agent(2, true);
			while (plyTwo <= 0)
         {
            System.out.print("Ply: ");
            plyTwo = scan.nextInt();
            System.out.println();
         }
      }
      else if (enteredNum == 3)
      {
         playerTwo = new Agent(2, false);
         while (plyTwo <= 0)
         {
            System.out.print("Ply: ");
            plyTwo = scan.nextInt();
            System.out.println();
         }
      }
      System.out.println("Game mode:?\n1-Run\n2-step");
      enteredNum = scan.nextInt();
      if(enteredNum == 2)
      {
         step = true;
       }
      enteredNum = 0;
   	
      //Begin Gameplay
      System.out.println(game.toString());
      while (game.isGameOver() == -1)
      {
         
      	
         //Player 1
         if (playerOneTurn)
         {
            //Get Move from human player
            if (human1)
            {
               while (enteredNum <= 0)
               {
                  System.out.print("Player 1 Move: ");
                  enteredNum = scan.nextInt();
                  System.out.println();
               }
               game.updateGame(0,enteredNum-1);
               System.out.println(game.toString());
            }
            //Get move from computer agent   
            else
            {
               GameBoard temp = new GameBoard(game);
               enteredNum = playerOne.getMove(temp, plyOne);
               System.out.println("Player 1 Move: " + enteredNum);
               game.updateGame(0,enteredNum);
               System.out.println(game.toString());
               if(step)
               {
                  System.out.println("Press enter to continue");
                  System.in.read();
                }
            }
          
            playerOneTurn = false;
         }
         //Player 2 moves
         else
         {
            if (human2)
            {
               while (enteredNum <= 0)
               {
                  System.out.print("Player 2 Move: ");
                  enteredNum = scan.nextInt();
                  System.out.println();
               }
               game.updateGame(1,enteredNum-1);
               System.out.println(game.toString());
            }
            else
            {
               GameBoard temp = new GameBoard(game);
               enteredNum = playerTwo.getMove(temp, plyTwo);
               System.out.println("Player 2 Move: " + enteredNum);
               game.updateGame(1,enteredNum);
               System.out.println(game.toString());
               if(step)
               {
                  System.out.println("Press enter to continue");
                  System.in.read();
                }
            }
            playerOneTurn = true;
         }
         enteredNum = 0;
      }
      //Print win message
      System.out.println("Player " + game.isGameOver() + " won!");
   }
}