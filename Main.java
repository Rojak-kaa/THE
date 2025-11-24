import java.time.LocalDate;
import java.util.*;

public class Main 
{

    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        Item editItem = new Item();
        Item addItem = new Item();
        

        System.out.println("Welcome to THE Bakery Shop!");

        String userChoice = ""; // declare first

        while( !userChoice.equals("1") && !userChoice.equals("2") )
        {
            System.out.println("========================================");
            System.out.println("Please select your user type:");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.print("Enter your choice: ");
            userChoice = sc.nextLine();   // update the value
        }

        if(userChoice.equals("1"))
        {
            System.out.println("Here are our items for sale:");

        }

        if(userChoice.equals("2"))
        {
            System.out.println("Staff login portal:");
            System.out.print("Enter staff id: ");
            String staffId = sc.nextLine();
            //System.out.println("Welcome, Staff " + staffName);
        }

        // //INVENTORY PROCESS
        // Inventory i = new Inventory();
        // // i.loadItemsFromFile("inventory.txt");
        // i.displayItems();

        //CUSTOMER PROCESS
        Customer c = new Customer();
        c.registerCustomer();
        c.displayCustomerInfo();

        //ORDER PROCESS
        Order o = new Order();
        o.typeOfOrder();
        o.takeOrder();

        //BILLING PROCESS
        System.out.println("Generating bill automatically...\n");
        Billing b = new Billing(o);
        b.generateReceipt();

        //REPORT PROCESS
        Report report = new Report(o, c, b, i);

        while (true) 
        {
            System.out.println("\n===== REPORT MENU =====");
            System.out.println("1. Sales Report");
            System.out.println("2. Inventory Report");
            System.out.println("3. Customer Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) 
            {
                case 1:
                    report.generateSalesReport();
                    break;
                case 2:
                    report.generateInventoryReport();
                    break;
                case 3:
                    report.generateCustomerReport();
                    break;
                case 4:
                    System.out.println("Exiting report menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}