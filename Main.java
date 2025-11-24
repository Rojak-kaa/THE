import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // System objects
        Inventory ivt = new Inventory();
        Menu m = new Menu(ivt);

        Order order = new Order();
        Customer c = new Customer();
        Staff s = new Staff();

        System.out.println("================================");
        System.out.println("   Welcome to THE Bakery Shop   ");
        System.out.println("================================");

        // ============================
        // LOGIN / REGISTER SECTION
        // ============================
        int hasAccount = 0;
        while (true) {
            System.out.print("Do you have an account? (1. yes / 2. no): ");
            String input = sc.nextLine();

            try {
                hasAccount = Integer.parseInt(input);
                if (hasAccount == 1 || hasAccount == 2) break;
            } catch (Exception e) {}

            System.out.println("Invalid input. Please enter 1 or 2.");
        }

        // ============================
        // USER TYPE SELECTION LOOP
        // ============================
        while (true) {

            int userChoice = 0;

            // If user just registered, skip to user type selection
            while (true) {
                System.out.println("\nSelect user type:");
                System.out.println("1. Customer");
                System.out.println("2. Staff");
                System.out.print("Enter number: ");

                try {
                    userChoice = Integer.parseInt(sc.nextLine());
                    if (userChoice == 1 || userChoice == 2) break;
                } catch (Exception e) {}

                System.out.println("Invalid number. Please enter 1 or 2.");
            }

            // ============================
            // CUSTOMER SECTION
            // ============================
            if (userChoice == 1) {

                if (hasAccount == 1) {
                    c.loginCustomer();
                } else {
                    // New registration
                    Person p = Person.register();
                    c.registerCustomer();
                }

                boolean exitCustomerMenu = false;
                while (!exitCustomerMenu) {
                    System.out.println("\n------- Customer Page --------");
                    System.out.println("1. Order Section");
                    System.out.println("2. View Order History");
                    System.out.println("3. Exit");
                    System.out.print("Enter number: ");

                    int cusChoice;
                    try {
                        cusChoice = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Invalid input!");
                        continue;
                    }

                    switch (cusChoice) {
                        case 1:
                            m.displayMenu();
                            order.typeOfOrder();
                            order.takeOrder();
                            System.out.println("Payment successful!");
                            Billing b = new Billing(order);
                            b.generateReceipt();
                            break;

                        case 2:
                            c.viewOrderHistory();
                            break;

                        case 3:
                            System.out.println("Returning to user type selection...");
                            exitCustomerMenu = true;
                            break;

                        default:
                            System.out.println("Invalid number. Try again.");
                    }
                }
            }

            // ============================
            // STAFF SECTION
            // ============================
            else if (userChoice == 2) {

                if (hasAccount == 1) {
                    s.loginStaff();
                } else {
                    // New registration
                    Person p = Person.register();
                    // Assume staff registration handled in register()
                }

                boolean exitStaffMenu = false;
                while (!exitStaffMenu) {
                    System.out.println("\n======= Staff Portal =======");
                    System.out.println("1. Manage Inventory");
                    System.out.println("2. Update Order Status");
                    System.out.println("3. View Order History");
                    System.out.println("4. View Report");
                    System.out.println("5. Exit");
                    System.out.print("Enter number: ");

                    int staffChoice;
                    try {
                        staffChoice = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        sc.nextLine();
                        System.out.println("Invalid number!");
                        continue;
                    }

                    switch (staffChoice) {
                        case 1:
                            ivt.showInventory();
                            break;
                        case 2:
                            s.updateStatus();
                            break;
                        case 3:
                            s.viewAllOrders();
                            break;
                        case 4:
                            runReportMenu(sc, c);
                            break;
                        case 5:
                            System.out.println("Returning to user type selection...");
                            exitStaffMenu = true;
                            break;
                        default:
                            System.out.println("Invalid number!");
                    }
                }
            }
        }
        // sc.close();  ← optional: if you want program to run forever, no need to close here
    }

    // ============================
    // REPORT SUB-MENU LOOP
    // ============================
    public static void runReportMenu(Scanner sc, Customer c) {

        Billing dummyBill = new Billing(new Order());
        double totalSpent = 0;
        Order order = new Order();

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(c);

        ArrayList<Billing> bills = new ArrayList<>();
        bills.add(dummyBill);

        Report report = new Report(customers, bills, totalSpent);

        while (true) {

            System.out.println("\n===== REPORT MENU =====");
            System.out.println("1. Sales Report");
            System.out.println("2. Inventory Report");
            System.out.println("3. Customer Report");
            System.out.println("4. Back to Staff Menu");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Invalid number!");
                continue;
            }

            switch (choice) {
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
                    return;  // back to staff menu
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
