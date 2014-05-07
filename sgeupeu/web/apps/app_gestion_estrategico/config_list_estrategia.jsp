<%-- 
    Document   : config_list_estrategia
    Created on : 24/10/2013, 04:14:01 PM
    Author     : Edwin
--%>


<%@page import="sge.modelo.Estrategia"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<script type="text/javascript">
var form = $('#addPersona');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#estrategias').show();
$('#estrategias').html(data);
document.getElementById("addPersona").reset();
document.getElementById("estrategias").reset(); 


}
});
 
return false;
});

$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#estrategias").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#estrategias").empty().append(html);
    }
    });
    return false;
    });
});

$(function() {
      $('table').footable();
    });
    
    $(document).ready(function() {
    $("tbody a").click(function() {
    $("#estrategias").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#estrategias").empty().append(html);
    }
    });
    return false;
    });
});
$('.toltipx').tooltip();

</script>
<style>

</style>
    </head>
    <body>
       
                    <div   class="row-fluid">
                        <div  class="span3" id="topmenu" >
                            <a href="<%=request.getContextPath()%>/apps/app_gestion_estrategico/add_post_estrategias.jsp" class="btn"><div class="icon-plus"></div>&nbsp;Registrar</a>
                            <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=57" class="btn"><div class="icon-remove"></div>&nbsp;Cerrar</a>
                        </div>
                        <div  class="span6"></div>
                        <div  class="span3">
                            <input type="search" name="" value="" id="estrategiasbuscar" placeholder="Buscar .." class="pull-right"/>
                        </div>    
                    </div>    
                        
                        <div  class="row-fluid">
                            <div  class="span12">
       <table data-filter="#estrategiasbuscar" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
       <thead>
        <tr>
            <th data-class="expand" data-sort-initial="true">
            </th> 
          <th>
            Nombre de Estrategia
          </th>
          <th data-hide="phone">
           Descripcion
          </th>
          <th data-hide="phone,tablet">
            Opciones
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Estrategia> lista=(List<Estrategia>) request.getSession().getAttribute("listEstrategia");
                        if (lista != null) {
                            for (Estrategia es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=es.getNro()%></td>
                    <td><%=es.getNombre()%></td>
                    <td><button class="btn btn-small toltipx" title="<%=es.getDescripcion()%>"><div class="icon-comment"></div></button></td>
                    <td style="width: 10%;text-align: center;">
                        <a class="btn btn-small toltipx" title="Desea Editar" href="<%=request.getContextPath()%>/GestionEstrategico?opt=59&idEstrategia=<%=es.getIdestrategia()%>"><div class="icon-edit"></div></a>
                        <a class="btn btn-small toltipx" title="Desea Eliminar" href="<%=request.getContextPath()%>/GestionEstrategico?opt=61&idEstrategia=<%=es.getIdestrategia()%>"><div class="icon-remove"></div></a>
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
