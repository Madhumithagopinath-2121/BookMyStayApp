import java.util.*;

class Reservation {
    private String reservationId;
    private String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }
}

class CancellationService {

    private Map<String, Integer> inventory;
    private Map<String, Reservation> activeBookings;
    private Stack<String> rollbackStack;

    public CancellationService() {
        inventory = new HashMap<>();
        activeBookings = new HashMap<>();
        rollbackStack = new Stack<>();

        // Initial inventory
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void addBooking(Reservation r) {
        activeBookings.put(r.getReservationId(), r);
    }

    public void cancelBooking(String reservationId) {

        if (!activeBookings.containsKey(reservationId)) {
            System.out.println("Invalid or already cancelled booking.");
            return;
        }

        Reservation r = activeBookings.get(reservationId);
        String roomType = r.getRoomType();

        // Rollback logic
        rollbackStack.push(reservationId);

        // Restore inventory
        inventory.put(roomType, inventory.get(roomType) + 1);

        // Remove booking
        activeBookings.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);

        // Print rollback history
        System.out.println("\nRollback History (Most Recent First):");
        for (int i = rollbackStack.size() - 1; i >= 0; i--) {
            System.out.println("Released Reservation ID: " + rollbackStack.get(i));
        }

        System.out.println("\nUpdated " + roomType + " Room Availability: " + inventory.get(roomType));
    }
}
public class BookMyStayApp {
    public static void main (String[] args){
        CancellationService service = new CancellationService();

        // Existing booking (simulate confirmed booking)
        Reservation r1 = new Reservation("Single-1", "Single");

        service.addBooking(r1);

        System.out.println("Booking Cancellation");

        // Cancel booking
        service.cancelBooking("Single-1");
    }
}
