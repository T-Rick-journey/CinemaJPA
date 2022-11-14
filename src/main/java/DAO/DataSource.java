package main.java.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    String driverClassName = "org.h2.Driver";
    String urlDB = "jdbc:h2:~/test";
    String usuarioDB = "sa";
    String senhaDB = "";
    
    Connection conn = null;

    private static DataSource ds = null;
    
    private DataSource(){
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    
    public static DataSource getInstance(){
        if (ds == null){
            ds = new DataSource();
        }
        return ds;
    }
    
   public Connection getConnection() throws SQLException {	
        if (conn == null)
            conn = DriverManager.getConnection(urlDB, usuarioDB, senhaDB);
        return conn;
    }
   
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {				
            e.printStackTrace();
            }
        conn = null;
        }
    }    
}