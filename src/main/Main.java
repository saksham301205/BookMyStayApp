package main;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay App
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates FIFO using Queue for booking requests.
 *
 * @author Saksham
 * @version 5.0
 */

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + ", Room: " + roomType);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request added for " + r.guestName);
    }

    public void displayQueue() {
        System.out.println("\nCurrent Booking Queue:");
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("User1", "Single Room"));
        queue.addRequest(new Reservation("User2", "Double Room"));
        queue.addRequest(new Reservation("User3", "Suite Room"));

        queue.displayQueue();
    }
}