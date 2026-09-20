package com.mycompany.provitalsst.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection con;

    public static Connection conectar() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/provital_sst";
           String usuario = "provital_user"; 
            String contraseña = "provital2026";

            con = DriverManager.getConnection(url, usuario, contraseña);

            System.out.println("Conexión exitosa con Provital SST");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("ERROR DE CONEXIÓN:");
            e.printStackTrace();

        }

        return con;
    }

    public static void main(String[] args) {
        conectar();
    }
}