package week7;

/**
 * Xn 의 물건이 있고, 최대 m의 물건을 담을 수 잇는 배낭에 가격이 최대로 담는 법은? 나를 담았을 때랑 안 담았을 때 중 큰 것을
 * 선택하는 걸 뒤에서부터 재귀적으로,,,
 */
public class Napsack_Algorithm {
	int count = 0;
	int price;

	int[] arrayW;
	int[] arrayV;

	int[][] dp;

	Napsack_Algorithm(int[] arrayW, int[] arrayV) {
		this.arrayW = arrayW;
		this.arrayV = arrayV;
	}

	// m은 무게(배낭에 남은 공간), n은 뒤에서부터 시작해서 Xn번째의 물건을 선택
	private int Knapsack(int m, int n) {
		count++;
		if (n <= 0)
			return 0;
		if (arrayW[n] > m) // n번째의 물건을 담았는데, 무게가 초과되면
			return Knapsack(m, n - 1); // 선택하지 않고, 다음 물건으로 넘어감
		else if (arrayW[n] <= m) { // n번째의 물건을 담았는데, 무게가 초과되지 않으면,
			return Math.max(arrayV[n] + Knapsack(m - arrayW[n], n - 1), Knapsack(m, n - 1)); // 담았을 때랑 안 담았을 때 중에 큰것을
																								// 재귀적으로;
		}
		return arrayV[n];
	}

	// 물건이 행 / 배낭이 열 (1,2,3,4,5,6,7,8,,,)
	private int Knapsack_dp(int m, int n) {
		int max = 0;
		dp = new int[n + 1][m + 1];
		for (int i = 0; i < n; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j < m; j++) {
			dp[0][j] = 0;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				count++;
				if (arrayW[i - 1] <= j) { // 물건을 담았는데, 무게초과가 아니라면,
					dp[i][j] = Math.max(arrayV[i - 1] + dp[i - 1][j - arrayW[i - 1]], dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
				max = Math.max(dp[i][j], max);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] arrayW = { 3, 2, 5, 1, 4, 3, 5 };
		int[] arrayV = { 1, 3, 4, 2, 7, 4, 8 };

		Napsack_Algorithm ns = new Napsack_Algorithm(arrayW, arrayV);
		int result = ns.Knapsack(12, 6);
		System.out.println("Knapsack. Recursion 의 결과 : " + result + " 입니다.");
		System.out.println("Knapsack. Recursion 의 Count : " + ns.count + " 입니다.");

		Napsack_Algorithm ns2 = new Napsack_Algorithm(arrayW, arrayV);
		int result2 = ns2.Knapsack_dp(12, 7);
		System.out.println("Knapsack. Recursion 의 결과 : " + result2 + " 입니다.");
		System.out.println("Knapsack. Recursion 의 Count : " + ns2.count + " 입니다.");
	}
}
