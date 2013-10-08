
public class HeuristicOne implements iHeuristic {

	int[] board = null;
	int fitnessValue = 0;
	
	public HeuristicOne(int[] board){
		this.board = board;
		calculateFitness();
	}
	
	public HeuristicOne(){
	}
	
	@Override
	public int calculateFitness() {
		return fitnessValue = calculateFitness(this.board);
	}
	
	public int calculateFitness(int[] board) {
		int fitness = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] != i && board[i] != 0) fitness++;
		}
		return fitness;
	}
	
	public int getFitness(){
		return this.fitnessValue;
	}

}
