/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.DAO;

/**
 *
 * @author a20armandocb
 */
public interface IValidateSql {
    default public String validate(String string){
        String textoLimpio = string;
        textoLimpio = textoLimpio.replace("'", "");
        textoLimpio = textoLimpio.replace("\"", "");
        textoLimpio = textoLimpio.replace("\\", "");
        return textoLimpio;
    }
}
