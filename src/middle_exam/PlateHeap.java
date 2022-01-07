package middle_exam;

public class PlateHeap {
	int [][] input ;
	int size;
	int [][] dpt;
	int count;
	
	public PlateHeap(int [][] data) {
		input = data;
		size = input.length;
	}
	
	public void resetCount() {
		count = 0;
	}
	
	public int getCount() {
		return count;
	}
	
	public void initDPT() {
		dpt =new int[size][size];
		for (int i=0; i<size; i++)
			for (int j=0; j<size; j++)
			dpt[i][j]=0;
		for (int i=0; i<size; i++)
			dpt[i][i]=input[i][1];
	}
	

	public int maxHeapRec(int n) {
		return maxHeapRec(n, input[n][0] );
	}
	public int maxHeapRec(int n, int R) {
		count++;
		if (n==0) {
				return 0;
		}
		if (input[n][0]<R) {
			return Math.max(input[n][1], maxHeapRec(n-1, R));
		}
		else
			return Math.max(input[n][1]+maxHeapRec(n-1, input[n][0]), maxHeapRec(n-1, R));
	}
	
	public int maxHeap(int n) {
		count++;
		if (n==0) {
			return input[0][1];
		}
		int max1 = 0;
		int max2 = input[n][1];
		for (int i = 0 ; i<n; i++) {
			if (input[i][0]>=input[n][0]) {
				max1 = Math.max(max1, input[n][1]+maxHeap(i));
			}
			max2 = Math.max(max2, maxHeap(i));
		}
		return Math.max(max1, max2);
	}
	
	public int maxHeapDP(int n) {
		count++;
		if (n==0) {
			return input[0][1];
		}
		int max1 = 0;
		int max2 = input[n][1];
		for (int i = 0 ; i<n; i++) {
			if (input[i][0]>=input[n][0]) {
				if (dpt[i][n]==0)
					dpt[i][n] = input[n][1]+maxHeapDP(i);
				max1 = Math.max(max1, dpt[i][n]);
			}
			
			if (dpt[i][n]==0)
				dpt[i][n] = maxHeapDP(i);
			max2 = Math.max(max2, dpt[i][n]);
			
		}
		return Math.max(max1, max2);
	}
	
	public void showDPT() {
	
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) 
				 System.out.printf("%3d", dpt[i][j]);
			System.out.println();
		}
	}

	public static void main(String[] args) {

		int [][] data = {{5,3}, {7,10}, {3,3}, {2,1}, {4,7}, {2,2}, {3,3}};  // <== (r, h)
		
		PlateHeap p = new PlateHeap(data);
		
		p.resetCount();
		System.out.println(p.maxHeapRec(data.length-1)+"  "+p.getCount());

		p.resetCount();
		System.out.println(p.maxHeap(data.length-1)+"  "+p.getCount());

		p.resetCount();
		p.initDPT();
		System.out.println(p.maxHeapDP(data.length-1)+"  "+p.getCount());
		p.showDPT();
		
	
	}

}