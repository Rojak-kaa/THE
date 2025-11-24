public class Item {
    private String id, name;
    private double price;
    private int quantity;

    public Item(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Increase stock
    public void increaseStock(int qty) { this.quantity += qty; }

    public boolean reduceStock(int qty) {
        if(qty > this.quantity) return false;
        this.quantity -= qty;
        return true;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Price: RM " + price + " | Qty: " + quantity;
    }

    public String toMenuString() {
        return id + " | " + name + " | RM " + price;
    }

    // File format
    public String toFileFormat() {
        return id + "," + name + "," + price + "," + quantity;
    }

    public static Item fromFileFormat(String line) {
        String[] parts = line.split(",", 4);
        return new Item(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
    }

}

