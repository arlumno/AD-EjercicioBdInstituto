/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;

import ad.ejbdinstituto.model.Alumno;
import ad.ejbdinstituto.model.Profesor;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author a20armandocb
 */
public class EstructuraBD {

    private ArrayList<String> sqlArray = new ArrayList<String>();
    private Statement statement;
    public static final String DB_NAME = "bdInstituto";
    public static final String DB_TABLE_PROFESORES = "profesores";
    public static final String DB_TABLE_ALUMNOS = "alumnos";
    public EstructuraBD() {
        this.statement = ConexionBD.getStatement();
        tablaProfesores();
        tablaAlumnos();
        tablaAsignaturas();
        tablaNotas();
        tablasInterrelaciones();
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//    }
    private void tablaProfesores() {
        sqlArray.add("CREATE DATABASE IF NOT EXISTS "+DB_NAME+";");
        sqlArray.add("USE "+DB_NAME+";");

        sqlArray.add("CREATE TABLE IF NOT EXISTS "+DB_TABLE_PROFESORES+" "
                + "(dni VARCHAR("+ Profesor.MAX_SIZE_DNI +") NOT NULL,"
                + "nombre VARCHAR("+ Profesor.MAX_SIZE_NOMBRE +") NOT NULL,"
                + "titulacion VARCHAR("+ Profesor.MAX_SIZE_TITULACION +") NOT NULL,"
                + "PRIMARY KEY (dni)"
                + ");");
    }

    private void tablaAlumnos() {
        sqlArray.add("CREATE TABLE IF NOT EXISTS "+DB_TABLE_ALUMNOS+" "
                + "(id_alumno INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                + "cod_alumno VARCHAR("+ Alumno.MAX_SIZE_CODIGO +") NOT NULL UNIQUE ,"
                + "nombre VARCHAR("+ Alumno.MAX_SIZE_NOMBRE +") NOT NULL,"
                + "PRIMARY KEY (id_alumno),"
                + "UNIQUE (cod_alumno),"
                + "CHECK (cod_alumno LIKE '...[A-Z]')"
                + ");");
//https://dev.mysql.com/doc/refman/5.6/en/create-table-foreign-keys.html
//https://www.educba.com/alternate-key-in-sql/

//        sqlArray.add("ALTER TABLE profesores "
//                + "add constraint validar_codalumno "
//                + "check (codalumno LIKE '...[A-Z]');");
    }

    private void tablaAsignaturas() {
        sqlArray.add("CREATE TABLE IF NOT EXISTS asignaturas "
                + "(id_asignatura INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                + "cod_asignatura VARCHAR (4) NOT NULL ,"
                + "ciclo VARCHAR(50) NOT NULL,"
                + "PRIMARY KEY (id_asignatura),"
                + "UNIQUE (cod_asignatura),"
                + "CHECK (cod_asignatura LIKE '...[A-Z]')"
                + ");");
    }

    private void tablasInterrelaciones() {
        sqlArray.add("CREATE TABLE IF NOT EXISTS profesores_alumnos_asignaturas "
                + "(dni_profesor VARCHAR (10) NOT NULL, "
                + "id_alumno INT(4) UNSIGNED ZEROFILL NOT NULL,"
                + "id_asignatura INT(4) UNSIGNED ZEROFILL NOT NULL,"
                + "PRIMARY KEY (id_asignatura, id_alumno)"
                + ");");
    }

    private void tablaNotas() {
        sqlArray.add("CREATE TABLE IF NOT EXISTS notas "
                + "(id_alumno INT(4) UNSIGNED ZEROFILL NOT NULL,"
                + "id_asignatura INT(4) UNSIGNED ZEROFILL NOT NULL,"
                + "fecha DATE NOT NULL,"
                + "nota FLOAT UNSIGNED ZEROFILL NOT NULL,"
                + "PRIMARY KEY (id_asignatura, id_alumno, fecha)"
                + ");");
    }

    public void build() {
        for (String sql : sqlArray) {
            try {
                statement.execute(sql);
                utilidades.Log.getInstance().addToLog("Creada base da datos");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Statement:\n " + sql);
                System.exit(0);
            }
        }
    }

    public void borrarBd() {
        String sql = "DROP DATABASE bdInstituto ";
        try {
            statement.execute(sql);
            utilidades.Log.getInstance().addToLog("Eliminada base da datos");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Statement:\n " + sql);
            System.exit(0);
        }
    }
    
}
