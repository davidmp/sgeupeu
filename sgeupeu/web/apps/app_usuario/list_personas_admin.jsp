<%-- 
    Document   : list_personas_admin
    Created on : 29/09/2013, 02:26:42 AM
    Author     : Edwin
--%>
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
            <a href="<%=request.getContextPath()%>/Usuario?opt=31" class="btn" ><div class="icon-plus"></div>&nbsp;Nueva Persona</a>
             </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="7" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th data-hide="phone">
            Nombres y Apellidos 
          </th>
          <th>
           Documento
          </th>
          <th data-hide="phone">
            Sexo
          </th>
          <th data-hide="phone">
            Fecha Nac.
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
           Telefono
          </th>
          <th data-hide="phone,tablet">
            Filial
          </th>
          <th data-hide="phone,tablet">
           opc
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Persona> lista=(List<Persona>) request.getSession().getAttribute("listar_persona");
                        if (lista != null) {
                            for (Persona us : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=us.getNombre()%>&nbsp;<%=us.getApellipaterno()%>&nbsp;<%=us.getApellimaterno()%></td>
                    <td><%=us.getDni()%></td>
                    <td><%=us.getSexo()%></td>
                    <td><%=us.getFechanacimiento()%></td>
                    <td><%=us.getTelefono()%></td>
                    <td><%=us.getIdfilialname()%></td>
                    <td>
            
            
            <a class="btn btn-small toltipx" title="Editar Datos Personales" href="<%=request.getContextPath()%>/Usuario?opt=33&idPersona=<%=us.getIdpersona()%>"><div class="icon-edit"></div></a>
            <a id="eliminar" class="btn btn-small toltipx" title="Desea Realmente Eliminar porque ya no es reversible ??" href="<%=request.getContextPath()%>/Usuario?opt=11&idPersona=<%=us.getIdpersona()%>"><div class="icon-remove"></div></a>          
            
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
