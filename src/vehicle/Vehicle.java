package vehicle;

import license.*;

public abstract class Vehicle {
    private String vehicleID;
    private String make;
    private String model;
    private int year;
    private double rentalRate;
    private boolean available;
    private Maintenance nextMaintenance;

    public Vehicle(String vehicleID, String make, String model, int year, double rentalRate){
        this.vehicleID = vehicleID;
        this.make = make;
        this.model = model;
        this.year = year;
        this.rentalRate = rentalRate;
        this.available = true;
    }

    public String getVehicleID(){
        return vehicleID;
    }
    
    public String getMake(){
        return make;
    }
    
    public String getModel(){
        return model;
    }
    
    public int getYear(){
        return year;
    }
    
    public double getRentalRate(){
        return rentalRate;
    }

    public boolean isAvailable(){
        return available;
    }

    public void setVehicleID(String vehicleID){
        this.vehicleID = vehicleID;
    }
    
    public void setMake(String make){
        this.make = make;
    }
    
    public void setModel(String model){
        this.model = model;
    }
    
    public void setYear(int year){
        this.year = year;
    }
    
    public void setRentalRate(double rentalRate){
        this.rentalRate = rentalRate;
    }

    public void setAvailability(boolean available){
        this.available = available;
    }

    public void setNextMaintenance(String maintenanceID, String date, String description, double cost){
        nextMaintenance = new Maintenance(maintenanceID, date, description, cost);
    }

    public void displayMaintenanceDetails(){
        nextMaintenance.displayDetails();
    }

    public abstract void displayDetails();

    public abstract void displaySummarizeDetails();

    public abstract boolean determineEligibility(License license);
}
