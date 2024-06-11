package homework4;

public class Friendship {
    private final String friend1;
    private final String friend2;
    private final int friendshipStrength;

    public Friendship(String friend1, String friend2, int friendshipStrength) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.friendshipStrength = friendshipStrength;
    }
    public String getFriend1() {
        return friend1;
    }

    public String getFriend2() {
        return friend2;
    }

    public int getFriendshipStrength() {
        return friendshipStrength;
    }

}