package main;

import java.util.*;

/**
 * Book My Stay App
 *
 * Use Case 7: Add-On Service Selection
 *
 * Demonstrates mapping of reservation to multiple services.
 *
 * @author Saksham
 * @version 7.0
 */

class AddOnService {
    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    public double getTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());

        for (AddOnService s : services) {
            total += s.cost;
        }
        return total;
    }

    public void displayServices(String reservationId) {
        List<AddOnService> services = serviceMap.getOrDefault(reservationId, new ArrayList<>());

        System.out.println("Services for Reservation " + reservationId + ":");
        for (AddOnService s : services) {
            System.out.println(s.name + " - " + s.cost);
        }
        System.out.println("Total Add-on Cost: " + getTotalCost(reservationId));
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Spa", 1500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 800));

        manager.displayServices(reservationId);
    }
}