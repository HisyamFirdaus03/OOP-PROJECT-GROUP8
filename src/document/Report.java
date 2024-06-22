package document;

public class Report implements Document{
    private String reportID;
    private Invoice[] invoice;
    
    public Report(String reportID, Invoice[] invoice){
        this.reportID = reportID;
        this.invoice = invoice;
    }

    public String getReportID(){
        return reportID;
    }

    public double calculateTotalPrice(){
        double totalPrice = 0;
        for(Invoice inv : invoice){
            if(inv != null)
                totalPrice += inv.getTotalPrice();
        }
        return totalPrice;
    }

    public double calculateTotalPayment(){
        double totalPayment = 0;
        for(Invoice inv : invoice){
            if(inv != null)
                totalPayment += inv.getTotalPayment();
        }
        return totalPayment;
    }

    public void displayDetails(){
        System.out.printf("%-14s: %s%n", "Report ID", reportID);
        System.out.printf("%-14s: %.1f%% %n", "Service Tax", (serviceTax * 100));
        System.out.printf("%-14s: %.1f%% %n", "Rental Fee", (rentalFee * 100));
        System.out.printf("%-14s: RM %.2f%n", "Total Payment", calculateTotalPayment());
        System.out.printf("%-14s: RM %.2f%n", "Total Price", calculateTotalPrice());
        System.out.printf("%-14s: RM %.2f%n", "Total Due", (calculateTotalPrice() - calculateTotalPayment()));
        System.out.println();           
    }
}
