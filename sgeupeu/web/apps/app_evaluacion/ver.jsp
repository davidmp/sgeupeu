<%@page import="sge.directori.FileDirectori"%>
<%@page import="sge.service.ReporteService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<% 
    ReporteService rs;
    ArrayList lista;
    Iterator<Object> inter;
    Map datos;
    FileDirectori fd=new FileDirectori();       
    int idavance=Integer.parseInt(request.getParameter("idavance")==null ? "0":request.getParameter("idavance"));
    int idfilial=Integer.parseInt(request.getParameter("idfilial")==null ? "0":request.getParameter("idfilial"));
    String carpeta=fd.directorioFilial(idfilial);
%>
<div style="text-align:center">
    <strong>LISTA DE ARCHIVOS</strong>
    <center>
    <table>
        <tr>
            <th>#</th>
            <th>Evidencia</th>
            <th>Usuario</th>
            <th>Fecha</th>
        </tr>
<% 
rs=new ReporteService();
lista=rs.reporteArchivos(idavance);
inter=lista.iterator();
int i=0;
while(inter.hasNext()){
datos=(Map)inter.next(); 
%>
<tr>
    <td><%=++i%></td>
    <td > 
    <center>
        <a href="ftp://sger1:lectura@192.168.13.38/<%=carpeta%><%=datos.get("url").toString()%>" target="_blank" >
    <img src="../../resources/file/<%=datos.get("tipo").toString()%>.png" width="30" height="30" title="<%=datos.get("evidencia").toString()%>"  alt="Archivo"/>
    </a> 
    </center>    
    </td>
    <td><%=datos.get("usuario") %></td>
    <td><%=datos.get("fecha_reg") %></td>
</tr>
<% } 
%>
    </table>
    </center><br>
    <b><a href="javascript:void(0)" onclick="$.pgwModal('close');">Cerrar</a></b>
</div>