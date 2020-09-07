package Entity;

import DBConnection.MyUtils;

public class Coach {
    private int coachId;
    private String coachFIO;
    private int salary;

    public Coach() {
    }
    public Coach( String coachFIO, int salary) {

        this.coachFIO = coachFIO;
        this.salary = salary;
    }
    public Coach(int coachId, String coachFIO, int salary) {
        this.coachId = coachId;
        this.coachFIO = coachFIO;
        this.salary = salary;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getCoachFIO() {
        return coachFIO;
    }

    public void setCoachFIO(String coachFIO) {
        this.coachFIO = coachFIO;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
