
public class HeuristicTwo implements iHeuristic {

	int[] board = null;
	int fitnessValue = 0;
	
	public HeuristicTwo(int[] board){
		this.board = board;
		calculateFitness();
	}
	
	public HeuristicTwo(){
	}
	
	@Override
	public int calculateFitness() {
		return this.fitnessValue = calculateFitness(this.board);
	}
	
	@Override
	public int calculateFitness(int[] board) {
		int fitness = 0;
		int row = 0;
		int col = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] != 0) {
				row = board[i] / 3;
				col = board[i] % 3;
				row = Math.abs(row - (i / 3));
				col = Math.abs(col - (i % 3));
				fitness += row;
				fitness += col;
			}

		}
		return fitness;
	}
	
	public int getFitness(){
		return this.fitnessValue;
	}

}
