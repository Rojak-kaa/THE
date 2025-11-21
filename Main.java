//import java.util.*;

public class Main 
{

    public static void main(String [] args)
    {
        // Scanner sc = new Scanner(System.in);
        // Item editItem = new Item();
        // Item addItem = new Item();
        

        // System.out.println("Welcome to THE Bakery Shop!");

        // String userChoice = ""; // declare first

        // while( !userChoice.equals("1") && !userChoice.equals("2") )
        // {
        //     System.out.println("========================================");
        //     System.out.println("Please select your user type:");
        //     System.out.println("1. Customer");
        //     System.out.println("2. Staff");
        //     System.out.print("Enter your choice: ");
        //     userChoice = sc.nextLine();   // update the value
        // }

        // if(userChoice.equals("1"))
        // {
        //     System.out.println("Here are our items for sale:");

        // }

        // if(userChoice.equals("2"))
        // {
        //     System.out.println("Staff login portal:");
        //     System.out.print("Enter staff id: ");
        //     String staffId = sc.nextLine();
        //     //System.out.println("Welcome, Staff " + staffName);
        // }

        Billing bill = new Billing();
        System.out.println("Bill ID: " +bill.generateBillId());
        System.out.println("Date and Time: " +bill.getBillDateTime());
    }
}
