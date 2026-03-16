import java.util.HashMap;
import java.util.Map;


class Room {

    private String type;
    private int beds;
    private int size;
    private double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
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

    public double getPrice() {
        return price;
    }
}

class RoomInventory {


    private Map<String, Integer> roomAvailability;


    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }


    private void initializeInventory() {

        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);

    }


    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }


    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

public class BookMyStayApp {
    public static void main (String[] args) {
        RoomInventory inventory = new RoomInventory();

        Room singleRoom = new Room("Single", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0);
        Room suiteRoom = new Room("Suite", 3, 750, 5000.0);

        System.out.println("Hotel Room Inventory Status\n");

        printRoom(singleRoom, inventory);
        printRoom(doubleRoom, inventory);
        printRoom(suiteRoom, inventory);
    }

    private static void printRoom(Room room, RoomInventory inventory) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println(room.getType() + " Room:");
        System.out.println("Beds: " + room.getBeds());
        System.out.println("Size: " + room.getSize() + " sqft");
        System.out.println("Price per night: " + room.getPrice());
        System.out.println("Available Rooms: " + availability.get(room.getType()));
        System.out.println();
    }
}
