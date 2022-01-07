package week4BalancedTree;

import week4BalancedTree.BST.Node;

public class AVLTree extends BST {
	
	public AVLTree() {
		super();
	}
	//재정의
	public void insert(char c) {
		Node r = insert(c, null, root);
		//find x (최초로 균형이 깨진 놈)
		Node p = r.parent;
		while(p != null) {
			if(!isBalanced(p)) {
				break;
			}
			p = p.parent;
		}
		Node x = p; //최초로 균형이 깨진놈 
		Node y = null;
		
		if(x != null) { //균형이 깨진놈이 있다면, 
			if(c < x.key) { 
				y = x.left;
				if(c < y.key) { // LL
					rotateRight(x);
				}
				else { // LR
					rotateLeft(y);
					rotateRight(x);
				}
			}
			else {
				y = x.right;
				if(c > y.key) { // RR
					rotateLeft(x);
				}
				else {// RL
					rotateRight(y);
					rotateLeft(x);
				}
			}
		}
	}

	public void AVLdelete(char c) {
		Node x = delete(c);
		Node y = null;
		Node z = null;
		Node w = null;
		
		
		while(x != null) { 
			if(!isBalanced(x)) {
				if(height(x.left) >= height(x.right)) { //왼쪽에서 발생
					y = x.left;
					if(y.left != null) {
						z = y.left;
						w = rotateRight(x);
					}
					else { // 지그재그로 왼쪽 오른쪽으로 자식이 달려있음 LR
						z = y.right;
						rotateLeft(y);
						w = rotateRight(x);
					}
				}
				else {
					y = x.right; // 오른쪽에서 발생
					if(y.left != null) { // 지그재그 오른쪽 왼쪽으로 자식이 달려있음 RL
						z = y.left;
						rotateRight(y);
						w = rotateLeft(y);
					}
					else { //오른쪽으로 나란히 두개가 달려있음 
						z = y.right;
						w = rotateLeft(x); // RR
					}
				}
				if(w.parent == null) { //root라면
					root = w;
				}
				x = w.parent; //한단계 위로 올라가서 다시 반복문 시작
			}
			else x=x.parent; //밸런스가 잘 맞춰져 있다면, 위로 올라가서 다시 반복문 시작 
		}
	}
	private Node rotateLeft(Node x) {
		Node y = x.right;
		y.parent = x.parent;
		if(y.parent == null) {
			root = y;
		}
		else {
			if(x==x.parent.left) x.parent.left = y;
			else x.parent.right = y;
		}
		x.parent = y;
		x.right = y.left;
		if(y.left != null) {
			y.left.parent = x;
		}
		y.left = x;
		return y;
	}
	
	private Node rotateRight(Node x) {
		Node y = x.left;
		y.parent = x.parent;
		if(y.parent == null) {
			root = y;
		}
		else {
			if(x==x.parent.left) x.parent.left = y;
			else x.parent.right = y;
		}
		x.parent = y;
		x.left = y.right;
		if(y.right != null) {
			y.right.parent = x;
		}
		y.right = x;
		return y;
	}
	
	private boolean isBalanced(Node p) {
		if(p == null) {
			return true;
		}
		if(Math.abs(height(p.left)-height(p.right)) <= 1) {
			return true;
		}
		else return false;
	}

	public static void main(String[] args) {
		int inputSize = 26;
		char [] data = new char[inputSize];
		
		for (int i=0; i<inputSize; i++) {
			data[i] = (char)((int)'A'+i);
		}
		
		BST bt = new BST();
		
		for(int i=0; i<inputSize; i++) {
			bt.insert(data[i]);
		}
		System.out.println(("Initial Skewed Tree"));
		bt.showTree();
		System.out.println("Max. Height = "+bt.height());
		System.out.println("IPL = "+bt.IPL());
		
		AVLTree bt1 = new AVLTree();
		for(int i=0; i<inputSize; i++) {
			bt1.insert(data[i]);
		}
		System.out.println(("AVL Tree"));
		bt1.showTree();
		System.out.println("Max. Height = "+bt1.height());
		System.out.println("IPL = "+bt1.IPL());
	}
}
