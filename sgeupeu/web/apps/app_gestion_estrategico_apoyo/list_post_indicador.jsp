<%-- 
    Document   : list_post_indicador
    Created on : 15/10/2013, 01:58:33 PM
    Author     : Edwin
--%>
<%@page import="sge.modelo.EstrategiaIndicador"%>
<%@page import="sge.modelo.Indicador"%>
<%@page import="sge.modelo.Estrategia"%>
<%@page import="sge.modelo.EjeEstrategia"%>
<%@page import="sge.modelo.Persona"%>
<%@page import="sge.modelo.Usuario"%>
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
    
    $(document).ready(function() {
    
    $("tbody a").click(function() {
    $("#indicadoresestrategias").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#indicadoresestrategias").empty().append(html);
    }
    });
    return false;
    });
});
$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#indicadoresestrategias").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#indicadoresestrategias").empty().append(html);
    }
    });
    return false;
    });
});
 $('.toltipx').tooltip();
 
 
 
 var form = $('#addPersonaccc');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#indicadoresestrategias').show();
$('#indicadoresestrategias').html(data);
document.getElementById("addPersonaccc").reset();
document.getElementById("indicadoresestrategias").reset(); 

}
});
 
return false;
});


</script>



    </head>
    <body>
        <%
        String var = (String)session.getAttribute("idEjeEstrategiaSession");
        int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
        %>
       
            <form method="POST" action="<%=request.getContextPath()%>/GestionEstrategicoApoyo" name="addPersonaccc" id="addPersonaccc">
             <legend>Registro de <strong style="color: #00747d;">Indicadores</strong> segun la <strong class="text-success">Estrategia</strong> Seleccionada</legend>
            
                 <div class="row-fluid">
                     <div class="span3">
                          <input type="text" name="idEjeEstrategia" value="<%=var%>" style="visibility: hidden;display: none;"/>
                 <label><strong>Indicadores</strong></label>
                 <select name="idIndicador" class="toltipx" title="Selecciona Un Indicador para Asignar a la Estrategia" required="">
                     <option></option>
                     <%
                        int x=0;     
                        List<Indicador> lista=(List<Indicador>) request.getSession().getAttribute("listar_indicador");
                        if (lista != null) {
                            for (Indicador es : lista) {

                    %>
                    <option value="<%=es.getIdindicador()%>"><%=es.getNombre()%></option>
                     <%}}%>
                 </select>
                     </div>
                     <div class="span3">
                             
                        <label><strong>Tipo</strong></label>
                         <select name="" name="instrumento" placeholder="Instrumen..." required="" class="span6" >
                             <option value="Creciente" >Creciente</option>
                             <option value="Decreciente" >Decreciente</option>
                         </select>                             
                 
            
                     </div>
                     <div class="span3">
                         <div id="topmenu">  
                        <label style="visibility: hidden;">alsdklaskdlad</label>
                        <input type="hidden" name="opt" value="50" />
                        <input type="hidden" name="idEjeX" value="<%=idEjeX%>" />
                        <button type="submit" class="btn toltipx"><div class="icon-ok"></div>&nbsp;Guardar</button>
                        <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=15&idEjeX=<%=idEjeX%>" class="btn toltipx" title="Registrar Nuevo Indicador en la Base de Datos"><div class="icon icon-plus"></div>&nbsp;Configuracion</a>
                        
                         </div>
                     </div>
                     <div class="span3">
                     <label style="visibility: hidden;">alsdklaskdlad</label>    
                     <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
                     </div>
                 </div>
                  </form>
                 
                 
                 
                 
                 
                 
        <div class="row-fluid">
		<div class="span12">

         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="7" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 5%;">
            Codigo
            </th>
            <th data-hide="phone" style="width: 5%;text-align: center; ">
                <div class="icon-th-list toltipx" title="Estrategias"></div>
            </th>
            <th style="color: #00747d;">
            Indicadores
            </th>
            <th data-hide="phone" style="width: 5%;text-align: center;width: 5%; ">
      <div class="icon-th-list toltipx" title="Instrumento"></div>
            </th>
            <th data-hide="phone" style="color: #00747d;width: 12%;">
            Tipo
            </th>
            <th data-hide="phone,tablet" style="width: 15%;text-align: center; ">
            opc
            </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int xu=0;     
                        List<EstrategiaIndicador> listaIndicador=(List<EstrategiaIndicador>) request.getSession().getAttribute("Listar_Estrategias_Indicador");
                        if (listaIndicador != null) {
                            for (EstrategiaIndicador es : listaIndicador) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td> <%=es.getNro()%></td>
                    <td style="background-color: #468847;color: white;text-align: center;"><button class="btn btn-small btn-success toltipx" title="ESTRATEGIA : <%=es.getIdEjeEstrategia_nombre()%>"><div class="icon-comment icon-white"></div></button></td>
                    <td style="background-color: #00747d; color: white;"><%=es.getIdIndicador_nombre()%></td>
                    <td style="text-align: center;"><%=es.getInstrumento()%></td>
                    <td style="background-color: #ffa600;color: black;;"><%=es.getIdIndicador_tipo()%></td>
                    <td>
                        <a class="btn btn-small toltipx" title="Desea Eliminar" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=51&idestrategiaIndicador=<%=es.getIdestrategiaIndicador()%>"><div class="icon-remove"></div></a>&nbsp;
                        <a class="btn btn-small toltipx" title="Ingresar Actividades" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=86&idestrategiaIndicador=<%=es.getIdestrategiaIndicador()%>"><div class="icon-tasks"></div>&nbsp;Actividades</a>
            
            
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
    </body>
</html>
