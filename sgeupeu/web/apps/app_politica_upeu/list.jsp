<%-- 
    Document   : list
    Created on : 29/07/2013, 05:52:04 PM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Politicaupeu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script type="text/javascript">
    
         </script>
<script type="text/javascript">
var form = $('#addUsuario');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#contenedor').show();
$('#contenedor').html(data);
document.getElementById("addUsuario").reset();
document.getElementById("contenedor").reset(); 

}
});
 
return false;
});

$(function() {
      $('table').footable();
    });

</script>
    </head>
    <body>
         <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .."/>
         <a data-target="#datosupeulista" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=2">lll </a>
          <form id="addUsuario" name="addUsuario" action="<%=request.getContextPath()%>/GestionEstrategico" method="POST">
            
              <div id="contenedor">
         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th data-hide="phone">
           Mision
          </th>
          <th>
            Vision
          </th>
          <th data-hide="phone,tablet">
            Opc
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Politicaupeu> lista=(List<Politicaupeu>) request.getSession().getAttribute("listar_politica_upeu");
                        if (lista != null) {
                            for (Politicaupeu pu : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=pu.getMision()%></td>
                    <td><%=pu.getVision()%></td>
                    <td>
                           <input type="hidden" name="idpoliticasupeu" value="<%=pu.getIdpoliticasupeu()%>" />
                            <input type="hidden" name="opt" value="3" />
                            <button type="submit" class="btn btn-small btn-success"><div class="icon-edit icon-white"></div></button>
                        
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
            </form>
    </body>
</html>
