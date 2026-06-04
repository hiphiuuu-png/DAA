import java.util.ArrayList;
import java.util.List;

public class SubsetGenerator {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        System.out.println("--- Recursive Inclusion/Exclusion ---");
        List<List<Integer>> recursiveResult = generateSubsetsRecursive(nums);
        for (List<Integer> subset : recursiveResult) {
            System.out.println(subset);
        }

        System.out.println("\n--- Bitmask Iteration ---");
        List<List<Integer>> bitmaskResult = generateSubsetsBitmask(nums);
        for (List<Integer> subset : bitmaskResult) {
            System.out.println(subset);
        }
    }

    
    // Approach 1: Brute Force (Recursive)
   
    public static List<List<Integer>> generateSubsetsRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int index, List<Integer> currentSubset, List<List<Integer>> result) {
        
        if (index == nums.length) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }
        
        backtrack(nums, index + 1, currentSubset, result);

        currentSubset.add(nums[index]);
        backtrack(nums, index + 1, currentSubset, result);
        
        currentSubset.remove(currentSubset.size() - 1);
    }
    
    
    // Approach 2: Bitmask Iteration
    
    public static List<List<Integer>> generateSubsetsBitmask(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        
        int totalSubsets = 1 << n; 

        for (int i = 0; i < totalSubsets; i++) {
            List<Integer> currentSubset = new ArrayList<>();
            
            
            for (int j = 0; j < n; j++) {
                
                if ((i & (1 << j)) != 0) {
                    currentSubset.add(nums[j]);
                }
            }
            result.add(currentSubset);
        }
        
        return result;
    }
}