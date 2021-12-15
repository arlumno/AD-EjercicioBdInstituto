/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;

import java.sql.*;

/**
 *
 * @author a20armandocb
 */
public class ConexionBD {

    private static final String DB_URL = "jdbc:mysql://localhost:3307/";
    private static final String DB_USER = "root";
    private static final String DB_USER_PWD = "usbw";
    private static Connection conexion;
    private static Statement statement;


    private ConexionBD() {

    }

    public static Statement getStatement() {
        if (statement == null) {
            conectar();
        }
        return statement;
    }

    private static void conectar() {
        try {
            conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_USER_PWD);
            Statement statement = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println(e.toString());

        }

    }

}
