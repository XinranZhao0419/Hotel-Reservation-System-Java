package model;

public class Room {
    private int roomNumber;
    private String roomType; // 房型，例如：Deluxe, Suite, Standard
    private String roomSpec; // 规格，例如：SINGLE, DOUBLE, SUITE
    private double price;
    private boolean available;

    public Room(int roomNumber, String roomType, String roomSpec) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomSpec = roomSpec;
        this.price = calculatePrice(roomType, roomSpec);
        this.available = true;
    }

    private double calculatePrice(String roomType, String roomSpec) {
        double basePrice;
        switch (roomSpec.toUpperCase()) {
            case "SINGLE":
                basePrice = 500;
                break;
            case "DOUBLE":
                basePrice = 800;
                break;
            case "SUITE":
                basePrice = 1200;
                break;
            default:
                basePrice = 500; // 默认单人房价格
        }

        switch (roomType.toUpperCase()) {
            case "DELUXE":
                return basePrice * 3; // Deluxe房型价格
            case "SUITE":
                return basePrice * 1.5; // Suite房型价格
            case "STANDARD":
            default:
                return basePrice; // Standard房型价格
        }
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomSpec() {
        return roomSpec;
    }

    public double getPrice() {
        return price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + " - " + roomSpec + "), Price: RMB " + price;
    }
}
