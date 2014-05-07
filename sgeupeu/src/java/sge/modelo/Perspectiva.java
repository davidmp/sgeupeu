/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;

/**
 *
 * @author Intel
 */
public class Perspectiva {
    private int idperspectiva;
    private String nombre;
    private String descripcion;
    private String codigo;

    public int getIdperspectiva() {
        return idperspectiva;
    }

    public void setIdperspectiva(int idperspectiva) {
        this.idperspectiva = idperspectiva;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
}
