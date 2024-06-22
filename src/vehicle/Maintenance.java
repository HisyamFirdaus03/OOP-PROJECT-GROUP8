package vehicle;

public class Maintenance {
    private String maintenanceID;
    private String date;
    private String description;
    private double cost;
    
    public Maintenance(String maintenanceID, String date, String description, double cost){
        this.maintenanceID = maintenanceID;
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public String getMaintenanceID(){
        return maintenanceID;
    }

    public String getDate(){
        return date;
    }

    public String getDescription(){
        return description;
    }

    public double getCost(){
        return cost;
    }

    public void setMaintenanceID(String maintenanceID){
        this.maintenanceID = maintenanceID;
    }

    public void setDate(String date){
        this.date = date;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setCost(double cost){
        this.cost = cost;
    }

    public void displayDetails(){
        System.out.println();
    }
}
