/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.modelo;

/**
 *
 * @author Intel
 */
public class NivelFinanciero {

    int idNivelFinanciero;
    String nombre;
    String codigo;
    String sigla;
    int estado;

    public int getIdNivelFinanciero() {
        return idNivelFinanciero;
    }

    public void setIdNivelFinanciero(int idNivelFinanciero) {
        this.idNivelFinanciero = idNivelFinanciero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    
}
