import java.util.*;

/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
 * Represents a booking request.
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
 * CLASS - BookingRequestQueue
 * ============================================================
 * Stores booking requests in FIFO order.
 */
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


/**
 * ============================================================
 * CLASS - RoomInventory
 * ============================================================
 * Maintains room availability.
 */
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    public void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public int getAvailability(String roomType) {
        return inventory.get(roomType);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }
}


/**
 * ============================================================
 * CLASS - RoomAllocationService
 * ============================================================
 * Handles safe room allocation.
 */
class RoomAllocationService {

    private Map<String, Integer> roomCounters = new HashMap<>();

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();

        if (!inventory.isAvailable(roomType)) {
            return;
        }

        int count = roomCounters.getOrDefault(roomType, 0) + 1;
        roomCounters.put(roomType, count);

        String roomId = roomType + "-" + count;

        inventory.decrement(roomType);

        System.out.println(
                "Booking confirmed for Guest: "
                        + reservation.getGuestName()
                        + ", Room ID: "
                        + roomId);
    }
}


/**
 * ============================================================
 * CLASS - ConcurrentBookingProcessor
 * ============================================================
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * This class represents a booking processor
 * that can be executed by multiple threads.
 *
 * It demonstrates how shared resources
 * such as booking queues and inventory
 * must be accessed in a thread-safe manner.
 *
 * @version 11.0
 */
class ConcurrentBookingProcessor implements Runnable {

    /** Shared booking request queue */
    private BookingRequestQueue bookingQueue;

    /** Shared room inventory */
    private RoomInventory inventory;

    /** Shared room allocation service */
    private RoomAllocationService allocationService;

    /**
     * Creates a new booking processor.
     */
    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService) {

        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    /**
     * Executes booking processing logic.
     */
    @Override
    public void run() {

        while (true) {

            Reservation reservation;

            /*
             Synchronize on the booking queue
             so only one thread retrieves
             a request at a time.
             */
            synchronized (bookingQueue) {

                if (bookingQueue.isEmpty()) {
                    break;
                }

                reservation = bookingQueue.getNextRequest();
            }

            /*
             Allocation also modifies inventory.
             Synchronization ensures safe updates.
             */
            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}



public class BookMyStayApp {
    public static void main (String[] args) {
        System.out.println("Concurrent Booking Simulation");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vannathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kunal", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        // Create booking processor threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService));

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService));

        // Start concurrent processing
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Display remaining inventory
        System.out.println("\nRemaining Inventory:");

        for (Map.Entry<String, Integer> entry :
                inventory.getInventory().entrySet()) {

            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
