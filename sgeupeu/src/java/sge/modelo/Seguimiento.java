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
public class Seguimiento {

    
    int idSeguimiento, idAvance, idCiclo, idTipoSeguim, idUserObs, estado, idUserView, idUserResp;
    String fechaObs, observacion, fechaView, fechaResp;

    public int getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(int idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    public int getIdAvance() {
        return idAvance;
    }

    public void setIdAvance(int idAvance) {
        this.idAvance = idAvance;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public int getIdTipoSeguim() {
        return idTipoSeguim;
    }

    public void setIdTipoSeguim(int idTipoSeguim) {
        this.idTipoSeguim = idTipoSeguim;
    }

    public int getIdUserObs() {
        return idUserObs;
    }

    public void setIdUserObs(int idUserObs) {
        this.idUserObs = idUserObs;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdUserView() {
        return idUserView;
    }

    public void setIdUserView(int idUserView) {
        this.idUserView = idUserView;
    }

    public int getIdUserResp() {
        return idUserResp;
    }

    public void setIdUserResp(int idUserResp) {
        this.idUserResp = idUserResp;
    }

    public String getFechaObs() {
        return fechaObs;
    }

    public void setFechaObs(String fechaObs) {
        this.fechaObs = fechaObs;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFechaView() {
        return fechaView;
    }

    public void setFechaView(String fechaView) {
        this.fechaView = fechaView;
    }

    public String getFechaResp() {
        return fechaResp;
    }

    public void setFechaResp(String fechaResp) {
        this.fechaResp = fechaResp;
    }
    
    
    
}
