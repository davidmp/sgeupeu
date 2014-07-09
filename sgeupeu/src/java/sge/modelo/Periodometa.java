/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;

/**
 *
 * @author osdmdz
 */
public class Periodometa {
   
Integer idperiodometa;
String periodo;
String fechainicio;
String fechafin;
Integer idtemporada;
Integer estado;

    public Integer getIdperiodometa() {
        return idperiodometa;
    }

    public void setIdperiodometa(Integer idperiodometa) {
        this.idperiodometa = idperiodometa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getIdtemporada() {
        return idtemporada;
    }

    public void setIdtemporada(Integer idtemporada) {
        this.idtemporada = idtemporada;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    
    
    
}
