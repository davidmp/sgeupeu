<%-- 
    Document   : home
    Created on : 30/09/2013, 03:52:17 PM
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
  $(".nav-tabs").tab();
  $(".nav-tabs").bind("show", function(e) {    
    var contentID  = $(e.target).attr("data-target");
    var contentURL = $(e.target).attr("href");
    if (typeof(contentURL) !== 'undefined')
      $(contentID).load(contentURL, function(){ $(".nav-tabs").tab(); });
    else
      $(contentID).tab('show');
  });
  $('.nav-tabs a:first').tab("show");
});
       </script>
       
    </head>
    <body>
        <div class="tabbable tabs-below">
            <ul class="nav nav-tabs">
                <li><a data-target=".tab-content" data-toggle="tab" href="perfil.jsp" title="Reporte de Usuarios Registrados en el Sistema como Administrador Central" class="informacion"><div class=" icon-th-list"></div>&nbsp;Panel de Control</a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=9" title="Registrar Nuevo Usuario con una Cuenta en el Sistema" class="informacion"><div class="icon-plus-sign"></div>&nbsp;Reportes</a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/apps/app_usuario/list_print.jsp" title="Imprimir un Reporte General de Todos mis Usuarios" class="informacion"><div class="icon-print"></div>&nbsp;Mas</a></li>
</ul>
 
<div class="tab-content">
  
</div>
        </div>

            
    </body>
</html>