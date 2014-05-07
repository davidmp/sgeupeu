/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import sge.dao.OrganizacionDao;
import sge.modelo.Eap;
import sge.modelo.Eapfacultad;
import sge.modelo.Facultad;
import sge.modelo.Filial;
import sge.modelo.Filialfacultad;
import sge.modelo.Institucion;
import sge.modelo.Periodo;

/**
 *
 * @author Edwin
 */
public class OrganizacionService {
     
    public OrganizacionDao dao;
    
    public List Listar_Datos_Institucion() {
        dao = new OrganizacionDao();
        return dao.Listar_Datos_Institucion();
    }
    
    public Institucion Buscar_Institucion_Id(int id) {
        dao = new OrganizacionDao();
        return dao.Buscar_Institucion_Id(id);
    }
    
      public void Actualizar_Datos_Institucion(HttpServletRequest r, int idinstitucion) {

        Institucion to = new Institucion();
        dao = new OrganizacionDao();
        
        to.setIdinstitucion(Integer.parseInt(r.getParameter("idinstitucion")));
        to.setRazonsocial(r.getParameter("razonsocial") == null ? "" : r.getParameter("razonsocial"));
        to.setRuc(r.getParameter("ruc") == null ? "" : r.getParameter("ruc"));
        to.setDireccion(r.getParameter("direccion") == null ? "" : r.getParameter("direccion"));
        to.setTelefono(r.getParameter("telefono") == null ? "" : r.getParameter("telefono"));
        to.setCelular(r.getParameter("celular") == null ? "" : r.getParameter("celular"));
        to.setEmail(r.getParameter("email") == null ? "" : r.getParameter("email"));
        to.setPaginaweb(r.getParameter("paginaweb") == null ? "" : r.getParameter("paginaweb"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setRector(r.getParameter("rector") == null ? "" : r.getParameter("rector"));
        
        dao.Actualizar_Datos_Institucion(to, idinstitucion);

    }
      
      
      // TODO DE FILIAL
      
      public List Listar_Filial() {
        dao = new OrganizacionDao();
        return dao.Listar_Filial();
    }
      
      public void Registrar_Filial(HttpServletRequest r) {
        Filial to = new Filial();
        dao = new OrganizacionDao();
        to.setDireccion(r.getParameter("direccion") == null ? "" : r.getParameter("direccion"));
        to.setTelefono(r.getParameter("telefono") == null ? "" : r.getParameter("telefono"));
        to.setCelular(r.getParameter("celular") == null ? "" : r.getParameter("celular"));
        to.setEmail(r.getParameter("email") == null ? "" : r.getParameter("email"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setCategoria(r.getParameter("categoria") == null ? "" : r.getParameter("categoria"));
        to.setEstado(r.getParameter("estado") == null ? "" : r.getParameter("estado"));
        to.setIdinstitucion(Integer.parseInt(r.getParameter("idinstitucion")));
        to.setRector(r.getParameter("rector") == null ? "" : r.getParameter("rector"));
        dao.Registrar_Filial(to);
       }
      
      public Filial BuscarFilialId(String id) {
        dao = new OrganizacionDao();
        return dao.BuscarFilialId(id);
    }
      public void EliminarFilial(int idFilial) {
        dao = new OrganizacionDao();
        dao.EliminarFilial(idFilial);
    }
      public void ActualizarDatosFilial(HttpServletRequest r, int idFilial) {

        Filial to = new Filial();
        dao = new OrganizacionDao();
        to.setIdfilial(Integer.parseInt(r.getParameter("idFilial")));
        to.setDireccion(r.getParameter("direccion") == null ? "" : r.getParameter("direccion"));
        to.setTelefono(r.getParameter("telefono") == null ? "" : r.getParameter("telefono"));
        to.setCelular(r.getParameter("celular") == null ? "" : r.getParameter("celular"));
        to.setEmail(r.getParameter("email") == null ? "" : r.getParameter("email"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setCategoria(r.getParameter("categoria") == null ? "" : r.getParameter("categoria"));
        to.setEstado(r.getParameter("estado") == null ? "" : r.getParameter("estado"));
        to.setIdinstitucion(Integer.parseInt(r.getParameter("idinstitucion")));
        to.setRector(r.getParameter("rector") == null ? "" : r.getParameter("rector"));
        dao.ActualizarDatosFilial(to, idFilial);

    }
      // TODO DE FACULTAD
      
      public List Listar_Facultad() {
        dao = new OrganizacionDao();
        return dao.Listar_Facultades();
    }
      public List Listar_FacultadesAreas(int idtipoarea) {
        dao = new OrganizacionDao();
        return dao.Listar_FacultadesAreas(idtipoarea);
    }
     public void Registrar_Facultad(HttpServletRequest r) {
        Facultad to = new Facultad();
        dao = new OrganizacionDao();
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        dao.Registrar_Facultad(to);

    }
     
      public Facultad Buscar_Facultad_Id(int id) {
        dao = new OrganizacionDao();
        return dao.Buscar_Facultad_Id(id);
    }
      
        public void Actualizar_Facultad(HttpServletRequest r, int idfacultad) {

        Facultad to = new Facultad();
        dao = new OrganizacionDao();
        to.setIdfacultad(Integer.parseInt(r.getParameter("idfacultad")));
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        dao.Actualizar_Facultad(to, idfacultad);

    }
        public void EliminarFacultad(int idFacultad) {
        dao = new OrganizacionDao();
        dao.EliminarFacultad(idFacultad);
        }
     
    
    // eap
        
       public List Listar_Eap(int idTipoarea){
       dao = new OrganizacionDao();
       return dao.Listar_EAP(idTipoarea);
       
       }
       public List Listar_EapPrimero(){
       dao = new OrganizacionDao();
       return dao.Listar_EAPPrimero();
       
       }
       
       public void Registrar_Eap(HttpServletRequest r) {
       
        Eap to = new Eap();
        dao = new OrganizacionDao();
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setCodigo(r.getParameter("codigo") == null ? "" : r.getParameter("codigo"));
        to.setIdtipoarea(Integer.parseInt(r.getParameter("tipoarea") == null ? "0" : r.getParameter("tipoarea")));
        dao.Registrar_EAP(to);

       }
       
       public Eap BuscarEapIdId(String id) {
       dao = new OrganizacionDao();
       return dao.BuscarEapIdId(id);
       }

      
        public void ActualizarDatosEap(HttpServletRequest r, int idEap) {

        Eap to = new Eap();
        dao = new OrganizacionDao();
        to.setIdeap(Integer.parseInt(r.getParameter("idEap")));
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setCodigo(r.getParameter("codigo") == null ? "" : r.getParameter("codigo"));
        to.setIdtipoarea(Integer.parseInt(r.getParameter("tipoarea") == null ? "0" : r.getParameter("tipoarea")));
        dao.ActualizarEap(to, idEap);

        
        }
        public void EliminarEap(int idEap) {
        dao = new OrganizacionDao();
        dao.EliminarEap(idEap);
        }
     
    
        // PERIODO
       
       public List Listar_Periodo(){
       dao = new OrganizacionDao();
       return dao.Listar_Periodo();
       }

       public List Listar_Periodo_Activo(){
       dao = new OrganizacionDao();
       return dao.Listar_Periodo_Activo();
       }
       public void Registrar_Periodo(HttpServletRequest r) {
           Periodo to = new Periodo();
        dao = new OrganizacionDao();
        to.setPeriodo(r.getParameter("periodo") == null ? "" : r.getParameter("periodo"));
        to.setFechainicio(r.getParameter("fechainicio") == null ? "" : r.getParameter("fechainicio"));
        to.setFechafin(r.getParameter("fechafin") == null ? "" : r.getParameter("fechafin"));
        to.setIdtemporada(Integer.parseInt(r.getParameter("temporada") == null ? "0" : r.getParameter("temporada")));
        dao.Registrar_Periodo(to);
       }
       public void Activar_Periodo(int idPeriodo) {
       dao = new OrganizacionDao();
       dao.Activar_Periodo(idPeriodo);
        
       }
        
       public void Desactivar_Periodo(int idPeriodo) {
       dao = new OrganizacionDao();
       dao.Desactivar_Periodo(idPeriodo);
        
       }
       public Periodo buscarPeriodoId(String id) {
       dao = new OrganizacionDao();
       return dao.buscarPeriodoId(id);
       }
      
       public void actualizarDatosPeriodo(HttpServletRequest r, int idPeriodo) {

        Periodo to = new Periodo();
        dao = new OrganizacionDao();
        to.setIdperiodo(Integer.parseInt(r.getParameter("idPeriodo")));
        to.setPeriodo(r.getParameter("periodo") == null ? "" : r.getParameter("periodo"));
        to.setEstado(r.getParameter("estado") == null ? "" : r.getParameter("estado"));
        to.setFechainicio(r.getParameter("fechainicio") == null ? "" : r.getParameter("fechainicio"));
        to.setFechafin(r.getParameter("fechafin") == null ? "" : r.getParameter("fechafin"));
        to.setIdtemporada(Integer.parseInt(r.getParameter("temporada") == null ? "0" : r.getParameter("temporada")));
        dao.actualizarDatosPeriodo(to, idPeriodo);

        }
       
        public void EliminarPeriodo(int idPeriodo) {
        dao = new OrganizacionDao();
        dao.EliminarPeriodo(idPeriodo);
        }
       
       
       // -------------------- fin de periodo
       
        public List listar_filial_facultad_desde_usuario(String idfilial, int idtipoarea) {
        dao = new OrganizacionDao(); 
        return dao.listar_filial_facultad_desde_usuario(idfilial, idtipoarea);
        }
        public List listar_filial_facultad_desde_usuarioPrimero(String idfilial) {
        dao = new OrganizacionDao(); 
        return dao.listar_filial_facultad_desde_usuarioPrimero(idfilial);
        }
        
        public void Registrar_Filial_Facultad_Filial(HttpServletRequest r) {
            Filialfacultad to = new Filialfacultad();
        dao = new OrganizacionDao();
        to.setIdcoordinadorfacultad(Integer.parseInt(r.getParameter("idcoordinadorfacultad")));
        to.setIdfilial(Integer.parseInt(r.getParameter("idfilial")));
        to.setIdfacultad(Integer.parseInt(r.getParameter("idfacultad")));
        dao.Registrar_Filial_Facultad_Filial(to);
        }
        
        public void eliminar_Filialfacultad(int idFilialfacultad) {
        dao = new OrganizacionDao();
        dao.eliminar_Filialfacultad(idFilialfacultad);
        }
        public List Listar_Eap_FacultadPrimero(String idfilial, int idtipoarea) {
        dao = new OrganizacionDao(); 
        return dao.Listar_Eap_FacultadPrimero(idfilial, idtipoarea);
        }
        public List Listar_Eap_Facultad(String idfilial) {
        dao = new OrganizacionDao(); 
        return dao.Listar_Eap_Facultad(idfilial);
        }
        
        public void Registrar_EAP_Facultad_Filial(HttpServletRequest r) {
            Eapfacultad to = new Eapfacultad();
        dao = new OrganizacionDao();
        to.setIdfilialfacultad(Integer.parseInt(r.getParameter("idfilialfacultad")));
        to.setIdeap(Integer.parseInt(r.getParameter("ideap")));
        to.setIdcoordinadoreap(Integer.parseInt(r.getParameter("idcoordinadoreap")));
        dao.Registrar_EAP_Facultad_filial(to);
        }
        
       
        public void eliminar_EAP_Facultad_Filial______delete(int idEapFacultad) {
        dao = new OrganizacionDao();
        dao.eliminar_EAP_Facultad_Filial______delete(idEapFacultad);
        }
        
        public List Listar_Eap_Facultad_Perfil(String idfilial,String idFilialfacultad) {
        dao = new OrganizacionDao(); 
        return dao.Listar_Eap_Facultad_Perfil(idfilial, idFilialfacultad);
        }

    public Eap Buscar_Eap_Id(HttpServletRequest request, int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
    public List Listar_FililFacultad(String idfilial) {
        dao = new OrganizacionDao(); 
        return dao.Listar_FililFacultad(idfilial);
        }
        public List reporteFilialFacultad() {
        dao = new OrganizacionDao(); 
        return dao.reporteFilialFacultad();
        }

        public List reporteFacultadEap() {
        dao = new OrganizacionDao(); 
        return dao.reporteFacultadEap();
        }
        
        public List ListarTemporadaActivaWeb() {
        dao = new OrganizacionDao(); 
        return dao.ListarTemporadaActivaWeb();
        }


        
    public Filialfacultad getFilialFacultadId(int id){
    dao = new OrganizacionDao();
    return dao.getFilialFacultadId(id);
    }
    
    
     public Filial getFilialId(int id){
    dao = new OrganizacionDao();
    return dao.getFilialId(id);
    }
}
