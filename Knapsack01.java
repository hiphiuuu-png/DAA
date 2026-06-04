public class Knapsack01 {

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;

        System.out.println("Brute Force Result: " + knapsackBruteForce(capacity, weights, values, values.length));
        System.out.println("DP Result: " + knapsackDP(capacity, weights, values, values.length));
    }

    // Approach 1: Brute Force
    public static int knapsackBruteForce(int W, int[] wt, int[] val, int n) {
        if (n == 0 || W == 0) {
            return 0;
        }

        if (wt[n - 1] > W) {
            return knapsackBruteForce(W, wt, val, n - 1);
        } else {
            return Math.max(
                val[n - 1] + knapsackBruteForce(W - wt[n - 1], wt, val, n - 1),
                knapsackBruteForce(W, wt, val, n - 1)
            );
        }
    }

    // Approach 2: Dynamic Programming
    public static int knapsackDP(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }
}