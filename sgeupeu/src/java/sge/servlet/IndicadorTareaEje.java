/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sge.modelo.Eap;
import sge.modelo.Tareas;
import sge.service.IndicadorService;
import sge.service.ReporteService;

/**
 *
 * @author Intel
 */
@WebServlet(name = "IndicadorTareaEje", urlPatterns = {"/IndicadorTareaEje"})
public class IndicadorTareaEje extends HttpServlet {

   private static String INDEXCOORDINADOREAPINFORME= "apps/app_tareas/indexEje.jsp";
   private static String INDEXCOORDINADOREAPMAIN= "apps/app_tareas/main_eje.jsp";
   private static String INDEXCOORDINADOREAPREPOR1= "apps/app_tareas/reportGeneralEje.jsp";

   
   
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
        try (PrintWriter out = response.getWriter()) {
        response.setContentType("text/html;charset=UTF-8");

        int opt = Integer.parseInt(request.getParameter("opt")==null ? "0": request.getParameter("opt"));
        IndicadorService is;
        ReporteService rs;
        IndicadorService in;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            switch (opt) {
                case 1: {
                response.sendRedirect(INDEXCOORDINADOREAPINFORME);
                }break;
                case 2: {    
 
                sge.modelo.Usuario w = null; w = (sge.modelo.Usuario) request.getSession().getAttribute("listaPerfilUsuario");
                int idEjeEstrategicoX=w.getIdejeestrategico(); 
                is=new IndicadorService();
                request.getSession().setAttribute("listar_eje", is.listaEjeIndividual(idEjeEstrategicoX));                    
                is=new IndicadorService();
                request.getSession().setAttribute("listar_periodo_meta", is.listaPeriodoMeta(request));  

                
                response.sendRedirect(INDEXCOORDINADOREAPMAIN);
                }break;
                case 3: {
                String periodo="";
                int mes1=0;
                int mes2=0;
                int eje=0;
                eje=Integer.parseInt(request.getParameter("ideje1")==null?"0":request.getParameter("ideje1") );
                mes1=Integer.parseInt(request.getParameter("mesinicio1")==null?"0":request.getParameter("mesinicio1") );
                mes2=Integer.parseInt(request.getParameter("mesfin1")==null?"0":request.getParameter("mesfin1") );
                Eap eU=(Eap)request.getSession().getAttribute("eapUsuario");
                periodo=(request.getParameter("perido1")==null?"0":request.getParameter("perido1")).toString();
                int ideapfacultad=eU.getIdeapfacultad();               
                rs=new ReporteService();
                ArrayList lista=rs.cabeceraPOA(ideapfacultad, Integer.parseInt(periodo));
                request.getSession().setAttribute("cabeceraInformePOA", lista);
                 
                rs=new ReporteService();                
                ArrayList lista2=rs.ejesSeleccionadosPOA(ideapfacultad, eje, Integer.parseInt(periodo));
                request.getSession().setAttribute("ejeSeleccionadosPOA", lista2); 
                

                
                response.sendRedirect(INDEXCOORDINADOREAPREPOR1+"?mes1="+mes1+"&mes2="+mes2+"&periodo="+periodo);
                }break;
                case 4: {                
                   principalValorSession(request);
                   in=new IndicadorService();
                   Date fechaactual = new Date();
                   sge.modelo.Tareas to=new sge.modelo.Tareas();
                   to.setIdActividad(Integer.parseInt(request.getParameter("idactividad")==null ? "0":request.getParameter("idactividad")));
                   to.setIdSubcuenta(Integer.parseInt(request.getParameter("idsubcuenta")==null ? "0":request.getParameter("idsubcuenta")));
                   to.setNombre(request.getParameter("tarea")==null ? "":request.getParameter("tarea"));
                   
                   to.setFecha(String.valueOf(request.getParameter("fecha")==null ? fechaactual :request.getParameter("fecha")));
                   to.setFecha2(String.valueOf(request.getParameter("fecha2")==null ? fechaactual :request.getParameter("fecha2")));
                   to.setMonto(Double.parseDouble(request.getParameter("monto")==null ? "0":request.getParameter("monto")));                                      
                   to.setRh(Double.parseDouble(request.getParameter("rh")==null ? "0":request.getParameter("rh")));                                      
                   to.setMateriales(Double.parseDouble(request.getParameter("materiales")==null ? "0":request.getParameter("materiales")));                                      
                   to.setEquipos(Double.parseDouble(request.getParameter("equipos")==null ? "0":request.getParameter("equipos")));                                      
                   to.setAp(Double.parseDouble(request.getParameter("ap")==null ? "0":request.getParameter("ap")));                                      
                   to.setMovilidad(Double.parseDouble(request.getParameter("movilidad")==null ? "0":request.getParameter("movilidad")));                                      
                   to.setOtros(Double.parseDouble(request.getParameter("otros")==null ? "0":request.getParameter("otros")));                                      
                 
                   if(in.insertarTarea(to)==1){
                   out.print("1");//fechaRango
                   }else{out.print("0"); }
                
                
                }break;
                case 5: {               
                   principalValorSession(request);
                   in=new IndicadorService();
                   int idtarea=Integer.parseInt(request.getParameter("idtarea")==null ? "0":request.getParameter("idtarea"));
                   if(in.eliminarTarea(idtarea)==1){
                   out.print("1");//fechaRango
                   }else{out.print("0"); }                                
                }break;
                case 6: {               
                   principalValorSession(request);
                   in=new IndicadorService();
                   int idactividad=Integer.parseInt(request.getParameter("idactividad")==null ? "0":request.getParameter("idactividad"));
                   double monto=Double.parseDouble(request.getParameter("monto")==null ? "0":request.getParameter("monto"));
                   if(in.actualizarMontoActividad(idactividad,monto)==1){
                   out.print("1");//fechaRango
                   }else{out.print("0"); }                                
                }break;

                default:{
                    System.out.println("Error...El valor es: "+opt);
                }
            }
        } catch (Exception e) {
        }
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
