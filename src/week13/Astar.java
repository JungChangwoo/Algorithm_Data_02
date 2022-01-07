package week13;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Astar {
	
	class State{ // 3x3배열로 하나의 Node
		int [][] matrix;
		int mCount ; // h(x)
		int fVal;	 // g(x) + h(x)
		int emptyX, emptyY; //0이 있는 위치 
		public State(int[][] input) {
			matrix = new int[input.length][input.length];
			for (int i=0;i<matrix.length;i++) 
				for (int j=0;j<matrix.length;j++){
					matrix[i][j]=input[i][j];
					if(matrix[i][j]==0) {
						emptyX=i;
						emptyY=j;
					}	
				}
			mCount =0;
		}
		
		public int diffCount(int[][] target) {
			int retVal = 0;
			for (int i=0;i<matrix.length;i++) 
				for (int j=0;j<matrix.length;j++){
					if(this.matrix[i][j]!=target[i][j])
						retVal++;
				}
			return retVal;
		}
		
		public void showMatrix() {
			System.out.println();
			for (int i=0;i<matrix.length;i++) {
				for (int j=0;j<matrix.length;j++){
					System.out.print(matrix[i][j]+"  ");
				}
				System.out.println();
			}
		}
	}
	
	LinkedList<State> Q ;  // State들의 LinkedList 
	State current;
	ArrayList<int[][]> prev; // 이전에 나왔던 Matrix를 저장해주는 변수
	int [][] target;
	int size;				 	
	
	public Astar(int [][] a, int [][] b) {
		current = new State(a);		//출발노드
		target = b;					//목표노드
		prev = new ArrayList<>();	
		Q = new LinkedList<>();	
		size = target.length;	
	}
	
	public void ShortestPath() {
		Q.add(current);	//우선 add 

		while(!Q.isEmpty()) {
			State u = Q.get(0);  //맨 앞에 거를 꺼냄 
			Q.remove(0);		 //꺼낸 것을 Remove
			u.fVal=u.mCount+u.diffCount(target); //h(x) + g(x)
			u.showMatrix(); 
			System.out.println(" F-value = "+u.fVal+" ="+u.mCount+" + "+u.diffCount(target));
			if (sameMatrix(u.matrix,target)) // 타겟하고 똑같으면 return 성공! 
				return;
			prev.add(u.matrix); //다음 거로 넘어가기 전에 현재의 값을 Prev로 넣어줌

			HashSet<State> adjacent = getCandidate(u); //이동할 수 있는 State들의 List
			int min = 999;

			for (State v : adjacent) {  // 후보자인 State들을 하나씩 Loop
				if (!visited(prev, v.matrix)) { // 이전에 방문했던 곳이 아니라면,,,,,
					v.fVal=v.mCount+v.diffCount(target); //mCount = h(x)
					if (v.fVal<=min) {					 	// 기존 min보다 큰 것만 배제하고,
						min = v.fVal;
						for(int i=0;i<Q.size();i++) {       // 우선순위 큐나 heap 대신 간단하게 LinkedList에서
							if(Q.get(i).fVal>v.fVal) {      // 새로운 min보다 큰 것을 remove시키고 => 자연히 같은 것은 남겨둠
								Q.remove(i);
							}
						}
						Q.add(v);
					}
				}
			}
		}
	}
	
	private boolean visited(ArrayList<int[][]> prev, int[][] matrix) {
		if (prev.size()==0)
			return false;
		
		else for (int k=0;k<prev.size();k++) 
			if (sameMatrix(prev.get(k), matrix))
						return true;
		return false;
	}
	
	private boolean sameMatrix(int[][] a, int [][] b) {
		for (int i=0;i<a.length;i++) 
			for (int j=0;j<a.length;j++)
				if (a[i][j]!=b[i][j])
					return false;
	return true;
	}

	private HashSet<State> getCandidate(State u) { //조건에 충족하는 State들을 후보자들의 List에 add
		HashSet<State> retSet = new HashSet<State>();
		if (u.emptyX-1>=0) retSet.add(createNewState(u, u.emptyX-1, u.emptyY)); 
		if (u.emptyX+1<size) retSet.add(createNewState(u, u.emptyX+1, u.emptyY));
		if (u.emptyY-1>=0) retSet.add(createNewState(u, u.emptyX, u.emptyY-1));
		if (u.emptyY+1<size) retSet.add(createNewState(u, u.emptyX, u.emptyY+1));		
		return retSet;
	}

	private State createNewState(State u, int i, int j) {
		State newState = new State(u.matrix);
		newState.matrix[u.emptyX][u.emptyY]=newState.matrix[i][j];
		newState.matrix[i][j]=0;
		newState.mCount=u.mCount+1;
		newState.emptyX=i;
		newState.emptyY=j;
		return newState;
	}

	
	public static void main(String[] args) {
			int [][] as_is = {{2,8,3},{1,6,4},{7,0,5}};
			int [][] to_be = {{1,2,3},{8,0,4},{7,6,5}};
			
			Astar me = new Astar(as_is, to_be);
			me.ShortestPath();

		}

}