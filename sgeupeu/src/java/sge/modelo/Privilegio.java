/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;



/**
 *
 * @author osdmdz
 */

public class Privilegio{
    
String idprivilegio;
String[] estado;
String[] idacceso_array; 
String idusuario;

    public String getIdprivilegio() {
        return idprivilegio;
    }

    public void setIdprivilegio(String idprivilegio) {
        this.idprivilegio = idprivilegio;
    }

    public String[] getEstado() {
        return estado;
    }

    public void setEstado(String[] estado) {
        this.estado = estado;
    }

    public String[] getIdacceso_array() {
        return idacceso_array;
    }

    public void setIdacceso_array(String[] idacceso_array) {
        this.idacceso_array = idacceso_array;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    
}