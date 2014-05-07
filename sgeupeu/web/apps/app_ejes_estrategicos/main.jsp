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
                Ejes Estrategicos !! </p> <small>Usted al aperturar una cuenta proporcionara la identificacion de Personas y el Perfil de Usuario</small>
	
            <hr class="divider">
            <ul id="MainTabs" class="nav nav-tabs">
                <li title="Apertura de una Temporada de Ejes Estrategicos " class="informacion"><a data-target="#temporada" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=7"><div class=" icon-arrow-right"></div>&nbsp;Temporadas</a></li>
                <li title="Ejes Estrategicos usted podra editar y eliminar" class="informacion"><a data-target="#registro" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=6"><div class=" icon-arrow-right"></div>&nbsp;Eje Estrategico</a></li>
                <li title="Objetivo Especifico" class="informacion"><a data-target="#banana" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=8"><div class=" icon-arrow-right"></div>&nbsp;Objetivo Especifico</a></li>
            </ul>
 
                <div class="tab-content">
  
  <div class="tab-pane" id="temporada">Loading...</div>
  <div class="tab-pane" id="registro">Loading...</div>
  <div class="tab-pane" id="banana">Loading...</div>
  
</div>
       </blockquote>
    </body>
</html>
