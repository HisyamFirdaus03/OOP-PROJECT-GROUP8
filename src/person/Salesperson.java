package person;

import java.util.ArrayList;

public class Salesperson extends Person{
    private String staffID;
    private ArrayList<Customer> customers;

    public Salesperson(String name, int age, String staffID){
        super(name, age);
        this.staffID = staffID;
        customers = new ArrayList<>();
    }

    public String getStaffID(){
        return staffID;
    }

    public void setStaffID(String staffID){
        this.staffID = staffID;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void displayCustomerDetails(){
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("   %-14s %-30s %-5s %-14s %n", "Customer ID", "Name", "Age", "Phone Number");
        System.out.println("----------------------------------------------------------------------");
        int i = 1;
        for(Customer customer : customers){
            System.out.print(i + ". ");
            customer.displayDetails();
            i++;
        }
    }

    public void displayDetails(){
        System.out.printf("%-14s %-30s %-5s %n", staffID, getName(), getAge());
    }
}
