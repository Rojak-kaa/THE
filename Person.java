import java.util.*;

public class Person {


    protected String name;
    protected String phoneNumber;   // only used by customer
    protected String customerID;
    protected String staffID;       // only used by staff


    protected String orderID;
    protected String itemID;
    protected String itemName;
    protected double itemPrice;
    protected int itemQty;
    protected double totalPrice;
    protected String orderType;
    protected char choice ;

    Scanner sc = new Scanner(System.in);

    

    // ====================================================
    // MAIN REGISTER METHOD (Inheritance Version)
    // ====================================================
    public static Person register() {
        Scanner sc = new Scanner(System.in);

       char choice;

        do {
        System .out.println("Register as:");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        System.out.print("Enter choice: ");

        choice = sc.next().charAt(0);

        if (choice == '1') {
            return new Customer().registerCustomer();
        } 
        else if (choice == '2') {
            return new Staff().registerStaff();
        }

        System.out.println("Invalid choice! Try again.");
    } while (choice != '1' && choice != '2');

}
}