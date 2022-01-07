package week12;
import java.util.Arrays; 
import java.util.HashSet;

import week10.WGraphInList;

public class DijkstraSP extends WGraphInList {
	int [] d  ;   // distance; 
	int r = -1;   // start node
	HashSet<String> S, V ;
	
	String[] prev;

	public DijkstraSP(int max) {
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
	//Override
	public void insertEdge(String from, String to, int w) {
		insertVertex(from);
		insertVertex(to);

		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);

		adjacentList.get(f).add(new EdgeElement(from, to, w));
	}	
	//override
	public void deleteEdge(String from, String to) {
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		if (f>=0 && t>=0) {
			adjacentList.get(f).remove(getEdge(from, to));
		}
	}
	
	public void ShortestPath() {
		// distance�� 9999�� �����Ǿ� �ֱ� ������, �ڵ����� extractMin�� �ϸ�, ������ ������ �߿��� ���� ���� ���� Return�� 
		while(S.size()<maxNumber) {
			String u = extractMin(diff(V,S));  // diff(V,S) == V-S
			S.add(u);
			System.out.println(">>> "+u+" is selected.");
			// u ������ ������ ������ �����ϴ� ������ �ϳ��� ���鼭 ���� ���� ���� d�� �־���(d�� ó���� 9999)  
			for (String v : adjacent(u)) {  // L(u) == adjacent(u)
				HashSet<String> temp = diff(V,S);
				int wuv = getWeight(u, v);
				int dv = d[vertices.indexOf(v)];
				int du = d[vertices.indexOf(u)];
				
				if (temp.contains(v) &&  (du+wuv)<dv) {
					d[vertices.indexOf(v)] = du+wuv;
					prev[vertices.indexOf(v)] = u;
				}
			}
		}
		for (int i=0; i<maxNumber; i++)
			System.out.print(vertices.get(i)+"("+d[i]+")");
		System.out.println();

	}
	
	public void showShortestPath() {
		for(int i=0; i<maxNumber; i++) {
			System.out.println(prev[i]+" => "+vertices.get(i)+"("+d[i]+")");
		}
	}
	
	private int getWeight(String u, String v) {
		return getEdge(u, v).weight;
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
		String [] vertices = { "����", "��õ", "����", "�뱸", "����", "�λ�", "���"};
		int [][] graphEdges = { {0, 1, 11 }, {0, 2, 8}, {0, 3, 9}, {1, 3, 13}, 
				      {1, 6, 8}, {2, 4, 10}, {3, 4, 5}, {3, 5, 12}, {5, 6, 7} };

		DijkstraSP myG = new DijkstraSP(vertices.length);

		myG.createGraph("Dijktra-Test Graph");
		for (int i = 0; i<graphEdges.length; i++)
			myG.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		myG.showGraph();
		
		System.out.println("\nDijkstraSP Algorithm starts from "+"����");

		myG.init("����");
		myG.ShortestPath();
		myG.showShortestPath();

	}

}
