package Entity;

public class Client {
    public String getClientFio() {
        return clientFio;
    }

    public void setClientFio(String clientFio) {
        this.clientFio = clientFio;
    }

    private String clientFio;
    private String phone;
    private int clientId;
    private int coachIdFk;
    private String coachName;


    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Client() {
    }
    public Client(String clientFio, String phone, int coachIdFk) {
        this. clientFio = clientFio;
        this.phone = phone;
        this.coachIdFk = coachIdFk;
    }
    public Client(int clientId, String clientFio, String phone, int coachIdFk) {
        this.clientFio = clientFio;
        this.phone = phone;
        this.clientId = clientId;
        this.coachIdFk = coachIdFk;

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCoachIdFk() {
        return coachIdFk;
    }

    public void setCoachIdFk(int coachIdFk) {
        this.coachIdFk = coachIdFk;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
