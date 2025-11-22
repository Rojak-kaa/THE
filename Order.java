import java.io.*;
import java.util.*;

public class Order {

    private static int counter = 0;
    private static String lastDate = "";
    List<String> orderItems = new ArrayList<>();

    protected String orderID;
    protected String itemID;
    protected String itemName;
    protected static int itemQty;
    protected double itemPrice;
    protected int itemStock;
    protected String remark;
    private char  userChoice;
    protected  boolean found;
    protected double totalPrice;

    protected String ForderID;
    protected String FitemID;
    protected String FitemName;
    protected int FitemQty;



    public static String generateOrderId() {
    String today = java.time.LocalDate.now()
            .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));

    int tryCounter = 1;
    boolean unique = false;
    String orderID = "";

    while (!unique) {
        orderID = today + "-" + String.format("%03d", tryCounter);
        unique = true;

        // Check in file
        try (BufferedReader reader = new BufferedReader(new FileReader("Order.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    String existingOrderID = parts[0].trim();
                    if (existingOrderID.equalsIgnoreCase(orderID)) {
                        unique = false;
                        tryCounter++;     // generate next ID
                        break;
                    }
                }
            }
        } catch (IOException e) {
            unique = true;  // File missing → OK
        }
    }

    return orderID;
}

public void updateInventoryStock(String targetID, int newStock) {
    List<String> updatedLines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader("InventoryList.txt"))) {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts[0].trim().equalsIgnoreCase(targetID)) {
                parts[2] = String.valueOf(newStock); // update the stock value
                parts[3] = String.valueOf(itemPrice);
                line = String.join(",", parts);
            }

            updatedLines.add(line);
        }

    } catch (IOException e) {
        System.out.println("Error reading inventory file.");
        return;
    }

    // Rewrite file with updated stock
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("InventoryList.txt"))) {
        for (String updated : updatedLines) {
            writer.write(updated);
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error writing inventory file.");
    }
}



    public void takeOrder() {
    Scanner sc = new Scanner(System.in);



    // Generate order ID
    orderID = generateOrderId();
    System.out.println("Order ID: " + orderID);

    userChoice = 'y';
    // Input Item ID

    while(userChoice !='n' && userChoice !='N' ){
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
            System.out.println("Item found: " + itemName  );//", Stock: " + itemStock
            break;
        } else {
            System.out.println("Item ID not found. Please enter again.");
        }
    }

    // Input Quantity
    while (true) {
                System.out.print("Enter Quantity: ");
                try {
                    itemQty = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number! Enter a valid integer.");
                    continue;
                }

                if (itemQty > 0 && itemQty <= itemStock) {
                    break; // valid quantity
                } else {
                    System.out.println("Invalid quantity! Available: " + itemStock);
                }
            }

            // update stock
            updateInventoryStock(itemID, itemStock - itemQty);

            // store order line
            totalPrice=itemPrice*itemQty;
            String orderLine = orderID + "," + itemID + "," + itemName + "," + itemQty+","+totalPrice;
            orderItems.add(orderLine);

            displayOrder();

            // ask user to continue
            System.out.print("Order another item? (y/n): ");
            userChoice = sc.next().charAt(0);
            sc.nextLine(); // flush buffer
        }

        displayOrder();

        // ========== SAVE ORDER TO FILE ==========
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Order.txt", true))) {
            for (String line : orderItems) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Order saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to order file.");
        }
    }


public void displayOrder() {

    System.out.println("\n===== ORDER DETAILS =====");

    for (String line : orderItems) {
        String[] parts = line.split(",");

        String orderID = parts[0];
        String itemID = parts[1];
        String itemName = parts[2];
        String qty = parts[3];
        String total = parts[4]; // <-- GET totalPrice FROM LIST

        System.out.println("Order ID : " + orderID);
        System.out.println("Item ID  : " + itemID);
        System.out.println("Name     : " + itemName);
        System.out.println("Qty      : " + qty);
        System.out.println("Total Price: RM " + total);
        System.out.println("----------------------------------");

        double grandTotal = 0;

        for (String lines : orderItems) {
            String[] partss = lines.split(",");
            grandTotal += Double.parseDouble(parts[4]);
        }

System.out.println("GRAND TOTAL: RM " + grandTotal);
    }
}


        

    }

    




