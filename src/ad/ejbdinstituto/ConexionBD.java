/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;

import java.sql.*;
import java.util.Properties;

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

    private static boolean conectar() {
        boolean resultado = true;
        try {
            Properties propiedades = new Properties();

            propiedades.setProperty("serverTimezone", "UTC");
            propiedades.setProperty("user", DB_USER);
            propiedades.setProperty("password", DB_USER_PWD);
            //conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_USER_PWD);
            conexion = DriverManager.getConnection(DB_URL, propiedades);
            statement = conexion.createStatement();
            executeSql("USE " +  EstructuraBD.DB_NAME);
        } catch (SQLException e) {
            peticiones.SalidasGui.mensaje("Error al realizar la conexión a la base de datos.\n" + e.toString());            
            resultado = false;
        }
        return resultado;
    }
    
    public static boolean executeSql(String sql, String log){
        boolean resultado = true;
        try {
            getStatement().execute(sql);
            if(log != ""){
                utilidades.Log.getInstance().addToLog(log);                
            }
        } catch (SQLException e) {
            peticiones.SalidasGui.mensaje("Error: " + e.getMessage() + "\nStatement:\n " + sql);            
            utilidades.Log.getInstance().addToLog("Error en: " + sql);                
            resultado = false;
        } catch (NullPointerException e){            
            resultado = false;
        }
        return resultado;
    }
    
    public static boolean executeSql(String sql){
        return executeSql(sql,"");
    }
    
     public static ResultSet executeQuerySql(String sql, String log){
        ResultSet resultado = null;
        try {
            resultado = getStatement().executeQuery(sql);
            if(log != ""){
                utilidades.Log.getInstance().addToLog(log);                
            }
        } catch (SQLException e) {
            peticiones.SalidasGui.mensaje("Error: " + e.getMessage() + "\nStatement:\n " + sql);            
            resultado = null;
        } catch (NullPointerException e){            
            resultado = null;
        }
        return resultado;
    }
     
    public static ResultSet executeQuerySql(String sql){
        return executeQuerySql(sql,"");
    }
}
