package DButils;

import java.sql.Connection;
import java.sql.SQLException;

public interface CPI {
    Connection getConnection();
    boolean returnConnection(Connection connection);
    void closeAllConnections() throws SQLException;

}
