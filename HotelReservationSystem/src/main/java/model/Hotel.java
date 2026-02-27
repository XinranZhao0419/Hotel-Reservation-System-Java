package model;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    // Add a room to the list
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Find a room by room number
    public Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null; // Return null if the room is not found
    }

    // Get a list of all rooms
    public List<Room> getAllRooms() {
        return rooms;
    }

    // Get a list of all available rooms
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // Remove a room by room number if it is available
    public boolean removeRoom(int roomNumber) {
        Room room = findRoom(roomNumber);
        if (room != null && room.isAvailable()) {
            rooms.remove(room);
            return true;
        }
        return false;
    }

    // Add a reservation
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // Remove a reservation
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    // Cancel a reservation by room number
    public boolean cancelReservationByRoomNumber(int roomNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == roomNumber) {
                reservations.remove(reservation);
                reservation.getRoom().setAvailable(true); // Set room as available
                return true;
            }
        }
        return false;
    }

    // Get a list of all reservations
    public List<Reservation> getAllReservations() {
        return reservations;
    }

    // Find a reservation by room number
    public Reservation findReservationByRoom(int roomNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == roomNumber) {
                return reservation;
            }
        }
        return null;
    }
}
