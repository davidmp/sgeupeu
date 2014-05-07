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
import sge.service.OrganizacionService;

/**
 *
 * @author Edwin
 */
@WebServlet(name = "Entering", urlPatterns = {"/Entering"})
public class Entering extends HttpServlet {

    private static String INDEX= "/index.jsp";
    private static String ERROR500= "/apps/app_errors/http_500.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int opt = Integer.parseInt(request.getParameter("opt"));
        OrganizacionService os;
        try {
            switch (opt) {
                case 1: {

                    os = new OrganizacionService();
                    List temporada = os.ListarTemporadaActivaWeb();
                    request.getSession().setAttribute("lista_temporada", temporada);
                    os = new OrganizacionService();
                    List filial = os.Listar_Filial();                   
                    request.getSession().setAttribute("lista_filial", filial);
                    response.sendRedirect(request.getContextPath()+INDEX);
                   
                }break;
                default: 
                {
                    response.sendRedirect(request.getContextPath()+ERROR500);
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
