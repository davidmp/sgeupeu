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
public class Cuenta {

    int idCuenta;
    String nombre;
    String codigo;
    int operativo;
    int estado;
    int idFilial;
    int idNivelFinanciero;
    int idtipoarea;
    String nombreNivel;
    String siglaNivel;

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
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

    public int getOperativo() {
        return operativo;
    }

    public void setOperativo(int operativo) {
        this.operativo = operativo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    public int getIdNivelFinanciero() {
        return idNivelFinanciero;
    }

    public void setIdNivelFinanciero(int idNivelFinanciero) {
        this.idNivelFinanciero = idNivelFinanciero;
    }

    public int getIdtipoarea() {
        return idtipoarea;
    }

    public void setIdtipoarea(int idtipoarea) {
        this.idtipoarea = idtipoarea;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public String getSiglaNivel() {
        return siglaNivel;
    }

    public void setSiglaNivel(String siglaNivel) {
        this.siglaNivel = siglaNivel;
    }
   
    
    
}
