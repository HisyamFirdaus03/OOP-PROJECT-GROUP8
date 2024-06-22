package init;

import java.util.Scanner;

import document.Document;
import exception.EmptyArrayException;
import exception.ExistedVehicleException;

import java.util.ArrayList;
import java.util.InputMismatchException;

import vehicle.*;
import person.*;
import license.*;

public class Init {
    private static VehicleInventory vehicleInventory;
    private static ArrayList<Customer> customers;
    private static ArrayList<Salesperson> salespersons;
    private static Scanner input;
    private static String[] strLicenseType;
    private static String currentDate = "20/06/2024";

    public static void main(String[] args) {
        input = new Scanner(System.in);
        vehicleInventory = new VehicleInventory();
        customers = new ArrayList<>();
        salespersons = new ArrayList<>();

        //Example salesperson and customer data
        salespersons.add(new Salesperson("Haikal", 25, "S1"));
        salespersons.add(new Salesperson("Adam", 22, "S2"));
        customers.add(new Customer("Syed", 21, "C1", "0112341908"));
        customers.get(0).addCarLicense(new CarLicense("1301201", "12/27", false, LicenseType.D));
        customers.get(0).addMotorcycleLicense(new MotorcycleLicense("123102", "12/27", false, LicenseType.B2));
        salespersons.get(0).addCustomer(customers.get(0));
        vehicleInventory.addVehicle(new Car("K1", "Honda", "Civic", 2020, 20, 4, 2.0, "Sport", true));
        vehicleInventory.addVehicle(new Car("K2", "Honda", "City", 2020, 15, 4, 1.5, "Sport", false));
        vehicleInventory.addVehicle(new Motorcycle("M1", "Yamaha", "R25", 2019, 5, 250));
        vehicleInventory.addVehicle(new Motorcycle("M2", "Kawasaki", "ZX4R", 2023, 15, 400));
        
        convertLicenseTypeToString();

        //Main menu section
        boolean isContinue = true;
        while(isContinue){
            try{
                displaySystemTitle();
                System.out.println("Choose to login as Customer/Salesperson");
                System.out.println("1. Customer");
                System.out.println("2. Salesperson");
                System.out.println("3. Exit");
                System.out.println();
    
                System.out.print("Enter your choice: ");
                int choice = input.nextInt();
                input.nextLine();
                switch(choice){
                    case 1:
                        displayCustomerMenu();
                        break;
                    case 2:
                        displaySalespersonMenu();
                        break;
                    case 3:
                        isContinue = false;
                        break;
                    default:
                        System.out.println("Invalid Input! Please Try Again");
                        continue;
                }
            }
            catch(NullPointerException e){
                System.out.println("------------------------------------------------------------");
                System.out.printf("%45s %n", "Error: Input entered not found!");
                System.out.println("------------------------------------------------------------");
            }
            catch(InputMismatchException e){
                input.nextLine();
                System.out.println("------------------------------------------------------------");
                System.out.printf("%45s %n", "Error: Input entered is invalid!");
                System.out.println("------------------------------------------------------------");
            }
            catch(EmptyArrayException e){
                System.out.println("------------------------------------------------------------");
                System.out.printf("%45s %n", e.getMessage());
                System.out.println("------------------------------------------------------------");
            }
            catch(ExistedVehicleException e){
                System.out.println("------------------------------------------------------------");
                System.out.printf("%45s %n", e.getMessage());
                System.out.println("------------------------------------------------------------");
            }
        }
        input.close();
    }

    public static void displaySystemTitle(){
        System.out.println();
        System.out.println("============================================================");
        System.out.printf("%40s %n","VEHICLE RENTAL SYSTEM");
        System.out.println("============================================================");
    }

    public static void convertLicenseTypeToString(){
        LicenseType[] licenseType = LicenseType.values();
        strLicenseType = new String[licenseType.length];

        for(int i = 0; i < licenseType.length; i++){
            strLicenseType[i] = licenseType[i].name();
        }
    }

    public static void displayCustomerMenu() throws NullPointerException, InputMismatchException, EmptyArrayException, ExistedVehicleException{
        System.out.print("Enter your customer ID: ");
        String custID = input.nextLine();
        Customer loggedCustomer = getCustomerByID(custID);

        boolean isContinue = true;
        while(isContinue){

            System.out.println();
            displaySystemTitle();
            System.out.printf("%36s %n%n","CUSTOMER MENU");

            System.out.println("Welcome, " + loggedCustomer.getName());
            System.out.println();

            System.out.println("1. View available car");
            System.out.println("2. View available motorcycle");
            System.out.println("3. View rented vehicles");
            System.out.println("4. Rent a car");
            System.out.println("5. Rent a motorcycle");
            System.out.println("6. Remove rental car");
            System.out.println("7. Remove rental motorcycle");
            System.out.println("8. View Report");
            System.out.println("9. Log Out");
            System.out.println();

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch(choice){
                case 1:
                    vehicleInventory.displayAvailableCar(loggedCustomer.getCarLicense());
                    break;
                case 2:
                    vehicleInventory.displayAvailableMotorcycle(loggedCustomer.getMotorcycleLicense());
                    break;
                case 3:
                    loggedCustomer.displayRentedVehicles();
                    break;
                case 4:
                    vehicleInventory.displayAvailableCar(loggedCustomer.getCarLicense());
                    rentCar(loggedCustomer);
                    break;
                case 5:
                    vehicleInventory.displayAvailableMotorcycle(loggedCustomer.getMotorcycleLicense());
                    rentMotorcycle(loggedCustomer);
                    break;
                case 6:
                    loggedCustomer.removeRentedCar();
                    break;
                case 7:
                    loggedCustomer.removeRentedMotorcycle();
                    break;
                case 8:
                    loggedCustomer.displayReportDocument();
                    break;
                case 9:
                    isContinue = false;
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
    }

    public static void rentCar(Customer customer) throws ExistedVehicleException{
        System.out.println();
        System.out.println("Rent a car");
        System.out.print("Enter the vehicle ID to rent the vehicle: ");
        String vehicleID = input.nextLine();
        Vehicle rentedCar = vehicleInventory.getVehicle(vehicleID);

        if(rentedCar == null)
            throw new NullPointerException();

        System.out.print("Enter the number of renting hours: ");
        int rentHours = input.nextInt();
        input.nextLine();

        double totalPrice = rentHours * rentedCar.getRentalRate();
        double taxedTotalPrice = totalPrice + (totalPrice * Document.serviceTax) + (totalPrice * Document.rentalFee);

        System.out.println("Service Tax: " + (Document.serviceTax * 100) + "%");
        System.out.println("Rental Fee: " + (Document.rentalFee * 100) + "%");
        System.out.printf("Total price: RM %.2f%n", totalPrice);
        System.out.printf("Total price with tax: RM %.2f%n", taxedTotalPrice);
        System.out.print("Enter the payment amount: RM ");
        double totalPayment = input.nextDouble();
        
        customer.addRentedCar(rentedCar, rentHours, currentDate, totalPayment, taxedTotalPrice);
        customer.displayInvoiceDocument(0);
    }

    public static void rentMotorcycle(Customer customer) throws ExistedVehicleException{
        System.out.println();
        System.out.println("Rent a motorcycle");
        System.out.print("Enter the vehicle ID to rent the vehicle: ");
        String vehicleID = input.nextLine();
        Vehicle rentedMotorcycle = vehicleInventory.getVehicle(vehicleID);

        if(rentedMotorcycle == null)
            throw new NullPointerException();

        System.out.print("Enter the number of renting hours: ");
        int rentHours = input.nextInt();
        input.nextLine();

        double totalPrice = rentHours * rentedMotorcycle.getRentalRate();
        double taxedTotalPrice = totalPrice + (totalPrice * Document.serviceTax) + (totalPrice * Document.rentalFee);

        System.out.println("Service Tax: " + (Document.serviceTax * 100) + "%");
        System.out.println("Rental Fee: " + (Document.rentalFee * 100) + "%");
        System.out.printf("Total price: RM %.2f%n", totalPrice);
        System.out.printf("Total price with tax: RM %.2f%n", taxedTotalPrice);
        System.out.print("Enter the payment amount: RM ");
        double totalPayment = input.nextDouble();
        
        customer.addRentedMotorcycle(rentedMotorcycle, rentHours, currentDate, totalPayment, taxedTotalPrice);
        customer.displayInvoiceDocument(1);
    }

    public static void displaySalespersonMenu() throws NullPointerException, InputMismatchException, EmptyArrayException{
        System.out.print("Enter your staff ID: ");
        String staffID = input.nextLine();
        Salesperson loggedSalesperson = getSalespersonByID(staffID);

        boolean isContinue = true;
        while(isContinue){
            
            System.out.println();
            displaySystemTitle();
            System.out.printf("%38s %n%n","SALESPERSON MENU");
            
            System.out.println("Welcome, " + loggedSalesperson.getName());
            System.out.println();

            System.out.println("1. View available car");
            System.out.println("2. View available motorcycle");
            System.out.println("3. View rented vehicles");
            System.out.println("4. Add a car to inventory");
            System.out.println("5. Add a motorcycle to inventory");
            System.out.println("6. Remove a car from inventory");
            System.out.println("7. Remove a motorcycle from inventory");
            System.out.println("8. Add a new customer");
            System.out.println("9. View all associated customers");
            System.out.println("10. Log out");
            System.out.println();

            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch(choice){
                case 1:
                    vehicleInventory.displayAvailableCar();
                    break;
                case 2:
                    vehicleInventory.displayAvailableMotorcycle();
                    break;
                case 3:
                    vehicleInventory.displayRentedVehicles();
                    break;
                case 4:
                    addCarToInventory();
                    break;
                case 5:
                    addMotorcyclesToInventory();
                    break;
                case 6:
                    removeCarFromInventory();
                    break;
                case 7:
                    removeMotorcycleFromInventory();
                    break;
                case 8:
                    addNewCustomer(loggedSalesperson);
                    break;
                case 9:
                    loggedSalesperson.displayCustomerDetails();
                    break;
                case 10:
                    isContinue = false;
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
    }

    public static Customer getCustomerByID(String customerID){
        for(Customer customer : customers){
            if(customer.getCustomerID().equals(customerID))
                return customer;
        }
        return null;
    }

    public static Salesperson getSalespersonByID(String staffID){
        for(Salesperson salesperson : salespersons){
            if(salesperson.getStaffID().equals(staffID))
                return salesperson;
        }
        return null;
    }

    public static void addCarToInventory() throws InputMismatchException{
        displaySystemTitle();
        System.out.printf("%38s %n%n","ADD CAR MENU");

        System.out.print("Enter the car make: ");
        String make = input.nextLine();
        System.out.print("Enter the car model: ");
        String model = input.nextLine();
        System.out.print("Enter the car year: ");
        int year = input.nextInt();
        input.nextLine();
        System.out.print("Enter the car rental rate: RM ");
        double rentalRate = input.nextDouble();
        input.nextLine();
        System.out.print("Enter the number of doors: ");
        int numberOfDoors = input.nextInt();
        input.nextLine();
        System.out.print("Enter the car engine liter: ");
        double engineLiter = input.nextDouble();
        input.nextLine();
        System.out.print("Enter the car type: ");
        String carType = input.nextLine();
        System.out.print("Does it require a competent license? (Y/N): ");
        char licenseInput = input.next().charAt(0);
        boolean requireCompetentLicense = (Character.toLowerCase(licenseInput) == 'y')? true : false;

        Car car = new Car("K" + (vehicleInventory.getCarCount() + 1), make, model, year, rentalRate, numberOfDoors, engineLiter, carType, requireCompetentLicense);
        vehicleInventory.addVehicle(car);
    }

    public static void addMotorcyclesToInventory() throws InputMismatchException{
        displaySystemTitle();
        System.out.printf("%36s %n%n","ADD MOTORCYCLE MENU");

        System.out.print("Enter the motorcycle make: ");
        String make = input.nextLine();
        System.out.print("Enter the motorcycle model: ");
        String model = input.nextLine();
        System.out.print("Enter the motorcycle year: ");
        int year = input.nextInt();
        input.nextLine();
        System.out.print("Enter the motorcycle rental rate: RM ");
        double rentalRate = input.nextDouble();
        input.nextLine();
        System.out.print("Enter the motorcycle engine power (cc): ");
        int enginePower = input.nextInt();
        input.nextLine();

        Motorcycle motorcycle = new Motorcycle("M" + (vehicleInventory.getMotorcycleCount() + 1), make, model, year, rentalRate, enginePower);
        vehicleInventory.addVehicle(motorcycle);
    }

    public static void removeCarFromInventory() throws NullPointerException{
        displaySystemTitle();
        System.out.printf("%36s %n%n","REMOVE CAR MENU");

        System.out.print("Enter the vehicle ID to remove: ");
        String vehicleID = input.nextLine();

        vehicleInventory.deleteVehicle(vehicleID);
    }

    public static void removeMotorcycleFromInventory() throws NullPointerException{
        displaySystemTitle();
        System.out.printf("%38s %n%n","REMOVE MOTORCYCLE MENU");

        System.out.print("Enter the vehicle ID to remove: ");
        String vehicleID = input.nextLine();

        vehicleInventory.deleteVehicle(vehicleID);
    }

    public static License addCustomerCarLicenseInfo() throws InputMismatchException{
        System.out.println();
        System.out.println("Car License");
        System.out.print("Enter the license number: ");
        String licenseNo = input.nextLine();
        System.out.print("Enter the license expiry date: ");
        String expDate = input.nextLine();
        System.out.print("Enter the license competency (Y/N): ");
        char licenseInput = input.next().charAt(0);
        input.nextLine();
        boolean competenceLicense = (Character.toLowerCase(licenseInput) == 'y')? true : false;
        System.out.print("Enter the car license type: ");
        String licenseTypeInput = input.nextLine();
        LicenseType licenseType = LicenseType.valueOf(licenseTypeInput);
        System.out.println();

        return new CarLicense(licenseNo, expDate, competenceLicense, licenseType);
    }

    public static License addCustomerMotorcycleLicenseInfo() throws InputMismatchException{
        System.out.println();
        System.out.println("Motorcycle License");
        System.out.print("Enter the license number: ");
        String licenseNo = input.nextLine();
        System.out.print("Enter the license expiry date: ");
        String expDate = input.nextLine();
        System.out.print("Enter the license competency (Y/N): ");
        char licenseInput = input.next().charAt(0);
        input.nextLine();
        boolean competenceLicense = (Character.toLowerCase(licenseInput) == 'y')? true : false;
        System.out.print("Enter the motorcycle license type: ");
        String licenseTypeInput = input.nextLine();
        LicenseType licenseType = LicenseType.valueOf(licenseTypeInput);
        System.out.println();

        return new MotorcycleLicense(licenseNo, expDate, competenceLicense, licenseType);
    }

    public static void addNewCustomer(Salesperson salesperson) throws InputMismatchException{
        displaySystemTitle();
        System.out.printf("%38s %n%n","ADD NEW CUSTOMER MENU");

        System.out.print("Enter the customer name: ");
        String name = input.nextLine();
        System.out.print("Enter the customer age: ");
        int age = input.nextInt();
        input.nextLine();
        System.out.print("Enter the customer phone number: ");
        String phoneNumber = input.nextLine();

        Customer newCust = new Customer(name, age, "C" + (customers.size() + 1), phoneNumber);
        customers.add(newCust);
        salesperson.addCustomer(newCust);

        System.out.println();
        System.out.println("Customer License Information");
        System.out.println("1. Car License");
        System.out.println("2. Motorcycle License");
        System.out.println("3. Both");
        System.out.print("Which license the customer have?");
        System.out.println();
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();

        switch(choice){
            case 1:
                newCust.addCarLicense((CarLicense)addCustomerCarLicenseInfo());
                break;
            case 2:
                newCust.addMotorcycleLicense((MotorcycleLicense)addCustomerMotorcycleLicenseInfo());
                break;
            case 3:
                newCust.addCarLicense((CarLicense)addCustomerCarLicenseInfo());
                newCust.addMotorcycleLicense((MotorcycleLicense)addCustomerMotorcycleLicenseInfo());
                break;
            default:
                throw new InputMismatchException();
        }
    }
}
