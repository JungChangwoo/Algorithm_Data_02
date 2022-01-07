package week11;

import java.util.Arrays;
import java.util.LinkedList;

import week9.GraphInMatrix; 
/**
 * ���� ���� : ���� �׷����� ��������(Vertex)�� ������ ������ �Ž����� �ʵ��� �����ϴ� �� 
 * = ��� ������ �Ϸķ� �����ϵ�, ���� x���� ���� y�� ���� ������ ������ x�� �ݵ�� y���� �տ� ��ġ
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
		int nOfVertices = vertices.size(); // ���߿� delete�Ǳ� ������, ���� ������ ����� ��
		
		for(int i=0; i<nOfVertices; i++) {
			A[i] = getNextNode(); //���԰����� ���� ������ ������ 
			deleteVertex(A[i]);   //���� u�� u�� ���Ⱓ���� ��� ����
			showGraph(); 
		}						  //=> �� ������ �迭 A���� �������� �������ĵǾ� �ִ�. 
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
			if(tempSum == 0) // incoming Edge�� ������ return 
				return vertices.get(i);
		}
		return null;
	}
	// ���� �켱 Ž�� Recursion����
	public void TPSort2() {
		LinkedList<String> R = new LinkedList<>();
		boolean[] visited = new boolean[vertices.size()]; //Ž���� ���ؼ� 
		Arrays.fill(visited, false); //�ʱ�ȭ
		
		for(String s : vertices) { 					// ��� ������ ���ؼ� Loop
			if(visited[vertices.indexOf(s)]==false)  // �湮���� ���� �����̶��,
				dfsTS(visited, s, R);
		}
		
		System.out.println(R);
	}
	
	private LinkedList<String> dfsTS(boolean[] visited, String s, LinkedList<String> R){
		visited[vertices.indexOf(s)] = true;			// �湮
		for(String x : adjacent(s))						// ������ ���� Loop
			if(visited[vertices.indexOf(x)]==false)		// ���� �湮���� �ʾҴٸ�, Recursion
				dfsTS(visited, x, R);
		System.out.println(s + " is added a the first");// �� �̻� �� ���� ������ add
		R.addFirst(s);
		return R;										
	}
	
	public static void main(String[] args) {
		int maxNoVertex = 10;
		String[] vertices = {"���ױ�", "��ȭ�ϱ�", "�������", "���ֱ�", "�����ֱ�", "����ֱ�"};
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
