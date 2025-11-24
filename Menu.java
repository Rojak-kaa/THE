import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Menu
{
    public void showMenu() {
        System.out.println("----- Menu  -----");

        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line is formatted like: itemID,itemName,itemPrice,quantity
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String itemID = parts[0];
                    String itemName = parts[1];
                    String itemPrice = parts[2];
                    String quantity = parts[3];

                    System.out.printf("%-5s | %-20s | RM %6.2f%n", itemID, itemName, Double.parseDouble(itemPrice));

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found!");
        } catch (IOException e) {
            System.out.println("Error reading inventory file.");
        }
    }
}
