import java.util.LinkedList;
import java.util.Queue;

/**
 * =========================================================
 * CLASS - Reservation
 * =========================================================
 * Represents a booking request made by a guest.
 */

class Reservation {

    /** Name of the guest making the booking */
    private String guestName;

    /** Requested room type */
    private String roomType;

    /**
     * Creates a new booking request
     */
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    /** Returns guest name */
    public String getGuestName() {
        return guestName;
    }

    /** Returns requested room type */
    public String getRoomType() {
        return roomType;
    }
}

/**
 * =========================================================
 * CLASS - BookingRequestQueue
 * =========================================================
 * Manages booking requests using FIFO queue
 */

class BookingRequestQueue {

    /** Queue storing booking requests */
    private Queue<Reservation> requestQueue;

    /** Constructor initializes queue */
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    /** Adds booking request to queue */
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    /** Retrieves and removes next request */
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    /** Checks if queue has pending requests */
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}



public class BookMyStayApp {
    public static void main (String[] args) {
        // Display header
        System.out.println("Booking Request Queue");

        // Create booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process requests in FIFO order
        while (bookingQueue.hasPendingRequests()) {

            Reservation request = bookingQueue.getNextRequest();

            System.out.println(
                    "Processing booking for Guest: "
                            + request.getGuestName()
                            + ", Room Type: "
                            + request.getRoomType()
            );
        }
    }
}
