package exception;

public class EmptyArrayException extends Exception{
    private String message;

    public EmptyArrayException(){

    }

    public EmptyArrayException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
