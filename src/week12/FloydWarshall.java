package week12;
public class FloydWarshall extends GraphInMatrixWD {
	int [][] d  ;   // distance; 

	public FloydWarshall(int max) {
		super(max);
	}
	
	public void init(String start) {
		d = new int [maxNumber][maxNumber];
		setInitAdjacentMatrix();
	}
	
	private void setInitAdjacentMatrix() {
		for(int i=0; i<maxNumber; i++) {
			for(int j=0; j<maxNumber; j++) {
				if(adjacentMatrix[i][j] == 0 && i!=j) {
					adjacentMatrix[i][j] = 99;
				}
			}
		}
	}
	
	public void ShortestPath() {
		// 그래프 초기화
		for(int i=0; i<maxNumber; i++) {
			for(int j=0; j<maxNumber; j++) {
				d[i][j] = adjacentMatrix[i][j];
			}
		}
		
		for(int k=0; k<maxNumber; k++) {
			for(int i=0; i<maxNumber; i++) {
				for(int j=0; j<maxNumber; j++) {
					if(d[i][k] + d[k][j] < d[i][j]) {
						d[i][j] = d[i][k] + d[k][j];
					}
				}
			}
		}
		// 결과 출력
		showFloydWarshallGraph();

	}

	private void showFloydWarshallGraph() {
		System.out.println("\n< "+graphName+" in AdjacentMatrix >");
		for(int i=0; i<maxNumber; i++) {
			System.out.print(vertices.get(i)+"  ");
			for(int j=0; j<maxNumber; j++) {
				System.out.printf("%3d", d[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		String [] vertices = { "서울", "인천", "대전", "대구", "광주", "부산", "울산"};
		int [][] graphEdges = { {0, 1, 11 }, {0, 2, 2}, {0, 3, 9}, {1, 3, 13}, 
				      {1, 6, 8}, {2, 4, 1}, {3, 4, 5}, {3, 5, 2}, {5, 6, 7} };

		FloydWarshall myG = new FloydWarshall(vertices.length);

		myG.createGraph("Floyd-Warshall Graph");
		for (int i = 0; i<graphEdges.length; i++)
			myG.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		myG.showGraph();
		
		System.out.println("\nFloyd-Warshall Algorithm starts from "+"서울");

		myG.init("서울");
		myG.ShortestPath();

	}

}
