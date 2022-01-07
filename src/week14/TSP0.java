package week14;

import java.util.Arrays;

public class TSP0 { 
	int [][] adjacentMatrix;
	int min = 999;
	
	public TSP0(int [][] in) {
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
				int distance = calculationDistance(path); 
				System.out.print("Path found ");
				for(int j=0; j<adjacentMatrix.length; j++) {
					System.out.print("-"+path[j]);
				}
				System.out.print(" distance = "+distance);
				System.out.println();
				if(distance < min)
					min = distance;
				return;
			}
			// �湮���� ���� ���� �̵��ϸ�, �ڱ� �ڽ��� ����, 
			if(visited[i]==false && adjacentMatrix[index][i]!=0 && adjacentMatrix[index][i]!=999) {
				visited[i] = true;
				path[count] = i;
				count++;
				findAllPath(i, visited, path, end, count);
				// ������ ���� �߿��� �ϳ��� �����ϰ� ����  ���� ���� ��� �߿� ������ �� ������ ����� �����Ǵ� ���� ���� 
				visited[i] = false;
				count--;
				path[count] = -1;
			}
		}
		
	}
	private int calculationDistance(int[] path) {
		int ret = 0;;
		for(int i=0; i<path.length-1; i++)
			ret += adjacentMatrix[path[i]][path[i+1]];
		ret += adjacentMatrix[path[path.length-1]][path[0]]; // ���������� �ٽ� ó������ ���ƿ��� �� 
		return ret;
	}
	public static void main(String[] args) {
		int [][] input = { {0, 10, 10, 30, 25}, {10, 0, 14, 21, 10},
				{10, 18, 0, 7, 9}, {8, 11, 7, 0, 3}, {14, 10, 10, 3, 0} };
		TSP0 me = new TSP0(input);
		me.findAllPath();
		System.out.println("min : "+me.min);
	}

}
