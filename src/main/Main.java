package main;

import java.util.*;

/**
 * Book My Stay App
 *
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 *
 * Demonstrates synchronized booking to avoid race conditions.
 *
 * @author Saksham
 * @version 11.0
 */

class BookingSystem {
    private Map<String, Integer> inventory = new HashMap<>();

    public BookingSystem() {
        inventory.put("Single Room", 2);
    }

    public synchronized void book(String user) {
        int available = inventory.get("Single Room");

        if (available > 0) {
            System.out.println(user + " is booking...");
            inventory.put("Single Room", available - 1);
            System.out.println("Booking confirmed for " + user);
        } else {
            System.out.println("No rooms available for " + user);
        }
    }
}

class BookingThread extends Thread {
    private BookingSystem system;
    private String user;

    BookingThread(BookingSystem system, String user) {
        this.system = system;
        this.user = user;
    }

    public void run() {
        system.book(user);
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        Thread t1 = new BookingThread(system, "User1");
        Thread t2 = new BookingThread(system, "User2");
        Thread t3 = new BookingThread(system, "User3");

        t1.start();
        t2.start();
        t3.start();
    }
}