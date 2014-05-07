<%-- 
    Document   : add_eje_objetivo
    Created on : 14/10/2013, 10:07:07 AM
    Author     : Edwin
--%>



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
$('#reportar').show();
$('#reportar').html(data);
document.getElementById("addPersona").reset();
document.getElementById("reportar").reset(); 


}
});
 
return false;
});

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

$(function() {
      $('table').footable();
    });
    
    $(document).ready(function() {
    $("tbody a").click(function() {
    $("#reportar").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#reportar").empty().append(html);
    }
    });
    return false;
    });
});

// add 

$('.toltipx').tooltip();

</script>
<style>

</style>
    </head>
    <body>
       
                    <div   class="row-fluid">
                        <div  class="span3" id="topmenu" >
                            <a href="<%=request.getContextPath()%>/apps/app_gestion_estrategico_apoyo/add_post_eje_objetivo.jsp" class="btn"><div class="icon-plus"></div>&nbsp;Registrar</a>
                            <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=37" class="btn"><div class="icon-remove"></div>&nbsp;Cerrar</a>
                        </div>
                        <div  class="span6"></div>
                        <div  class="span3">
                            <input type="search" name="" value="" id="filterejeobjetivo" placeholder="Buscar .." class="pull-right"/>
                        </div>    
                    </div>    
                        
                        <div  class="row-fluid">
                            <div  class="span12">
       <table data-filter="#filterejeobjetivo" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
            </th> 
          <th>
            Eje Estratégico
          </th>
          <th data-hide="phone,tablet">
              &Aacute;rea
          </th>
          <th data-hide="phone">
           
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
            Objetivo Estratégico
          </th>
          
          <th data-hide="phone,tablet">
     
          </th>
          <th data-hide="phone,tablet">
            Opciones
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Ejeestrategico> lista=(List<Ejeestrategico>) request.getSession().getAttribute("listar_ejes_estrategicos");
                        if (lista != null) {
                            for (Ejeestrategico es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=es.getNombre()%></td>
                    <td><%=es.getTipoarea() %></td>
                    <td><button class="btn btn-small toltipx" title="<%=es.getDescripcion()%>"><div class="icon-comment"></div></button></td>
                    <td><%=es.getObjetivoestrategico()%></td>
                    <td><button class="btn btn-small toltipx" title="<%=es.getMapaestrategico()%>"><div class="icon-comment"></div></button></td>
                    <td style="width: 10%;text-align: center;">
                        
                        
                        <a class="btn btn-small toltipx" title="Desea Editar" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=54&idEjeEstrategico=<%=es.getIdejeestrategico()%>"><div class="icon-edit"></div></a>
                        <a class="btn btn-small toltipx" title="Desea Eliminar" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=40&idEjeEstrategico=<%=es.getIdejeestrategico()%>"><div class="icon-remove"></div></a>
                        
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
