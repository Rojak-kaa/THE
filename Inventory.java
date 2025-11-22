//1
// Inventory.java
import java.util.*;

public class Inventory {
    private final List<BakeryItem> items = new ArrayList<>();
    private int nextId = 1;
    private final Scanner sc = new Scanner(System.in);

    public Inventory() {
        //loadItemsFromFile();
    }

    //Add Items
    public booleann addItem() {
        String itemName;
        double price;
        int quantity;

        System.out.println("Enter item name: ");
        itemName = sc.nextLine());

        System.out.println("Enter item price: ");
        price = sc.nextDouble;
        sc.nextLine();

        System.out.println("Enter item quantity: ");
        quantity = sc.nextInt();
        sc.nextLine();

        BakeryItem bi = new BakeryItem(nextId++, itemName, price, quantity);
        items.add(bi);

        System.out.println("Item added successfully!");
        return bi;
    }

    //Remove Items
    public boolean removeItem() {
        String itemId;

        System.out.println("What item would you like to remove?");
        System.out.println("Enter Item ID: );
        itemId = sc.nextInt();
        sc.nextLine();

        BakeryItem item = searchById(itemId);
        if (item == null) {
            System.out.println("Item not found!");
            return false;
        }

        items.remove(item);
        System.out.println("Item removed successfully!);
        return true;
    }

    //Display Inventory
    public void displayInv() {
        System.out.println("----- Inventory -----");
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (BakeryItem i : items) {
            System.out.println("Name: " + i.getName());
            System.out.println("Item ID: " + i.getId());
            System.out.println("Price: RM " + i.getPrice());
            System.out.println("Quantity: " + i.getQuantity());
        }
    }

    //searchById Method
    public BakeryItem searchById(int itemId) {
        for (BakeryItem i : items) {
            if (i.getId() == itemId) return i;
        }
        return null;
    }
    //Update Inventory (name, price, quantity)
    public boolean updateInv(String itemId) {
        BakeryItem item = searchById(itemId);
        if (item == null) {
            System.out.println("Item not found!");
            return false;
        }

        System.out.println("Current Item Details: ");
        System.out.println("1. Name: " + item.getName());
        System.out.println("2. Price: " + item.getPrice());
        System.out.println("3. Quantity: " + item.getQuantity());

        System.out.println("What would you like to update?");
        System.out.println("1. Name: ");
        System.out.println("2. Price: ");
        System.out.println("3. Quantity: ");
        System.out.println("4. Update All: ");
        System.out.println("Enter choice: ");

        String newName;
        double newPrice;
        int newQuantity;

        int choice = sc.nextInt();
        sc.nextLine();

        if(choice ==1) {
            System.out.println("Enter new name: ");
            newName = sc.nextLine();
            item.setName(newName);
        }

        else if(choice ==2) {
            System.out.println("Enter new price: ");
            newPrice = sc.nextDouble();
            sc.nextLine();
            item.setPrice(newPrice);
        }

        else if(choice ==3) {
            System.out.println("Enter new quantity: ");
            newQuantity = sc.nextInt();
            sc.nextLine();
            item.setQuantity(newQuantity);
        }

        else if(choice ==4) {
            System.out.println("Enter new name: ");
            newName = sc.nextLine();
            System.out.print("Enter new price: ");
            newPrice = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter new quantity: ");
            newQuantity = sc.nextInt();
            sc.nextLine();

            item.setName(newName);
            item.setPrice(newPrice);
            item.setQuantity(newQuantity);
        }

        else {
            System.out.println("Invalid choice!");
            return false;
        }

        System.out.println("Item updated successfully!");
        return true;
    }
    public void reportInv() {
        System.out.println("----- Inventory Report -----");

        int totalItems = 0;
        double totalValue = 0.00;

        for(BakeryItem i : items) {
            totalItems += i.getQuantity();
            totalValue += i.getQuantity() * i.getPrice();
        }

        System.out.println("Total different items: " +items.size());
        System.out.println("Total quantity in stock: " +totalItems);
        System.out.println("Total stock value RM: " +totalValue);
    }
}

//2
public class Inventory extends Item {


    //Scanner sc = new Scanner(System.in);

    public Inventory(String name, String iID, double price, int stock) {
        super.addItem(name, iID, price, stock);
        //this.stock = stock;
    }

    /*public void editInventory(String name, String iID, double price, int stock) {
        super.editItem(name, iID, price, stock);
        //this.stock = stock;
    }*/

    public void displayInventory()
    {
        System.out.println("Item ID" +"\t"+"Item Name" +"\t"+ "Price" +"\t"+ "Stock");
        for (int i=0;i<SIZE; i++)
        {
            System.out.println(itemID[i]+"\t"+item[i]+"\t"+itemPrice[i]+"\t"+itemStock[i]);
        }
        
    }


}
