package main;

import java.io.*;
import java.util.*;

/**
 * Book My Stay App
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Demonstrates serialization & deserialization for persistence.
 *
 * @author Saksham
 * @version 12.0
 */

class DataStore implements Serializable {
    Map<String, Integer> inventory;
    List<String> bookings;

    DataStore(Map<String, Integer> inventory, List<String> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

class PersistenceService {
    private static final String FILE = "data.ser";

    public void save(DataStore data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(data);
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data.");
        }
    }

    public DataStore load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            System.out.println("Data loaded successfully.");
            return (DataStore) ois.readObject();
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        System.out.println("Updated by Saksham");
        PersistenceService ps = new PersistenceService();

        DataStore data = ps.load();

        Map<String, Integer> inventory;
        List<String> bookings;

        if (data == null) {
            inventory = new HashMap<>();
            inventory.put("Single Room", 2);
            bookings = new ArrayList<>();
        } else {
            inventory = data.inventory;
            bookings = data.bookings;
        }

        bookings.add("Booking1");
        inventory.put("Single Room", inventory.get("Single Room") - 1);

        System.out.println("Current Inventory: " + inventory);
        System.out.println("Bookings: " + bookings);

        ps.save(new DataStore(inventory, bookings));
    }
}