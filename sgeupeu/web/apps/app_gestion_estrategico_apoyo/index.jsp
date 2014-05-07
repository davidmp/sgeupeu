<%-- 
    Document   : index
    Created on : 30/08/2013, 12:13:34 AM
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
        <%
        String org = (String)session.getAttribute("name_ge");
        String data = (String)session.getAttribute("data_ge");
        %>
        
        <blockquote>
            <p class="text-info">
            <%=org%>
            </p> 
            <small><%=data%></small>
        </blockquote>
           
            <ul class="nav nav-tabs ">
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=1" title="Mision y Vision de la Universidad" class="informacion"><div class="icon-leaf"></div>&nbsp;<strong class="text-success">Mision y Vision</strong></a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=6" title="Lista de Ejes y Objetivos" class="informacion"><div class="icon-flag"></div>&nbsp;<strong class="text-info">Ejes y Obj. Estrategicos</strong></a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=41" title="Lista de Estrategias" class="informacion"><div class="icon-lock"></div>&nbsp;<strong class="text-warning">Estrategias</strong></a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=46" title="Lista de Indicadores" class="informacion"><div class="icon-folder-open"></div>&nbsp;<strong class="text-success">Indicadores</strong></a></li>
                
</ul>
 
<div class="tab-content">
  
</div>
            
     
    </body>
</html>
