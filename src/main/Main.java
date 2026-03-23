package main;

import java.util.*;

/**
 * Book My Stay App
 *
 * Use Case 8: Booking History & Reporting
 *
 * Demonstrates storing booking history and generating reports.
 *
 * @author Saksham
 * @version 8.0
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

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void add(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAll() {
        return history;
    }
}

class BookingReportService {
    public void generateReport(List<Reservation> history) {
        System.out.println("Booking History Report:\n");

        for (Reservation r : history) {
            r.display();
        }

        System.out.println("\nTotal Bookings: " + history.size());
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.add(new Reservation("User1", "Single Room"));
        history.add(new Reservation("User2", "Double Room"));
        history.add(new Reservation("User3", "Suite Room"));

        BookingReportService report = new BookingReportService();
        report.generateReport(history.getAll());
    }
}