/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;

/**
 *
 * @author Intel
 */
public class InstitucionAcreditadora {
 
    private int idinstitucionacredit;
    private String nombre;
    private String descripcion;
    private String tipo;
    private int estado;   

    public int getIdinstitucionacredit() {
        return idinstitucionacredit;
    }

    public void setIdinstitucionacredit(int idinstitucionacredit) {
        this.idinstitucionacredit = idinstitucionacredit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
}
