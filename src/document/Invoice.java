package document;

public class Invoice implements Document{
    private String invoiceID;
    private String date;
    private double totalPayment;
    private double totalPrice;

    public Invoice(String invoiceID, String date, double totalPayment, double totalPrice){
        this.invoiceID = invoiceID;
        this.date = date;
        this.totalPayment = totalPayment;
        this.totalPrice = totalPrice;
    }

    public String getInvoiceID(){
        return invoiceID;
    }

    public String getDate(){
        return date;
    }

    public double getTotalPayment(){
        return totalPayment;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void displayDetails(){  
        System.out.printf("%-14s: %s%n", "Invoice ID", invoiceID);
        System.out.printf("%-14s: %s%n", "Date", date);
        System.out.printf("%-14s: RM %.2f%n", "Total Payment", totalPayment);
        System.out.printf("%-14s: RM %.2f%n", "Total Price", totalPrice);
        System.out.printf("%-14s: RM %.2f%n", "Amount Due", (totalPrice - totalPayment));
        System.out.println();  
    }
}
