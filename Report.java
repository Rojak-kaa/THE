import java.util.*;
import java.io.*;
import java.time.*;
//import java.time.format.DateTimeFormatter;

public class Report
{
    //private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //private String reportType;
    private LocalDate date;

    public LocalDate getDate()
    {
        return date;
    }

    ////private ArrayList<Order> orders;
    private ArrayList<Customer> customers;
    private ArrayList<Billing> billings;
    //private Inventory inventory;
    private double totalSpent;

    // public Report(ArrayList<Order> orders, ArrayList<Customer> customers, ArrayList<Billing> billings, Inventory inventory, double totalSpent)
    // {
    //     this.orders = orders;
    //     this.customers = customers;
    //     this.billings = billings;
    //     this.inventory = inventory;
    //     this.totalSpent = 0.00;
    // }

    public Report(ArrayList<Customer> customers, ArrayList<Billing> billings, double totalSpent)
    {
        this.customers = customers;
        this.billings = billings;
        this.totalSpent = 0.00;
    }

    //Sales Report
    public void generateSalesReport() 
    {
        System.out.println("\n===== SALES REPORT MENU =====");
        System.out.println("1. Daily Sales Report");
        System.out.println("2. Monthly Report");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) 
        {
                case 1:
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String dateInput = sc.next();
                    LocalDate day = LocalDate.parse(dateInput);
                    generateDailyReport(day);
                    break;
                case 2:
                    System.out.print("Enter Year: ");
                    int year = sc.nextInt();
                    System.out.print("Enter Month (1-12): ");
                    int month = sc.nextInt();
                    generateMonthlyReport(year, month);
                    break;
                case 3:
                    System.out.println("Exiting report menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
        }
        sc.close();
    }
    

    //DAILY SALES REPORT

    public void generateDailyReport(LocalDate date) 
    {
        System.out.println("===== DAILY REPORT: " + date + " =====");

        int totalBill = 0;
        double totalSales = 0;
        boolean found = false;

        for (Billing b : billings) 
        {
            if (b.getDate().equals(date)) 
            {
                found = true;
                break;
            }
        }

        if (!found) 
        {
            System.out.println("No bills found for this date.");
            return;
        }

        System.out.println("-----------------------------------");
        System.out.printf("%-15s %-15s%n", "Bill Id", "Total Amount");
        System.out.println("-----------------------------------");

        for (Billing b : billings) 
        {
            if (b.getDate().equals(date)) 
            {
                totalBill++;
                totalSales += b.getGrandTotal();
                System.out.printf("%-15s %-13.2f%n", b.getBillId(), b.getGrandTotal());
            }
        }

        System.out.println("-----------------------------------");
        System.out.printf("%-11s %-3d %-13s %-3.2f%n", "Total Bill: ", totalBill, "Total Sales: ", totalSales);
        System.out.println("===================================\n");
    }

    //MONTHLY SALES REPORT

    public void generateMonthlyReport(int year, int month) 
    {
        System.out.println("===== REPORT FOR " + month + "/" + year + " =====");

        int totalBill = 0;
        double totalSales = 0;
        boolean found = false;

        for (Billing b : billings) 
        {
            LocalDate d = b.getDate();
            if (d.getYear() == year && d.getMonthValue() == month) 
            {
                found = true;
                break;
            }
        }

        if (!found) 
        {
            System.out.println("No bills found for this month.");
            return;
        }

        System.out.println("-----------------------------------");
        System.out.printf("%-7s %-7s %-15s%n", "Date", "Bill Id", "Total Amount");
        System.out.println("-----------------------------------");

        for (Billing b : billings) 
        {
            LocalDate d = b.getDate();
            if (d.getYear() == year && d.getMonthValue() == month) 
            {
                totalBill++;
                totalSales += b.getGrandTotal();
                System.out.printf("%-7s %-7s %-13.2f%n", b.getDate(), b.getBillId(), b.getGrandTotal());
            }
        }

        System.out.println("-----------------------------------");
        System.out.printf("%-11s %-3d %-13s %-3.2f%n", "Total Bill: ", totalBill, "Total Sales: ", totalSales);
        System.out.println("===================================\n");
    }

    //Inventory Report
    public void generateInventoryReport()
    {
        System.out.println("===== INVENTORY REPORT =====");
        try (BufferedReader reader = new BufferedReader(new FileReader("InventoryList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String itemID = parts[0].trim();
                    String itemName = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());
                    
                    System.out.println(itemName + " (ID: " + itemID + ") - Quantity Available: " + quantity + " | Price: RM " + price);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file.");
        }
        
        System.out.println("=============================\n");
    }

    //Customer Report

    public double calculateTotalSpent(Customer c)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader("Order.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    String custID = parts[0].trim();
                    
                    if (custID.equals(c.getCustID())) {
                        double price = Double.parseDouble(parts[5].trim());
                        totalSpent += price;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading order file.");
        }
        return totalSpent;
    }

    public int countTotalOrder(Customer c)
    {
        int count = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("Order.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 8) {
                String custID = parts[0].trim();
                String orderID = parts[1].trim();
                
                if (custID.equals(c.getCustID())) {
                    boolean alreadyExists = false;
                    for (int i = 0; i < count; i++) {
                        if (parts[1].trim().equals(orderID)) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    
                    if (!alreadyExists) {
                        count++;
                    }
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading order file.");
    }
        return count;
    }

    public void generateCustomerReport()
    {
        System.out.println("===== CUSTOMER REPORT =====");

        for (Customer c : customers)
        {
            System.out.println("\nCustomer Id: " + c.getCustID());
            System.out.println("Customer: " + c.getName());
            System.out.println("Order History:");

            ArrayList<String> printedOrders = new ArrayList<>();

            // for (Order o : c.getOrderHistory())
            // {
            //     System.out.println("  Order ID: " + o.getOrderId() +
            //             " | Total Price: RM " + o.getGrandTotal());
            // }

            // System.out.println("Total Orders: " + countTotalOrder(c));
            // System.out.println("Total Amount Spent: RM " + calculateTotalSpent(c));

            try (BufferedReader reader = new BufferedReader(new FileReader("Order.txt"))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 8) {
                        String custID = parts[0].trim();
                        String orderID = parts[1].trim();
                        double price = Double.parseDouble(parts[5].trim());
                        
                        boolean alreadyPrinted = false;
                        for (String printed : printedOrders){
                            if (printed.equals(orderID)){
                                alreadyPrinted = true;
                                break;
                            }
                        }
                        if (custID.equals(c.getCustID()) && !alreadyPrinted) {
                            System.out.println("  Order ID: " + orderID + " | Total Price: RM " + price);
                            printedOrders.add(orderID);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading order file.");
            }

            System.out.println("Total Orders: " + countTotalOrder(c));
            System.out.println("Total Amount Spent: RM " + calculateTotalSpent(c));
        }

        System.out.println("========================================\n");
    }
}