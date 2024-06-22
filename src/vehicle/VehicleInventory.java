package vehicle;
import java.util.ArrayList;

import exception.EmptyArrayException;
import license.*;

public class VehicleInventory {
    private ArrayList<Vehicle> cars;
    private ArrayList<Vehicle> motorcycles;

    public VehicleInventory(){
        cars = new ArrayList<>();
        motorcycles = new ArrayList<>();
    }

    public Vehicle searchVehicle(String vehicleID){
        for(Vehicle vehicle : cars){
            System.out.println(vehicle.getVehicleID() + "/");
            if(vehicle.getVehicleID().equals(vehicleID)){
                return vehicle;
            }
        }

        for(Vehicle vehicle : motorcycles){
            if(vehicle.getVehicleID().equals(vehicleID)){
                return vehicle;
            }
        }
        return null;
    }

    public int getCarCount(){
        return cars.size();
    }

    public int getMotorcycleCount(){
        return motorcycles.size();
    }

    public Vehicle getVehicle(String vehicleID){
        for(Vehicle vehicle : cars){
            if(vehicle.getVehicleID().equals(vehicleID))
                return vehicle;
        }

        for(Vehicle vehicle : motorcycles){
            if(vehicle.getVehicleID().equals(vehicleID))
                return vehicle;
        }

        return null;
    }

    public void addVehicle(Car car){
        cars.add(car);
    }

    public void addVehicle(Motorcycle motorcycle){
        motorcycles.add(motorcycle);
    }

    public void deleteVehicle(String vehicleID){
        for(Vehicle vehicle : cars){
            if(vehicle.getVehicleID().equals(vehicleID)){
                cars.remove(vehicle);
                return;
            }
        }

        for(Vehicle vehicle : motorcycles){
            if(vehicle.getVehicleID().equals(vehicleID)){
                motorcycles.remove(vehicle);
                return;
            }
        }

        throw new NullPointerException();
    }

    public void displayAllCarsDetails(){
        for(Vehicle vehicle : cars){
            vehicle.displayDetails();
        }
    }

    public void displayAllMotorcyclesDetails(){
        for(Vehicle vehicle : motorcycles){
            vehicle.displayDetails();
        }
    }

    public void displayAllVehiclesDetails(){
        System.out.println("Cars");
        displayAllCarsDetails();
        System.out.println("Motorcycles");
        displayAllMotorcyclesDetails();
    }

    public void displayVehicleDetails(String vehicleID) throws NullPointerException{
        Vehicle vehicle = searchVehicle(vehicleID);
        vehicle.displayDetails();
    }

    public void displayAvailableCar(){
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("   %-12s %-12s %-10s %-6s %-14s %-20s %-10s %n", "Vehicle ID", "Make", "Model", "Year", "Engine Liter", "Rental Rate(RM/h)", "Type");
        System.out.println("------------------------------------------------------------------------------------------");
        int i = 1;
        for(Vehicle vehicle : cars){
            if(vehicle.isAvailable()){
                System.out.print(i + ". ");
                vehicle.displayDetails();
                i++;
            }
        }
    }

    public void displayAvailableCar(License license){
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("   %-12s %-12s %-10s %-6s %-14s %-20s %-10s %n", "Vehicle ID", "Make", "Model", "Year", "Engine Liter", "Rental Rate(RM/h)", "Type");
        System.out.println("------------------------------------------------------------------------------------------");
        int i = 1;
        for(Vehicle vehicle : cars){
            if(vehicle.isAvailable() && vehicle.determineEligibility(license)){
                System.out.print(i + ". ");
                vehicle.displayDetails();
                i++;
            }
        }
    }

    public void displayAvailableMotorcycle(){
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("   %-12s %-12s %-10s %-6s %-20s %-12s %n", "Vehicle ID", "Make", "Model", "Year", "Rental Rate(RM/h)", "Engine Power(cc)");
        System.out.println("-------------------------------------------------------------------------------------");
        int i = 1;
        for(Vehicle vehicle : motorcycles){
            if(vehicle.isAvailable()){
                System.out.print(i + ". ");
                vehicle.displayDetails();
                i++;
            }
        }
    }

    public void displayAvailableMotorcycle(License license){
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("   %-12s %-12s %-10s %-6s %-20s %-12s %n", "Vehicle ID", "Make", "Model", "Year", "Rental Rate(RM/h)", "Engine Power(cc)");
        System.out.println("-------------------------------------------------------------------------------------");
        int i = 1;
        for(Vehicle vehicle : motorcycles){
            if(vehicle.isAvailable() && vehicle.determineEligibility(license)){
                System.out.print(i + ". ");
                vehicle.displayDetails();
                i++;
            }
        }
    }

    public void displayRentedVehicles() throws EmptyArrayException{
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.printf("%35s %n", "RENTED CARS");
        System.out.println("------------------------------------------------------------");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.printf("   %-12s %-12s %-10s %-6s %-14s %-20s %-10s %n", "Vehicle ID", "Make", "Model", "Year", "Engine Liter", "Rental Rate(RM/h)", "Type");
        System.out.println("------------------------------------------------------------------------------------------");
        int i = 1;
        for(Vehicle vehicle : cars){
            if(!vehicle.isAvailable()){
                System.out.print(i + ". ");
                vehicle.displayDetails();
                i++;
            }
        }
        
        System.out.println();
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.printf("%38s %n", "RENTED MOTORCYCLE");
        System.out.println("------------------------------------------------------------");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("   %-12s %-12s %-10s %-6s %-20s %-12s %n", "Vehicle ID", "Make", "Model", "Year", "Rental Rate(RM/h)", "Engine Power(cc)");
        System.out.println("-------------------------------------------------------------------------------------");
        i = 1;
        for(Vehicle vehicle : motorcycles){
            if(!vehicle.isAvailable()){
                System.out.print(i + ". ");
                vehicle.displayDetails();
                i++;
            }
        }
    }
}
