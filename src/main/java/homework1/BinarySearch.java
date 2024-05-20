package homework1;

public class BinarySearch {
    public static int[] search(Entry[] entries, String searchableName) {
        int low = 0;
        int high = entries.length - 1;
        int[] indices = new int[2];

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (searchableName.compareTo(entries[mid].getName())<0){
                high = mid - 1;
            } else if (searchableName.compareTo(entries[mid].getName())>0) {
                low = mid + 1;
            } else {
                int beginning = mid;
                int end = mid;
                if(beginning!=0){
                    while (entries[beginning-1].compareTo(entries[beginning])==0){
                        beginning--;
                    }
                }

                if (end != entries.length-1){
                    while (entries[end+1].compareTo(entries[end])==0){
                        end++;
                    }
                }
                indices[0]=beginning;
                indices[1]=end;
                return indices;
            }
        }
        return new int[0];
    }
}