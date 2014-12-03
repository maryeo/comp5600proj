public class GameBoard
{
   private int[][] board;
   int length;

   public GameBoard(int columns, int stones)
   {
      length = columns;
      board = new int[2][columns];
      for (int i = 0; i < columns; i++)
      {
         board[0][i] = stones;
         board[1][i] = stones;
      }
   }

   public int size()
   {
      return length;
   }

   public int get(int row, int column)
   {
      return board[row][column];
   }

   public void updateGame(int row, int column)
   {
      int stones = board[row][column];
      board[row][column] = 0;
      int rowIndex = row;
      int colIndex = column;
      /*for (int i = 0; i < stones; i++)
      {
      	//Assumed going clockwise.
         if (colIndex == 1)
         {
            if (rowIndex - 1 < 0)
            {
               colIndex = 0;
            }
            else
            {
               rowIndex--;
            }
         }
         else
         {
            if (rowIndex + 1 > board[0].length - 1)
            {
               colIndex = 1;
            }
            else
            {
               rowIndex++;
            }
         }*/
         //some errors in update (also that was counterclockwise not clockwise)
         //
      for(; stones > 0; stones--)
      {
         if(rowIndex == 0)
         {
               
            if(colIndex != length-1)                  
            {
               colIndex ++;
               board[rowIndex][colIndex]++;
            }
            else
            {
               rowIndex = 1;
               board[rowIndex][colIndex]++;
            }
         }
         else if(rowIndex == 1)
         {
            if(colIndex != 0)                  
            {
               colIndex --;
               board[rowIndex][colIndex]++;
            }
            else
            {
               rowIndex = 0;
               board[rowIndex][colIndex]++;
            }
         } 
      }
   }

   public int isGameOver()
   {
      int top = 0;
      int bottom = 0;
      for (int i = 0; i < length; i++)
      {
         top = top + board[0][i];
         bottom = bottom + board[1][i];
      }
   
      if (top == 0)
      {
         return 2; //Player one wins
      }
      if (bottom == 0)
      {
         return 1; //Player two wins
      }
      return -1; //Game not over
   }

   public String toString()
   {
      String result = "";
      for (int i = 0; i < length; i++)
      {
         result = result + " " + board[0][i];
      }
      result = result + "\r\n";
      for (int i = 0; i < length; i++)
      {
         result = result + " " + board[1][i];
      }
      return result;
   }
}