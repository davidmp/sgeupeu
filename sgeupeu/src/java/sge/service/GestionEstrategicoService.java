/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import sge.dao.GestionEstrategicoDao;
import sge.modelo.Actividadindicador;
import sge.modelo.Coordinadorfacultad;
import sge.modelo.EjeEstrategia;
import sge.modelo.Ejeestrategico;
import sge.modelo.Estadoperiodoeap;
import sge.modelo.Estadoperiodofacultad;
import sge.modelo.Estadoperiodofilial;
import sge.modelo.Estrategia;
import sge.modelo.EstrategiaIndicador;
import sge.modelo.Indicador;
import sge.modelo.Periodometa;
import sge.modelo.Politicaupeu;
import sge.modelo.Temporada;
import sge.modelo.TemporadaEjeObjEstrategico;

/**
 *
 * @author Edwin
 */
public class GestionEstrategicoService {
    
    public GestionEstrategicoDao dao;
    public List Listar_Politica_Upeu() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Politica_Upeu();
    }
     public Politicaupeu Buscar_Politica_Upeu_Id(String id) {
        dao = new GestionEstrategicoDao();
        return dao.Buscar_Politica_Upeu_Id(id);
    }
     public int idEjeXSeleccionada(int idTemporadaEje) {
        dao = new GestionEstrategicoDao();
        return dao.idEjeXSeleccionada(idTemporadaEje);
    }

    public void Actualizar_Datos_Politica_Upeu(HttpServletRequest r, int idpoliticasupeu) {

        Politicaupeu to = new Politicaupeu();
        dao = new GestionEstrategicoDao();
        to.setIdpoliticasupeu(Integer.parseInt(r.getParameter("idpoliticasupeu")));
        to.setMision(r.getParameter("mision") == null ? "" : r.getParameter("mision"));
        to.setVision(r.getParameter("vision") == null ? "" : r.getParameter("vision"));
        to.setEstado(r.getParameter("estado") == null ? "" : r.getParameter("estado"));
        dao.Actualizar_Datos_Politica_Upeu(to, idpoliticasupeu);

    }
    
    // EJE ESTRATEGICO
    
    public List Listar_Ejes_Estrategicos(int idtipoarea) {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Ejes_Estrategicos(idtipoarea);
    }
    public Ejeestrategico buscarEjeEstrategicoId(String id) {
        dao = new GestionEstrategicoDao();
        return dao.buscarEjeEstrategicoId(id);
    }
    public void ActualizarDatosEjeEstrategico(HttpServletRequest r, int idEjeEstrategico) {

        Ejeestrategico to = new Ejeestrategico();
        dao = new GestionEstrategicoDao();
        to.setIdejeestrategico(Integer.parseInt(r.getParameter("idEjeEstrategico")));
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setObjetivoestrategico(r.getParameter("objetivoestrategico") == null ? "" : r.getParameter("objetivoestrategico"));
        to.setMapaestrategico(r.getParameter("mapaestrategico") == null ? "" : r.getParameter("mapaestrategico"));
        dao.ActualizarDatosEjeEstrategico(to, idEjeEstrategico);

    }
    
    // ------ FIN DE EJE ESTRATEGICO
    
    // EJES ESTRATEGCOS SEGUN LA TEMPORADA
    
    public List Listar_Ejes_Objetivos_Estrategicos_Temporada(String idtemporada, int idTipoarea) {
    dao = new GestionEstrategicoDao();
    return dao.Listar_Ejes_Objetivos_Estrategicos_Temporada(idtemporada, idTipoarea);
    }
    public void registro_Temporada_Eje_Obj_Estrategico_array(HttpServletRequest r) {
        TemporadaEjeObjEstrategico to = new TemporadaEjeObjEstrategico();
        dao = new GestionEstrategicoDao();
        to.setIdtemporada(Integer.parseInt(r.getParameter("idTemporada")));
        to.setIdEjeEstrategicoArray((String[]) (r.getParameterValues("idEjeEstrategico") == null ? "" : r.getParameterValues("idacceso")));
        dao.registro_Temporada_Eje_Obj_Estrategico_array(to);

    }
    
    public List Listar_Objetivo_Especifico() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Objetivo_Especifico();
    }
     public List Listar_Periodo() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Perido();
    }
     
     /*
     public void registro_coordinadorfacultad_array(HttpServletRequest r) {

         Coordinadorfacultad to = new Coordinadorfacultad();
        dao = new GestionEstrategicoDao();
        to.setIdcoordinadorfacultad(Integer.parseInt(r.getParameter("idcoordinadorfacultad")));
        to.setIdpersona((String[]) (r.getParameterValues("idpersona") == null ? "" : r.getParameterValues("idacceso")));
        to.setIdperiodo(Integer.parseInt(r.getParameter("idperiodo")));
        to.setIdfilial(Integer.parseInt(r.getParameter("idfilial")));
        dao.registro_coordinadorfacultad_array(to);

    }
     */
     // gestion estratégica !! ----->
     
     
     public List<Indicador> listaIndicador(HttpServletRequest rq){
        dao = new GestionEstrategicoDao();
        Periodometa pm = new Periodometa();          
        if(rq.getParameter("idperiodometa")==null){
        pm.setIdperiodometa(null);
        }else{
        pm.setIdperiodometa(Integer.parseInt(rq.getParameter("idperiodometa")));
        }
        return dao.listaIndicador(pm);
    }
   public List<Periodometa> listaPeriodoMeta(HttpServletRequest rq){
        dao = new GestionEstrategicoDao();
        return dao.listaPeriodoMeta();
    }
 
    public List listar_indicador_periodo(String idperiodometa) {
    dao = new GestionEstrategicoDao();
    return dao.listar_indicador_periodo(idperiodometa);
 }
    
    // CRUD ESTRATEGIA
    
    public List Listar_Estrategia(int idTipoarea, int idFlial) {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Estrategia(idTipoarea, idFlial);
    }
    public void RegistrarEstrategia(HttpServletRequest r) {
        Estrategia to = new Estrategia();
        dao = new GestionEstrategicoDao();
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setNro(r.getParameter("nro") == null ? "" : r.getParameter("nro"));
        to.setIdareaunidad(Integer.parseInt(r.getParameter("idareaunidad") == null ? "0" : r.getParameter("idareaunidad")));
        to.setIdperspectiva(Integer.parseInt(r.getParameter("idperspectiva") == null ? "0" : r.getParameter("idperspectiva")));
        to.setIdfilial(Integer.parseInt(r.getParameter("idfilial") == null ? "0" : r.getParameter("idfilial")));
        System.out.println("Llegooo!!");
        dao.RegistrarEstrategia(to);

    }

    public Estrategia BuscarEstrategiaId(String id) {
        dao = new GestionEstrategicoDao();
        return dao.BuscarEstrategiaId(id);
    }
    public void ActualizarDatosEstrategia(HttpServletRequest r, int idEstrategia) {

        Estrategia to = new Estrategia();
        dao = new GestionEstrategicoDao();
        to.setIdestrategia(Integer.parseInt(r.getParameter("idEstrategia")));
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setNro(r.getParameter("nro") == null ? "" : r.getParameter("nro"));
        dao.ActualizarDatosEstrategia(to, idEstrategia);

    }
    public void EliminaridEstrategia(int idEstrategia) {
        dao = new GestionEstrategicoDao();
        dao.EliminaridEstrategia(idEstrategia);
    }

    // FIN DE CRUD
    
    // CRUD INDICADOR
    
    public List Listar_Indicador(int idTipoArea, int idFilial, int idEjeEstrategico) {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Indicador(idTipoArea, idFilial, idEjeEstrategico);
    }
    public List listaIndicadoresEje(int idEje) {
        dao = new GestionEstrategicoDao();
        return dao.listaIndicadoresEje(idEje);
    }
    
    
    public List Listar_Tipo_Meta() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Tipo_Meta();
    }
    
     public void Registrar_Indicador(HttpServletRequest r) {
        Indicador to = new Indicador();
        dao = new GestionEstrategicoDao();

        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setEstado(Integer.parseInt(r.getParameter("estado")));
        
        to.setIdtipometa(Integer.parseInt(r.getParameter("idTipoMeta")));
        to.setIdejeestrategico(Integer.parseInt(r.getParameter("idejeestrategico")));
        to.setCodigo(String.valueOf(r.getParameter("codigo")));
        to.setIdfilial(Integer.parseInt(r.getParameter("idfilial")));
        to.setIdtipoarea(Integer.parseInt(r.getParameter("idareaunidad")));
        dao.Registrar_Indicador(to);

    }
     
    public Indicador BuscarIndicadorId(String id) {
        dao = new GestionEstrategicoDao();
        return dao.BuscarIndicadorId(id);
    } 
    
    public void ActualizarDatosIndicador(HttpServletRequest r, int idIndicador) {

        Indicador to = new Indicador();
        dao = new GestionEstrategicoDao();
        to.setIdindicador(Integer.parseInt(r.getParameter("idIndicador")));
        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setEstado(Integer.parseInt(r.getParameter("estado")));
        to.setNro(Integer.parseInt(r.getParameter("nro")));
        to.setIdtipometa(Integer.parseInt(r.getParameter("idTipoMeta")));
        to.setInstrumento(r.getParameter("instrumento") == null ? "" : r.getParameter("instrumento"));
        dao.ActualizarDatosIndicador(to, idIndicador);

    }
     
     
     // ESTADOS DE TEMPORADAS Y METAS ------------------------------->------------------------>-------------------->
     
    public List Listar_Temporada() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Temporada();
    }
    
    public List Listar_Rango_Temporada(String idtemporada) {
    dao = new GestionEstrategicoDao();
    return dao.Listar_Rango_Temporada(idtemporada);
    }
    
    
    public List Listar_Temporada_Print() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Temporada_Print();
    }
    public void Activar_Temporada(int idTemporada) {
        dao = new GestionEstrategicoDao();
        dao.Activar_Temporada(idTemporada);
    }
    public void Desactivar_Temporada(int idTemporada) {
        dao = new GestionEstrategicoDao();
        dao.Desactivar_Temporada(idTemporada);
    }
    public void Registrar_Temporada(HttpServletRequest r) {
        Temporada to = new Temporada();
        dao = new GestionEstrategicoDao();

        to.setInicio(r.getParameter("inicio") == null ? "" : r.getParameter("inicio"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setFin(r.getParameter("fin") == null ? "" : r.getParameter("fin"));
        to.setEstado(r.getParameter("estado") == null ? "" : r.getParameter("estado"));
        dao.Registrar_Temporada(to);
    }
    
    public Temporada BuscarTemporadaId(String id) {
        dao = new GestionEstrategicoDao();
        return dao.BuscarTemporadaId(id);
    }
    public void ActualizarDatosTemporada(HttpServletRequest r, int idTemporada) {

        Temporada to = new Temporada();
        dao = new GestionEstrategicoDao();
        to.setIdtemporada(Integer.parseInt(r.getParameter("idTemporada")));
        to.setInicio(r.getParameter("inicio") == null ? "" : r.getParameter("inicio"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setFin(r.getParameter("fin") == null ? "" : r.getParameter("fin"));
        to.setEstado(r.getParameter("estado") == null ? "" : r.getParameter("estado"));
        dao.ActualizarDatosTemporada(to, idTemporada);

    }
    public void EliminaridTemporada(int idTemporada) {
        dao = new GestionEstrategicoDao();
        dao.EliminaridTemporada(idTemporada);
    }
    
    // DESARROLLO DE PERIODO META ------------------------------->------------------------>-------------------->
     
    public Temporada GetIdTemporada(String id) {
        dao = new GestionEstrategicoDao();
        return dao.GetIdTemporada(id);
    }
    public List Listar_PeriodoMeta_Temporada(String idtemporada) {
    dao = new GestionEstrategicoDao();
    return dao.Listar_PeriodoMeta_Temporada(idtemporada);
    }
    public List Listar_PeriodoMeta() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_PeriodoMeta();
    }
    public void Activar_Periodometa(int idperiodometa) {
        dao = new GestionEstrategicoDao();
        dao.Activar_Periodometa(idperiodometa);
    }
    public void Desactivar_Periodometa(int idperiodometa) {
        dao = new GestionEstrategicoDao();
        dao.Desactivar_Periodometa(idperiodometa);
    }
    public void Registrar_Periodo_Meta(HttpServletRequest r) {
        Periodometa to = new Periodometa();
        dao = new GestionEstrategicoDao();

        to.setPeriodo(r.getParameter("periodo") == null ? "" : r.getParameter("periodo"));
        to.setIdtemporada(Integer.parseInt(r.getParameter("idtemporada")));
        to.setEstado(Integer.parseInt(r.getParameter("estado")));
        dao.Registrar_Periodo_Meta(to);
    }
    public void EliminarPeriodoMeta(int idperiodometa) {
        dao = new GestionEstrategicoDao();
        dao.EliminarPeriodoMeta(idperiodometa);
    }
    
    public Periodometa BuscarPeriodoMetaId(String id) {
        dao = new GestionEstrategicoDao();
        return dao.BuscarPeriodoMetaId(id);
    }
    public void ActualizarDatosPeriodometa(HttpServletRequest r, int idperiodometa) {

        Periodometa to = new Periodometa();
        dao = new GestionEstrategicoDao();
        to.setIdperiodometa(Integer.parseInt(r.getParameter("idperiodometa")));
        to.setPeriodo(r.getParameter("periodo") == null ? "" : r.getParameter("periodo"));
        to.setIdtemporada(Integer.parseInt(r.getParameter("idtemporada")));
        to.setEstado(Integer.parseInt(r.getParameter("estado")));
        dao.ActualizarDatosPeriodometa(to, idperiodometa);

    }
     
    // REGISTRO ESTADO PERIODO FILIAL
    
      
     public List Listar_Estado_Periodo_Filial() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Estado_Periodo_Filial();
    }
      public List Listar_Filial() {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Filial();
    }
    
      
    // EJES Y OBJETIVOS
      
      public void Registrar_Eje_Objetivo_Estrategico(HttpServletRequest r) {
        Ejeestrategico to = new Ejeestrategico();
        dao = new GestionEstrategicoDao();

        to.setNombre(r.getParameter("nombre") == null ? "" : r.getParameter("nombre"));
        to.setDescripcion(r.getParameter("descripcion") == null ? "" : r.getParameter("descripcion"));
        to.setObjetivoestrategico(r.getParameter("objetivoestrategico") == null ? "" : r.getParameter("objetivoestrategico"));
        to.setMapaestrategico(r.getParameter("mapaestrategico") == null ? "" : r.getParameter("mapaestrategico"));
        dao.Registrar_Eje_Objetivo_Estrategico(to);

    }
     
      public void Registrar_Eje_Objetivo_Estrategico_Temporada(HttpServletRequest r) {
        TemporadaEjeObjEstrategico to = new TemporadaEjeObjEstrategico();
        dao = new GestionEstrategicoDao();
        to.setIdtemporada(Integer.parseInt(r.getParameter("idtemporada")));
        to.setIdEjeEstrategico(Integer.parseInt(r.getParameter("idEjeEstrategico")));
        dao.Registrar_Eje_Objetivo_Estrategico_Temporada(to);

    }
      
      public void Eliminar_Eje_Objetivo_Estrategico_Temporada(int IdTemporadaEjeEstrategico) {
        dao = new GestionEstrategicoDao();
        dao.Eliminar_Eje_Objetivo_Estrategico_Temporada(IdTemporadaEjeEstrategico);
    }
      public void Eliminar_Eje_Objetivo_Estrategico(int idEjeEstrategico) {
        dao = new GestionEstrategicoDao();
        dao.Eliminar_Eje_Objetivo_Estrategico(idEjeEstrategico);
    }

     public List Listar_Estrategias_eje(String idtemporadaejeestrategico, int idTipoarea, int idFilial) {
    dao = new GestionEstrategicoDao();
    return dao.Listar_Estrategias_eje(idtemporadaejeestrategico,idTipoarea, idFilial);
    }
    
      public void Registrar_Eje_Estrategia(HttpServletRequest r) {
          EjeEstrategia to = new EjeEstrategia();
          //listarEstrategiaCodigo
        dao = new GestionEstrategicoDao();
        String codigo=dao.listarEstrategiaCodigo(Integer.parseInt(r.getParameter("idestrategia"))).getNro();
        to.setIdestrategia(Integer.parseInt(r.getParameter("idestrategia")));
        to.setNro(codigo);
        to.setIdtemporadaejeestrategico(Integer.parseInt(r.getParameter("idtemporadaejeestrategico")));
        dao = new GestionEstrategicoDao();
        dao.Registrar_Eje_Estrategia(to);

    } 
      
      
      
      
    // FIN DE EJE ESTRATEGIA  
      
      
      
        public void Eliminar_Eje_Estrategia(int idEjeEstrategia) {
        dao = new GestionEstrategicoDao();
        dao.Eliminar_Eje_Estrategia(idEjeEstrategia);
    }
    
        public List Listar_Estrategia_Indicador(String idEjeEstrategia, int idTipoarea, int idFilial) {
    dao = new GestionEstrategicoDao();
    return dao.Listar_Estrategia_Indicador(idEjeEstrategia, idTipoarea, idFilial);
    }
    
        
        public void Registrar_Estrategia_Indicador(HttpServletRequest r) {       
        EstrategiaIndicador to = new EstrategiaIndicador();
        dao = new GestionEstrategicoDao();
        String codigo= dao.listarEjeIndicadorCodigo(Integer.parseInt(r.getParameter("idIndicador"))).getCodigo();
        
        to.setInstrumento(r.getParameter("instrumento") == null ? "" : r.getParameter("instrumento"));
        to.setNro(codigo);
        to.setIdIndicador(Integer.parseInt(r.getParameter("idIndicador")));
        to.setIdEjeEstrategia(Integer.parseInt(r.getParameter("idEjeEstrategia")));
        dao = new GestionEstrategicoDao();
        dao.Registrar_Estrategia_Indicador(to);

        } 
        
        public void Eliminar_Estrategia_Indicador(int idestrategiaIndicador) {
        dao = new GestionEstrategicoDao();
        dao.Eliminar_Estrategia_Indicador(idestrategiaIndicador);
    }
        public void Eliminar_Indicador(int idIndicador) {
        dao = new GestionEstrategicoDao();
        dao.Eliminar_Indicador(idIndicador);
    }
        
        
        
        // ESTADO PERIODO FILIAL :::::::::::::::::::::::::::::::::::::
        
        public List Listar_Estado_Periodo_Filial_Apertura(String idperiodometa, String idFilial) {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Estado_Periodo_Filial(idperiodometa, idFilial);
        }
        
        public void InsertarEstadoPeriodoFilial(HttpServletRequest r) {
        Estadoperiodofilial to = new Estadoperiodofilial();
        dao = new GestionEstrategicoDao();
        to.setIdperiodometa(r.getParameter("idperiodometa") == null ? "" : r.getParameter("idperiodometa"));
        to.setEstadometa(r.getParameter("estadometa") == null ? "" : r.getParameter("estadometa"));
        to.setEstadoavance(r.getParameter("estadoavance") == null ? "" : r.getParameter("estadoavance"));
        to.setIdFilial(r.getParameter("idFilial") == null ? "" : r.getParameter("idFilial"));
        dao.InsertarEstadoPeriodoFilial(to);

        }
        
        public void Aperturar_Estado_Meta_Filial(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.Aperturar_Estado_Meta_Filial(idestadoperiodofilial);
        }
        public void Cerrar_Estado_Meta_Filial(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.Cerrar_Estado_Meta_Filial(idestadoperiodofilial);
        }
        public void Aperturar_Estado_Avance_Filial(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.Aperturar_Estado_Avance_Filial(idestadoperiodofilial);
        }
        public void Cerrar_Estado_Avance_Filial(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.Cerrar_Estado_Avance_Filial(idestadoperiodofilial);
        }
        public void EliminarEstadoPeriodoFilialAper(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.EliminarEstadoPeriodoFilialAper(idestadoperiodofilial);
        }
        
    
        // ESTADO PERIODO FACULTAD :::::::::::::::::::::::::::::::::::::
        
        public List Listar_Estado_Periodo_Facultad(String idperiodometa, String idFilialfacultad) {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Estado_Periodo_Facultad(idperiodometa, idFilialfacultad);
        }
        
        public void InsertarEstadoPeriodoFacultad(HttpServletRequest r) {
        Estadoperiodofacultad to = new Estadoperiodofacultad();
        dao = new GestionEstrategicoDao();
        to.setIdperiodometa(r.getParameter("idperiodometa") == null ? "" : r.getParameter("idperiodometa"));
        to.setEstadometa(r.getParameter("estadometa") == null ? "" : r.getParameter("estadometa"));
        to.setEstadoavance(r.getParameter("estadoavance") == null ? "" : r.getParameter("estadoavance"));
        to.setIdFilialfacultad(r.getParameter("idFilialfacultad") == null ? "" : r.getParameter("idFilialfacultad"));
        dao.InsertarEstadoPeriodoFacultad(to);

        }
        public void Aperturar_Estado_Meta_Facultad(int idestadoperiodofacultad) {
        dao = new GestionEstrategicoDao();
        dao.Aperturar_Estado_Meta_Facultad(idestadoperiodofacultad);
        }
        public void Cerrar_Estado_Meta_Facultad(int idestadoperiodofacultad) {
        dao = new GestionEstrategicoDao();
        dao.Cerrar_Estado_Meta_Facultad(idestadoperiodofacultad);
        }
        public void Aperturar_Estado_Avance_Facultad(int idestadoperiodofacultad) {
        dao = new GestionEstrategicoDao();
        dao.Aperturar_Estado_Avance_Facultad(idestadoperiodofacultad);
        }
        public void Cerrar_Estado_Avance_Facultad(int idestadoperiodofacultad) {
        dao = new GestionEstrategicoDao();
        dao.Cerrar_Estado_Avance_Facultad(idestadoperiodofacultad);
        }
        public void EliminarEstadoPeriodoFacultadAper(int idestadoperiodofacultad) {
        dao = new GestionEstrategicoDao();
        dao.EliminarEstadoPeriodoFacultadAper(idestadoperiodofacultad);
        }
        public EstrategiaIndicador BuscarEstrategiaIndicadorId(String id) {
        dao = new GestionEstrategicoDao();
        return dao.BuscarEstrategiaIndicadorId(id);
        }
        
        public List Listar_Actividad_Indicador(String idestrategiaIndicador) {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Actividad_Indicador(idestrategiaIndicador);
        }

        
        
        public void InsertarActividadindicadorSI(HttpServletRequest r) {
        Actividadindicador to = new Actividadindicador();
        dao = new GestionEstrategicoDao();
        to.setNro(r.getParameter("nro") == null ? "" : r.getParameter("nro"));
        to.setAccion(r.getParameter("accion") == null ? "" : r.getParameter("accion"));
        to.setCantidad(r.getParameter("cantidad") == null ? "" : r.getParameter("cantidad"));
        to.setPresupuesto(r.getParameter("presupuesto") == null ? "" : r.getParameter("presupuesto"));
        to.setRubro(r.getParameter("rubro") == null ? "" : r.getParameter("rubro"));
        to.setEnero(Integer.parseInt(r.getParameter("enero") == null ? "0" : r.getParameter("enero")));
        to.setFebrero(Integer.parseInt(r.getParameter("febrero") == null ? "0" : r.getParameter("febrero")));
        to.setMarzo(Integer.parseInt(r.getParameter("marzo") == null ? "0" : r.getParameter("marzo")));
        to.setAbril(Integer.parseInt(r.getParameter("abril") == null ? "0" : r.getParameter("abril")));
        to.setMayo(Integer.parseInt(r.getParameter("mayo") == null ? "0" : r.getParameter("mayo")));
        to.setJunio(Integer.parseInt(r.getParameter("junio") == null ? "0" : r.getParameter("junio")));
        to.setJulio(Integer.parseInt(r.getParameter("julio") == null ? "0" : r.getParameter("julio")));
        to.setAgosto(Integer.parseInt(r.getParameter("agosto") == null ? "0" : r.getParameter("agosto")));
        to.setSetiembre(Integer.parseInt(r.getParameter("setiembre") == null ? "0" : r.getParameter("setiembre")));
        to.setOctubre(Integer.parseInt(r.getParameter("octubre") == null ? "0" : r.getParameter("octubre")));
        to.setNoviembre(Integer.parseInt(r.getParameter("noviembre") == null ? "0" : r.getParameter("noviembre")));
        to.setDiciembre(Integer.parseInt(r.getParameter("diciembre") == null ? "0" : r.getParameter("diciembre")));
        to.setIdestrategiaIndicador(Integer.parseInt(r.getParameter("idestrategiaIndicador")));
        dao.InsertarActividadindicadorSI(to);

        }
        
        public void EliminarActividadindicadorSI(int idActividadIndicador) {
        dao = new GestionEstrategicoDao();
        dao.EliminarActividadindicadorSI(idActividadIndicador);
        }
        
        public List Listar_ComboEAPFacultad(String idfilialfacultad) {
        dao = new GestionEstrategicoDao();
        return dao.Listar_ComboEAPFacultad(idfilialfacultad);
        }
        
        
        // ESTADO PERIODO EAP ----
        
        
        public List Listar_Estado_Periodo_EAP(String idperiodometa, String idEapFacultad) {
        dao = new GestionEstrategicoDao();
        return dao.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad);
        }
        
        public void InsertarEstadoPeriodoEap(HttpServletRequest r) {
        Estadoperiodoeap to = new Estadoperiodoeap();
        dao = new GestionEstrategicoDao();
        to.setIdperiodometa(Integer.parseInt(r.getParameter("idperiodometa") == null ? "" : r.getParameter("idperiodometa")));
        to.setEstadometa(r.getParameter("estadometa") == null ? "" : r.getParameter("estadometa"));
        to.setEstadoavance(r.getParameter("estadoavance") == null ? "" : r.getParameter("estadoavance"));
        to.setIdEapFacultad(Integer.parseInt(r.getParameter("idEapFacultad") == null ? "" : r.getParameter("idEapFacultad")));
        dao.InsertarEstadoPeriodoEap(to);

        }
        
        public void Aperturar_Estado_Meta_Eap(int idestadoperiodoeap) {
        dao = new GestionEstrategicoDao();
        dao.Aperturar_Estado_Meta_Eap(idestadoperiodoeap);
        }
        public void Cerrar_Estado_Meta_Eap(int idestadoperiodoeap) {
        dao = new GestionEstrategicoDao();
        dao.Cerrar_Estado_Meta_Eap(idestadoperiodoeap);
        }
        public void Aperturar_Estado_Avance_Eap(int idestadoperiodoeap) {
        dao = new GestionEstrategicoDao();
        dao.Aperturar_Estado_Avance_Eap(idestadoperiodoeap);
        }
        public void Cerrar_Estado_Avance_Eap(int idestadoperiodoeap) {
        dao = new GestionEstrategicoDao();
        dao.Cerrar_Estado_Avance_Eap(idestadoperiodoeap);
        }
        public void EliminarEstadoPeriodoEapAper(int idestadoperiodoeap) {
        dao = new GestionEstrategicoDao();
        dao.EliminarEstadoPeriodoEapAper(idestadoperiodoeap);
        }
        
   
        /*Inicio Servicio de estado de control de POA y PM*/
        public void aperturarPOAestadoEAP(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.aperturarPOAestadoEAP(idestadoperiodofilial);
        }
        public void cerrarPOAestadoEAP(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.cerrarPOAestadoEAP(idestadoperiodofilial);
        }        
        public void aperturarPMestadoEAP(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.aperturarPMestadoEAP(idestadoperiodofilial);
        }
        public void cerrarPMestadoEAP(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.cerrarPMestadoEAP(idestadoperiodofilial);
        }   
        
        
        public void aperturarPOAestadoFacultad(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.aperturarPOAestadoFacultad(idestadoperiodofilial);
        }
        public void cerrarPOAestadoFacultad(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.cerrarPOAestadoFacultad(idestadoperiodofilial);
        }        
        public void aperturarPMestadoFacultad(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.aperturarPMestadoFacultad(idestadoperiodofilial);
        }
        public void cerrarPMestadoFacultad(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.cerrarPMestadoFacultad(idestadoperiodofilial);
        }  
        
        
        
        public void aperturarPOAestadoFilial(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.aperturarPOAestadoFilial(idestadoperiodofilial);
        }
        public void cerrarPOAestadoFilial(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.cerrarPOAestadoFilial(idestadoperiodofilial);
        }        
        public void aperturarPMestadoFilial(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.aperturarPMestadoFilial(idestadoperiodofilial);
        }
        public void cerrarPMestadoFilial(int idestadoperiodofilial) {
        dao = new GestionEstrategicoDao();
        dao.cerrarPMestadoFilial(idestadoperiodofilial);
        }         
        /*Fin Servicio de estado de control de POA y PM*/
        
}
