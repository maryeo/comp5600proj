   import java.io.*;
   import java.util.Scanner;

   public class Mancala
   {
      public static void main(String args[])
      {
         int columns = 0;
         int stones = 0;
         int enteredNum = 0;
         Agent playerOne = null;
         int plyOne = 0;
         Agent playerTwo = null;
         int plyTwo = 0;
         GameBoard game;
         boolean playerOneTurn = true;
         Scanner scan = new Scanner(System.in);
      	
         while (columns <= 0 || columns > 10)
         {
            System.out.print("Squares per player: ");
            columns = scan.nextInt();
            System.out.println();
         }
         while (stones <= 0)
         {
            System.out.print("Initial number of stones per square: ");
            stones = scan.nextInt();
            System.out.println();
         }
         game = new GameBoard(columns, stones);
      	
         while (enteredNum <= 0 || enteredNum > 3)
         {
            System.out.println("Player one options: ");
            System.out.println("1 - Human");
            System.out.println("2 - Computer, AndOrSearch");
            System.out.println("3 - Computer, AlphaBetaSearch");
            System.out.print("Enter choice: ");
            enteredNum = scan.nextInt();
         }
         if (enteredNum == 2)
         {
            playerOne = new Agent(1, true);
         }
         else if (enteredNum == 3)
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
      	
         while (enteredNum <= 0 || enteredNum > 3)
         {
            System.out.println("Player two options: ");
            System.out.println("1 - Human");
            System.out.println("2 - Computer, AndOrSearch");
            System.out.println("3 - Computer, AlphaBetaSearch");
            System.out.print("Enter choice: ");
            enteredNum = scan.nextInt();
         }
         if (enteredNum == 2)
         {
            playerOne = new Agent(2, true);
         }
         else if (enteredNum == 3)
         {
            playerOne = new Agent(2, false);
            while (plyTwo <= 0)
            {
               System.out.print("Ply: ");
               plyTwo = scan.nextInt();
               System.out.println();
            }
         }
         enteredNum = 0;
      	
         while (game.isGameOver() == -1)
         {
            System.out.println(game.toString());
         	
            if (playerOneTurn)
            {
               if (playerOne.equals(null))
               {
                  while (enteredNum <= 0)
                  {
                     System.out.print("Move: ");
                     enteredNum = scan.nextInt();
                     System.out.println();
                  }
               }
               else
               {
                  enteredNum = playerOne.getMove(game, plyOne);
                  System.out.println("Move: " + enteredNum);
               }
               playerOneTurn = false;
            }
            else
            {
               if (playerTwo.equals(null))
               {
                  while (enteredNum <= 0)
                  {
                     System.out.print("Move: ");
                     enteredNum = scan.nextInt();
                     System.out.println();
                  }
               }
               else
               {
                  enteredNum = playerTwo.getMove(game, plyTwo);
                  System.out.println("Move: " + enteredNum);
               }
               playerOneTurn = true;
            }
            enteredNum = 0;
         }
         
         System.out.println("Player " + game.isGameOver() + " won!");
      }
   }