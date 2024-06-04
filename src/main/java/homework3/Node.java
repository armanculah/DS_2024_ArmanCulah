package homework3;

import java.util.ArrayList;

public class Node {
    String key;
    ArrayList<Entry> values;
    Node left, right;
    boolean color;

    public Node(String key, Entry value, boolean color) {
        this.key = key;
        this.values = new ArrayList<>();
        this.values.add(value);
        this.left = this.right = null;
        this.color = color;
    }
}