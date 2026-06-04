import java.util.Arrays;

public class CoinExchange {

    public static void main(String[] args) {
        int[] canonicalCoins = {1, 2, 5, 10, 20, 50, 100}; 
        int amount1 = 68;

        System.out.println("--- Canonical System (Indian/US Currency) ---");
        System.out.println("Amount: " + amount1);
        System.out.println("Brute Force: " + coinChangeBruteForce(canonicalCoins, amount1) + " coins");
        System.out.println("Greedy: " + coinChangeGreedy(canonicalCoins, amount1) + " coins");
        System.out.println("DP: " + coinChangeDP(canonicalCoins, amount1) + " coins\n");

        int[] nonCanonicalCoins = {1, 3, 4};
        int amount2 = 6;

        System.out.println("--- Non-Canonical System ---");
        System.out.println("Coins: [1, 3, 4], Amount: " + amount2);
        System.out.println("Greedy Result: " + coinChangeGreedy(nonCanonicalCoins, amount2) + " coins");
        System.out.println("DP Result: " + coinChangeDP(nonCanonicalCoins, amount2) + " coins");
    }

    // Approach 1: Brute Force (Fixed with Memoization)
   
    public static int coinChangeBruteForce(int[] coins, int amount) {
       
        return solveRecursive(coins, amount, new Integer[amount + 1]);
    }

    private static int solveRecursive(int[] coins, int amount, Integer[] memo) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        
        
        if (memo[amount] != null) return memo[amount];

        int minCoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = solveRecursive(coins, amount - coin, memo);
            if (res >= 0 && res < minCoins) {
                minCoins = 1 + res;
            }
        }
        
        
        memo[amount] = (minCoins == Integer.MAX_VALUE) ? -1 : minCoins;
        return memo[amount];
    }

    
    // Approach 2: Greedy (Unchanged - Works Perfectly)
  
    public static int coinChangeGreedy(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        
        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
            }
        }
        return amount == 0 ? count : -1;
    }

  
    // Approach 3: Dynamic Programming (Unchanged - Works Perfectly)
    
    public static int coinChangeDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}