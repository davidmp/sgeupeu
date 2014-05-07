<%-- 
    Document   : list_periodo
    Created on : 29/09/2013, 11:19:00 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Periodo"%>
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
    $(".tab-content").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $(".tab-content").empty().append(html);
    }
    });
    return false;
    });
});
$(document).ready(function() {
    $("#topmenu a").click(function() {
    $(".tab-content").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $(".tab-content").empty().append(html);
    }
    });
    return false;
    });
});
 $('.toltipx').tooltip();
</script>
    </head>
    <body>
        <div class="row-fluid">
		<div class="span12">

             <div id="topmenu" class="pull-left">
            <a href="<%=request.getContextPath()%>/Organizacion?opt=12" class="btn" ><div class="icon-plus"></div>&nbsp;Nuevo Periodo</a>
            <a href="<%=request.getContextPath()%>/Organizacion?opt=11" class="btn" ><div class="icon-list"></div>&nbsp;Reportar </a>
             </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 4%;">
                #
            </th> 
            <th data-hide="phone">
                Estado
          </th>
          <th>
            Periodo
          </th>
          <th data-hide="phone">
            Fecha Inicio
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
            Fecha Fin
          </th>
          <th>
            Temporada
          </th>          
          <th data-hide="phone,tablet" style="width: 15%;">
            Opc
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Periodo> lista=(List<Periodo>) request.getSession().getAttribute("listar_periodo");
                        if (lista != null) {
                            for (Periodo pe : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%
           int op_estado = Integer.parseInt(pe.getEstado());                     
           if(op_estado==1){
               %> <span class="label label-success" style="width: 90%;">Activado</span>  <%
            }else if(op_estado==0){
            %> <span class="label label-inverse" style="width: 90%;">Desactivo</span><%
            } %></td>
                    <td><%=pe.getPeriodo()%></td>
                    <td><%=pe.getFechainicio()%></td>
                    <td><%=pe.getFechafin()%></td>
                    <td><%=pe.getNombretemporada() %></td>
                    <td>
                        <%
            int opt = Integer.parseInt(pe.getEstado());
            if(opt==1){
                %> <a class="btn btn-small toltipx" href="<%=request.getContextPath()%>/Organizacion?opt=15&&idPeriodo=<%=pe.getIdperiodo()%>" title="Desactivar Periodo"><div class="btn-icon-only icon-ban-circle"></div></a> <%
            }else if(opt==0){
                %> <a class="btn btn-small toltipx" href="<%=request.getContextPath()%>/Organizacion?opt=14&&idPeriodo=<%=pe.getIdperiodo()%>" title="Activar Periodo: Al activar el periodo usted esta aperturando una etapa o periodo para los coordinadores"><div class="btn-icon-only icon-ok"></div></a> <%
            } %>
            
            <a href="<%=request.getContextPath()%>/Organizacion?opt=16&idPeriodo=<%=pe.getIdperiodo()%>" class="btn btn-small toltipx" title="Editar Periodo"><div class="icon-edit"></div></a>
            <a href="<%=request.getContextPath()%>/Organizacion?opt=28&idPeriodo=<%=pe.getIdperiodo()%>" class="btn btn-small toltipx" title="Eliminar : Para Eliminar Este Campo no debe de tener relacion con ningun otro modulo"><div class="icon-remove"></div></a>
                    </td>
                    
                    
                </tr>
             <% }} %>
            </tbody>
      <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="6" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
        </tr>
      </tfoot>
    </table>
            </div>
	</div>
    </body>
</html>

