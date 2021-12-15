/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author a20armandocb
 */
public class CrearEstructuraBD {
    private ArrayList<String> sqlArray = new ArrayList<String>();
    private Statement conexion;

    public CrearEstructuraBD(Statement conexion) {
        this.conexion = conexion;
        estructuraProfesores();
        
        build();
    }
    
    /**
     * @param args the command line arguments
     */
    
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    
    private void estructuraProfesores(){
        sqlArray.add("CREATE DATABASE IF NOT EXISTS bdInstituto;");
        sqlArray.add("USE BDInstituto;");
        
        sqlArray.add("CREATE TABLE IF NOT EXISTS profesores "
                + "(dni VARCHAR (10) NOT NULL,"
                + "nombre VARCHAR (50) NOT NULL,"
                + "titulacion VARCHAR (50) NOT NULL,"
                + "PRIMARY KEY (dni)"
                + ");");
        
        sqlArray.add("CREATE TABLE IF NOT EXISTS alumnos "
                + "(id_alumno int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                + "cod_alumno VARCHAR (4) NOT NULL ,"
                + "nombre VARCHAR (50) NOT NULL,"
                + "PRIMARY KEY (id_alumno),"
                + "SECONDARY KEY (cod_alumno),"
                + "CHECK (codalumno LIKE '...[A-Z]');"
                + ");");
        
//        sqlArray.add("ALTER TABLE profesores "
//                + "add constraint validar_codalumno "
//                + "check (codalumno LIKE '...[A-Z]');");
        
         sqlArray.add("CREATE TABLE IF NOT EXISTS asignaturas "
                + "(id_asignatura int(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                + "cod_asignatura VARCHAR (4) NOT NULL ,"
                + "ciclo VARCHAR (50) NOT NULL,"
                + "PRIMARY KEY (id_asignatura),"
                + "SECONDARY KEY (cod_asignatura),"
                + "CHECK (cod_asignatura LIKE '...[A-Z]');"                 
                + ");");
        
        sqlArray.add("CREATE TABLE IF NOT EXISTS profesores_alumnos_asignaturas "
                + "(dni_profesor VARCHAR (10) NOT NULL, "
                + "id_alumno int(4) UNSIGNED ZEROFILL NOT NULL,"
                + "id_asignatura int(4) UNSIGNED ZEROFILL NOT NULL,"
                + "PRIMARY KEY (id_asignatura)"
                + ");");
        
        sqlArray.add("");
        sqlArray.add("");
        sqlArray.add("");
        sqlArray.add("");
    }
    
    private void build(){
        try{
            for(String sql: sqlArray){
                conexion.execute(sql);
            }            
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }
}
