package main;

import java.util.*;

/**
 * Book My Stay App
 *
 * Use Case 9: Error Handling & Validation
 *
 * Demonstrates validation and custom exception handling.
 *
 * @author Saksham
 * @version 9.0
 */

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public void validateRoom(String type) throws InvalidBookingException {
        if (!inventory.containsKey(type)) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }
    }

    public void validateAvailability(String type) throws InvalidBookingException {
        if (inventory.get(type) <= 0) {
            throw new InvalidBookingException("No availability for: " + type);
        }
    }

    public void bookRoom(String type) throws InvalidBookingException {
        validateRoom(type);
        validateAvailability(type);

        inventory.put(type, inventory.get(type) - 1);
        System.out.println("Booking successful for " + type);
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        try {
            inventory.bookRoom("Single Room");
            inventory.bookRoom("Suite Room"); // invalid
            inventory.bookRoom("Double Room");
            inventory.bookRoom("Double Room"); // no availability
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Application continues safely...");
    }
}