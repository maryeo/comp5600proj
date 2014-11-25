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
      
      }
   
      public boolean isGameOver()
      {
         int top = 0;
         int bottom = 0;
      
         for (int i = 0; i < length; i++)
         {
            top = top + board[0][i];
            bottom = bottom + board[1][i];
         }
      
         if (top == 0 || bottom == 0)
         {
            return true;
         }
         return false;
      }
   
      public String toString()
      {
         String result = "";
         for (int i = 0; i < length; i++)
         {
            result = result + " " + board[0][i];
         }
         result = result + "/r/n";
         for (int i = 0; i < length; i++)
         {
            result = result + " " + board[1][i];
         }
         return result;
      }
   }