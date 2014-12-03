   import java.util.Hashtable;
   import java.util.ArrayList;

   public class Agent
   {
      int playerNum;
      boolean andOr;
      
      public Agent()
      {
         //default constructor
      }
   
      public Agent(int playerNumber, boolean useAndOr)
      {
         playerNum = playerNumber;
         andOr = useAndOr;
      }
   
      public int getMove(GameBoard state, int ply)
      {
         if (andOr)
         {
            return andOrSearch(state);
         }
         else
         {
            return alphaBetaSearch(state, ply);
         }
      }
   
      public int getPlayerNum()
      {
         return playerNum;
      }
      
      private int andOrSearch(GameBoard state)
      {
         int action = -1;
      	
         return action;
      }
   
      private int alphaBetaSearch(GameBoard state, int ply)
      {
         int v = Integer.MIN_VALUE;
         int a = 0;
         int alpha = Integer.MIN_VALUE;
         int beta = Integer.MAX_VALUE;
         Hashtable<GameBoard, Integer> hash = new Hashtable<GameBoard, Integer>();
      
         int count = 0;
         for (int action : orderMoves(state, true))
         {
            GameBoard newState = result(state, action);
            int newV = min(newState, alpha, beta, ply, count, hash);
				System.out.println(action);
            if (newV > v)
            {
               v = newV;
               a = action;
            }
            if (v >= beta)
            {
               return a;
            }
            else if (v > alpha)
            {
               alpha = v;
            }
         }
      
         return a;
      }
   
      private int min(GameBoard state, int alpha, int beta, int ply, int count, Hashtable<GameBoard, Integer> hash)
      {
         Integer v = hash.get(state);
         if (v == null)
         {
            if ((state.isGameOver()== -1)|| count >= ply)
            {
               return H1(state);
            }
            v = Integer.MAX_VALUE;
            for (int action : orderMoves(state, false))
            {
               GameBoard newState = result(state, action);
               int newV = max(newState, alpha, beta, ply, count + 1, hash);
               if (newV < v)
               {
                  v = newV;
               }
               if (v <= alpha)
               {
                  return v;
               }
               else if (v < beta)
               {
                  beta = v;
               }
            }
            hash.put(state, v);
         }
         return v;
      }
   
      private int max(GameBoard state, int alpha, int beta, int ply, int count, Hashtable<GameBoard, Integer> hash)
      {
         Integer v = hash.get(state);
         if (v == null)
         {
            if ((state.isGameOver()== -1)|| count >= ply)
            {
               return H1(state);
            }
            v = Integer.MIN_VALUE;
            for (int action : orderMoves(state, true))
            {
               GameBoard newState = result(state, action);
               int newV = min(newState, alpha, beta, ply, count + 1, hash);
               if (newV > v)
               {
                  v = newV;
               }
               if (v >= beta)
               {
                  return v;
               }
               else if (v > alpha)
               {
                  alpha = v;
               }
            }
            hash.put(state, v);
         }
         return v;
      }
   
      private GameBoard result(GameBoard state, int action)
      {
         state.updateGame(playerNum - 1, action);
         return state;
      }
   
      private ArrayList<Integer> orderMoves(GameBoard state, boolean agentsMove)
      {
         ArrayList<Integer> moves = new ArrayList<Integer>();
			ArrayList<Integer> actions = actions(state);
			for (int i = 0; i < actions.size(); i++)
			{
				int action1 = actions.get(i);
				GameBoard newState = result(state, actions.get(i));
				
				for (int j = 0; i < moves.size(); i++)
				{
					if (agentsMove && H1(newState) >= H1(result(state, moves.get(j))))
					{
						int temp = action1;
						action1 = moves.get(j);
						moves.set(j, temp);
					}
					if (!agentsMove && H1(newState) <= H1(result(state, moves.get(j))))
					{
						int temp = action1;
						action1 = moves.get(j);
						moves.set(j, temp);
					}
				}
				moves.add(action1);
				System.out.println(action1);
			}
      
         return moves;
      }
      
      private ArrayList<Integer> actions(GameBoard state)
      {
         ArrayList<Integer> moves = new ArrayList<Integer>();
         for (int i = 0; i < state.size(); i++)
         {
            if (state.get(playerNum - 1, i) != 0)
            {
               moves.add(i);
					System.out.print(i);
            }
         }
         return moves;
      }
   
      private int H1(GameBoard state)
      {
         int result = 0;
         int index = (playerNum + 1) % 2;
         for (int i = 0; i < state.size(); i++)
         {
            result = result + state.get(index, i);
         }
         return result;
      }
   }