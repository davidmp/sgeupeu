<%-- 
    Document   : index
    Created on : 28/08/2013, 09:50:49 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Usuario"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Facultad"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Periodometa"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Indicador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

<%

    Usuario w = null;


    w = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");

    Eap eU=(Eap)request.getSession().getAttribute("eapUsuario");
    Facultad faU=(Facultad)request.getSession().getAttribute("facultadUsuario");
    Filial fiU=(Filial)request.getSession().getAttribute("filialUsuario");


    List<Ejeestrategico> ejeEs=null;
    ejeEs=(List<Ejeestrategico>)request.getSession().getAttribute("listar_eje");            
            
    List<Indicador> indicador=null;
    indicador=(List<Indicador>)request.getSession().getAttribute("listar_indicador");
    
    List<Periodometa> periodoM=null;
    periodoM=(List<Periodometa>)request.getSession().getAttribute("listar_periodo_meta");
    


%>

       
        
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
      <div class="row-fluid">
		<div class="span12">              
            <ul id="MainTabs" class="nav nav-tabs">
                <li title="Lista de indicadores" class="informacion">
                    <a data-target="#waza" data-toggle="tab" href="<%=request.getContextPath()%>/InformesActividad?opt=2&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon-arrow-right"></div>&nbsp;Reporte de Actividades    </a>
                </li>   
            </ul>
                    
            <div class="tab-content">
            <div class="tab-pane" id="waza">Loading...</div>
            </div>
                
            </div>
        </div>
    </body>
</html>
