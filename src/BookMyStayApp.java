import java.util.*;

/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
 * Represents a confirmed reservation.
 */
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


/**
 * ============================================================
 * CLASS - BookingHistory
 * ============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class maintains a record of confirmed reservations.
 * It provides ordered storage for historical and reporting
 * purposes.
 *
 * @version 8.0
 */
class BookingHistory {

    /**
     * List that stores confirmed reservations.
     */
    private List<Reservation> confirmedReservations;

    /**
     * Initializes an empty booking history.
     */
    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    /**
     * Adds a confirmed reservation to booking history.
     *
     * @param reservation confirmed booking
     */
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    /**
     * Returns all confirmed reservations.
     *
     * @return list of reservations
     */
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}


/**
 * ============================================================
 * CLASS - BookingReportService
 * ============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class generates reports from booking history data.
 * Reporting logic is separated from data storage.
 *
 * @version 8.0
 */
class BookingReportService {

    /**
     * Displays a summary report of all confirmed bookings.
     *
     * @param history booking history
     */
    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report");

        for (Reservation reservation : history.getConfirmedReservations()) {
            System.out.println(
                    "Guest: " + reservation.getGuestName()
                            + ", Room Type: " + reservation.getRoomType()
            );
        }
    }
}



public class BookMyStayApp {
    public static void main (String[] args) {
        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vannathi", "Suite"));

        BookingReportService reportService = new BookingReportService();

        System.out.println("Booking History and Reporting");

        reportService.generateReport(history);
    }
}
