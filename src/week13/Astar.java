package week13;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Astar {
	
	class State{ // 3x3�迭�� �ϳ��� Node
		int [][] matrix;
		int mCount ; // h(x)
		int fVal;	 // g(x) + h(x)
		int emptyX, emptyY; //0�� �ִ� ��ġ 
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
	
	LinkedList<State> Q ;  // State���� LinkedList 
	State current;
	ArrayList<int[][]> prev; // ������ ���Դ� Matrix�� �������ִ� ����
	int [][] target;
	int size;				 	
	
	public Astar(int [][] a, int [][] b) {
		current = new State(a);		//��߳��
		target = b;					//��ǥ���
		prev = new ArrayList<>();	
		Q = new LinkedList<>();	
		size = target.length;	
	}
	
	public void ShortestPath() {
		Q.add(current);	//�켱 add 

		while(!Q.isEmpty()) {
			State u = Q.get(0);  //�� �տ� �Ÿ� ���� 
			Q.remove(0);		 //���� ���� Remove
			u.fVal=u.mCount+u.diffCount(target); //h(x) + g(x)
			u.showMatrix(); 
			System.out.println(" F-value = "+u.fVal+" ="+u.mCount+" + "+u.diffCount(target));
			if (sameMatrix(u.matrix,target)) // Ÿ���ϰ� �Ȱ����� return ����! 
				return;
			prev.add(u.matrix); //���� �ŷ� �Ѿ�� ���� ������ ���� Prev�� �־���

			HashSet<State> adjacent = getCandidate(u); //�̵��� �� �ִ� State���� List
			int min = 999;

			for (State v : adjacent) {  // �ĺ����� State���� �ϳ��� Loop
				if (!visited(prev, v.matrix)) { // ������ �湮�ߴ� ���� �ƴ϶��,,,,,
					v.fVal=v.mCount+v.diffCount(target); //mCount = h(x)
					if (v.fVal<=min) {					 	// ���� min���� ū �͸� �����ϰ�,
						min = v.fVal;
						for(int i=0;i<Q.size();i++) {       // �켱���� ť�� heap ��� �����ϰ� LinkedList����
							if(Q.get(i).fVal>v.fVal) {      // ���ο� min���� ū ���� remove��Ű�� => �ڿ��� ���� ���� ���ܵ�
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

	private HashSet<State> getCandidate(State u) { //���ǿ� �����ϴ� State���� �ĺ��ڵ��� List�� add
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