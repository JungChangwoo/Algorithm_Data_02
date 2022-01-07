package week9HW;

public class GraphBaseMain {

	public static void main(String[] args) {
		int maxNoVertex = 12;
		String [] vertices = { "seoul", "busan", "jeju", "incheon", "ulsan", "daegu", "daejeon", "kwangju"};
		int [][] graphEdges = { {1,2}, {1,3}, {2,3}, {2,7}, 
				                {4,5}, {4,3}, {5,1}, {5,6},
				                {7,4}, {7,8}};
		GraphInMatrix myGM = new GraphInMatrix(maxNoVertex);

		myGM.createGraph("TestGraph in Matrix");
		myGM.showGraph();

		for (int i = 0; i<graphEdges.length; i++)
			myGM.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
		myGM.showGraph();
		
		myGM.deleteVertex("busan");
		myGM.showGraph();
		
		myGM.insertEdge("seoul", "busan");
		myGM.insertEdge("busan", "jeju");
		myGM.showGraph();

		
		System.out.println("\nAdjacent Set of "+"seoul");
		System.out.println(myGM.adjacent("seoul"));
		
		myGM.BFS(vertices[0]);
		myGM.DFS(vertices[0]);	
		
	///////////////////////////////////////////////////////////////////
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		GraphInList myGL = new GraphInList(maxNoVertex);

		myGL.createGraph("TestGraph in List");
		myGL.showGraph();
		
		for (int i = 0; i<graphEdges.length; i++)
			myGL.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
		myGL.showGraph();
		
		myGL.deleteVertex("busan");
		myGL.showGraph();
		
		myGL.insertEdge("seoul", "busan");
		myGL.insertEdge("busan", "jeju");
		myGL.showGraph();

		
		System.out.println("\nAdjacent Set of "+"seoul");
		System.out.println(myGL.adjacent("seoul"));
		
		myGL.BFS(vertices[0]);
		myGL.DFS(vertices[0]);

		///////////////////////////////////////////////////////////////////
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		GraphInArray myGA = new GraphInArray(maxNoVertex);

		myGA.createGraph("TestGraph in List");
		myGA.showGraph();
		
		for (int i = 0; i<graphEdges.length; i++)
			myGA.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
		myGA.showGraph();
		
		myGA.deleteVertex("busan");
		myGA.showGraph();
		
		myGA.insertEdge("seoul", "busan");
		myGA.insertEdge("busan", "jeju");
		myGA.showGraph();

		
		System.out.println("\nAdjacent Set of "+"seoul");
		System.out.println(myGA.adjacent("seoul"));
		
		myGA.BFS(vertices[0]);
		myGA.DFS(vertices[0]);

	}

}

