package week10HW;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
//
//public class GraphInArrayPrime {
//
//	private class TableEntry {
//		String vertex;
//		int cost;
//		ArrayList<Edge> arrayEntry;
//
//		private TableEntry() {
//			String vertex = "";
//			cost = 0;
//			arrayEntry = null;
//		}
//	}
//
//	String graphName;
//	ArrayList<String> vertices;
//	ArrayList<TableEntry> adjacentArray;
//	int maxNumber = 0;
//	boolean[] visited;
//
//	public GraphInArrayPrime(int maxN) {
//		maxNumber = maxN;
//		visited = new boolean[maxNumber];
//	}
//
//	public void createGraph(String name) {
//		graphName = name;
//		vertices = new ArrayList<String>();
//		adjacentArray = new ArrayList<TableEntry>();
//	}
//
//	public void showGraph() {
//		showGraphInList();
//	}
//
//	private void showGraphInList() {
//		System.out.println("\n< " + graphName + " in AdjacentArray >");
//		for (int i = 0; i < vertices.size(); i++) {
//			System.out.print(vertices.get(i) + "  ");
//			for (Edge s : adjacentArray.get(i).arrayEntry)
//				System.out.print(" => " + s.vertex);
//			System.out.println();
//		}
//	}
//
//	public void insertVertex(String s) {
//		if (!vertices.contains(s)) {
//			vertices.add(s);
//			adjacentArray.add(new TableEntry());
//		}
//	}
//
//	public void insertEdge(Edge from, Edge to) {
//		insertVertex(from.vertex);
//		insertVertex(to.vertex);
//
//		int f = vertices.indexOf(from.vertex);
//		int t = vertices.indexOf(to.vertex);
//
//		adjacentArray.get(f).arrayEntry.add(to);
//		adjacentArray.get(t).arrayEntry.add(from);
//
//	}
//
//	public void deleteVertex(String s) {
//		int index = vertices.indexOf(s);
//		if (index >= 0) {
//			for (int i = 0; i < vertices.size(); i++) {
//				deleteEdge(s, vertices.get(i));
//				deleteEdge(vertices.get(i), s);
//			}
//			adjacentArray.remove(index);
//			vertices.remove(index);
//		}
//	}
//
//	public void deleteEdge(String from, String to) {
//		int f = vertices.indexOf(from);
//		int t = vertices.indexOf(to);
//		if (f >= 0 && t >= 0) {
//			int toVertex = adjacentArray.get(f).arrayEntry.indexOf(from);
//			adjacentArray.get(f).arrayEntry.remove(toVertex);
//			int fromVertex = adjacentArray.get(t).arrayEntry.indexOf(to);
//			adjacentArray.get(t).arrayEntry.remove(fromVertex);
//			;
//		}
//	}
//
//	public boolean isEmpty() {
//		if (vertices.size() == 0)
//			return true;
//		else
//			return false;
//	}
//
//	public HashSet<String> adjacent(String s) {
//		HashSet<String> result = new HashSet<String>();
//
//		int index = vertices.indexOf(s);
//		if (index >= 0) {
//			for (Edge v : adjacentArray.get(index).arrayEntry)
//				result.add(v.vertex);
//		}
//		return result;
//	}
//
//	public void initVisited() {
//		for (int i = 0; i < vertices.size(); i++)
//			visited[i] = false;
//	}
//
//	public void DFS(String s) {
//		initVisited();
//		System.out.println("\n *** DFS Recursion *** \n");
//		DFSRecursion(s);
//	}
//
//	private void DFSRecursion(String s) {
//		int index = vertices.indexOf(s);
//		visited[index] = true;
//		System.out.println(s + " is visited ");
//		for (Edge v : adjacentArray.get(index).arrayEntry)
//			if (!visited[vertices.indexOf(v.vertex)])
//				DFSRecursion(v.vertex);
//	}
//
//	public void BFS(String s) {
//		initVisited();
//		System.out.println("\n *** BFS Iteration *** \n");
//		BFSIteration(s);
//	}
//
//	public void BFSIteration(String s) {
//		Deque<String> que = new ArrayDeque<String>();
//		visited[vertices.indexOf(s)] = true;
//		System.out.println(s + " is visited ");
//		que.add(s);
//
//		while (!que.isEmpty()) {
//			String v = que.poll();
//			int index = vertices.indexOf(v);
//			for (Edge u : adjacentArray.get(index).arrayEntry) {
//				int ui = vertices.indexOf(u.vertex);
//				if (!visited[ui]) {
//					visited[ui] = true;
//					System.out.println(u + " is visited ");
//					que.add(u.vertex);
//				}
//			}
//		}
//	}
//
//	public void Prime() {
//		int r = 0;
//		ArrayList<TableEntry> selectedVertices = new ArrayList<TableEntry>();
//		; // 선택된 정점 집합
//		for (int i = 0; i < adjacentArray.size(); i++) {
//			adjacentArray.get(i).cost = 999999;
//		}
//		adjacentArray.get(r).cost = 0;
//		while (selectedVertices.size() == adjacentArray.size()) {
//			ArrayList<TableEntry> vMinusS = new ArrayList<TableEntry>();
//			for (int i = 0; i < adjacentArray.size(); i++) {
//				boolean isMatch = false;
//				for (int j = 0; j < selectedVertices.size(); j++) {
//					if (adjacentArray.get(i).vertex.equals(selectedVertices.get(j).vertex)) {
//						isMatch = true;
//					}
//				}
//				if(isMatch = false) {
//					vMinusS.add(adjacentArray.get(i));
//				}
//			}
//			String minVertex = extractMin(vMinusS, adjacentArray.get(r));
////			selectedVertices.add
//		}
//	}
//
//	private String extractMin(ArrayList<TableEntry> vMinusS, TableEntry tableEntry) {
//		for(int i=0; i<vMinusS.size(); i++) {
//			if(vMinusS.get(i).equals(tableEntry)) {
//				int min = 999999;
//				String minVertex = "";
//				for(int j=0; j<vMinusS.get(i).arrayEntry.size(); j++) {
//					if(vMinusS.get(i).arrayEntry.get(j).weight < min) {
//						min = vMinusS.get(i).arrayEntry.get(j).weight;
//						minVertex = vMinusS.get(i).arrayEntry.get(j).vertex;
//					}
//				}
//				return minVertex;
//			}
//		}
//		return null;
//	}
//
//}
//
//class Edge {
//	String vertex;
//	int weight;
//	
//	Edge(String to, int weight) {
//		vertex = to;
//		this.weight = weight;
//	}
//}

