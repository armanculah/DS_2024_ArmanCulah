package homework4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SocialNetwork {

    HashMap<String, ArrayList<Friendship>> adj;

    public SocialNetwork() {
        this.adj = new HashMap<>();
    }

    public SocialNetwork(Scanner in) {
        this();
        in.nextLine();

        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (!line.contains(";")) continue;
            String[] parts = line.split(";");
            if (parts.length == 3) {
                String friend1 = parts[0].trim();
                String friend2 = parts[1].trim();
                int strength = Integer.parseInt(parts[2].trim());
                addFriendship(new Friendship(friend1, friend2, strength));
            }
        }
    }

    public void addUser(String user) {
        if (!adj.containsKey(user)) {
            adj.put(user, new ArrayList<>());
        }
    }

    public void addFriendship(Friendship f) {
        String friend1 = f.getFriend1();
        String friend2 = f.getFriend2();

        addUser(friend1);
        addUser(friend2);

        adj.get(friend1).add(f);
        adj.get(friend2).add(new Friendship(friend2, friend1, f.getFriendshipStrength()));
    }

    public ArrayList<FriendshipRecommendation> recommendFriends(String user) {
        if (!adj.containsKey(user)) {
            throw new IllegalArgumentException("User not found in the network");
        }

        Set<String> friends = new HashSet<>();
        HashMap<String, Integer> potentialFriends = new HashMap<>();

        for (Friendship f : adj.get(user)) {
            friends.add(f.getFriend2());
        }

        for (Friendship f : adj.get(user)) {
            for (Friendship ff : adj.get(f.getFriend2())) {
                if (!friends.contains(ff.getFriend2()) && !ff.getFriend2().equals(user)) {
                    int minStrength = Math.min(f.getFriendshipStrength(), ff.getFriendshipStrength());
                    potentialFriends.put(ff.getFriend2(), potentialFriends.getOrDefault(ff.getFriend2(), 0) + minStrength);
                }
            }
        }

        ArrayList<FriendshipRecommendation> recommendations = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : potentialFriends.entrySet()) {
            recommendations.add(new FriendshipRecommendation(entry.getKey(), entry.getValue()));
        }
        recommendations.sort(Collections.reverseOrder());
        return recommendations;
    }
}