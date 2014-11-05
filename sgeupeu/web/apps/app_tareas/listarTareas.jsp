<%-- 
    Document   : listObservacion
    Created on : 15/07/2014, 06:05:49 PM
    Author     : Intel
--%>

<%@page import="sge.modelo.Tareas"%>
<%@page import="java.util.List"%>
<%@page import="sge.service.IndicadorService"%>
<%@page import="sge.service.ReporteService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sge.service.SeguimientoService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">            
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        IndicadorService ss;
        ss=new IndicadorService();

        int idactividad=Integer.parseInt(request.getParameter("idactividad")==null ? "0":request.getParameter("idactividad"));
        double presupuesto=Double.parseDouble(request.getParameter("presupuesto")==null ? "0":request.getParameter("presupuesto"));
        String accion=String.valueOf(request.getParameter("accion")==null ? " ":request.getParameter("accion").toString());
        String fechas=String.valueOf(request.getParameter("fechas")==null ? " ":request.getParameter("fechas").toString());
        ReporteService rs;
        rs=new ReporteService();
        double saldo=rs.saldoTareas(idactividad);
        %>
    <center>
        <div>
            <p>
                <b>Actividad: </b><% out.print(accion); %><br>
                <b>Presupesto: </b> <% out.print(presupuesto); %> &nbsp;&nbsp;&nbsp;  <b>Tiene: </b> <% out.print(presupuesto-saldo); %><br>
                <b>Fechas: </b> <% out.print(fechas); %>
            </p>
            <table style="width: 100%">
                <tr>
                    <th style="width: 2%">#</th>
                    <th style="width: 28%">Tarea</th>
                    <th style="width: 10%">Fecha I</th>
                    <th style="width: 10%">Fecha F</th>
                    <th style="width: 5%">RR.HH</th>                     
                    <th style="width: 5%">Material</th>                     
                    <th style="width: 5%">Equipo</th>                     
                    <th style="width: 5%">Aten.Pers</th>                     
                    <th style="width: 5%">Movilidad</th>                     
                    <th style="width: 5%">Otros</th>                     
                    <th style="width: 5%">Total</th>                     
                    <th style="width: 5%">Opc.</th>                     
                </tr>
                <%
                
                List<Tareas> lista=null;
                lista = ss.listarTareas(idactividad);  
                //out.println("VER:"+lista.size());
                double resumen=0;
                int i=0;
                for(Tareas datos: lista){                   
                    i=i+1;
                    resumen+=datos.getMonto();
                %>
                
                <tr style="<% if((i%2)==1){out.print("background-color:greenyellow");}else{out.print("background-color:#ffffff");} %>">
                    <td><%=i%></td>
                    <td><%=datos.getNombre() %></td>
                    <td><%=datos.getFecha()  %></td>
                    <td><%=datos.getFecha2() %></td>
                    <td align="right"><%=datos.getRh() %></td>                    
                    <td align="right"><%=datos.getMateriales() %></td>                    
                    <td align="right"><%=datos.getEquipos() %></td>                    
                    <td align="right"><%=datos.getAp() %></td>                    
                    <td align="right"><%=datos.getMovilidad() %></td>                    
                    <td align="right"><%=datos.getOtros() %></td>                    
                    
                    <td align="right"><%=datos.getMonto() %></td>                    
                    <td align="center"><img src="../../resources/32/deletew.png" width="30" height="30" onclick="registrarObs('<%=datos.getIdtarea()%>')"  alt="Elimanr"/></td>                    
                </tr>
                <% }  %>
                <tr> 
                    <td colspan="10" align="right" > <b>RESUMEN S/. </b> </td>
                    <td align="center" colspan="2"><%=resumen%> </td>
                </tr>
            </table>
            <br/>
            <b><a href="javascript:void(0)" onclick="$.pgwModal('close');">Cerrar</a></b>
            
        </div>
    </center>
    </body>
</html>
   <script type="text/javascript">
        function registrarObs(idtarea){
            $.ajax({
            type:"GET",
            url:"<%=request.getContextPath()%>/IndicadorTareaEje?opt=5&idtarea="+idtarea,
            data:"",
            success: function(datos){
               if($.trim(datos)=="1"){
                   $.pgwModal('close');
               } else{
                   alert("Error al Eliminar!!");
               }                 
            }               
         });
           
        }
        

    </script>                
