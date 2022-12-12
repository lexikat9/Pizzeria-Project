public class Customer {
    private String name;
    private boolean isRewardsMember;
    private double rewardsPoints;
    private long phoneNumber;
    private String email;

    // First constructor
    public Customer(String name) {
        this.name = name;
        isRewardsMember = false;
        rewardsPoints = 0.00;
    }

    // Second constructor
    public Customer(String name, long phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        isRewardsMember = true;
        rewardsPoints = 20.00;
    }

    public void addRewardsPoints(double points) {
        points += points;
    }

    public void useRewardsPoints(double points) {
        points -= points;
    }

    // All accessors for the fields
    public String getName() {
        return name;
    }

    public boolean getIsRewardsMember() {
        return isRewardsMember;
    }

    public double getRewardsPoints() {
        return rewardsPoints;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
