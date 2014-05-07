<%-- 
    Document   : index
    Created on : 28/08/2013, 09:50:49 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Filialfacultad"%>
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



    List<Ejeestrategico> ejeEs=null;
    ejeEs=(List<Ejeestrategico>)request.getSession().getAttribute("listar_eje");            
            
    List<Indicador> indicador=null;
    indicador=(List<Indicador>)request.getSession().getAttribute("listar_indicador");
    List<Periodometa> periodoM=null;
    periodoM=(List<Periodometa>)request.getSession().getAttribute("listar_periodo_meta");
    Filial filial=(Filial)request.getSession().getAttribute("sessionFilial");
    Filialfacultad Ffacultad=(Filialfacultad)request.getSession().getAttribute("sessionFacultad");
    
    System.out.println(" ke paso ps --- --- -- -- -- > "+filial);

    

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
          <br><br>            
            <ul id="MainTabs" class="nav nav-tabs">
                
                <li title="Muestra avance por Filial" class="informacion">
                     <a data-target="#filial" data-toggle="tab" href="<%=request.getContextPath()%>/Indicador?opt=17"><div class="icon-tasks"></div>&nbsp;Avance consolidado de la Filial <strong><%=filial.getDireccion()%></strong></a>
                </li>  
                
                 <li title="Muestra avance por Facultad" class="informacion">
                    <a data-target="#facultad" data-toggle="tab" href="<%=request.getContextPath()%>/Indicador?opt=16&idfilialfacultad=<%=Ffacultad.getIdfilialfacultad()%>"><div class="icon-tasks"></div>&nbsp;Avance consolidado de la Facultad :<strong><%=Ffacultad.getIdfacultad_nombre()%></strong></a>
                </li> 

            </ul>


              
            <div class="tab-content">  
            <div class="tab-pane" id="filial">Loading...</div>    
            <div class="tab-pane" id="facultad">Loading...</div>
            </div>
                
            </div>
        </div>
    </body>
</html>
