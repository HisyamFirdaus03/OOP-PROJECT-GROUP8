package vehicle;

import java.util.InputMismatchException;

import license.*;

public class Motorcycle extends Vehicle{
    private int enginePower;

    public Motorcycle(String vehicleID, String make, String model, int year, double rentalRate, int enginePower){
        super(vehicleID, make, model, year, rentalRate);
        this.enginePower = enginePower;
    }

    public int getEnginePower(){
        return enginePower;
    }

    public void setEnginePower(int enginePower){
        this.enginePower = enginePower;
    }

    public void displayDetails(){
        System.out.printf("%-12s %-12s %-10s %-6d %-20.2f %-12d %n", getVehicleID(), getMake(), getModel(), getYear(), getRentalRate(), enginePower);
    }

    public void displaySummarizeDetails(){
        System.out.println();
        System.out.println("Motorcycle Information");
        System.out.printf("%-14s: %s%n", "Vehicle ID", getVehicleID());
        System.out.printf("%-14s: %s%n", "Make", getMake());
        System.out.printf("%-14s: %s%n", "Model", getModel());
        System.out.printf("%-14s: %d%n", "Year", getYear());
        System.out.printf("%-14s: %d%n", "Engine Power", getEnginePower());
        System.out.println();
    }

    public boolean determineEligibility(License license){
        int maxEnginePower;
        switch(license.getLicenseType()){
            case LicenseType.B2:
                maxEnginePower = 250;
                break;
            case LicenseType.B1:
                maxEnginePower = 500;
                break;
            case LicenseType.B:
                maxEnginePower = 1200;
                break;
            default:
                throw new InputMismatchException();
        }

        return (enginePower <= maxEnginePower);
    }
}
