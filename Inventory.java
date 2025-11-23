import java.util.*;
import java.io.*;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<Item>();
    private String fileName = "inventory.txt";

    public Inventory() {
        loadFromFile(); // load items from file
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item findItem(String id) {
        for (Item i : items) {
            if (i.getItemId().equals(id)) return i;
        }
        return null;
    }

    public void addItem(Item item) {
        items.add(item);
        saveToFile();
    }

    public boolean removeItem(String id) {
        Item item = findItem(id);
        if(item != null) {
            items.remove(item);
            saveToFile();
            return true;
        }
        return false;
    }

    public void restockItem(String id, int qty) {
        Item item = findItem(id);
        if (item != null) {
            item.increaseStock(qty);
            System.out.println("Item restocked!");
        } else {
            System.out.println("Item not found!");
        }
    }

    public void updateItemPrice(String id, double price) {
        Item item = findItem(id);
        if (item != null) {
            item.setPrice(price);
            System.out.println("Price updated!");
        } else {
            System.out.println("Item not found!");
        }
    }

    public void displayItems() {
        System.out.println("--- Inventory ---");
        for (Item i : items) {
            System.out.println(i);
        }
    }

    public void saveToFile() {
        try(FileWriter fw = new FileWriter(fileName)) {
            for(Item i : items) {
                fw.write(i.toFileFormat() + System.lineSeparator());
            }
        } catch(Exception e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try {
            File file = new File(fileName);
            if(!file.exists()) return; // skip if no file yet
            Scanner fileScanner = new Scanner(file);
            items.clear();
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if(line.isEmpty()) continue;
                items.add(Item.fromFileFormat(line));
            }
            fileScanner.close();
        } catch(Exception e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}