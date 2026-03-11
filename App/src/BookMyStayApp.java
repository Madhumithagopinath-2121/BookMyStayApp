import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        }
    }

    public void displayInventory() {
        System.out.println("=== Current Room Inventory ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

public class BookMyStayApp {
    public static void main (String[] args){
        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println();

        System.out.println("Availability of Single Room: " + inventory.getAvailability("Single Room"));

        inventory.updateAvailability("Single Room", 4);

        System.out.println();
        System.out.println("After updating inventory:");

        inventory.displayInventory();
    }
}
