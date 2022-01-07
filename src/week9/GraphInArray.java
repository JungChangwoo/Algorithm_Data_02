package week9;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;

public class GraphInArray {
	
	private class TableEntry{ //arraySize�� ���ÿ� ArrayList�� ������ �ϳ��� ������ ǥ���ϱ� ���ؼ�
		int arraySize;
		ArrayList<String> arrayEntry;
		
		private TableEntry() {
			arraySize = 0;
			arrayEntry = null;
		}
	}
	
	String graphName ;
	ArrayList<String> vertices ;
	ArrayList<TableEntry> adjacentArray ;
	int maxNumber = 0;
	boolean [] visited ; 

	public GraphInArray(int maxN) {
		maxNumber = maxN ;
		visited = new boolean [maxNumber];
	}

	public void createGraph(String name) {
		graphName = name;
		vertices = new ArrayList<String>();
		adjacentArray = new ArrayList<TableEntry>();	
	}

	public void showGraph() {
		showGraphInList();
	}

	private void showGraphInList() {
		System.out.println("\n< "+graphName+" in AdjacentArray >");
		for (int i=0; i<vertices.size();i++){
			System.out.print(vertices.get(i)+"  ");
			 for (String s : adjacentArray.get(i).arrayEntry)
				 System.out.print(" => "+ s );
			 System.out.println();
		}
	}

	public void insertVertex(String s) {
		if (!vertices.contains(s)) {
			vertices.add(s);
			adjacentArray.add(new TableEntry()); //TableEntry�� �ϳ��� ���
		}
	}	

	public void insertEdge(String from, String to) {
		insertVertex(from);
		insertVertex(to);

		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);

		adjacentArray.get(f).arrayEntry.add(to);
		adjacentArray.get(t).arrayEntry.add(from);

	}	

	public void deleteVertex(String s) {
		int index = vertices.indexOf(s);
		if (index>=0) {
			for (int i=0; i<vertices.size(); i++) {
				deleteEdge(s, vertices.get(i));
				deleteEdge(vertices.get(i), s);
			}
			adjacentArray.remove(index);
			vertices.remove(index);
		}
	}

	public void deleteEdge(String from, String to) {
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		if (f>=0 && t>=0) {
			adjacentArray.get(f).arrayEntry.remove(to); ;
			adjacentArray.get(t).arrayEntry.remove(from); ;
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
			for (String v : adjacentArray.get(index).arrayEntry) 
					result.add(v);
		}
		return result;
	}

	public void initVisited() {
		for (int i=0; i<vertices.size();i++) 
			visited[i] = false;
	}

	public void DFS(String s) {
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecursion(s);
	}
	private void DFSRecursion(String s) {
		int index = vertices.indexOf(s);					//1. Index
		visited[index]=true;								//2. visited
		System.out.println(s+" is visited ");
		for (String v : adjacentArray.get(index).arrayEntry)//3. ������ ���� Loop
			if (!visited[vertices.indexOf(v)])
				DFSRecursion(v);							//4. �湮�� �� �Ǿ��ٸ� Recursion
	}

	public void BFS(String s) {
		initVisited();
		System.out.println("\n *** BFS Iteration *** \n");
		BFSIteration(s);
	}

	public void BFSIteration(String s) {
		Deque<String> que = new ArrayDeque<String>();
		visited[vertices.indexOf(s)]=true;					//1. Index�� ã�Ƽ� visited
		System.out.println(s+" is visited ");
		que.add(s);											//2. que�� add

		while (!que.isEmpty()) {							//3. ��� ������ �� ������ 
			String v = que.poll();
			int index = vertices.indexOf(v);				
			for (String u : adjacentArray.get(index).arrayEntry) {//4. ���� ������ ������ ���� Loop
				int ui = vertices.indexOf(u);						
				if (!visited[ui]) {
					visited[ui]=true;
					System.out.println(u+" is visited ");
					que.add(u);										//5. ���� ������ ��� �����༭, �ٽ� loop
				}	
			}
		}
	}

}
