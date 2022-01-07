package week10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Kruscal extends WGraphInList  {
	DSet[] disjointSet;
//	ArrayList<String> parent;  //���� �����̸�, ���� �θ� ����Ű�� ���� �� => �ٸ� �θ� ����Ű�� �ִٸ�, �ٸ� ���� 
	HashSet<EdgeElement> T ;   //����Ʈ��
	LinkedList<EdgeElement> Q; //�������� (ũ�� ������)

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

		EdgeElement newEdge = new EdgeElement(from, to, w); //sort�� �� ���ڰ����� �־��ֱ� ���ؼ� ���� ����

		adjacentList.get(f).add(newEdge);
		adjacentList.get(t).add(new EdgeElement(to, from, w));

		sortInsert(newEdge);
	}	

	private void sortInsert(EdgeElement newEdge) {
		int index=0;
		Iterator<EdgeElement> iter = Q.iterator();
		while (iter.hasNext()) { // ���ο� Edge�� ũ�� ������ ���ĵǾ� ���� ��, �ڽ��� �ڸ��� ã�Ƽ� Add�� ����
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
		// T(����Ʈ��)�� ��� ������ ������ ������
		while(T.size()<maxNumber-1) {
			EdgeElement euv = Q.remove(0); //���� ���� ������ ����
			// ���� �ٸ� ���տ� �ִٸ�, 
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
		DSet p = disjointSet[vertices.indexOf(s)].findSet(disjointSet[vertices.indexOf(s)]);//root��ȯ
		return vertices.get(p.key);
	}
			

}