/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;

public class Temporada {
    
int idtemporada; 
String inicio;
String descripcion;
String fin;
String estado;

    public int getIdtemporada() {
        return idtemporada;
    }

    public void setIdtemporada(int idtemporada) {
        this.idtemporada = idtemporada;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
        }