/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import sge.directori.FileDirectori;
import sge.service.GestionEstrategicoService;
import sge.service.IndicadorService;
import sge.service.OrganizacionService;

/**
 *
 * @author Intel
 */
@WebServlet(name = "IndicadorApoyo", urlPatterns = {"/IndicadorApoyo"})
public class IndicadorApoyo extends HttpServlet {
     private static String INDEXCOORDINADOREAP= "apps/app_coordinadores_apoyo/index.jsp";     
     private static String LIST_INDICADOR= "apps/app_coordinadores_apoyo/list_indicador.jsp";
     private static String POA= "apps/app_coordinadores_apoyo/poa.jsp";
     private static String EVIDENCIA= "apps/app_coordinadores_apoyo/evidencia.jsp";
     
     private static String INDEXCONSOLIDADOS= "apps/consolidados/index.jsp";
     private static String CONSOLIDADOEAP= "apps/consolidados/consolidados_eap.jsp";
     private static String CONSOLIDADOFACULTAD= "apps/consolidados/consolidados_facultad.jsp";
     private static String CONSOLIDADOFILIAL= "apps/consolidados/consolidados_filial.jsp";
     
     private static String POLITICAS= "apps/app_coordinadores_apoyo/politicas.jsp";
     private static String EJES= "apps/app_coordinadores_apoyo/ejes.jsp";
     private static String ESTRATEGIAS= "apps/app_coordinadores_apoyo/estrategias.jsp";
     private String dirUploadFiles; 
     FTPClient ftp = null;
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public int idTipoAreaPri=0;
    public String idCategoriaUsuarioPri="0";
    public int idPerspectivaPri=0;
    public String idFilialPri="0";
    public int idEjeEstrategicoPri=0;
    public String idUsuarioPri="0";
    
    public void principalValorSession(HttpServletRequest r){
    sge.modelo.Usuario usuSess=(sge.modelo.Usuario)r.getSession().getAttribute("listaPerfilUsuario");
    idTipoAreaPri=usuSess.getIdtipoarea();
    idCategoriaUsuarioPri=usuSess.getIdcategoriausuario();
    idPerspectivaPri=usuSess.getIdperspectiva();
    idFilialPri=usuSess.getIdfilial();
    idEjeEstrategicoPri=usuSess.getIdejeestrategico();
    idUsuarioPri=usuSess.getIdusuario();
    } 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        IndicadorService is;
        int opt=0;
        int k=0;
        System.out.println("------------ INDICADOR CONTROLADOR --------");
        if(request.getParameter("nro_indicador")!=null){
         k=Integer.parseInt(request.getParameter("nro_indicador")==null?"":request.getParameter("nro_indicador"));    
        opt=Integer.parseInt(request.getParameter("opt"+k+"")==null?"":request.getParameter("opt"+k+""));
        }
        else{
          if(request.getParameter("nro_indicador_1")!=null){
          k=Integer.parseInt(request.getParameter("nro_indicador_1")==null?"":request.getParameter("nro_indicador_1"));    
          opt=Integer.parseInt(request.getParameter("opt"+k+"")==null?"":request.getParameter("opt"+k+""));
          }
          else{
                if(request.getParameter("nro_indicador_2")!=null){
                k=Integer.parseInt(request.getParameter("nro_indicador_2")==null?"":request.getParameter("nro_indicador_2"));    
                opt=Integer.parseInt(request.getParameter("opt"+k+"")==null?"":request.getParameter("opt"+k+""));
                }
                else{
                opt= Integer.parseInt(request.getParameter("opt")==null?"":request.getParameter("opt"));
                }
          }
        }
   System.out.println("------------ opt -------- : "+opt);
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            switch(opt){
                case 1:{
                    is=new IndicadorService();
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    response.sendRedirect(INDEXCOORDINADOREAP);
                break;}
                case 2:{
                    is=new IndicadorService();
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("nro", null);
                    request.getSession().setAttribute("eje", null);
                    request.getSession().setAttribute("indicador", null);
                    request.getSession().setAttribute("listar_indicador", null);  
                    String periodoVigente= (String)request.getParameter("idperiodometa")==null?"": request.getParameter("idperiodometa");
                    System.out.println("Holas:" + periodoVigente);
                    response.sendRedirect(LIST_INDICADOR+"?periodoVigente="+periodoVigente);
                break;}
                case 3:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                      
                    is=new IndicadorService();
                    is.insertarMeta(request);
                    String nombre=request.getParameter("valor")==null?"":request.getParameter("valor"); 
                     if(nombre==""){ 
                       request.getSession().setAttribute("listar_indicador", is.listaIndicador(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));        
                     }else{
                     request.getSession().setAttribute("listar_indicador", is.listaIndicadorBuscar(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
                     } 
                    response.sendRedirect(LIST_INDICADOR);
                    
                break;}
                case 4:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                      
                    is=new IndicadorService();
                    is.actualizarMeta(request);
                     request.getSession().setAttribute("listar_indicador", is.listaIndicador(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));                    
                    response.sendRedirect(LIST_INDICADOR);
                break;}
                case 5:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                      
        is=new IndicadorService();   
        int var=Integer.parseInt(request.getParameter("nro_indicador_4")==null?"":request.getParameter("nro_indicador_4"));
        int idmeta = Integer.parseInt(request.getParameter("idmeta"+var+"")==null?"":request.getParameter("idmeta"+var+""));
        int idavancevalida = Integer.parseInt(request.getParameter("idavancevalida"+var+"")==null?"":request.getParameter("idavancevalida"+var+""));
        dirUploadFiles = getServletContext().getRealPath( getServletContext().getInitParameter( "dirUploadFilesF" ) );
        if( ServletFileUpload.isMultipartContent( request ) ){
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload( factory ); 
        upload.setSizeMax( new Long( getServletContext().getInitParameter( "maxFileSize" ) ).longValue() ); // 1024 x 300 = 307200 bytes = 300 Kb       
        List listUploadFiles = null;
        FileItem item = null;   
        try{
        listUploadFiles = upload.parseRequest( request );         
        Calendar ahora = Calendar.getInstance();
        int dia = ahora.get(Calendar.DAY_OF_MONTH);   //dia del mes
        int mes = ahora.get(Calendar.MONTH);  //mes, de 0 a 11
        int anio = ahora.get(Calendar.YEAR);  //año
        int hora24 = ahora.get(Calendar.HOUR_OF_DAY); //hora en formato 24hs
        int minutos = ahora.get(Calendar.MINUTE);
        int segundos = ahora.get(Calendar.SECOND);
        int milisegundos = ahora.get(Calendar.MILLISECOND);
        Iterator it = listUploadFiles.iterator();
        while( it.hasNext() ){
        item = ( FileItem ) it.next();
        if( !item.isFormField() ){
        if( item.getSize() > 0 ){
                 System.out.println(" Name ->" + item.getName());
   
        String filenameI="X";
        filenameI=is.nombreArchivoDenominacion(idmeta);
        String evidencia   =item.getName();                            
        String url   = filenameI+"-"+dia+"."+mes+"."+anio+"-"+hora24+"."+minutos+"."+segundos+"."+milisegundos+ (item.getName().substring( item.getName().lastIndexOf( "." ) )); 
        
        String type     = item.getContentType();
        long tamanio    = item.getSize();
        String tipo = url.substring( url.lastIndexOf( "." ) );                          
        File archivo = new File( dirUploadFiles, url);
        item.write( archivo );
        
        // Subir al FTP Files
        
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect("192.168.13.38");
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.login("sgew1", "escritura");
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();   
            FileDirectori fd=new FileDirectori();            
        String directorioX=fd.directorioFilial(Integer.parseInt(idFilialPri));        
        try(InputStream input = new FileInputStream(archivo)){
        this.ftp.storeFile(directorioX+url, input);
        
        }
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already saved to server
            }
        }        
        

        
        
        // Fin FTP        
        is=new IndicadorService(); 
        if ( archivo.exists() ){    
        is.insertarEvidencia(evidencia, tipo, url, idavancevalida,Integer.parseInt(idUsuarioPri));
        System.out.println(" Guardado->" + archivo.getAbsolutePath());
        }else{   
        System.out.println( "FALLO AL GUARDAR. NO EXISTE " + archivo.getAbsolutePath() + "</p>");
        }}}}           
        }catch( FileUploadException e ){e.printStackTrace();            
        }catch (Exception e){e.printStackTrace();}}     
        
        request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
        request.getSession().setAttribute("eje", is.Eje(request));
        request.getSession().setAttribute("listar_indicador", is.listaIndicadorEje(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
        request.getSession().setAttribute("listaEvidencia", is.ListaEvidenciaMeta(request));
        request.getSession().setAttribute("variable", is.nroVariableEvidencia(request));
        request.getSession().setAttribute("periodoMeta", is.periodoMeta(request));
        request.getSession().setAttribute("estrategiaIndicador", is.estrategiaIndicadorEvidencia(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
        response.sendRedirect(EVIDENCIA);         
   break;}
                    
                case 6:{
                    is=new IndicadorService();
                    is.eliminarEvidenciaMeta(request);
                    response.sendRedirect(LIST_INDICADOR);
                break;}     
                    
                    
                case 7:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                      
                    is=new IndicadorService();
                    request.getSession().setAttribute("nro", is.nroMeta(request));
                    request.getSession().setAttribute("avanceMeta", is.avanceMeta(request));
                     String nombre=request.getParameter("valor")==null?"":request.getParameter("valor"); 
                     if(nombre==""){ 
                       request.getSession().setAttribute("listar_indicador", is.listaIndicador(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));        
                     }else{
                     request.getSession().setAttribute("listar_indicador", is.listaIndicadorBuscar(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
                     } 

                    response.sendRedirect(LIST_INDICADOR);
                break;}
                    
               case 8:{
                    request.getSession().setAttribute("idperiodometa", null);
                    request.getSession().setAttribute("eje",null);
                    is=new IndicadorService();
                    sge.modelo.Usuario w = null; w = (sge.modelo.Usuario) request.getSession().getAttribute("listaPerfilUsuario");
                    int idEjeEstrategicoX=w.getIdejeestrategico();
                    System.out.println("Verrrrrr: "+idEjeEstrategicoX);
                    request.getSession().setAttribute("listar_eje", is.listaEjeIndividual(idEjeEstrategicoX));
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    request.getSession().setAttribute("listar_indicador", null);  
                    response.sendRedirect(LIST_INDICADOR);
                break;}
                   
                case 9:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                      
                    is=new IndicadorService();
                    is.insertarAvance(request,idUsuarioPri);
                  String nombre=request.getParameter("valor")==null?"":request.getParameter("valor"); 
                     if(nombre==""){ 
                       request.getSession().setAttribute("listar_indicador", is.listaIndicador(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));        
                     }else{
                     request.getSession().setAttribute("listar_indicador", is.listaIndicadorBuscar(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
                     } 
                    response.sendRedirect(LIST_INDICADOR);
                break;}
                    
                case 10:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                      
                    is=new IndicadorService();
                    is.actualizarAvance(request);
                    
                    request.getSession().setAttribute("listar_indicador", is.listaIndicador(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
                     response.sendRedirect(LIST_INDICADOR);
                break;}
                    
                    case 11:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                          
                    is=new IndicadorService();
                    is.buscar(request);
                    request.getSession().setAttribute("eje", is.Eje(request));
                    //request.getSession().setAttribute("eje", is.ejeEap(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("listar_indicador", is.listaIndicadorBuscar(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
                    request.getSession().setAttribute("nro", null);
                    request.getSession().setAttribute("nroestindicador", is.nroIndicador(request));
                    request.getSession().setAttribute("indicador", is.nombreIndicador(request));
                    response.sendRedirect(LIST_INDICADOR+"?valor="+is.nombreIndicador(request));
                    break;}
                        
                        
                        
                    case 12:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                            
                    is=new IndicadorService(); 
                    request.getSession().setAttribute("listaActividadIndicador", is.ListaActividadIndicador(request));
                    request.getSession().setAttribute("listaActividad", is.ListaActividadMeta(request));
                    request.getSession().setAttribute("variable", is.nroVariable(request));
                    request.getSession().setAttribute("periodoMeta", is.periodoMeta(request));
                    request.getSession().setAttribute("estrategiaIndicador", is.estrategiaIndicador(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
                    response.sendRedirect(POA);
                    
                    break;}
                        
                   case 13:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                        
                    is=new IndicadorService();  
                    is.insertarActividad(request);
                    request.getSession().setAttribute("variable", is.nroVariable(request));
                    request.getSession().setAttribute("listaActividad", is.ListaActividadMeta(request));
                    request.getSession().setAttribute("periodoMeta", is.periodoMeta(request));
                    request.getSession().setAttribute("estrategiaIndicador", is.estrategiaIndicador(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
                    response.sendRedirect(POA);
                    
                    break;}
             // Consolidado-----         
                    case 14:{
                    is=new IndicadorService();
                    OrganizacionService org=new OrganizacionService();

                    
                    // --------------------- SET SESSIONES CONSOLIDADOS    
   
              String idfilial= (String)request.getSession().getAttribute("IDFilialFsession");
              int idfilialfacultad = Integer.parseInt(request.getParameter("idfilialfacultad")==null?"":request.getParameter("idfilialfacultad"));
              if(request.getParameter("idfilial")==null){
              request.getSession().setAttribute("sessionFilial", org.getFilialId(Integer.parseInt(idfilial)));
              }else{
              int idfilial1 = Integer.parseInt(request.getParameter("idfilial")==null?"":request.getParameter("idfilial"));   
              request.getSession().setAttribute("sessionFilial", org.getFilialId(idfilial1));
              }
                
              request.getSession().setAttribute("sessionFacultad", org.getFilialFacultadId(idfilialfacultad));
      
                   request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    response.sendRedirect(INDEXCONSOLIDADOS);                    
                    break;}    
            
                     case 15:{
                    is=new IndicadorService();
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("listaEapConsolidado", is.listaEapConsolidado(request));
                    response.sendRedirect(CONSOLIDADOEAP);                     
                    break;} 
                         
                     case 16:{
                    
  
                    request.getSession().setAttribute("idperiodometa", null);
                    request.getSession().setAttribute("eje",null);     
                    is=new IndicadorService();
                    sge.modelo.Usuario w = null; w = (sge.modelo.Usuario) request.getSession().getAttribute("listaPerfilUsuario");
                    int idEjeEstrategicoX=w.getIdejeestrategico();                    
                    request.getSession().setAttribute("listar_eje", is.listaEjeIndividual(idEjeEstrategicoX));
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("listaFacultadConsolidado", null); 
                    request.getSession().setAttribute("listaFilialConsolidado", null);
                   
                    
                    response.sendRedirect(CONSOLIDADOFACULTAD);  
                    break;}    
                         
                    case 17:{
                    request.getSession().setAttribute("idperiodometa", null);
                    request.getSession().setAttribute("eje",null);    
                    is=new IndicadorService();
                    sge.modelo.Usuario w = null; w = (sge.modelo.Usuario) request.getSession().getAttribute("listaPerfilUsuario");
                    int idEjeEstrategicoX=w.getIdejeestrategico();                      
                    request.getSession().setAttribute("listar_eje", is.listaEjeIndividual(idEjeEstrategicoX));
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("listaFilialConsolidado", null);
                    response.sendRedirect(CONSOLIDADOFILIAL);                      
                    break;} 
                        
           //Consolidado----------------------------
           
                        
             // Inicio coordnador EAP           
                    case 18:{
                   GestionEstrategicoService ges=new GestionEstrategicoService();
                   List listarPoliticaUpeu = ges.Listar_Politica_Upeu();
                   request.getSession().setAttribute("listar_politica_upeu", listarPoliticaUpeu);
                    response.sendRedirect(POLITICAS);                      
                    break;} 
                        
                        
                    case 19:{
                    is=new IndicadorService();                    
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    sge.modelo.Usuario w = null; w = (sge.modelo.Usuario) request.getSession().getAttribute("listaPerfilUsuario");
                    int idEjeEstrategicoX=w.getIdejeestrategico();                      
                    request.getSession().setAttribute("listar_eje", is.listaEjeIndividual(idEjeEstrategicoX));
                    response.sendRedirect(EJES);                      
                    break;}      
                        
                        
                   case 20:{
                     is=new IndicadorService();
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    response.sendRedirect(ESTRATEGIAS);                    
                    break;}      
                       
                       
                    case 21:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                          
                    
//        //Inicio Conexion        
//                try {
//                FTPClient ftp = null;                
//                ftp = new FTPClient();
//                ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//                int reply;
//                ftp.connect("192.168.13.38");
//                reply = ftp.getReplyCode();
//                if (!FTPReply.isPositiveCompletion(reply)) {
//                    ftp.disconnect();
//                     throw new Exception("Exception in connecting to FTP Server");
//                }
//                ftp.login("tisge", "wmtisge:$0114");
//                ftp.setFileType(FTP.BINARY_FILE_TYPE);
//                ftp.enterLocalPassiveMode();                             
//                } catch (Exception e) {
//                }
//        
//        //End Conexion                    
                    is=new IndicadorService(); 
                        
                    request.getSession().setAttribute("listaEvidencia", is.ListaEvidenciaMeta(request));
                    request.getSession().setAttribute("variable", is.nroVariableEvidencia(request));
                    request.getSession().setAttribute("periodoMeta", is.periodoMeta(request));
                    request.getSession().setAttribute("estrategiaIndicador", is.estrategiaIndicadorEvidencia(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
                    response.sendRedirect(EVIDENCIA);
                    break;
                    
                    }
                        
                        
                    case 22:{
                    is=new IndicadorService();
                    request.getSession().setAttribute("eje", is.ejeFacultad(request));
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("listaFacultadConsolidado", is.listaIndicadorFacultadBuscar(request)); 
                    response.sendRedirect(CONSOLIDADOFACULTAD);  
                    
                    break;}
                        
                        
                    case 23:{
                    is=new IndicadorService();
                    request.getSession().setAttribute("eje", is.ejeFilial(request));
                    request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("listaFilialConsolidado", is.listaIndicadorFilialBuscar(request));
                    response.sendRedirect(CONSOLIDADOFILIAL);  
                    break;}            
                        
            
        // Inicio coordnador Eje EAP        
                    case 24:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                          
                    is=new IndicadorService();
                    is.buscar(request);
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("eje", is.Eje(request));
                   request.getSession().setAttribute("cicloAcademicoActivoSess", is.cicloActivo());
                    request.getSession().setAttribute("nro", null);
                    request.getSession().setAttribute("nroestindicador", is.nroIndicador(request));
                    
                     String nombre=request.getParameter("valor")==null?"":request.getParameter("valor"); 
                     if(nombre==""){ 
                      request.getSession().setAttribute("listar_indicador", is.listaIndicadorEje(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));       
                     }else{
                     request.getSession().setAttribute("listar_indicador", is.listaIndicadorBuscar(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
                     }
                    response.sendRedirect(LIST_INDICADOR);
                    break;}  
                        
                    case 25:{
                    is=new IndicadorService();
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("eje", is.Eje(request));
                    request.getSession().setAttribute("listaFacultadConsolidado", is.listaIndicadorFacultadEje(request));
                    response.sendRedirect(CONSOLIDADOFACULTAD);
                    break;}  
                        
                    case 26:{
                    is=new IndicadorService();
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("eje", is.Eje(request));
                    request.getSession().setAttribute("listaFilialConsolidado", is.listaIndicadorFilialEje(request));
                    response.sendRedirect(CONSOLIDADOFILIAL);
                    break;}         
                       
                       
                    case 27:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                          
                    is=new IndicadorService();
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                  
                    request.getSession().setAttribute("nro", null);
                    request.getSession().setAttribute("eje", null);
                    
                     String nombre=request.getParameter("valor")==null?"":request.getParameter("valor"); 
                     if(nombre==""){ 
                       request.getSession().setAttribute("listar_indicador", is.listaIndicador(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
                    }else{
                     request.getSession().setAttribute("listar_indicador", is.listaIndicadorBuscar(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
                     }
                    
                    response.sendRedirect(LIST_INDICADOR);
                    break;}
                        
                        
                    case 28:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                          
                    is=new IndicadorService(); 
                    is.EliminarActividadMeta(request);
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("eje", is.Eje(request));
                    request.getSession().setAttribute("listar_indicador", is.listaIndicadorEje(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
                    request.getSession().setAttribute("nro", null);  
                    request.getSession().setAttribute("variable", is.nroVariable(request));
                    request.getSession().setAttribute("listaActividad", is.ListaActividadMeta(request));
                    request.getSession().setAttribute("periodoMeta", is.periodoMeta(request));
                    request.getSession().setAttribute("estrategiaIndicador", is.estrategiaIndicador(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
                    response.sendRedirect(POA);
                    
                    break;}     
                    
                    case 29:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                          
                    is=new IndicadorService(); 
                    is.EliminarEvidencia(request);
                    request.getSession().setAttribute("idperiodometa", is.idPeriodoMeta(request));
                    request.getSession().setAttribute("eje", is.Eje(request));
                    request.getSession().setAttribute("listar_indicador", is.listaIndicadorEje(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));
                    request.getSession().setAttribute("listaEvidencia", is.ListaEvidenciaMeta(request));
                    request.getSession().setAttribute("variable", is.nroVariableEvidencia(request));
                    request.getSession().setAttribute("periodoMeta", is.periodoMeta(request));
                    request.getSession().setAttribute("estrategiaIndicador", is.estrategiaIndicadorEvidencia(request,is.estadometa(request),is.estadoavance(request),Integer.parseInt(idFilialPri)));
                    response.sendRedirect(EVIDENCIA);
                    
                    break;}     
                    
                    case 30:{
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                          
                    is=new IndicadorService();                     
                    is=new IndicadorService(); 
                    is.actualizarActividad(request);
                    request.getSession().setAttribute("listaActividadIndicador", is.ListaActividadIndicadorAx(request));                    
                    request.getSession().setAttribute("listaActividad", is.ListaActividadMetaAx(request));                    
                    request.getSession().setAttribute("variable", is.nroVariableAx(request));
                    request.getSession().setAttribute("periodoMeta", is.periodoMetaAx(request));
                    request.getSession().setAttribute("estrategiaIndicador", is.estrategiaIndicadorAx(request,is.estadometa(request),is.estadoavance(request), Integer.parseInt(idFilialPri)));                    
                    
                    response.sendRedirect(POA);

                    break;}                    
                    
            }
        } finally {            
            out.close();
        }
                  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
