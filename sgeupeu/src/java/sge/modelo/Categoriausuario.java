/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;



/**
 *
 * @author osdmdz
 */

public class Categoriausuario{

Integer idcategoriausuario;
String nombre;
String descripcion;
int estado;
String codigo;

    public Integer getIdcategoriausuario() {
        return idcategoriausuario;
    }

    public void setIdcategoriausuario(Integer idcategoriausuario) {
        this.idcategoriausuario = idcategoriausuario;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
