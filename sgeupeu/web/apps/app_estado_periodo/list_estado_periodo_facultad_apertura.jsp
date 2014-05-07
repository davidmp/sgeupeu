<%@page import="sge.modelo.Estadoperiodofacultad"%>
<%@page import="sge.modelo.Estadoperiodofilial"%>
<%@page import="java.util.List"%>
        <script type="text/javascript">
    $(function() {
      $('table').footable();
    });
    
    $(document).ready(function() {
    $("tbody a").click(function() {
    $("#ListaEstadoPeriodoFacultad").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#ListaEstadoPeriodoFacultad").empty().append(html);
    }
    });
    return false;
    });
});

// add 


$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#ListaEstadoPeriodoFacultad").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#ListaEstadoPeriodoFacultad").empty().append(html);
    }
    });
    return false;
    });
});
 $('.toltipx').tooltip();
 
 
 var form = $('#addEstadoFacultad');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#ListaEstadoPeriodoFacultad').show();
$('#ListaEstadoPeriodoFacultad').html(data);
document.getElementById("addEstadoFacultad").reset();
document.getElementById("ListaEstadoPeriodoFacultad").reset(); 

}
});
 
return false;
});



     </script>
     <%
           String idperiodometa = (String)session.getAttribute("IDPeriodosession");
           String idFilialFacultad = (String)session.getAttribute("IDFilialFacultadsession");
         %>
     <div class="row-fluid">
         <div class="span4">
            <div id="topmenu">
                
                <form action="<%=request.getContextPath()%>/GestionEstrategico" method="POST" name="addEstadoFacultad" id="addEstadoFacultad">
                    
                    <input type="hidden" name="idperiodometa" value="<%=idperiodometa%>" />
                    <input type="hidden" name="estadometa" value="0" />
                    <input type="hidden" name="estadoavance" value="0" />
                    <input type="hidden" name="idFilialfacultad" value="<%=idFilialFacultad%>" />
                    <input type="hidden" name="opt" value="80" />
                    <button class="btn toltipx" type="submit" title="Se Aperturará Segun el Periodo, Filial y Facultad Seleccionado : y usted controlará los Estados del Ingreso de Metas y Avances por Facultad"><div class="icon-plus"></div>&nbsp;Aperturar Nuevo</button>
                    <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=79" class="btn toltipx" title="Actualizar" ><div class="icon-refresh"></div></a>
               </form>
            </div>
        </div>
         <div class="span2">
           
         </div>
         <div class="span3">
             
         </div>
         <div class="span3">
      <input type="search" name="" value="" id="filterTemporada" placeholder="Buscar .." class="pull-right hidden"/>
               
         </div>
     </div>
     <div class="row-fluid">
		<div class="span12">
      <table data-filter="#filterTemporada" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 2%;">
              
            </th> 
          <th>
           Nombre de Facultad
          </th>
          <th data-hide="phone">
           Periodo
          </th>
          <th data-hide="phone,tablet" style="width: 20%;">
           Estado Meta
          </th>
          <th data-hide="phone,tablet" style="width: 20%;">
           Estado Avance
          </th>
          <th data-hide="phone,tablet" style="width: 5%;">
            
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Estadoperiodofacultad> lista=(List<Estadoperiodofacultad>) request.getSession().getAttribute("listar_estado_periodo_facultad");
                        if (lista != null) {
                            for (Estadoperiodofacultad es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><div class="icon-arrow-right"></div></td>
                    <td><%=es.getIdFilialfacultad_nombre()%></td>
                    <td><%=es.getIdperiodometa_nombre() %></td>
                    <td style="width: 20%;">
                    <%int optmeta=Integer.parseInt(es.getEstadometa());
            if(optmeta==1){
                %> <span class="label label-success span12"><div class="icon-eye-open icon-white"></div>&nbsp;Aperturado <a class="btn btn-small toltipx pull-right" title="Cerrar Apertura" href="<%=request.getContextPath()%>/GestionEstrategico?opt=81&idestadoperiodofacultad=<%=es.getIdestadoperiodofacultad() %>"><div class="icon-remove"></div></a></span> <%
            }else if(optmeta==0){
                %> <span class="label label-inverse span12"><div class="icon-eye-close icon-white"></div>&nbsp;Cerrado <a class="btn btn-small toltipx pull-right" title="Aperturar" href="<%=request.getContextPath()%>/GestionEstrategico?opt=82&idestadoperiodofacultad=<%=es.getIdestadoperiodofacultad()%>"><div class="icon-ok"></div></a></span><%
            } %>
                    
                    </td>
                    <td style="width: 20%;">
                        <%int optavance=Integer.parseInt(es.getEstadoavance());
            if(optavance==1){
                %> <span class="label label-success span12"><div class="icon-eye-open icon-white"></div>&nbsp;Aperturado <a class="btn btn-small toltipx pull-right" title="Cerrar Apertura" href="<%=request.getContextPath()%>/GestionEstrategico?opt=83&idestadoperiodofacultad=<%=es.getIdestadoperiodofacultad()%>"><div class="icon-remove"></div></a></span> <%
            }else if(optavance==0){
                %> <span class="label label-inverse span12"><div class="icon-eye-close icon-white"></div>&nbsp;Cerrado <a class="btn btn-small toltipx pull-right" title="Aperturar" href="<%=request.getContextPath()%>/GestionEstrategico?opt=84&idestadoperiodofacultad=<%=es.getIdestadoperiodofacultad()%>"><div class="icon-ok"></div></a></span><%
            } %>
                        
                    </td>
                    <td>
                        <a class="btn btn-small toltipx" title="Eliminar Apertura" href="<%=request.getContextPath()%>/GestionEstrategico?opt=85&idestadoperiodofacultad=<%=es.getIdestadoperiodofacultad()%>"><div class="icon-remove-circle"></div></a>   
                    </td>
                    
                </tr>
             <% }} %>
            </tbody>
      <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="8" style="text-align: center;"><ul id="pagination" class="footable-nav hidden" /></td>
        </tr>
      </tfoot>
    </table>
                </div>
     </div>