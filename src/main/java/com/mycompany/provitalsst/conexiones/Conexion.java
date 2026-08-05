package com.mycompany.provitalsst.conexiones;


import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static Connection con;

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ProvitalSST";
            String usuario = "root";
            String contraseña = "admin";

            con = DriverManager.getConnection(url, usuario, contraseña);

            System.out.println("Conexión exitosa");

        } catch (Throwable e) {
            System.out.println("ERROR DE CONEXIÓN:");
            e.printStackTrace();
        }

        return con;
    }

    public static void main(String[] args){
        conectar();
    }
}