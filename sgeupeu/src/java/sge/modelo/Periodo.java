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

public class Periodo{

int idperiodo;
String periodo;
String estado;
String fechainicio;
String fechafin;
int idtemporada;
String nombretemporada;

    public int getIdtemporada() {
        return idtemporada;
    }

    public void setIdtemporada(int idtemporada) {
        this.idtemporada = idtemporada;
    }

    public String getNombretemporada() {
        return nombretemporada;
    }

    public void setNombretemporada(String nombretemporada) {
        this.nombretemporada = nombretemporada;
    }


    public int getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(int idperiodo) {
        this.idperiodo = idperiodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
 