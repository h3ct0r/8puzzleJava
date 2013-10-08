import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class Node implements Comparable<Object>{
	private Node parent;
	private int[] board;
	private iHeuristic heuristic;
	
	final private int MOVEMENT_COST = 1;
	
	// f = g + h
	// g = real cost to get here
	// h = estimated cost to get to goal
	private int f = 0;
	private int g = 0;
	private int h = 0;
	
	public Node(int[] board, Node parent, iHeuristic heuristic){
		this.board = board;
		this.parent = parent;
		this.heuristic = heuristic;
		
		if(this.parent != null){
			this.g = this.parent.getRealCostG() + MOVEMENT_COST;
		}
		else{
			this.g = 0;
		}
		
		calculateFitness();
		this.f = this.g + this.h;
	}
	
	public int calculateInversions() {
		int inversions = 0;
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < i; j++) {
				if (this.board[i] != 0 && this.board[j] != 0) {
					if (this.board[i] < this.board[j])
						inversions++;
				}
			}
		}
		return inversions;
	}
	
	public int calculateFitness(){
		return this.h = this.heuristic.calculateFitness(this.board);
	}
	
	public int getRealCostG(){
		return g;
	}
	
	public Node setParent(Node parentNode){
		return this.parent = parentNode;
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public int[] getBoard(){
		return this.board;
	}
	
	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.f = this.g + this.h;
		this.g = g;
	}

	public int getH() {
		return calculateFitness();
	}

	public void setH(int h) {
		this.h = h;
		calculateFitness();
		this.f = this.g + this.h;
	}
	
	public List<Node> calculateSuccessors(){
		List<Node> succesors = new LinkedList<Node>();
		
		int blankPos = 0;
		while (this.board[blankPos] != 0) {
			blankPos++;
		}
		
		switch(blankPos){
			case 0:
				succesors.add(new Node(getDownState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getRightState(this.board.clone(), blankPos), this, heuristic));
			break;
			case 1:
				succesors.add(new Node(getDownState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getRightState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getLeftState(this.board.clone(), blankPos), this, heuristic));
			break;
			case 2:
				succesors.add(new Node(getDownState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getLeftState(this.board.clone(), blankPos), this, heuristic));
			break;
			case 3:
				succesors.add(new Node(getUpState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getRightState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getDownState(this.board.clone(), blankPos), this, heuristic));
			break;
			case 4:
				succesors.add(new Node(getUpState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getDownState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getLeftState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getRightState(this.board.clone(), blankPos), this, heuristic));
			break;
			case 5:
				succesors.add(new Node(getUpState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getDownState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getLeftState(this.board.clone(), blankPos), this, heuristic));
			break;
			case 6:
				succesors.add(new Node(getUpState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getRightState(this.board.clone(), blankPos), this, heuristic));
			break;
			case 7:
				succesors.add(new Node(getUpState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getLeftState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getRightState(this.board.clone(), blankPos), this, heuristic));
			break;
			case 8:
				succesors.add(new Node(getUpState(this.board.clone(), blankPos), this, heuristic));
				succesors.add(new Node(getLeftState(this.board.clone(), blankPos), this, heuristic));
			break;
		}
		
		return succesors;
	}
	
	private int[] getUpState(int[] board, int blankPos){
		int temp = board[blankPos - 3];
		board[blankPos - 3] = 0;
		board[blankPos] = temp;
		return board;
	}
	
	private int[] getDownState(int[] board, int blankPos){
		int temp = board[blankPos + 3];
		board[blankPos + 3] = 0;
		board[blankPos] = temp;
		return board;
	}
	
	private int[] getLeftState(int[] board, int blankPos){
		int temp = board[blankPos - 1];
		board[blankPos - 1] = 0;
		board[blankPos] = temp;
		return board;
	}
	
	private int[] getRightState(int[] board, int blankPos){
		int temp = board[blankPos + 1];
		board[blankPos + 1] = 0;
		board[blankPos] = temp;
		return board;
	}
	
	@Override
	public int compareTo(Object input) {
		if (this.getF() < ((Node) input).getF()) return -1;
		else if (this.getF() > ((Node) input).getF()) return 1;
		return 0;
	}
	
	@Override
	  public boolean equals(Object o){
	    if(o instanceof Node){
	      Node n = (Node) o;
	      if(n.getF() != this.getF()) return false;
	      return Arrays.equals(((Node) o).getBoard(), this.board);
	    }
	    return false;
	  }
}
