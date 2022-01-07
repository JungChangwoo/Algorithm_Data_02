package week10;

import java.util.Arrays; 
import java.util.HashSet;

public class PrimWithHeap extends WGraphInList {
	int [] d  ;   // distance; �� ������ Value
	int r = -1;   // start node
	HashSet<String> S, V ;
	
	HeapTree minHeap = new HeapTree(); 

	public PrimWithHeap(int max) {
		super(max);
	}
	public void init(String start) {
		d = new int [maxNumber];
		S = new HashSet<String>();
		V = new HashSet<String>();

		for (String s : vertices )
			V.add(s);
		r = vertices.indexOf(start);
		Arrays.fill(d, 9999);
		d[r]=0;
		minHeap.insertHeap(r, 0);
	}
	
	public void MST() {
		while(S.size()<maxNumber) {
			String u = extractMin();  // diff(V,S) == V-S, ó������ ���۳�尡 Return
			S.add(u);
			System.out.println(">>> "+u+" is selected.");
			// u ������ ������ ������ loop
			for (String v : adjacent(u)) {  // L(u) == adjacent(u)
				HashSet<String> temp = diff(V,S); // S�� ������ �ʴ� ������ �� 
				int wuv = getWeight(u, v);		  // ������ ����ġ
				int dv = d[vertices.indexOf(v)];  // ������ ������ ��
				if (temp.contains(v) &&  wuv<dv) {  // ���࿡, S�� ������ ������, ������ ����ġ�� �� �۴ٸ�,
					d[vertices.indexOf(v)] = wuv ;// ������ ����ġ�� ���� ������ ������ ���� 
					minHeap.insertHeap(vertices.indexOf(v), wuv);
				}
			}
		}
		for (int i=0; i<maxNumber; i++)
			System.out.print(vertices.get(i)+"("+d[i]+")");
		System.out.println();

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
	
	private String extractMin() {
		String minVertex = vertices.get(minHeap.deleteHeap());
		return minVertex;
	}

}