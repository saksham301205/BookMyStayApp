package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates use of HashMap for centralized inventory.
 *
 * @author Saksham
 * @version 3.0
 */

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        for (String type : inventory.keySet()) {
            System.out.println(type + " Available: " + inventory.get(type));
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nUpdating Single Room count...\n");
        inventory.updateAvailability("Single Room", 4);

        inventory.displayInventory();
    }
}