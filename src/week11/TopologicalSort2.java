package week11;

import java.util.ArrayList;
import java.util.Arrays;
import week9.GraphInList;


/**
 * ���� ���� : ���� �׷����� ��������(Vertex)�� ������ ������ �Ž����� �ʵ��� �����ϴ� �� = ��� ������ �Ϸķ� �����ϵ�, ����
 * x���� ���� y�� ���� ������ ������ x�� �ݵ�� y���� �տ� ��ġ
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
				if(deleteEdge(s, vertices.get(i)))//������ �������� ����� Edge ����
					c[verticesCopy.indexOf(vertices.get(i))] -= 1;
				deleteEdge(vertices.get(i), s); //�������� ������ ������ Edge ���� 
			}
			adjacentList.remove(index);			//adjacentList �� LinkedList ����
			vertices.remove(index);				//vertices���� ����
		}
	}

	public void TPSort1() {
		// c�� �� ������ ���԰��� ���� ���
		initIncoming();
		for(int i=0; i<c.length; i++) {
		}

		String[] A = new String[vertices.size()]; // Path
		int nOfVertices = vertices.size(); // ���߿� delete�Ǳ� ������, ���� ������ ����� ��
		for(int i=0; i<vertices.size(); i++) { //���߿� delete�Ǳ� ������,,, Copy
			verticesCopy.add(vertices.get(i));
		}
		//����
		for (int i = 0; i < nOfVertices; i++) {
			A[i] = getNextNode(); // ���԰����� ���� ������ ������
			deleteVertex(A[i]); // ���� u�� u�� ���Ⱓ���� ��� ����
			showGraph();
		} // => �� ������ �迭 A���� �������� �������ĵǾ� �ִ�.
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
				System.out.println("���õ� ��� : " + vertices.get(i));
				return vertices.get(i);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		int maxNoVertex = 6;
		String[] vertices = { "���ױ�", "��ȭ�ϱ�", "�������", "���ֱ�", "�����ֱ�", "����ֱ�" };
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
