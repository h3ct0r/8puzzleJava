import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class IDSearch {

	static final int[] GOAL_STATE = {0,1,2,3,4,5,6,7,8}; 
	private Node startNode = null;
	private Node goalNode = null;
	private int limit = 20000;
	private Double elapsedTime = 0.0;
	private Integer cost = 0;
	private Boolean isSuccess = false;
	
	final private boolean DEBUG_ENABLED = false;
	
	private PriorityQueue<Node> openList = new PriorityQueue<Node>();
	private Set<Node> closedList = new HashSet<Node>();
	
	public IDSearch(iHeuristic heuristic, int[] board){
		this.startNode = new Node(board, null, heuristic);
		this.goalNode = new Node(GOAL_STATE, null, heuristic);
		
		Stopwatch sw = new Stopwatch();
		Node endNode = startSearch();
		this.elapsedTime = sw.elapsedTime();
		
		if(endNode == null){
			System.out.println("Elapsed time ["+elapsedTime+"] cost ["+cost+"] Cant find a solution for this puzzle");
		}
		else{
			this.cost = endNode.getG();
			System.out.println("Elapsed time ["+elapsedTime+"] cost ["+cost+"] Solution found!");
			isSuccess = true;
			//printPath(endNode);
		}
	}
	
	public IDSearch(iHeuristic heuristic){
		this.startNode = new Node(new int[] {1,0,2,3,4,5,6,7,8}, null, heuristic);
		this.goalNode = new Node(GOAL_STATE, null, heuristic);
		Node endNode = startSearch();
		printPath(endNode);
	}
	
	public double getTime(){
		return this.elapsedTime;
	}
	
	public boolean getSuccess(){
		return this.isSuccess;
	}
	
	public Node startSearch(){
		if(startNode.calculateInversions() % 2 == 1){
			System.out.println("Unsolvable !");
			return null;
		}
		
		Node currentNode = null;
		List<Node> nList = null;
		int counter = 0;

		for (int i = 0; i < limit; i++) {
			currentNode = startNode;
			nList = new LinkedList<Node>();
			counter = 0;
			
			nList.add(currentNode);
			
			while(!nList.isEmpty()){
				currentNode = nList.remove(0);
				//printNode(currentNode);
				if (Arrays.equals(currentNode.getBoard(), goalNode.getBoard())){
					return currentNode;
				}
				if(counter < i){
					for (Node child : currentNode.calculateSuccessors()) {
						nList.add(child);
					}
				}
				counter++;
			}
		}

		return null;
	}
	
	private Node isNodeInOpenList(Node node){
		Iterator it = openList.iterator();
		
		while(it.hasNext()){
			Node itNode = (Node) it.next();
			if(Arrays.equals(itNode.getBoard(), node.getBoard())) return itNode;
		}
		
		return null;
	}
	
	private Boolean isNodeInClosedList(Node node){
		return closedList.contains(node);
		/*for(int i = 0; i < closedList.size(); i++){
			if(Arrays.equals(closedList.get(i).getBoard(), node.getBoard())) return i;
		}
		
		return null;*/
	}
	
	public void printPath(Node node){
		if(node == null){
			System.out.println("Cant find a solution for this puzzle");
			return;
		}
		
		System.out.println("");
		System.out.println("F="+ node.getF()+ " G="+node.getG()+" H="+node.getH()+" ");
		
		List<Node> pathList = new LinkedList<Node>();
		pathList.add(node);
		
		while(node.getParent() != null){
			pathList.add(node.getParent());
			node = node.getParent();
		}
		
		Collections.reverse(pathList);
		
		for(Node n : pathList){
			printNode(n);
		}
	}
	
	public void printNode(Node n) {
		System.out.println("");
		System.out.println(n.getBoard()[0] + " " + n.getBoard()[1] + " "
				+ n.getBoard()[2]);
		System.out.println(n.getBoard()[3] + " " + n.getBoard()[4] + " "
				+ n.getBoard()[5]);
		System.out.println(n.getBoard()[6] + " " + n.getBoard()[7] + " "
				+ n.getBoard()[8]);
	}
	
	public void printNode(Node n, String s) {
		System.out.println("");
		System.out.println(s);
		printNode(n);
	}

}
