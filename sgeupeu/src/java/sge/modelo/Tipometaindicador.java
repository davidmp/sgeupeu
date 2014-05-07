/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;

/**
 *
 * @author Intel
 */
public class Tipometaindicador {
    private Integer idTipoMeta;
    private String tipo;
    private String descripcion;
    private int estado;

    public Integer getIdTipoMeta() {
        return idTipoMeta;
    }

    public void setIdTipoMeta(Integer idTipoMeta) {
        this.idTipoMeta = idTipoMeta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
}
