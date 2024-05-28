package homework2;

import java.util.ArrayList;
import java.util.Scanner; //for user input in main

public class Scheduler {
    public static void scheduleAndRun(ArrayList<Process> processList) {
        int currentTime = 0; // Tracks the current time unit
        ProcessQueue processQueue = new ProcessQueue(); // Priority queue for processes
        Process runningProcess = null; // Currently running process
        int accumulatedWaitingTime = 0; // Sum of all waiting times
        int totalProcesses = processList.size(); // Total number of processes

        // Loop until all processes are processed
        while (!processList.isEmpty() || runningProcess != null || processQueue.length > 0) {
            // Collect processes arriving at the current time
            ArrayList<Process> arrivingProcesses = new ArrayList<>();
            for (Process process : processList) {
                if (process.getArrivalTime() == currentTime) {
                    processQueue.addProcess(process); // Add process to the queue
                    arrivingProcesses.add(process); // Track added processes
                }
            }
            processList.removeAll(arrivingProcesses); // Remove added processes from the list

            // Preempt the current process if a higher priority process is available
            if (runningProcess != null && processQueue.length > 0 && processQueue.peekNextProcess().getPriority() < runningProcess.getPriority()) {
                processQueue.addProcess(runningProcess); // Requeue the current process
                runningProcess = processQueue.runNextProcess(); // Run the higher priority process
            }

            // Start a new process if no process is currently running
            if (runningProcess == null && processQueue.length > 0) {
                runningProcess = processQueue.runNextProcess(); // Run the next process from the queue
            }

            // Run the current process for one time unit
            if (runningProcess != null) {
                System.out.println("Time: " + currentTime + "    " + runningProcess.getProcessName()); // Print the current process
                runningProcess.setBurstTime(runningProcess.getBurstTime() - 1); // Decrease burst time by 1
                if (runningProcess.getBurstTime() == 0) {
                    runningProcess.setFinishTime(currentTime + 1); // Set finish time of the process
                    int waitingTime = runningProcess.getFinishTime() - runningProcess.getArrivalTime() - runningProcess.getBurstTimeConst();
                    accumulatedWaitingTime += waitingTime; // Accumulate the waiting time
                    runningProcess = null; // Reset current process
                }
            } else {
                System.out.println("Time: " + currentTime + "    READY"); // Print READY if no process is running
            }

            currentTime++; // Increment the current time
        }

        // Print total time and average waiting time
        System.out.println("Total execution time: " + currentTime);
        double averageWaitTime = (double) accumulatedWaitingTime / totalProcesses;
        System.out.println("Average waiting time: " + averageWaitTime);
    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<>();

        System.out.println("Enter the number of proceses:");
        int numberOfProcceses = scanner.nextInt();

        for (int i = 1; i <= numberOfProcceses; i++) {
            System.out.println("Enter the priority for P" + i + ":");
            int priority = scanner.nextInt();
            System.out.println("Enter the burst time for P" + i + ":");
            int burstTime = scanner.nextInt();
            System.out.println("Enter the arrival time for P" + i + ":");
            int arrivalTime = scanner.nextInt();

            processes.add(new Process("P" + i, priority, burstTime, arrivalTime));
        }
        scheduleAndRun(processes);
}
}

