import java.time.LocalDate;
import java.util.*;

public class Main 
{

    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);

        //Create inventory and load item
        Inventory inv = new Inventory();
        Menu m =new Menu(inv);
        
        Order o = new Order();
        Customer c = new Customer();
        Staff s = new Staff();
        char userChoice;
        userChoice=' ';

        System.out.println("===========================");
        System.out.println("Welcome to THE Bakery Shop!");
        System.out.println("===========================");
        
        //ask user for the account
        int hasAccount = 0;
        while(true){
            System.out.println("Do you have an account?(y/n):");
            userChoice = sc.next().charAt(0);

            if(userChoice == 'y' || userChoice == 'Y' || userChoice == 'n' || userChoice == 'N') {
                break;  // <-- EXIT LOOP
            }

            System.out.println("Invalid value. Try again.");
        }

        
        if (userChoice == 'y' || userChoice == 'Y') 
        {
            while (true)  // loop until user gives valid input
            {
                System.out.println("\n Please select your user type:");
                System.out.println("1. Customer \n2.Staff");
                System.out.print("Enter choice: ");

                userChoice = sc.next().charAt(0);

                if (userChoice == '1' || userChoice == '2') {
                    break;  
                }

                System.out.println("Invalid choice. Try again.");
            }
        }
        else if(userChoice =='N' || userChoice =='n')
            {
                Person p = Person.register();
            }


        while( userChoice!=('1') && userChoice!=('2') )
        {
            System.out.println("========================================");
            System.out.println("Please select your user type:");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.print("Enter your choice: ");
            userChoice = sc.next().charAt(0);   // update the value
        }

        if(userChoice == 1)
        {
            System.out.println("Here are our items for sale:");

        }

        if(userChoice == 2)
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
        c.registerCustomer();
        c.viewOrderHistory();

        //ORDER PROCESS
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