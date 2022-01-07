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
				deleteEdge(s, vertices.get(i)); //������ �������� ����� Edge ����
				deleteEdge(vertices.get(i), s); //�������� ������ ������ Edge ���� 
			}
			adjacentList.remove(index);			//adjacentList �� LinkedList ����
			vertices.remove(index);				//vertices���� ����
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
			for (String v : adjacentList.get(index)) // LinkedList�� �ִ� String�� ���鼭
					result.add(v);
		}
		return result;
	}

	public void initVisited() {
		for (int i=0; i<vertices.size();i++) 
			visited[i] = false;
	}

	public void DFS(String s) { //���̸� �켱Ž���ϱ� ���ؼ�, �ٷιٷ� Recursion
		initVisited();
		System.out.println("\n *** DFS Recursion *** \n");
		DFSRecursion(s);
	}
	private void DFSRecursion(String s) {
		int index = vertices.indexOf(s);
		visited[index]=true;
		System.out.println(s+" is visited ");
		for (String v : adjacentList.get(index)) //�������κ��� ������ �� Loop
			if (!visited[vertices.indexOf(v)])
				DFSRecursion(v);				 //���� �湮 X => recursion 
	}

	public void BFS(String s) { //�ʺ� �켱Ž���� ���ؼ� ������ ����������� Loop�� ���� ����  / Recursion X
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
			for (String u : adjacentList.get(index)) { //�������κ��� ������ �� Loop
				int ui = vertices.indexOf(u);		   //������ �͵� ���� ��� �湮�Ѵ�.
				if (!visited[ui]) {
					visited[ui]=true;
					System.out.println(u+" is visited ");
					que.add(u);						   //������ �͵��� ��� �湮�ǰ� ����, �ϳ��� �ٽ� �װŸ� �������� Loop
				}
			}
		}
	}

}
