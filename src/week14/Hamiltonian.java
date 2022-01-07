package week14;

import java.util.Arrays;

public class Hamiltonian { 
	int [][] adjacentMatrix;
	
	public Hamiltonian(int [][] in) {
		adjacentMatrix = in;
	}
	public void findAllPath() {
		boolean [] visited = new boolean[adjacentMatrix.length]; // ������� �湮�� ��带 ����
		int [] path = new int[adjacentMatrix.length]; // ������� ������ ���
		int start = 0; // ������
		int vCount = 0; // ������� ���õ� ����� ����
		Arrays.fill(visited, false);
		Arrays.fill(path, -1);
		
		visited[start] = true;
		vCount++;
		path[0] = start;
		findAllPath(start, visited, path, start, vCount); // start : �������� �˷���
	}

	private void findAllPath(int index, boolean[] visited, int[] path, int end, int count) {
		for(int i=0; i<adjacentMatrix.length; i++) { // �� ��忡�� ������ ������ ����ŭ Loop 
			if(i==end && count==adjacentMatrix.length) { // �ٽ� ���������� ���ƿ��� �� 
				System.out.print("Path found ");
				for(int j=0; j<adjacentMatrix.length; j++) {
					System.out.print("-"+path[j]);
				}
				System.out.println();
				return;
			}
			// �湮���� ���� ���� �̵��ϸ�, �ڱ� �ڽ��̰ų� ��ΰ� ���� ���� ���� 
			if(visited[i]==false && adjacentMatrix[index][i]!=0 && adjacentMatrix[index][i]!=999) {
				visited[i] = true;
				path[count] = i;
				count++;
				findAllPath(i, visited, path, end, count); //DFS�� ���,,, 
				// ������ ���� �߿��� �ϳ��� �����ϰ� ����  ���� ���� ��� �߿� ������ �� ������ ����� �����Ǵ� ���� ���� 
				visited[i] = false;
				count--;
				path[count] = -1;
			}
		}
		
	}
	public static void main(String[] args) {
		int [][] input = { {0, 10, 10, 30, 25}, {10, 0, 14, 21, 10},
				{10, 18, 0, 7, 9}, {8, 11, 7, 0, 3}, {14, 10, 10, 3, 0} };
		Hamiltonian me = new Hamiltonian(input);
		me.findAllPath();
	}

}
