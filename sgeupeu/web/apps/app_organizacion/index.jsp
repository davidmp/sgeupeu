<%-- 
    Document   : index
    Created on : 25/08/2013, 01:36:24 PM
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
        String org = (String)session.getAttribute("name_org");
        String data = (String)session.getAttribute("data_org");
        %>
        
        <blockquote>
            <p class="text-success">
            <%=org%>
            </p> 
            <small><%=data%></small>
        </blockquote>
         
            <ul class="nav nav-tabs ">
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Organizacion?opt=1" title="Datos de la Organizacion" class="informacion"><div class="icon-home"></div>&nbsp;Institucion</a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Organizacion?opt=5" title="Lista de Filiales" class="informacion"><div class="icon-th-large"></div>&nbsp;Filiales</a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Organizacion?opt=6" title="Lista de Facultades" class="informacion"><div class="icon-flag"></div>&nbsp;Facultades/UPG/Unidad Apoyo</a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Organizacion?opt=10" title="Lista de EAP" class="informacion"><div class=" icon-flag"></div>&nbsp;E.A.P/EPG/&Aacute;reas</a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Organizacion?opt=11" title="Lista de Periodos o Etapas de Trabajo" class="informacion"><div class="icon-calendar"></div>&nbsp;Periodo.</a></li>
</ul>
 
<div class="tab-content">
  
</div>
            
    </body>
</html>
