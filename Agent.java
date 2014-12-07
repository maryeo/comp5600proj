   import java.util.Hashtable;
   import java.util.ArrayList;
	import java.util.Random;

   public class Agent
   {
      int playerNum;
      boolean andOr;
		Hashtable<GameBoard, Integer> prevMoves = new Hashtable<GameBoard, Integer>();
      
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
            return andOrSearch(state, ply);
         }
         else
         {
				int move = alphaBetaSearch(state, ply);
				prevMoves.put(state, move);
            return move;
         }
      }
   
      public int getPlayerNum()
      {
         return playerNum;
      }
      
		//Adaptation of Figure 11 in chapter four.
		//Recalculating plan every move. More costly, but should have better performance.
      private int andOrSearch(GameBoard state, int ply)
      {
			//Instructions say don't use the move ordering for this algorithm!
         int action = orSearch(state, new Hashtable<GameBoard, Integer>(), ply, 0).get(0);
         return action;
      }
		
		//Don't need to keep track of plan since 
		private ArrayList<Integer> orSearch(GameBoard state, Hashtable<GameBoard, Integer> path, int ply, int count)
		{
			ArrayList<Integer> plan = new ArrayList<Integer>();
			if(state.isGameOver() == playerNum)
			{
				plan.add(Integer.MAX_VALUE);
				return plan;
			}
			if(path.containsKey(state))
			{
				return null;
			}
			if(count >= ply)
			{
				plan.add(H1(state));
				return plan;
			}
			for(int action : actions(state))
			{
				path.put(state, action);
				plan = andSearch(result(state, action), path, ply, count + 1);
				if(plan != null)
				{
					return plan;
				}
			}
			return null;
		} 
		
		private ArrayList<Integer> andSearch(GameBoard state, Hashtable<GameBoard, Integer> path, int ply, int count)
		{
			if(state.isGameOver() == playerNum)
			{
			}
			return null;
		}
   
      private int alphaBetaSearch(GameBoard state, int ply)
      {
		   int a = 0;
			if(prevMoves.containsKey(state))
			{
				ArrayList<Integer> actions = actions(state);
				Random rand = new Random();
				a = rand.nextInt(actions.size());
			}
			else
			{
				int v = Integer.MIN_VALUE;
         	int alpha = Integer.MIN_VALUE;
         	int beta = Integer.MAX_VALUE;
         	Hashtable<GameBoard, Integer> hash = new Hashtable<GameBoard, Integer>();
      		
         	int count = 0;
         	for (int action : orderMoves(state, true))
         	{
            	GameBoard newState = result(state, action);
            	int newV = min(newState, alpha, beta, ply, count, hash);
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
				GameBoard newState = result(state, actions.get(i)); ///HERE.
				
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
            }
         }
         return moves;
      }
   
      private int H1(GameBoard state)
      {
         int result = 0;
         int index = (playerNum) % 2;
         for (int i = 0; i < state.size(); i++)
         {
            result = result + state.get(index, i);
         }
         return result;
      }
   }