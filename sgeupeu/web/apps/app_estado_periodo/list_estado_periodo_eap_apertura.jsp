<%@page import="sge.modelo.Estadoperiodoeap"%>
<%@page import="sge.modelo.Estadoperiodofacultad"%>
<%@page import="sge.modelo.Estadoperiodofilial"%>
<%@page import="java.util.List"%>
        <script type="text/javascript">
    $(function() {
      $('table').footable();
    });
    
    $(document).ready(function() {
    $("tbody a").click(function() {
    $("#ListaEstadoPeriodoEAP").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#ListaEstadoPeriodoEAP").empty().append(html);
    }
    });
    return false;
    });
});

// add 


$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#ListaEstadoPeriodoEAP").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#ListaEstadoPeriodoEAP").empty().append(html);
    }
    });
    return false;
    });
});
 $('.toltipx').tooltip();
 
 
 var form = $('#addEstadoEAP');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#ListaEstadoPeriodoEAP').show();
$('#ListaEstadoPeriodoEAP').html(data);
document.getElementById("addEstadoEAP").reset();
document.getElementById("ListaEstadoPeriodoEAP").reset(); 

}
});
 
return false;
});



     </script>
     <%
           String idperiodometa = (String)session.getAttribute("IDPeriodosession");
           String idEapFacultad = (String)session.getAttribute("IDEapFacultadSession");
         %>
     <div class="row-fluid">
         <div class="span4">
            <div id="topmenu">
                
                <form action="<%=request.getContextPath()%>/GestionEstrategico" method="POST" name="addEstadoEAP" id="addEstadoEAP">
                    
                    <input type="hidden" name="idperiodometa" value="<%=idperiodometa%>" />
                    <input type="hidden" name="estadometa" value="0" />
                    <input type="hidden" name="estadoavance" value="0" />
                    <input type="hidden" name="idEapFacultad" value="<%=idEapFacultad%>" />
                    <input type="hidden" name="opt" value="95" />
                    <button class="btn toltipx" type="submit" title="Se Aperturará Segun el Periodo, Filial, Facultad, E.A.P Seleccionado : y usted controlará los Estados del Ingreso de Metas y Avances por E.A.P"><div class="icon-plus"></div>&nbsp;Aperturar Nuevo</button>
                    <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=96" class="btn toltipx" title="Actualizar" ><div class="icon-refresh"></div></a>
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
          <th style="width: 23%;">
           Nombre de Escuela Academica Profesional
          </th>
          <th data-hide="phone" style="width: 20%;" >
           Periodo
          </th>
          <th data-hide="phone,tablet" style="width: 15%;">
           Estado Meta
          </th>
          <th data-hide="phone,tablet" style="width: 15%;">
           Estado Avance
          </th>
          <th data-hide="phone,tablet" style="width: 10%;">
           Estado POA
          </th>
          <th data-hide="phone,tablet" style="width: 10%;">
           Estado P.M
          </th>
          <th data-hide="phone,tablet" style="width: 5%;">
            
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Estadoperiodoeap> lista=(List<Estadoperiodoeap>) request.getSession().getAttribute("lista_estado_periodo_eap");
                        if (lista != null) {
                            for (Estadoperiodoeap es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><div class="icon-arrow-right"></div></td>
                    <td><%=es.getIdEapFacultad_nombre()%></td>
                    <td><%=es.getIdperiodometa_nombre() %></td>
                    <td >
                    <%int optmeta=Integer.parseInt(es.getEstadometa());
            if(optmeta==1){
                %> <span class="label label-success span12"><div class="icon-eye-open icon-white"></div>&nbsp;Aperturado <a class="btn btn-small toltipx pull-right" title="Cerrar Apertura" href="<%=request.getContextPath()%>/GestionEstrategico?opt=98&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-remove"></div></a></span> <%
            }else if(optmeta==0){
                %> <span class="label label-inverse span12"><div class="icon-eye-close icon-white"></div>&nbsp;Cerrado <a class="btn btn-small toltipx pull-right" title="Aperturar" href="<%=request.getContextPath()%>/GestionEstrategico?opt=97&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-ok"></div></a></span><%
            } %>
                    
                    </td>
                    <td >
                        <%int optavance=Integer.parseInt(es.getEstadoavance());
            if(optavance==1){
                %> <span class="label label-success span12"><div class="icon-eye-open icon-white"></div>&nbsp;Aperturado <a class="btn btn-small toltipx pull-right" title="Cerrar Apertura" href="<%=request.getContextPath()%>/GestionEstrategico?opt=100&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-remove"></div></a></span> <%
            }else if(optavance==0){
                %> <span class="label label-inverse span12"><div class="icon-eye-close icon-white"></div>&nbsp;Cerrado <a class="btn btn-small toltipx pull-right" title="Aperturar" href="<%=request.getContextPath()%>/GestionEstrategico?opt=99&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-ok"></div></a></span><%
            } %>
                        
                    </td>
                    <td >
                        <%int optpoa=Integer.parseInt(es.getEstadopoa());
                    if(optpoa==1){
                    %> <span class="label label-success span12"><div class="icon-eye-open icon-white"></div>&nbsp;Aperturado <a class="btn btn-small toltipx pull-right" title="Cerrar Apertura" href="<%=request.getContextPath()%>/GestionEstrategico?opt=102&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-remove"></div></a></span> <%
                    }else if(optpoa==0){
                    %> <span class="label label-inverse span12"><div class="icon-eye-close icon-white"></div>&nbsp;Cerrado <a class="btn btn-small toltipx pull-right" title="Aperturar" href="<%=request.getContextPath()%>/GestionEstrategico?opt=103&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-ok"></div></a></span><%
                    } %>
                    </td>
                    <td >
                        <%int optpm=Integer.parseInt(es.getEstadopm());
                    if(optpm==1){
                    %> <span class="label label-success span12"><div class="icon-eye-open icon-white"></div>&nbsp;Aperturado <a class="btn btn-small toltipx pull-right" title="Cerrar Apertura" href="<%=request.getContextPath()%>/GestionEstrategico?opt=104&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-remove"></div></a></span> <%
                    }else if(optpm==0){
                    %> <span class="label label-inverse span12"><div class="icon-eye-close icon-white"></div>&nbsp;Cerrado <a class="btn btn-small toltipx pull-right" title="Aperturar" href="<%=request.getContextPath()%>/GestionEstrategico?opt=105&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-ok"></div></a></span><%
                    } %>
                    </td>
                    <td>
                        <a class="btn btn-small toltipx" title="Eliminar Apertura" href="<%=request.getContextPath()%>/GestionEstrategico?opt=101&idestadoperiodoeap=<%=es.getIdestadoperiodoeap()%>"><div class="icon-remove-circle"></div></a>   
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