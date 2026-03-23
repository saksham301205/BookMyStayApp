package main;

import java.util.*;

/**
 * Book My Stay App
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Demonstrates FIFO processing, unique room allocation using Set,
 * and inventory update.
 *
 * @author Saksham
 * @version 6.0
 */

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decrease(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingService {
    private Queue<Reservation> queue = new LinkedList<>();
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private RoomInventory inventory;
    private int idCounter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void addRequest(Reservation r) {
        queue.add(r);
    }

    public void processBookings() {
        while (!queue.isEmpty()) {
            Reservation r = queue.poll();

            if (inventory.getAvailability(r.roomType) > 0) {

                String roomId = r.roomType.substring(0, 2).toUpperCase() + idCounter++;

                allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());

                if (!allocatedRooms.get(r.roomType).contains(roomId)) {
                    allocatedRooms.get(r.roomType).add(roomId);
                    inventory.decrease(r.roomType);

                    System.out.println("Booking confirmed for " + r.guestName +
                            " | Room: " + r.roomType +
                            " | ID: " + roomId);
                }

            } else {
                System.out.println("No rooms available for " + r.guestName);
            }
        }
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        service.addRequest(new Reservation("User1", "Single Room"));
        service.addRequest(new Reservation("User2", "Single Room"));
        service.addRequest(new Reservation("User3", "Single Room"));

        service.processBookings();
    }
}