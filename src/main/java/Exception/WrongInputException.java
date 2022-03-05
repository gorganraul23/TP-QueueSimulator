package Exception;

public class WrongInputException extends Exception{

    public WrongInputException(){

    }
    public WrongInputException(String msg){
        super(msg);
    }
}
