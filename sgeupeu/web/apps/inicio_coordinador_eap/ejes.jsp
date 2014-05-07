<%-- 
    Document   : ejes
    Created on : 13/09/2013, 01:49:17 AM
    Author     : oscdmdz
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <%
    
    List<Ejeestrategico> ejeEs=null;
    ejeEs=(List<Ejeestrategico>)request.getSession().getAttribute("listar_eje");
    int i=0;
%>
 <body >
     
     <table>
         <tr>
             <td>
                 <img src="../../resources/img/ejes-grafico.png" width="672" height="455" alt="ejes-grafico" class="img-polaroid"/>

             </td> 

             <td>
                 
<div class="accordion" id="accordion2">
            <% int edwin=0;
            if(ejeEs!=null){for(Ejeestrategico pm: ejeEs){i++;%>
            
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse<%=i%>">
                            <font color="#267DFF" size="4"><strong> Eje <%=++edwin%>. <%=pm.getNombre()%></strong></font>            

                              </a>
                            </div>
                            <div id="collapse<%=i%>" class="accordion-body collapse ">
                              <div class="accordion-inner">
                              <%=pm.getDescripcion()%>
                              <p></p>
                              <p class="text-left"><strong>MAPA ESTRATÉGICO:</strong></p>

                                         <center>            
                                    <img src="<%= request.getContextPath()%>/public/images/avatar/<%=pm.getMapaestrategico()%>" width="723" height="509" alt="politicas" class="img-polaroid"/>
                                    </center> 
                              </div>
                            </div>
                        </div>            
<%}}%>    
</div>                         
             </td> 
         </tr>
     </table>   
     



    </body>
</html>
