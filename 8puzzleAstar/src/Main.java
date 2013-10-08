import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Main {
	
	static final int WORKING_TIMES = 10; 
	
	public static void main(String[] args) {
		
		iHeuristic heuristic1 = new HeuristicOne();
		iHeuristic heuristic2 = new HeuristicTwo();
		int[] board = new int[9];
		
		double timeSumH1 = 0.0;
		double minTimeH1 = 0.0;
		double maxTimeH1 = 0.0;
		int solvedPuzzlesH1 = 0;
		
		double timeSumH2 = 0.0;
		double minTimeH2 = 0.0;
		double maxTimeH2 = 0.0;
		int solvedPuzzlesH2 = 0;
		
		double timeSumIDS = 0.0;
		double minTimeIDS = 0.0;
		double maxTimeIDS = 0.0;
		int solvedPuzzlesIDS = 0;
		
		
		for(int i = 0; i < WORKING_TIMES; i++){
			List<Integer> bList = new LinkedList<Integer>();
			bList.add(0);
			bList.add(1);
			bList.add(2);
			bList.add(3);
			bList.add(4);
			bList.add(5);
			bList.add(6);
			bList.add(7);
			bList.add(8);
			Collections.shuffle(bList);
			
			for(int h = 0;h < 9;h++){
				board[h] = bList.get(h);
			}
			
			System.out.println("-"+i+"----------------------");
			/*System.out.println("Heuristic 01");
			AstarSearch as = new AstarSearch(heuristic1, board);
			double time = as.getTime();
			if(time < minTimeH1) minTimeH1 = time;
			if(time > maxTimeH1) maxTimeH1 = time;
			timeSumH1 += time;
			if(as.getSuccess()) solvedPuzzlesH1++;
			
			System.out.println("Heuristic 02");
			AstarSearch as2 = new AstarSearch(heuristic2, board);
			time = as2.getTime();
			if(time < minTimeH2) minTimeH2 = time;
			if(time > maxTimeH2) maxTimeH2 = time;
			timeSumH2 += time;
			if(as2.getSuccess()) solvedPuzzlesH2++;*/
			
			IDSearch ids = new IDSearch(heuristic1, board);
			System.out.println("--------------------------- \n");
		}
		
		System.out.println("\nStats:");
		//System.out.println("H1 minTime ["+minTimeH1+"] maxTime ["+maxTimeH1+"] averageTime ["+(timeSumH1/WORKING_TIMES)+"] wins ["+solvedPuzzlesH1+"] percentWin ["+solvedPuzzlesH1+"%] ");
		//System.out.println("H1 minTime ["+minTimeH2+"] maxTime ["+maxTimeH2+"] averageTime ["+(timeSumH2/WORKING_TIMES)+"] wins ["+solvedPuzzlesH2+"] percentWin ["+solvedPuzzlesH2+"%] ");
		System.out.println("IDS minTime ["+minTimeIDS+"] maxTime ["+maxTimeIDS+"] averageTime ["+(timeSumIDS/WORKING_TIMES)+"] wins ["+solvedPuzzlesIDS+"] percentWin ["+solvedPuzzlesIDS+"%] ");
	}

}
