package week10HW;

import java.util.ArrayList;

import java.util.LinkedList;

import java.util.PriorityQueue;

import java.util.Queue;

import java.util.Scanner;

public class prim {

	static int V, E;

	static boolean visit[];

	static ArrayList<Edge>[] graph;

	static ArrayList<Edge> MST;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		V = sc.nextInt();

		E = sc.nextInt();

		graph = new ArrayList[V + 1];

		visit = new boolean[V + 1];

		for (int i = 0; i <= V; i++)

			graph[i] = new ArrayList<Edge>();

		MST = new ArrayList<Edge>();

		for (int i = 1; i <= E; i++) {

			int u = sc.nextInt();

			int v = sc.nextInt();

			int val = sc.nextInt();

			graph[u].add(new Edge(u, v, val));

			graph[v].add(new Edge(v, u, val));

		}

		int point = 1;

		solve(point);

		for (int i = 0; i < MST.size(); i++) {

			System.out.println(MST.get(i).begin + " " + MST.get(i).end + " " + MST.get(i).value);

		}

	}

	private static void solve(int P) {

		// TODO Auto-generated method stub

		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(P);

		while (!queue.isEmpty()) {

			int now = queue.poll();

			visit[now] = true;

			for (Edge e : graph[now]) {

				if (!visit[e.end])

					pq.add(e);

			}

			while (!pq.isEmpty()) {

				Edge e = pq.poll();

				if (!visit[e.end]) {

					queue.add(e.end);

					visit[e.end] = true;

					MST.add(e);

					break;

				}

			}

		}

	}

	public static class Edge implements Comparable<Edge> {

		int begin;

		int end;

		int value;

		public Edge(int b, int e, int v) {

			this.begin = b;

			this.end = e;

			this.value = v;

		}

		@Override

		public int compareTo(Edge o) {

			// TODO Auto-generated method stub

			return this.value - o.value;

		}

	}

}