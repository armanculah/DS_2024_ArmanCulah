package homework1;

import java.io.IOException;
import java.util.Scanner;

public class PhonebookV1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Loading the entries...");
        Entry[] entries = FileUtils.readFile("src/main/java/homework1/raw_phonebook_data.csv");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the attribute to sort by:");
        System.out.println("1. Name");
        System.out.println("2. Street Address");
        System.out.println("3. City");
        System.out.println("4. Postcode");
        System.out.println("5. Country");
        System.out.println("6. Phone Number");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String sortAttribute = "";
        if (choice == 1) {
            sortAttribute = "name";
        } else if (choice == 2) {
            sortAttribute = "streetaddress";
        } else if (choice == 3) {
            sortAttribute = "city";
        } else if (choice == 4) {
            sortAttribute = "postcode";
        } else if (choice == 5) {
            sortAttribute = "country";
        } else if (choice == 6) {
            sortAttribute = "phonenumber";
        } else {
            System.out.println("Invalid choice.");
            sortAttribute = "name";
        }

        System.out.println("Entries are sorted by " + sortAttribute + "...");
        MergeSort.sort(entries, Entry.getComparator(sortAttribute));

        System.out.println("Writing to file..");
        FileUtils.writeToFile(entries, "sorted_phonebook.csv");

        System.out.println("================= \n" + "System ready");

        while (true) {
            System.out.print("Value for search " + sortAttribute + ", or -1 to cancel: \n");
            String searchableValue = scanner.nextLine();

            if (searchableValue.equals("-1")) {
                System.out.println("Canceling the program...");
                break;
            }

            int[] result = BinarySearch.search(entries, searchableValue);
            if (result.length == 0) {
                System.out.println("No entries found for " + sortAttribute + ".");
            } else {
                int numOfEntries = result[1] - result[0] + 1;
                System.out.println(numOfEntries + " entries were found.");
                System.out.println("Entries found from index " + result[0] + " to " + result[1] + "\n");
                for (int i = result[0]; i <= result[1]; i++) {
                    System.out.println(entries[i] + "\n");
                }
            }
        }

        scanner.close();
    }
}
