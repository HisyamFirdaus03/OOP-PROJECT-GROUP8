package vehicle;

import license.*;

public class Car extends Vehicle{
    private int numberOfDoors;
    private double engineLiter;
    private String type;
    private boolean requireCompetentLicense;

    public Car(String vehicleID, String make, String model, int year, double rentalRate, int numberOfDoors, double engineLiter, String type, boolean requireCompetentLicense){
        super(vehicleID, make, model, year, rentalRate);
        this.numberOfDoors = numberOfDoors;
        this.engineLiter = engineLiter;
        this.type = type;
        this.requireCompetentLicense = requireCompetentLicense;
    }

    public int getNumberOfDoors(){
        return numberOfDoors;
    }

    public double getEngineLiter(){
        return engineLiter;
    }

    public String getType(){
        return type;
    }

    public boolean isRequireCompetentLicense(){
        return requireCompetentLicense;
    }

    public void setNumberOfDoors(int numberOfDoors){
        this.numberOfDoors = numberOfDoors;
    }

    public void setEngineLiter(double engineLiter){
        this.engineLiter = engineLiter;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setRequireCompetentLicense(boolean requireCompetentLicense){
        this.requireCompetentLicense = requireCompetentLicense;
    }
    
    public void displayDetails(){
        System.out.printf("%-12s %-12s %-10s %-6d %-14.1f %-20.2f %-10s %n", getVehicleID(), getMake(), getModel(), getYear(), getEngineLiter(), getRentalRate(), getType());
    }

    public void displaySummarizeDetails(){
        System.out.println();
        System.out.println("Car Information");
        System.out.printf("%-14s: %s%n", "Vehicle ID", getVehicleID());
        System.out.printf("%-14s: %s%n", "Make", getMake());
        System.out.printf("%-14s: %s%n", "Model", getModel());
        System.out.printf("%-14s: %d%n", "Year", getYear());
        System.out.printf("%-14s: %s%n", "Type", getType());
        System.out.printf("%-14s: %.1f%n", "Engine Liter", engineLiter);
        System.out.println();
    }

    public boolean determineEligibility(License license){
        return ((license.isCompetentLicense() && requireCompetentLicense) || (license.isCompetentLicense() && !requireCompetentLicense) || (!license.isCompetentLicense() && !requireCompetentLicense));
    }
}
