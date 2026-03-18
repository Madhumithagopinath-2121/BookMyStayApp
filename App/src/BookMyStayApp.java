import java.util.*;

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double price;

    public Reservation(String reservationId, String guestName, String roomType, double price) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.price = price;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }
}

class BookingHistory {
    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

class BookingReportService {

    public void printAllBookings(List<Reservation> reservations) {
        System.out.println("\nBooking History and Reporting");
        System.out.println("\nBooking History Report:");
        for (Reservation r : reservations) {
            System.out.println("ID: " + r.getReservationId() +
                    ", Guest: " + r.getGuestName() +
                    ", Room: " + r.getRoomType() +
                    ", Price: ₹" + r.getPrice());
        }
    }

    public double calculateTotalRevenue(List<Reservation> reservations) {
        double total = 0;
        for (Reservation r : reservations) {
            total += r.getPrice();
        }
        return total;
    }

    public int totalBookings(List<Reservation> reservations) {
        return reservations.size();
    }
}
public class BookMyStayApp {
    public static void main (String[] args){
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        Reservation r1 = new Reservation("101", "Abhi", "Single", 3000);
        Reservation r2 = new Reservation("102", "Subha", "Double", 5000);
        Reservation r3 = new Reservation("103", "Vanmathi", "Suite", 2000);

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        List<Reservation> allBookings = history.getAllReservations();

        reportService.printAllBookings(allBookings);

        System.out.println("\nTotal Bookings: " + reportService.totalBookings(allBookings));
        System.out.println("Total Revenue: ₹" + reportService.calculateTotalRevenue(allBookings));
    }
}
