/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;



/**
 *
 * @author osdmdz
 */
public class Facultad{

int idfacultad;
String nombre;
String descripcion;
String tipoarea;
int idtipoarea;

Integer idfilialfacultad;
Integer consolidado;

    public String getTipoarea() {
        return tipoarea;
    }

    public void setTipoarea(String tipoarea) {
        this.tipoarea = tipoarea;
    }

    public int getIdtipoarea() {
        return idtipoarea;
    }

    public void setIdtipoarea(int idtipoarea) {
        this.idtipoarea = idtipoarea;
    }



    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
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

    public Integer getIdfilialfacultad() {
        return idfilialfacultad;
    }

    public void setIdfilialfacultad(Integer idfilialfacultad) {
        this.idfilialfacultad = idfilialfacultad;
    }

    public Integer getConsolidado() {
        return consolidado;
    }

    public void setConsolidado(Integer consolidado) {
        this.consolidado = consolidado;
    }

    
    
}
