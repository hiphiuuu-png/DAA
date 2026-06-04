import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ActivitySelection {

    static class Activity {
        char id;
        int start;
        int finish;

        public Activity(char id, int start, int finish) {
            this.id = id;
            this.start = start;
            this.finish = finish;
        }
    }

    public static void main(String[] args) {
        Activity[] activities = {
            new Activity('A', 1, 4),
            new Activity('B', 3, 5),
            new Activity('C', 0, 6),
            new Activity('D', 5, 7),
            new Activity('E', 3, 9),
            new Activity('F', 5, 9),
            new Activity('G', 6, 10),
            new Activity('H', 8, 11)
        };

        System.out.println("Brute Force Result: " + activitySelectionBruteForce(activities) + " activities");
        System.out.println("Greedy Result: " + activitySelectionGreedy(activities) + " activities");
    }

    // Approach 1: Brute Force
    public static int activitySelectionBruteForce(Activity[] arr) {
        int n = arr.length;
        int maxCount = 0;
        int totalSubsets = 1 << n;

        for (int i = 0; i < totalSubsets; i++) {
            List<Activity> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(arr[j]);
                }
            }

            if (isValid(subset)) {
                maxCount = Math.max(maxCount, subset.size());
            }
        }

        return maxCount;
    }

    private static boolean isValid(List<Activity> subset) {
        for (int i = 0; i < subset.size(); i++) {
            for (int j = i + 1; j < subset.size(); j++) {
                if (subset.get(i).start < subset.get(j).finish && 
                    subset.get(j).start < subset.get(i).finish) {
                    return false;
                }
            }
        }
        return true;
    }

    // Approach 2: Greedy
    public static int activitySelectionGreedy(Activity[] arr) {
        Activity[] sortedActivities = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedActivities, Comparator.comparingInt(a -> a.finish));

        int count = 0;
        int lastFinishTime = -1;

        for (Activity a : sortedActivities) {
            if (a.start >= lastFinishTime) {
                count++;
                lastFinishTime = a.finish;
            }
        }

        return count;
    }
}