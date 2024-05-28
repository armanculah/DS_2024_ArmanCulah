package homework2;

public class Process implements Comparable<Process> {
    private final String processName;
    private int priority;
    private int burstTime;
    private final int burstTimeConst;
    private final int arrivalTime;
    private int finishTime;

    public Process(String processName, int priority, int burstTime, int arrivalTime) {
        this.processName = processName;
        this.priority = priority;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.burstTimeConst = burstTime;
    }

    public String getProcessName() {
        return processName;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getBurstTimeConst() {
        return burstTimeConst;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    @Override
    public int compareTo(Process other) {
        if (this.priority > other.priority) {
            return 1;
        } else if (this.priority == other.priority) {
            return Integer.compare(this.arrivalTime, other.arrivalTime);
        } else {
            return -1;
        }
    }
}
