package week12;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

public class DijkstraSP2 extends GraphInMatrixWD {
	int [] d  ;   // distance; 
	int r = -1;   // start node
	HashSet<String> S, V ;
	
	String[] prev;

	public DijkstraSP2(int max) {
		super(max);
	}
	public void init(String start) {
		d = new int [maxNumber];
		S = new HashSet<String>();
		V = new HashSet<String>();
		
		prev = new String[maxNumber];

		for (String s : vertices )
			V.add(s);
		r = vertices.indexOf(start);
		Arrays.fill(d, 9999);
		d[r]=0;
	}
	
	public void ShortestPath() {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSSP(vertices.get(r));
	}
	
	public void BFSSP(String s) {
		Deque<String> que = new ArrayDeque<String>();
		visited[vertices.indexOf(s)]=true;
		System.out.println(s+" is visited ");
		que.add(s);
		
		while (!que.isEmpty()) {
			String v = que.poll(); //출발 
			int index = vertices.indexOf(v);
			for (int i=0; i<vertices.size();i++) {
				String u = vertices.get(i); //도착
				if (adjacentMatrix[index][i]!=0) { //adjacentMatrix == edge의 값(Weight) 
					if(d[i] > d[index]+adjacentMatrix[index][i]) {
						d[i] = d[index]+adjacentMatrix[index][i];
						prev[i] = v;
					}
					System.out.println(u+" 's distance is updated  ");
					que.add(u);
				}
			}
		}
	}
	
	public void showShortestPath() { 
		for(int i=0; i<maxNumber; i++) {
			System.out.println(prev[i]+" => "+vertices.get(i)+"("+d[i]+")");
		}
	}
	
	private int getWeight(String u, String v) {
		return adjacentMatrix[vertices.indexOf(u)][vertices.indexOf(v)];
	}
	private HashSet<String> diff(HashSet<String> s1, HashSet<String> s2) {
		HashSet<String> result = s1;
		for (String s : s2)
			result.remove(s);
		return result;
	}
	
	private String extractMin(HashSet<String> diff) {
		String minVertex = null;
		int min = 9999;;
		for (String s : diff) {
			if (d[vertices.indexOf(s)] < min) {
				minVertex = s;
				min = d[vertices.indexOf(s)];
			}
		}
		return minVertex;
	}
	
	public static void main(String[] args) {
		String [] vertices = { "서울", "인천", "대전", "대구", "광주", "부산", "울산"};
		int [][] graphEdges = { {0, 1, 11 }, {0, 2, 8}, {0, 3, 9}, {1, 3, 13}, 
				      {1, 6, 8}, {2, 4, 10}, {3, 4, 5}, {3, 5, 12}, {5, 6, 7} };

		DijkstraSP2 myG = new DijkstraSP2(vertices.length);

		myG.createGraph("Dijktra-Test Graph");
		for (int i = 0; i<graphEdges.length; i++)
			myG.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		myG.showGraph();
		
		System.out.println("\nDijkstraSP Algorithm starts from "+"서울");

		myG.init("서울");
		myG.ShortestPath();
		myG.showShortestPath();

	}

}
