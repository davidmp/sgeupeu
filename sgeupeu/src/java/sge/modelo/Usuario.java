/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.modelo;



public class Usuario {

Integer id;   
String idusuario;
String usuario; 
String password;
String estado;
String fechaAcceso; 
String fechaAlta; 
String idpersona;
String idpersona_nombre;
String idpersona_apell_paterno;
String idpersona_apell_materno;
String idfilial;
String idfilial_nombre;
String idcategoriausuario;
String idcategoriausuario_nombre;
int idperspectiva;
int idtipoarea;
int idejeestrategico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(String fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    public String getIdpersona_nombre() {
        return idpersona_nombre;
    }

    public void setIdpersona_nombre(String idpersona_nombre) {
        this.idpersona_nombre = idpersona_nombre;
    }

    public String getIdpersona_apell_paterno() {
        return idpersona_apell_paterno;
    }

    public void setIdpersona_apell_paterno(String idpersona_apell_paterno) {
        this.idpersona_apell_paterno = idpersona_apell_paterno;
    }

    public String getIdpersona_apell_materno() {
        return idpersona_apell_materno;
    }

    public void setIdpersona_apell_materno(String idpersona_apell_materno) {
        this.idpersona_apell_materno = idpersona_apell_materno;
    }

    public String getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(String idfilial) {
        this.idfilial = idfilial;
    }

    public String getIdfilial_nombre() {
        return idfilial_nombre;
    }

    public void setIdfilial_nombre(String idfilial_nombre) {
        this.idfilial_nombre = idfilial_nombre;
    }

    public String getIdcategoriausuario() {
        return idcategoriausuario;
    }

    public void setIdcategoriausuario(String idcategoriausuario) {
        this.idcategoriausuario = idcategoriausuario;
    }

    public String getIdcategoriausuario_nombre() {
        return idcategoriausuario_nombre;
    }

    public void setIdcategoriausuario_nombre(String idcategoriausuario_nombre) {
        this.idcategoriausuario_nombre = idcategoriausuario_nombre;
    }

    public int getIdperspectiva() {
        return idperspectiva;
    }

    public void setIdperspectiva(int idperspectiva) {
        this.idperspectiva = idperspectiva;
    }

    public int getIdtipoarea() {
        return idtipoarea;
    }

    public void setIdtipoarea(int idtipoarea) {
        this.idtipoarea = idtipoarea;
    }

    public int getIdejeestrategico() {
        return idejeestrategico;
    }

    public void setIdejeestrategico(int idejeestrategico) {
        this.idejeestrategico = idejeestrategico;
    }

    
}
