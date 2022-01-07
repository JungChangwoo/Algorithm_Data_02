package week4BalancedTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BST {
	
	class Node {
		char key;
		Node parent;
		Node left;
		Node right;
		
		public Node(char c) {
			key = c;
			parent = null;
			left = null;
			right = null;
		}
		
		public String toString() {
			String retVal = "";
			return retVal + key + "(" + height(this) + ")";
		}
	}
	
	Node root;
	int numNode;
	
	public BST() {
		root = null;
		numNode = 0;
	}
	
	public void insert(char x) {
		insert(x, null, root); // null = parent of root // root=지금 검토하려는 트리의 시작점
	}
	protected Node insert(char x, Node parent, Node r) {
		if(r == null) { // root가 없는 경우와 마지막에 도달했을 경우
			if(parent == null) { // root가 없을 경우 
				root = insertNode(x, null);
				return root;
			}
			else { // leaf (마지막에 도달했을 경우
				if(x < parent.key) {
					parent.left = insertNode(x, parent);
					return parent.left;
				}
				else if (x > parent.key) {
					parent.right = insertNode(x, parent);
					return parent.right;
				}
				else { // never happen,,,
					return null;
				}
			}
		}
		else { // leaf에 도달 못 했다면, => 계속 타고 내려감
			if(x < r.key) {
				return insert(x, r, r.left);
			}
			else if(x > r.key){
				return insert(x, r, r.right);
			}
			else return null;
		}
	}
	
	private Node insertNode(char x, Node parent) {
		Node newNode = new Node(x); //새로운 노드를 만들어서
		newNode.parent = parent; //
		numNode++;
		return newNode;
	}

	public Node search(Node startNode, char x) {
		Node p = startNode;
		if(p == null || p.key == x) { // 못찾았거나 = null, 찾으면 return
			return p;
		}
		else if(x < p.key) {
			return search(p.left, x);
		}
		else {
			return search(p.right, x);
		}
	}
	
	public Node delete(char x) {
		Node r = search(root, x);
		if(r != null) {
			numNode--;
			return delete(r);
		}
		else {
			return null;
		}
	}
	
	private Node delete(Node r) {
		if (r.parent == null) { // r = root
			root = deleteNode(r);
			return null;
		}
		else if (r == r.parent.left) {
			r.parent.left = deleteNode(r);
			if(r.parent.left != null) {
				return r.parent.left;
			}
			return r.parent;
		}
		else {
			r.parent.right = deleteNode(r);
			if(r.parent.right != null) {
				return r.parent.right;
			}
			return r.parent;
		}
	}
	
	private Node deleteNode(Node r) {
		
		//case 1 : no child
		if(r.left == null && r.right == null) {
			return null;
		}
		//case 2 : 1 Child
		else if(r.left == null && r.right != null) {
			r.right.parent = r.parent;
			return r.right;
		}
		else if(r.left != null && r.right == null) {
			r.left.parent = r.parent;
			return r.left;
		}
		else { //case 3 : 2 Child
//			Node s = successor(r);
//			r.key = s.key;
//			if(s == s.parent.left) {
//				s.parent.left = s.right;
//			}
//			else {
//				s.parent.right = s.right;
//			}
//			return s.parent;
			Node p = predecessor(r);
			r.key = p.key;
			
			delete(p);
			
			return r;
		}
	}
	
	private Node successor(Node v) {
		if(v == null) {
			return null;
		}
		Node p = v.right;
		while(p.left != null) {
			p = p.left;
		}
		return p;
	}
	
	private Node predecessor(Node v) {
		if(v == null) {
			return null;
		}
		Node p = v.left;
		while(p.right != null) {
			p = p.right;
		}
		return p;
	}
	
	
	public void showTree() {
		if (root == null) {
			return;
		}
		Deque<Node> que = new ArrayDeque<Node>();
		que.add(root);
		int depthLevel = 0;
		while(que.peek() != null) {
			Deque<Node> temp = new ArrayDeque<Node>();
			System.out.print("Depth-level "+depthLevel+"  :  ");
			while (que.peek() != null) {
				temp.add(que.poll());
			}
			while(temp.peek() != null) {
				Node e = temp.poll();
				System.out.print(e.toString()+" ");
				if(e.left != null) {
					que.add(e.left);
				}
				if(e.right != null) {
					que.add(e.right);
				}
			}
			System.out.println();
			depthLevel++;
		}
	}
	
	private String toString(Node t) {
		return inorder(t);
	}

	private String inorder(Node t) {
		if(t == null) {
			return "";
		}
		else {
			return inorder(t.left)+" "+t.toString()+" "+inorder(t.right);
		}
	}
	
////////////////////////////////////////////////////////////////////////////
	public int height() {
		return height(root);
	}
	
	protected int height(Node r) {
		if (r == null) {
			return -1;
		}
		else {
			return 1+ Math.max(height(r.left), height(r.right));
		}
	}
	
	public int IPL() {
		int count = 0;
		return IPLCount(root, count);
	}
	
	private int IPLCount(Node r, int count) {
		if(r == null) {
			return count;
		}
		count++;
		int lCount = IPLCount(r.left, count);
		int rCount = IPLCount(r.right, count);
		return count + lCount + rCount;
	}

////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		char [] data = {'M', 'Y', 'U', 'N', 'G', 'I', 'S', 'W'};
		BST bt = new BST();
		
		for(int i=0; i<data.length; i++) {
			bt.insert(data[i]);
		}
		System.out.println("\nTree Created : ");
		bt.showTree(); 
		
		bt.delete('S');
		System.out.print("\nAfter deleting 'S'  : ");
		bt.showTree();
		bt.delete('G');
		System.out.print("\nAfter deleting 'G'  : ");
		bt.showTree();
		bt.delete('U');
		System.out.print("\nAfter deleting 'U'  : ");
		bt.showTree();
	}

}