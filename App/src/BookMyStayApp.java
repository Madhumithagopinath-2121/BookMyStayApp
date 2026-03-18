import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingSystem {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Integer> roomCounter = new HashMap<>();

    public BookingSystem() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        roomCounter.put("Single", 0);
        roomCounter.put("Double", 0);
        roomCounter.put("Suite", 0);
    }

    public synchronized void bookRoom(BookingRequest request) {

        String type = request.roomType;

        if (inventory.get(type) > 0) {

            int count = roomCounter.get(type) + 1;
            roomCounter.put(type, count);

            String roomId = type + "-" + count;

            inventory.put(type, inventory.get(type) - 1);

            System.out.println("Booking confirmed for Guest: "
                    + request.guestName + ", Room ID: " + roomId);

        } else {
            System.out.println("No rooms available for " + request.guestName);
        }
    }

    public void printInventory() {
        System.out.println("\nRemaining Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));
    }
}

class BookingThread extends Thread {

    private BookingSystem system;
    private BookingRequest request;

    public BookingThread(BookingSystem system, BookingRequest request) {
        this.system = system;
        this.request = request;
    }

    public void run() {
        system.bookRoom(request);
    }
}
public class BookMyStayApp {
    public static void main (String[] args){
        BookingSystem system = new BookingSystem();

        System.out.println("Concurrent Booking Simulation");

        List<BookingThread> threads = new ArrayList<>();

        threads.add(new BookingThread(system, new BookingRequest("Abhi", "Single")));
        threads.add(new BookingThread(system, new BookingRequest("Vanmathi", "Double")));
        threads.add(new BookingThread(system, new BookingRequest("Kural", "Suite")));
        threads.add(new BookingThread(system, new BookingRequest("Subha", "Single")));

        // Start all threads
        for (Thread t : threads) {
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        system.printInventory();
    }
}
