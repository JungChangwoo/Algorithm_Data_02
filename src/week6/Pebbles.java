package week6;

public class Pebbles { // 돌 놓기
	
	int [][] peb;
	int [][] memo;
	int [][] nextPattern = {
			{0,0,0},  // X
			{2,3,0},  // 1 => 2,3
			{1,3,4},  // 2 => 1,3,4
			{1,2,0},  // 3 => 1,2
			{2,0,0} };// 4 => 2
	int nCols;
	int count; // DP랑 비교하기 위해서
	
	public Pebbles(int [][] input) {
		peb = input;
		nCols = peb[0].length;
		memo = new int[5][nCols]; // 5 이유 : 0번째 X
		reset();
	}
	void reset() {
		count = 0;
		for(int mi=0; mi<5; mi++) 
			for(int mj=0; mj<nCols; mj++)
				memo[mi][mj] = -9999;
	}
	int getCount() {return count;}
	
	public int maxPebble(int n) { //맨 뒤에서부터 시작하는데, 맨 뒤에서부터 시작할 때 패턴을 하나씩 넣어주면서 시작 
		int max = -99999;
		for(int j=1; j<=4; j++) 
			max = Math.max(pebble(n,j), max);
		return max;
	}
	
	public int maxPebbleMemo(int n) {
		int max = -99999;
		for(int j=1; j<=4; j++) 
			max = Math.max(pebbleMemo(n,j), max);
		return max;
	}
	
	int pebbleMemo(int i, int p) {
		count++;
		if (i==1) {
			memo[p][i] = aPatternValue(i,p);
			return memo[p][i];
		}
		else {
			int max = -99999;
			int k = 0;
			while(k<3 && nextPattern[p][k] != 0) {
				int q = nextPattern[p][k];
				if(memo[q][i-1] <= -999) {
					memo[q][i-1] = pebbleMemo(i-1,q);
				}
				max = Math.max(memo[q][i-1], max);
				k++;
			}
			memo[p][i] = max + aPatternValue(i, p);
			return memo[p][i];
		}
	}
	
	int pebble(int i, int p) { // n번째, 패턴숫자 j(1,2,3,4)
		count++;
		if (i==1) { //마지막에 도달하면 바로 return
			return aPatternValue(i,p);
		}
		else {
			int max = -99999;
			int k = 0; // nextPattern에서 가능한 패턴의 숫자를 return해주기 위해ㅓ
			while(k<3 && nextPattern[p][k] != 0) { //다음 가능한 Pattern을 하나씩 해봄 => 그 중 가장 큰 것을 구함
				int q = nextPattern[p][k];
				max = Math.max(pebble(i-1,q), max);
				k++;
			}
			return aPatternValue(i, p)+max; // 현재의 값에 sub중 가장 큰 것을 더해줌
		}
	}
	
	private int aPatternValue(int i, int p) {
		int retVal = 0;
		switch(p) {
		case 1 : retVal = peb[0][i];
		break;
		case 2 : retVal = peb[1][i];
		break;
		case 3 : retVal = peb[2][i];
		break;
		case 4 : retVal = peb[0][i] + peb[2][i];
		break;
		}
		return retVal;
	}
	
	public static void main(String[] args) {
		int [][] input = {
				{0,6,7,12,-5,5,3,11,3,7,-2,5,4},
				{0,-8,10,14,9,7,13,8,5,6,1,3,9},
				{0,11,12,7,4,8,-2,9,4,-4,3,7,10} };
		
		Pebbles myPeb = new Pebbles(input);
		
		for(int i=1; i<input[0].length; i++) {
			myPeb.reset();
			System.out.println(">>> "+i+" : [Recursion] "
					+myPeb.maxPebble(i)+", Count = "+myPeb.getCount());
			myPeb.reset();
			System.out.println(" ===> [DP] "
					+myPeb.maxPebbleMemo(i)+", Count = "+myPeb.getCount());
		}
	}
}
