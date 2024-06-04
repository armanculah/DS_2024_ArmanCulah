package homework3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    public static RedBlackTree readFile(String filePath) throws IOException {
        RedBlackTree tree = new RedBlackTree();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            String name = parts[0].trim();
            String streetAddress = parts[1].trim();
            String city = parts[2].trim();
            String postcode = parts[3].trim();
            String country = parts[4].trim();
            String phoneNumber = parts[5].trim();
            Entry entry = new Entry(name, streetAddress, city, postcode, country, phoneNumber);
            tree.put(entry.name(), entry);
        }
        br.close();

        return tree;
    }
}