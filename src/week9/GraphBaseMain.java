package week9;

public class GraphBaseMain {

	public static void main(String[] args) {
		int maxNoVertex = 10;
		String [] vertices = { "Ã¶¼ö", "¿µÈñ", "µ¿°Ç", "ÁØÈ£", "Àç»ó", "½Â¿ì"};
		int [][] graphEdges = { {1,2}, {1,3}, {1,4}, {2,3}, 
				                {3,5}, {1,6}, {5,6}, {4,6} };
		GraphInMatrix myGM = new GraphInMatrix(maxNoVertex);

		myGM.createGraph("TestGraph in Matrix");
		myGM.showGraph();

		for (int i = 0; i<graphEdges.length; i++)
			myGM.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
		myGM.showGraph();
		
		myGM.deleteVertex("¿µÈñ");
		myGM.showGraph();
		
		myGM.insertEdge("Ã¶¼ö", "¿µÈñ");
		myGM.insertEdge("¿µÈñ", "µ¿°Ç");
		myGM.showGraph();

		
		System.out.println("\nAdjacent Set of "+"Ã¶¼ö");
		System.out.println(myGM.adjacent("Ã¶¼ö"));
		
		myGM.BFS(vertices[0]);
		myGM.DFS(vertices[0]);	
		
	///////////////////////////////////////////////////////////////////
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		GraphInList myGL = new GraphInList(maxNoVertex);

		myGL.createGraph("TestGraph in Array");
		myGL.showGraph();
		
		for (int i = 0; i<graphEdges.length; i++)
			myGL.insertEdge(vertices[graphEdges[i][0]-1],vertices[graphEdges[i][1]-1]);
		myGL.showGraph();
		
		myGL.deleteVertex("¿µÈñ");
		myGL.showGraph();
		
		myGL.insertEdge("Ã¶¼ö", "¿µÈñ");
		myGL.insertEdge("¿µÈñ", "µ¿°Ç");
		myGL.showGraph();

		
		System.out.println("\nAdjacent Set of "+"Ã¶¼ö");
		System.out.println(myGL.adjacent("Ã¶¼ö"));
		
		myGL.BFS(vertices[0]);
		myGL.DFS(vertices[0]);

	}

}

