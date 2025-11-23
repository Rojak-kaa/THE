import java.time.LocalDate;
import java.util.*;

public class Billing
{
    private static int billCounter = 0;

    private String billId;
    private LocalDate date;
    private double totalPrice, grandTotal;

    private Order order;

    public Order getOrder()
    {
        return order;
    }//a reference of order

    public Billing(Order order)
    {
        this.order = order;
        this.billId = generateBillId();
        this.date = LocalDate.now();
        this.totalPrice = 0.00;
        this.grandTotal = 0.00;
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

    public LocalDate getDate()
    {
        return date;
    }

    public void calculateTotalPrice()
    {
        totalPrice = order.getPrice() * order.getQuantity();
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void calculateGrandTotal()
    {
        grandTotal += totalPrice;
    }

    public double getGrandTotal()
    {
        return grandTotal;
    }

    public void calculateBill()
    {
        calculateTotalPrice();
        calculateGrandTotal();

    }

    public void generateReceipt() 
    {
        calculateBill();

        System.out.println("Bill Id: " + billId + "\t Date: " + date);
        System.out.println("Order Id: " + order.getOrderId() + "\t Customer Id: " + order.getCustID());
        System.out.println("==================================================================================");
        System.out.printf("%-10s %-10s %-15s %-15s%n", "Item Id", "Quantity", "Price per Unit", "Total Price");
        System.out.println("-----------------------------------------------------------------------------------");

        grandTotal = 0.00;

        List<String> orderItems = order.orderItems;

        for (String line : orderItems) 
        {
            String[] parts = line.split(",");

            String itemId = parts[2].trim();
            int quantity = Integer.parseInt(parts[4].trim());
            double totalPrice = Double.parseDouble(parts[5].trim());
            double price = totalPrice / quantity;

            //System.out.printf("%-10s %-10d %-8.2f %-8.2f%n", order.getItemId(), order.getQuantity(), order.getPrice(), getTotalPrice());
        System.out.printf("%-10s %-10d %-15.2f %-15.2f%n", itemId, quantity, price, totalPrice);
        }

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("Total Amount: %.2f%n", grandTotal);
        System.out.println("==================================================================================");
    }
}