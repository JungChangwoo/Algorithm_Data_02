package week10;

import java.util.ArrayDeque;
import java.util.Deque;

public class HeapTree {
	
	Node heap;  // root
	Node last;  // last

	public HeapTree() {
		heap = null;
		last = null;
	}

//	public void heapSort(char[] input) {
//		buildHeap(input);
//		preOrderTraverse(heap);
//		System.out.println();
//		inOrderTraverse(heap);
//		System.out.println();
//		postOrderTraverse(heap);
//		System.out.println();                                                                                                                            
//		sortOut();
//	}
	
	private void sortOut() {
		System.out.println("< Max.Heap >");
		
		while(heap != null) {
			System.out.println(deleteHeap()+"  "+toString());
		}
	}

	public Integer deleteHeap() {
		if(heap == null) {
			return null;
		}
		int retVal = heap.vertexNum;
		
		if(heap == last) {
			heap = null;
			last = null;
		}
		else {
			heap.vertexNum = last.vertexNum; //마지막노드를 루트로
			Node prev = getPrev(last); 		 //마지막노드가 왼쪽이였는지,,,오른쪽이였는지,,등
			
			//마지막 Node를 삭제
			if(last == last.parent.left) {
				last.parent.left = null;
			}
			else {
				last.parent.right = null;
			}
			last = prev;					 //last가 prev를 가리키도록
			
			heapifyDownward(heap);			 //내려가면서 조건을 충족하는지 
		}
		
		return retVal;
	}

	private Node getPrev(Node node) {
		if(node == null || node == heap) {
			return node;
		}
		// right가 last일때
		if(node.parent.right != null) {
			return node.parent.left;
		}
		// last가 left일때, 2가지 케이스 고려
		Node p = node;
		while(p.parent != null && p == p.parent.left) {
			p = p.parent;
		}
		// 만약 last가 왼쪽 끝이 아니라면
		if(p.parent != null) {
			p = p.parent.left;
		}
		while(p.right != null) {
			p = p.right;
		}
		return p;
	}
	//부모가 자식보다 작은 minHeap이 조건
	private void heapifyDownward(Node node) {
		if (node == null || node.left == null) {
			return;
		}
		// left인지 right 중 어떤 것이 더 작은지 모르기 때문에,,,
		Node smaller = node.left;
		if(node.right != null && node.right.value < node.left.value) {
			smaller = node.right;
		}
		if(smaller.value > node.value) { //자식이 부모보다 크다면,,, 바꾸고 다시 Recursion
			swap(smaller, node);
			heapifyDownward(smaller);
		}
		
	}
	// 부모가 자식보다 작은 minHeap이 조건
	private void heapifyUpward(Node node) {
		// 자식노드가 없는데 자식노드로 내려왔거나 index가 0인 즉, null인 부분에 도달하면 return
		if(node == null || node.left == null) {
			return;
		}
		// left right 중 누가 더 큰지 모르기 때문에 우선 left로 정함
		Node smaller = node.left;
		// right 자식이 있고 right가 부모보다 크다면, 교환해줄 자식을 right로 설정
		if (node.right != null && node.right.value < node.left.value) {
			smaller = node.right;
		}
		// 자식이 부모보다 크다면, 교환
		if (smaller.value > node.value) {
			swap(smaller, node);
			heapifyUpward(node.parent);
		}

	}
//
//	private void buildHeap(char[] input) {
//		System.out.println("<< Heap implemented in Linked-Tree >>");
//		for (int i = 0; i < input.length; i++) {
//			insertHeap(input[i]);
//		}
//	}

	public void insertHeap(int vn, int v) {
		// 처음이라면
		if(heap == null) {
			heap = new Node(vn, v, null, null, null);
			last = heap;
			return;
		}
		// 어디에 넣어줘야 할지 알기 위해(현재 부모에 넣어야 할지 다음 부모에 넣어야 할지
		Node pNext = getParentOfNext(last);
		// 새로운 노드 만들고 부모는 pNext
		last = new Node(vn, v, null, null, pNext);
		// left가 없다면 last를 left에
		if(pNext.left == null) {
			pNext.left = last;
		}
		// right가 없다면 last를 right에
		else {
			pNext.right = last;
		}
		// 넣고나서 정렬 
		heapifyUpward(last.parent);
		System.out.println(toString());
	}

	private Node getParentOfNext(Node node) {
		if(node == null || node == heap) {
			return node;
		}
		// left가 last일때
		if(node.parent.right == null) {
			return node.parent;
		}
		// last가 right일때, 2가지 케이스 고려
		Node p = node;
		while(p.parent != null && p == p.parent.right) {
			p = p.parent;
		}
		// 만약 last가 오른쪽 끝이 아니라면
		if(p.parent != null) {
			p = p.parent.right;
		}
		while(p.left != null) {
			p = p.left;
		}
		return p;
	}

	private void swap(Node a, Node b) {
		int temp = a.vertexNum;
		a.vertexNum = b.vertexNum;
		b.vertexNum = temp;
		temp = a.value;
		a.value = b.value;
		b.value = temp;
	}
	
	public String toString() {
		if(heap == null) {
			return null;
		}
		Deque<Node> q = new ArrayDeque<Node>();
		q.add(heap);
		return levelOrderTraverse(q, "");
	}
	
//	public String toString2() {
//		if(heap == null) {
//			return null;
//		}
//		Stack<Node> q = new Stack<Node>();
//		q.push(heap);
//		return levelOrderTraverseQ(q, "");
//	}
	
	private void preOrderTraverse(Node t) {
		if(t != null) {
			System.out.print(t.vertexNum+" "+t.value);
			preOrderTraverse(t.left);
			preOrderTraverse(t.right);
		}
	}
	
	private void inOrderTraverse(Node t) {
		if(t != null) {
			inOrderTraverse(t.left);
			System.out.print(t.vertexNum+" "+t.value);
			inOrderTraverse(t.right);
		}
	}
	
	private void postOrderTraverse(Node t) {
		if(t != null) {
			postOrderTraverse(t.left);
			postOrderTraverse(t.right);
			System.out.print(t.vertexNum+" "+t.value);
		}
	}
	
	// Queue 버전
	private String levelOrderTraverse(Deque<Node> q, String retString) {
		Node node = q.poll();
		if(node == null) {
			return retString;
		}
		retString = retString + "  "+node.vertexNum+ " "+node.value;
		if(node.left != null) {
			q.add(node.left);
			if(node.right != null) {
				q.add(node.right);
			}
		}
		return levelOrderTraverse(q, retString);
	}
	
//	// Stack 버전
//	private String levelOrderTraverseQ(Stack<Node> s, String retString) {
//		Node node = s.pop();
//		if(node == null) {
//			return retString;
//		}
//		retString = retString + "  "+node.key;
//		if(node.left != null) {
//			s.push(node.left);
//			retString = retString + "  "+node.key;
//			if(node.right != null) {
//				s.push(node.right);
//			}
//		}
//		return levelOrderTraverseQ(s, retString);
//		
//	}
//
//	public static void main(String[] args) {
//		char[] data = { 'M', 'Y', 'U', 'N', 'G', 'I', 'S', 'W' };
//
//		HeapTree h = new HeapTree();
//
//		h.heapSort(data);
//	
//
//	}
	
	class Node {
		int vertexNum;
		int value;
		Node left, right, parent;
		
		public Node(int vn, int v, Node l, Node r, Node p) {
			vertexNum = vn;
			value = v;
			left = l;
			right = r;
			parent = p;
		}
		
		public String toString() {
			return ""+value;
		}
	}

}