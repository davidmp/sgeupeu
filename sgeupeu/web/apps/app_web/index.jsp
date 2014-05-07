<%-- 
    Document   : index
    Created on : 28/07/2013, 01:07:07 PM
    Author     : Edwin
--%>
 <%@page import="sge.modelo.Usuario"%>
<%
String user = (String)session.getAttribute("SessionCounter");
if(user!=null){
    
Usuario w = null;
w = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
    
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
    <link href="<%=request.getContextPath()%>/resources/css/table.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/table.sortable.css" rel="stylesheet">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/ico/favicon.png">
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.js"></script>
 
    
    <script type="text/javascript">
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-146052-10']);
      _gaq.push(['_trackPageview']);
      (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' === document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
      })();
  
  $('.informacion').tooltip();
  $(".alert").alert();
  
  
$(document).ready(function() {
    $(".dropdown-menu li a").click(function() {
    $(".well-small").empty().append();
    $(".dropdown-menu li a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $(".well-small").empty().append(html);
    }
    });
    return false;
    });
});

    </script>
    <style>
        .marketing{margin: 5% auto;}
    </style>
  </head>
  <body data-spy="scroll" data-target=".bs-docs-sidebar">
      

    <!-- Navbar
    ================================================== -->
    <div class="navbar navbar-inverse navbar-fixed-top" style="border-bottom: 8px solid #eee;">
      <div class="navbar-inner">
          <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">SGE - <%=w.getIdfilial_nombre()%> </a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active">
                  <a href="" class="informacion" title="Inicio del Sistema"><div class="icon-home icon-white"></div>&nbsp;Inicio</a>
              </li>
              <%
              int opt = Integer.parseInt(w.getIdcategoriausuario());
              if(opt==1){%>
              <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><div class="icon icon-cog icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="<%=request.getContextPath()%>/apps/app_usuario/index_admin.jsp"><div class="icon icon-minus"></div>&nbsp;Crear Cuentas </a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Institucion</li>
                  <li><a href="#"><div class="icon icon-minus"></div>&nbsp;Datos Insitucion</a></li>
                  <li><a href="#"><div class="icon icon-minus"></div>&nbsp;Crear Filiales</a></li>
                  <li><a href="#"><div class="icon icon-minus"></div>&nbsp;Crear Facultades</a></li>
                  <li><a href="#"><div class="icon icon-minus"></div>&nbsp;Crear E.A.P</a></li>
                  <li class="nav-header">Gestión Estratégico</li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=1"><div class="icon icon-minus"></div>&nbsp;Politica UPeU</a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=5"><div class="icon icon-minus"></div>&nbsp;Ejes y Objetivos Estrategicos</a></li>
                  <li><a href="#"><div class="icon icon-minus"></div>&nbsp;Eje Objetivo</a></li>
                  <li><a href="#"><div class="icon icon-minus"></div>&nbsp;Ejes Estrategias</a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_politica_upeu/list.jsp"><div class="icon icon-minus"></div>&nbsp;Estrategia Linea de Accion</a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/list.jsp"><div class="icon icon-minus"></div>&nbsp;Linea de Accion Indicador</a></li>
                </ul>
              </li>
              <%}else if(opt==2){%>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><div class="icon icon-cog icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/main_filial.jsp"><div class="icon icon-minus"></div>&nbsp;Mis Usuarios </a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_coordinador_facultad/main.jsp"><div class="icon icon-minus"></div>&nbsp;Coordinadores de Facultad </a></li>
                  <li class="divider"></li>
                  </ul>
              </li>
              <%}else if(opt==3){%>
              
             <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><div class="icon icon-cog icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/main_filial.jsp"><div class="icon icon-minus"></div>&nbsp;Mis Usuarios </a></li>
                  <li><a href="#"><div class="icon icon-minus"></div>&nbsp;Coordinadores de EAP</a></li>
                  <li><a href="#"><div class="icon icon-minus"></div>&nbsp;Reportes</a></li>
                  <li class="divider"></li>
                  </ul>
              </li>
              
              <%}else if(opt==4){%>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><div class="icon icon-cog icon-white"></div>&nbsp;<%=w.getIdcategoriausuario_nombre()%>&nbsp;<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li class="nav-header">Gestión Estratégico</li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_politica_upeu/main.jsp"><div class="icon icon-minus"></div>&nbsp;Asignar mis Metas </a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_politica_upeu/main.jsp"><div class="icon icon-minus"></div>&nbsp;Ver mis Indicadores </a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_politica_upeu/main.jsp"><div class="icon icon-minus"></div>&nbsp;Ver Reportes </a></li>
                </ul>
              </li>
              
              <%}else{out.println("Elige un Perfil de Usuario ?");}%>
            </ul>
              <ul class="nav pull-right">
                  <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><div class="icon-user icon-white"></div>&nbsp;<%=w.getUsuario()%> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/perfil.jsp"><div class="icon  icon-user"></div>&nbsp;Mi Perfil</a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/cuenta.jsp"><div class="icon  icon-file"></div>&nbsp;Mi Cuenta</a></li> 
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/mensajes.jsp"><div class="icon  icon-envelope"></div>&nbsp;Mensajes</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Nav header</li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_cuenta/configuracion.jsp"><div class="icon icon-wrench"></div>&nbsp;Configuracion</a></li>
                  <li><a data-target=".marketing" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_web/logout.jsp"><div class="icon   icon-off"></div>&nbsp;Salir</a></li>
                </ul>
              </li>
              </ul>
          </div>
        </div>
      </div>
    </div>

 <div class="container">

        <div class="well-small">
       
            cargando ..
        </div>
    </div>

    <!-- Footer
    ================================================== -->

    <footer class="footer-links">
        <blockquote>
         
        </blockquote>
    </footer>



    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
    <!--<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>-->
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
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-affix.js"></script>

    <script src="<%=request.getContextPath()%>/resources/js/holder/holder.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/google-code-prettify/prettify.js"></script>

    <script src="<%=request.getContextPath()%>/resources/js/application.js"></script>

    <script src="<%=request.getContextPath()%>/resources/js/footable.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/footable.sortable.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/footable.filter.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/footable.paginate.js" type="text/javascript"></script>

    <!-- Analytics
    ================================================== -->
    <script>
      var _gauges = _gauges || [];
      (function() {
        var t   = document.createElement('script');
        t.type  = 'text/javascript';
        t.async = true;
        t.id    = 'gauges-tracker';
        t.setAttribute('data-site-id', '4f0dc9fef5a1f55508000013');
        t.src = '//secure.gaug.es/track.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(t, s);
      })();
    </script>

  </body>
</html>
<%} else{%>
<%@include file="../app_errors/http_401.jsp"%>
<%}%>