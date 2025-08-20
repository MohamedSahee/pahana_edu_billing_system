package model;

import java.time.LocalDateTime;

public class Bill {
    private int id;
    private int customerId;
    private LocalDateTime createdAt;
    private double total;

    public Bill() {}
    public Bill(int id, int customerId, LocalDateTime createdAt, double total) {
        this.id = id;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.total = total;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
