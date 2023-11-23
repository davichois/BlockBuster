package com.alex.blockbuster.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectionsbd {
    public static Connection conection;

    //javaconnections:motorDB://host:port/schemaDB
    private static final String URL = "jdbc:mysql://localhost:3306/dbBlockbuster";
    private static final String USER = "root";
    private static final String PASSWORD = "davidprada27";

    //metodo para abrir la coneccion
    public Connection openConnection() throws SQLException {
        try {
            Class.forName( "com.mysql.jdbc.Driver");
            conection = DriverManager.getConnection(URL, USER, PASSWORD);
            return conection;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: " + e);
        }
        return conection;
    }

    //metodo para cerrar la coneccion
    public void closeConnection() throws SQLException {
        try {
            conection.close();
        } catch (SQLException e){
            System.err.println("error: " + e);
            conection.close();
        }finally {
            conection.close();
        }
    }
}
