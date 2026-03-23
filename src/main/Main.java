package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates read-only search using inventory.
 *
 * @author Saksham
 * @version 4.0
 */

abstract class Room {
    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    void display() {
        System.out.println(type + " - Price: " + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() { super("Single Room", 1000); }
}

class DoubleRoom extends Room {
    DoubleRoom() { super("Double Room", 2000); }
}

class SuiteRoom extends Room {
    SuiteRoom() { super("Suite Room", 5000); }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

class RoomSearchService {
    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void search(Room[] rooms) {
        for (Room r : rooms) {
            int available = inventory.getAvailability(r.type);
            if (available > 0) {
                r.display();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        RoomSearchService service = new RoomSearchService(inventory);
        service.search(rooms);
    }
}