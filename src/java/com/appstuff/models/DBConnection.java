package com.appstuff.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author efsan1
 */
public final class DBConnection{
    
    private Connection connection = null;
    private final String dbUserName;
    private final String dbPassword;
    private String connLink = null;

    
    // non-default constructor
    public DBConnection() throws SQLException {
        
        this.dbUserName = "root";
        this.dbPassword = "XAXxrq12615";
        connLink = "jdbc:mysql://node160432-administrationportal.j.layershift.co.uk/onthatilechildresdb";
        //local host:
        /*this.dbUserName = "root";
        this.dbPassword = "password";
        connLink= "jdbc:mysql://localhost:3306/onthatile children's ministries";*/
    }
    
    // creating a connection to the DB
    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(connLink,dbUserName, dbPassword);
            connection.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException ex) {
        }
        return this.connection;
    } 

}
