<%-- 
    Document   : list_post_eje_objetivo_estrategico
    Created on : 27/09/2013, 12:56:42 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="sge.modelo.Temporada"%>
<%@page import="sge.modelo.TemporadaEjeObjEstrategico"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <script type="text/javascript">
            $(function() {
      $('table').footable();
    });
    
     
var form = $('#addTemporadaEjeObjEst');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#reportar').show();
$('#reportar').html(data);
document.getElementById("addTemporadaEjeObjEst").reset();
document.getElementById("reportar").reset(); 

}
});
 
return false;
});
    $(document).ready(function() {
    $("tbody a").click(function() {
    $("#reportar").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $("#reportar").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
    $.ajax({ url: this.href, success: function(html) {
    $("#reportar").empty().append(html);
    }
    });
    return false;
    });
});
   $('.toltipx').tooltip();
        </script>
 
   <script> 
function ChequearTodos(chkbox) 
{ 
for (var i=0;i < document.forms["addTemporadaEjeObjEst"].elements.length;i++) 
{ 
var elemento = document.forms[0].elements[i]; 
if (elemento.type === "checkbox") 
{ 
elemento.checked = chkbox.checked; 
} 
} 
}

$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#reportar").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#reportar").empty().append(html);
    }
    });
    return false;
    });
});
 $('.toltipx').tooltip();
</script> 
    </head>
    <body>
        
        <%
        String var = (String)session.getAttribute("edwintemporadasessionID");
        %>
       <div class="row-fluid">
       <div class="span12">
	<form id="addTemporadaEjeObjEst" name="addTemporadaEjeObjEst" action="<%=request.getContextPath()%>/GestionEstrategicoApoyo" method="POST">
            <legend><h4>Registro de <strong class="text-info">Ejes Estrategicos</strong> con sus <strong style="color: #b94a48;"> Objetivos Estrategicos</strong></h4></legend>
            <fieldset>
                <%
        List<Temporada> tem=(List<Temporada>) request.getSession().getAttribute("Listar_Rango_Temporada");
                if (tem != null) {
                for (Temporada v : tem) {
                    %>
	   
            
            <div class="row-fluid">
				<div class="span3">
                                    
                                    <input type="text" name="idtemporada" value="<%=v.getIdtemporada()%>" style="visibility: hidden;display: none;"/>
                                     <%}}%>
                                    <select name="idEjeEstrategico" title="Seleccione un Eje Estrategico para asignar a la temporada seleccionada y guardar" class="toltipx"  required="">
                                        <option></option>
                                         <%
                        int xd=0;     
                        List<Ejeestrategico> listaejeobj=(List<Ejeestrategico>) request.getSession().getAttribute("listar_ejes_estrategicos");
                        if (listaejeobj != null) {
                            for (Ejeestrategico es : listaejeobj) {

                    %>
                    <option title="<%=es.getObjetivoestrategico()%>"value="<%=es.getIdejeestrategico()%>"> <%=es.getCodigo() %>:&nbsp;<%=es.getNombre()%> (<%=es.getTipoarea() %>)&nbsp;&nbsp;</option>
                                        
                                        <% }} %>
                                    </select>
                               </div>
				<div class="span3">
                                    <div id="topmenu">
                        <input type="hidden" name="opt" value="38"/>
                        <button class="btn" type="submit"><div class="icon-ok"></div>&nbsp;Guardar</button>
                        <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=35" class="btn toltipx" title="Registrar nuevo Eje Objetivo Estrategico en la Base de Datos"><div class="icon-wrench"></div>&nbsp;Configuracion</a>
                        </div>
                                </div>
                        <div class="span3"></div>
				<div class="span3">
                                     <input type="search" id="Filter_Ejes_Objetivos_Estrategicos_Temporada" placeholder="Buscar .." class="pull-right"/>
       
                               </div>
                        
            </div>
                   
                        
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
       
	<div class="row-fluid">
            	<div class="span12">
        <table data-filter="#Filter_Ejes_Objetivos_Estrategicos_Temporada" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
            </th> 
            <th style="color: #3a87ad;">
            Eje Estratégico
          </th>
          <th data-hide="phone">
           <div class="icon-arrow-right"></div>
          </th>
          <th data-hide="phone,tablet" style="color: #b94a48;">
            Objetivo Estratégico
          </th>
          <th data-hide="phone,tablet">
              &Aacute;rea
          </th>
          <th data-hide="phone,tablet">
            Mapa
          </th>
          <th data-hide="phone,tablet">
          
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<TemporadaEjeObjEstrategico> lista=(List<TemporadaEjeObjEstrategico>) request.getSession().getAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada");
                        if (lista != null) {
                            for (TemporadaEjeObjEstrategico es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td style="background-color: #3a87ad;color: white"><%=es.getIdEjeEstrategico_nombre()%></td>
                    <td><button class="btn btn-small toltipx" title="Descripcion Eje :<%=es.getIdEjeEstrategico_descripcion()%>"><div class="icon-comment"></div></button></td>
                    <td style="background-color: #b94a48;color: white"><%=es.getIdEjeEstrategico_Objetivo()%></td>
                    <td><%=es.getNombreTipoArea()%></td>
                    <td><button class="btn btn-small toltipx" title="Mapa Objetivo :<%=es.getIdEjeEstrategico_mapa()%>"><div class="icon-comment"></div></button></td>
                    <td style="width: 3%;text-align: center;">
                    <a class="btn btn-small toltipx" title="Desea Eliminar" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=39&IdTemporadaEjeEstrategico=<%=es.getIdTemporadaEjeEstrategico()%>"><div class="icon-remove"></div></a>
                    </td>
                </tr>
             <% }} %>
            </tbody>
      <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="8" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
        </tr>
      </tfoot>
    </table>
		</div>
		
	</div>
            
            
            <div class="row-fluid">
		<div class="span12">
			
                    
                    <form action="<%=request.getContextPath()%>/GestionEstrategicoApoyo" method="post" name="addTemporadaEjeObjEst" id="addTemporadaEjeObjEst">
                    
			<div id="modal-container-163566" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 id="myModalLabel">
						<%
        List<Temporada> temporada=(List<Temporada>) request.getSession().getAttribute("Listar_Rango_Temporada");
                if (temporada != null) {
                for (Temporada te : temporada) {
                    %>
                    <p class="text-info">Asignar a la Temporada&nbsp;<%=te.getInicio()%>&nbsp;-&nbsp;<%=te.getFin()%></p>
                   <%}}%> 
					</h4>
				</div>
				<div class="modal-body">
					
                                    
                            
                                    
                                    
                                    
                                    
				</div>
				<div class="modal-footer">
                                    <input type="hidden" name="idTemporada" value="<%=var%>"/>
                                    <input type="hidden" name="opt" value="34" />
                                    <input type="submit" name="" value="save" />
                                    <button type="reset" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</button> 
                                    <button type="button" class="btn btn-primary">Guardar</button>
				</div>
			</div>
            
                    </form>
		</div>
	</div>

       
    </body>
</html>
