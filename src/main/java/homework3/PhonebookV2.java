package homework3;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhonebookV2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Loading the entries ");
        RedBlackTree tree;
        try {
            tree = FileUtils.readFile("src/main/java/homework3/raw_phonebook_data.csv");
        } catch (IOException e) {
            System.out.println("File not found.");
            return;
        }

        int[] edgeCounts = tree.countRedAndBlackEdges();
        System.out.println("System is ready.");
        System.out.println("Total red edges in the tree: " + edgeCounts[1]);
        System.out.println("Total black edges in the tree: " + edgeCounts[0]);

        while (true) {
            System.out.println("Enter a name or -1 to exit: ");
            String input = scanner.nextLine().trim();
            if (input.equals("-1")) {
                System.out.println("Goodbye");
                break;
            }

            ArrayList<Entry> entries = tree.get(input);
            if (entries != null) {
                System.out.println("Entries found: " + entries.size());
                for (Entry entry : entries) {
                    System.out.println(entry.toString());
                }
            } else {
                System.out.println("Entry not found.");
            }
        }
            try {
                tree.inorderTraversalAndSaveToFile("phonebook_data.csv");
            } catch (IOException e) {
                System.out.println("Error saving to file: " + e.getMessage());
            }
        }

    }
