package week11;

import java.util.ArrayList;
import java.util.Arrays;
import week9.GraphInList;


/**
 * 위상 정렬 : 유향 그래프의 꼭짓점들(Vertex)을 엣지의 방향을 거스르지 않도록 나열하는 것 = 모든 정점을 일렬로 나열하되, 정점
 * x에서 정점 y로 가는 간선이 있으면 x는 반드시 y보다 앞에 위치
 */
public class TopologicalSort2 extends GraphInList {
	int[] c;
	ArrayList<String> verticesCopy ;
	public TopologicalSort2(int maxN) {
		super(maxN);
		c = new int[maxN];
		Arrays.fill(c, 0);
		verticesCopy = new ArrayList<String>();
	}

	public void insertEdge(String from, String to) {
		insertVertex(from);
		insertVertex(to);

		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);

		adjacentList.get(f).add(to);
	}
	public boolean deleteEdge(String from, String to) {
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		if (f >= 0 && t >= 0) {
			if(adjacentList.get(f).remove(to))
				return true;
		}
		return false;
	}
	public void deleteVertex(String s) {
		int index = vertices.indexOf(s);
		c[verticesCopy.indexOf(s)] = -1;
		if (index>=0) {
			for (int i=0; i<vertices.size(); i++) {
				if(deleteEdge(s, vertices.get(i)))//삭제된 정점에서 출발한 Edge 삭제
					c[verticesCopy.indexOf(vertices.get(i))] -= 1;
				deleteEdge(vertices.get(i), s); //도착지가 삭제된 정점인 Edge 삭제 
			}
			adjacentList.remove(index);			//adjacentList 의 LinkedList 삭제
			vertices.remove(index);				//vertices에서 삭제
		}
	}

	public void TPSort1() {
		// c에 각 정점의 진입간선 수를 기록
		initIncoming();
		for(int i=0; i<c.length; i++) {
		}

		String[] A = new String[vertices.size()]; // Path
		int nOfVertices = vertices.size(); // 나중에 delete되기 때문에, 따로 변수로 만들어 줌
		for(int i=0; i<vertices.size(); i++) { //나중에 delete되기 때문에,,, Copy
			verticesCopy.add(vertices.get(i));
		}
		//시작
		for (int i = 0; i < nOfVertices; i++) {
			A[i] = getNextNode(); // 진입간선이 없는 정점을 선택함
			deleteVertex(A[i]); // 정점 u와 u의 진출간선을 모두 제거
			showGraph();
		} // => 이 시점에 배열 A에는 정점들이 위상정렬되어 있다.
		System.out.print(">>> Start ");
		for (int i = 0; i < nOfVertices; i++)
			System.out.print("=> " + A[i]);
		System.out.println();
	}

	private void initIncoming() {
		for (int u = 0; u < vertices.size(); u++) {
			for (int v = 0; v < adjacentList.get(u).size(); v++) {// incoming Edge loop
				c[vertices.indexOf(adjacentList.get(u).get(v))]++;
			}
		}
	}

	private String getNextNode() {
		for (int i = 0; i < vertices.size(); i++) {
			if(c[verticesCopy.indexOf(vertices.get(i))] == 0) {
				System.out.println("선택된 노드 : " + vertices.get(i));
				return vertices.get(i);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		int maxNoVertex = 6;
		String[] vertices = { "물붓기", "점화하기", "봉지뜯기", "라면넣기", "스프넣기", "계란넣기" };
		int[][] graphEdges = { { 0, 1 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 2, 3 }, { 2, 4 }, { 3, 5 }, { 4, 5 } };

		TopologicalSort2 myGM = new TopologicalSort2(maxNoVertex);

		myGM.createGraph("Topological Sort1"); 

		for (int i = 0; i < graphEdges.length; i++)
			myGM.insertEdge(vertices[graphEdges[i][0]], vertices[graphEdges[i][1]]);
		myGM.showGraph();

		System.out.println("Topological Sort1 : start");
		myGM.TPSort1();
	}

}
