package homework1;

import java.util.Comparator;

public class MergeSort {
    public static void sort(Entry[] entries, Comparator<Entry> comparator) {
        System.out.println("Sorting the entries...");
        Entry[] aux = new Entry[entries.length];
        sort(entries, aux, 0, entries.length - 1, comparator);
    }

    private static void sort(Entry[] elements, Entry[] aux, int low, int high, Comparator<Entry> comparator) {
        if (high <= low) {
            return;
        }

        int mid = low + (high - low) / 2;
        sort(elements, aux, low, mid, comparator);
        sort(elements, aux, mid + 1, high, comparator);
        merge(elements, aux, low, mid, high, comparator);
    }


    private static void merge(Entry[] elements, Entry[] aux, int low, int mid, int high, Comparator<Entry> comparator) {
        for (int k = low; k <= high; k++) {
            aux[k] = elements[k];
        }

        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                elements[k] = aux[j++];
            } else if (j > high) {
                elements[k] = aux[i++];
            } else if (comparator.compare(aux[j], aux[i]) < 0) {
                elements[k] = aux[j++];
            } else {
                elements[k] = aux[i++];
            }
        }
    }
}