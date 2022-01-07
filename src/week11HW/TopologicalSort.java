package week11HW;

import java.util.Arrays;
import java.util.LinkedList;

import week9.GraphInList;

public class TopologicalSort extends GraphInList{

	public TopologicalSort(int maxN) {
		super(maxN);
	}
	
	public void insertEdge(String from, String to) {
		insertVertex(from);
		insertVertex(to);
		
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		
		adjacentList.get(f).add(to);
//		adjacentList.get(t).add(from); deleted! // 유향 => 필요 x
	}
	
//	public void deleteEdge(String from, String to) {
//		int f = vertices.indexOf(from);
//		int t = vertices.indexOf(to);
//		if (f>=0 && t>=0) {
//			adjacentList.get(f).remove(to); ;
////			adjacentList.get(t).remove(from); deleted! // 유향 => 필요 x
//		}
//	}
	
	public void TPSort1() {
		String[] A = new String[vertices.size()];
		int nOfVertices = vertices.size(); // 나중에 delete되기 때문에, 따로 변수로 만들어 줌
		
		for(int i=0; i<nOfVertices; i++) {
			A[i] = getNextNode();
			deleteVertex(A[i]);
			showGraph(); 
		}
		System.out.print(">>> Start ");
		for(int i=0; i<nOfVertices; i++) 
			System.out.print("=> "+A[i]);
		System.out.println();
	}
	
	private String getNextNode() {
		for(int i=0; i<vertices.size(); i++) {
			int tempSum = 0;
			for(int j=0; j<vertices.size(); j++)  
				for(int k=0; k<adjacentList.get(j).size(); k++) {
					if(adjacentList.get(j).get(k).equals(vertices.get(i))){
						tempSum ++;//incoming Edge loop
					}
				}
			if(tempSum == 0) // incoming Edge가 없으면 return 
				return vertices.get(i);
		}
		return null;
	}
	
	public void TPSort2() {
		LinkedList<String> R = new LinkedList<>();
		boolean[] visited = new boolean[vertices.size()];
		Arrays.fill(visited, false);
		
		for(String s : vertices) {
			if(visited[vertices.indexOf(s)]==false)
				dfsTS(visited, s, R);
		}
		
		System.out.println(R);
	}
	
	private LinkedList<String> dfsTS(boolean[] visited, String s, LinkedList<String> R){
		visited[vertices.indexOf(s)] = true;
		for(String x : adjacent(s))
			if(visited[vertices.indexOf(x)]==false)
				dfsTS(visited, x, R);
		System.out.println(s + " is added a the first");
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
	}	

}
