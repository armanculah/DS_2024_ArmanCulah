package homework3;

import java.util.Comparator;

public record Entry(String name, String streetAddress, String city, String postcode, String country,
                    String phoneNumber) implements Comparable<Entry> {

    @Override
    public String toString() {
        return
                "Name: " + name + '\n' +
                        "Street Address: " + streetAddress + '\n' +
                        "City: " + city + '\n' +
                        "Postcode: " + postcode + '\n' +
                        "Country: " + country + '\n' +
                        "Phone Number: " + phoneNumber;
    }

    @Override
    public int compareTo(Entry other) {
        return this.name.compareTo(other.name);
    }

}
