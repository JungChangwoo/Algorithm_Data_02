package week2hash;

public class OpenAddDouble { // Double Hashing

	int nOfHops =0;
	double threshold = 0.99; //임계점
	
	int [] table;
	int tableSize;
	int numberOfItems;
	
	public OpenAddDouble (int n) {
		tableSize = n;
		table = new int[tableSize];
		numberOfItems = 0;
		// -1 : null, -999 : deleted
		for (int i=0; i<tableSize; i++)
			table[i]=-1;
	}

	private int hashFunction(int d) {
		// 곱하기방법
		double temp = (double)d * 0.6180339887;
		double res = temp - Math.floor(temp);
		return (int)(res*tableSize);
	}
	// tableSize보다 조금 작은 소수 구하는 함수
	private int findPrime(int m) {
		for(int i = m-1; i>(m/2); i--) {
			if(isPrime(i)) {
				return i;
			}
		}
		return 0;
	}
	private boolean isPrime(int i) {
		for(int j=2; j<(i/2); j++) {
			float x = (float) i/j;
			int y = i/j;
			if(x == y) return false;
		}
		return true;
	}
	
	//Double Hashing
	private int hashFunction2(int d) {
		// 곱하기방법2
		double temp = (double) d * 0.6180339887;
		double res = temp - Math.floor(temp); 
		return (int) (res * findPrime(tableSize));
	}

//	public int hashInsert(int d)
	public int hashInsert(int d) {
		if(loadfactor() >= threshold) {
			enlargeTable();
		}
		int hashCode = hashFunction(d);
		nOfHops = 1;
		if (table[hashCode] == -1) {
			table[hashCode] = d;
			numberOfItems++;
			return nOfHops;
		}
		else { // collision
			nOfHops++;
			int nOfCollisions = 1;
			int hashCode2 = hashFunction2(d);
			int probeIndex = (hashCode + nOfCollisions*hashCode2) % tableSize;
			while(table[probeIndex] != -1 && table[probeIndex] != -999) {
				nOfHops++;
				nOfCollisions++;
				probeIndex = (hashCode + nOfCollisions*hashCode2) % tableSize;
//				if(probeIndex == hashCode) {
//					return 0; // not Happen
//				}
			}
			table[probeIndex] = d;
			numberOfItems++;
			return nOfHops;
		}
	}
	

//	public int hashSearch(int d)
	public int hashSearch(int d) {
		int hashCode = hashFunction(d);
		nOfHops = 1;
		
		if (table[hashCode] == d) {
			return nOfHops;
		}
		else { // collision
			nOfHops++;
			int nOfCollisions = 1;
			int hashCode2 = hashFunction2(d);
			int probeIndex = (hashCode + nOfCollisions*hashCode2)  % tableSize;
			while(table[probeIndex] != -1 && table[probeIndex] != d) {
				nOfHops++;
				nOfCollisions++;
				probeIndex = (hashCode + nOfCollisions*hashCode2)  % tableSize;
//				if(probeIndex == hashCode) {
//					return 0; // not Happen
//				}
			}
			// 못 찾은 경우와 찾은 경우가 있음
			if(table[probeIndex] == d) {
				return nOfHops;
			}
			else {
				return -nOfHops;
			}
		}
	}
	
	
//	public int hashDelete(int d) 
	public int hashDelete(int d) {
		
		int hashCode = hashFunction(d);
		nOfHops = 1;
		
		if (table[hashCode] == d) {
			table[hashCode] = -999;
			numberOfItems--;
			return nOfHops;
		}
		else { // collision
			nOfHops++;
			int hashCode2 = hashFunction2(d);
			int probeIndex = (hashCode + hashCode2) % tableSize;
			while(table[probeIndex] != -1 && table[probeIndex] != d) {
				nOfHops++;
				probeIndex = (probeIndex + hashCode2) % tableSize;
				if(probeIndex == hashCode) {
					return 0; // not Happen
				}
			}
			// 못 찾은 경우와 찾은 경우가 있음
			if(table[probeIndex] == d) {
				table[probeIndex] = -999;
				numberOfItems--;
				return nOfHops;
			}
			else {
				return -nOfHops;
			}
		}
	}
	
	
//	private void enlargeTable() 
	private void enlargeTable() {
		int [] oldTable = this.table;
		int oldSize = this.tableSize;
		this.tableSize *= 2; // 원래는 2배로 하면 안 됨
		this.table = new int [this.tableSize];
		for(int i=0; i < tableSize; i++) {
			table[i] = -1;
		}
		//reHashing
		for(int i=0; i<oldSize; i++) {
			if(oldTable[i] >= 0) {
			hashInsert(oldTable[i]);
			}
		}
	}
	
	
	

	public double loadfactor() {
		return (double)numberOfItems/tableSize;
	}
	
	public void showTable() {
		System.out.println("Current Hash Table : ");
		for (int i = 0; i<tableSize; i++)
			System.out.print(table[i]+"  ");
		System.out.println();
	}

	
	public static void main(String[] args) {
		int tableSize = 17;
		
		int [] data = {10, 12, 18, 20, 22, 23, 26, 27, 42, 57};
		int dataSize = data.length;
		
		System.out.println("\n *** Open Addressing - Linear Probing ***");
		
		OpenAddrLinear myHash = new OpenAddrLinear(tableSize);
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
