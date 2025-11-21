


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
