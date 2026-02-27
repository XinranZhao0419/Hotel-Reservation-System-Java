//Name: XinRan Zhao
//UCD ID:23219923
//BDIC ID:23374112

package ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import javafx.geometry.Insets;

import java.util.Optional;
import javafx.util.Pair;

public class HotelReservationUI extends Application {

    private Hotel hotel;
    private Stage primaryStage;  // Main window
    private Reservation currentReservation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        hotel = new Hotel();
        initializeRooms();  // Initialize rooms

        primaryStage.setTitle("Hotel Reservation");

        // Create main interface
        VBox mainMenu = createMainMenu();

        // Set layout for main interface
        Scene scene = new Scene(mainMenu, 600, 600);  // Resize window to four times the size
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeRooms() {
        // Standard Room
        hotel.addRoom(new Room(101, "Standard", "SINGLE"));
        hotel.addRoom(new Room(102, "Standard", "DOUBLE"));
        hotel.addRoom(new Room(103, "Standard", "SUITE"));

        // Deluxe Room
        hotel.addRoom(new Room(104, "Deluxe", "SINGLE"));
        hotel.addRoom(new Room(105, "Deluxe", "DOUBLE"));
        hotel.addRoom(new Room(106, "Deluxe", "SUITE"));

        // Suite Room
        hotel.addRoom(new Room(107, "Suite", "SINGLE"));
        hotel.addRoom(new Room(108, "Suite", "DOUBLE"));
        hotel.addRoom(new Room(109, "Suite", "SUITE"));
    }



    private VBox createMainMenu() {
        VBox menuBox = new VBox(20);
        menuBox.setAlignment(Pos.CENTER);  // Center align options
        menuBox.setStyle("-fx-background-color: #ADD8E6;"); // Set light blue background

        Button guestPanelButton = new Button("Guest Panel");
        Button adminPanelButton = new Button("Admin Panel");
        Button exitButton = new Button("Exit");

        String buttonStyle = "-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;";
        guestPanelButton.setStyle(buttonStyle);
        adminPanelButton.setStyle(buttonStyle);
        exitButton.setStyle("-fx-font-size: 18px; -fx-background-color: #FF5733; -fx-text-fill: white; -fx-background-radius: 8;");

        guestPanelButton.setOnAction(e -> openGuestPanel());
        adminPanelButton.setOnAction(e -> openAdminPanel());
        exitButton.setOnAction(e -> primaryStage.close());

        menuBox.getChildren().addAll(guestPanelButton, adminPanelButton, exitButton);
        return menuBox;
    }

    private void openGuestPanel() {
        VBox guestBox = new VBox(15);
        guestBox.setAlignment(Pos.CENTER);
        guestBox.setStyle("-fx-background-color: #ADD8E6;"); // Set light blue background

        Button viewRoomsButton = new Button("View Available Rooms");
        Button bookRoomButton = new Button("Book Room");
        Button viewReservationsButton = new Button("View Your Reservations");
        Button cancelReservationButton = new Button("Cancel Reservation");
        Button backButton = new Button("Back");

        String buttonStyle = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;";
        viewRoomsButton.setStyle(buttonStyle);
        bookRoomButton.setStyle(buttonStyle);
        viewReservationsButton.setStyle(buttonStyle);
        cancelReservationButton.setStyle(buttonStyle);
        backButton.setStyle("-fx-font-size: 16px; -fx-background-color: #FF5733; -fx-text-fill: white; -fx-background-radius: 8;");

        viewRoomsButton.setOnAction(e -> showAvailableRooms());
        bookRoomButton.setOnAction(e -> bookRoom());
        viewReservationsButton.setOnAction(e -> viewReservations());
        cancelReservationButton.setOnAction(e -> cancelReservation());
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createMainMenu(), 600, 600)));  // Return to main menu

        guestBox.getChildren().addAll(viewRoomsButton, bookRoomButton, viewReservationsButton, cancelReservationButton, backButton);

        primaryStage.setScene(new Scene(guestBox, 600, 600));
    }

    private void openAdminPanel() {
        VBox adminBox = new VBox(15);
        adminBox.setAlignment(Pos.CENTER);
        adminBox.setStyle("-fx-background-color: #ADD8E6;"); // Set light blue background

        Button addRoomButton = new Button("Add Room");
        Button viewAllRoomsButton = new Button("View All Rooms");
        Button deleteRoomButton = new Button("Delete Room");
        Button viewAllReservationsButton = new Button("View All Reservations");
        Button cancelAnyReservationButton = new Button("Cancel Reservation");
        Button backButton = new Button("Back");

        String buttonStyle = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #075563; -fx-text-fill: white; -fx-background-radius: 8;";
        addRoomButton.setStyle(buttonStyle);
        viewAllRoomsButton.setStyle(buttonStyle);
        deleteRoomButton.setStyle(buttonStyle);
        viewAllReservationsButton.setStyle(buttonStyle);
        cancelAnyReservationButton.setStyle(buttonStyle);
        backButton.setStyle("-fx-font-size: 16px; -fx-background-color: rgba(66,29,21,0.57); -fx-text-fill: white; -fx-background-radius: 8;");

        addRoomButton.setOnAction(e -> addRoom());
        viewAllRoomsButton.setOnAction(e -> showAllRooms());
        deleteRoomButton.setOnAction(e -> deleteRoom());
        viewAllReservationsButton.setOnAction(e -> viewAllReservations());
        cancelAnyReservationButton.setOnAction(e -> cancelAnyReservation());
        backButton.setOnAction(e -> primaryStage.setScene(new Scene(createMainMenu(), 600, 600)));  // Return to main menu

        adminBox.getChildren().addAll(addRoomButton, viewAllRoomsButton, deleteRoomButton, viewAllReservationsButton, cancelAnyReservationButton, backButton);

        primaryStage.setScene(new Scene(adminBox, 600, 600));
    }

    // Guest functions
    private void showAvailableRooms() {
        StringBuilder rooms = new StringBuilder("Available Rooms:\n");
        for (Room room : hotel.getAvailableRooms()) {
            rooms.append(room.toString()).append("\n");
        }
        showAlert("Available Rooms", rooms.toString());
    }


    private void bookRoom() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Book Room");
        dialog.setHeaderText("Enter room number to book and number of nights:");

        ButtonType bookButtonType = new ButtonType("Book", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(bookButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Room Number");
        TextField nightsField = new TextField();
        nightsField.setPromptText("Number of Nights");
        Label totalPriceLabel = new Label();

        grid.add(new Label("Room Number:"), 0, 0);
        grid.add(roomNumberField, 1, 0);
        grid.add(new Label("Number of Nights:"), 0, 1);
        grid.add(nightsField, 1, 1);
        grid.add(new Label("Total Price:"), 0, 2);
        grid.add(totalPriceLabel, 1, 2);

        nightsField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int roomNumber = Integer.parseInt(roomNumberField.getText().trim());
                int nights = Integer.parseInt(newValue.trim());
                Room room = hotel.findRoom(roomNumber);
                if (room != null && nights > 0) {
                    double price = room.getPrice() * nights;
                    if (nights > 7) {
                        price *= 0.8;  // Apply 20% discount for more than 7 nights
                    }
                    totalPriceLabel.setText("RMB " + price);
                } else {
                    totalPriceLabel.setText("");
                }
            } catch (NumberFormatException e) {
                totalPriceLabel.setText("");
            }
        });

        roomNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int roomNumber = Integer.parseInt(newValue.trim());
                int nights = Integer.parseInt(nightsField.getText().trim());
                Room room = hotel.findRoom(roomNumber);
                if (room != null && nights > 0) {
                    double price = room.getPrice() * nights;
                    if (nights > 7) {
                        price *= 0.8;  // Apply 20% discount for more than 7 nights
                    }
                    totalPriceLabel.setText("RMB " + price);
                } else {
                    totalPriceLabel.setText("");
                }
            } catch (NumberFormatException e) {
                totalPriceLabel.setText("");
            }
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == bookButtonType) {
                return new Pair<>(roomNumberField.getText(), nightsField.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(roomAndNights -> {
            int roomNumber = Integer.parseInt(roomAndNights.getKey().trim());
            int nights = Integer.parseInt(roomAndNights.getValue().trim());
            Room room = hotel.findRoom(roomNumber);
            if (room != null && room.isAvailable()) {
                double price = room.getPrice() * nights;
                if (nights > 7) {
                    price *= 0.8;  // Apply 20% discount for more than 7 nights
                }
                currentReservation = new Reservation(room, nights, price);
                room.setAvailable(false);
                hotel.addReservation(currentReservation);
                showAlert("Booking Successful", "Room booked successfully: " + room.toString() + "\nTotal Price: RMB " + price);
            } else {
                showAlert("Booking Failed", "Room is unavailable or does not exist!");
            }
        });
    }

    private void viewReservations() {
        if (currentReservation != null && hotel.getAllReservations().contains(currentReservation)) {
            showAlert("Your Reservation", currentReservation.toString());
        } else {
            showAlert("Your Reservation", "You have no reservations.");
            currentReservation = null;  // Reset current reservation
        }
    }

    private void cancelReservation() {
        if (currentReservation != null) {
            currentReservation.getRoom().setAvailable(true);
            hotel.removeReservation(currentReservation);
            currentReservation = null;
            showAlert("Cancel Reservation", "Your reservation has been canceled.");
        } else {
            showAlert("Cancel Reservation", "You have no reservations.");
        }
    }

    // Admin functions
    private void addRoom() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Room");
        dialog.setHeaderText("Enter room details:");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Room Number");

        ChoiceBox<String> roomTypeChoice = new ChoiceBox<>();
        roomTypeChoice.getItems().addAll("Standard", "Deluxe", "Suite");
        roomTypeChoice.setValue("Standard");

        ChoiceBox<String> roomSpecChoice = new ChoiceBox<>();
        roomSpecChoice.getItems().addAll("SINGLE", "DOUBLE", "SUITE");
        roomSpecChoice.setValue("SINGLE");

        grid.add(new Label("Room Number:"), 0, 0);
        grid.add(roomNumberField, 1, 0);
        grid.add(new Label("Room Type:"), 0, 1);
        grid.add(roomTypeChoice, 1, 1);
        grid.add(new Label("Room Spec:"), 0, 2);
        grid.add(roomSpecChoice, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Pair<>(roomNumberField.getText(), roomTypeChoice.getValue() + "," + roomSpecChoice.getValue());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(roomDetails -> {
            try {
                int roomNumber = Integer.parseInt(roomDetails.getKey().trim());
                String[] typeAndSpec = roomDetails.getValue().split(",");
                String roomType = typeAndSpec[0].trim();
                String roomSpec = typeAndSpec[1].trim();
                Room room = new Room(roomNumber, roomType, roomSpec);
                hotel.addRoom(room);
                showAlert("Room Added", "Room added successfully:\n" + room.toString());
            } catch (Exception e) {
                showAlert("Error", "Invalid input. Please try again.");
            }
        });
    }



    private void showAllRooms() {
        StringBuilder rooms = new StringBuilder("All Rooms:\n");
        for (Room room : hotel.getAllRooms()) {
            rooms.append(room.toString())
                    .append(" - ")
                    .append(room.isAvailable() ? "Available" : "Booked")
                    .append("\n");
        }
        showAlert("All Rooms", rooms.toString());
    }


    private void deleteRoom() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Delete Room");
        dialog.setHeaderText("Enter the room number to delete:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(roomNumber -> {
            boolean success = hotel.removeRoom(Integer.parseInt(roomNumber));
            if (success) {
                showAlert("Delete Successful", "Room deleted successfully.");
            } else {
                showAlert("Delete Failed", "Room does not exist or is booked, cannot delete.");
            }
        });
    }

    private void viewAllReservations() {
        StringBuilder reservations = new StringBuilder("All Reservations:\n");
        for (Reservation reservation : hotel.getAllReservations()) {
            reservations.append(reservation.toString()).append("\n");
        }
        showAlert("All Reservations", reservations.toString());
    }

    private void cancelAnyReservation() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cancel Reservation");
        dialog.setHeaderText("Enter the room number to cancel reservation:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(roomNumber -> {
            boolean success = hotel.cancelReservationByRoomNumber(Integer.parseInt(roomNumber));
            if (success) {
                showAlert("Cancel Successful", "Reservation canceled successfully.");
            } else {
                showAlert("Cancel Failed", "No reservation found for this room.");
            }
        });
    }

    // Display message box
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // 获取对话框的对话面板
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinWidth(400); // 设置宽度
        dialogPane.setMinHeight(300); // 设置高度
        dialogPane.setStyle("-fx-font-size: 14px;"); // 设置字体大小（可选）

        alert.showAndWait();
    }

}
