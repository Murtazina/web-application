package Entity;

public class Accounting {
    private int subscriptionIdFk;
    private String month;
    private String paymentMade;
    private int clientIdFk;

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    private String clientLastName;
    public String getPaymentMade() {
        return paymentMade;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public int getSubscription() {
        return subscription;
    }

    public void setSubscription(int subscription) {
        this.subscription = subscription;
    }

    private String nameClient;
    private int subscription;
    public Accounting() {
    }

    public Accounting(int subscriptionIdFk, String month, String paymentMade, int clientIdFk) {
        this.subscriptionIdFk = subscriptionIdFk;
        this.month = month;
        this.paymentMade = paymentMade;
        this.clientIdFk = clientIdFk;
    }

    public int getSubscriptionIdFk() {
        return subscriptionIdFk;
    }

    public void setSubscriptionIdFk(int subscriptionIdFk) {
        this.subscriptionIdFk = subscriptionIdFk;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String isPaymentMade() {
        return paymentMade;
    }

    public void setPaymentMade(String paymentMade) {
        this.paymentMade = paymentMade;
    }

    public int getClientIdFk() {
        return clientIdFk;
    }

    public void setClientIdFk(int clientIdFk) {
        this.clientIdFk = clientIdFk;
    }
}
