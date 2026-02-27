package model;

public class Reservation {
    private Room room;
    private int days;
    private double totalCost;

    public Reservation(Room room, int days, double price) {
        this.room = room;
        this.days = days;
        calculateTotalCost();
    }

    private void calculateTotalCost() {
        totalCost = room.getPrice() * days;
        if (days > 7) {
            totalCost *= 0.8; // 20% discount
        }
    }

    public Room getRoom() { return room; }
    public int getDays() { return days; }
    public double getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return room.toString() + " - Days: " + days + " - Total Cost: RMB " + totalCost;
    }
}
