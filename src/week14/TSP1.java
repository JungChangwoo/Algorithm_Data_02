package week14;

import java.util.HashSet;

public class TSP1 {
	int [][] adjacentMatrix;
	int nV; // 정점의 갯수
	
	public TSP1(int [][] in) {
		adjacentMatrix = in;
		nV = adjacentMatrix.length;
	}
	public int minDistance(int start) {
		HashSet<Integer> thruSet = new HashSet<>(); //경로 Set
		for(int i=0; i<nV; i++) {
			thruSet.add(i);
		}
		thruSet.remove(start);
		return minDistance(start, thruSet, start);
	}
	private int minDistance(int index, HashSet<Integer> thruSet, int end) {
		if(thruSet.size() == 0) //다 경유했다면,,
			return adjacentMatrix[index][end];
		
		int min = 999;
		for(int i : thruSet) {
			HashSet<Integer> next = reduce(thruSet, i); // 맨 앞은 제외한 경로Set
			if(adjacentMatrix[index][i] != 999) { //경로가 있다면,, 
				//맨 앞까지의 거리와 나머지에서 가장 작은 Min >> 이것은 반복,,마지막에 도달할 때까지 
				int tempDist = adjacentMatrix[index][i] + minDistance(i, next, end); 
				if(tempDist < min)
					min = tempDist;
			}
		}
		return min; 
	}
	private HashSet<Integer> reduce(HashSet<Integer> thruSet, int i) {
		HashSet<Integer> result = new HashSet<Integer>();
		for(int k : thruSet) result.add(k);
		result.remove(i);
		return result;
	}
	
	public static void main(String[] args) {
		int [][] input = { {0, 10, 10, 30, 25}, {10, 0, 14, 21, 10},
				{10, 18, 0, 7, 9}, {8, 11, 7, 0, 3}, {14, 10, 10, 3, 0} };
		TSP1 me = new TSP1(input);
		System.out.println(me.minDistance(0));
	}

}
