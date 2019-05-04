package Exceptions;

import java.sql.SQLException;

public class ConnectionError extends RuntimeException{

    public ConnectionError(Exception e){
        super("Connection Error, Please Try Again.");
        System.err.println(e.getMessage());
    }


    public ConnectionError (String message){
        super(message);
    }

}
