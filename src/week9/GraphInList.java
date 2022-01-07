package week9;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class GraphInList {

	public String graphName ;
	public ArrayList<String> vertices ;
	public ArrayList<LinkedList<String>> adjacentList ;
	public int maxNumber = 0;
	public boolean [] visited ; 

	public GraphInList(int maxN) {
		maxNumber = maxN ;
		visited = new boolean [maxNumber];
	}

	public void createGraph(String name) {
		graphName = name;
		vertices = new ArrayList<String>();
		adjacentList = new ArrayList<LinkedList<String>>();	
	}

	public void showGraph() {
		showGraphInList();
	}

	private void showGraphInList() {
		System.out.println("\n< "+graphName+" in AdjacentList >");
		for (int i=0; i<vertices.size();i++){
			System.out.print(vertices.get(i)+"  ");
			 for (String s : adjacentList.get(i))
				 System.out.print(" => "+ s );
			 System.out.println();
		}
	}

	public void insertVertex(String s) {
		if (!vertices.contains(s)) {
			vertices.add(s);
			adjacentList.add(new LinkedList<String>());
		}
	}	

	public void insertEdge(String from, String to) {
		insertVertex(from);
		insertVertex(to);

		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);

		adjacentList.get(f).add(to);
		adjacentList.get(t).add(from);

	}	

	public void deleteVertex(String s) {
		int index = vertices.indexOf(s);
		if (index>=0) {
			for (int i=0; i<vertices.size(); i++) {
				deleteEdge(s, vertices.get(i)); //삭제된 정점에서 출발한 Edge 삭제
				deleteEdge(vertices.get(i), s); //도착지가 삭제된 정점인 Edge 삭제 
			}
			adjacentList.remove(index);			//adjacentList 의 LinkedList 삭제
			vertices.remove(index);				//vertices에서 삭제
		}
	}

	public boolean deleteEdge(String from, String to) {
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		if (f>=0 && t>=0) {
			adjacentList.get(f).remove(to); ;
			adjacentList.get(t).remove(from); ;
		}
		return true;
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
			for (String v : adjacentList.get(index)) // LinkedList에 있는 String을 돌면서
					result.add(v);
		}
		return result;
	}

	public void initVisited() {
		for (int i=0; i<vertices.size();i++) 
			visited[i] = false;
	}

	public void DFS(String s) { //깊이를 우선탐색하기 위해서, 바로바로 Recursion
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecursion(s);
	}
	private void DFSRecursion(String s) {
		int index = vertices.indexOf(s);
		visited[index]=true;
		System.out.println(s+" is visited ");
		for (String v : adjacentList.get(index)) //정점으로부터 인접한 것 Loop
			if (!visited[vertices.indexOf(v)])
				DFSRecursion(v);				 //아직 방문 X => recursion 
	}

	public void BFS(String s) { //너비 우선탐색을 위해서 인접한 정점들까지만 Loop을 도는 구조  / Recursion X
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSIteration(s);
	}

	public void BFSIteration(String s) {
		Deque<String> que = new ArrayDeque<String>();
		visited[vertices.indexOf(s)]=true;
		System.out.println(s+" is visited ");
		que.add(s);

		while (!que.isEmpty()) {
			String v = que.poll();
			int index = vertices.indexOf(v);
			for (String u : adjacentList.get(index)) { //정점으로부터 인접한 것 Loop
				int ui = vertices.indexOf(u);		   //인접한 것들 먼저 모두 방문한다.
				if (!visited[ui]) {
					visited[ui]=true;
					System.out.println(u+" is visited ");
					que.add(u);						   //인접한 것들이 모두 방문되고 나면, 하나씩 다시 그거를 기준으로 Loop
				}
			}
		}
	}

}
