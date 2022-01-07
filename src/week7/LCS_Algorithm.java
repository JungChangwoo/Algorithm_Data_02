package week7;

public class LCS_Algorithm {
	int count = 0;

	char[] arrayX;
	char[] arrayY;

	int[][] dp_table;

	public LCS_Algorithm(char[] inputX, char[] inputY) {
		this.arrayX = inputX;
		this.arrayY = inputY;
	}

	private int LCS(int m, int n) {
		count++;
		if (m == 0 || n == 0)
			return 0;
		else if (arrayX[m] == arrayY[n]) {
			return LCS(m - 1, n - 1) + 1;
		} else
			return Math.max(LCS(m - 1, n), LCS(m, n - 1));
	}

	private int LCS_DP(int m, int n) {
		dp_table = new int[m][n];
		for (int i = 0; i < m; i++) {
			dp_table[i][0] = 0;
		}
		for (int j = 0; j < n; j++) {
			dp_table[0][j] = 0;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				count++;
				if (arrayX[i] == arrayY[j]) {
					dp_table[i][j] = dp_table[i - 1][j - 1] + 1;
				} else {
					dp_table[i][j] = Math.max(dp_table[i - 1][j], dp_table[i][j - 1]);
				}
			}
		}
		return dp_table[m-1][n-1];
	}

	public static void main(String[] args) {
		char [] arrayX = {'0', 'a', 'b', 'b', 'c', 'e', 'f', 'e', 'f', 'd', 'a', 'b', 'c'};
		char [] arrayY = {'0', 'a', 'b', 'c', 'd', 'e', 'f', 'b', 'd', 'a', 'b', 'c', 'd'};
		LCS_Algorithm lcs = new LCS_Algorithm(arrayX, arrayY);
		
		int result = lcs.LCS(12,12);
		System.out.println("LCS의 결과. 최장공통 부분 길이는  : " + result + " 입니다.");
		System.out.println("LCS의 결과. Count  : " + lcs.count + " 입니다.");
		
		LCS_Algorithm lcs2 = new LCS_Algorithm(arrayX, arrayY);
		
		int result_dp = lcs2.LCS_DP(13, 13);
		System.out.println("LCS_DP의 결과. 최장공통 부분 길이는  : " + result_dp + " 입니다.");
		System.out.println("LCS의 결과. Count  : " + lcs2.count + " 입니다.");
	}

}
