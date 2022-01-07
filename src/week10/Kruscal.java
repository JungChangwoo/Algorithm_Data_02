package week10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Kruscal extends WGraphInList  {
	DSet[] disjointSet;
//	ArrayList<String> parent;  //같은 집합이면, 같은 부모를 가리키고 있을 것 => 다른 부모를 가리키고 있다면, 다른 집합 
	HashSet<EdgeElement> T ;   //신장트리
	LinkedList<EdgeElement> Q; //간선집합 (크기 순으로)

	public Kruscal(int max) {
		super(max);
//		parent = new ArrayList<String>() ;
		disjointSet = new DSet[max];
		Q = new LinkedList<EdgeElement>();
		T = new HashSet<EdgeElement>();
	}	
	
	public void init() {
		for(int i=0; i<maxNumber; i++) {
			disjointSet[i] = new DSet();
			disjointSet[i].makeSet(i);
			System.out.println(disjointSet[i].toString());
		}
	}
	
	// overriding
	public void insertEdge(String from, String to, int w) {
		insertVertex(from);
		insertVertex(to);

		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);

		EdgeElement newEdge = new EdgeElement(from, to, w); //sort할 때 인자값으로 넣어주기 위해서 따로 선언

		adjacentList.get(f).add(newEdge);
		adjacentList.get(t).add(new EdgeElement(to, from, w));

		sortInsert(newEdge);
	}	

	private void sortInsert(EdgeElement newEdge) {
		int index=0;
		Iterator<EdgeElement> iter = Q.iterator();
		while (iter.hasNext()) { // 새로운 Edge를 크기 순으로 정렬되어 있을 때, 자신의 자리를 찾아서 Add를 해줌
			if (newEdge.weight>iter.next().weight) 
				index++; 
		}
		Q.add(index,newEdge);
		showQ();
	}

	private void showQ() {
		System.out.print("\n>>> Q state : ");
		for (EdgeElement e : Q) {
			System.out.print("-> "+e.weight);
		}
		System.out.println();

	}

	public void MST() {
		// T(신장트리)가 모든 정점을 포함할 때까지
		while(T.size()<maxNumber-1) {
			EdgeElement euv = Q.remove(0); //가장 작은 간선을 꺼냄
			// 서로 다른 집합에 있다면, 
			if (findSet(euv.source)!=findSet(euv.destination)) {
//				union(euv.source, euv.destination);
				disjointSet[vertices.indexOf(euv.source)].union(disjointSet[vertices.indexOf(euv.destination)]);
				System.out.println(euv+"  is selected");
				T.add(euv);
			}
		}
		for(int i=0; i<maxNumber; i++) {
			disjointSet[i].showParent();
		}
	}
	
	private String findSet(String s) {
		DSet p = disjointSet[vertices.indexOf(s)].findSet(disjointSet[vertices.indexOf(s)]);//root반환
		return vertices.get(p.key);
	}
			

}