<%-- 
    Document   : index
    Created on : 13/09/2013, 01:39:16 AM
    Author     : oscdmdz
--%>

<%@page import="sge.modelo.Usuario"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Facultad"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Periodometa"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Indicador"%>

<%
Usuario we = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
Eap eUe=(Eap)request.getSession().getAttribute("eapUsuario");
Facultad faUe=(Facultad)request.getSession().getAttribute("facultadUsuario");
Filial fiUe=(Filial)request.getSession().getAttribute("filialUsuario");
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

    <div class="row-fluid">
		<div class="span12">
                
            <ul id="MainTabs" class="nav nav-tabs">
        <li title="Visión y Misión" class="informacion">
            <a data-target="#politicas" data-toggle="tab" href="<%=request.getContextPath()%>/Indicador?opt=18&idfilial=<%if(fiUe!=null){%><%=fiUe.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eUe!=null){%><%=eUe.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad<%if(faUe!=null){%><%=faUe.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon-th-large"></div>&nbsp;Visión y Misión</a>
        </li>
        
        <li title="Ejes Estrategicos UPeU" class="informacion">
            <a data-target="#eje" data-toggle="tab" href="<%=request.getContextPath()%>/Indicador?opt=19&idfilial=<%if(fiUe!=null){%><%=fiUe.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eUe!=null){%><%=eUe.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad<%if(faUe!=null){%><%=faUe.getIdfilialfacultad()%><%}else{%>0<%}%>"><div class="icon-th-large"></div>&nbsp;Ejes Estratégicos</a>
        </li>
        <% if(fiUe.getIdfilial()==2){ %>
        <li title="Modelo de Estructura de Informe" class="informacion">            
            <a data-target="#dmp" data-toggle="tab"   href="<%= request.getContextPath()%>/public/juliaca/PLANTILLA GENERAL DE INFORMES MODELO 2.docx"  rel="tooltip" ><div class="icon-book"></div>Modelo de Informes</a><br/>
            
        </li>
        <% } %>
    
            </ul>
                    
            <div class="tab-content">
              <div class="tab-pane" id="politicas">Loading...</div>
              <div class="tab-pane" id="eje">Loading...</div>
              <div class="tab-pane" id="dmp">                  
                
                <a href="<%= request.getContextPath()%>/public/juliaca/PLANTILLA GENERAL DE INFORMES MODELO 2.docx" role="button" class="btn btn-success" data-toggle="modal" target="_blank" rel="tooltip" ><i class="icon-download-alt icon-white"></i>Estructura de Modelo de Informe General</a> <br/>
                <a href="<%= request.getContextPath()%>/public/juliaca/FichaTecnica.pdf" role="button" class="btn btn-success" data-toggle="modal" target="_blank" rel="tooltip" ><i class="icon-download-alt icon-white"></i>Ficha T&eacute;cnica</a>                  
              </div>
            
            </div>
                
                </div>
    </div>
