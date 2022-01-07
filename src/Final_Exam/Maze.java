package Final_Exam;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

public class Maze {
	public class Coordinate{
		int x, y;
		public Coordinate(int i, int j) {
			x=i; y=j;
		}
		public boolean equals(Coordinate other) {
			return (this.x==other.x)&&(this.y==other.y); 
		}
		public void copyFrom(Coordinate other) {
			this.x = other.x;
			this.y = other.y;
		}
		public String toString() {
			return "("+x+","+y+")";
		}		
	}
	int size ;
	Coordinate start, destin;
	public Maze(int n) {
		size = n;;
		start = new Coordinate(0,0);
		destin = new Coordinate(size-1,size-1);
	}
	
	public int BFS(int [][] in) {
		int [][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x,start.y);
		
		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		int seq = 1;

		while(Q.peek()!=null) {
			Coordinate r = Q.poll();
			if (m[r.x][r.y]==0) {
				m[r.x][r.y] = seq++;

				HashSet<Coordinate> candid = new HashSet<>();
				candid = adjacent(r, m);

				for (Coordinate c : candid)
					if (m[c.x][c.y]==0)
						Q.add(c);
			}
		}
		show("BFS result", m);
		
		return seq;
	}

	public void Dijkstra(int [][] in) {
		int [][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x,start.y);
		int seq = 1;
		m[p.x][p.y] =seq;
		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		int d=1;  // distance = 1 for all cases
		
		while(Q.peek()!=null) {
			Coordinate r = Q.poll();
			seq = m[r.x][r.y]+1;

			HashSet<Coordinate> candid = new HashSet<>();
			candid = adjacent(r, m);

			for (Coordinate c : candid) {
				if (m[c.x][c.y]==0 || m[c.x][c.y]>seq) {
					m[c.x][c.y] = seq;
					Q.add(c);
					}
			}
		}
		show("Dijkstra result", m);
	}

	public void AStar(int [][] in) {
		int [][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x,start.y);
		int seq = 1;
		m[p.x][p.y] =seq;
		Deque<Coordinate> Q = new ArrayDeque<>();
		Q.add(p);
		int d=1;  // distance = 1 for all cases
		
		while(Q.peek()!=null) {
			Coordinate r = Q.poll();
			seq = m[r.x][r.y]+1;

			HashSet<Coordinate> candid = new HashSet<>();
			candid = adjacent(r, m);
			// destination 까지의 직선 거리가 가장 가까운 것 하나만 선택
			int hVal=99999;
			Coordinate best = new Coordinate(0,0);
			for (Coordinate c : candid) {
				int temp=calcHVal(c);
				if (temp<hVal) {
					hVal=temp;
					best.copyFrom(c);
				}
			}
			if (m[best.x][best.y]==0 || m[best.x][best.y]>seq) {
				m[best.x][best.y] = seq;
				Q.add(best);
			}

		}
		show("Dijkstra + A* result", m);
	}

	private int calcHVal(Coordinate c) {
		double temp = Math.sqrt((destin.x-c.x)*(destin.x-c.x)+(destin.y-c.y)*(destin.y-c.y));
		return (int)temp;
	}

	public int DFS(int [][] in) {
		int [][] m = deepCopy(in);
		Coordinate p = new Coordinate(start.x,start.y);

		int n = DFS(m, p, 1); // 1 means sequence :  [0,0]=1
		show("DFS result", m);
		return n;
	}
	
	public int DFS(int [][] m, Coordinate p, int seq) {
		m[p.x][p.y] = seq++;
		HashSet<Coordinate> candid = new HashSet<>();
		candid = adjacent(p, m);
		
		for (Coordinate c : candid) {
			if (m[c.x][c.y]==0)
				seq=DFS(m,c,seq);
		}
		return seq;
	}
	
	private HashSet<Coordinate> adjacent(Coordinate u, int [][] maze) {  // can filter 1-boundary condition, 2-not the wall(-1)
		HashSet<Coordinate> retSet = new HashSet<>();
		if (u.x-1>=0 && maze[u.x-1][u.y]!=-1) retSet.add(new Coordinate(u.x-1, u.y));
		if (u.x+1<size && maze[u.x+1][u.y]!=-1) retSet.add(new Coordinate(u.x+1, u.y));
		if (u.y-1>=0 && maze[u.x][u.y-1]!=-1) retSet.add(new Coordinate(u.x, u.y-1));
		if (u.y+1<size && maze[u.x][u.y+1]!=-1) retSet.add(new Coordinate(u.x, u.y+1));		
		return retSet;
	}
	
	private void show(String s, int [][] m) {
		System.out.println("\n [ "+s+" ]");
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				System.out.printf("%3d",m[i][j]);
			}
			System.out.println();
		}
	}
	
	private int[][] deepCopy(int[][] m) {
		int [][] ret = new int[size][size];
		for (int i=0;i<size;i++)
			for (int j=0;j<size;j++)
				ret[i][j]=m[i][j];
		return ret;
	}

	public static void main(String[] args) {
		int [][] input = { {0,-1,0,0,0,0,0,-1,0,-1},
		           		   {0,-1,0,-1,-1,-1,0,-1,0,0},
		                   {0,0,0,0,0,0,0,0,0,-1},
		                   {-1,0,-1,0,-1,0,-1,-1,0,0},
		                   {-1,0,-1,0,-1,0,0,-1,-1,0},
		                   {-1,0,0,0,0,0,0,0,0,0},
		                   {0,0,-1,0,0,-1,-1,0,-1,-1},
		                   {0,-1,-1,0,0,0,0,0,0,-1},
		                   {0,-1,0,0,-1,0,0,-1,0,0},
		                   {0,0,-1,0,0,0,-1,-1,0,0} };
		int size  = input.length;;
	
		Maze  me = new Maze(size);
		me.DFS(input);
		me.BFS(input);
		me.Dijkstra(input);
		me.AStar(input);

	}

}