import java.io.*;
import java.util.*;

class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<String, Integer> rooms;

    public Inventory() {
        rooms = new HashMap<>();
        rooms.put("Single", 5);
        rooms.put("Double", 3);
        rooms.put("Suite", 2);
    }
}

class PersistenceService {

    private static final String FILE_NAME = "inventory.dat";

    public static void save(Inventory inventory) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");

        } catch (Exception e) {
            System.out.println("Error saving inventory.");
        }
    }

    public static Inventory load() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (Inventory) ois.readObject();

        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return new Inventory();
        }
    }
}

public class BookMyStayApp {
    public static void main (String[] args){
        System.out.println("System Recovery");

        // Load previous state (if exists)
        Inventory inventory = PersistenceService.load();

        // Display inventory
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.rooms.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Save current state
        PersistenceService.save(inventory);

    }
}
