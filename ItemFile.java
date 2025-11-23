import java.io.*;
import java.util.*;



public class ItemFile {
    

    Scanner sc = new Scanner (System.in);
    private String itemID;
    private boolean found;
    private String itemName;
    private int itemStock;
    private double itemPrice;
    private int userChoice;
    private int newItemStock;

    Order order = new Order();

    public void addStock()
    {

        System.out.println("=================================");
        System.out.println("           Add Item              ");
        System.out.println("=================================");
        System.out.println("Which item you want to add stock?");
        System.out.print("Item Id:");
        itemID=sc.next();

   while(true) {
        System.out.print("Item ID: ");
        itemID = sc.nextLine().trim();

        found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("InventoryList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String currentID = parts[0].trim();
                    String currentName = parts[1].trim();
                    int stock = Integer.parseInt(parts[2].trim());
                    double price = Double.parseDouble(parts[3]);

                    if (currentID.equalsIgnoreCase(itemID)) {
                        found = true;
                        itemStock = stock;
                        itemName = currentName;
                        itemPrice = price;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file.");
            return;
        }

        if (found) {
            System.out.println("Item found: " + itemName  );
            System.out.println("Stock: "+ itemStock);
            break;
        } else {
            System.out.println("Item ID not found. Please enter again.");
        }
    }

     while (true) {
                System.out.print("Add how many stock:");
                try {
                    newItemStock = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number! Enter a valid integer.");
                    continue;
                }

                if (itemStock > 0 ) {
                    break; // valid quantity
                } else {
                    System.out.println("Invalid quantity! ");
                }
            }

        order.updateInventoryStock(itemID, itemStock+newItemStock);
        System.out.println("Update successful");
        System.out.println(itemName +" new stock have "+ (itemStock+newItemStock));

        }



    
}


