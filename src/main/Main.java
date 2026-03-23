package main;

import java.util.*;

/**
 * Book My Stay App
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Demonstrates rollback using Stack and inventory restoration.
 *
 * @author Saksham
 * @version 10.0
 */

class BookingManager {
    private Map<String, String> bookings = new HashMap<>(); // bookingId -> roomType
    private Map<String, Integer> inventory = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();
    private int idCounter = 1;

    public BookingManager() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
    }

    public String book(String roomType) {
        if (inventory.getOrDefault(roomType, 0) > 0) {
            String bookingId = "B" + idCounter++;
            bookings.put(bookingId, roomType);
            inventory.put(roomType, inventory.get(roomType) - 1);

            System.out.println("Booked: " + bookingId + " (" + roomType + ")");
            return bookingId;
        } else {
            System.out.println("No availability for " + roomType);
            return null;
        }
    }

    public void cancel(String bookingId) {
        if (!bookings.containsKey(bookingId)) {
            System.out.println("Invalid booking ID");
            return;
        }

        String roomType = bookings.get(bookingId);

        rollbackStack.push(bookingId); // track rollback
        inventory.put(roomType, inventory.get(roomType) + 1);
        bookings.remove(bookingId);

        System.out.println("Cancelled: " + bookingId + " (" + roomType + ")");
    }

    public void showInventory() {
        System.out.println("\nInventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {

        BookingManager manager = new BookingManager();

        String b1 = manager.book("Single Room");
        String b2 = manager.book("Double Room");

        manager.showInventory();

        manager.cancel(b1);

        manager.showInventory();

        manager.cancel("B999"); // invalid
    }
}