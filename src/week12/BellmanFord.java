package week12;

import java.util.Arrays;

import week10.WGraphInList;

public class BellmanFord extends WGraphInList {
	int[] d; // distance;
	int r = -1; // start node

	String[] prev;

	public BellmanFord(int max) {
		super(max);
	}

	public void init(String start) {
		d = new int[maxNumber];

		prev = new String[maxNumber];

		r = vertices.indexOf(start);
		Arrays.fill(d, 9999);
		d[r] = 0;
	}

	public void insertEdge(String from, String to, int w) {
		insertVertex(from);
		insertVertex(to);

		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);

		adjacentList.get(f).add(new EdgeElement(from, to, w));

	}

	public void deleteEdge(String from, String to) {
		int f = vertices.indexOf(from);
		int t = vertices.indexOf(to);
		if (f >= 0 && t >= 0) {
			adjacentList.get(f).remove(getEdge(from, to));
		}
	}

	public void ShortestPath() {
		for (int i = 0; i < maxNumber - 1; i++) {
			for (int u = 0; u < maxNumber; u++) {
				for (int v = 0; v < maxNumber; v++) {
					int wuv = getWeight(vertices.get(u), vertices.get(v));
					if ((d[u] + wuv) < d[v]) {
						d[v] = d[u] + wuv;
						prev[v] = vertices.get(u);
					}
				}
			}
		}
		// ���� ����Ŭ ���� ���� Ȯ��
		for (int u = 0; u < maxNumber; u++) {
			for (int v = 0; v < maxNumber; v++) {
				int wuv = getWeight(vertices.get(u), vertices.get(v));
				if ((d[u] + wuv) < d[v]) {
					System.out.println("���� ����Ŭ ����");
					return;
				}
			}
		}
	}

	public void showShortestPath() {
		for (int i = 0; i < maxNumber; i++) {
			System.out.println(prev[i] + " => " + vertices.get(i) + "(" + d[i] + ")");
		}
	}

	private int getWeight(String u, String v) {
		if(getEdge(u, v) == null) return 99;
		else return getEdge(u, v).weight;
	}

	public static void main(String[] args) {
		String[] vertices = { "����", "��õ", "����", "�뱸", "����", "�λ�", "���" };
		int[][] graphEdges = { { 0, 1, 11 }, { 0, 2, -2 }, { 0, 3, 9 }, { 1, 3, 13 }, { 1, 6, 8 }, { 2, 4, -1 },
				{ 3, 4, 5 }, { 3, 5, -2 }, { 5, 6, 7 } };

		BellmanFord myG = new BellmanFord(vertices.length);

		myG.createGraph("Dijktra-Test Graph");
		for (int i = 0; i < graphEdges.length; i++)
			myG.insertEdge(vertices[graphEdges[i][0]], vertices[graphEdges[i][1]], graphEdges[i][2]);
		myG.showGraph();

		System.out.println("\nDijkstraSP Algorithm starts from " + "����");

		myG.init("����");
		myG.ShortestPath();
		myG.showShortestPath();

	}

}
