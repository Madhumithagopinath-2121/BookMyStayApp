import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {

    private static final List<String> validRoomTypes =
            Arrays.asList("Single", "Double", "Suite");

    public static void validate(String guestName, String roomType, int availableRooms)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available.");
        }
    }
}
public class BookMyStayApp {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        int availableRooms = 2;

        try {
            System.out.println("Booking Validation");

            System.out.print("Enter guest name: ");
            String name = sc.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = sc.nextLine();

            BookingValidator.validate(name, roomType, availableRooms);

            availableRooms--;

            System.out.println("Booking successful for " + name + " in " + roomType + " room.");

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        sc.close();
    }
}
