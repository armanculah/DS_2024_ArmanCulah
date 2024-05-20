package homework1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static Entry[] readFile(String filePath) throws IOException {
        List<Entry> entries = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String name = parts[0].trim();
            String streetAddress = parts[1].trim();
            String city = parts[2].trim();
            String postcode = parts[3].trim();
            String country = parts[4].trim();
            String phoneNumber = parts[5].trim();
            Entry entry = new Entry(name, streetAddress, city, postcode, country, phoneNumber);
            entries.add(entry);
        }
        reader.close();

        return entries.toArray(new Entry[0]);
    }

    public static void writeToFile(Entry[] entries, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Entry entry : entries) {
            writer.write(entry.toString());
            writer.newLine();
        }

        writer.close();
    }
}
