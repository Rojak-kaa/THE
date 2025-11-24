import java.io.*;

public class Customer extends Person {

    private String customerID;  // add field for customer ID

    public static String generateCustomerID(){

    // String orderID;
    // String itemID;
    // String itemName;
    // double itemPrice;
    
    int tryCounter = 1;
    boolean unique = false;
    String customerID = "";
    String input;
    boolean found;

    while(!unique){
        
        customerID = "C" + String.format("%03d", tryCounter);
        unique = true;  // assume it's unique

        try (BufferedReader reader = new BufferedReader(new FileReader("Customer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    String existingCustomerID = parts[0].trim();
                    if (existingCustomerID.equalsIgnoreCase(customerID)) {
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
        return customerID;
    
    }



    public Customer registerCustomer() {

        customerID=generateCustomerID();
        System.out.println("Customer ID: "+customerID);

        System.out.print("Enter your name: ");
        this.name = sc.nextLine();

        

        while (true) {
    System.out.print("Enter your phone number: ");
    this.phoneNumber = sc.nextLine();

    boolean found = false;

    try (BufferedReader reader = new BufferedReader(new FileReader("Customer.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            // Ensure phone number is in column 2
            if (parts.length >= 2) {
                String existingPhone = parts[1].trim();

                if (existingPhone.equalsIgnoreCase(phoneNumber)) {
                    found = true;
                    break;
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading customer file.");
    }

    if (found) {
        System.out.println("This phone number is already registered. Try another.");
    } else {
        break;  // phone number is unique → exit loop
    }
}



        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Customer.txt", true))) {
            writer.write(customerID+","+phoneNumber + "," + name);
            writer.newLine();
            System.out.println("Customer registered successfully!");
        } catch (Exception e) {
            System.out.println("Error writing customer file.");
        }

        return this;
        
    }


    public boolean loginCustomer() {
    boolean found = false;
    do{
        System.out.print("Enter phone number: ");
        String input = sc.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("Customer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");

                // safety check
                if (p.length >= 3) {
                    String storedPhone = p[1].trim();   // phone number is column 2
                    String storedName  = p[2].trim();   // name is column 3

                    // check phone number match
                    if (storedPhone.equalsIgnoreCase(input)) {
                        this.phoneNumber = storedPhone;
                        this.name = storedName;

                        System.out.println("Welcome " + this.name + "!");
                        return true;
                    }

                    found = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }

        System.out.println("Phone number not found.");
        return false;

    }while(false);
}


public void viewOrderHistory() {
    System.out.println("-------- View Purchase History ------");
    System.out.print("Enter your phone number: ");
    this.phoneNumber = sc.nextLine().trim();

    boolean found = false;

    try (BufferedReader reader = new BufferedReader(new FileReader("Order.txt"))) {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length >= 8) {

                String currentCustomerID = parts[0].trim();
                String currentOrderID    = parts[1].trim();
                String currentItemID     = parts[2].trim();
                String currentItemName   = parts[3].trim();
                int qty                  = Integer.parseInt(parts[4].trim());
                double price             = Double.parseDouble(parts[5].trim());
                String eatMethod         = parts[6].trim();
                String recordPhone       = parts[7].trim();

                // ✔ Correct matching condition
                if (recordPhone.equals(this.phoneNumber)) {

                    found = true;

                    System.out.println("Customer ID : " + currentCustomerID);
                    System.out.println("Order ID    : " + currentOrderID);
                    System.out.println("Item ID     : " + currentItemID);
                    System.out.println("Item Name   : " + currentItemName);
                    System.out.println("Quantity    : " + qty);
                    System.out.println("Price       : RM " + price);
                    System.out.println("Eat Method  : " + eatMethod);
                    System.out.println("--------------------------------------");
                }
            }
        }

    } catch (IOException e) {
        System.out.println("Error reading order file.");
        return;
    }

    if (!found) {
        System.out.println("Phone number not found.");
    }
    }

    // Getters
    public String getCustID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
    return phoneNumber;
    }

}