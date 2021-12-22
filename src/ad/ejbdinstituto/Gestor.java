/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a20armandocb
 */
public class Gestor {

    public Gestor() {
        
    }
    
    public void crearBD(){
        EstructuraBD estructura = new EstructuraBD();
        if(peticiones.EntradasGui.pedirBoolean("Vas a proceder a crear la base da datos.Todos los datos anteriores se perderán.\n¿Confirmar operación?")){
            estructura.build();
            peticiones.SalidasGui.mensaje("Operación realizada con éxito");
        }else{
            peticiones.SalidasGui.mensaje("Operación Cancelada");
        }
    }
    
     
    public void borrarBD(){
        EstructuraBD estructura = new EstructuraBD();
        if(peticiones.EntradasGui.pedirBoolean("Vas a proceder a ELIMINAR la base da datos.\n¿Confirmar operación?")){
            estructura.borrarBd();
            peticiones.SalidasGui.mensaje("Operación realizada con éxito");
        }else{
            peticiones.SalidasGui.mensaje("Operación Cancelada");
        }
    }
    public void verLog(){
        peticiones.SalidasGui.bloqueTexto(utilidades.Log.getInstance().getLog());
    }
    public void borrarLog(){
        utilidades.Log.getInstance().borrarLog();
        peticiones.SalidasGui.mensaje("Log Borrado");
    }
}
