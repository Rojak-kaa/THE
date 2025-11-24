public class Menu
{
    private Inventory inventory; //connect to Inventory

    public Menu(Inventory inv) {
        this.inventory = inv;
    }

    //Display Menu (Available Items)
    public void displayMenu() {
        System.out.println("----- Menu -----");

        for(Item item : inventory.getItems()) {
            if(item.getQuantity() > 0) {
                System.out.println("Item ID: " + item.getId());
                System.out.println("Name: " + item.getName());
                System.out.println("Price: " + item.getPrice());
            }
        }
    }
}
