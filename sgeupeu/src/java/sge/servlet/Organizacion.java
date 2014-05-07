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
import sge.modelo.Eap;
import sge.modelo.Facultad;
import sge.modelo.Filial;
import sge.modelo.Institucion;
import sge.modelo.Periodo;
import sge.service.OrganizacionService;
import sge.service.UsuarioService;

/**
 *
 * @author Edwin
 */
@WebServlet(name = "Organizacion", urlPatterns = {"/Organizacion"})
public class Organizacion extends HttpServlet {
    
    String alert_true="<div class='alert alert-success'><script>window.setTimeout(function() { $('.alert').alert('close'); }, 3000);</script><h4>Alert!</h4>Se Registro Correctamente !!</div>";
    String update_true="<div class='alert alert-success'><script>window.setTimeout(function() { $('.alert').alert('close'); }, 3000);</script><h4>Alert!</h4>Se Actualizo los datos correctamente !!</div>";
    String alert_false="<div class='alert alert-error'><script>window.setTimeout(function() { $('.alert').alert('close'); }, 3000);</script><h4>Alert!</h4>Hay un error al registrar un campo de registro !!</div>";
    

    //INSTITUCION
    private static String INDEX= "apps/app_organizacion/index.jsp";
    private static String LISTAR= "apps/app_organizacion/institucion.jsp";
    private static String EDITAR= "apps/app_organizacion/edit_institucion.jsp";
    
    //FILIALES
    private static String LISTARFILIAL= "apps/app_organizacion/list_filial.jsp";
    private static String ADDFILIAL= "apps/app_organizacion/add_filial.jsp";
    private static String EDITFILIAL= "apps/app_organizacion/edit_filial.jsp";
    
    // FACULTAD
    private static String LISTARFACULTAD= "apps/app_organizacion/list_facultad.jsp";
    private static String FACULTADEDIT= "apps/app_organizacion/edit_facultad.jsp";
    
    // EAP
    private static String LISTAREAP= "apps/app_organizacion/list_eap.jsp";
    private static String EDITEAP= "apps/app_organizacion/edit_eap.jsp";
    
    //LISTA PERIODO
    
    private static String LISTAPERIODO= "apps/app_organizacion/list_periodo.jsp";
    private static String ADDPERIODO= "apps/app_organizacion/add_periodo.jsp";
    private static String EDITPERIODO= "apps/app_organizacion/edit_periodo.jsp";
    
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
        
        OrganizacionService os;
        try {
              switch (opt) {
                case 1: {
                    
                    // listar datos de la institucion
                   os=new OrganizacionService();
                   List listarDatos = os.Listar_Datos_Institucion();
                   request.getSession().setAttribute("listar_datos_institucion", listarDatos);
                   response.sendRedirect(LISTAR);
                    
                }break;  
               
                case 2: {
                     
                    // editar datos de la institucion
                    os=new OrganizacionService();
                    os.Actualizar_Datos_Institucion(request, Integer.parseInt(request.getParameter("idinstitucion")));
                    
                    os=new OrganizacionService();
                    List listarDatos = os.Listar_Datos_Institucion();
                    request.getSession().setAttribute("listar_datos_institucion", listarDatos);
                    response.sendRedirect(LISTAR);
                    
                    
                }break;  
                 
                case 3: {
                    
                    // buscar ID Institucion
                    
                    os=new OrganizacionService();
                    Institucion tos = os.Buscar_Institucion_Id(Integer.parseInt(request.getParameter("idinstitucion")));
                    request.getSession().setAttribute("listaInstitucionById", tos);
                    response.sendRedirect(EDITAR); 
                    
                }break;  
                 
                case 4: {
                   String name = "Mi Organización ";
                   String data_name = "Datos, Filiales, Facultades/UPG/Unidad Apoyo y Escuelas Academicas Profesionales/EPG/&Aacute;reas";
                   UsuarioService us=new UsuarioService();
                   List listarAreas = us.areasUnidadUpeU();
                   request.getSession().setAttribute("listar_AreasUnidadSess", listarAreas);  
                   
                   request.getSession().setAttribute("name_org",name); 
                   request.getSession().setAttribute("data_org",data_name); 
                   response.sendRedirect(INDEX); 
                    
                }break;  
                 
                case 5: {
                    
                   os=new OrganizacionService();
                   List listarfilial = os.Listar_Filial();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(LISTARFILIAL);
                    
                }break;  
                 
                case 6: {
                    
                   os=new OrganizacionService();
                   List listarfacu = os.Listar_Facultad();
                   request.getSession().setAttribute("listar_facultad", listarfacu);
                   response.sendRedirect(LISTARFACULTAD);
                    
                }break;  
                 
                case 7: {
                   // guardar Facultad
                   os=new OrganizacionService();
                   os.Registrar_Facultad(request);
                   out.print(alert_true);
                    
                }break;  
                case 8: {
                    
                    // buscar facultad
                    
                    os=new OrganizacionService();
                    Facultad fa = os.Buscar_Facultad_Id(Integer.parseInt(request.getParameter("idFacultad")));
                    request.getSession().setAttribute("Buscar_Facultad_Id", fa);
                    response.sendRedirect(FACULTADEDIT); 
                    
                }break;  
                case 9: {
                    
                    // Actualizar facultad
                   os=new OrganizacionService();
                   os.Actualizar_Facultad(request, Integer.parseInt(request.getParameter("idfacultad")));
                   System.out.println(" enviando actualizado--- ");
                   os=new OrganizacionService();
                   List listarfacu = os.Listar_Facultad();
                   request.getSession().setAttribute("listar_facultad", listarfacu);
                   response.sendRedirect(LISTARFACULTAD);
                    
                }break;  
                 
                case 10: {
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session  
                    
                    // Listar Eap

                   os=new OrganizacionService();
                   List listareap = os.Listar_EapPrimero();
                   request.getSession().setAttribute("listar_eap", listareap);
                   response.sendRedirect(LISTAREAP);
                    
                }break;  
                case 101: {
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session  
                    
                    // Listar Eap

                   os=new OrganizacionService();
                   List listareap = os.Listar_Eap(idTipoAreaPri);
                   request.getSession().setAttribute("listar_eap", listareap);
                   response.sendRedirect(LISTAREAP);
                    
                }break;  
                case 11: {
                    
                    // LISTAR PERIODOS 
                    
                   os=new OrganizacionService();
                   List periodo = os.Listar_Periodo();
                   request.getSession().setAttribute("listar_periodo", periodo);
                   response.sendRedirect(LISTAPERIODO);
                    
                }break;  
                case 12: {
                    os=new OrganizacionService();
                   List periodo = os.ListarTemporadaActivaWeb();
                   request.getSession().setAttribute("listarTemporadaSess", periodo);                    
                    // CARGAR DATOS ADD PERIODO
                   response.sendRedirect(ADDPERIODO);
                    
                }break;  
                case 13: {
                   // guardar Facultad
                   os=new OrganizacionService();
                   os.Registrar_Periodo(request);
                   os=new OrganizacionService();
                   List periodo = os.Listar_Periodo();
                   request.getSession().setAttribute("listar_periodo", periodo);
                   response.sendRedirect(LISTAPERIODO);
                    
                }break;  
                case 14: {
                   
                   // ACTIVAR PERIODO 
                   
                    os=new OrganizacionService();
                    int idPeriodo= Integer.parseInt(request.getParameter("idPeriodo"));
                    os.Activar_Periodo(idPeriodo);
                    os=new OrganizacionService();
                    List periodo = os.Listar_Periodo();
                    request.getSession().setAttribute("listar_periodo", periodo);
                    response.sendRedirect(LISTAPERIODO);
               }
               break;       
               case 15: {
                   
                   // DESACTIVAR PERIODO
                   
                    os=new OrganizacionService();
                    int idPeriodo= Integer.parseInt(request.getParameter("idPeriodo"));
                    os.Desactivar_Periodo(idPeriodo);
                    os=new OrganizacionService();
                    List periodo = os.Listar_Periodo();
                    request.getSession().setAttribute("listar_periodo", periodo);
                    response.sendRedirect(LISTAPERIODO);
               }
               break;
                case 16: {
                    
                    //Recuperar Datos del periodo
                    os=new OrganizacionService();
                   List periodo = os.ListarTemporadaActivaWeb();
                   request.getSession().setAttribute("listarTemporadaSess", periodo);                      
                    os=new OrganizacionService();
                    Periodo to = os.buscarPeriodoId(request.getParameter("idPeriodo"));
                    request.getSession().setAttribute("listaPeriodoById", to);
                    response.sendRedirect(EDITPERIODO);

                }
                break;
                    
                    case 17: {

                        //Actualizar
                        os=new OrganizacionService();
                        os.actualizarDatosPeriodo(request, Integer.parseInt(request.getParameter("idPeriodo")));
                        os=new OrganizacionService();
                        List periodo = os.Listar_Periodo();
                        request.getSession().setAttribute("listar_periodo", periodo);
                        response.sendRedirect(LISTAPERIODO);

                    }
                    break;
                    case 18: {

                        //IR A ADD FILIAL
                        response.sendRedirect(ADDFILIAL);

                    }
                    break;    
                    case 19: {
                        
                        os=new OrganizacionService();
                        os.Registrar_Filial(request);
                        os=new OrganizacionService();
                        List listarfilial = os.Listar_Filial();
                        request.getSession().setAttribute("listar_filial", listarfilial);
                        response.sendRedirect(LISTARFILIAL);
                    
                    }break;  
                        
                    case 20: {
                        os=new OrganizacionService();
                        Filial to= os.BuscarFilialId(request.getParameter("idFilial"));
                        request.getSession().setAttribute("listaFilialById", to);
                        response.sendRedirect(EDITFILIAL);
                    
                    }break;  
                    case 21: {
                     
                        os=new OrganizacionService();
                        os.ActualizarDatosFilial(request,Integer.parseInt(request.getParameter("idFilial")));
                        os=new OrganizacionService();
                        List listarfilial = os.Listar_Filial();
                        request.getSession().setAttribute("listar_filial", listarfilial);
                        response.sendRedirect(LISTARFILIAL);
                    
                    }break;  
                    case 22: {
                     
                        os=new OrganizacionService();
                        int id = Integer.parseInt(request.getParameter("idFilial"));
                        os.EliminarFilial(id);
                        os=new OrganizacionService();
                        List listarfilial = os.Listar_Filial();
                        request.getSession().setAttribute("listar_filial", listarfilial);
                        response.sendRedirect(LISTARFILIAL);
                    
                    }break;  
                        
                    case 23: {
                     
                        os=new OrganizacionService();
                        int id = Integer.parseInt(request.getParameter("idFacultad"));
                        os.EliminarFacultad(id);
                        os=new OrganizacionService();
                        List listarfacu = os.Listar_Facultad();
                        request.getSession().setAttribute("listar_facultad", listarfacu);
                        response.sendRedirect(LISTARFACULTAD);
                    
                    }break;  
                    case 24: {
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                       
                        os=new OrganizacionService();
                        os.Registrar_Eap(request);
                        os=new OrganizacionService();
                        List listareap = os.Listar_Eap(idTipoAreaPri);
                        request.getSession().setAttribute("listar_eap", listareap);
                        response.sendRedirect(LISTAREAP);
                    
                    }break;  
                    case 25: {
                        
                    os=new OrganizacionService();
                    Eap tovar = os.BuscarEapIdId(request.getParameter("idEap"));
                    request.getSession().setAttribute("DatosEapById", tovar);
                    response.sendRedirect(EDITEAP);

                    }break;  
                    case 26: {
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                       
                        os=new OrganizacionService();
                        os.ActualizarDatosEap(request, Integer.parseInt(request.getParameter("idEap")));
                        os=new OrganizacionService();
                        List listareap = os.Listar_Eap(idTipoAreaPri);
                        request.getSession().setAttribute("listar_eap", listareap);
                        response.sendRedirect(LISTAREAP);
                    
                    }break;  
                    case 27: {
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                       
                        os=new OrganizacionService();
                        int idEap = Integer.parseInt(request.getParameter("idEap"));
                        os.EliminarEap(idEap);
                        os=new OrganizacionService();
                        List listareap = os.Listar_Eap(idTipoAreaPri);
                        request.getSession().setAttribute("listar_eap", listareap);
                        response.sendRedirect(LISTAREAP);
                    
                    }break;  
                    case 28: {
                     
                        // ELIMINAR PERIODO
                        
                        os=new OrganizacionService();
                        int id = Integer.parseInt(request.getParameter("idPeriodo"));
                        os.EliminarPeriodo(id);
                        os=new OrganizacionService();
                        List periodo = os.Listar_Periodo();
                        request.getSession().setAttribute("listar_periodo", periodo);
                        response.sendRedirect(LISTAPERIODO);
                    
                    }break;  
                     
               
                   
                   
                default: 
                {
                    out.println(alert_false);
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
