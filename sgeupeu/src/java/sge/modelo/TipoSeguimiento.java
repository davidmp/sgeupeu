/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.modelo;

/**
 *
 * @author Intel
 */
public class TipoSeguimiento {

    
    int idTipoSeguim, estado;
    String etiqueta, nombre;

    public int getIdTipoSeguim() {
        return idTipoSeguim;
    }

    public void setIdTipoSeguim(int idTipoSeguim) {
        this.idTipoSeguim = idTipoSeguim;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
