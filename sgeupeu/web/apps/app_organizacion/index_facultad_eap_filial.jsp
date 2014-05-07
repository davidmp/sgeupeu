<%-- 
    Document   : index_facultad_eap_filial
    Created on : 01/10/2013, 12:46:30 PM
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
        String org = (String)session.getAttribute("name_coord");
        String data = (String)session.getAttribute("data_coord");
        %>
        
        <blockquote>
            <p class="text-success">
            <%=org%>
            </p> 
            <small><%=data%></small>
        </blockquote> 
            <ul class="nav nav-tabs ">
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=67" title="Lista de mis Facultades" class="" ><div class="icon-chevron-right"></div>&nbsp;Mis Facultades</a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/Usuario?opt=79" title="Lista de mis E.A.P" class=""><div class="icon-chevron-right"></div>&nbsp;Mis E.A.P</a></li>
            </ul>
 
<div class="tab-content">
  
</div>
            
    </body>
</html>
