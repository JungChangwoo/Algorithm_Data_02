package week2hash;

public class Chaining {
	
	int nOfHops=0;
	
	private class HashNode {
		int key;
		HashNode next;
		public HashNode(int k) {
			key=k;
			next=null;
		}
		public String toString() {
			return "->"+key;
		}	
	}
	
	HashNode [] table;
	int tableSize;
	int numberOfItems;
	
	public Chaining(int n) {
		tableSize = n;
		numberOfItems=0;
		table = new HashNode[tableSize];
		for (int i=0; i<tableSize; i++)
			table[i]=null;
	}
	private int hashFunction(int d) {
		// 나누기 방법
		return d%tableSize;
	}	
//	public int hashInsert(int d) 
	public int hashInsert(int d) {
		int hashCode = hashFunction(d);
		HashNode newNode = new HashNode(d);
		newNode.next = table[hashCode]; // 원래 있던 Node가 next
		table[hashCode] = newNode;		// 맨 앞에 집어넣음
		numberOfItems ++;
		nOfHops = 1; // 맨 앞에 넣는다고 하면, 항상 횟수는 1이니까.
		return nOfHops;
	}

//	public int hashSearch(int d) 
	public int hashSearch(int d) {
		int hashCode = hashFunction(d);
		// 링크를 따라갈 Pointer가 필요함
		HashNode p = table[hashCode];
		nOfHops = 1;
		while(p != null) {
			if(p.key == d) {
				return nOfHops;
			}
			else {
				nOfHops ++;
				p = p.next;
			}
		}
		// 못 찾았다면,
		return -nOfHops;
		
	}
	
//	public int hashDelete(int d) 
	public int hashDelete(int d) {
		int hashCode = hashFunction(d);
		// 링크를 따라갈 Pointer가 필요함
		HashNode p = table[hashCode];
		nOfHops = 1;
		//그런 값이 없다... 
		if(p == null) {
			return -nOfHops;
		} //맨 앞에 있는 값이더라... 
		else if (p.key == d){
			table[hashCode] = p.next; // 뒤에 거를 앞에 놓음
			numberOfItems--;
			return nOfHops;
		}
		// 두 개를 만들어야 앞 뒤를 연결할 수 있음
		HashNode q = p.next;
		nOfHops++;
		// 맨 앞이 아닐 경우 null이 나올 때까지 반복
		while(q != null) {
			if(q.key == d) {
				p.next = q.next;
				numberOfItems--;
				return nOfHops;
			}
			else {
				p = q;
				q = q.next;
				nOfHops++;
			}
		}
		// 만약 못 찾았다면
		return -nOfHops;
	}
	
//	public double loadfactor() //적재율
	public double loadfactor() {
		return ((double) numberOfItems / tableSize);
	}
	
	public void showTable() {
		System.out.println("\n\n<< Current Table Status >>");
		for (int i=0;i<tableSize; i++) {
			HashNode p = table[i];
			System.out.print("\n "+i+" : ");
			while(p!=null) {
				System.out.print(p.toString());
				p=p.next;
			}
		}
	}
	

	public static void main(String[] args) {
		int tableSize = 16;
		
		int [] data = {10, 12, 18, 20, 22, 23, 26, 27, 42, 57};
		int dataSize = data.length;
		
		System.out.println("\n *** Chaining ***");
		
		Chaining myHash = new Chaining(tableSize);
		// Insert
		int sumOfSuccess = 0;
		int sumOfFailure = 0;
		int maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashInsert(data[i]);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else
				sumOfFailure += count;
		}
		myHash.showTable();
		System.out.println("\n\n [Insert] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);
		System.out.println("\n Load Factors ="+myHash.loadfactor()); 
		
		// Search with existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashSearch(data[i]);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Search 1] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);

		// Search with non-existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashSearch(data[i]+1);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Search 2] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);

		// Delete with non-existing data set
		sumOfSuccess = 0;
		sumOfFailure = 0;
		maxCount = 0;
		for (int i =0; i<dataSize; i++) {
			int count = myHash.hashDelete(data[i]+1);
			if (count>=0) {
				sumOfSuccess += count;
				if (count>maxCount) maxCount = count;
			}
			else {
				sumOfFailure += count;
				if ((-count)>maxCount) maxCount = -count;
			}
		}
		System.out.println("\n\n [Delete] No. of Hops : Success ="+sumOfSuccess 
				+ "  Failure = "+sumOfFailure+"   Max. Hop Count = "+ maxCount);


	}

}

