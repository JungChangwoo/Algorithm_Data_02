package week7;

/**
 * Xn �� ������ �ְ�, �ִ� m�� ������ ���� �� �մ� �賶�� ������ �ִ�� ��� ����? ���� ����� ���� �� ����� �� �� ū ����
 * �����ϴ� �� �ڿ������� ���������,,,
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

	// m�� ����(�賶�� ���� ����), n�� �ڿ������� �����ؼ� Xn��°�� ������ ����
	private int Knapsack(int m, int n) {
		count++;
		if (n <= 0)
			return 0;
		if (arrayW[n] > m) // n��°�� ������ ��Ҵµ�, ���԰� �ʰ��Ǹ�
			return Knapsack(m, n - 1); // �������� �ʰ�, ���� �������� �Ѿ
		else if (arrayW[n] <= m) { // n��°�� ������ ��Ҵµ�, ���԰� �ʰ����� ������,
			return Math.max(arrayV[n] + Knapsack(m - arrayW[n], n - 1), Knapsack(m, n - 1)); // ����� ���� �� ����� �� �߿� ū����
																								// ���������;
		}
		return arrayV[n];
	}

	// ������ �� / �賶�� �� (1,2,3,4,5,6,7,8,,,)
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
				if (arrayW[i - 1] <= j) { // ������ ��Ҵµ�, �����ʰ��� �ƴ϶��,
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
		System.out.println("Knapsack. Recursion �� ��� : " + result + " �Դϴ�.");
		System.out.println("Knapsack. Recursion �� Count : " + ns.count + " �Դϴ�.");

		Napsack_Algorithm ns2 = new Napsack_Algorithm(arrayW, arrayV);
		int result2 = ns2.Knapsack_dp(12, 7);
		System.out.println("Knapsack. Recursion �� ��� : " + result2 + " �Դϴ�.");
		System.out.println("Knapsack. Recursion �� Count : " + ns2.count + " �Դϴ�.");
	}
}
