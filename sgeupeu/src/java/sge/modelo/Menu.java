/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;



/**
 *
 * @author osdmdz
 */

public class Menu{

Integer idmenu;
String nombre;
int estado;
String alias;

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
