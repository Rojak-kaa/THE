import java.io.*;

public class Staff extends Person {

    public static String generateStaffID(){

    int tryCounter = 1;
    boolean unique = false;
    String staffID = "";

    while(!unique){
        
        staffID = "S" + String.format("%03d", tryCounter);
        unique = true;  // assume it's unique

        try (BufferedReader reader = new BufferedReader(new FileReader("Staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    String existingStaffID = parts[0].trim();
                    if (existingStaffID.equalsIgnoreCase(staffID)) {
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
        return staffID;
    
    }


    public Staff registerStaff() {

        staffID=generateStaffID();
        System.out.println("Staff ID: "+staffID);

        System.out.print("Enter your name: ");
        this.name = sc.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Staff.txt", true))) {
            writer.write(staffID + "," + name);
            writer.newLine();
            System.out.println("Staff registered successfully!");
        } catch (Exception e) {
            System.out.println("Error writing staff file.");
        }

        return this;
    }


    public boolean loginStaff() {
        System.out.print("Enter staff ID: ");
        String id = sc.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("Staff.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                if (p[0].equalsIgnoreCase(id)) {
                    this.staffID = p[0];
                    this.name = p[1];
                    System.out.println("Welcome Staff " + this.name + "!");
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading staff file.");
        }

        System.out.println("Staff not found.");
        return false;
    }

    public void viewAllOrders() {
    System.out.println("======= ALL ORDER RECORDS =======");

    try (BufferedReader reader = new BufferedReader(new FileReader("Order.txt"))) {
        String line;

        boolean empty = true;

        while ((line = reader.readLine()) != null) {
            empty = false;

            String[] p = line.split(",");
            if (p.length >= 8) {
                System.out.println("Customer ID : " + p[0]);
                System.out.println("Order ID    : " + p[1]);
                System.out.println("Item ID     : " + p[2]);
                System.out.println("Item Name   : " + p[3]);
                System.out.println("Quantity    : " + p[4]);
                System.out.println("Total Price : RM " + p[5]);
                System.out.println("Order Type  : " + p[6]);
                System.out.println("Phone Number: " + p[7]);
                System.out.println("--------------------------------");
            }
        }

        if (empty) {
            System.out.println("No order records found.");
        }

    } catch (IOException e) {
        System.out.println("Error reading order file.");
    }
}

public void updateStatus()
{
    do{
    System.out.println("-------Update Status--------");
    System.out.println("pending(y/n): ");
    choice = sc.next().charAt(0);
    if(choice == 'y'||choice == 'Y')
        {
            System.out.print("In progress(y/n):");
            choice=sc.next().charAt(0);
            if(choice == 'y'||choice == 'Y')
                {
                    System.out.print("Completed(y/n): ");
                    choice = sc.next().charAt(0);
                    break;
                }
        }
    }while(choice == 'n' || choice =='N');

}
}
