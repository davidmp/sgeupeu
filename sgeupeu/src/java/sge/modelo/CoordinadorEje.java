/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;

/**
 *
 * @author Intel
 */
public class CoordinadorEje {
    private int idcoordinadoreje;
    private int idPeriodo;
    private int idPersona;  
    
    private String nombrepersona;
    private String dni;
    private String email;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    
    public String getNombrepersona() {
        return nombrepersona;
    }

    public void setNombrepersona(String nombrepersona) {
        this.nombrepersona = nombrepersona;
    }

    
    
    
    public int getIdcoordinadoreje() {
        return idcoordinadoreje;
    }

    public void setIdcoordinadoreje(int idcoordinadoreje) {
        this.idcoordinadoreje = idcoordinadoreje;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    
    
    
}
