package homework3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public ArrayList<Entry> get(String searchableName) {
        Node x = root;
        int redCount = 0, blackCount = 0;
        while (x != null) {
            if (searchableName.compareTo(x.key) < 0) {
                x = x.left;
                if (x != null && !isRed(x)) blackCount++;
            } else if (searchableName.compareTo(x.key) > 0) {
                x = x.right;
                if (x != null && !isRed(x)) blackCount++;
            } else {
                System.out.println("Red edges on the path: " + redCount);
                System.out.println("Black edges on the path: " + blackCount);
                return x.values;
            }
            if (x != null && isRed(x)) redCount++;
        }
        return null;
    }

    public void put(String searchableName, Entry entry) {
        root = put(root, searchableName, entry);
        root.color = BLACK;
    }

    private Node put(Node h, String key, Entry entry) {
        if (h == null) return new Node(key, entry, RED);

        if (key.compareTo(h.key) < 0) {
            h.left = put(h.left, key, entry);
        } else if (key.compareTo(h.key) > 0) {
            h.right = put(h.right, key, entry);
        } else {
            h.values.add(entry);
        }

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int[] countRedAndBlackEdges() {
        int[] counts = new int[2];
        countEdges(root, counts);
        return counts;
    }

    private void countEdges(Node x, int[] counts) {
        if (x == null) return;
        if (x.left != null) {
            counts[x.left.color ? 1 : 0]++;
            countEdges(x.left, counts);
        }
        if (x.right != null) {
            counts[x.right.color ? 1 : 0]++;
            countEdges(x.right, counts);
        }
    }

    public void inorderTraversalAndSaveToFile(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        inorderTraversalAndSaveToFile(root, writer);
        writer.close();
    }

    private void inorderTraversalAndSaveToFile(Node node, FileWriter writer) throws IOException {
        if (node == null) return;
        inorderTraversalAndSaveToFile(node.left, writer);
        for (Entry entry : node.values) {
            writer.write(entry.name() + ";" +
                    entry.streetAddress() + ";" +
                    entry.city() + ";" +
                    entry.postcode() + ";" +
                    entry.country() + ";" +
                    entry.phoneNumber() + "\n");
        }
        inorderTraversalAndSaveToFile(node.right, writer);
    }
}