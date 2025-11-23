import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Report
{
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    //private String reportType;
    private LocalDate date;

    public LocalDate getDate()
    {
        return date;
    }

    private ArrayList<Order> orders;
    private ArrayList<Customer> customers;
    private ArrayList<Billing> billings;
    private Inventory inventory;

    public Report(ArrayList<Order> orders, ArrayList<Customer> customers, ArrayList<Billing> billings, Inventory inventory)
    {
        this.orders = orders;
        this.customers = customers;
        this.billings = billings;
        this.inventory = inventory;
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
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
        }

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
        for (Item i : inventory.getItems())
        {
            System.out.println(i.getName() + " - Quantity Available: " + i.getStock());
        }
        System.out.println("=============================\n");
    }

    //Customer Report

    public double calculateTotalSpent(Customer c)
    {
        double sum = 0;
        for (Order o : c.getOrderHistory())
        {
            sum += o.getGrandTotal();
        }
        return sum;
    }

    public int countTotalOrder(Customer c)
    {
        return c.getOrderHistory().size();
    }

    public void generateCustomerReport()
    {
        System.out.println("===== CUSTOMER REPORT =====");

        for (Customer c : customers)
        {
            System.out.println("Customer Id: " + c.getCustId());
            System.out.println("\nCustomer: " + c.getName());
            System.out.println("Order History:");

            for (Order o : c.getOrderHistory())
            {
                System.out.println("  Order ID: " + o.getOrderId() +
                        " | Total Price: RM " + o.getGrandTotal());
            }

            System.out.println("Total Orders: " + countTotalOrder(c));
            System.out.println("Total Amount Spent: RM " + calculateTotalSpent(c));

        }

        System.out.println("========================================\n");
    }
}