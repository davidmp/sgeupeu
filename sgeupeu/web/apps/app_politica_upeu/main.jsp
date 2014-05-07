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
                Datos de Politica UPeU !! </p> <small>puedes editar y reportar este documento</small>
	
            <hr class="divider">
            <ul id="MainTabs" class="nav nav-tabs">
                <li title="Reporte de la Politica UPeU" class="informacion"><a data-target="#datosupeulista" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=2"><div class=" icon-arrow-right"></div>&nbsp;Lista de Politica UPeU</a></li>
  <li title="Necesitas editar la Politica" class="informacion"><a data-target="#banana" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=3"><div class=" icon-arrow-right"></div>&nbsp;Reservar</a></li>
</ul>
 
                <div class="tab-content">
  <div class="tab-pane" id="datosupeulista">Loading...</div>
  <div class="tab-pane" id="banana">Loading...</div>
  
</div>
       </blockquote>
    </body>
</html>
