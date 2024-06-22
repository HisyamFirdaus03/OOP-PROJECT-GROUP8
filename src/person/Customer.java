package person;
import document.*;
import license.*;
import vehicle.*;
import exception.*;

public class Customer extends Person {
    private final static int MAX_NUMBER_LICENSE = 2;
    private final static int MAX_NUMBER_VEHICLES = 2;
    private String customerID;
    private String phoneNumber;
    private int[] rentHours;
    private License[] licenses;
    private Vehicle[] vehicles;
    private Invoice[] invoice;
    private Document report;

    public Customer(String name, int age, String customerID, String phoneNumber){
        super(name, age);
        this.customerID = customerID;
        this.phoneNumber = phoneNumber;
        licenses = new License[MAX_NUMBER_LICENSE];
        vehicles = new Vehicle[MAX_NUMBER_VEHICLES];
        invoice = new Invoice[MAX_NUMBER_VEHICLES];
        rentHours = new int[MAX_NUMBER_VEHICLES];
    }
    
    public String getCustomerID(){
        return customerID;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public License getCarLicense(){
        return licenses[0];
    }

    public License getMotorcycleLicense(){
        return licenses[1];
    }

    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void displayDetails(){
        System.out.printf("%-14s %-30s %-5s %-14s %n", customerID, getName(), getAge(), phoneNumber);
    }

    public void addCarLicense(String licenseNo, String expDate, boolean competentLicense, LicenseType licenseType){
        licenses[0] = new CarLicense(licenseNo, expDate, competentLicense, licenseType);
    }

    public void addCarLicense(CarLicense carLicense){
        licenses[0] = carLicense;
    }

    public void addMotorcycleLicense(String licenseNo, String expDate, boolean competentLicense, LicenseType licenseType){
        licenses[1] = new MotorcycleLicense(licenseNo, expDate, competentLicense, licenseType);
    }

    public void addMotorcycleLicense(MotorcycleLicense motorLicense){
        licenses[1] = motorLicense;
    }

    public void displayLicenseDetails(){
        for(License license : licenses){
            license.displayDetails();
        }
    }

    public void displayRentedVehicles() throws EmptyArrayException{
        System.out.println("------------------------------------------------------------");
        System.out.printf("%38s %n", "RENTED VEHICLE");
        System.out.println("------------------------------------------------------------");  
        if((vehicles[0] == null) && (vehicles[1] == null)){
            throw new EmptyArrayException("There are currently no rented vehicle to be displayed!");
        }
        for(Vehicle vehicle : vehicles){
            if(vehicle == null)
                continue;
            vehicle.displaySummarizeDetails();
        }
    }

    public void addRentedCar(Vehicle vehicle, int rentHours, String date, double totalPayment, double totalPrice) throws NullPointerException, ExistedVehicleException{
        if(vehicles[0] != null)
            throw new ExistedVehicleException("Error: You already rented a car!");

        vehicles[0] = vehicle;
        this.rentHours[0] = rentHours;
        vehicle.setAvailability(false);
        invoice[0] = new Invoice("I" + vehicle.getVehicleID(), date, totalPayment, totalPrice);
    }

    public void addRentedMotorcycle(Vehicle vehicle, int rentHours, String date, double totalPayment, double totalPrice) throws NullPointerException, ExistedVehicleException{
        if(vehicles[1] != null)
            throw new ExistedVehicleException("Error: You already rented a motorcycle!");

        vehicles[1] = vehicle;
        this.rentHours[1] = rentHours;
        vehicle.setAvailability(false);
        invoice[1] = new Invoice("I" + vehicle.getVehicleID(), date, totalPayment, totalPrice);
    }

    public void removeRentedCar() throws EmptyArrayException{
        if(vehicles[0] == null)
            throw new EmptyArrayException("Error: There are currently no rented car to be removed!");
        vehicles[0].setAvailability(true);
        vehicles[0] = null;
        rentHours[0] = 0;
    }

    public void removeRentedMotorcycle() throws EmptyArrayException{
        if(vehicles[1] == null)
            throw new EmptyArrayException("Error: There are currently no rented motorcycle to be removed!");
        vehicles[1].setAvailability(true);
        vehicles[1] = null;
        rentHours[1] = 0;
    }

    public void generateReportDocument(){
        report = null;
        report = new Report("R" + customerID, invoice);
    }

    public void displayReportDocument() throws NullPointerException, EmptyArrayException{
        generateReportDocument();
        System.out.println("------------------------------------------------------------");
        System.out.printf("%42s %n", "CUSTOMER FULL REPORT");
        System.out.println("------------------------------------------------------------"); 
        report.displayDetails();
        displayLicenseDetails();
        displayRentedVehicles();
    }

    public void displayInvoiceDocument(int index) throws NullPointerException{
        System.out.println("------------------------------------------------------------");
        System.out.printf("%32s %n", "INVOICE");
        System.out.println("------------------------------------------------------------");   
        System.out.printf("%-14s: %s%n", "Name", getName());
        System.out.printf("%-14s: %s%n", "Customer ID", getCustomerID());
        System.out.printf("%-14s: %d%n", "Age", getAge());
        System.out.printf("%-14s: %s%n", "Phone Number", getPhoneNumber());
        System.out.println();
        vehicles[index].displaySummarizeDetails();       
        System.out.println();        
        invoice[index].displayDetails();
    }
}
