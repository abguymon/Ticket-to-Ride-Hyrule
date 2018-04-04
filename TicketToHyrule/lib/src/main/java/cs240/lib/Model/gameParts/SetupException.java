package cs240.lib.Model.gameParts;

/**
 * Created by David on 2/27/2018.
 */

public class SetupException extends RuntimeException {
    public SetupException(){super();}
    public SetupException(String message){
        super(message);
    }
}
