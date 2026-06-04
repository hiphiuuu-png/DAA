public class SubsetSum {

    public static void main(String[] args) {
        int[] nums = {3, 34, 4, 12, 5, 2};
        int target = 9;

        System.out.println("Array: [3, 34, 4, 12, 5, 2], Target: " + target);

        // Approach 1: Recursive Brute Force
        boolean recursiveResult = isSubsetSumRecursive(nums, target);
        System.out.println("Recursive Result: " + recursiveResult);

        // Approach 2: Dynamic Programming Table
        boolean dpResult = isSubsetSumDP(nums, target);
        System.out.println("DP Table Result: " + dpResult);
    }


    // Approach 1: Brute Force (Recursive)

    public static boolean isSubsetSumRecursive(int[] nums, int target) {
        return backtrack(nums, target, nums.length - 1);
    }

    private static boolean backtrack(int[] nums, int target, int index) {
        
        if (target == 0) {
            return true; 
        }
        if (index < 0) {
            return false; 
        }

        if (nums[index] > target) {
            return backtrack(nums, target, index - 1);
        }

        return backtrack(nums, target - nums[index], index - 1) || 
               backtrack(nums, target, index - 1);
    }

    
    // Approach 2: Dynamic Programming (Table)
    
    public static boolean isSubsetSumDP(int[] nums, int target) {
        int n = nums.length;
        
        boolean[][] dp = new boolean[n + 1][target + 1];

        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
 
        for (int j = 1; j <= target; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                
                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } 
                else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][target];
    }
}