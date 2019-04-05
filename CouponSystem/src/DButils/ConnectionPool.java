package DButils;


import Exceptions.ConnectionError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ConnectionPool implements CPI{

    private static ConnectionPool connectionPool = new ConnectionPool();
    private int listSize = 10;
    private static List<Connection> connections ;
    private static List<Connection> usedConnections = new ArrayList<>();
    private Connection connection;
    public static ConnectionPool getInstance(){
        if(connectionPool==null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }


    private ConnectionPool(){
        usedConnections = new ArrayList<>();
         connections = new ArrayList<>();

         Collections.synchronizedList(connections);
         Collections.synchronizedList(usedConnections);
        System.out.println("Setting up connections...");
        for(int i=0;i<listSize;i++){

            try {
                connection = DriverManager.getConnection(DButil.connectionURL(),"client","123123");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connections.add(connection);
            connection= null;
        }
        System.out.println(connections.size() + " Connections set up.");
    }

    @Override
    public Connection getConnection() { ;
        synchronized (usedConnections) {
            usedConnections.add(connection);
        }

        synchronized (connections){
               return connections.remove(0);

            }


    }

    @Override
    public boolean returnConnection(Connection connection) {
        try {
            this.connection=DriverManager.getConnection(DButil.connectionURL(),"client","123123");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        synchronized (connections){
            connections.add(this.connection);
        };
        synchronized (usedConnections){
            return usedConnections.remove(this.connection);
        }
    }

    @Override
    public void closeAllConnections() {
        usedConnections.forEach(this::returnConnection);
        while (connections.size() != 0) {
            for (Connection c : connections) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            connections.clear();
        }
    }

    public void getSize(){
        System.out.println("Unused connections:" + connections.size() + "  Used Connections: "+usedConnections.size());
    }
}






