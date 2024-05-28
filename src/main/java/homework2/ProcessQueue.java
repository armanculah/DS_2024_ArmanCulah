package homework2;

public class ProcessQueue {
    private Process[] pq = new Process[2];
    public int length = 0;

    public void addProcess(Process process) { //adds to the priority queue (insert)
        if (pq.length == length + 1) {
            resize(2 * pq.length);
        }
        pq[++length] = process;
        swim(length);
    }

    public Process runNextProcess() { //returns the next priority process and removes it
        if (length == 0) {
            return null;
        }
        Process min = pq[1];
        swap(1, length--);
        pq[length + 1] = null;

        if (length > 0 && length == pq.length / 4) {
            resize(pq.length / 2);
        }

        sink(1);
        return min;
    }

    public Process peekNextProcess() { //returns the next-priority process, but doesnt remove it.

        if (length == 0) {
            return null;
        }
        return pq[1];
    }

    private void swim(int k) { //taken fr
        while (k > 1 && less(k, k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }


    /* sink down a node to its correct position */
    private void sink(int k) {
        while (2 * k <= length) {
            int j = 2 * k;
            if (j < length && less(j + 1, j)) {
                j++;
            }
            if (!less(j, k)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    /* Swap the array elements at provided indexes */
    private void swap(int i, int j) {
        Process temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }


    /* Check which of the two elements is smaller */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /* Create a new internal array with a given capacity */
    private void resize(int capacity) {
        Process[] copy = new Process[capacity];
        for (int i = 1; i <= length; i++) {
            copy[i] = pq[i];
        }
        pq = copy;
    }

    /* Check if the priority queue is empty */
    public boolean isEmpty() {
        return length == 0;
    }
}
