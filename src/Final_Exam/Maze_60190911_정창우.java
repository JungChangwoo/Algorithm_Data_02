package Final_Exam;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class Maze_60190911_��â�� {

///////////////////////////////////////////////////////////////////
/// 0) �й�, �̸��� �����Ͻÿ�.
	String numId = "60190911"; // �й�
	String name = "��â��"; // �̸�
///////////////////////////////////////////////////////////////////
	int last = 0;

	public class Coordinate { // ��ǥ
		int x, y;

		public Coordinate(int i, int j) {
			x = i;
			y = j;
		}

		public boolean equals(Coordinate other) {
			return (this.x == other.x) && (this.y == other.y);
		}

		public void copyFrom(Coordinate other) {
			this.x = other.x;
			this.y = other.y;
		}

		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}

	int size;
	Coordinate start, destin; // ���, ����

	public Maze_60190911_��â��(int n) {
		size = n;
		start = new Coordinate(0, 0);
		destin = new Coordinate(size - 1, size - 1); // ��ü���� �� ������

		System.out.println("<< " + numId + " : " + name + " >>");
	}

	public int DFS(int[][] in) {
		int[][] m = deepCopy(in);
		show("Initial State", m);

		Coordinate p = new Coordinate(start.x, start.y);
		int seq = 1;
		int n = DFS(m, p, seq); // sequence-value starts from 1, ie. [0,0]=1 //������ �湮�ߴ���,,,
		show("DFS result", m);
		return n;
	}

	public int DFS(int[][] m, Coordinate p, int seq) {
///////////////////////////////////////////////////////////////////
/// 1) ���⿡ DFS �ڵ带 �ϼ��Ͻÿ�.
		m[p.x][p.y] = seq; // ������ m�� ���

		HashSet<Coordinate> adjacent = adjacent(p, m);
		for (Coordinate v : adjacent) {
			if (m[v.x][v.y] == 0) {// ���� �湮���� ���� ���̶��,
				seq = DFS(m, v, seq+1); //seq ++�� ���� �����Ǿ�� ��,,,
			}
		}
///////////////////////////////////////////////////////////////////
		return seq;
	}
	
	private HashSet<Coordinate> getCandidate(Coordinate p) {
		HashSet<Coordinate> retSet = new HashSet<Coordinate>();
		if (p.x - 1 >= 0)
			retSet.add(createNewState(p, p.x - 1, p.y));
		if (p.x + 1 < size)
			retSet.add(createNewState(p, p.x + 1, p.y));
		if (p.y - 1 >= 0)
			retSet.add(createNewState(p, p.x, p.y - 1));
		if (p.y + 1 < size)
			retSet.add(createNewState(p, p.x, p.y + 1));
		return retSet;
	}

	private Coordinate createNewState(Coordinate p, int i, int y) {
		Coordinate newCoordinate = new Coordinate(i, y);
		return newCoordinate;
	}

	public void BFS(int[][] in) {
		int[][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x, start.y);

		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		int seq = 1;

		while (Q.peek() != null) {
/// 2) ���⿡ BFS �ڵ带 �ϼ��Ͻÿ�.
			Coordinate v =  Q.poll();
			HashSet<Coordinate> adjacent = adjacent(v, m);
			m[v.x][v.y] = seq;
			seq++;
			for(Coordinate c : adjacent) {
				if(m[c.x][c.y] == 0) { //�湮���� �ʾҴٸ�,,,
					m[c.x][c.y] += 1;
					Q.add(c);
				}
			}
///////////////////////////////////////////////////////////////////
		}
		show("BFS result", m);
	}

	public void Dijkstra(int[][] in) {
		int[][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x, start.y);
		int seq = 1;
/// 3) ���⿡ Dijkstra �ڵ带 �ϼ��Ͻÿ�.
		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		
		while (!Q.isEmpty()) {
			Coordinate v = Q.poll(); //��� 
			HashSet<Coordinate> adjacent = adjacent(v, m);
			m[v.x][v.y] = seq;
			for(Coordinate c : adjacent) {
				if(m[c.x][c.y] == 0) { //�湮���� �ʾҴٸ�,,,
					if(m[c.x][c.y] < m[v.x][v.y]+1)
						m[c.x][c.y] = m[v.x][v.y]+1;
					Q.add(c);
				}
			}
		}
///////////////////////////////////////////////////////////////////

		show("Dijkstra result", m);
	}

	public void AStar(int[][] in) {
		int[][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x, start.y);
		int seq = 1;
///////////////////////////////////////////////////////////////////
/// 4) ���⿡ A* �ڵ带 �ϼ��Ͻÿ�.
		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		
		while (!Q.isEmpty()) {
			Coordinate v = Q.poll(); //��� 
			HashSet<Coordinate> adjacent = adjacent(v, m);
			m[v.x][v.y] = seq;
			if(v.equals(destin)) {
				show("Dijkstra + A* result", m);
				return;
			}
			seq++;
			for(Coordinate c : adjacent) {
				if(m[c.x][c.y] == 0) { //�湮���� �ʾҴٸ�,,,
					if(m[c.x][c.y]+ calcHVal(c) < m[v.x][v.y]+1+calcHVal(v))
						m[c.x][c.y] = m[v.x][v.y]+1;
					Q.add(c);
				}
			}
		}
///////////////////////////////////////////////////////////////////
		show("Dijkstra + A* result", m);
	}

	private HashSet<Coordinate> adjacent(Coordinate u, int[][] maze) { // can filter 1-boundary condition, 2-not the
																		// wall(-1)
		HashSet<Coordinate> retSet = new HashSet<>();
		if (u.x - 1 >= 0 && maze[u.x - 1][u.y] != -1)
			retSet.add(new Coordinate(u.x - 1, u.y));
		if (u.x + 1 < size && maze[u.x + 1][u.y] != -1)
			retSet.add(new Coordinate(u.x + 1, u.y));
		if (u.y - 1 >= 0 && maze[u.x][u.y - 1] != -1)
			retSet.add(new Coordinate(u.x, u.y - 1));
		if (u.y + 1 < size && maze[u.x][u.y + 1] != -1)
			retSet.add(new Coordinate(u.x, u.y + 1));
		return retSet;
	}

	private int calcHVal(Coordinate c) {
		double temp = Math.sqrt((destin.x - c.x) * (destin.x - c.x) + (destin.y - c.y) * (destin.y - c.y));
		return (int) temp;
	}

	private void show(String s, int[][] m) {
		System.out.println("\n [ " + s + " ]");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.printf("%3d", m[i][j]);
			}
			System.out.println();
		}
	}

	private int[][] deepCopy(int[][] m) {
		int[][] ret = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				ret[i][j] = m[i][j];
		return ret;
	}

	public static void main(String[] args) {
		int[][] input = { { 0, -1, 0, 0, 0, 0, 0, -1, 0, -1 }, { 0, -1, 0, -1, -1, -1, 0, -1, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 }, { -1, 0, -1, 0, -1, 0, -1, -1, 0, 0 },
				{ -1, 0, -1, 0, -1, 0, 0, -1, -1, 0 }, { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, -1, 0, 0, -1, -1, 0, -1, -1 }, { 0, -1, -1, 0, 0, 0, 0, 0, 0, -1 },
				{ 0, -1, 0, 0, -1, 0, 0, -1, 0, 0 }, { 0, 0, -1, 0, 0, 0, -1, -1, 0, 0 } };

		int size = input.length;

		Maze_60190911_��â�� me = new Maze_60190911_��â��(size);
		System.out.println(me.DFS(input));
		me.BFS(input);
		me.Dijkstra(input);
		me.AStar(input);
	}

}
