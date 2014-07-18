/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sge.modelo.Ejeestrategico;
import sge.modelo.Estrategia;
import sge.modelo.EstrategiaIndicador;
import sge.modelo.Periodometa;
import sge.modelo.Politicaupeu;
import sge.modelo.Temporada;
import sge.service.GestionEstrategicoService;
import sge.service.OrganizacionService;
import sge.service.UsuarioService;

/**
 *
 * @author Edwin
 */
@WebServlet(name = "GestionEstrategico", urlPatterns = {"/GestionEstrategico"})
public class GestionEstrategico extends HttpServlet {
    
    String alert_true="<div class='alert alert-success'><script>window.setTimeout(function() { $('.alert').alert('close'); }, 3000);</script><h4>Alert!</h4>Se Registro Correctamente !!</div>";
    String update_true="<div class='alert alert-success'><script>window.setTimeout(function() { $('.alert').alert('close'); }, 3000);</script><h4>Alert!</h4>Se Actualizo los datos correctamente !!</div>";
    String alert_false="<div class='alert alert-error'><script>window.setTimeout(function() { $('.alert').alert('close'); }, 3000);</script><h4>Alert!</h4>Hay un error al registrar un campo de registro !!</div>";
   
    String GUARDANDOTRUE="<div class='alert alert-success'><script>function redirecionar(){$.ajax({type:'POST',url:'GestionEstrategico?opt=88',data:'',success:function(msg){$('#indicadoresestrategias').html(msg);$('#indicadoresestrategias').show('normal');}});}setTimeout('redirecionar();', 3000);window.setTimeout(function() { $('.alert').alert('close'); }, 3000); </script><h4>Alert!</h4>Se Registro Correctamente !!</div>";

    // POLITICA UPEU
    
    private static String LISTAPOLITICAUPEU= "apps/app_gestion_estrategico/list_politica_upeu.jsp";
    private static String DATOSPOLITICAUPEU= "apps/app_gestion_estrategico/datos_politica_upeu.jsp";
    
    // LISTA DE EJES Y OBJETIVOS ESTRATEGICOS
    
    private static String LISTAEJESESTRATEGICOS= "apps/app_gestion_estrategico/list_pre_eje_objetivo_estrategico.jsp";
    private static String LISTAEJESOBJESTRATEGICOS= "apps/app_gestion_estrategico/list_post_eje_objetivo_estrategico.jsp";
    private static String DATOSEJESESTRATEGICOS= "apps/app_gestion_estrategico/datos.jsp";
    
    private static String ADDEJESOBJESTRATEGICOS= "apps/app_gestion_estrategico/add_eje_objetivo.jsp";
    private static String EDITEJESOBJESTRATEGICOS= "apps/app_gestion_estrategico/edit_eje_estrategico.jsp";
    
    private static String ADDEJESOBJESTRATEGICOSTEMPORADA= "apps/app_gestion_estrategico/add_eje_objetivo_temporada.jsp";
    
    
    
    // LISTA DE ESTRATEGIA
    
    private static String LISTPREESTRATEGIAS= "apps/app_gestion_estrategico/list_pre_estrategias.jsp";
    private static String LISTCOMBOEJESOBJ= "apps/app_gestion_estrategico/get_list_combo_ejes_objetivos.jsp";
    private static String LISTPOSTESTRATEGIAS= "apps/app_gestion_estrategico/list_post_estrategias.jsp";
    private static String LISTAESTRATEGIA= "apps/app_gestion_estrategico/config_list_estrategia.jsp";
    private static String EDITESTRATEGIA= "apps/app_gestion_estrategico/edit_post_estrategias.jsp";
    
    
    
    
    // LISTA INDICADOR
    
    private static String LISTAINDICADOR= "apps/app_gestion_estrategico/list_indicador.jsp";
    private static String ADDINDICADOR= "apps/app_gestion_estrategico/add_indicador.jsp";
    private static String ADDPOSTINDICADOR= "apps/app_gestion_estrategico/add_post_indicador.jsp";
    private static String EDITPOSTINDICADOR= "apps/app_gestion_estrategico/edit_post_indicador.jsp";
    
    private static String LISTPREINDICADOR= "apps/app_gestion_estrategico/list_pre_indicador.jsp";
    private static String LISTCOMBOEJESOBJINDICADOR= "apps/app_gestion_estrategico/get_list_combo_objetivos.jsp";
    private static String LISTCOMBOESTRATEGIASINDICADOR= "apps/app_gestion_estrategico/get_list_combo_estrategias.jsp";
    private static String LISTPOSTESTRATEGIASINDICADOR= "apps/app_gestion_estrategico/list_post_indicador.jsp";
    
    // gestion estrategica
    
    private static String INDEXSGE= "apps/app_coordinador_eap/index_periodo.jsp";
    private static String INDEXINDICADOR= "apps/app_coordinador_eap/index.jsp";
    private static String LISTINDICADOR= "apps/app_coordinador_eap/list_indicador.jsp";
    private static String LISTACTIVIDAD= "apps/app_coordinador_eap/list_actividad.jsp";
    
    // GESTION ESTRATEGICO DE LA UPEU .............
    
    private static String INDEXGESTIONESTRATEGICO= "apps/app_gestion_estrategico/index.jsp";
    
    
    // ESTADOS DE TEMPORADAS Y PERIODOS METAS
    
    private static String INDEXTM= "apps/app_estado_periodo/index.jsp";
    private static String LISTARTEMPORADA= "apps/app_estado_periodo/list_temporada.jsp";
    private static String ADDTEMPORADA= "apps/app_estado_periodo/add_temporada.jsp";
    private static String EDITGETTEMPORADA= "apps/app_estado_periodo/edit_temporada.jsp";
    
    // SELECCION DE TEMPORADA PARA EL INGRESO DE PERIODOS
    
    private static String LISTARPRETEMPORADAPERIODO= "apps/app_estado_periodo/list_pre_temporada.jsp";
    private static String LISTARPERIODOMETATEMPORADA= "apps/app_estado_periodo/get_list_tempodara_periodo.jsp";
    private static String EDITPERIODOMETA= "apps/app_estado_periodo/edit_periodo_meta.jsp";
    
    
    
    // ESTADO PERIODO 
    
    private static String INDEXESTADOFILIAL= "apps/app_estado_periodo/view_estado_filial.jsp";
    private static String GETCOMBOPERIODOS= "apps/app_estado_periodo/get_combo_list_periodos.jsp";
    private static String GETCOMBOFILIALES= "apps/app_estado_periodo/get_combo_list_filiales.jsp";
    private static String LISTESTADOPERIODOFILIAL= "apps/app_estado_periodo/list_estado_periodo_filial_apertura.jsp";
    
    
    private static String INDEXESTADOFACULTAD= "apps/app_estado_periodo/view_estado_facultad.jsp";
    private static String GETCOMBOFILIALFACULTAD= "apps/app_estado_periodo/get_combo_filial_facultad.jsp";
    private static String LISTESTADOPERIODOFACULTAD= "apps/app_estado_periodo/list_estado_periodo_facultad_apertura.jsp";
    
    
    

    // en construccion {

    private static String INDEXESTADOEAP= "apps/app_estado_periodo/view_estado_eap.jsp";
    private static String GETCOMBOEAPFACULTAD= "apps/app_estado_periodo/get_combo_list_eap.jsp";
    private static String LISTESTADOPERIODOEAP= "apps/app_estado_periodo/list_estado_periodo_eap_apertura.jsp";
    
    //}
    
    // ACTIVIDADES
    
    private static String LISTACTIVIDADES= "apps/app_gestion_estrategico/list_actividades_indicador.jsp";
    private static String ADDACTIVIDADES= "apps/app_gestion_estrategico/add_actividad_indicador.jsp";
    
    public int idTipoAreaPri=0;
    public String idCategoriaUsuarioPri="0";
    public int idPerspectivaPri=0;
    public String idFilialPri="0";
    public int idEjeEstrategicoPri=0;
    
    public void principalValorSession(HttpServletRequest r){
    sge.modelo.Usuario usuSess=(sge.modelo.Usuario)r.getSession().getAttribute("listaPerfilUsuario");
    idTipoAreaPri=usuSess.getIdtipoarea();
    idCategoriaUsuarioPri=usuSess.getIdcategoriausuario();
    idPerspectivaPri=usuSess.getIdperspectiva();
    idFilialPri=usuSess.getIdfilial();
    idEjeEstrategicoPri=usuSess.getIdejeestrategico();    
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int opt = Integer.parseInt(request.getParameter("opt"));
        GestionEstrategicoService ges;
        OrganizacionService os;
        try {
           
            switch (opt) {
                case 1: {
                    
                   // Muestra la politica de la UPeU
                    
                   ges=new GestionEstrategicoService();
                   List listarPoliticaUpeu = ges.Listar_Politica_Upeu();
                   request.getSession().setAttribute("listar_politica_upeu", listarPoliticaUpeu);
                   response.sendRedirect(LISTAPOLITICAUPEU);
                }
                break;
                case 2: {
                   
                   // Edita la Politica de la UPeU
                    
                   ges=new GestionEstrategicoService();
                   ges.Actualizar_Datos_Politica_Upeu(request, Integer.parseInt(request.getParameter("idpoliticasupeu")));
                   ges=new GestionEstrategicoService();
                   List listarPoliticaUpeu = ges.Listar_Politica_Upeu();
                   request.getSession().setAttribute("listar_politica_upeu", listarPoliticaUpeu);
                   response.sendRedirect(LISTAPOLITICAUPEU);

                    
                }
                break;
                 case 3: {
                     // cargar datos para editar Politica UPeU
                    ges=new GestionEstrategicoService();
                    Politicaupeu to = ges.Buscar_Politica_Upeu_Id(request.getParameter("idpoliticasupeu"));
                    request.getSession().setAttribute("listaPoliticasUPeUById", to);
                    response.sendRedirect(DATOSPOLITICAUPEU);
                    
                }
                break;   
                
                case 4: {
                    
                     // CARGAR DATOS PARA EDITAR INDICADOR
                    
                    ges=new GestionEstrategicoService();
                    sge.modelo.Indicador to =ges.BuscarIndicadorId(request.getParameter("idIndicador"));
                    request.getSession().setAttribute("listaidIndicadorById", to);
                    ges=new GestionEstrategicoService();
                    List listarTipoMeta = ges.Listar_Tipo_Meta();
                    request.getSession().setAttribute("listar_tipo_meta", listarTipoMeta);
                    response.sendRedirect(EDITPOSTINDICADOR);
                     
                }
                break;   
                     
               case 5: {
                   
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                    
                   // ACTUALIZAR INDICADOR --> indicador 
                   ges=new GestionEstrategicoService();
                   ges.ActualizarDatosIndicador(request, Integer.parseInt(request.getParameter("idIndicador")));
                   ges=new GestionEstrategicoService();
                   List listarTipoMeta = ges.Listar_Tipo_Meta();
                   request.getSession().setAttribute("listar_tipo_meta", listarTipoMeta);
                   ges=new GestionEstrategicoService();
                   List listar_indicador = ges.Listar_Indicador(idTipoAreaPri, Integer.parseInt(idFilialPri), idEjeEstrategicoPri);
                   request.getSession().setAttribute("listar_indicador", listar_indicador);
                   
                   
                   response.sendRedirect(ADDINDICADOR);
                    
               }
                break;   
               
                case 6: {
                   
                   // PRE LIST EJE Y OBJETIVO ESTRATEGICO
                    
             
                   String namepre = "Seleccione una Temporada para listar los Ejes y Objetivos Asignados";
                   String datapre = "Ejes y Objetivos Estrategico ";
                   
                   request.getSession().setAttribute("name_pre_eje_objetivo_est",namepre); 
                   request.getSession().setAttribute("data_pre_eje_objetivo_est",datapre); 
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada_Print();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion
                   
                   ges=new GestionEstrategicoService();
                   List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                   request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   response.sendRedirect(LISTAEJESESTRATEGICOS);
                   
               }
                break;  
                    
               case 7: {
                   
                   //  CASE ELIMINADO
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada();
                   request.getSession().setAttribute("listar_temporada", listarTemporada);
                //   response.sendRedirect(LISTATEMPORADA);
                    
               }
                break;       
                   
               case 8: {
                   
                   //  LISTA DE ESTRATEGIA
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                     
                   ges=new GestionEstrategicoService();
                   List listarEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                   request.getSession().setAttribute("listar_estrategia", listarEstrategia);
                   response.sendRedirect(LISTAESTRATEGIA);
                    
               }
                break;       
               
               case 9: {
                   String name = " Gestion de Indicadores del segun el plan estratégico";
                   String data_name = " Seleccione el Periodo de indicadores para ver un reporte de avances ";
                   
                   request.getSession().setAttribute("name_org_sge",name); 
                   request.getSession().setAttribute("data_org_sge",data_name); 
                   response.sendRedirect(INDEXINDICADOR); 
                    
               }
                break;       
                
               case 10: {
                   // LISTA DE INDICADORES ::.
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                    
                   ges=new GestionEstrategicoService();
                   List listarIndicador = ges.Listar_Indicador(idTipoAreaPri, Integer.parseInt(idFilialPri), idEjeEstrategicoPri);
                   request.getSession().setAttribute("listar_indicador", listarIndicador);
                   response.sendRedirect(LISTAINDICADOR);
                   
                    
               }
               break;       
               
               case 11: {
                   
                   // lista de actividades
                   
                   response.sendRedirect(LISTACTIVIDAD); 
                    
               }
               break;       
               
                   // Gestion Estratégica ------------------------------------------>
                   
               case 12: {
                   
                   // Template de reporte de periodos inicio para ver los indicadores ------>
                   
                    ges=new GestionEstrategicoService(); 
                    request.getSession().setAttribute("listar_periodo_meta", ges.listaPeriodoMeta(request));
                    response.sendRedirect(INDEXSGE); 
                    
               }
               break;       
               
               case 13: {
                   
                   // Recuperando Indicadores segun el periodo seleccionado ------>
                   
                    ges=new GestionEstrategicoService();
                    String query = (String)request.getParameter("edwin");
                    request.getSession().setAttribute("listar_indicador_periodo", ges.listar_indicador_periodo(query));
                    response.sendRedirect(LISTINDICADOR);
                    
               }
               break;       
               
               // GESTION ESTRATEGICOS ------------------------------------------------------------------------------>
                   
               case 14: {
                   
                   // Recuperando Indicadores segun el periodo seleccionado ------>
                   
                   String name = " Gestion Estrategica de la UPeU";
                   String data_name = "Ejes y Objetivos Estrategicos - Enseñanza y Aprendizaje de Calidad";
                   
                   request.getSession().setAttribute("name_ge",name); 
                   request.getSession().setAttribute("data_ge",data_name); 
                   
                    ges=new GestionEstrategicoService();
                    List Filial = ges.Listar_Filial();
                    request.getSession().setAttribute("ListarFilialComboX", Filial);  
                    UsuarioService us;
                    us=new UsuarioService();
                    List listarAreas = us.areasUnidadUpeU();
                    request.getSession().setAttribute("listar_AreasUnidadSessX", listarAreas);  
                   
                   
                   response.sendRedirect(INDEXGESTIONESTRATEGICO); 
                    
               }
               break;       
               
               case 15: {
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion //Listar_Ejes_Estrategicos
                   // REGISTRO DE NUEVO INDICADOR --> indicador 
                   ges=new GestionEstrategicoService();
                   List listarEjeEstrategico = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                   request.getSession().setAttribute("listar_EjeEstrategicoSessXX", listarEjeEstrategico);  
                   int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
                   ges=new GestionEstrategicoService();
                   List listarTipoMeta = ges.Listar_Tipo_Meta();
                   request.getSession().setAttribute("listar_tipo_meta", listarTipoMeta);
                   ges=new GestionEstrategicoService();
                   List listar_indicador = ges.Listar_Indicador(idTipoAreaPri, Integer.parseInt(idFilialPri),idEjeX);
                   request.getSession().setAttribute("listar_indicador", listar_indicador);
                   
                   
                   response.sendRedirect(ADDINDICADOR+"?idEjeX="+idEjeX);
                   
                    
               }
               break;       
               
               case 16: {
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                   
                   // GUARDAR DATOS INDICADOR --> indicador 
                   int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
                   ges=new GestionEstrategicoService();
                   ges.Registrar_Indicador(request);
                   ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idEjeEstrategiaSession");
                    request.getSession().setAttribute("Listar_Estrategias_Indicador",ges.Listar_Estrategia_Indicador(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    List listarIndicadores = ges.Listar_Indicador(idTipoAreaPri, Integer.parseInt(idFilialPri), idEjeX);
                    request.getSession().setAttribute("listar_indicador", listarIndicadores);
                    
                    System.out.println("VERRRR-->"+idEjeX);
                    response.sendRedirect(ADDINDICADOR+"?idEjeX="+idEjeX);
                    
               }
               break;       
               
              case 17: {
                   
                   // CARGAR DATOS INDEX ESTADOS TEMPORADAS Y METAS
                   
                   String namein = "Administracion de Temporadas y Estados de Periodos ";
                   String data_namein = " Usted puede activar y desactivar el estado de una temporada o perido ";
                   
                   request.getSession().setAttribute("name_temporadas_periodos",namein); 
                   request.getSession().setAttribute("data_temporadas_periodos",data_namein); 
                   response.sendRedirect(INDEXTM); 
                    
               }
               break;       
               case 18: {
                   
                   // LISTAR TMPORADAS
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   response.sendRedirect(LISTARTEMPORADA);
                  
                   
                    
               }
               break;       
               case 19: {
                   
                   // ACTIVAR TEMPORADA 
                   
                    ges=new GestionEstrategicoService();
                    int idTemporada= Integer.parseInt(request.getParameter("idTemporada"));
                    ges.Activar_Temporada(idTemporada);
                    ges=new GestionEstrategicoService();
                    List listarTemporada = ges.Listar_Temporada();
                    request.getSession().setAttribute("listarTemporada", listarTemporada);
                    response.sendRedirect(LISTARTEMPORADA);
               }
               break;       
               case 20: {
                   
                   // DESACTIVAR TEMPORADA 
                   
                    ges=new GestionEstrategicoService();
                    int idTemporada= Integer.parseInt(request.getParameter("idTemporada"));
                    ges.Desactivar_Temporada(idTemporada);
                    ges=new GestionEstrategicoService();
                    List listarTemporada = ges.Listar_Temporada();
                    request.getSession().setAttribute("listarTemporada", listarTemporada);
                    response.sendRedirect(LISTARTEMPORADA);
               }
               break;       
               case 21: {
                   
                   // LISTAR TEMPORADAS PARA INGRESAR PERIODOS
                   String namepre = "Seleccione una Temporada para ingresar el periodo o fecha";
                   String datapre = " temporada y descripcion ";
                   
                   request.getSession().setAttribute("name_pre_temporada",namepre); 
                   request.getSession().setAttribute("data_pre_temporada",datapre); 
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada_Print();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   response.sendRedirect(LISTARPRETEMPORADAPERIODO);
                  
                   
                    
               }
               break;       
               case 22:{
                   
                   // GET LIST TEMPORADA ID
                    ges=new GestionEstrategicoService();
                    String query = (String)request.getParameter("edwintemporada");
                    request.getSession().setAttribute("edwintemporadasession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasession");
                    request.getSession().setAttribute("listar_periodo_meta_temporada", ges.Listar_PeriodoMeta_Temporada(id));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                    
                    
                    response.sendRedirect(LISTARPERIODOMETATEMPORADA);
               }break;
                case 23: {
                   
                   // ACTIVAR PERIODO META 
                   
                    ges=new GestionEstrategicoService();
                    int idperiodometa= Integer.parseInt(request.getParameter("idperiodometa"));
                    ges.Activar_Periodometa(idperiodometa);
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasession");
                    request.getSession().setAttribute("listar_periodo_meta_temporada", ges.Listar_PeriodoMeta_Temporada(id));
                    response.sendRedirect(LISTARPERIODOMETATEMPORADA);
               }
               break;       
               case 24: {
                   
                   // DESACTIVAR PERIODO META
                   
                    ges=new GestionEstrategicoService();
                    int idperiodometa= Integer.parseInt(request.getParameter("idperiodometa"));
                    ges.Desactivar_Periodometa(idperiodometa);
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasession");
                    request.getSession().setAttribute("listar_periodo_meta_temporada", ges.Listar_PeriodoMeta_Temporada(id));
                    response.sendRedirect(LISTARPERIODOMETATEMPORADA);
               }
               break;       
               case 25: {
                   
                   // GUARDAR PERIODO META
                   
                    ges=new GestionEstrategicoService();
                    ges.Registrar_Periodo_Meta(request);
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasession");
                    request.getSession().setAttribute("listar_periodo_meta_temporada", ges.Listar_PeriodoMeta_Temporada(id));
                    response.sendRedirect(LISTARPERIODOMETATEMPORADA);
                    
               }
               break;       
               case 26: {
                   
                   // CARGAR ADD TEMPORADA
                    response.sendRedirect(ADDTEMPORADA);
                    
               }
               break;       
               case 27: {
                   
                   // GUARDAR TEMPORADA
                   
                    ges=new GestionEstrategicoService();
                    ges.Registrar_Temporada(request);
                    out.print(alert_true);
                  
               }
               break;       
               case 28: {
                   
                   // CARGAR TEMPORADAS PARA LISTAR PERIODOS y LUEGO LAS FILIALES PARA  --->> FILIAL
                   
                   String namepre = "Aperturar a Filiales";
                   String datapre = "Especificamente a una Filial";
                   
                   request.getSession().setAttribute("name_pre_periodo",namepre); 
                   request.getSession().setAttribute("data_pre_periodo",datapre); 
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada_Print();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   response.sendRedirect(INDEXESTADOFILIAL);
                  
               }
               break;       
               case 29: {
                   
                   
                   // LISTAR PERIODOS SEGUN LA TEMPORADA SELECCIONADA
                   
                    ges=new GestionEstrategicoService();
                    String query = (String)request.getParameter("getIDtemporada");
                    request.getSession().setAttribute("IDtemporadasession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("IDtemporadasession");
                    request.getSession().setAttribute("listar_combo_periodo", ges.Listar_PeriodoMeta_Temporada(id));
                    response.sendRedirect(GETCOMBOPERIODOS);
                  
                  
               }
               break;       
               case 30: {
                   
                   // CARGAR ADD ESTADO PERIODO FILIAL       -----------------????
                   // LISTAR FILIALES RECUPERANDO ID DEL PERIODO META :: COMBO
                    String query = (String)request.getParameter("getIDPeriodo");
                    request.getSession().setAttribute("IDPeriodosession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("IDPeriodosession");
                    System.out.println("ID PERIODO META ---->" +id);
                    
                    ges=new GestionEstrategicoService();
                    List Filial = ges.Listar_Filial();
                    request.getSession().setAttribute("ListarFilialCombo", Filial);
                    response.sendRedirect(GETCOMBOFILIALES);
               }
               break;       
               
               case 31: {
                   
                   // LISTAR ESTADO PERIODO FILIAL ::::::::::::::::::::::::::::
                   
                    ges=new GestionEstrategicoService();
                    String query = (String)request.getParameter("getIDFilial");
                    request.getSession().setAttribute("IDFilialsession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    
                    
                    
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 32: {
                   
                   // ELIMINAR PERIODOMETA
                   
                  ges=new GestionEstrategicoService();
                  int idPeriodoMeta = Integer.parseInt(request.getParameter("idPeriodoMeta"));
                  ges.EliminarPeriodoMeta(idPeriodoMeta);
                  out.print(alert_true);
                  HttpSession session = request.getSession(true); 
                  String id=(String)session.getAttribute("edwintemporadasession");
                  request.getSession().setAttribute("listar_periodo_meta_temporada", ges.Listar_PeriodoMeta_Temporada(id));
                  response.sendRedirect(LISTARPERIODOMETATEMPORADA);
                  
                  
               }
               break;       
               case 33:{
                   
                   // GET LIST EJES Y OBJETIVOS ESTRATEGICOS
                   
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                     
                    ges=new GestionEstrategicoService();
                    String queryID = (String)request.getParameter("edwintemporadaID");
                    request.getSession().setAttribute("edwintemporadasessionID",queryID);
                    
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasessionID");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id, idTipoAreaPri ));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                    //Esta funcion carga Valores
                    principalValorSession(request);
                    //Fin de la funcion
                    ges=new GestionEstrategicoService();
                    List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                    request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   
                    
                    response.sendRedirect(LISTAEJESOBJESTRATEGICOS);
               }break;
               case 34:{
                   
                   // GUARDAR DATOS TEMPORADA EJE OBJ ESTRATEGICO
        //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                     
                   ges=new GestionEstrategicoService();
                   ges.registro_Temporada_Eje_Obj_Estrategico_array(request);
                   
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasessionID");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id, idTipoAreaPri));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                             
                    ges=new GestionEstrategicoService();
                    List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                    request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   
                    
                    response.sendRedirect(LISTAEJESOBJESTRATEGICOS);
                    
               }break;       
               case 35:{
                   
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                   
                   // CARGAR ADD EJE OBJETIVO ESTRATEGICO 
                   ges=new GestionEstrategicoService();
                   List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                   request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   
                   response.sendRedirect(ADDEJESOBJESTRATEGICOS);
                    
               }break;       
               case 36:{
                   
                   // GUARDAR DATOS EJE OBJETIVO SIN TEMPORADA
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                     
                   ges=new GestionEstrategicoService();
                   ges.Registrar_Eje_Objetivo_Estrategico(request);
                   ges=new GestionEstrategicoService();
                   HttpSession session = request.getSession(true); 
                   String id=(String)session.getAttribute("edwintemporadasessionID");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id, idTipoAreaPri));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                    ges=new GestionEstrategicoService();
                  
                    List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                    request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   response.sendRedirect(ADDEJESOBJESTRATEGICOS);
                    
               }break;       
               case 37:{
                   
                   // LISTAR EJES Y OBJETIVOSS EN SESION ------------------- 
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                       
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasessionID");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id,idTipoAreaPri));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                    ges=new GestionEstrategicoService();
                
                    List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                    request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   
                   response.sendRedirect(LISTAEJESOBJESTRATEGICOS);
                    
               }break;       
               case 38:{
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                   // LISTAR EJES Y OBJETIVOSS EN SESION ------------------- 
                    ges=new GestionEstrategicoService();
                    ges.Registrar_Eje_Objetivo_Estrategico_Temporada(request);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasessionID");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id,idTipoAreaPri));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                 
                    ges=new GestionEstrategicoService();
                    List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                    request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   
                   response.sendRedirect(LISTAEJESOBJESTRATEGICOS);
                    
               }break;       
               
               case 39:{
                   
                   // ELIMINAR EJES Y OBJETIVOSS EN SESION ------------------- 
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                       
                    ges=new GestionEstrategicoService();
                    int IdTemporadaEjeEstrategico = Integer.parseInt(request.getParameter("IdTemporadaEjeEstrategico"));
                    ges.Eliminar_Eje_Objetivo_Estrategico_Temporada(IdTemporadaEjeEstrategico);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasessionID");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id,idTipoAreaPri));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                 
                    ges=new GestionEstrategicoService();
                    List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                    request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   
                   response.sendRedirect(LISTAEJESOBJESTRATEGICOS);
                    
               }break;       
               case 40:{
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                       
                   // ELIMINAR EJES Y OBJETIVOSS EN SESION ------------------- 
                    ges=new GestionEstrategicoService();
                    int idEjeEstrategico = Integer.parseInt(request.getParameter("idEjeEstrategico"));
                    ges.Eliminar_Eje_Objetivo_Estrategico(idEjeEstrategico);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasessionID");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id, idTipoAreaPri));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                
                    ges=new GestionEstrategicoService();
                    List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                    request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   
                    response.sendRedirect(ADDEJESOBJESTRATEGICOS);

                    
               }break;       
               case 41: {
                   
                   // PRE LIST EJE Y OBJETIVO ESTRATEGICO
                    
             
                   String namepre = "Seleccione una Temporada y luego los Ejes";
                   String datapre = "Estrategias ";
                   
                   request.getSession().setAttribute("name_pre_eje_objetivo_est",namepre); 
                   request.getSession().setAttribute("data_pre_eje_objetivo_est",datapre); 
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada_Print();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                   
                   ges=new GestionEstrategicoService();
                   List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                   request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   response.sendRedirect(LISTPREESTRATEGIAS);
                   
               }break;  
               case 42:{
                   
                   // GET LIST COMBO EJES Y OBJETIVOS SEGUN LA TEMPORADA SELECCIONADA
                   
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                     
                    ges=new GestionEstrategicoService();
                    String queryID = (String)request.getParameter("edwintemporadaID");
                    request.getSession().setAttribute("edwintemporadasessionIDxxxx",queryID);
                    
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasessionIDxxxx");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id, idTipoAreaPri));
                    response.sendRedirect(LISTCOMBOEJESOBJ);
               }break;
               
               case 43:{
                   
                   // LIST ESTRATEGIAS SEGUN EJE Y OBJETIVO SELECCIONADO
                   

                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                    ges=new GestionEstrategicoService();
                    String queryID = (String)request.getParameter("idtemporadaejeestrategico");
                    request.getSession().setAttribute("idtemporadaejeestrategicoSessionID",queryID);
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idtemporadaejeestrategicoSessionID");
                    request.getSession().setAttribute("Listar_Estrategias_eje",ges.Listar_Estrategias_eje(id,idTipoAreaPri, Integer.parseInt(idFilialPri)));
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                    ges=new GestionEstrategicoService();
                    List listarEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                    request.getSession().setAttribute("listar_estrategia", listarEstrategia);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIAS);
               }break;
                   
               case 44:{
                   
                   // GUARDAR DATOS DE ESTRATEGIAS SEGUN EJE Y OBJETIVO SELECCIONADO
                   

                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                    ges=new GestionEstrategicoService();
                    ges.Registrar_Eje_Estrategia(request);
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idtemporadaejeestrategicoSessionID");
                    request.getSession().setAttribute("Listar_Estrategias_eje",ges.Listar_Estrategias_eje(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    List listarEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                    request.getSession().setAttribute("listar_estrategia", listarEstrategia);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIAS);
               }break;
               case 45:{
                   
                   // ELIMINAR DATOS DE ESTRATEGIAS SEGUN EJE Y OBJETIVO SELECCIONADO
                   

                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                    ges=new GestionEstrategicoService();
                    int idEjeEstrategia = Integer.parseInt(request.getParameter("idEjeEstrategia"));
                    ges.Eliminar_Eje_Estrategia(idEjeEstrategia);
                    
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idtemporadaejeestrategicoSessionID");
                    request.getSession().setAttribute("Listar_Estrategias_eje",ges.Listar_Estrategias_eje(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    List listarEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                    request.getSession().setAttribute("listar_estrategia", listarEstrategia);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIAS);
               }break;
               case 46: {
                   
                   // PRE LIST INDICADOR
                    
             
                   String namepre = "Modulo de Indicador";
                   String datapre = "Indicador";
                   
                   request.getSession().setAttribute("name_pre_eje_objetivo_est",namepre); 
                   request.getSession().setAttribute("data_pre_eje_objetivo_est",datapre); 
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada_Print();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                   
                   ges=new GestionEstrategicoService();
                   List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                   request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   ges=new GestionEstrategicoService();
                   List listarIndicador= ges.Listar_Indicador(idTipoAreaPri, Integer.parseInt(idFilialPri),idEjeEstrategicoPri);
                   request.getSession().setAttribute("listar_indicador", listarIndicador);
                   
                   
                   response.sendRedirect(LISTPREINDICADOR);
                   
               }break;  
               case 47:{
                   
                   // GET LIST COMBO EJES Y OBJETIVOS SEGUN LA TEMPORADA SELECCIONADA PARA INDICADOR
                   
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                     
                    ges=new GestionEstrategicoService();
                    String queryID = (String)request.getParameter("edwintemporadaID");
                    request.getSession().setAttribute("temporadasessionIDIn",queryID);
                    
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("temporadasessionIDIn");
                    request.getSession().setAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada",ges.Listar_Ejes_Objetivos_Estrategicos_Temporada(id, idTipoAreaPri));
                    response.sendRedirect(LISTCOMBOEJESOBJINDICADOR);
               }break;
               case 48:{
                   
                   // LIST ESTRATEGIAS SEGUN EJE Y OBJETIVO SELECCIONADO
                   

                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                    ges=new GestionEstrategicoService();
                    String queryID = (String)request.getParameter("idtemporadaejeestrategicoccc");
                    
                    request.getSession().setAttribute("idtemporadaejeestrategicoSessionID",queryID);
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idtemporadaejeestrategicoSessionID");
                    request.getSession().setAttribute("Listar_Estrategias_eje",ges.Listar_Estrategias_eje(id,idTipoAreaPri,Integer.parseInt(idFilialPri)));
                    
                    ges=new GestionEstrategicoService();
                    int idEjeX= ges.idEjeXSeleccionada(Integer.parseInt(id));
                    
                    
                    response.sendRedirect(LISTCOMBOESTRATEGIASINDICADOR+"?idEjeX="+idEjeX);
               }break;
               case 49:{
                   
                   // LIST INDICADOR SEGUN LA ESTRATEGIA SELECCIONADA
                   
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                            
                    ges=new GestionEstrategicoService();
                    String queryID = (String)request.getParameter("idEjeEstrategia");
                    request.getSession().setAttribute("idEjeEstrategiaSession",queryID);
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idEjeEstrategiaSession");
                    
                    
                    request.getSession().setAttribute("Listar_Estrategias_Indicador",ges.Listar_Estrategia_Indicador(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    //List listarIndicadores = ges.Listar_Indicador();
                    int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
                    
                    
                    
                    System.out.println("DMPVER:"+idEjeX);
                    List listarIndicadores = ges.Listar_Indicador(idTipoAreaPri,Integer.parseInt(idFilialPri), idEjeX);
                    
                    
                    request.getSession().setAttribute("listar_indicador", listarIndicadores);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIASINDICADOR+"?idEjeX="+idEjeX);
               }break;
               case 50:{
                   
                   // GUARDAR ESTRATEGIA INDICADOR SEGUN LA ESTRATEGIA SELECCIONADA
                   
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                   
                    ges=new GestionEstrategicoService();
                    System.out.println("Holas  Paso Por Aqui!");
                    int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
                    ges.Registrar_Estrategia_Indicador(request);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idEjeEstrategiaSession");
                    request.getSession().setAttribute("Listar_Estrategias_Indicador",ges.Listar_Estrategia_Indicador(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                   // List listarIndicadores = ges.Listar_Indicador();
                    
                    System.out.println("Probar:::::::"+id);
                    List listarIndicadores = ges.Listar_Indicador(idTipoAreaPri,Integer.parseInt(idFilialPri), idEjeX);
                    
                    request.getSession().setAttribute("listar_indicador", listarIndicadores);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIASINDICADOR+"?idEjeX="+idEjeX);
               }break;
               case 51:{
                   
                   // ELIMINAR ESTRATEGIA INDICADOR SEGUN LA ESTRATEGIA SELECCIONADA
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                    
                    ges=new GestionEstrategicoService();
                    int idestrategiaIndicador = Integer.parseInt(request.getParameter("idestrategiaIndicador"));
                    ges.Eliminar_Estrategia_Indicador(idestrategiaIndicador);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idEjeEstrategiaSession");
                    request.getSession().setAttribute("Listar_Estrategias_Indicador",ges.Listar_Estrategia_Indicador(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    List listarIndicadores = ges.Listar_Indicador(idTipoAreaPri, Integer.parseInt(idFilialPri), idEjeEstrategicoPri);
                    request.getSession().setAttribute("listar_indicador", listarIndicadores);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIASINDICADOR);
               }break;
               case 52:{
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                   
                   // LISTAR ESTRATEGIA INDICADOR SEGUN LA ESTRATEGIA SELECCIONADA
                   int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idEjeEstrategiaSession");
                    request.getSession().setAttribute("Listar_Estrategias_Indicador",ges.Listar_Estrategia_Indicador(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    List listarIndicadores = ges.Listar_Indicador(idTipoAreaPri,Integer.parseInt(idFilialPri),idEjeX);
                    request.getSession().setAttribute("listar_indicador", listarIndicadores);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIASINDICADOR+"?idEjeX="+idEjeX);
               }break;
               case 53: {
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                    
                   // ELIMINAR REGISTRO DE INDICADOR --> indicador 
                    ges=new GestionEstrategicoService();
                    int idIndicador = Integer.parseInt(request.getParameter("idIndicador"));
                    ges.Eliminar_Indicador(idIndicador);
                    
                   ges=new GestionEstrategicoService();
                   List listarTipoMeta = ges.Listar_Tipo_Meta();
                   request.getSession().setAttribute("listar_tipo_meta", listarTipoMeta);
                   ges=new GestionEstrategicoService();
                   List listar_indicador = ges.Listar_Indicador(idTipoAreaPri,Integer.parseInt(idFilialPri),idEjeEstrategicoPri);
                   request.getSession().setAttribute("listar_indicador", listar_indicador);
                   
                   
                   response.sendRedirect(ADDINDICADOR);
                   
                    
               }break;       
               case 54: {
                    //Reportar por Id
                    ges=new GestionEstrategicoService();
                    Ejeestrategico to = ges.buscarEjeEstrategicoId(request.getParameter("idEjeEstrategico"));
                    request.getSession().setAttribute("listaEjeestrategicoById", to);
                    response.sendRedirect(EDITEJESOBJESTRATEGICOS);

                }
                break;
                case 55:{
                   
                   // ACTUALIZAR DATOS DE EJE OBJETIVO ESTRATEGICO 
                   ges=new GestionEstrategicoService(); 
                   ges.ActualizarDatosEjeEstrategico(request,Integer.parseInt(request.getParameter("idEjeEstrategico")));
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                   
                   ges=new GestionEstrategicoService();
                   List listarEjesEstrategicos = ges.Listar_Ejes_Estrategicos(idTipoAreaPri);
                   request.getSession().setAttribute("listar_ejes_estrategicos", listarEjesEstrategicos);
                   
                   response.sendRedirect(ADDEJESOBJESTRATEGICOS);
                    
                }break;       
                case 56:{
                   
                   // CONFIG LIST ESTRATEGIA
                    UsuarioService us;     
                                    
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                     
                   us=new UsuarioService();
                   List listarPerspectivas = us.perspectivasUPeU();
                   request.getSession().setAttribute("listar_PerspectivasSessX", listarPerspectivas);                     
                    
                   ges=new GestionEstrategicoService();
                   List listEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                   request.getSession().setAttribute("listEstrategia", listEstrategia);
                   
                   response.sendRedirect(LISTAESTRATEGIA);
                    
               }break;       
               case 57:{
                   
                   // LIST ESTRATEGIAS SEGUN EJE Y OBJETIVO SELECCIONADO CON SESSION
                   

                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idtemporadaejeestrategicoSessionID");
                    request.getSession().setAttribute("Listar_Estrategias_eje",ges.Listar_Estrategias_eje(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    List listarEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                    request.getSession().setAttribute("listar_estrategia", listarEstrategia);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIAS);
               }break;
               case 58:{
                   
                   // GUARDAR DATOS DE ESTRATEGIA ----> CONFIG LIST ESTRATEGIA
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                   ges=new GestionEstrategicoService();
                   ges.RegistrarEstrategia(request);
                   ges=new GestionEstrategicoService();
                   List listEstrategia = ges.Listar_Estrategia(idTipoAreaPri,Integer.parseInt(idFilialPri));
                   request.getSession().setAttribute("listEstrategia", listEstrategia);
                   
                   // RECUPERANDO LAS SESSIONES

                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idtemporadaejeestrategicoSessionID");
                    request.getSession().setAttribute("Listar_Estrategias_eje",ges.Listar_Estrategias_eje(id, idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    List listarEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                    request.getSession().setAttribute("listar_estrategia", listarEstrategia);
                    
                   
                   
                   response.sendRedirect(LISTAESTRATEGIA);
                    
               }break;       
               case 59: {
                   
                    // CARGAR DATOS PARA EDITAR ESTRATEGIA
                   
                    ges=new GestionEstrategicoService();
                    Estrategia to = ges.BuscarEstrategiaId(request.getParameter("idEstrategia"));
                    request.getSession().setAttribute("listaEstrategiaById", to);
                    response.sendRedirect(EDITESTRATEGIA);

                }
                break;
                case 60:{
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                     
                   // ACTUALIZAR DATOS ESTRATEGIA -->  CONFIG LIST ESTRATEGIA
                   ges=new GestionEstrategicoService();
                   ges.ActualizarDatosEstrategia(request, Integer.parseInt(request.getParameter("idEstrategia")));
                   ges=new GestionEstrategicoService();
                   List listEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                   request.getSession().setAttribute("listEstrategia", listEstrategia);
                   
                   response.sendRedirect(LISTAESTRATEGIA);
                    
               }break;       
                case 61:{
                   
                   // ELIMINAR DATOS ESTRATEGIA -->  CONFIG LIST ESTRATEGIA
                   ges=new GestionEstrategicoService();
                   int idEstrategia = Integer.parseInt(request.getParameter("idEstrategia"));
                   ges.EliminaridEstrategia(idEstrategia);
                   //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion                      
                   ges=new GestionEstrategicoService();
                   List listEstrategia = ges.Listar_Estrategia(idTipoAreaPri, Integer.parseInt(idFilialPri));
                   request.getSession().setAttribute("listEstrategia", listEstrategia);
                   
                   response.sendRedirect(LISTAESTRATEGIA);
                    
               }break;       
               case 62:{
                   
                   // CARGAR DATOS PARA REGISTRO DE INDICADORES
 
                   int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
                   
                   ges=new GestionEstrategicoService();
                   List listarTipoMeta = ges.Listar_Tipo_Meta();
                   request.getSession().setAttribute("listar_tipo_meta", listarTipoMeta);
                   response.sendRedirect(ADDPOSTINDICADOR+"?idEjeX="+idEjeX);
                    
               }break;       
               case 63:{
                   
                   // CARGAR DATOS PARA REGISTRO DE INDICADORES
                   
                   ges=new GestionEstrategicoService();
                   Temporada to = ges.BuscarTemporadaId(request.getParameter("idTemporada"));
                   request.getSession().setAttribute("listaTemporadaById", to);
                   response.sendRedirect(EDITGETTEMPORADA);
                    
               }break;       
               case 64:{
                   
                   // ACTUALIZAR DATOS TEMPORADA
                   
                   ges=new GestionEstrategicoService();
                   ges.ActualizarDatosTemporada(request,Integer.parseInt(request.getParameter("idTemporada")));
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   response.sendRedirect(LISTARTEMPORADA);
                    
               }break;       
               case 65:{
                   
                   // ELIMINAR DATOS TEMPORADA
                   
                   ges=new GestionEstrategicoService();
                   int idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
                   ges.EliminaridTemporada(idTemporada);
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   response.sendRedirect(LISTARTEMPORADA);
                    
               }break;       
               case 66:{
                   
                   // RECUPERANDO DATOS DE PERIODO META
                   
                    ges=new GestionEstrategicoService();
                    Periodometa to = ges.BuscarPeriodoMetaId(request.getParameter("idperiodometa"));
                    request.getSession().setAttribute("listaPeriodoMetaById", to);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasession");
                    request.getSession().setAttribute("listar_periodo_meta_temporada", ges.Listar_PeriodoMeta_Temporada(id));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                    response.sendRedirect(EDITPERIODOMETA);
                    
               }break;
               case 67:{
                   
                   // LISTAR PERIODO META SEGUN LA TEMPORADA SELECCIONADA EN SESSION
                   
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasession");
                    request.getSession().setAttribute("listar_periodo_meta_temporada", ges.Listar_PeriodoMeta_Temporada(id));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                    response.sendRedirect(LISTARPERIODOMETATEMPORADA);
                    
               }break;
               case 68:{
                   
                   // ACTUALIZAR PERIODO META SEGUN LA TEMPORADA SELECCIONADA EN SESSION
                   
                    ges=new GestionEstrategicoService();
                    ges.ActualizarDatosPeriodometa(request,  Integer.parseInt(request.getParameter("idperiodometa")));
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("edwintemporadasession");
                    request.getSession().setAttribute("listar_periodo_meta_temporada", ges.Listar_PeriodoMeta_Temporada(id));
                    request.getSession().setAttribute("Listar_Rango_Temporada", ges.Listar_Rango_Temporada(id));
                    response.sendRedirect(LISTARPERIODOMETATEMPORADA);
                    
               }break;
               case 69: {
                   
                   // LISTAR ESTADO PERIODO FILIAL  EN SESSION ::::::::::::::::::::::::::::
                   
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 70: {
                   
                   // GUARDAR APERTURA ESTADO PERIODO FILIAL  EN SESSION ::::::::::::::::::::::::::::
                    ges=new GestionEstrategicoService();
                    ges.InsertarEstadoPeriodoFilial(request);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;
                   
                   //  ESTADOS ACTIVAR Y DESACTIVAR FILIAL :::::::::::::::
                   
               case 71: {
                   
                   // APERTURAR META FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.Aperturar_Estado_Meta_Filial(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 72: {
                   
                   // CERRAR APERTURAR META FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.Cerrar_Estado_Meta_Filial(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 73: {
                   
                   // APERTURAR AVANCE FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.Aperturar_Estado_Avance_Filial(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 74: {
                   
                   // CERRAR APERTURA AVANCE FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.Cerrar_Estado_Avance_Filial(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 75: {
                   
                   // ELIMINAR ESTADO PERIODO FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.EliminarEstadoPeriodoFilialAper(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 76: {
                   
                   // CARGAR TEMPORADAS PARA LISTAR PERIODOS y LUEGO LAS FACULTADES PARA  --->> FILIAL
                   
                   String namepre = "Aperturar a Facultades";
                   String datapre = "Especificamente a una facultad";
                   
                   request.getSession().setAttribute("name_pre_periodo",namepre); 
                   request.getSession().setAttribute("data_pre_periodo",datapre); 
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada_Print();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   response.sendRedirect(INDEXESTADOFACULTAD);
                  
               }
               break;       
               case 77: {
                   
                   // LISTAR FILIAL FACULTAD SEGUN LA FILIAL SELECCIONADA PERIODO FILIAL ::::::::::::::::::::::::::::
                   
                    os=new OrganizacionService();
                    String query = (String)request.getParameter("getIDFilial");
                    request.getSession().setAttribute("IDFilialFacultadsession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialFacultadsession");
                    //request.getSession().setAttribute("listar_Filial_Facultad_Combo",os.listar_filial_facultad_desde_usuario(idFilial, idTipoAreaPri) );
                    request.getSession().setAttribute("listar_Filial_Facultad_Combo",os.listar_filial_facultad_desde_usuarioPrimero(idFilial) );
                    
                    
                    
                    response.sendRedirect(GETCOMBOFILIALFACULTAD);
                  
               }
               break;       
               case 78: {
                   
                   // LISTAR ESTADO PERIODO FACULTAD ---------->> LISTA O REPORTE ::::::::::::::::::::::::::::
                   
                    ges=new GestionEstrategicoService();
                    String query = (String)request.getParameter("getIDFilialFacultad");
                    request.getSession().setAttribute("IDFilialFacultadsession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 79: {
                   
                   // LISTAR ESTADO PERIODO FACULTAD ---------->> LISTA O REPORTE ::::::::::::::::::::::::::::
                   
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 80: {
                   
                   // GUARDAR DATOS ESTADO PERIODO FACULTAD ---------->> NUEVO::::::::::::::::::::::::::::
                   
                    ges=new GestionEstrategicoService();
                    ges.InsertarEstadoPeriodoFacultad(request);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;
               case 81: {
                   
                   // CERRAR APERTURAR META FACULTAD
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.Cerrar_Estado_Meta_Facultad(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 82: {
                   
                   // APERTURAR META FACULTAD
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.Aperturar_Estado_Meta_Facultad(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 83: {
                   
                   // CERRAR APERTURAR AVANCE FACULTAD
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.Cerrar_Estado_Avance_Facultad(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 84: {
                   
                   // APERTURAR AVANCE FACULTAD
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.Aperturar_Estado_Avance_Facultad(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 85: {
                   
                   // ELIMINAR ESTADO PERIODO FACULTAD
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.EliminarEstadoPeriodoFacultadAper(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 86: {
                   
                   // INGRESANDO ACTIVIDADES PARA CADA INDICADOR POST SELECCIONADO
                   
                    ges=new GestionEstrategicoService();
                    EstrategiaIndicador to = ges.BuscarEstrategiaIndicadorId(request.getParameter("idestrategiaIndicador"));
                    request.getSession().setAttribute("ActividadGetIDEstrategiaIndicador", to);
                    
                    String query = (String)request.getParameter("idestrategiaIndicador");
                    request.getSession().setAttribute("idestrategiaIndicadorsession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String idestrategiaIndicador=(String)session.getAttribute("idestrategiaIndicadorsession");
                    
                    System.out.println("--- estrategia indicador -->"+idestrategiaIndicador);
                    request.getSession().setAttribute("Listar_Actividad_Indicador", ges.Listar_Actividad_Indicador(idestrategiaIndicador));
                    
                    response.sendRedirect(LISTACTIVIDADES);
                  
               }
               break;       
               case 87:{
                   
                   // LISTAR ESTRATEGIA INDICADOR SEGUN LA ESTRATEGIA SELECCIONADA EN SESSION ----------->
                                      //Esta funcion carga Valores
                   principalValorSession(request);
                   //Fin de la funcion 
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String id=(String)session.getAttribute("idEjeEstrategiaSession");
                    request.getSession().setAttribute("Listar_Estrategias_Indicador",ges.Listar_Estrategia_Indicador(id,idTipoAreaPri, Integer.parseInt(idFilialPri)));
                    ges=new GestionEstrategicoService();
                    List listarIndicadores = ges.Listar_Indicador(idTipoAreaPri,Integer.parseInt(idFilialPri), idEjeEstrategicoPri);
                    request.getSession().setAttribute("listar_indicador", listarIndicadores);
                    
                    
                    response.sendRedirect(LISTPOSTESTRATEGIASINDICADOR);
               }break;
               case 88: {
                   
                   // LISTAR ACTIVIDADES PARA CADA INDICADOR POST SELECCIONADO EN SESSION
                   
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idestrategiaIndicador=(String)session.getAttribute("idestrategiaIndicadorsession");
                    request.getSession().setAttribute("Listar_Actividad_Indicador", ges.Listar_Actividad_Indicador(idestrategiaIndicador));
                    request.getSession().setAttribute("ActividadGetIDEstrategiaIndicador", ges.BuscarEstrategiaIndicadorId(idestrategiaIndicador));
                    
                    response.sendRedirect(LISTACTIVIDADES);
               
               }
               break;       
               case 89: {
                   
                   // GUARDAR ACTIVIDADES PARA CADA INDICADOR POST SELECCIONADO
                    System.out.println("  hasta aqui llego main--->"+opt);
                    ges=new GestionEstrategicoService();
                    ges.InsertarActividadindicadorSI(request);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idestrategiaIndicador=(String)session.getAttribute("idestrategiaIndicadorsession");
                    request.getSession().setAttribute("Listar_Actividad_Indicador", ges.Listar_Actividad_Indicador(idestrategiaIndicador));
                    request.getSession().setAttribute("ActividadGetIDEstrategiaIndicador", ges.BuscarEstrategiaIndicadorId(idestrategiaIndicador));
                    
                    response.sendRedirect(LISTACTIVIDADES);
                   
               }
               break;       
               case 90: {
                   
                   // IR A ADD ACTIVIDADES PARA CADA INDICADOR POST SELECCIONADO EN SESSION
                   
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idestrategiaIndicador=(String)session.getAttribute("idestrategiaIndicadorsession");
                    request.getSession().setAttribute("Listar_Actividad_Indicador", ges.Listar_Actividad_Indicador(idestrategiaIndicador));
                    request.getSession().setAttribute("ActividadGetIDEstrategiaIndicador", ges.BuscarEstrategiaIndicadorId(idestrategiaIndicador));
                    
                    response.sendRedirect(ADDACTIVIDADES);
               
               }
               break;       
               case 91: {
                   
                   // LISTAR ACTIVIDADES PARA CADA INDICADOR POST SELECCIONADO EN SESSION
                    ges=new GestionEstrategicoService();
                    int idActividadIndicador = Integer.parseInt(request.getParameter("idActividadIndicador"));
                    
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+idActividadIndicador);
                    ges.EliminarActividadindicadorSI(idActividadIndicador);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idestrategiaIndicador=(String)session.getAttribute("idestrategiaIndicadorsession");
                    request.getSession().setAttribute("Listar_Actividad_Indicador", ges.Listar_Actividad_Indicador(idestrategiaIndicador));
                    request.getSession().setAttribute("ActividadGetIDEstrategiaIndicador", ges.BuscarEstrategiaIndicadorId(idestrategiaIndicador));
                    
                    response.sendRedirect(LISTACTIVIDADES);
               
               }
               break;       
               case 92: {
                   
                   // CARGAR ESTADO DE TEMPORADAS ESPECIFICAMENTE EN E.A.P.
                   
                   String namepre = "Aperturar a E.A.P";
                   String datapre = "Especificamente a una E.A.P";
                   
                   request.getSession().setAttribute("name_pre_periodo",namepre); 
                   request.getSession().setAttribute("data_pre_periodo",datapre); 
                   
                   ges=new GestionEstrategicoService();
                   List listarTemporada = ges.Listar_Temporada_Print();
                   request.getSession().setAttribute("listarTemporada", listarTemporada);
                   response.sendRedirect(INDEXESTADOEAP);
                  
               }
               break;       
               case 93: {
                   
                   // LISTAR COMBO EAP FACULTAD SEGUN LA FACULTAD SELECCIONADA
                   
                    ges=new GestionEstrategicoService();
                    String query = (String)request.getParameter("getIDFilialFacultad");
                    request.getSession().setAttribute("IDFilialFacultadsession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    request.getSession().setAttribute("comboEAPFacultadSession", ges.Listar_ComboEAPFacultad(idFilialfacultad));
                    response.sendRedirect(GETCOMBOEAPFACULTAD);
                  
               }
               break;       
               case 94: {
                   
                   // LISTAR ESTADO PERIODO EAP SEGUN LA EAP SELECCIONADA
                   
                    ges=new GestionEstrategicoService();
                    String query = (String)request.getParameter("getIDEapFacultad");
                    request.getSession().setAttribute("IDEapFacultadSession",query);
                    
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;       
               case 95: {
                   
                   // GUARDAR Y LISTAR ESTADO PERIODO EAP SEGUN LA EAP SELECCIONADA
                   
                    ges=new GestionEstrategicoService();
                    ges.InsertarEstadoPeriodoEap(request);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;       
               case 96: {
                   
                   // LISTAR ESTADO PERIODO EAP SEGUN LA EAP SELECCIONADA EN SESSION
                   
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;       
               case 97: {
                   
                   // APERTURAR META EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.Aperturar_Estado_Meta_Eap(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;       
               case 98: {
                   
                   // CERRAR APERTURA META EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.Cerrar_Estado_Meta_Eap(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;       
               case 99: {
                   
                   // APERTURAR AVANCE EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.Aperturar_Estado_Avance_Eap(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;       
               case 100: {
                   
                   // CERRAR APERTURA AVANCE EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.Cerrar_Estado_Avance_Eap(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;       
               case 101: {
                   
                   // Eliminar APERTURA AVANCE EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.EliminarEstadoPeriodoEapAper(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;       
                   
               case 102: {
                   
                   // CERRAR APERTURA AVANCE EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.cerrarPOAestadoEAP(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;                
               case 103: {
                   
                   // APERTURAR APERTURA AVANCE EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.aperturarPOAestadoEAP(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;                
               case 104: {
                   
                   // APERTURAR APERTURA AVANCE EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.cerrarPMestadoEAP(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;                
               case 105: {
                   
                   // APERTURAR APERTURA AVANCE EAP
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodoep = Integer.parseInt(request.getParameter("idestadoperiodoeap"));
                    ges.aperturarPMestadoEAP(idestadoperiodoep);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idEapFacultad=(String)session.getAttribute("IDEapFacultadSession");
                    String idperiodometa=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("lista_estado_periodo_eap", ges.Listar_Estado_Periodo_EAP(idperiodometa, idEapFacultad));
                    response.sendRedirect(LISTESTADOPERIODOEAP);
                  
               }
               break;                
                   
               case 106: {                   
                   // CERRAR APERTURAR AVANCE FACULTAD                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.cerrarPOAestadoFacultad(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 107: {
                   
                   // APERTURAR AVANCE FACULTAD
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.aperturarPOAestadoFacultad(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);                  
               }
               break;                    
               case 108: {                   
                   // CERRAR APERTURAR AVANCE FACULTAD                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.cerrarPMestadoFacultad(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);
                  
               }
               break;       
               case 109: {
                   
                   // APERTURAR AVANCE FACULTAD
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofacultad = Integer.parseInt(request.getParameter("idestadoperiodofacultad"));
                    ges.aperturarPMestadoFacultad(idestadoperiodofacultad);
                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFacultadsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_facultad", ges.Listar_Estado_Periodo_Facultad(idPeriodoMeta, idFilialfacultad));
                    
                    response.sendRedirect(LISTESTADOPERIODOFACULTAD);                  
               }
               break;     
                   
               case 110: {
                   
                   // CERRAR APERTURA AVANCE FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.cerrarPOAestadoFilial(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 111: {
                   
                   // CERRAR APERTURA AVANCE FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.aperturarPOAestadoFilial(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 112: {
                   
                   // CERRAR APERTURA AVANCE FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.cerrarPMestadoFilial(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
               case 113: {
                   
                   // CERRAR APERTURA AVANCE FILIAL
                   
                    ges=new GestionEstrategicoService();
                    int idestadoperiodofilial = Integer.parseInt(request.getParameter("idestadoperiodofilial"));
                    ges.aperturarPMestadoFilial(idestadoperiodofilial);

                    ges=new GestionEstrategicoService();
                    HttpSession session = request.getSession(true); 
                    String idFilial=(String)session.getAttribute("IDFilialsession");
                    String idPeriodoMeta=(String)session.getAttribute("IDPeriodosession");
                    request.getSession().setAttribute("listar_estado_periodo_filial", ges.Listar_Estado_Periodo_Filial_Apertura(idPeriodoMeta, idFilial));
                    response.sendRedirect(LISTESTADOPERIODOFILIAL);
                  
               }
               break;       
                
               default: 
                {
                  out.println("Vuelva a iniciar session error de internet !!");
                }
            
                }
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
