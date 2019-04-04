package Exceptions;

import java.sql.SQLException;

public class ConnectionError extends Exception{

    public ConnectionError(SQLException e){
        super("Error closing connection, retrying");
        System.err.println(e.getMessage());
    }
}
