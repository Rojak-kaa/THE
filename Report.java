
import java.time.LocalDateTime;

public class Report 
{
    private String reportType;
    private LocalDateTime dateTime;

    public void generateReport(String reportType) 
    {
        this.reportType = reportType;
        this.dateTime = LocalDateTime.now();
    }

    public void displayReport()
    {
        System.out.println("Report Type: " + reportType);
        System.out.println("Generated on: " + dateTime.toString());
    }

    public double calculateTotal(double[] itemPrices) 
    {
        double total = 0.0;
        for (double price : itemPrices) 
        {
            total += price;
        }
        return total;
    }
}