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
import sge.modelo.CoordinadorEjeeap;
import sge.modelo.Coordinadoreap;
import sge.modelo.Coordinadorfacultad;
import sge.modelo.Filial;
import sge.modelo.Persona;
import sge.service.GestionEstrategicoService;
import sge.service.OrganizacionService;
import sge.service.UsuarioService;

/**
 *
 * @author edwin
 */
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class Usuario extends HttpServlet {

    private static String SESSION= "apps/app_web/main.jsp";
    private static String INDEX= "index.jsp";
    String message="Bienvenido !! ";
    String correcto="Se Registro Correctamente !!!";
    String error="Error en Registro !!!";
    String bienvenido="<div class='alert alert-success' ><button type='button' class='close' onclick='salir();'  data-dismiss='alert'>×</button>"+message+".</div>";

    private static String PANELCONTROL= "apps/app_mantenimiento/index.jsp";
    private static String USUARIO= "apps/app_mantenimiento/list_user_avanzado.jsp";
    private static String USUARIOFILIAL= "apps/app_usuario/list.jsp";
    private static String DATOSUSUARIO= "apps/app_usuario/perfil.jsp";
    private static String DIRECCIONAJAX= "apps/app_web/index.jsp#listaruser";
    
    private static String LISTARPERSONAADMIN= "apps/app_usuario/list_personas_admin.jsp";
    private static String EDITARPERSONAADMIN= "apps/apps_persona/persona_edit_admin.jsp";
    
    // LISTA DE USUARIOS COMO ADMINISTRADOR CENTRAL
    
    private static String INDEXUSUARIO= "apps/app_usuario/index_admin.jsp";
    private static String LISTARUSUARIOADMIN= "apps/app_usuario/list_usuario_admin.jsp";
    
    
    
    
    
    
    private static String LISTARUSUARIOFILIAL= "apps/app_cuenta/list_filial.jsp";
    private static String LISTARCOORDINADORFACULTADFILIAL= "apps/app_coordinador_facultad/list_coordinador_facultad.jsp";
    private static String LISTARUSUARIOFILIALPERIODO= "apps/app_coordinador_facultad/list_usuario_periodo.jsp";
    private static String ADDDATOS= "apps/app_usuario/add_usuario_admin.jsp";
    private static String ADDPERSONA= "apps/app_usuario/add_persona_admin.jsp";
    private static String ADITPERSONA= "apps/app_usuario/edit_persona_admin.jsp";
    private static String ADITPERSONAUSER= "apps/app_usuario/edit_persona_user.jsp";
    private static String ADDUSUARIOPER= "apps/app_usuario/add_usuario_per_admin.jsp";
    private static String EDITUSUARIO= "apps/app_usuario/edit_usuario_admin.jsp";
    private static String ADDDATOSFILIAL= "apps/app_cuenta/add_filial.jsp";
    private static String ADDCUENTA= "apps/app_usuario/add_cuenta_usuario_admin.jsp";
    private static String ADDCUENTAFILIAL= "apps/app_cuenta/add_cuenta_filial.jsp";
    private static String ADDSUCCESSFUL= "apps/app_cuenta/add_successful.jsp";
    
    // FIN DE MENU CUENTA 
    
    private static String EDITARUSUARIOADMIN= "apps/apps_usuario/usuario_edit_admin.jsp";
    private static String AUTOCOMPLETEPERSONA= "apps/apps_usuario/usuario_autocomplete.jsp";
    private static String LISTARAUTOCOMPLETE= "apps/apps_usuario/listar.jsp";
    private static String PRIVILEGIOS= "apps/apps_privilegio/privilegio_list.jsp";
    private static String REGISTROCUENTA= "apps/apps_cuenta/datos_cuenta_add.jsp";
    private static String REGISTROCUENTACCESO= "apps/apps_cuenta/acceso_cuenta_add.jsp";
    
   
    
    // ACCESOS DE USUARIO FILIAL
    
    private static String INDEXUSUARIOFILIAL= "apps/app_usuario/index_filial.jsp";
    private static String LISTARUSUARIOSFILIAL= "apps/app_usuario/list_usuario_filial.jsp";
    private static String LISTAPERSONASFILIAL= "apps/app_usuario/list_personas_filial.jsp";
    private static String ADDPERSONAFILIAL= "apps/app_usuario/add_persona_filial.jsp";
    private static String ADDUSUARIOFILIAL= "apps/app_usuario/add_usuario_filial.jsp";
    private static String ADITPERSONAFILIAL= "apps/app_usuario/edit_persona_filial.jsp";
    private static String ADITPERSONAUSERFILIAL= "apps/app_usuario/edit_persona_filial_user.jsp";
    private static String LISTARUSUARIOSFILIALPRIVILEGIO= "apps/app_usuario/priv_usuario_filial.jsp";
    private static String EDITUSUARIOFILIAL= "apps/app_usuario/edit_usuario_filial.jsp";
    private static String ADDDATOSCUENTAFILIAL= "apps/app_usuario/add_datos_cuenta_filial.jsp";
    private static String ADDDATOSCUENTAUSUARIOFILIAL= "apps/app_usuario/add_datos_cuenta_usuario_filial.jsp";
    
    
    // APP COORDINADOR
    
    private static String INDEXCOORDINADORFILIAL= "apps/app_coordinador/index_coordinador_filial.jsp";
    private static String LISTAPRECOORDINADORFILIALPERIODO= "apps/app_coordinador/list_pre_coordinador_filial_periodo.jsp";
    private static String LISTAPOSTCOORDINADORFILIALPERIODO= "apps/app_coordinador/list_post_coordinador_filial_periodo.jsp";
    private static String EDITPOSTCOORDINADORFILIALPERIODO= "apps/app_coordinador/edit_post_coordinador_filial_periodo.jsp";
    
    // APP E-A-P
    
    private static String LISTAPRECOORDINADOREAPFILIALPERIODO= "apps/app_coordinador/list_pre_coordinador_eap_filial_periodo.jsp";
    private static String LISTAPOSTCOORDINADOEAPFILIALPERIODO= "apps/app_coordinador/list_post_coordinador_eap_filial_periodo.jsp";
    private static String EDITPOSTCOORDINADOREAPFILIALPERIODO= "apps/app_coordinador/edit_post_coordinador_eap_filial_periodo.jsp";
    
    // FILIAL FACULTAD
    
    private static String INDEXFILIALFACULTADEAP= "apps/app_organizacion/index_facultad_eap_filial.jsp";
    private static String LISTAFILIALFACULTADFILIAL= "apps/app_organizacion/list_filial_facultad.jsp";
    private static String ADDFILIALFACULTADPERIODOFILIAL= "apps/app_organizacion/add_filial_facultad_periodo.jsp";
    private static String ADDFILIALFACULTADFILIAL= "apps/app_organizacion/add_filial_facultad_filial.jsp";
    private static String ADDCOORDINADOREJEFILIAL= "apps/app_organizacion/add_eap_ejecoordinador.jsp";
    
    // LIST EAP-FACULTAD - DESDE USUARIO FILIAL
    
    private static String LISTAEAPFACULTADFILIAL= "apps/app_organizacion/list_eap_facultad.jsp";
    private static String ADDEAPFACULTADFILIAL= "apps/app_organizacion/add_eap_facultad_filial.jsp";
    
    
    
    // WEB PERFILES DE SESSION
    private static String LISTEAPFACULTADFILIAL= "apps/app_web/__list_eap_facultad.jsp";
    private static String LISTFILIALFACULTAD= "apps/app_web/__list_filial_facultad.jsp";
    
    //Change Password
    private static String CHANGEPASSWORD= "apps/app_web/Change.jsp";

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
        //String orderId = request.getAttribute("listaruser").toString();
        UsuarioService us;
        GestionEstrategicoService ge;
        OrganizacionService os;
        try {
            switch (opt) {
                case 1: {

                   us=new UsuarioService();
                   List listarUsuario = us.listar_usuario();
                   request.getSession().setAttribute("listar_usuario", listarUsuario);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   us=new UsuarioService();
                   List listarAreas = us.areasUnidadUpeU();
                   request.getSession().setAttribute("listar_AreasUnidadSess", listarAreas);                   
                   
                   us=new UsuarioService();
                   List listarPerspectivas = us.perspectivasUPeU();
                   request.getSession().setAttribute("listar_PerspectivasSess", listarPerspectivas); 
                   
                   us=new UsuarioService();
                   List listarEjeEstrategico = us.ejesUPeU();
                   request.getSession().setAttribute("listar_EjeEstrategicoSess", listarEjeEstrategico);                   
                   response.sendRedirect(LISTARUSUARIOADMIN);
                }
                break;
             case 2: {

                   // registrar usuario como superadministrador                 
                   us = new UsuarioService();
                   us.registrar_usuario(request);                   
                   us=new UsuarioService();
                   List listarUsuario = us.listar_usuario();
                   request.getSession().setAttribute("listar_usuario", listarUsuario);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   us=new UsuarioService();
                   List listarAreas = us.areasUnidadUpeU();
                   request.getSession().setAttribute("listar_AreasUnidadSess", listarAreas);
                   us=new UsuarioService();
                   List listarPerspectivas = us.perspectivasUPeU();
                   request.getSession().setAttribute("listar_PerspectivasSess", listarPerspectivas); 
                   
                   us=new UsuarioService();
                   List listarEjeEstrategico = us.ejesUPeU();
                   request.getSession().setAttribute("listar_EjeEstrategicoSess", listarEjeEstrategico);                     
                   response.sendRedirect(LISTARUSUARIOADMIN);
                }
                break;
                 
                case 3: {

                   // eliminar usuario como superadministrador
                 
                   us = new UsuarioService();
                   int idusuario = Integer.parseInt(request.getParameter("idUsuario"));
                   us.eliminar_usuario(idusuario);
                   
                   us=new UsuarioService();
                   List listarUsuario = us.listar_usuario();
                   request.getSession().setAttribute("listar_usuario", listarUsuario);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   
                   response.sendRedirect(LISTARUSUARIOADMIN);
                }
                break;
                    
                case 4: {
                    
                    // Valida login Usuario y obtener el ID 
                    
                    UsuarioService uss=new UsuarioService();
                    if(uss.validar(request).toArray().length !=0){
                    request.getSession().setAttribute("SessionCounter", uss.Id_usuario_session(request));
                    request.getSession().setAttribute("listax", uss.validar(request));
                    System.out.println("id usuario session --> "+uss.Id_usuario_session(request));
                    System.out.println("id filial usuario session --> "+uss.Id_filial_usuario(request));
                    System.out.println("id categoria usuario  --> "+uss.Id_categoria_usuario(request));
                    if(uss.Id_usuario_session(request)!=null){
                    String idUsuarioS=uss.Id_usuario_session(request);
                    request.getSession().setAttribute("IdUsuarioS",idUsuarioS);
                    String idUsuarioFilial=uss.Id_filial_usuario(request);
                    request.getSession().setAttribute("IdUsuarioF",idUsuarioFilial);
                    request.getSession().setAttribute("listaPerfilUsuario", uss.datos_usuario(idUsuarioS));
                    
                    //Periodo vigente
                    uss=new UsuarioService();
                    request.getSession().setAttribute("SesPeriodoVigente", uss.periodoVigenteUPeU());
                    
                    // CARGA DE DATOS PERFIL USER FILIAL LISTA DE FACULTADES
                    
                    os=new OrganizacionService();
                    HttpSession session = request.getSession(true); 
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaFilialFacultadfilial", os.listar_filial_facultad_desde_usuarioPrimero(idfi));
                    request.getSession().setAttribute("listaEAPFacultadfilial", os.Listar_Eap_Facultad(idfi));
                    
                    
                    sge.modelo.Usuario w = null; w = (sge.modelo.Usuario) request.getSession().getAttribute("listaPerfilUsuario");
                    int idEjeEstrategicoX=w.getIdejeestrategico();
                  
                    if(idEjeEstrategicoX==0){
                    request.getSession().setAttribute("eapUsuario",uss.eapUsuario(uss.Id_usuario(request)));
                    request.getSession().setAttribute("facultadUsuario",uss.facultadUsuario(uss.Id_usuario(request)));
                    }else{
                        if(Integer.parseInt(w.getIdcategoriausuario())==4 || Integer.parseInt(w.getIdcategoriausuario())==6){
                        request.getSession().setAttribute("eapUsuario",uss.eapUsuario(uss.Id_usuario(request)));
                        request.getSession().setAttribute("facultadUsuario",uss.facultadUsuario(uss.Id_usuario(request)));
                        }else{
                            request.getSession().setAttribute("eapUsuario",uss.eapUsuarioEje(uss.Id_usuario(request), Integer.parseInt(idUsuarioFilial)));
                            request.getSession().setAttribute("facultadUsuario",uss.facultadUsuarioEje(uss.Id_usuario(request), Integer.parseInt(idUsuarioFilial)));                        
                        }
                    }                    
                    
                    request.getSession().setAttribute("filialUsuario",uss.filialUsuario(uss.Id_usuario(request)));
                   
                    
                     os=new OrganizacionService();
                     List Filialista = os.Listar_Filial();
                     request.getSession().setAttribute("listaFilialesAdmin", Filialista);
                     
                     os=new OrganizacionService();
                     List FilialFacultad = os.reporteFilialFacultad();
                     request.getSession().setAttribute("listaFilialFacultad", FilialFacultad);
                     
                     os=new OrganizacionService();
                     List FacultadEap = os.reporteFacultadEap();
                     request.getSession().setAttribute("listaFacultadeap", FacultadEap);
                     
                     
                    }else{
                    }
                   
                    
                    
                    response.sendRedirect(request.getContextPath()+"/"+SESSION);
          
                    }else{response.sendRedirect(request.getContextPath()+"/"+INDEX);} 
                }
                break;
                
                case 5: {
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaUserFilial", us.listar_usuario_filial(idfilialuser));
                    
                    HttpSession sessionuser = request.getSession(true); 
                    String datosuser=(String)sessionuser.getAttribute("IdUsuarioS");
                    request.getSession().setAttribute("datosuser", us.datos_usuario(datosuser));
                    
                    
                    us=new UsuarioService();
                    List listarPersona = us.listar_persona();
                    request.getSession().setAttribute("listar_persona", listarPersona);
                    
                    
                    
                    response.sendRedirect(request.getContextPath()+"/"+ USUARIOFILIAL);
                }
                break;
                    
                 case 6: {
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idUsuario=(String)session.getAttribute("IdUsuarioS");
                    request.getSession().setAttribute("listaPerfilUsuario", us.datos_usuario(idUsuario));
                    response.sendRedirect(request.getContextPath()+"/"+ DATOSUSUARIO);
                }
                break;
                  case 7: {
                    // registrar con ajax Usuario !!
                      
                    us = new UsuarioService();
                    us.registrar_usuario(request);
                    out.println("<div class='alert alert-success' ><button type='button' class='close' onclick='salir();'  data-dismiss='alert'>×</button>"+correcto+".</div>");
                  }
                break;  
                  
                  case 8: {
                      
                      // CARGAR PAGINA INDEX DE USUARIO
                   String name = "Mis Usuarios";
                   String data_name = "Reporte, Registro, Actualizacion de Datos y activacion de cuentas ";
                   
                   request.getSession().setAttribute("name_user",name); 
                   request.getSession().setAttribute("data_user",data_name);    
                   response.sendRedirect(INDEXUSUARIO);
                  }
                break;  
                case 9: {
                   
                    // Lista de Personas Registradas
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   
                   response.sendRedirect(LISTARPERSONAADMIN);
                  }
                break; 
                case 10: {
                   
                   // Registrar Persona 
                    
                   us = new UsuarioService();
                   us.registrar_persona(request);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   
                   response.sendRedirect(LISTARPERSONAADMIN);
                  }
                break; 
                    
                case 11: {
                    
                   // Eliminar Datos por siempre de la Persona 
                    
                   us = new UsuarioService();
                   int idpersona = Integer.parseInt(request.getParameter("idPersona"));
                   us.eliminar_persona(idpersona);
                   
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   
                   response.sendRedirect(LISTARPERSONAADMIN);
                  }
                break;
                    
                case 12: {
                    
                    // recuperar Datos de la Persona
                    us = new UsuarioService();
                    Persona to = us.Buscar_Persona_Id(request.getParameter("idPersona"));
                    request.getSession().setAttribute("listaPersonaById", to);
                    us=new UsuarioService();
                    List listarfilial = us.listar_filial_usuario();
                    request.getSession().setAttribute("listar_filial", listarfilial);
                   
                    response.sendRedirect(EDITARPERSONAADMIN);
                  }
                break;
                    
                   case 13: {
                    
                    // Actualizar Datos de la Persona
                   us = new UsuarioService();
                   us.Actualizar_Datos_Personas(request, Integer.parseInt(request.getParameter("idpersona")));
                       System.out.println(" enviando actualizado--- ");
                   
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   
                   response.sendRedirect(LISTARPERSONAADMIN);
                  }
                break;
                
                case 14: {
                    
                    // Autocomplete Persona
                    us=new UsuarioService();
                    String query = (String)request.getParameter("q");
                    request.getSession().setAttribute("List_Autocomplete_Persona", us.Autocomplete_Persona(query));
                   response.sendRedirect(LISTARAUTOCOMPLETE);
                }
                break;
                case 15: {
                    
                    // ir a la pagina autocomplete
                   response.sendRedirect(AUTOCOMPLETEPERSONA);
                  }
                break;        
                
                case 16: {
                    
                    // guardar array de privilegios
                   System.out.println(" llego al controlador ---");
                   
                   us = new UsuarioService();
                   us.registro_privilegio_array(request);
                   us=new UsuarioService();
                   List listarAccesos = us.Lista_Accesos();
                   request.getSession().setAttribute("listar_acceso", listarAccesos);
                   
                   response.sendRedirect(PRIVILEGIOS);
                   
                  }
                break; 
                case 17: {
                   
                    // cargar datos de Privilegios que son lista de accesos
                    
                   us=new UsuarioService();
                   List listarAccesos = us.Lista_Accesos();
                   request.getSession().setAttribute("listar_acceso", listarAccesos);
                   
                   response.sendRedirect(PRIVILEGIOS);
                   
                  }
                break; 
                    
                case 18: {
                   
                    // REgistro para tener una cuenta en el sistema
                   us=new UsuarioService();
                   List listarAccesos = us.Proximo_IDPersona();
                   request.getSession().setAttribute("Proximo_IDPersona", listarAccesos);
                   
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(REGISTROCUENTA);
                   
                  }
                break;     
                    
                 case 19: {
                   
                   // Guardar datos de la Persona de cuenta  
                     
                   us = new UsuarioService();
                   us.Registrar_Datos_Cuenta(request);
                   int PostIdPersona = Integer.parseInt(request.getParameter("idpersona"));
                   System.out.println(" este es el ID ---> "+PostIdPersona);
                   request.getSession().setAttribute("postID", PostIdPersona);   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   
                   //response.sendRedirect("#banana"+REGISTROCUENTACCESO);
                   response.sendRedirect(ADDCUENTA);
                   
                  }
                break;     
                    
                  case 20: {
                   
                   // Guardar datos del Usuario de cuenta  ver si activar o no dependiendo
                   /*us = new UsuarioService();
                   int PostIdPersona = us.extraUltimoIdPersona(String.valueOf(request.getParameter("dni")));                     
                   request.getSession().setAttribute("postID", PostIdPersona); */
                   //   
                      
                   us = new UsuarioService();
                   us.registrar_usuario(request);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   
                   response.sendRedirect(ADDSUCCESSFUL);
                   
                  }
                break;     
                     
                    case 21: {
                   
                   
                   us=new UsuarioService();
                   List listarAccesos = us.Proximo_IDPersona();
                   request.getSession().setAttribute("Proximo_IDPersona", listarAccesos);
                   
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(ADDDATOS);
                   
                  }
                break;     
                case 22: {

                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuserx=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listar_usuario_filial", us.listar_usuario_filial(idfilialuserx));
                   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   
                   response.sendRedirect(LISTARUSUARIOFILIAL);
                }
                break;  
                    
                    
                    case 23: {
                   
                   us=new UsuarioService();
                   List listarAccesos = us.Proximo_IDPersona();
                   request.getSession().setAttribute("Proximo_IDPersona_filial", listarAccesos);
                   
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listar_usuario_filial", us.listar_usuario_filial(idfilialuser));
                   
                   response.sendRedirect(ADDDATOSFILIAL);
                   
                  }
                break;  
               
                  case 24: {
                   
                   // Guardar datos de la Persona de cuenta  Filial
                     
                   us = new UsuarioService();
                   us.Registrar_Datos_Cuenta(request);
                   int PostIdPersona = Integer.parseInt(request.getParameter("idpersona"));
                   System.out.println(" este es el ID ---> "+PostIdPersona);
                   request.getSession().setAttribute("postIDFilial", PostIdPersona);   
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listar_usuario_filial", us.listar_usuario_filial(idfilialuser));
                   us=new UsuarioService();
                   List listarcategoriafilial = us.listar_categoria_usuario_filial();
                   request.getSession().setAttribute("listar_categoria_filial", listarcategoriafilial);
                   
                   //response.sendRedirect("#banana"+REGISTROCUENTACCESO);
                   response.sendRedirect(ADDCUENTAFILIAL);
                   
                  }
                break; 
                      
                case 25: {
                   
                   // Guardar datos del Usuario de cuenta  Filial
                     
                   us = new UsuarioService();
                   us.registrar_usuario(request);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   
                   response.sendRedirect(ADDSUCCESSFUL);
                   
                  }
                break;     
                
                    case 26: {

                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuserx=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listar_usuario_filial", us.listar_usuario_filial(idfilialuserx));
                   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   
                   response.sendRedirect(LISTARCOORDINADORFACULTADFILIAL);
                }
                break;  
                        
                          case 27: {

                   // Ir Al Registro de usuario Filial Periodo           
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuserx=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listar_usuario_filial", us.listar_usuario_filial(idfilialuserx));
                   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   ge=new GestionEstrategicoService();
                   List listarPeriodo = ge.Listar_Periodo();
                   request.getSession().setAttribute("listar_periodo", listarPeriodo);
                   
                   response.sendRedirect(LISTARUSUARIOFILIALPERIODO);
                }
                break;  
                   
                              
                              
                              // -------------- case denegadoo --------------!!!!
                 case 28: {

                   // Ir Al Registro de usuario Filial Periodo   Guardar !!
                   ge = new GestionEstrategicoService();
                 //  ge.registro_coordinadorfacultad_array(request);
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuserx=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listar_usuario_filial", us.listar_usuario_filial(idfilialuserx));
                   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   ge=new GestionEstrategicoService();
                   List listarPeriodo = ge.Listar_Periodo();
                   request.getSession().setAttribute("listar_periodo", listarPeriodo);
                   
                   response.sendRedirect(LISTARUSUARIOFILIALPERIODO);
                }
                break;  
                case 29: {
                   
                   // ACTIVAR USUARIO 
                   us=new UsuarioService();
                   int id= Integer.parseInt(request.getParameter("idUsuario"));
                   us.Activar_Usuario(id);
                   us=new UsuarioService();
                   List listarUsuario = us.listar_usuario();
                   request.getSession().setAttribute("listar_usuario", listarUsuario);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   response.sendRedirect(LISTARUSUARIOADMIN);
               }
               break;       
               case 30: {
                   
                   // DESACTIVAR USUARIO
                   
                   us=new UsuarioService();
                   int id= Integer.parseInt(request.getParameter("idUsuario"));
                   us.Desactivar_Usuario(id);
                   us=new UsuarioService();
                   List listarUsuario = us.listar_usuario();
                   request.getSession().setAttribute("listar_usuario", listarUsuario);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   response.sendRedirect(LISTARUSUARIOADMIN);
               }
               break;       
                case 31: {
                    
                    // CARGAR DATOS PERSONA ADD
                   
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(ADDPERSONA);
                   
                  }
                break;     
                case 32: {
                    
                    // CARGAR DATOS PERSONA ADD
                    
                   us=new UsuarioService();
                   List listarcombopersona = us.listar_persona();
                   request.getSession().setAttribute("listarcombopersona", listarcombopersona);
                   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   
                   us=new UsuarioService();
                   List listaAreaUnidad = us.areasUnidadUpeU();
                   request.getSession().setAttribute("listar_areasUnidad", listaAreaUnidad);
                   
                   
                   response.sendRedirect(ADDUSUARIOPER);
                   
                  }
                break;     
                case 33: {
                    
                    // CARGAR DATOS PERSONA ADITAR
                   
                   us=new UsuarioService();
                   Persona per =us.Buscar_Persona_Id(request.getParameter("idPersona"));
                   request.getSession().setAttribute("listaPersonaById",per);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(ADITPERSONA);
                   
                  }
                break;     
                case 34: {
                    
                    // ACTUALIZAR DATOS DE PERSONA
                    System.out.println("-------------> hasta aqui lego");
                    us=new UsuarioService();
                    us.Actualizar_Datos_Personas(request,Integer.parseInt(request.getParameter("idpersona")));
                    
                    us=new UsuarioService();
                    List listarPersona = us.listar_persona();
                    request.getSession().setAttribute("listar_persona", listarPersona);
                    us=new UsuarioService();
                    List listarfilial = us.listar_filial_usuario();
                    request.getSession().setAttribute("listar_filial", listarfilial);
                   
                    response.sendRedirect(LISTARPERSONAADMIN);
                  }
                break; 
                case 35: {
                    
                    // CARGAR DATOS USUARIO ID
                   us=new UsuarioService();
                   sge.modelo.Usuario tox = us.Buscar_Usuario_Id(request.getParameter("idUsuario"));
                   
                   request.getSession().setAttribute("listaUsuarioById", tox); 
                   us=new UsuarioService();
                   List listarcombopersona = us.listar_persona();
                   request.getSession().setAttribute("listarcombopersona", listarcombopersona);
                   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(EDITUSUARIO);
                   
                  }
                break;     
                case 36: {
                    
                    // ACTUALIZAR DATOS DE USUARIO 
                    System.out.println("-------------> hasta aqui lego");
                    us=new UsuarioService();
                    us.Actualizar_Datos_Usuario(request,Integer.parseInt(request.getParameter("idusuario")));
                   
                   us=new UsuarioService();
                   List listarUsuario = us.listar_usuario();
                   request.getSession().setAttribute("listar_usuario", listarUsuario);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   
                   response.sendRedirect(LISTARUSUARIOADMIN);
                  }
                break; 
                case 37: {
                    
                    // CARGAR DATOS PERSONA ADITAR DESDE USER
                   
                   us=new UsuarioService();
                   Persona per =us.Buscar_Persona_Id(request.getParameter("idPersona"));
                   request.getSession().setAttribute("listaPersonaById",per);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(ADITPERSONAUSER);
                   
                  }
                break;     
                case 38: {
                    
                    // ACTUALIZAR DATOS DE PERSONA
                    System.out.println("-------------> hasta aqui lego");
                    us=new UsuarioService();
                    us.Actualizar_Datos_Personas(request,Integer.parseInt(request.getParameter("idpersona")));
                    us=new UsuarioService();
                   List listarUsuario = us.listar_usuario();
                   request.getSession().setAttribute("listar_usuario", listarUsuario);
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario();
                   request.getSession().setAttribute("listar_categoria", listarcategoria);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   us=new UsuarioService();
                   List listarPersona = us.listar_persona();
                   request.getSession().setAttribute("listar_persona", listarPersona);
                   
                   response.sendRedirect(LISTARUSUARIOADMIN);
                  }
                break; 
                   
                    
               //  ADMINISTRACION POR FILIAL ACCESOS DE USUARIOS Y COORDINADORES DE FACULTAD Y EAP     
                    
                    
                case 39: {
                      
                      // CARGAR PAGINA INDEX DE USUARIO
                   String name = "Usuarios de mi Filial ";
                   String data_name = "Reporte, Registro, Actualizacion de Datos y activacion de cuentas ";
                   
                   request.getSession().setAttribute("name_user",name); 
                   request.getSession().setAttribute("data_user",data_name);    
                   response.sendRedirect(INDEXUSUARIOFILIAL);
                  }
                break;  
                case 40: {

                    // LISTA DE USUARIOS POR FILIAL
                    
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaUserFilial", us.listar_usuario_filial(idfilialuser));
                   us=new UsuarioService();
                   List listarAreas = us.areasUnidadUpeU();
                   request.getSession().setAttribute("listar_AreasUnidadSess", listarAreas);                   
                   
                   us=new UsuarioService();
                   List listarPerspectivas = us.perspectivasUPeU();
                   request.getSession().setAttribute("listar_PerspectivasSess", listarPerspectivas); 
                   
                   us=new UsuarioService();
                   List listarEjeEstrategico = us.ejesUPeU();
                   request.getSession().setAttribute("listar_EjeEstrategicoSess", listarEjeEstrategico);                     
                   response.sendRedirect(LISTARUSUARIOSFILIAL);
                }
                break;
                case 41: {

                    // LISTA DE PERSONAS POR FILIAL
                    
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfilialuser));
                    response.sendRedirect(LISTAPERSONASFILIAL);
                }
                break;
                case 42: {
                    
                    // CARGAR DATOS PERSONA ADD FILIAL
                   
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(ADDPERSONAFILIAL);
                   
                  }
                break;     
                case 43: {
                   
                   // Registrar Persona FILIAL
                    
                   us = new UsuarioService();
                   us.registrar_persona(request);
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfilialuser));
                   response.sendRedirect(LISTAPERSONASFILIAL);
                  }
                break; 
                case 44: {
                    
                    // CARGAR DATOS FORM ADD USUARIO
                    
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listaComboPersonasFilial", us.listar_personas_filial(idfilialuser));
                   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario_filial();
                   request.getSession().setAttribute("listar_categoria_usuario_filial", listarcategoria );
                   
                   response.sendRedirect(ADDUSUARIOFILIAL);
                   
                  }
                break;     
                case 45: {

                   // registrar usuario como superadministrador
                 
                   us = new UsuarioService();
                   us.registrar_usuario(request);
                   
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listaUserFilial", us.listar_usuario_filial(idfilialuser));
                   response.sendRedirect(LISTARUSUARIOSFILIAL);
                
                }
                break;
                case 46: {
                    
                   // Eliminar Datos por siempre de la Persona 
                    
                   us = new UsuarioService();
                   int idpersona = Integer.parseInt(request.getParameter("idPersona"));
                   us.eliminar_persona(idpersona);
                   
                   us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfilialuser));
                    response.sendRedirect(LISTAPERSONASFILIAL);
                  }
                break;
                case 47: {
                    
                    // CARGAR DATOS PERSONA ADITAR FILIAL
                   
                   us=new UsuarioService();
                   Persona per =us.Buscar_Persona_Id(request.getParameter("idPersona"));
                   request.getSession().setAttribute("listaPersonaById",per);
                   response.sendRedirect(ADITPERSONAFILIAL);
                   
                  }
                break;     
                case 48: {
                    
                    // ACTUALIZAR DATOS DE PERSONA FILIAL 
                    us=new UsuarioService();
                    us.Actualizar_Datos_Personas(request,Integer.parseInt(request.getParameter("idpersona")));
                    
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfilialuser));
                    response.sendRedirect(LISTAPERSONASFILIAL);
                  }
                break; 
                case 49: {
                   
                   // DESACTIVAR USUARIO FILIAL
                   
                   us=new UsuarioService();
                   int id= Integer.parseInt(request.getParameter("idUsuario"));
                   us.eliminar_usuario(id);
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listaUserFilial", us.listar_usuario_filial(idfilialuser));
                   response.sendRedirect(LISTARUSUARIOSFILIAL);
               }
               break;       
               case 50: {
                   
                   // CARGAR LISTA DE USUARIOS PARA PRIVILEGIOS
                   
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listaUserFilialpriv", us.listar_usuario_filial_priv(idfilialuser));
                   response.sendRedirect(LISTARUSUARIOSFILIALPRIVILEGIO);
               }
               break;       
               case 51: {
                   
                   // CARGAR INDEX COORDINADOR FACULTAD Y EAP FILIAL
                   
                   String namepre = "Administracion de Coordinadores y Directores de Areas de Apoyo";
                   String datapre = " Personas - Coordinadores y Directores ";
                   
                   request.getSession().setAttribute("name_coord",namepre); 
                   request.getSession().setAttribute("data_coord",datapre); 
                   response.sendRedirect(INDEXCOORDINADORFILIAL);
                   
                  
               }
               break;       
               case 52: {

                    // LISTA DE COORDINADORES FACULTAD POR FILIAL Y PERIODO
                   
                    us=new UsuarioService();
                    String idPeriodo = (String)request.getParameter("idperi");
                    request.getSession().setAttribute("idPeriodoF",idPeriodo);
                    
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    
                    request.getSession().setAttribute("listar_Coordinador_facultad", us.listar_Coordinador_facultad(idPer, idfi));
                    us=new UsuarioService();
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    
                    System.out.println("VERRR:"+idfi);
                    
                    response.sendRedirect(LISTAPOSTCOORDINADORFILIALPERIODO);
                   
                }
                break;
                case 53: {
                   
                   // CARGAR INDEX COORDINADOR FACULTAD Y EAP FILIAL
                   
                   String namepre = "Selecciona un Periodo para ver los Coordinadores y Directores";
                   String datapre = " Personas - coordinadores - Directores ";
                   
                   request.getSession().setAttribute("name_coord_pre",namepre); 
                   request.getSession().setAttribute("data_coord_pre",datapre);
                   os=new OrganizacionService();
                   List periodo = os.Listar_Periodo();
                   request.getSession().setAttribute("listar_periodo",periodo);
                   
                   response.sendRedirect(LISTAPRECOORDINADORFILIALPERIODO);
                   
                  
               }
               break;       
               case 54: {

                    //Guardar o Insert
                    us=new UsuarioService();
                    us.Registrar_Coordinador_Facultad(request);
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_facultad", us.listar_Coordinador_facultad(idPer, idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    
                    response.sendRedirect(LISTAPOSTCOORDINADORFILIALPERIODO);
                }
                break;
                   
                case 55: {
                    
                    //Eliminar idCoordinadorFacultad
                    
                    us=new UsuarioService();
                    int id = Integer.parseInt(request.getParameter("idCoordinadorFacultad"));
                    us.EliminarCoordinadorFacultad(id);
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_facultad", us.listar_Coordinador_facultad(idPer, idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    response.sendRedirect(LISTAPOSTCOORDINADORFILIALPERIODO);

                }
                break;
                    
                case 56: {
                    
                    // CARGAR DATOS COORDINADOR FACULTAD
                    
                    us=new UsuarioService();
                    Coordinadorfacultad to = us.buscarCoordinadorFacultadId(request.getParameter("idCoordinadorFacultad"));
                    request.getSession().setAttribute("listaCoordinadorFacultadById", to);
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfilialuser));
                    os=new OrganizacionService();
                    List periodo = os.Listar_Periodo();
                    request.getSession().setAttribute("listar_periodo",periodo);
                    response.sendRedirect(EDITPOSTCOORDINADORFILIALPERIODO);
                   
                }
                break;
                    
                  case 57: {
                    
                    // LISTAR GET SESSION LIST COORDINADOR FACULTAD
                      
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_facultad", us.listar_Coordinador_facultad(idPer, idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    response.sendRedirect(LISTAPOSTCOORDINADORFILIALPERIODO);
                }
                break;
  
                 case 58: {

                    //Actualizar coordinador Facultad
                     
                   us=new UsuarioService();
                   us.ActualizarCoordinadorfacultad(request, Integer.parseInt(request.getParameter("idCoordinadorFacultad")));
                   us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_facultad", us.listar_Coordinador_facultad(idPer, idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    response.sendRedirect(LISTAPOSTCOORDINADORFILIALPERIODO);

                }
                break;
               
                case 59: {
                   
                   // CARGAR INDEX COORDINADOR EAP FILIAL
                   
                   String namepre = "Selecciona un Periodo para ver los Coordinadores";
                   String datapre = " Personas - coordinadores de E.A.P y Directores de areas de apoyo ";
                   
                   request.getSession().setAttribute("name_coord_pre",namepre); 
                   request.getSession().setAttribute("data_coord_pre",datapre);
                   os=new OrganizacionService();
                   List periodo = os.Listar_Periodo();
                   request.getSession().setAttribute("listar_periodo",periodo);
                   
                   response.sendRedirect(LISTAPRECOORDINADOREAPFILIALPERIODO);
                   
                  
               }
               break;       
               case 60: {

                    // LISTA DE COORDINADORES EAP POR FILIAL Y PERIODO
                   
                    us=new UsuarioService();
                    String idPeriodo = (String)request.getParameter("idperi");
                    request.getSession().setAttribute("idPeriodoF",idPeriodo);
                    
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_eap", us.listar_Coordinador_EAP(idPer, idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    
                    
                    response.sendRedirect(LISTAPOSTCOORDINADOEAPFILIALPERIODO);
                   
                }
                break;
                
                case 61: {

                    //Guardar o Insert
                    us=new UsuarioService();
                    us.Registrar_Coordinador_EAP(request);
                    
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_eap", us.listar_Coordinador_EAP(idPer, idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    
                    response.sendRedirect(LISTAPOSTCOORDINADOEAPFILIALPERIODO);
                }
                break;
                
                case 62: {
                    
                    //Eliminar idCoordinador EAP
                    
                    us=new UsuarioService();
                    int id = Integer.parseInt(request.getParameter("idCoordinadorEap"));
                    us.EliminarCoordinadorEAP(id);
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_eap", us.listar_Coordinador_EAP(idPer, idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    
                    response.sendRedirect(LISTAPOSTCOORDINADOEAPFILIALPERIODO);
                }
                break;
                    
                case 63: {
                    
                    // CARGAR DATOS COORDINADOR EAP
                    
                    us=new UsuarioService();
                    Coordinadoreap to = us.buscarCoordinadorEAPId(request.getParameter("idCoordinadorEap"));
                    request.getSession().setAttribute("listaCoordinadorEAPById", to);
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfilialuser));
                    os=new OrganizacionService();
                    List periodo = os.Listar_Periodo();
                    request.getSession().setAttribute("listar_periodo",periodo);
                    response.sendRedirect(EDITPOSTCOORDINADOREAPFILIALPERIODO);
                   
                }
                break;
                
                case 64: {
                    
                    // LISTA SESSION COORDINADOR EAP PERIODO FILIAL
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idPer=(String)session.getAttribute("idPeriodoF");
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_eap", us.listar_Coordinador_EAP(idPer, idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    
                    response.sendRedirect(LISTAPOSTCOORDINADOEAPFILIALPERIODO);
                }
                break;
                case 65: {

                    // ACTUALIZAR COORDINADOR EAP PERIODO FILIAL
                     
                   us=new UsuarioService();
                   us.ActualizarCoordinadorEAP(request, Integer.parseInt(request.getParameter("idCoordinadorEap")));
                   us=new UsuarioService();
                   HttpSession session = request.getSession(true); 
                   String idPer=(String)session.getAttribute("idPeriodoF");
                   String idfi=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listar_Coordinador_eap", us.listar_Coordinador_EAP(idPer, idfi));
                   request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                   response.sendRedirect(LISTAPOSTCOORDINADOEAPFILIALPERIODO);
                }
                break;
               case 66: {
                   
                   // CARGAR INDEX FILIAL FACULTAD y de EAP
                   
                   String namepre = "Gestion de Facultades y E.A.P";
                   String datapre = " Personas - coordinadores facultad - coordinadores EAP";
                   
                   request.getSession().setAttribute("name_coord",namepre); 
                   request.getSession().setAttribute("data_coord",datapre); 
                   response.sendRedirect(INDEXFILIALFACULTADEAP);
                   
                  
               }
               break;       
               case 67: {

                    // LISTAR FILIAL FACULTAD -- DESDE USUARIO - FILIAL
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                     
                   os=new OrganizacionService();
                   HttpSession session = request.getSession(true); 
                   String idfi=(String)session.getAttribute("IdUsuarioF");
                   
                   System.out.println(" llegooo recuperando id ---->>"+idfi);
                   
                   request.getSession().setAttribute("listaFilialFacultadfilial", os.listar_filial_facultad_desde_usuario(idfi,idTipoAreaPri));
                   response.sendRedirect(LISTAFILIALFACULTADFILIAL);
                }
                break;
               case 68: {
                    
                    // CARGAR DATOS PERSONA ADITAR DESDE USER FILIAL
                   
                   us=new UsuarioService();
                   Persona per =us.Buscar_Persona_Id(request.getParameter("idPersona"));
                   request.getSession().setAttribute("listaPersonaById",per);
                   us=new UsuarioService();
                   List listarfilial = us.listar_filial_usuario();
                   request.getSession().setAttribute("listar_filial", listarfilial);
                   response.sendRedirect(ADITPERSONAUSERFILIAL);
                   
                  }
                break;     
                case 69: {
                    
                    // ACTUALIZAR DATOS DE PERSONA DESDE LIST USER FILIAL
                    
                    us=new UsuarioService();
                    us.Actualizar_Datos_Personas(request,Integer.parseInt(request.getParameter("idpersona")));
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaUserFilial", us.listar_usuario_filial(idfilialuser));
                    response.sendRedirect(LISTARUSUARIOSFILIAL);
                  }
                break;     
                case 70: {
                    
                    // CARGAR DATOS USUARIO ID DESDE USUARIO FILIAL
                   
                    us=new UsuarioService();
                   sge.modelo.Usuario tox = us.Buscar_Usuario_Id(request.getParameter("idUsuario"));
                   request.getSession().setAttribute("listaUsuarioById", tox); 
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   request.getSession().setAttribute("listaComboPersonasFilial", us.listar_personas_filial(idfilialuser));
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario_filial();
                   request.getSession().setAttribute("listar_categoria_usuario_filial", listarcategoria );
                   
                   response.sendRedirect(EDITUSUARIOFILIAL);
                   
                  }
                break;     
                case 71: {
                    
                    // ACTUALIZAR DATOS DE USUARIO FILIAL
                    
                    System.out.println("-------------> hasta aqui lego");
                    us=new UsuarioService();
                    us.Actualizar_Datos_Usuario(request,Integer.parseInt(request.getParameter("idusuario")));
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaUserFilial", us.listar_usuario_filial(idfilialuser));
                    response.sendRedirect(LISTARUSUARIOSFILIAL);
                   
                  }
                break; 
                case 72: {
                    
                    // CREAR CUENTA USUARIO FILIAL
                   
                   us=new UsuarioService();
                   List listarAccesos = us.Proximo_IDPersona();
                   request.getSession().setAttribute("Proximo_IDPersona", listarAccesos);
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   response.sendRedirect(ADDDATOSCUENTAFILIAL);
                   
                  }
                break;     
                case 73: {
                   
                   // Guardar datos de la Persona de cuenta  FILIAL 
                     
                   us = new UsuarioService();
                   us.Registrar_Datos_Cuenta(request);
                   
                   us = new UsuarioService();
                   int PostIdPersona = us.extraUltimoIdPersona(String.valueOf(request.getParameter("dni")));
                   
                   System.out.println(" este es el ID ---> "+PostIdPersona);
                   request.getSession().setAttribute("postID", PostIdPersona);   
                   us=new UsuarioService();
                   List listarcategoria = us.listar_categoria_usuario_filial();
                   request.getSession().setAttribute("listar_categoria_usuario_filial", listarcategoria);
                   HttpSession session = request.getSession(true); 
                   String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                   
                   response.sendRedirect(ADDDATOSCUENTAUSUARIOFILIAL);
                   
                  }
                 break;     
                 case 74: {
                   
                   // Guardar datos del Usuario de cuenta  
                     
                   us = new UsuarioService();
                   us.registrar_usuario(request);
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfilialuser=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaUserFilial", us.listar_usuario_filial(idfilialuser));
                    response.sendRedirect(LISTARUSUARIOSFILIAL);
                   
                   }
                   break;     
                   case 75: {
                   
                   // CARGAR ADD PERIODO PARA LISTAR LOS COORDINADORES DE FACULTAD  --- ELIMINADOOOOOOOOOOOOOO
                   
                   String namepre = "Registro de mis Facultades";
                   String datapre = " Selecciona un Periodo para ver los Coordinadores ";
                   
                   request.getSession().setAttribute("name_coord_pre",namepre); 
                   request.getSession().setAttribute("data_coord_pre",datapre);
                   os=new OrganizacionService();
                   List periodo = os.Listar_Periodo();
                   request.getSession().setAttribute("listar_periodo",periodo);
                   
                   response.sendRedirect(ADDFILIALFACULTADPERIODOFILIAL);
                   
                   }
                   break;       
                      
                   case 76: {

                    // ADD FORM FILIALFACULTAD LISTA DE COORDINADORES FACULTAD POR FILIAL Y PERIODO
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                     
                    us=new UsuarioService();
                    HttpSession session = request.getSession(true); 
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_Coordinador_facultad", us.listar_Coordinador_facultad_ALL(idfi));
                    request.getSession().setAttribute("listaPersonasFilial", us.listar_personas_filial(idfi));
                    os=new OrganizacionService();
                    List facultad = os.Listar_FacultadesAreas(idTipoAreaPri);
                    request.getSession().setAttribute("listar_facultad",facultad);
                   
                    
                    response.sendRedirect(ADDFILIALFACULTADFILIAL);
                   
                    }
                    break;
                    case 77: {
                        
                    // REGISTRAR FILIAL FACULTAD
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                        
                    os=new OrganizacionService();
                    os.Registrar_Filial_Facultad_Filial(request);
                    os=new OrganizacionService();
                    HttpSession session = request.getSession(true); 
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaFilialFacultadfilial", os.listar_filial_facultad_desde_usuarioPrimero(idfi));
                    response.sendRedirect(LISTAFILIALFACULTADFILIAL);
                    
                    }
                    break;  
                
                    case 78: {
                        
                    // ELIMINAR FILIAL FACULTAD
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                        
                    os=new OrganizacionService();
                    int idFilialfacultad = Integer.parseInt(request.getParameter("idFilialfacultad"));
                    os.eliminar_Filialfacultad(idFilialfacultad);
                    os=new OrganizacionService();
                    HttpSession session = request.getSession(true); 
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaFilialFacultadfilial", os.listar_filial_facultad_desde_usuarioPrimero(idfi));
                    response.sendRedirect(LISTAFILIALFACULTADFILIAL);
                    
                    }
                    break;  
                
                    case 79: {

                    // LISTAR EAP FACULTAD -- DESDE USUARIO - FILIAL
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                       
                    os=new OrganizacionService();
                    HttpSession session = request.getSession(true); 
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                   
                    System.out.println(" llegooo recuperando id ---->>"+idfi);
                   
                    request.getSession().setAttribute("listaEAPFacultadfilial", os.Listar_Eap_FacultadPrimero(idfi,idTipoAreaPri));
                    response.sendRedirect(LISTAEAPFACULTADFILIAL);
                    }
                    break;
                   
                    case 80: {
                        
                    // CARGAR DATOS PARA ADD EAP FACULTAD
                    //Inicio Sesion
                    principalValorSession(request);
                    //Fin Sesion
                        
                    os=new OrganizacionService();
                    HttpSession session = request.getSession(true); 
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaFilialFacultadfilial", os.listar_filial_facultad_desde_usuarioPrimero(idfi));
                    us=new UsuarioService();
                    request.getSession().setAttribute("listacoordinadoreapall", us.listar_Coordinador_EAP_ALL(idfi));
                   
                    os=new OrganizacionService();
                    List eap = os.Listar_Eap(idTipoAreaPri);
                    request.getSession().setAttribute("listar_eap",eap);
                   
                    
                    
                    response.sendRedirect(ADDEAPFACULTADFILIAL);
                    
                    }
                    break;  
                    
                    case 81: {

                    // REGISTRAR EAP FACULTAD EAP FACULTAD -- DESDE USUARIO - FILIAL
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                       
                    os=new OrganizacionService();
                    os.Registrar_EAP_Facultad_Filial(request);
                    
                    HttpSession session = request.getSession(true); 
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaEAPFacultadfilial", os.Listar_Eap_Facultad(idfi));
                    response.sendRedirect(LISTAEAPFACULTADFILIAL);
                    }
                    break;
                      
                    case 82: {
                        
                    // ELIMINAR EAP FACULTAD
                    //Inicio Session
                    principalValorSession(request);
                    //Fin Session                          
                    os=new OrganizacionService();
                    int id = Integer.parseInt(request.getParameter("idEapFacultad"));
                    os.eliminar_EAP_Facultad_Filial______delete(id);
                        System.out.println("--------------->  legoooo"  +id);
                    os=new OrganizacionService();
                    HttpSession session = request.getSession(true); 
                    String idfi=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listaEAPFacultadfilial", os.Listar_Eap_Facultad(idfi));
                    response.sendRedirect(LISTAEAPFACULTADFILIAL);
                    
                    }
                    break;  
                    case 83:{
                   
                   // GET LIST EAP FACULTAD ----------
                    os=new OrganizacionService();
                    String query = (String)request.getParameter("idfilialfacultad");
                    request.getSession().setAttribute("idfilialfacultadsession",query);
                    System.out.println(" id filial  -- > --> --> --> "+request.getSession().getAttribute("IDFilialFsession"));
                    int idFilial=(Integer)request.getSession().getAttribute("IDFilialFsession");
                    
                    System.out.println(" id filial  xx -- > --> --> --> "+idFilial);
                    
                    HttpSession session = request.getSession(true); 
                    String idfilialfacultad=(String)session.getAttribute("idfilialfacultadsession");
                    String idfilial=(String)session.getAttribute("IdUsuarioF");
                    request.getSession().setAttribute("listar_eap_facultad", os.Listar_Eap_Facultad_Perfil(idfilial, idfilialfacultad));
                    response.sendRedirect(LISTEAPFACULTADFILIAL);
                    
                    }break;
                    case 84: {
                   
                   // LISTAR FILIAL FACULTAD SEGUN LA FILIAL SELECCIONADA
                   
                    os=new OrganizacionService();
                    String query = (String)request.getParameter("getIDFilialx");
                    request.getSession().setAttribute("IDFilialFsession",query);
                    System.out.println(" ----> o o o o o  o "+query);
                    HttpSession session = request.getSession(true); 
                    String idFilialfacultad=(String)session.getAttribute("IDFilialFsession");
                    request.getSession().setAttribute("listar_filial_facultad", os.Listar_FililFacultad(idFilialfacultad));
                    response.sendRedirect(LISTFILIALFACULTAD);
                  
                    }
                    break;                
                   case 85: {                    
                    // ELIMINAR EAP FACULTAD                        
                    os=new OrganizacionService();
                    int id = Integer.parseInt(request.getParameter("idEapFacultad"));
                    Filial fi=(Filial)request.getSession().getAttribute("filialUsuario");
                    us=new UsuarioService();
                    request.getSession().setAttribute("listaCoordinadorEjeEapSessX", us.listarCoordinadoresEjeEaps(fi.getIdfilial(), id));
                    us=new UsuarioService();                    
                    request.getSession().setAttribute("coordinadoresEjeFilialSess", us.coordinadorEje(fi.getIdfilial()));
                    response.sendRedirect(ADDCOORDINADOREJEFILIAL+"?idEapFacultad="+id);
                    }
                    break;                         
                   case 86: {                    
                    // ELIMINAR EAP FACULTAD                        
                    os=new OrganizacionService();
                    int id = Integer.parseInt(request.getParameter("idEapFacultad"));
                    int idcoor = Integer.parseInt(request.getParameter("idCoordinadorEje"));
                    CoordinadorEjeeap toc=new CoordinadorEjeeap();
                    toc.setIdEapFacultad(id);
                    toc.setIdcoordinadoreje(idcoor);
                    us=new UsuarioService();                    
                    us.registrarEapCoordinadorEje(toc);
                    
                    
                    Filial fi=(Filial)request.getSession().getAttribute("filialUsuario");
                    
                    us=new UsuarioService();
                    request.getSession().setAttribute("listaCoordinadorEjeEapSessX", us.listarCoordinadoresEjeEaps(fi.getIdfilial(), id));
                    
                    us=new UsuarioService();
                    request.getSession().setAttribute("coordinadoresEjeFilialSess", us.coordinadorEje(fi.getIdfilial()));
                    response.sendRedirect(ADDCOORDINADOREJEFILIAL+"?idEapFacultad="+id);
                    }
                    break;
                       
                   case 90: {                   
                    // ELIMINAR EAP FACULTAD
                    us=new UsuarioService();
                    if(us.compararClaveAnterior(Integer.parseInt(request.getParameter("idusuario")), request.getParameter("clave"))==1){
                        us=new UsuarioService();
                        us.cambiarClaveUsuario(Integer.parseInt(request.getParameter("idusuario")), request.getParameter("newclave"));
                        response.sendRedirect(request.getContextPath()+"/Change");
                    }else{
                        String smg="La clave anterior no es correcta!!";
                        response.sendRedirect(CHANGEPASSWORD+"?msg="+smg);
                    }
                                                          
                    
                    }
                    break;                         
                   
                    
                     
                   
                    
                 
                
                default: 
                {
                    System.out.println("No hay valores....");
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
