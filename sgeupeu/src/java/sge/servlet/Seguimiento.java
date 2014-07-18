/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sge.modelo.TipoSeguimiento;
import sge.service.SeguimientoService;

/**
 *
 * @author Intel
 */
@WebServlet(name = "Seguimiento", urlPatterns = {"/Seguimiento"})
public class Seguimiento extends HttpServlet {

  private static String INDEXCOORDINADOREAPINFORME= "apps/app_evaluacion/indexAudit.jsp";
   private static String INDEXCOORDINADOREAPMAIN= "apps/app_evaluacion/main_evaluacionAudit.jsp";
   private static String INDEXCOORDINADOREAPREPOR1= "apps/app_evaluacion/reporteEvaluacionAreaApoyo.jsp";   

   
   
   
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
        SeguimientoService ss;

        int opt = Integer.parseInt(request.getParameter("opt")==null ? "0": request.getParameter("opt"));
        
        try {
         switch (opt) {
                case 1: {                
                   principalValorSession(request);
                   ss=new SeguimientoService();
                   sge.modelo.Seguimiento to=new sge.modelo.Seguimiento();
                   to.setIdAvance(Integer.parseInt(request.getParameter("idavance")==null ? "0":request.getParameter("idavance")));
                   to.setIdCiclo(Integer.parseInt(request.getParameter("idciclo")==null ? "0":request.getParameter("idciclo")));
                   to.setIdTipoSeguim(Integer.parseInt(request.getParameter("tiposeguimiento")==null ? "0":request.getParameter("tiposeguimiento")));
                   to.setObservacion(String.valueOf(request.getParameter("observacion")==null ? "0":request.getParameter("observacion")));
                   to.setEstado(1);
                   to.setIdUserObs(Integer.parseInt(idUsuarioPri));                   
                   if(ss.insertarSeguimiento(to)==1){
                   out.print("1");
                   }else{out.print("0"); }
                
                
                }break;
               case 2: { 
                   
                   principalValorSession(request);
                   
                   int idavance=Integer.parseInt(request.getParameter("idavance")==null ? "0":request.getParameter("idavance"));                  
                   ss=new SeguimientoService();
                   if(idavance!=0){
                   ss.actulizarUserViewEstado(Integer.parseInt(idUsuarioPri), idavance);
                   }
                   ss=new SeguimientoService();
                   ArrayList listaEjes=null;
                   listaEjes = ss.reporteObservacionesModelo2(idavance);  
    
                   if(listaEjes.size()>0){
                       
                  
                   out.print("<center><div><p><b>  </b></p> <table border='1' style=\"width: 100%\"> <tr><th style=\"width: 5%\">#</th><th style=\"width: 40%\">Observaci&oacute;n</th><th style=\"width: 25%\">Usuario</th> <th style=\"width: 10%\">Fecha</th></tr>");
               
                   Iterator<Object> inter2=listaEjes.iterator();
                    int i=0;
                     while(inter2.hasNext()){
                    Map datos=  (Map)inter2.next();
                    i=i+1;
                    String color="#ffffff";
                    if((i%2)==1){color="background-color:greenyellow";}else{color="background-color:#ffffff";}                  
                    out.print("<tr style='"+color+"'>"); 
                    out.print("<td>"+i+"</td>");
                    out.print("<td>"+datos.get("observacion")+"</td>");
                    out.print("<td>"+datos.get("usuarioobs")+"</td>");
                    out.print("<td>"+datos.get("fechaobs")+"</td>");
                    out.print("</tr>"); 
                   }
                   out.print("</table><br/></div></center>"); 
                   }else{
                   out.print("<div><center>No hay Registro de bservaciones</center></div>"); 
                   }
                  
               } break;
               case 3: {
                   int idseguimiento=Integer.parseInt(request.getParameter("idseguimiento")==null ? "0":request.getParameter("idseguimiento"));  
                   ss=new SeguimientoService();
                   if(ss.actulizarEstado(idseguimiento, 0)==1){
                   out.print("1");
                   }else{out.print("0"); }               
               }break;
                   
                default:{
                    System.out.println("Error...el valor es: ");
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
