package cs240.lib.communicator;

/**
 * Created by David on 2/10/2018.
 */

public class InvalidAuthTokenException extends RuntimeException {
    public InvalidAuthTokenException(){
        super();
    }
    public InvalidAuthTokenException(String message){
        super(message);
    }
}
