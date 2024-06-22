package exception;

public class ExistedVehicleException extends Exception{
    private String message;

    public ExistedVehicleException(){

    }

    public ExistedVehicleException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
