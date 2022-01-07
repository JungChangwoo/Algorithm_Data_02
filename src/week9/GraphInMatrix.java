package week9;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

public class GraphInMatrix {
	
	public String graphName ;
	public ArrayList<String> vertices ;
	public int [][] adjacentMatrix ;
	public int maxNumber = 0;
	public boolean [] visited ; // DFS / BFS를 위한 
	
	public GraphInMatrix(int maxN) {
		maxNumber = maxN ;
		visited = new boolean [maxNumber];
	}
	
	public void createGraph(String name) {
		graphName = name;
		vertices = new ArrayList<String>();
		adjacentMatrix = new int [maxNumber][maxNumber];	
	}
	
	public void showGraph() {
		showGraphInMatrix();
	}

	private void showGraphInMatrix() {
		System.out.println("\n< "+graphName+" in AdjacentMatrix >");
		for (int i=0; i<vertices.size();i++){
			System.out.print(vertices.get(i)+"  ");
			for (int j=0; j<vertices.size();j++)
				System.out.printf("%3d", adjacentMatrix[i][j] );
			System.out.println();
		}		
	}
	
	public void insertVertex(String s) {
		if (!vertices.contains(s)) {
			vertices.add(s);
		}
	}	

	public void insertEdge(String from, String to) {
		insertVertex(from);
		insertVertex(to);
		
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		
		adjacentMatrix[f][t] = 1 ;
		adjacentMatrix[t][f] = 1 ;
	}	
	
	public void deleteVertex(String s) {
		int index = vertices.indexOf(s);
		if (index>=0) {
			int n = vertices.size();
			for (int i=index+1; i<n; i++) 
				for (int j=0; j<n; j++) 
					adjacentMatrix[i-1][j] = adjacentMatrix[i][j];
			for (int i=index+1; i<n; i++) 
				for (int j=0; j<n; j++) 
					adjacentMatrix[j][i-1] = adjacentMatrix[j][i];
			// reset n-1 th row & column 
			for (int i=0;i<n; i++) {
				adjacentMatrix[i][n-1] = 0;
				adjacentMatrix[n-1][i] = 0;
			}
			
			vertices.remove(s);
		}
	}
	
	public void deleteEdge(String from, String to) {
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		if (f>=0 && t>=0) {
			adjacentMatrix[f][t] = 0 ;
			adjacentMatrix[t][f] = 0 ;
		}
	}
	
	public boolean isEmpty() {
		if (vertices.size()==0)
			return true;
		else 
			return false;
	}

	public  HashSet<String> adjacent(String s){
		HashSet<String> result= new HashSet<String>();

		int index = vertices.indexOf(s);
		if (index>=0) {
			for (int i=0; i<vertices.size();i++) {
				if (adjacentMatrix[index][i]!=0) //해당 정점이 있는 행에서 1인 부분을 다 Add
					result.add(vertices.get(i));
			}
		}
		return result;
	}
	
	public void initVisited() { //전체 초기화 
		for (int i=0; i<vertices.size();i++) 
			visited[i] = false;
	}
	
	public void DFS(String s) {
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecusrsion(s);
	}
	private void DFSRecusrsion(String s) {
		int index = vertices.indexOf(s);
		visited[index]=true;
		System.out.println(s+" is visited ");
		for (int i=0; i<vertices.size();i++) { //인접한 정점들 중 방문 x면, Recursion 
			if (adjacentMatrix[index][i]==1 && !visited[i]) {
				DFSRecusrsion(vertices.get(i));
			}
		}
	}
	
	public void BFS(String s) {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSIteration(s);
	}

	public void BFSIteration(String s) {
		Deque<String> que = new ArrayDeque<String>();
		visited[vertices.indexOf(s)]=true;
		System.out.println(s+" is visited ");
		que.add(s);
		
		while (!que.isEmpty()) { //모든 정점을 돌 때까지 
			String v = que.poll();
			int index = vertices.indexOf(v);
			for (int i=0; i<vertices.size();i++) { //꺼낸 정점에 인접한 것들은 모두 Que에 add
				if (adjacentMatrix[index][i]==1 && !visited[i]) {
					visited[i]=true;
					System.out.println(vertices.get(i)+" is visited ");
					que.add(vertices.get(i)); // 인접한 정점의 Adjacent로 다시 돌음 
				}
			}
		}
	}

}
