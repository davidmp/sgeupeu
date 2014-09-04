/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sge.modelo.Ejeestrategico;
import sge.modelo.Facultad;
import sge.modelo.Filial;
import sge.service.IndicadorService;
import sge.service.OrganizacionService;
import sge.service.ReporteService;
import sge.service.UsuarioService;

/**
 *
 * @author Intel
 */
@WebServlet(name = "EstadisticaGeneral", urlPatterns = {"/EstadisticaGeneral"})
public class EstadisticaGeneral extends HttpServlet {

   private static String INDEXCOORDINADOREAPINFORME= "apps/app_estadistica/indexGeneral.jsp";
   private static String INDEXCOORDINADOREAPMAIN= "apps/app_estadistica/main_EstadisticaGeneral.jsp";   
   private static String INDEXCOORDINADOREAPREPOR1= "apps/app_estadistica/reporteEstadistica.jsp";
   
   private static String INDEXCOORDINADOREAPINFORMEJ= "apps/app_estadistica/indexGeneralJ.jsp";
   private static String INDEXCOORDINADOREAPMAINJ= "apps/app_estadistica/main_EstadisticaGeneralJ.jsp";   
   private static String INDEXCOORDINADOREAPREPOR1J= "apps/app_estadistica/reporteEstadisticaJ.jsp";
   

   
   
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
        ReporteService rs;
        UsuarioService us;
        OrganizacionService os;
        int opt = Integer.parseInt(request.getParameter("opt")==null ? "0": request.getParameter("opt"));
        
        try {
         switch (opt) {
                case 1: {
                is=new IndicadorService();
                request.getSession().setAttribute("listar_eje", is.listaEje(1));                      
                rs=new ReporteService();
                request.getSession().setAttribute("listar_tipoarea", rs.areaPrePosgrado());                      
                response.sendRedirect(INDEXCOORDINADOREAPINFORME);
                }break;
                case 2: {
                principalValorSession(request);    
                rs=new ReporteService();
                us=new UsuarioService();
                Facultad fa= null;
                int idfacultadPoa = Integer.parseInt(request.getParameter("idfilialfacultad")==null ? "0": request.getParameter("idfilialfacultad"));
                fa= us.facultadUsuarioRealSelect(Integer.parseInt(idUsuarioPri));
                System.out.println("Probarr>>>"+Integer.parseInt(idUsuarioPri));                
                if(fa!=null && Integer.parseInt(idCategoriaUsuarioPri)!=7){
                if(idfacultadPoa!=0){   
                System.out.println("Probarr>>>"+fa.getIdfilialfacultad());
                request.getSession().setAttribute("listarEapTempReporte", rs.reporEapFacultadFilialEap(Integer.parseInt(idFilialPri), idfacultadPoa));
                }else{
                request.getSession().setAttribute("listarEapTempReporte", rs.reporEapFacultadFilialEap(Integer.parseInt(idFilialPri), fa.getIdfilialfacultad().intValue()));
                }
                }else{
                request.getSession().setAttribute("listarEapTempReporte", rs.reporEapFacultadFilial(Integer.parseInt(idFilialPri)));
                }                
                is=new IndicadorService();
                request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));                    
                    
                response.sendRedirect(INDEXCOORDINADOREAPMAIN);
                } break;
                    
                    
                case 3: {
                int periodo=0;
                String eje="";
                int tipoarea;
                eje=request.getParameter("ideje1")==null?"0":request.getParameter("ideje1");    
                String[] vectorDatos=(eje.replace('*','/')).split("/");
                
                periodo=Integer.parseInt(request.getParameter("perido1")==null?"0":request.getParameter("perido1") );                
                tipoarea=Integer.parseInt(request.getParameter("tipoarea1")==null?"0":request.getParameter("tipoarea1"));

                
                rs=new ReporteService();
                Filial lista=null;              
                lista=rs.filialPerido(Integer.parseInt(idFilialPri), periodo);
                request.getSession().setAttribute("cabeceraInformePOA", lista);
                
                rs=new ReporteService();                
                ArrayList lista2=rs.reporteCarrerasPregrado(Integer.parseInt(idFilialPri));
                request.getSession().setAttribute("carrerasPregradoRE", lista2); 
                /*rs=new ReporteService();
                ArrayList lista3=rs.evaluacionPlanEstrategico(Integer.parseInt(vectorDatos[0]), periodo, Integer.parseInt(vectorDatos[1]), Integer.parseInt(idFilialPri),eje);
                request.getSession().setAttribute("avanceTodoSemaforo", lista3); */
                
                response.sendRedirect(INDEXCOORDINADOREAPREPOR1+"?idtipoarea="+tipoarea+"&idperiodo="+periodo+"&eje="+vectorDatos[0]+"&idFilialPri="+idFilialPri+"&nombreeje="+vectorDatos[1]);                             
                }break;
                case 11: {
                is=new IndicadorService();
                request.getSession().setAttribute("listar_eje", is.listaEje(1));                      
                os=new OrganizacionService();
                request.getSession().setAttribute("listar_sed", os.Listar_Filial());                      
                rs=new ReporteService();
                request.getSession().setAttribute("listar_tipoarea", rs.areaPrePosgrado());                      
                response.sendRedirect(INDEXCOORDINADOREAPINFORMEJ);
                }break;
                case 21: {
                principalValorSession(request);    
                rs=new ReporteService();
                us=new UsuarioService();
                Facultad fa= null;
                int idfacultadPoa = Integer.parseInt(request.getParameter("idfilialfacultad")==null ? "0": request.getParameter("idfilialfacultad"));
                fa= us.facultadUsuarioRealSelect(Integer.parseInt(idUsuarioPri));
                System.out.println("Probarr>>>"+Integer.parseInt(idUsuarioPri));                
                if(fa!=null && Integer.parseInt(idCategoriaUsuarioPri)!=7){
                if(idfacultadPoa!=0){   
                System.out.println("Probarr>>>"+fa.getIdfilialfacultad());
                request.getSession().setAttribute("listarEapTempReporte", rs.reporEapFacultadFilialEap(Integer.parseInt(idFilialPri), idfacultadPoa));
                }else{
                request.getSession().setAttribute("listarEapTempReporte", rs.reporEapFacultadFilialEap(Integer.parseInt(idFilialPri), fa.getIdfilialfacultad().intValue()));
                }
                }else{
                request.getSession().setAttribute("listarEapTempReporte", rs.reporEapFacultadFilial(Integer.parseInt(idFilialPri)));
                }                
                is=new IndicadorService();
                request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));                    
                    
                response.sendRedirect(INDEXCOORDINADOREAPMAINJ);
                } break;
                    
                    
                case 31: {
                int periodo=0;
                String eje="";
                int tipoarea;
                int filial;
                eje=request.getParameter("ideje1")==null?"0":request.getParameter("ideje1");    
                String[] vectorDatos=(eje.replace('*','/')).split("/");
                
                periodo=Integer.parseInt(request.getParameter("perido1")==null?"0":request.getParameter("perido1") );                
                tipoarea=Integer.parseInt(request.getParameter("tipoarea1")==null?"0":request.getParameter("tipoarea1"));
                filial=Integer.parseInt(request.getParameter("subf")==null?"0":request.getParameter("subf"));

                
                rs=new ReporteService();
                Filial lista=null;              
                lista=rs.filialPerido(filial, periodo);
                request.getSession().setAttribute("cabeceraInformePOA", lista);
                
                rs=new ReporteService();                
                ArrayList lista2=rs.reporteCarrerasPregrado(filial);
                request.getSession().setAttribute("carrerasPregradoRE", lista2); 
                /*rs=new ReporteService();
                ArrayList lista3=rs.evaluacionPlanEstrategico(Integer.parseInt(vectorDatos[0]), periodo, Integer.parseInt(vectorDatos[1]), Integer.parseInt(idFilialPri),eje);
                request.getSession().setAttribute("avanceTodoSemaforo", lista3); */
                
                response.sendRedirect(INDEXCOORDINADOREAPREPOR1J+"?idtipoarea="+tipoarea+"&idperiodo="+periodo+"&eje="+vectorDatos[0]+"&idFilialPri="+filial+"&nombreeje="+vectorDatos[1]);                             
                }break;
    
                default:{
                    System.out.println("Error...El valor es: "+opt);
                }
                }   
            
            
            
            
        } catch (Exception e) {
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
