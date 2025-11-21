import java.time.LocalDateTime;

public class Billing
{
    private static int billCounter = 0;

    private  final String billId;
    //private final LocalDateTime billDateTime;
    private double totalAmt;

    public Billing()
    {
        this.billId = generateBillId();
        //this.billDateTime = getBillDateTime();
        this.totalAmt = 0.0;
    }

    public String generateBillId()
    {
        billCounter++;
        
        String billId = "BILL-" +  String.format("%03d", billCounter);
        return billId;
    }

    public String getBillId()
    {
        return billId;
    }

    public LocalDateTime getBillDateTime()
    {
        LocalDateTime billDateTime = LocalDateTime.now();
        return billDateTime;
    }    
  
    public String getOrderId()
    {

    }

    public double calculateTotalAmt(double amount)
    {
        totalAmt += amount;
        return totalAmt;
    }
}