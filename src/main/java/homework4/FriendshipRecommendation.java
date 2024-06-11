package homework4;

public class FriendshipRecommendation implements Comparable<FriendshipRecommendation> {
    private String user;
    private int recommendationStrength;

    public FriendshipRecommendation(String user, int recommendationStrength) {
        this.user = user;
        this.recommendationStrength = recommendationStrength;
    }

    public String getUser() {
        return user;
    }

    public int getRecommendationStrength() {
        return recommendationStrength;
    }

    @Override
    public int compareTo(FriendshipRecommendation o) {
        return Integer.compare(this.recommendationStrength, o.recommendationStrength);
    }
}