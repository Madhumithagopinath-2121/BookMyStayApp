import java.util.*;

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decreaseRoom(String roomType) {
        int count = inventory.get(roomType);
        inventory.put(roomType, count - 1);
    }
}

class BookingService {

    private Queue<Reservation> requestQueue;
    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms;
    private int roomCounter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        requestQueue = new LinkedList<>();
        allocatedRooms = new HashMap<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public void processBookings() {

        while (!requestQueue.isEmpty()) {

            Reservation request = requestQueue.poll();
            String type = request.getRoomType();

            if (inventory.getAvailability(type) > 0) {

                String roomId = type.replace(" ", "").toUpperCase() + "-" + roomCounter++;

                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                inventory.decreaseRoom(type);

                System.out.println("Reservation Confirmed for " + request.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Assigned Room ID: " + roomId);
                System.out.println();

            } else {

                System.out.println("Reservation Failed for " + request.getGuestName());
                System.out.println("No available rooms for: " + type);
                System.out.println();
            }
        }
    }
}


public class BookMyStayApp {
    public static void main (String[] args){
        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);

        System.out.println("Room Allocation Process");

        bookingService.addRequest(new Reservation("Abhi", "Single Room"));
        bookingService.addRequest(new Reservation("Subha", "Double Room"));
        bookingService.addRequest(new Reservation("Vanmathi", "Single Room"));
        bookingService.addRequest(new Reservation("Madhu", "Suite Room"));

        bookingService.processBookings();
    }
}
