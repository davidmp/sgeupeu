/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import sge.dao.UsuarioDao;
import sge.modelo.CoordinadorEje;
import sge.modelo.CoordinadorEjeeap;
import sge.modelo.Coordinadoreap;
import sge.modelo.Coordinadorfacultad;
import sge.modelo.Eap;
import sge.modelo.Facultad;
import sge.modelo.Filial;
import sge.modelo.Periodo;
import sge.modelo.Persona;
import sge.modelo.Privilegio;
import sge.modelo.Temporada;
import sge.modelo.Tipoarea;
import sge.modelo.Usuario;

/**
 *
 * @author edwin
 */
public class UsuarioService {
    
    public UsuarioDao dao;
    public List listar_usuario() {
        dao = new UsuarioDao();
        return dao.listar_usuario();
    }
    public int compararClaveAnterior(int idUsuario, String clave) {
        dao = new UsuarioDao();
        return dao.compararClaveAnterior(idUsuario, clave);
    }
    public void cambiarClaveUsuario(int idUsuario, String clave) {
        dao = new UsuarioDao();
        dao.cambiarClaveUsuario(idUsuario, clave);
    }
    public List listar_categoria_usuario() {
        dao = new UsuarioDao();
        return dao.listar_categoria_usuario();
    }
    public List listar_categoria_usuario_filial() {
        dao = new UsuarioDao();
        return dao.listar_categoria_usuario_filial();
    }
    public List listar_filial_usuario() {
        dao = new UsuarioDao();
        return dao.listar_filial_usuario();
    }
    public List listar_persona() {
        dao = new UsuarioDao();
        return dao.listar_persona();
    }
    
    
    public void registrar_usuario(HttpServletRequest r) {
        Usuario to = new Usuario();
        dao = new UsuarioDao();

        to.setUsuario(r.getParameter("usuario") == null ? "" : r.getParameter("usuario"));
        to.setPassword(r.getParameter("password") == null ? "" : r.getParameter("password"));
        to.setEstado(r.getParameter("estado") == null ? "" : r.getParameter("estado"));
        to.setFechaAcceso(r.getParameter("fecha_acceso") == null ? "" : r.getParameter("fecha_acceso"));
        to.setFechaAlta(r.getParameter("fecha_alta") == null ? "" : r.getParameter("fecha_alta"));
        to.setIdpersona(r.getParameter("idpersona") == null ? "" : r.getParameter("idpersona"));
        to.setIdfilial(r.getParameter("idfilial") == null ? "" : r.getParameter("idfilial"));
        
        String idCategoria=r.getParameter("idcategoriausuario") == null ? "" : r.getParameter("idcategoriausuario");
        to.setIdcategoriausuario(idCategoria);
        
        
   
        
        to.setIdtipoarea(Integer.parseInt(r.getParameter("idareaunidad") == null ? "0" : r.getParameter("idareaunidad")));
        to.setIdejeestrategico(Integer.parseInt(r.getParameter("idejeestrateico") == null ? "0" : r.getParameter("idejeestrateico")));
        to.setIdperspectiva(Integer.parseInt(r.getParameter("idperspectiva") == null ? "0" : r.getParameter("idperspectiva")));        
        dao.registrar_usuario(to);
        
        
        
        dao = new UsuarioDao();
        int idPeriodo=dao.idPeriodoActivo();
        if(Integer.parseInt(idCategoria)==5){
            System.out.println("VER:"+Integer.parseInt(r.getParameter("idpersona") == null ? "0" : r.getParameter("idpersona")));
            System.out.println("VER:"+idCategoria);            
        dao = new UsuarioDao();
        CoordinadorEje tr=new CoordinadorEje();
        tr.setIdPeriodo(idPeriodo);
        tr.setIdPersona(Integer.parseInt(r.getParameter("idpersona") == null ? "0" : r.getParameter("idpersona")));
     
        dao.registrarCoordinadorEje(tr);
        }      
    }
     public void eliminar_usuario(int idusuario) {
        dao = new UsuarioDao();
        dao.eliminar_usuario(idusuario);
    }
     
      public void Activar_Usuario(int idUsuario) {
        dao = new UsuarioDao();
        dao.Activar_Usuario(idUsuario);
    }
    public void Desactivar_Usuario(int idUsuario) {
        dao = new UsuarioDao();
        dao.Desactivar_Usuario(idUsuario);
    }
    
     
 public List<Usuario> validar(HttpServletRequest rq){
 UsuarioDao daos= new UsuarioDao();
 Usuario to=new Usuario();
 to.setUsuario(rq.getParameter("usuario_txt"));
 to.setPassword(rq.getParameter("password_txt"));
 return daos.validar(to);
 }
 public Periodo periodoVigenteUPeU(){
  dao= new UsuarioDao();
 return dao.periodoVigenteUPeU();
 }
 public String Id_usuario_session(HttpServletRequest rq){
 UsuarioDao daoid= new UsuarioDao();
 Usuario tos=new Usuario();
 tos.setUsuario(rq.getParameter("usuario_txt"));
 tos.setPassword(rq.getParameter("password_txt"));
 return daoid.Id_usuario_session(tos);
 }
 public String Id_filial_usuario(HttpServletRequest rq){
 UsuarioDao daoidf= new UsuarioDao();
 Usuario tosf=new Usuario();
 tosf.setUsuario(rq.getParameter("usuario_txt"));
 tosf.setPassword(rq.getParameter("password_txt"));
 return daoidf.Id_filial_usuario(tosf);
 }
 public String Id_categoria_usuario(HttpServletRequest rq){
 UsuarioDao daoidc= new UsuarioDao();
 Usuario tosc=new Usuario();
 tosc.setUsuario(rq.getParameter("usuario_txt"));
 tosc.setPassword(rq.getParameter("password_txt"));
 return daoidc.Id_categoria_usuario(tosc);
 }
 
 public List listar_usuario_filial(String idfilial) {
 dao = new UsuarioDao();
 return dao.listar_usuario_filial(idfilial);
 }
 public List listar_usuario_filial_priv(String idfilial) {
 dao = new UsuarioDao();
 return dao.listar_usuario_filial_priv(idfilial);
 }
 
 public List listar_personas_filial(String idfilial) {
 dao = new UsuarioDao();
 return dao.listar_personas_filial(idfilial);
 }
 
 
 public Usuario datos_usuario(String idusuario) {
        dao = new UsuarioDao();
        return dao.datos_usuario(idusuario);
    }
 public void registrar_persona(HttpServletRequest r) {
        Persona to = new Persona();
        dao = new UsuarioDao();

        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setApellipaterno(r.getParameter("apellipaterno") == null ? "" : r.getParameter("apellipaterno"));
        to.setApellimaterno(r.getParameter("apellimaterno") == null ? "" : r.getParameter("apellimaterno"));
        to.setDni(r.getParameter("dni") == null ? "" : r.getParameter("dni"));
        to.setSexo(r.getParameter("sexo") == null ? "" : r.getParameter("sexo"));
        to.setTelefono(r.getParameter("telefono") == null ? "" : r.getParameter("telefono"));
        to.setCelular(r.getParameter("celular") == null ? "" : r.getParameter("celular"));
        to.setEmail(r.getParameter("email") == null ? "" : r.getParameter("email"));
        to.setFechanacimiento(r.getParameter("fechanacimiento") == null ? "" : r.getParameter("fechanacimiento"));
        to.setIdfilial(Integer.parseInt(r.getParameter("idfilial")));
        //to.setIdfilial(r.getParameter("idcategoriausuario") == null ? "" : r.getParameter("idcategoriausuario"));
        dao.registrar_persona(to);

    }
 
  public void eliminar_persona(int idpersona) {
        dao = new UsuarioDao();
        dao.eliminar_persona(idpersona);
    }
  public Persona Buscar_Persona_Id(String id) {
        dao = new UsuarioDao();
        return dao.Buscar_Persona_Id(id);
    }

  public Usuario Buscar_Usuario_Id(String idusuario) {
        dao = new UsuarioDao();
        return dao.Buscar_Usuario_Id(idusuario);
    }

    public void Actualizar_Datos_Personas(HttpServletRequest r, int idpersona) {

        Persona to = new Persona();
        dao = new UsuarioDao();
        to.setIdpersona(Integer.parseInt(r.getParameter("idpersona")));
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setApellipaterno(r.getParameter("apellipaterno") == null ? "" : r.getParameter("apellipaterno"));
        to.setApellimaterno(r.getParameter("apellimaterno") == null ? "" : r.getParameter("apellimaterno"));
        to.setDni(r.getParameter("dni") == null ? "" : r.getParameter("dni"));
        to.setSexo(r.getParameter("sexo") == null ? "" : r.getParameter("sexo"));
        to.setTelefono(r.getParameter("telefono") == null ? "" : r.getParameter("telefono"));
        to.setCelular(r.getParameter("celular") == null ? "" : r.getParameter("celular"));
        to.setEmail(r.getParameter("email") == null ? "" : r.getParameter("email"));
        to.setFechanacimiento(r.getParameter("fechanacimiento") == null ? "" : r.getParameter("fechanacimiento"));
        to.setIdfilial(Integer.parseInt(r.getParameter("idfilial")));

        dao.Actualizar_Datos_Persona(to, idpersona);

    }
    
    public void Actualizar_Datos_Usuario(HttpServletRequest r, int idusuario) {

        Usuario to = new Usuario();
        dao = new UsuarioDao();
        to.setUsuario(r.getParameter("usuario") == null ? "" : r.getParameter("usuario"));
        to.setPassword(r.getParameter("password") == null ? "" : r.getParameter("password"));
        to.setEstado(r.getParameter("estado") == null ? "" : r.getParameter("estado"));
        to.setFechaAcceso(r.getParameter("fecha_acceso") == null ? "" : r.getParameter("fecha_acceso"));
        to.setIdpersona(r.getParameter("idpersona") == null ? "" : r.getParameter("idpersona"));
        to.setIdfilial(r.getParameter("idfilial") == null ? "" : r.getParameter("idfilial"));
        to.setIdcategoriausuario(r.getParameter("idcategoriausuario") == null ? "" : r.getParameter("idcategoriausuario"));
        dao.Actualizar_Datos_Usuario(to, idusuario);

    }
    
 public List Autocomplete_Persona(String dni) {
 dao = new UsuarioDao();
 return dao.Autocomplete_Persona(dni);
 }
 
 public void registro_privilegio_array(HttpServletRequest r) {

        Privilegio to = new Privilegio();
        dao = new UsuarioDao();
        to.setIdusuario(r.getParameter("idusuario") == null ? "" : r.getParameter("idusuario"));
        to.setIdacceso_array((String[]) (r.getParameterValues("idacceso") == null ? "" : r.getParameterValues("idacceso")));
        to.setEstado((String[]) (r.getParameterValues("estado") == null ? "" : r.getParameterValues("estado")));
        dao.registro_privilegio_array(to);

    }
 public List Lista_Accesos() {
        dao = new UsuarioDao();
        return dao.Lista_Accesos();
    }
 public List coordinadorEje(int idFilial){
        dao = new UsuarioDao();
        return dao.coordinadorEje(idFilial);
 }
 public List listarCoordinadoresEjeEaps(int idFilal, int idEapFilial){
        dao = new UsuarioDao();
        return dao.listarCoordinadoresEjeEaps(idFilal, idEapFilial);
 }
 public int registrarEapCoordinadorEje(CoordinadorEjeeap to){
        dao = new UsuarioDao();
        return dao.registrarEapCoordinadorEje(to);
 }
 
 
 public List Proximo_IDPersona() {
        dao = new UsuarioDao();
        return dao.Proximo_IDPersona();
    }
public int extraUltimoIdPersona(String dni){
        dao = new UsuarioDao();
        return dao.extraUltimoIdPersona(dni);
}
 
 public void Registrar_Datos_Cuenta(HttpServletRequest r) {
        Persona to = new Persona();
        dao = new UsuarioDao();
        to.setIdpersona(Integer.parseInt(r.getParameter("idpersona")));
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setApellipaterno(r.getParameter("apellipaterno") == null ? "" : r.getParameter("apellipaterno"));
        to.setApellimaterno(r.getParameter("apellimaterno") == null ? "" : r.getParameter("apellimaterno"));
        to.setDni(r.getParameter("dni") == null ? "" : r.getParameter("dni"));
        to.setSexo(r.getParameter("sexo") == null ? "" : r.getParameter("sexo"));
        to.setTelefono(r.getParameter("telefono") == null ? "" : r.getParameter("telefono"));
        to.setCelular(r.getParameter("celular") == null ? "" : r.getParameter("celular"));
        to.setEmail(r.getParameter("email") == null ? "" : r.getParameter("email"));
        to.setFechanacimiento(r.getParameter("fechanacimiento") == null ? "" : r.getParameter("fechanacimiento"));
        to.setIdfilial(Integer.parseInt(r.getParameter("idfilial")));
        //to.setIdfilial(r.getParameter("idcategoriausuario") == null ? "" : r.getParameter("idcategoriausuario"));
        dao.registrar_persona(to);

    }
    public List listar_Coordinador_facultad(String idPeriodo, String idfilial) {
    dao = new UsuarioDao();
    return dao.listar_Coordinador_facultad(idPeriodo, idfilial);
    }
    
    public List listar_Coordinador_facultad_ALL(String idfilial) {
    dao = new UsuarioDao();
    return dao.listar_Coordinador_facultad_ALL(idfilial);
    }
    
    public void Registrar_Coordinador_Facultad(HttpServletRequest r) {
        Coordinadorfacultad to = new Coordinadorfacultad();
        dao = new UsuarioDao();
        to.setIdpersona(Integer.parseInt(r.getParameter("idpersona")));
        to.setIdperiodo(Integer.parseInt(r.getParameter("idPeriodo")));
        dao.Registrar_Coordinador_Facultad(to);

    }
    public void EliminarCoordinadorFacultad(int idCoordinadorFacultad) {
        dao = new UsuarioDao();
        dao.EliminarCoordinadorFacultad(idCoordinadorFacultad);
    }

    public Coordinadorfacultad buscarCoordinadorFacultadId(String id) {
        dao = new UsuarioDao();
        return dao.buscarCoordinadorFacultadId(id);
    }

    public void ActualizarCoordinadorfacultad(HttpServletRequest r, int idCoordinadorFacultad) {

        Coordinadorfacultad to = new Coordinadorfacultad();
        dao = new UsuarioDao();
        to.setIdcoordinadorfacultad(Integer.parseInt(r.getParameter("idCoordinadorFacultad")));
        to.setIdpersona(Integer.parseInt(r.getParameter("idpersona")));
        to.setIdperiodo(Integer.parseInt(r.getParameter("idPeriodo")));
        dao.ActualizarCoordinadorfacultad(to, idCoordinadorFacultad);

    }
    
    public List listar_Coordinador_EAP(String idPeriodo, String idfilial) {
    dao = new UsuarioDao();
    return dao.listar_Coordinador_EAP(idPeriodo, idfilial);
    }

    public List listar_Coordinador_EAP_ALL(String idfilial) {
    dao = new UsuarioDao();
    return dao.listar_Coordinador_EAP_ALL(idfilial);
    }

    public void Registrar_Coordinador_EAP(HttpServletRequest r) {
        Coordinadoreap to = new Coordinadoreap();
        dao = new UsuarioDao();
        to.setIdpersona(Integer.parseInt(r.getParameter("idpersona")));
        to.setIdperiodo(Integer.parseInt(r.getParameter("idPeriodo")));
        dao.Registrar_Coordinador_EAP(to);

    }
    public void EliminarCoordinadorEAP(int idCoordinadorEap) {
        dao = new UsuarioDao();
        dao.EliminarCoordinadorEAP(idCoordinadorEap);
    }

    public Coordinadoreap buscarCoordinadorEAPId(String id) {
        dao = new UsuarioDao();
        return dao.buscarCoordinadorEAPId(id);
    }

    public void ActualizarCoordinadorEAP(HttpServletRequest r, int idCoordinadorEap) {

        Coordinadoreap to = new Coordinadoreap();
        dao = new UsuarioDao();
        to.setIdCoordinadorEap(Integer.parseInt(r.getParameter("idCoordinadorEap")));
        to.setIdpersona(Integer.parseInt(r.getParameter("idpersona")));
        to.setIdperiodo(Integer.parseInt(r.getParameter("idPeriodo")));
        dao.ActualizarCoordinadorEAP(to, idCoordinadorEap);

    }
     public int Id_usuario(HttpServletRequest rq){
 UsuarioDao daoid= new UsuarioDao();
 Usuario tos=new Usuario();
 tos.setUsuario(rq.getParameter("usuario_txt"));
 tos.setPassword(rq.getParameter("password_txt"));
 return daoid.Id_usuario(tos);
 }
  public Eap eapUsuario(int idusuario){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.eapUsuario(idusuario);
 }
  public Eap eapUsuarioEje(int idusuario, int idFilial){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.eapUsuarioEje(idusuario, idFilial);
 }
  public Facultad facultadUsuario(int idusuario){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.facultadUsuario(idusuario);
 }  
 public Facultad facultadUsuarioRealSelect(int idUsuario){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.facultadUsuarioRealSelect(idUsuario);
 }  
  public Facultad facultadUsuarioEje(int idusuario, int idFilial){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.facultadUsuarioEje(idusuario, idFilial);
 }  
 public List areasUnidadUpeU(){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.areasUnidadUpeU();
 }  
 public List perspectivasUPeU(){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.perspectivasUPeU();
 }  
 public List ejesUPeU(){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.ejesUPeU();
 }
 
  
  public Filial filialUsuario(int idusuario){
 UsuarioDao daoidf= new UsuarioDao();
 return daoidf.filialUsuario(idusuario);
 }
 
}
