import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(60, 10),
            new Item(100, 20),
            new Item(120, 30)
        };
        int capacity = 50;

        System.out.println("Brute Force Result: " + knapsackBruteForce(items, capacity, 0));
        System.out.println("Greedy Result: " + knapsackGreedy(items, capacity));
    }

    // Approach 1: Brute Force (Discretized Splits)
    public static double knapsackBruteForce(Item[] items, int capacity, int index) {
        if (index == items.length || capacity == 0) {
            return 0.0;
        }

        double maxProfit = 0.0;
        int maxWeightWeCanTake = Math.min(items[index].weight, capacity);
        double valuePerUnit = (double) items[index].value / items[index].weight;

        for (int w = 0; w <= maxWeightWeCanTake; w++) {
            double currentProfit = (w * valuePerUnit) + knapsackBruteForce(items, capacity - w, index + 1);
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }

    // Approach 2: Greedy (Famous Approach)
    public static double knapsackGreedy(Item[] items, int capacity) {
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                double r1 = (double) i1.value / i1.weight;
                double r2 = (double) i2.value / i2.weight;
                return Double.compare(r2, r1); 
            }
        });

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity >= item.weight) {
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                totalValue += item.value * ((double) capacity / item.weight);
                break;
            }
        }

        return totalValue;
    }
}