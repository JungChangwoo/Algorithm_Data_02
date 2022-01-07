 package week10;

public class MSTmain {

	public static void main(String[] args) {
		String [] vertices = { "����", "��õ", "����", "�뱸", "����", "�λ�", "���"};
		int [][] graphEdges = { {0, 1, 11 }, {0, 2, 8}, {0, 3, 9}, {1, 3, 13}, 
				      {1, 6, 8}, {2, 4, 10}, {3, 4, 5}, {3, 5, 12}, {5, 6, 7} };

		Prim myG = new Prim(vertices.length);

		myG.createGraph("Prim-Test Graph");
		for (int i = 0; i<graphEdges.length; i++)
			myG.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		myG.showGraph();
		
		myG.deleteVertex("����");
		myG.showGraph();
		 
		myG.insertEdge("����", "��õ", 11);
		myG.insertEdge("����", "����", 8);
		myG.insertEdge("����", "�뱸", 9);
		myG.showGraph();

		System.out.println("\nAdjacent Set of "+"����");
		System.out.println(myG.adjacent("����"));
		
		System.out.println("\nPrim Algorithm starts from "+"����");

		myG.init("����");
		myG.MST();
		/////////////////////////////////////////////////////
		PrimWithHeap myG2 = new PrimWithHeap(vertices.length);

		myG2.createGraph("PrimWithHeap-Test Graph");
		for (int i = 0; i<graphEdges.length; i++)
			myG2.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		myG2.showGraph();
		
		myG2.deleteVertex("����");
		myG2.showGraph();
		 
		myG2.insertEdge("����", "��õ", 11);
		myG2.insertEdge("����", "����", 8);
		myG2.insertEdge("����", "�뱸", 9);
		myG2.showGraph();

		System.out.println("\nAdjacent Set of "+"����");
		System.out.println(myG2.adjacent("����"));
		
		System.out.println("\nPrimWithHeap Algorithm starts from "+"����");

		myG2.init("����");
		myG2.MST();
		
		Kruscal myK = new Kruscal(vertices.length);
		myK.createGraph("Kruscal-Test Graph");
		for (int i = 0; i<graphEdges.length; i++)
			myK.insertEdge(vertices[graphEdges[i][0]],vertices[graphEdges[i][1]], graphEdges[i][2]);
		myK.showGraph();
		
		System.out.println("\nKruscal Algorithm");

		myK.init();
		myK.MST();

	}

}
