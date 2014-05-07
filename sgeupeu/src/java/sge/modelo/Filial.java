/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;


/**
 *
 * @author 
 */
public class Filial{
    
int idfilial;
String direccion;
String telefono;
String celular;
String email;
String descripcion;
String categoria;
String estado;
int idinstitucion;
String idinstitucion_name;
String rector;

Integer consolidado;

    public int getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(int idfilial) {
        this.idfilial = idfilial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(int idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public String getIdinstitucion_name() {
        return idinstitucion_name;
    }

    public void setIdinstitucion_name(String idinstitucion_name) {
        this.idinstitucion_name = idinstitucion_name;
    }

    public String getRector() {
        return rector;
    }

    public void setRector(String rector) {
        this.rector = rector;
    }

    public Integer getConsolidado() {
        return consolidado;
    }

    public void setConsolidado(Integer consolidado) {
        this.consolidado = consolidado;
    }

    
}
