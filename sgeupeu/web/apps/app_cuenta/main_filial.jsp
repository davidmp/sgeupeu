<%-- 
    Document   : main_filial
    Created on : 01/08/2013, 10:04:52 AM
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
                Administracion de Cuentas Usuario de Filial!! </p> <small>Usted al aperturar una cuenta proporcionara la identificacion de Personas y el Perfil de Usuario</small>
	
            <hr class="divider">
            <ul id="MainTabs" class="nav nav-tabs">
                <li title="Reporte de Usuarios Registrados segun mi Filial" class="informacion"><a data-target="#usuariofilial" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=22"><div class=" icon-arrow-right"></div>&nbsp;Lista de mis Usuarios</a></li>
                <li title="Crear una nueva Cuenta para un Usuario" class="informacion"><a data-target="#addusuariofilial" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=23"><div class=" icon-arrow-right"></div>&nbsp;Registrar Nuevo</a></li>
                
                <li title="Matricula a tus Usuarios a un Periodo" class="informacion"><a data-target="#coordinadorperiodo" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=27"><div class=" icon-arrow-right"></div>&nbsp;Coordinador Periodo</a></li>
                <li title="Al Registrar un nuevo coordinador Facultad debes de asignarle un periodo segun la central" class="informacion"><a data-target="#addcoordinador" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=23"><div class=" icon-arrow-right"></div>&nbsp;Registrar Coordinador</a></li>
                <li title="Reporte de mis Coordinadores de Facultades con Acceso al Sistema" class="informacion"><a data-target="#coordinadorfacultad" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=23"><div class=" icon-arrow-right"></div>&nbsp;Coordinador Facultad</a></li>
</ul>
 
                <div class="tab-content">
  <div class="tab-pane" id="usuariofilial">Loading...</div>
  <div class="tab-pane" id="addusuariofilial">Loading...</div>
  
  <div class="tab-pane" id="coordinadorperiodo">Loading...</div>
  <div class="tab-pane" id="addcoordinador">Loading...</div>
  <div class="tab-pane" id="coordinadorfacultad">Loading...</div>
  
</div>
       </blockquote>
    </body>
</html>