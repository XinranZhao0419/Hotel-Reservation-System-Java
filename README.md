# Hotel Reservation System in Java 🏨

This project implements a fully functional Hotel Reservation System using Java and Object-Oriented Programming principles. The system provides an interactive graphical user interface built with JavaFX and supports both Guest and Admin operations.

The implementation follows a structured architecture separating data models, business logic, and user interface components.

---

## 📦 Repository Contents

This repository contains the complete project implementation and supporting materials.

### 💻 Source Code

The full Java source code is provided in the `src` folder.

It includes the core system components:

- Hotel.java — system core logic and management
- Room.java — room data model
- Reservation.java — reservation data model and pricing logic
- HotelReservationUI.java — graphical user interface implemented using JavaFX

These classes together implement the system functionality and user interaction.

---

### 📄 Technical Report

A detailed configuration and implementation report is included in the `report` directory:

```
report/configuration.pdf
```

The report explains:

- System architecture design
- Class structure and relationships
- Algorithm implementation
- Object-oriented design decisions
- MVC architecture implementation

---

### 🎥 Demonstration Video

This repository also includes a demonstration video showing the system in operation.

The video demonstrates:

- User interface interaction
- Room booking workflow
- Reservation management
- Admin panel functionality

The video provides a real execution demonstration of the system.

---

## 🖥️ Execution Environment

This project requires the following environment:

### Operating System

- Windows
- macOS
- Linux

### Java Version

- Java 11 or later

### Required Library

- JavaFX

---

## ▶️ How to Run

### Step 1: Compile

Navigate to the src folder:

```bash
javac *.java
```

### Step 2: Run

```bash
java HotelReservationUI
```

---

## 🎯 System Functionality

The system provides two main user roles:

### Guest Functions

- View available rooms
- Book rooms
- View reservations
- Cancel reservations

### Admin Functions

- Add rooms
- View all rooms
- Delete rooms
- View all reservations
- Cancel reservations

---

## ✨ Project Highlights

### Object-Oriented Design

The system is built using core OOP principles:

- Encapsulation
- Inheritance
- Polymorphism

Each class represents a logical entity in the system.

---

### MVC Architecture

The system follows the Model-View-Controller pattern:

Model:

- Hotel
- Room
- Reservation

View and Controller:

- HotelReservationUI

This separation improves maintainability and scalability.

---

### JavaFX Graphical Interface

The project uses JavaFX to build a graphical user interface.

This allows:

- Interactive user experience
- Real-time system interaction

---

### Reservation Cost Algorithm

The system calculates reservation costs based on:

- Room type
- Number of days

If booking exceeds 7 days, a 20% discount is automatically applied.

---

### Room Management System

The system supports:

- Dynamic room creation
- Availability tracking
- Reservation linking

---

## 📁 Project Structure

```
Hotel-Reservation-System-Java
│
├── README.md
│
├── src
│   ├── Hotel.java
│   ├── Room.java
│   ├── Reservation.java
│   ├── HotelReservationUI.java
│
├── report
│   └── configuration.pdf
│
├── demo
│   └── video.mp4
```

---

## 🚀 Summary

This project demonstrates the implementation of a structured hotel management system using Java.

It integrates:

- Object-Oriented Programming
- Graphical User Interface
- Reservation logic
- Structured architecture

The repository includes the complete source code, technical report, and system demonstration video, providing a full representation of the system design, implementation, and functionality.
