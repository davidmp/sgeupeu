<%-- 
    Document   : listObservacion
    Created on : 15/07/2014, 06:05:49 PM
    Author     : Intel
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sge.service.SeguimientoService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">            
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        SeguimientoService ss;
        ss=new SeguimientoService();

        int idavance=Integer.parseInt(request.getParameter("idavance")==null ? "0":request.getParameter("idavance"));
        String indicador=String.valueOf(request.getParameter("indicador")==null ? " ":request.getParameter("indicador").toString());
        sge.modelo.Usuario usuSess=(sge.modelo.Usuario)request.getSession().getAttribute("listaPerfilUsuario");
        %>
    <center>
        <div>
            <p><b> <% out.print(indicador); %> </b></p>
            <table style="width: 100%">
                <tr>
                    <th style="width: 2%">#</th>
                    <th style="width: 28%">Observaci&oacute;n</th>
                    <th style="width: 5%">Tipo</th>
                    <th style="width: 15%">Usuario</th>
                    <th style="width: 5%">Fecha</th>                    
                    <th style="width: 15%">Vi&oacute;</th>
                    <th style="width: 5%">Fecha</th>  
                    <th style="width: 15%">Corregi&oacute;</th>
                    <th style="width: 5%">Fecha</th>  
                    <th style="width: 3%">Estado</th>  
                    <th style="width: 2%">Opc</th>  
                </tr>
                <%
                
                ArrayList listaEjes=null;
                listaEjes = ss.reporteObservacionesModelo2(idavance);            
                Iterator<Object> inter2=listaEjes.iterator();
                int i=0;
                while(inter2.hasNext()){
                    Map datos=  (Map)inter2.next();
                    i=i+1;
                %>
                
                <tr style="<% if((i%2)==1){out.print("background-color:greenyellow");}else{out.print("background-color:#ffffff");} %>">
                    <td><%=i%></td>
                    <td><%=datos.get("observacion")%></td>
                    <td><%=datos.get("obstipo")%></td>
                    <td><%=datos.get("usuarioobs")%></td>
                    <td><%=datos.get("fechaobs")%></td>                    
                    <td><%=datos.get("usuarioview")%></td>
                    <td><%=datos.get("fechaview")%></td>  
                    <td><%=datos.get("usuarioresp")%></td>
                    <td><%=datos.get("fecharesp")%></td>  
                    <td><%=datos.get("estado")%></td>  iduserobs
                    <td> <% if(Integer.parseInt(usuSess.getIdusuario())==Integer.parseInt(datos.get("iduserobs").toString())){ %> 
                        <img src="../../resources/32/deletew.png" width="30" height="30" onclick="registrarObs('<%=datos.get("idseguimiento")%>')"  alt="Elimanr"/>
                    <% }else{ %>                    
                     <img src="../../resources/32/view.png" width="30" height="30"   alt="Ver"/>
                    <% } %>
                    </td>  
                </tr>
                <% }  %>
            </table>
            <br/>
            <b><a href="javascript:void(0)" onclick="$.pgwModal('close');">Cerrar</a></b>
            
        </div>
    </center>
    </body>
</html>
    <script type="text/javascript">
        function registrarObs(idseguimiento){
            $.ajax({
            type:"GET",
            url:"<%=request.getContextPath()%>/Seguimiento?opt=3&idseguimiento="+idseguimiento,
            data:"",
            success: function(datos){
               if($.trim(datos)=="1"){
                   $.pgwModal('close');
               } else{
                   alert("Error al Insertar!!");
               }                 
            }               
         });
           
        }
        

    </script>