public class StaircaseProblem {

    public static void main(String[] args) {
        int n = 5; 

        System.out.println("Climbing " + n + " stairs:");

        long startTime = System.nanoTime();
        int recursiveWays = climbStairsRecursive(n);
        long recursiveTime = System.nanoTime() - startTime;
        System.out.println("Recursive Result: " + recursiveWays + " ways (Time: " + recursiveTime + " ns)");

        startTime = System.nanoTime();
        int dpWays = climbStairsDP(n);
        long dpTime = System.nanoTime() - startTime;
        System.out.println("DP/Fibonacci Result: " + dpWays + " ways (Time: " + dpTime + " ns)");
    }

    
    // Approach 1: Brute Force (Recursive)
    
    public static int climbStairsRecursive(int n) {
        if (n <= 2) {
            return n;
        }
    
        return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2);
    }

    // Approach 2: Dynamic Programming / Fibonacci
  
    public static int climbStairsDP(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1; 
        int prev1 = 2; 
        int current = 0;

        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2; 
            
            prev2 = prev1;
            prev1 = current;
        }

        return current;
    }
}