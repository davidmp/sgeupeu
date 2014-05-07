/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;

/**
 *
 * @author oscdmdz
 */
public class Evidencia {
   
Integer idevidencia;
Integer nro;
String evidencia;
String tipo;
String url;
Integer idmeta;
Integer idavance;
String fecha_reg;

    public Integer getIdavance() {
        return idavance;
    }

    public void setIdavance(Integer idavance) {
        this.idavance = idavance;
    }



    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }



    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public Integer getIdevidencia() {
        return idevidencia;
    }

    public void setIdevidencia(Integer idevidencia) {
        this.idevidencia = idevidencia;
    }

    public Integer getIdmeta() {
        return idmeta;
    }

    public void setIdmeta(Integer idmeta) {
        this.idmeta = idmeta;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
