/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;
import java.sql.*;
import java.util.Scanner;
import menu.Menu;


/**
 *
 * @author a20armandocb
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         boolean continuar = true;
        Menu menu = construirMenuPrincipal();
        do {
            try {
                continuar = menuAcciones(menu);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } while (continuar);
    }
    
    private static Menu construirMenuPrincipal() {
        Gestor gestor = new Gestor();
        Menu menu = new Menu();
        menu.setTituloMenu("Menú Base Datos");
        menu.setTextoSalir("Salir");
        
        menu.addOpcion("Crear Base de Datos", ()-> { gestor.crearBD();});
        menu.addOpcion("ELIMINAR Base de Datos", ()-> { gestor.borrarBD();});
        
        return menu;
    }
    
    private static boolean menuAcciones(Menu menu) throws Exception {
        boolean continuar = true;
        menu.mostrarGUI();

        switch (menu.getSeleccion()) {
            case 0:
                //salir
                continuar = false;
                break;               
        }
        return continuar;
    }
}
