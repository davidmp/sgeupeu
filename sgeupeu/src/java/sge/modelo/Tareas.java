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
public class Tareas {
    int idtarea;
    String  nombre;
    String  fecha;
    String  fecha2;
    double  monto;
    int idActividad;
    int idSubcuenta;
    double rh;
    double materiales;
    double equipos;
    double ap;
    double movilidad;
    double otros;
    String nombreSubcuanta;

    public int getIdtarea() {
        return idtarea;
    }

    public void setIdtarea(int idtarea) {
        this.idtarea = idtarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }



    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdSubcuenta() {
        return idSubcuenta;
    }

    public void setIdSubcuenta(int idSubcuenta) {
        this.idSubcuenta = idSubcuenta;
    }

    public String getNombreSubcuanta() {
        return nombreSubcuanta;
    }

    public void setNombreSubcuanta(String nombreSubcuanta) {
        this.nombreSubcuanta = nombreSubcuanta;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public double getRh() {
        return rh;
    }

    public void setRh(double rh) {
        this.rh = rh;
    }

    public double getMateriales() {
        return materiales;
    }

    public void setMateriales(double materiales) {
        this.materiales = materiales;
    }

    public double getEquipos() {
        return equipos;
    }

    public void setEquipos(double equipos) {
        this.equipos = equipos;
    }

    public double getAp() {
        return ap;
    }

    public void setAp(double ap) {
        this.ap = ap;
    }

    public double getMovilidad() {
        return movilidad;
    }

    public void setMovilidad(double movilidad) {
        this.movilidad = movilidad;
    }

    public double getOtros() {
        return otros;
    }

    public void setOtros(double otros) {
        this.otros = otros;
    }
    
    
}
