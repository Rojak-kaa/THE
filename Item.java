public class Item {
    protected String name, iID;
    protected double price;
    protected int stock;

    public int SIZE = 10;
    String [] item = new String[SIZE];
    {
        item[0]="Black Forest Cake";
        item[1]="Red Velvet Cake";
        item[2]="Hotdog bun";
        item[3]="Croissant";
        item[4]="Baguette";
        item[5]="Kaya bun";
        item[6]="Pineapple tart";
        item[7]="Chocolate muffin";
        item[8]="Blueberry scone";
        item[9]="Cinnamon roll";
    }

    String [] itemID = new String[SIZE];
    {
        itemID[0]="ITM001";
        itemID[1]="ITM002";
        itemID[2]="ITM003";
        itemID[3]="ITM004";
        itemID[4]="ITM005";
        itemID[5]="ITM006";
        itemID[6]="ITM007";
        itemID[7]="ITM008";
        itemID[8]="ITM009";
        itemID[9]="ITM010";
    }
    
    double [] itemPrice = new double[SIZE];
    {
        itemPrice[0]=45.00;
        itemPrice[1]=50.00;
        itemPrice[2]=2.50;
        itemPrice[3]=3.00;
        itemPrice[4]=2.20;
        itemPrice[5]=1.80;
        itemPrice[6]=0.90;
        itemPrice[7]=1.20;
        itemPrice[8]=1.50;
        itemPrice[9]=1.10;
    }

    int [] itemStock = new int[SIZE];
    {
        itemStock[0]=100;
        itemStock[1]=100;
        itemStock[2]=100;
        itemStock[3]=100;
        itemStock[4]=100;
        itemStock[5]=100;
        itemStock[6]=100;
        itemStock[7]=100;
        itemStock[8]=100;
        itemStock[9]=100;
    }


    /*public void addItem(String name, String iID, double price, int stock) {
        this.name = name;
        this.iID = iID;
        this.price = price;
        this.stock = stock;
    }

    public void editItem(String name, String iID, double price,int stock) {
        this.name = name;
        this.iID = iID;
        this.price = price;
        this.stock = stock;
    }*/

    public void updateStockQty(int index, int newStock) 
    {
        if (index >= 0 && index < SIZE)
        {
            itemStock[index] += newStock;
        } else 
        {
            System.out.println("Invalid index!");
        }
    }

    public void updateStockName(int index, String newName) 
    {
        if (index >= 0 && index < SIZE)
        {
            item[index] = newName;
        } else 
        {
            System.out.println("Invalid index!");
        }
    }

    public void updateStockPrice(int index, double newPrice) 
    {
        if (index >= 0 && index < SIZE)
        {
            itemPrice[index] = newPrice;
        } else 
        {
            System.out.println("Invalid index!");
        }
    }

    public void updateStockID(int index, String newID) 
    {
        if (index >= 0 && index < SIZE)
        {
            itemID[index] = newID;
        } else 
        {
            System.out.println("Invalid index!");
        }
    }

    //Getter methods
    public String getItemId() {
        return iID;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void increaseStock(int qty) {
        this.stock += qty;
    }


    /*@Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }*/
}
