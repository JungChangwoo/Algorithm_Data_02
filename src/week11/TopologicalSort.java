package week11;

import java.util.Arrays;
import java.util.LinkedList;

import week9.GraphInMatrix; 
/**
 * 위상 정렬 : 유향 그래프의 꼭짓점들(Vertex)을 엣지의 방향을 거스르지 않도록 나열하는 것 
 * = 모든 정점을 일렬로 나열하되, 정점 x에서 정점 y로 가는 간선이 있으면 x는 반드시 y보다 앞에 위치
 */
public class TopologicalSort extends GraphInMatrix{

	public TopologicalSort(int maxN) {
		super(maxN);
	}
	
	public void insertEdge(String from, String to) {
		insertVertex(from);
		insertVertex(to);
		
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		
		adjacentMatrix[f][t] = 1;
//		adjacentMatrix[t][f] = 1; deleted!
	}
	
	public void deleteEdge(String from, String to) {
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		if (f>=0 && t>=0) {
			adjacentMatrix[f][t] = 0 ;
//			adjacentMatrix[t][f] = 0 ; deleted!
		}
	} 
	
	public void TPSort1() {
		String[] A = new String[vertices.size()];
		int nOfVertices = vertices.size(); // 나중에 delete되기 때문에, 따로 변수로 만들어 줌
		
		for(int i=0; i<nOfVertices; i++) {
			A[i] = getNextNode(); //진입간선이 없는 정점을 선택함 
			deleteVertex(A[i]);   //정점 u와 u의 진출간선을 모두 제거
			showGraph(); 
		}						  //=> 이 시점에 배열 A에는 정점들이 위상정렬되어 있다. 
		System.out.print(">>> Start ");
		for(int i=0; i<nOfVertices; i++) 
			System.out.print("=> "+A[i]);
		System.out.println();
	}
	
	private String getNextNode() {
		for(int i=0; i<vertices.size(); i++) {
			int tempSum = 0;
			for(int j=0; j<vertices.size(); j++)  
				tempSum += adjacentMatrix[j][i]; //incoming Edge loop
			if(tempSum == 0) // incoming Edge가 없으면 return 
				return vertices.get(i);
		}
		return null;
	}
	// 깊이 우선 탐색 Recursion으로
	public void TPSort2() {
		LinkedList<String> R = new LinkedList<>();
		boolean[] visited = new boolean[vertices.size()]; //탐색을 위해서 
		Arrays.fill(visited, false); //초기화
		
		for(String s : vertices) { 					// 모든 정점에 대해서 Loop
			if(visited[vertices.indexOf(s)]==false)  // 방문하지 않은 정점이라면,
				dfsTS(visited, s, R);
		}
		
		System.out.println(R);
	}
	
	private LinkedList<String> dfsTS(boolean[] visited, String s, LinkedList<String> R){
		visited[vertices.indexOf(s)] = true;			// 방문
		for(String x : adjacent(s))						// 인접한 정점 Loop
			if(visited[vertices.indexOf(x)]==false)		// 아직 방문하지 않았다면, Recursion
				dfsTS(visited, x, R);
		System.out.println(s + " is added a the first");// 더 이상 갈 곳이 없으면 add
		R.addFirst(s);
		return R;										
	}
	
	public static void main(String[] args) {
		int maxNoVertex = 10;
		String[] vertices = {"물붓기", "점화하기", "봉지뜯기", "라면넣기", "스프넣기", "계란넣기"};
		int [][] graphEdges = {{0,1}, {1,3}, {1,4}, {1,5},
							   {2,3}, {2,4}, {3,5}, {4,5} };
		
		TopologicalSort myGM = new TopologicalSort(maxNoVertex);
		
		myGM.createGraph("Topological Sort1");
		
		for(int i=0; i<graphEdges.length; i++) 
			myGM.insertEdge(vertices[graphEdges[i][0]], vertices[graphEdges[i][1]]);
		myGM.showGraph();
		
		System.out.println("Topological Sort1 : start");
		myGM.TPSort1();
		
		TopologicalSort myGM2 = new TopologicalSort(maxNoVertex);
		
		myGM2.createGraph("Topological Sort2");
		
		for(int i=0; i<graphEdges.length; i++) 
			myGM2.insertEdge(vertices[graphEdges[i][0]], vertices[graphEdges[i][1]]);
		myGM2.showGraph();
		
		System.out.println("Topological Sort2 : start");
		myGM2.TPSort2();
	}	

}
