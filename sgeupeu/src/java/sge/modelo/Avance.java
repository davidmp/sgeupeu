/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;

import java.util.Date;



/**
 *
 * @author osdmdz
 */

public class Avance{

Integer idavance;

Date fecha;
String evidencia;
int idmeta;

String meta;
String estado;
int idusuario;
int id_ciclo;

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(int id_ciclo) {
        this.id_ciclo = id_ciclo;
    }




    public Integer getIdavance() {
        return idavance;
    }

    public void setIdavance(Integer idavance) {
        this.idavance = idavance;
    }



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public int getIdmeta() {
        return idmeta;
    }

    public void setIdmeta(int idmeta) {
        this.idmeta = idmeta;
    }


   
}
