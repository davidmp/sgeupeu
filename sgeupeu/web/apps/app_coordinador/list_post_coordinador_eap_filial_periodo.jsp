<%-- 
    Document   : list_post_coordinador_eap_filial_periodo
    Created on : 01/10/2013, 12:21:04 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Coordinadoreap"%>
<%@page import="sge.modelo.Persona"%>
<%@page import="sge.modelo.Coordinadorfacultad"%>
<%@page import="sge.modelo.Usuario"%>
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
$(document).ready(function() {
    $("#topmenu a").click(function() {
    $(".tab-content").empty().append();
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
            String idPer = (String)session.getAttribute("idPeriodoF");
        %>
        
       
        
        
        <div class="row-fluid">
                        <div class="span12"  id="topmenu">
         <form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/Usuario" method="POST">
                  <select name="idpersona" required="" class="pull-left toltipx" title="selecciona una persona registrada">
                  <option></option>
                  <%
                        List<Persona> listaper=(List<Persona>) request.getSession().getAttribute("listaPersonasFilial");
                        if (listaper != null) {
                            for (Persona per : listaper) {

                    %>
                    <option value="<%=per.getIdpersona()%>"><%=per.getNombre()%>&nbsp;<%=per.getApellipaterno()%>&nbsp;<%=per.getApellimaterno()%></option>
                         
                         <%}}%>
                   </select>
                   <input type="hidden" name="idPeriodo" value="<%=idPer%>"/>
                   <input type="hidden" name="opt" value="61" />
                   <button type="submit" class="btn"><div class="icon icon-ok"></div>&nbsp;Guardar</button>
                   
                          </form>         
        
                        </div>
         </div>
       
        <div class="row-fluid">
		<div class="span12">

         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          <tr>
              <td colspan="8">
                  
                                   <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-left"/>
        
              </td>
          </tr>
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 4%;">
                #
            </th> 
          <th>
              Coordinadores de E.A.P/EPG/&Aacute;reas 
          </th>
          <th data-hide="phone">
            DNI Persona
          </th>
          <th data-hide="phone" style="width: 10%">
            Periodo
          </th>
        
          <th data-hide="phone,tablet" style="width: 10%">
            Opc
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Coordinadoreap> lista=(List<Coordinadoreap>) request.getSession().getAttribute("listar_Coordinador_eap");
                        if (lista != null) {
                            for (Coordinadoreap us : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=us.getIdpersona_nombre()%>&nbsp;<%=us.getIdpersona_apellpaterno()%>&nbsp;<%=us.getIdpersona_apellmaterno()%></td>
                    <td><%=us.getIdpersona_dni()%></td>
                    <td><%=us.getIdperiodo_name()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=63&idCoordinadorEap=<%=us.getIdCoordinadorEap()%>" class="btn btn-small"><div class="icon-edit"></div></a>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=62&idCoordinadorEap=<%=us.getIdCoordinadorEap()%>" class="btn btn-small toltipx" title="Desea Realmente Eliminar porque ya no es reversible"><div class="icon-remove"></div></a>
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

