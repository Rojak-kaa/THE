import java.time.LocalDateTime;
import java.io.*;
import java.util.*;

public class Billing {
    private static int billCounter = 0;

    private String billId;
    private LocalDateTime dateTime;
    private double totalPrice, totalAmt;
    private int totalQty;

    private Order order; //a reference of order
    private Item item;

    public Billing(Order order, Item item) {
        this.order = order;
        this.item = item;
        this.billId = generateBillId();
        this.dateTime = LocalDateTime.now();
        this.totalPrice = 0.00;
        this.totalAmt = 0.00;
    }

//    public String getOrderId()
//    {
//        return order.getOrderId();
//    }
//
//    public String getCustId()
//    {
//        return order.getCustId();
//    }
//
//    public String getItemId()
//    {
//        return order.getItemId();
//    }
//
//    public int getQuantity()
//    {
//        return order.Quantity();
//    }

    public String generateBillId() {
        billCounter++;

        return billId = "B" + String.format("%03d", billCounter);
    }

    public String getBillId() {
        return billId;
    }

    public double getTotalPrice() {
        totalPrice = item.getPrice() * order.getQuantity();
        return totalPrice;
    }

    public int getTotalQty() {
        totalQty += order.getQuantity();
        return totalQty;
    }

    public double calculateBill() {
        totalAmt = item.getPrice() * order.getQuantity();
        return totalAmt;
    }

//    public double getTotalAmt()
//    {
//        return totalAmt;
//    }

    public void generateReceipt() {
        calculateBill();

        System.out.println("Bill Id: " + billId + "\t Date and Time: " + dateTime);
        System.out.println("Order Id: " + order.getOrderId() + "\t Customer Id: " + order.getCustId());
        System.out.println("==================================================================================");
        System.out.printf("%-10s %-10s %-15s %-15s%n", "Item Id", "Quantity", "Price per Unit", "Total Price");
        System.out.println("==================================================================================");
        System.out.printf("%-10s %-10d %-8.2f %-8.2f%n", order.getItemId(), order.getQuantity(), item.getPrice(), getTotalPrice());
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%-10s %-5d %-10s %-3.2f%n", "Total Quantity: ", getTotalQty(), "Total Amount: ", getTotalAmt());
    }
}