package homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        String filePath = "/Users/armanculah/Documents/OCD/IBU/GitHub/DS/FENMS-034-master/src/main/java/homework4/social_network.csv";


        Scanner fileScanner = new Scanner(new File(filePath));

        SocialNetwork socialNetwork = new SocialNetwork(fileScanner);

        System.out.println("Total: " + socialNetwork.adj.size());
        int totalFriendships = socialNetwork.adj.values().stream().mapToInt(ArrayList::size).sum() / 2;

        System.out.println("Total friendships: " + totalFriendships);




        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a name to recommend friends for or -1 to exit: ");
            String input = inputScanner.nextLine();


            if (input.equals("-1")) {
                break;
            }

            try {
                ArrayList<FriendshipRecommendation> recommendations = socialNetwork.recommendFriends(input);
                System.out.println("Total recommendations: " + recommendations.size());
                System.out.println("Top 10 recommendations based on friendship strength:");

                for (int i = 0; i < Math.min(10, recommendations.size()); i++) {
                    FriendshipRecommendation rec = recommendations.get(i);
                    System.out.println((i + 1) + ". " + rec.getUser() + ": " + rec.getRecommendationStrength());
                }

            } catch (IllegalArgumentException e) {
                System.out.println("User"+ input +"not found in the network.");
            }
        }
    }
}