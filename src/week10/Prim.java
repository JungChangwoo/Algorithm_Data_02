package week10;

import java.util.Arrays; 
import java.util.HashSet;

public class Prim extends WGraphInList {
	int [] d  ;   // distance; 각 정점의 Value
	int r = -1;   // start node
	HashSet<String> S, V ;

	public Prim(int max) {
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
	}
	
	public void MST() {
		while(S.size()<maxNumber) {
			String u = extractMin(diff(V,S));  // diff(V,S) == V-S, 처음에는 시작노드가 Return
			S.add(u);
			System.out.println(">>> "+u+" is selected.");
			// u 정점과 인접한 정점을 loop
			for (String v : adjacent(u)) {  // L(u) == adjacent(u)
				HashSet<String> temp = diff(V,S); // S에 속하지 않는 정점들 중 
				int wuv = getWeight(u, v);		  // 간선의 가중치
				int dv = d[vertices.indexOf(v)];  // 인접한 정정의 값
				if (temp.contains(v) &&  wuv<dv) {  // 만약에, S에 속하지 않으며, 간선의 가중치가 더 작다면,
					d[vertices.indexOf(v)] = wuv ;// 간선의 가중치의 값을 정점의 값으로 변경 
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

}
