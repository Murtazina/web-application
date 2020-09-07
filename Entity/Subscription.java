package Entity;

public class Subscription {
 private int subscriptionId;
 private int price;
 private String description;
 private int hallIdFk;

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    private String hallName;
    public Subscription() {
    }
    public Subscription(int price, String description, int hallIdFk) {
         this.price = price;
        this.description = description;
        this.hallIdFk = hallIdFk;
    }
    public Subscription(int subscriptionId, int price, String description, int hallIdFk) {
        this.subscriptionId = subscriptionId;
        this.price = price;
        this.description = description;
        this.hallIdFk = hallIdFk;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHallIdFk() {
        return hallIdFk;
    }

    public void setHallIdFk(int hallIdFk) {
        this.hallIdFk = hallIdFk;
    }
}
