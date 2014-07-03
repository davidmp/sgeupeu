

<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Facultad"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Usuario"%>
<%
String user = (String)session.getAttribute("SessionCounter");
if(user!=null){
    
Usuario w = null;
w = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");

  
System.out.println("idUsuario            INDEX  ---> "+w.getIdusuario());

Eap eU=(Eap)request.getSession().getAttribute("eapUsuario");
Facultad faU=(Facultad)request.getSession().getAttribute("facultadUsuario");
Filial fiU=(Filial)request.getSession().getAttribute("filialUsuario");

//
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>welcome.sge.upe</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/docs.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/table.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/table.sortable.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-fileupload.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-fileupload.css" rel="stylesheet">

    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/ico/favicon.png">
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.js"></script>
    <link href="<%=request.getContextPath()%>/resources/css/estado-indicador.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-popover.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-popover.css" rel="stylesheet">
 
 
    <style>
     .edwinc{
            background-image: url('flowers-right.png');
            background-position: right;
            background-repeat: no-repeat;
     }
    </style>
                                   
  
<script type='text/javascript'>
$(document).ready(function() {
    $(".dropdown-menu li a").click(function() {
    $(".well-small").empty().append();
    $(".dropdown-menu li a").removeClass("current");
    $(".well-small").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
    $(this).addClass("current");
    $.ajax({ url: this.href, success: function(html) {
    $(".well-small").empty().append(html);
    }
    });
    return false;
    });
});
</script>
<script type="text/javascript">
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-146052-10']);
      _gaq.push(['_trackPageview']);
      (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' === document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
      })();
 
    </script>
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
        
      <div class="navbar-inner">
          
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="">SGE - <%=w.getIdfilial_nombre()%> </a>
          <div class="nav-collapse collapse">
            <ul class="nav" >
                 
              <%
              int opt = Integer.parseInt(w.getIdcategoriausuario());
              if(opt==1){%>
                
              <!-- Menu del administrador Central con todos los privilegios del sistema -->
                <li class="dropdown">
                  <a href="#" data-toggle="dropdown" class="dropdown-toggle"><div class="icon-user icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                  <ul  class="dropdown-menu">
                      <li><a href="<%=request.getContextPath()%>/Organizacion?opt=4"><div class="icon-chevron-right"></div>&nbsp;Admin - Organización</a></li>
                      <li><a href="<%=request.getContextPath()%>/Usuario?opt=8"><div class="icon-chevron-right"></div>&nbsp;Admin - Usuarios</a></li>
                      <li><a href="<%=request.getContextPath()%>/GestionEstrategico?opt=17"><div class="icon-chevron-right"></div>&nbsp;Admin - Temporadas</a></li>
                      <li><a href="<%=request.getContextPath()%>/GestionEstrategico?opt=14"><div class="icon-chevron-right"></div>&nbsp;Admin - Gestión Estratégica (Acad&eacute;mica)</a></li>
                      <li><a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=14"><div class="icon-chevron-right"></div>&nbsp;Admin - Gestión Estratégica (Direcciones de Apoyo)</a></li>
                      <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/InformeActividadGeneral?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte Actividades</a></li>
                      <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/EvaluacionGeneral?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte de Evaluaci&oacute;n</a></li>
                      
                  </ul>
                </li>
                
                 <%}else if(opt==2){%>
                 
                 <!-- Menu del administrador Filial con acceso a sus facultades y escuelas academicas -->
                <li class="dropdown">
                  <a href="#" data-toggle="dropdown" class="dropdown-toggle"><div class="icon-user icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                  <ul  class="dropdown-menu">
                   <li><a href="<%=request.getContextPath()%>/Usuario?opt=39"><div class="icon-chevron-right"></div>Mis Usuarios</a></li>
                   <li><a href="<%=request.getContextPath()%>/Usuario?opt=51"><div class="icon-chevron-right"></div>Mis Coordinadores/Directores</a></li>
                   <li><a href="<%=request.getContextPath()%>/Usuario?opt=66"><div class="icon-chevron-right"></div>Mis Facultades/E.A.P/Coord. Ejes</a></li>
                   <li><a href="<%=request.getContextPath()%>/UsuarioApoyo?opt=66"><div class="icon-chevron-right"></div> Mis Unidades de Apoyo/&Aacute;reas de Apoyo</a></li>
                   <li><a href="<%=request.getContextPath()%>/GestionEstrategico?opt=14"><div class="icon-chevron-right"></div>&nbsp;Gestión Estratégica (Area Acad&eacute;mica)</a></li>
                   <li><a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=14"><div class="icon-chevron-right"></div>&nbsp;Gestión Estratégica (Areas de Apoyo)</a></li>                   
                   <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/InformeActividadGeneral?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte Actividades</a></li>
                   <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/EvaluacionGeneral?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte de Evaluaci&oacute;n</a></li>
                  </ul>
                </li> 
                
                 <%}else if(opt==3){%>
                 
                 <!-- Menu del administrador de Facultad -->
                 <li class="dropdown">
                  <a href="#" data-toggle="dropdown" class="dropdown-toggle"><div class="icon-user icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                  <ul  class="dropdown-menu">                   
                   <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/InformeActividadGeneral?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte Actividades</a></li>
                   <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/EvaluacionGeneral?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte de Evaluaci&oacute;n</a></li>
                  </ul>
                </li>
                   <% } else if(opt==4){ %>
                   
                   <!-- Menu del administrador de EAP con acceso a sus indicadores -->
                   <li class="dropdown">
                  <a href="#" data-toggle="dropdown" class="dropdown-toggle"><div class="icon-user icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                  <ul  class="dropdown-menu">
                   <li><a data-target=".marketing" data-toggle="tab" href="../inicio_coordinador_eap/index.jsp"><div class="icon-home"></div>&nbsp;Inicio</a></li>
                   <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/Indicador?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Indicadores</a></li>
                   <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/InformesActividad?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte Actividades</a></li>
<!--                   <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/Indicador?opt=14&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reportes Consolidados</a></li>-->

                  </ul>
                </li>
                <%}else if(opt==5){
                %>

                   <!-- Menu de Coordinadores de Ejes de EAP con acceso a sus indicadores -->
                   <li class="dropdown">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle"><div class="icon-user icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                  <ul  class="dropdown-menu">
                    <li><a data-target=".marketing" data-toggle="tab" href="../app_coordinadores_ajes/index.jsp"><div class="icon-home"></div>&nbsp;Inicio</a></li>
                    <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/IndicadoresEje?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Indicadores</a></li>
                    <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/InformeActividadEje?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte Actividades</a></li>                    
<!--                    <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/IndicadoresEje?opt=14&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reportes Consolidados</a></li>-->

                  </ul>
                </li>                
                
                
                  <%  } else if(opt==6){ %>
                        <!-- Menu de Coordinadores de Ejes de Areas de Apoyo con acceso a sus indicadores -->
                        <li class="dropdown">
                         <a href="#" data-toggle="dropdown" class="dropdown-toggle"><div class="icon-user icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                        <ul  class="dropdown-menu">
                         <li><a data-target=".marketing" data-toggle="tab" href="../app_coordinadores_apoyo/index.jsp"><div class="icon-home"></div>&nbsp;Inicio</a></li>
                         <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/IndicadorApoyo?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Indicadores</a></li>
                         <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/InformesActividadApoyo?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte Actividades </a></li>
                         <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/InformeActividadAuditEapAreas?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte Actividades de EAP/Áreas </a></li>
                         
                         
<!--                         <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/IndicadorApoyo?opt=14&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reportes Consolidados</a></li>-->
                        </ul>
                        </li>                    
                  
                  <%}else  if(opt==7){ %>
                  
                <li class="dropdown">
                  <a href="#" data-toggle="dropdown" class="dropdown-toggle"><div class="icon-user icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                  <ul  class="dropdown-menu">                      
                      <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/InformeActividadGeneral?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte Actividades</a></li>
                      <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/EvaluacionGeneral?opt=1&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon icon-minus"></div>&nbsp;Reporte de Evaluaci&oacute;n</a></li>
                      
                  </ul>
                </li>                  
                  <% } else
                    {response.sendRedirect(request.getContextPath()+"/apps/app_errors/http_500.jsp"); }%>
                
                
            </ul>
               <ul class="nav pull-right">
                  <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><div class="icon-user icon-white"></div>&nbsp;<%=w.getUsuario()%> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/perfil.jsp"><div class="icon  icon-user"></div>&nbsp;Mi Perfil</a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/cambiar_clave.jsp"><div class="icon  icon-user"></div>&nbsp;Cambiar Clave</a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_web/logout.jsp"><div class="icon   icon-off"></div>&nbsp;Salir</a></li>
                </ul>
              </li>
              </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
           
    <div class="container">
        <div class="well-small">
       
            <%
            int perfil_counter = Integer.parseInt(w.getIdcategoriausuario());
            if(perfil_counter==1){%>
            <%@include file="__perfil_admin.jsp"%>
            <%}else if(perfil_counter==2){%>
            <%@include file="__perfil_filial.jsp"%>
            <%}else if(perfil_counter==3){%>
            <%@include file="__perfil_facultad.jsp"%>
            <%}else if(perfil_counter==4 || perfil_counter==5 || perfil_counter==6 || perfil_counter==7){%>
            <%@include file="../inicio_coordinador_eap/index.jsp"%>
            <%}else{ out.println(" esta en construccion");}%>
        </div>
    </div> <!-- /container -->

    
    
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-transition.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-alert.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-modal.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-dropdown.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-scrollspy.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-tab.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-tooltip.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-popover.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-button.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-collapse.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-carousel.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-typeahead.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-affix.jssss"></script>
    <script src="<%=request.getContextPath()%>/resources/js/holder/holder.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/google-code-prettify/prettify.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/application.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-fileupload.js"></script>
       <script src="<%=request.getContextPath()%>/resources/js/bootstrap-fileupload.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/holder/holder.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/google-code-prettify/prettify.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/footable.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/footable.sortable.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/footable.filter.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/footable.paginate.js" type="text/javascript"></script>
    
    <script src="<%=request.getContextPath()%>/resources/js/x/jquery.form.js" type="text/javascript"></script>
    
<%} else{ response.sendRedirect(request.getContextPath()+"/index.jsp");} %>
