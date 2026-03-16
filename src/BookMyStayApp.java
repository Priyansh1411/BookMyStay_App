import java.util.HashMap;
import java.util.Map;

/**
 * DOMAIN CLASS - Room
 * Represents a room type and its attributes
 */
class Room {

    private String type;
    private int beds;
    private int size;
    private double pricePerNight;

    public Room(String type, int beds, int size, double pricePerNight) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.pricePerNight = pricePerNight;
    }

    public String getType() {
        return type;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}


/**
 * STATE HOLDER - RoomInventory
 * Maintains room availability counts
 */
class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
    }

    public void setAvailability(String roomType, int count) {
        availability.put(roomType, count);
    }

    public Map<String, Integer> getRoomAvailability() {
        return availability;
    }
}


/**
 * SERVICE CLASS - RoomSearchService
 * Provides read-only search functionality
 */
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        // Single Room
        if (availability.get("Single") > 0) {
            System.out.println("Single Room:");
            System.out.println("Beds: " + singleRoom.getBeds());
            System.out.println("Size: " + singleRoom.getSize() + " sqft");
            System.out.println("Price per night: " + singleRoom.getPricePerNight());
            System.out.println("Available: " + availability.get("Single"));
            System.out.println();
        }

        // Double Room
        if (availability.get("Double") > 0) {
            System.out.println("Double Room:");
            System.out.println("Beds: " + doubleRoom.getBeds());
            System.out.println("Size: " + doubleRoom.getSize() + " sqft");
            System.out.println("Price per night: " + doubleRoom.getPricePerNight());
            System.out.println("Available: " + availability.get("Double"));
            System.out.println();
        }

        // Suite Room
        if (availability.get("Suite") > 0) {
            System.out.println("Suite Room:");
            System.out.println("Beds: " + suiteRoom.getBeds());
            System.out.println("Size: " + suiteRoom.getSize() + " sqft");
            System.out.println("Price per night: " + suiteRoom.getPricePerNight());
            System.out.println("Available: " + availability.get("Suite"));
            System.out.println();
        }
    }
}


public class BookMyStayApp {
    public static void main (String[] args) {
        // Create Room Objects
        Room singleRoom = new Room("Single", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0);
        Room suiteRoom = new Room("Suite", 3, 750, 5000.0);

        // Create Inventory
        RoomInventory inventory = new RoomInventory();

        // Set Availability
        inventory.setAvailability("Single", 5);
        inventory.setAvailability("Double", 3);
        inventory.setAvailability("Suite", 2);

        // Create Search Service
        RoomSearchService searchService = new RoomSearchService();

        // Perform Search
        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}
