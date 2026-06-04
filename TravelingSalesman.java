import java.util.Arrays;

public class TravelingSalesman {

    public static void main(String[] args) {
        int[][] dist = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        System.out.println("Traveling Salesman Problem (4 Cities)\n");

        // Approach 1: Recursive Brute Force
        long startTime = System.nanoTime();
        int bruteForceResult = tspBruteForce(dist);
        long bruteForceTime = System.nanoTime() - startTime;
        System.out.println("Brute Force Result: Minimum Cost = " + bruteForceResult + 
                           " (Time: " + bruteForceTime + " ns)");

        // Approach 2: Dynamic Programming (Held-Karp)
        startTime = System.nanoTime();
        int dpResult = tspDP(dist);
        long dpTime = System.nanoTime() - startTime;
        System.out.println("Held-Karp DP Result: Minimum Cost = " + dpResult + 
                           " (Time: " + dpTime + " ns)");
    }

    
    // Approach 1: Brute Force (Permutations)
   
    public static int tspBruteForce(int[][] dist) {
        int n = dist.length;
        boolean[] visited = new boolean[n];
        
        visited[0] = true; 
        
        return backtrack(dist, visited, 0, 1, 0);
    }

    private static int backtrack(int[][] dist, boolean[] visited, int currCity, int count, int currentCost) {
        int n = dist.length;
        
        
        if (count == n) {
            return currentCost + dist[currCity][0];
        }

        int minCost = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                
                int newCost = backtrack(dist, visited, i, count + 1, currentCost + dist[currCity][i]);
                minCost = Math.min(minCost, newCost);
                
                
                visited[i] = false;
            }
        }

        return minCost;
    }

   
    // Approach 2: Dynamic Programming (Held-Karp)
    
    public static int tspDP(int[][] dist) {
        int n = dist.length;
        
        
        int[][] memo = new int[1 << n][n];
        
      
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return solveDP(dist, 1, 0, memo);
    }

    private static int solveDP(int[][] dist, int mask, int currCity, int[][] memo) {
        int n = dist.length;
        
        if (mask == (1 << n) - 1) {
            return dist[currCity][0];
        }

        if (memo[mask][currCity] != -1) {
            return memo[mask][currCity];
        }

        int minCost = Integer.MAX_VALUE;

        
        for (int i = 0; i < n; i++) {
            
            if ((mask & (1 << i)) == 0) {
                
                int newCost = dist[currCity][i] + solveDP(dist, mask | (1 << i), i, memo);
                minCost = Math.min(minCost, newCost);
            }
        }

        return memo[mask][currCity] = minCost;
    }
}