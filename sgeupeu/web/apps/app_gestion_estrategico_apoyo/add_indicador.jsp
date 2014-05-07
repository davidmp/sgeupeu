<%@page import="sge.modelo.Indicador"%>
<%@page import="java.util.List"%>

<script type="text/javascript">
var form = $('#addPersona');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#indicadoresestrategias').show();
$('#indicadoresestrategias').html(data);
document.getElementById("addPersona").reset();
document.getElementById("indicadoresestrategias").reset(); 


}
});
 
return false;
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

$(function() {
      $('table').footable();
    });
    
    $(document).ready(function() {
    $("tbody a").click(function() {
    $("#reportar").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#indicadoresestrategias").empty().append(html);
    }
    });
    return false;
    });
});

// add 

$('.toltipx').tooltip();

</script>
<%
int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
%>
<div   class="row-fluid">
                        <div  class="span3" id="topmenu" >
                            <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=62&idEjeX=<%=idEjeX%>" class="btn"><div class="icon-plus"></div>&nbsp;Registrar</a>
                            <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=52&idEjeX=<%=idEjeX%>" class="btn"><div class="icon-remove"></div>&nbsp;Cerrar</a>
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
            C&oacute;digo  
            </th> 
          <th>
           Nombre de Indicador
          </th>
          <th data-hide="phone">
           Formato
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
            Tipo
          </th>
          <th data-hide="phone,tablet">
            Opc
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Indicador> lista=(List<Indicador>) request.getSession().getAttribute("listar_indicador");
                        if (lista != null) {
                            for (Indicador es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=es.getCodigo()%></td>
                    <td><%=es.getNombre()%></td>
                    <td><button class="btn btn-small toltipx" title="<%=es.getDescripcion()%>"><div class="icon-comment"></div></button></td>
                    <td><%=es.getIdtipometa_nombre()%></td>
                    <td style="width: 10%;text-align: center;">
                        
                        
                        <a class="btn btn-small toltipx" title="Desea Editar" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=4&idIndicador=<%=es.getIdindicador()%>"><div class="icon-edit"></div></a>
                         <a class="btn btn-small toltipx" title="Desea Eliminar" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=53&idIndicador=<%=es.getIdindicador()%>"><div class="icon-remove"></div></a>
            
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
  