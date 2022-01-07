package week14;

import java.util.Arrays;

public class TSP0 { 
	int [][] adjacentMatrix;
	int min = 999;
	
	public TSP0(int [][] in) {
		adjacentMatrix = in;
	}
	public void findAllPath() {
		boolean [] visited = new boolean[adjacentMatrix.length]; // 현재까지 방문된 노드를 구분
		int [] path = new int[adjacentMatrix.length]; // 현재까지 지나온 경로
		int start = 0; // 시작점
		int vCount = 0; // 현재까지 선택된 노드의 갯수
		Arrays.fill(visited, false);
		Arrays.fill(path, -1);
		
		visited[start] = true;
		vCount++;
		path[0] = start;
		findAllPath(start, visited, path, start, vCount); // start : 마지막을 알려줌
	}

	private void findAllPath(int index, boolean[] visited, int[] path, int end, int count) {
		for(int i=0; i<adjacentMatrix.length; i++) { // 각 노드에서 인접한 노드들의 수만큼 Loop 
			if(i==end && count==adjacentMatrix.length) { // 다시 시작점으로 돌아왔을 때 
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
			// 방문되지 않은 노드로 이동하며, 자기 자신은 제외, 
			if(visited[i]==false && adjacentMatrix[index][i]!=0 && adjacentMatrix[index][i]!=999) {
				visited[i] = true;
				path[count] = i;
				count++;
				findAllPath(i, visited, path, end, count);
				// 인접한 노드들 중에서 하나를 수행하고 나서  다음 인접 노드 중에 선택할 때 이전의 결과가 오염되는 것을 방지 
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
		ret += adjacentMatrix[path[path.length-1]][path[0]]; // 마지막에서 다시 처음으로 돌아오는 값 
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
