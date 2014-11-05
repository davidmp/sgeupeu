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
public class PartidaPresupuesto {
    int idParPresupuesto;
    int idCuenta;
    double porcentaje;
    double monto;
    int idPeriodo;
    int idEapFacultad;
    int estado;
    int saldoactual;

    public int getIdParPresupuesto() {
        return idParPresupuesto;
    }

    public void setIdParPresupuesto(int idParPresupuesto) {
        this.idParPresupuesto = idParPresupuesto;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getIdEapFacultad() {
        return idEapFacultad;
    }

    public void setIdEapFacultad(int idEapFacultad) {
        this.idEapFacultad = idEapFacultad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(int saldoactual) {
        this.saldoactual = saldoactual;
    }
    
    
    
}
