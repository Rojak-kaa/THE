import java.io.*;
import java.util.*;

public class Inventory {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Item> items = new ArrayList<Item>();
    private String fileName = "inventory.txt";

    public Inventory() {
        loadFromFile(); // load items from file
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void showInventory() {
        System.out.println("1. Show All Items");
        System.out.println("2. Add Item");
        System.out.println("3. Remove Item");
        System.out.println("4. Restock Item");
        System.out.println("5. Update Item Price");

        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice) {
            case 1: displayItems();break;
            case 2: addItem(); break;
            case 3: removeItem(); break;
            case 4: restockItem(); break;
            case 5: updateItemPrice(); break;
            default: System.out.println("Invalid number!"); break;
        }
    }

    public void addItem() {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine(); // consume enter

        items.add(new Item(id, name, price, qty));
        saveToFile();

        System.out.println("Item added!");
    }

    public void removeItem() {
    System.out.print("Enter ID to remove: ");
    String id = sc.nextLine();

    Item item = findItem(id);

    if (item != null) {
        // remove from the list
        items.remove(item);

        // rewrite the file WITHOUT the removed item
        try (FileWriter fw = new FileWriter("inventory.txt")) { 
            for (Item i : items) {
                fw.write(i.toFileFormat() + System.lineSeparator());
            }
            System.out.println("Item removed successfully!");
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

    } else {
        System.out.println("Item not found!");
    }
}


    public void restockItem() {
        System.out.print("Enter ID to restock: ");
        String id = sc.nextLine();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        Item item = findItem(id);

        if (item != null) {
            item.increaseStock(qty);
            saveToFile();
            System.out.println("Item restocked!");
        } else {
            System.out.println("Item not found!");
        }
    }


   public void updateItemPrice() {
    System.out.print("Enter ID to update price: ");
    String id = sc.nextLine();

    Item item = findItem(id);

    if (item != null) {
        System.out.print("Enter new price: ");
        double price;
        try {
            price = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid price input!");
            return;
        }

        item.setPrice(price);

        // rewrite the file with all items (updated)
        try (FileWriter fw = new FileWriter("inventory.txt")) { // overwrite mode
            for (Item i : items) {
                fw.write(i.toFileFormat() + System.lineSeparator());
            }
            System.out.println("Price updated successfully!");
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }

    } else {
        System.out.println("Item not found!");
    }
}

    public Item findItem(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)) return i;
        }
        return null;
    }


    public void displayItems() {
         System.out.println("----------- Inventory -----------");

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line is formatted like: itemID,itemName,itemPrice,quantity
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String itemID = parts[0];
                    String itemName = parts[1];
                    String itemPrice = parts[2];
                    String quantity = parts[3];

                    System.out.printf("%-5s | %-20s | RM %6.2f%n", itemID, itemName, Double.parseDouble(itemPrice));

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found!");
        } catch (IOException e) {
            System.out.println("Error reading inventory file.");
        }
    }

    public void saveToFile() {
        try(FileWriter fw = new FileWriter(fileName,true)) {
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
