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
import sge.modelo.Eap;
import sge.modelo.Ejeestrategico;
import sge.modelo.Facultad;
import sge.service.IndicadorService;
import sge.service.ReporteService;
import sge.service.UsuarioService;

/**
 *
 * @author Intel
 */
@WebServlet(name = "InformeActividadGeneral", urlPatterns = {"/InformeActividadGeneral"})
public class InformeActividadGeneral extends HttpServlet {

   private static String INDEXCOORDINADOREAPINFORME= "apps/app_informesactividad/indexGeneral.jsp";
   private static String INDEXCOORDINADOREAPMAIN= "apps/app_informesactividad/main_actividadGeneral.jsp";
   private static String INDEXCOORDINADOREAPREPOR1= "apps/app_informesactividad/reportGeneral.jsp";
   private static String INDEXCOORDINADOREAPREPOR2= "apps/app_informesactividad/reporteGeneralAreaApoyo.jsp";
   
   
   
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

        int opt = Integer.parseInt(request.getParameter("opt")==null ? "0": request.getParameter("opt"));
        IndicadorService is;
        ReporteService rs;
        UsuarioService us;
        try {
            switch (opt) {
                case 1: {
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
                }break;
                case 3: {
                String periodo="";
                int mes1=0;
                int mes2=0;
                int eje=0;
                int ideapfacultad;
                String datosGeneral="";
                eje=Integer.parseInt(request.getParameter("ideje1")==null?"0":request.getParameter("ideje1") );
                mes1=Integer.parseInt(request.getParameter("mesinicio1")==null?"0":request.getParameter("mesinicio1") );
                mes2=Integer.parseInt(request.getParameter("mesfin1")==null?"0":request.getParameter("mesfin1") );                
                datosGeneral=(request.getParameter("eap1")==null?"0":request.getParameter("eap1")).toString();
                
                String[] vectorDatos=(datosGeneral.replace('*','/')).split("/");
                
                rs=new ReporteService();
                ArrayList lista=rs.cabeceraPOA(Integer.parseInt(vectorDatos[0]));
                request.getSession().setAttribute("cabeceraInformePOA", lista);
                System.out.println("verr>  "+Integer.parseInt(vectorDatos[0]));
                rs=new ReporteService();                
                ArrayList lista2=rs.ejesSeleccionadosPOA(Integer.parseInt(vectorDatos[0]), eje);
                request.getSession().setAttribute("ejeSeleccionadosPOA", lista2); 
                
                if(Integer.parseInt(vectorDatos[1])==3){
                response.sendRedirect(INDEXCOORDINADOREAPREPOR2+"?mes1="+mes1+"&mes2="+mes2);    
                }else{
                response.sendRedirect(INDEXCOORDINADOREAPREPOR1+"?mes1="+mes1+"&mes2="+mes2);
                }                               
                }break;
                case 4: {
                principalValorSession(request);
                is=new IndicadorService();
                int idtipoarea=Integer.parseInt(request.getParameter("idtipoarea")==null?"0":request.getParameter("idtipoarea") );
                int codigoComparar=Integer.parseInt(request.getParameter("codigo")==null?"0":request.getParameter("codigo") );
                //is.listaEjeIndividual(idEjeEstrategicoX)
                List<Ejeestrategico> ejeEs=null;
                if(idtipoarea==3){
                ejeEs=is.listaEjeIndividualAreas(idtipoarea, codigoComparar);
                }else{
                ejeEs=is.listaEje(idtipoarea);
                }                
                  if(idtipoarea!=3){
                  out.print("<option value='0'> Todos </option>");
                  }                  
                 if(ejeEs!=null){ 
                  for(Ejeestrategico ejeE:ejeEs){
                    
                    out.print("<option value='"+ejeE.getIdejeestrategico()+"'> "+ejeE.getNombre()+" </option>");                    
                  }
                 }
                }
                break;
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
