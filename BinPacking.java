import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinPacking {

    public static void main(String[] args) {
        int[] items = {2, 5, 4, 7, 1, 3, 8};
        int capacity = 10;

        System.out.println("Items: " + Arrays.toString(items));
        System.out.println("Bin Capacity: " + capacity + "\n");

        // 1. Brute Force (Optimal)
        System.out.println("--- Brute Force ---");
        int bruteForceBins = getOptimalBins(items, capacity);
        System.out.println("Minimum bins required: " + bruteForceBins);

        // 2. Heuristics
        System.out.println("\n--- Greedy Heuristics ---");
        System.out.println("Next Fit Bins: " + nextFit(items, capacity));
        System.out.println("First Fit Bins: " + firstFit(items, capacity));
        System.out.println("Best Fit Bins: " + bestFit(items, capacity));
        System.out.println("First Fit Decreasing Bins: " + firstFitDecreasing(items, capacity));
    }

    // Approach 1: Brute Force (Recursive Optimal)
    
    static int minBins = Integer.MAX_VALUE;

    public static int getOptimalBins(int[] items, int capacity) {
        minBins = items.length; // Worst case: 1 bin per item
        int[] bins = new int[items.length];
        backtrack(items, bins, capacity, 0, 0);
        return minBins;
    }

    private static void backtrack(int[] items, int[] bins, int capacity, int itemIndex, int currentBinsUsed) {
        
        if (currentBinsUsed >= minBins) return;

       
        if (itemIndex == items.length) {
            minBins = Math.min(minBins, currentBinsUsed);
            return;
        }

        
        for (int i = 0; i <= currentBinsUsed; i++) { 
            if (bins[i] + items[itemIndex] <= capacity) {
                bins[i] += items[itemIndex];
                
                
                int nextBinsUsed = (i == currentBinsUsed) ? currentBinsUsed + 1 : currentBinsUsed;
                
                backtrack(items, bins, capacity, itemIndex + 1, nextBinsUsed);
                
                
                bins[i] -= items[itemIndex];
            }
        }
    }

    
    // Approach 2: Next Fit
  
    public static int nextFit(int[] items, int capacity) {
        int bins = 0;
        int remainingSpace = 0;

        for (int item : items) {
            
            if (item > remainingSpace) {
                bins++;
                remainingSpace = capacity - item;
            } else {
                remainingSpace -= item;
            }
        }
        return bins;
    }

    
    // Approach 3: First Fit
  
    public static int firstFit(int[] items, int capacity) {
        List<Integer> binSpaces = new ArrayList<>();

        for (int item : items) {
            boolean placed = false;
            
            for (int i = 0; i < binSpaces.size(); i++) {
                if (binSpaces.get(i) >= item) {
                    binSpaces.set(i, binSpaces.get(i) - item);
                    placed = true;
                    break;
                }
            }
           
            if (!placed) {
                binSpaces.add(capacity - item);
            }
        }
        return binSpaces.size();
    }

    // Approach 4: Best Fit

    public static int bestFit(int[] items, int capacity) {
        List<Integer> binSpaces = new ArrayList<>();

        for (int item : items) {
            int minSpaceLeft = capacity + 1;
            int bestBinIndex = -1;

            for (int i = 0; i < binSpaces.size(); i++) {
                int spaceLeft = binSpaces.get(i) - item;
                if (spaceLeft >= 0 && spaceLeft < minSpaceLeft) {
                    minSpaceLeft = spaceLeft;
                    bestBinIndex = i;
                }
            }

            if (bestBinIndex != -1) {
                binSpaces.set(bestBinIndex, minSpaceLeft);
            } else {
                binSpaces.add(capacity - item);
            }
        }
        return binSpaces.size();
    }

    
    // Approach 5: First Fit Decreasing (FFD)
    
    public static int firstFitDecreasing(int[] items, int capacity) {
        
        int[] sortedItems = Arrays.stream(items)
                                  .boxed()
                                  .sorted(Collections.reverseOrder())
                                  .mapToInt(Integer::intValue)
                                  .toArray();
        
        
        return firstFit(sortedItems, capacity);
    }
}