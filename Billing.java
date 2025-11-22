import java.time.LocalDateTime;
import java.io.*;
import java.util.*;

public class Billing
{
    private static int billCounter = 0;

    private String billId;
    private LocalDateTime dateTime;
    private double totalPrice, totalAmt;

    private Order order; //a reference of order

    public Billing(Order order)
    {
        this.order = order;
        this.billId = generateBillId();
        this.dateTime = LocalDateTime.now();
        this.totalPrice = 0.00;
        this.totalAmt = 0.00;
    }

    public String generateBillId()
    {
        billCounter++;
        return "B" + String.format("%03d", billCounter);
    }

    public String getBillId()
    {
        return billId;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public double calculatTotalPrice()
    {
        totalPrice = order.getPrice() * order.getQuantity();
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public double calculateTotalAmt()
    {
        totalAmt += totalPrice;
    }

    public double getTotalAmt()
    {
        return totalAmt;
    }

    public void generateReceipt() {
        calculateBill();

        System.out.println("Bill Id: " + billId + "\t Date and Time: " + dateTime);
        System.out.println("Order Id: " + order.getOrderId() + "\t Customer Id: " + order.getCustId());
        System.out.println("==================================================================================");
        System.out.printf("%-10s %-10s %-15s %-15s%n", "Item Id", "Quantity", "Price per Unit", "Total Price");
        System.out.println("==================================================================================");

        totalAmt = 0;

        for(int i = 0; i < order.getItemId().size(); i++)
        {
            String itemId =order.getItemId().get(i);
            int order.getQuantity().get(i);
            double price = order.getPrice().get(i);

            System.out.printf("%-10s %-10d %-8.2f %-8.2f%n", order.getItemId(), order.getQuantity(), order.getPrice(), getTotalPrice());
        }

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("Total Amount: %.2f%n", getTotalAmt());
        System.out.println("==================================================================================");
    }
}