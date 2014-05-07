<%-- 
    Document   : index_filial
    Created on : 19/08/2013, 07:39:51 PM
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
        String org = (String)session.getAttribute("name_user");
        String data = (String)session.getAttribute("data_user");
        %>
        
        <blockquote>
            <p class="text-success">
            <%=org%>
            </p> 
            <small><%=data%></small>
        </blockquote> 
            <ul class="nav nav-tabs ">
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=40" title="Reporte de Usuarios Registrados en mi Filial" class="" ><div class="icon-user"></div>&nbsp;Usuarios</a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=41" title="Reporte de Personas Registradas en mi Filial" class=""><div class="icon-file"></div>&nbsp;Personas</a></li>
            </ul>
 
<div class="tab-content">
  
</div>
            
    </body>
</html>
