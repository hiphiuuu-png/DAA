import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JobSequencing {

    static class Job {
        char id;
        int deadline;
        int profit;

        public Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }

        @Override
        public String toString() {
            return String.format("{%c, D:%d, P:%d}", id, deadline, profit);
        }
    }

    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
            new Job('A', 2, 100),
            new Job('B', 1, 19),
            new Job('C', 2, 27),
            new Job('D', 1, 25),
            new Job('E', 3, 15)
        );

        System.out.println("Available Jobs: " + jobs + "\n");

        // 1. Brute Force 
        System.out.println("--- Brute Force Approach ---");
        int maxDeadline = jobs.stream().mapToInt(j -> j.deadline).max().orElse(0);
        bruteForceMaxProfit = 0;
        
        long startBF = System.nanoTime();
        jobSequencingBruteForce(jobs, 1, maxDeadline, 0, new boolean[jobs.size()]);
        long timeBF = System.nanoTime() - startBF;
        
        System.out.println("Maximum Profit: " + bruteForceMaxProfit + " (Time: " + timeBF + " ns)\n");

        // 2. Greedy Approach
        System.out.println("--- Greedy Approach ---");
        long startGreedy = System.nanoTime();
        jobSequencingGreedy(new ArrayList<>(jobs)); // Pass a copy to avoid sorting the original
        long timeGreedy = System.nanoTime() - startGreedy;
        System.out.println("(Time: " + timeGreedy + " ns)");
    }

    
    // Approach 1: Brute Force (Recursive Assignment)
    
    static int bruteForceMaxProfit = 0;

    public static void jobSequencingBruteForce(List<Job> jobs, int currentSlot, int maxSlot, int currentProfit, boolean[] used) {
        // Base case: We have evaluated all available time slots
        if (currentSlot > maxSlot) {
            bruteForceMaxProfit = Math.max(bruteForceMaxProfit, currentProfit);
            return;
        }

        jobSequencingBruteForce(jobs, currentSlot + 1, maxSlot, currentProfit, used);

     
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            
            
            if (!used[i] && job.deadline >= currentSlot) {
                used[i] = true; // Mark as used
                
                
                jobSequencingBruteForce(jobs, currentSlot + 1, maxSlot, currentProfit + job.profit, used);
                
                used[i] = false; 
            }
        }
    }


    // Approach 2: Greedy (Famous Approach)
    
    public static void jobSequencingGreedy(List<Job> jobs) {
      
        jobs.sort((a, b) -> b.profit - a.profit);

        
        int maxDeadline = 0;
        for (Job j : jobs) {
            maxDeadline = Math.max(maxDeadline, j.deadline);
        }

       
        Job[] slots = new Job[maxDeadline + 1];
        int totalProfit = 0;
        int jobsDone = 0;

        
        for (Job job : jobs) {
            
            for (int i = job.deadline; i > 0; i--) {
                if (slots[i] == null) {
                    
                    slots[i] = job;
                    totalProfit += job.profit;
                    jobsDone++;
                    break; 
                }
            }
        }

       
        System.out.print("Scheduled Jobs: ");
        for (int i = 1; i <= maxDeadline; i++) {
            if (slots[i] != null) {
                System.out.print(slots[i].id + " ");
            }
        }
        System.out.println("\nJobs Completed: " + jobsDone);
        System.out.println("Maximum Profit: " + totalProfit);
    }
}