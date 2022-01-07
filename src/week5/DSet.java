package week5;

public class DSet { 
	
	int key;
	int rank; //자신을 루트로하는 서브트리의 높이 
	DSet parent;
	
	public DSet() {
		key = 1;
		rank = -1;
		parent = null;
	}
	
	public boolean equals(DSet other) {
		if(key == other.key)
			return true;
		else
			return false;
	}
	
	public String toString() {
		return ""+key+"["+parent.key+","+rank+"]";
	}
	
	public void showParent() {
		DSet p = this;
		System.out.print(p.toString());
		while(!p.equals(p.parent)) {
			p = p.parent;
			System.out.print("--> "+p.toString());
		}
		System.out.println();
	}
	
	public DSet makeSet(int k) {
		key = k;
		rank = 0;
		parent = this;
		return this;
	}
	//node가 속한 루트를 Return 
//	public DSet findSet(DSet node) {
//		DSet p = node;
//		while(!p.equals(p.parent)) { // root가 아니라면
//			p = p.parent;
//		}
//		return p;//root
//	}
	public DSet findSet(DSet node) {
	DSet p = node;
	if(p.equals(p.parent)) return p;
	return p.parent = findSet(p.parent);//root
}
	
	public DSet union(DSet other) {
		DSet u = findSet(this);
		DSet v = findSet(other);
		
		if(u.rank > v.rank) {
			v.parent = u;
			return u;
		}
		else if (v.rank > u.rank) {
			u.parent = v;
			return v;
		}
		else {
			v.parent = u;
			u.rank++; //rank가 같은 두 집합을 union하면 올라감
			return u;
		}
	}
	public static void main(String[] args) {
		// data = {1,2,3,4,5,6,7}
		int dataSize = 7;
		
		DSet[] element = new DSet[dataSize];
		
		for(int i=0; i<dataSize; i++) {
			element[i] = new DSet();
			element[i].makeSet(i);
			System.out.println(element[i].toString());
		}
		
		System.out.print("Union 0 & 1 ==> ");
		DSet p = element[0].union(element[1]);
		System.out.println(p.toString());
		System.out.print("Union 2 & 1 ==> ");
		p = element[2].union(element[1]);
		System.out.println(p.toString());
		
		System.out.print("Union 3 & 4 ==> ");
		p = element[3].union(element[4]);
		System.out.println(p.toString());
		
		System.out.print("Union 2 & 4 ==> ");
		p = element[2].union(element[4]);
		System.out.println(p.toString());
		
		for(int i=0; i<dataSize; i++) {
			element[i].showParent();
		}
	}

}
