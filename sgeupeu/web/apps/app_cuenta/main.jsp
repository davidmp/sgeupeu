<%-- 
    Document   : list
    Created on : 29/07/2013, 05:52:04 PM
    Author     : Edwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <script>
     $(function() {
  $("#MainTabs").tab();
  $("#MainTabs").bind("show", function(e) {    
    var contentID  = $(e.target).attr("data-target");
    var contentURL = $(e.target).attr("href");
    if (typeof(contentURL) !== 'undefined')
      $(contentID).load(contentURL, function(){ $("#MainTabs").tab(); });
    else
      $(contentID).tab('show');
  });
  $('#MainTabs a:first').tab("show");
});
$('.informacion').tooltip();
$(".alert").alert();
        </script>
    </head>
    <body>
 <blockquote>
            <p class="text-warning">
                Administracion de Cuentas Usuario!! </p> <small>Usted al aperturar una cuenta proporcionara la identificacion de Personas y el Perfil de Usuario</small>
	
            <hr class="divider">
            <ul id="MainTabs" class="nav nav-tabs">
                <li title="Reporte de Usuarios Registrados" class="informacion"><a data-target="#registro" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=1"><div class=" icon-arrow-right"></div>&nbsp;Reporte</a></li>
  <li title="Crear una nueva Cuenta para un Usuario" class="informacion"><a data-target="#banana" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=21"><div class=" icon-arrow-right"></div>&nbsp;Registrar</a></li>
</ul>
 
                <div class="tab-content">
  <div class="tab-pane" id="registro">Loading...</div>
  <div class="tab-pane" id="banana">Loading...</div>
  
</div>
       </blockquote>
    </body>
</html>
