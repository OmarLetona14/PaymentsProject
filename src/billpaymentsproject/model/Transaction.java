/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billpaymentsproject.model;

/**
 *
 * @author Omar
 */
public class Transaction {
    
    private int id;
    private int correlative;
    private String currentDate;
    private String currentTime;
    private double amount;

    public Transaction(int id, int correlative, String currentDate, String currentTime, double amount) {
        this.id = id;
        this.correlative = correlative;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrelative() {
        return correlative;
    }

    public void setCorrelative(int correlative) {
        this.correlative = correlative;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
 
}
