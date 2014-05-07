
<%@page import="java.util.List"%>
<%@page import="sge.modelo.EstrategiaIndicador"%>
<%@page import="sge.modelo.Actividadindicador"%>
<%@page import="sge.modelo.Actividad"%>
<%@page import="sge.modelo.Actividadcronograma"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
var form = $('#addActividadIndicadorAdmin');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#mensajeactividad').show();
$('#mensajeactividad').html(data);
document.getElementById("addActividadIndicadorAdmin").reset();
document.getElementById("mensajeactividad").reset(); 


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

// add 

$('.toltipx').tooltip();

</script>
<%
            EstrategiaIndicador ei = null;
            ei = (EstrategiaIndicador) request.getSession().getAttribute("ActividadGetIDEstrategiaIndicador");
        %> 

<div   class="row-fluid">
    <div  class="span12">
        <legend>Actividades del Indicador</legend>
        <h5><strong class="text-success"><div class="icon-hand-right"></div>&nbsp;Indicador :</strong>&nbsp;<%=ei.getIdIndicador_nombre()%></h5>
        <h6><strong class="text-info"><div class="icon-hand-right"></div>&nbsp;Tipo de Indicador :</strong>&nbsp;<%=ei.getIdIndicador_tipo()%></h6>              
    </div>
</div>   
<div   class="row-fluid">
    		
                        <div  class="span6" id="topmenu">
                            <a  href="<%=request.getContextPath()%>/GestionEstrategico?opt=90" class="btn"><div class="icon-plus"></div>&nbsp;Registrar</a>
                            <a  href="<%=request.getContextPath()%>/GestionEstrategico?opt=88" class="btn"><div class="icon-list"></div>&nbsp;Reportar</a>
                             <a  href="<%=request.getContextPath()%>/GestionEstrategico?opt=87" class="btn"><div class="icon-remove"></div>&nbsp;Cerrar</a>
                        </div>
                       <div  class="span6">
                            
                            <input type="search" name="" value="" id="filteractividadindicador" placeholder="Buscar .." class="pull-right"/>
                        </div>    
                    </div>    
                        
                        <div  class="row-fluid">
                            <div  class="span12">
   
                                <table data-filter="#filteractividadindicador" class="table table-bordered table-condensed footable table-hover" data-page-size="7" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 3%;" rowspan="2">
                #
            </th>
            <th rowspan="2" style="width: 20%;text-align: center;">
      <h5>Actividad a Realizar</h5>
            </th>
            <th data-hide="phone" rowspan="2" style="width: 13%;text-align: center;">
                <h6>Información</h6>
            </th>
            <th data-hide="phone" colspan="12" style="text-align: center;">
      <h5>Cronograma</h5>
            </th>
            
            <th data-hide="phone,tablet" style="width:5%;text-align: center; " rowspan="2">
            Opc
            </th>
        </tr>
         <tr>
            <th>Ene</th>
            <th>Feb</th>
            <th>Mar</th>
            <th>Abr</th>
            <th>May</th>
            <th>Jun</th>
            <th>Jul</th>
            <th>Ago</th>
            <th>Set</th>
            <th>Oct</th>
            <th>Nov</th>
            <th>Dic</th>
            
        </tr>
       
      </thead>
      <tbody>
          <%
                        int x=0;     
                        List<Actividadindicador> lista=(List<Actividadindicador>) request.getSession().getAttribute("Listar_Actividad_Indicador");
                        if (lista != null) {
                            for (Actividadindicador a : lista) {

                    %>
                 <tr>
                    <td style="width: 3%"><%=a.getNro()%></td>
                    <td style="width: 20%"><%=a.getAccion()%></td>
                    <td style="width: 13%;text-align: center;"> 
                    <button class="btn btn-small toltipx" title="RUBRO:&nbsp;<%=a.getRubro()%>"><div class="icon-comment"></div></button>
                    <button class="btn btn-small toltipx" title="PRESUPUESTO :&nbsp;<%=a.getPresupuesto()%>"><div class="icon-shopping-cart"></div></button>
                    <button class="btn btn-small toltipx" title="CANTIDAD :&nbsp;<%=a.getCantidad()%>"><div class="icon-briefcase"></div></button>
                    </td>
                    <td>
                        <% if(a.getEnero()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getEnero()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getFebrero()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getFebrero()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getMarzo()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getMarzo()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getAbril()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getAbril()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getMayo()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getMayo()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getJunio()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getJunio()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getJulio()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getJulio()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getAgosto()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getAgosto()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getSetiembre()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getSetiembre()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getOctubre()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getOctubre()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getNoviembre()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getNoviembre()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <% if(a.getDiciembre()==1){%> 
                        <div class="icon-ok"></div>
                        <%}else if(a.getDiciembre()==0){%> 
                        <div></div>
                        <%}%>
                    </td>
                    <td>
                        <a class="btn btn-small toltipx" title="Eliminar Actividad" href="<%=request.getContextPath()%>/GestionEstrategico?opt=91&idActividadIndicador=<%=a.getIdActividadIndicador()%>"><div class="icon-remove"></div></a>
                    </td>
                    
                </tr>
             <%}}%>
            </tbody>
      <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="18" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
        </tr>
      </tfoot>
    </table>
                            </div>
                        </div>    
      
            
         