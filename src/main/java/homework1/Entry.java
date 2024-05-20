package homework1;

import java.util.Comparator;

public class Entry implements Comparable<Entry> {

    private String name;
    private String streetAddress;
    private String city;
    private String postcode;
    private String country;
    private String phoneNumber;

    public Entry(String name, String streetAddress, String city, String postcode, String country, String phoneNumber){
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

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

    public String getName() {
        return name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Comparator<Entry> getComparator(String attribute) {
        switch (attribute.toLowerCase()) {
            case "name":
                return new NameComparator();
            case "streetaddress":
                return new StreetAddressComparator();
            case "city":
                return new CityComparator();
            case "postcode":
                return new PostCodeComparator();
            case "country":
                return new CountryComparator();
            case "phonenumber":
                return new PhoneNumberComparator();
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }
    }

    private static class NameComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry o1, Entry o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    private static class StreetAddressComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry o1, Entry o2) {
            return o1.streetAddress.compareTo(o2.streetAddress);
        }
    }

    private static class CityComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry o1, Entry o2) {
            return o1.city.compareTo(o2.city);
        }
    }

    private static class PostCodeComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry o1, Entry o2) {
            return o1.postcode.compareTo(o2.postcode);
        }
    }

    private static class CountryComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry o1, Entry o2) {
            return o1.country.compareTo(o2.country);
        }
    }

    private static class PhoneNumberComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry o1, Entry o2) {
            return o1.phoneNumber.compareTo(o2.phoneNumber);
        }
    }
}
