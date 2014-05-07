<%-- 
    Document   : list_eap_facultad
    Created on : 04/10/2013, 08:05:36 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Eapfacultad"%>
<%@page import="sge.modelo.Filialfacultad"%>
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
                 <a href="<%=request.getContextPath()%>/UsuarioApoyo?opt=80" class="btn" ><div class="icon-plus"></div>&nbsp;Crear Direcci&oacute;n de Apoyo</a>
            
             </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 4%;">
                #
            </th> 
           <th>
            Unidad de Apoyo
          </th>
          <th data-hide="phone">
              Direcci&oacute;n de Apoyo
          </th>
          <th data-hide="phone">
            Director de Direcci&oacute;n de Apoyo
          </th>
          <th data-hide="phone,tablet" style="width: 12%;">
            Opc
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Eapfacultad> lista=(List<Eapfacultad>) request.getSession().getAttribute("listaEAPFacultadfilial");
                        if (lista != null) {
                            for (Eapfacultad us : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=us.getIdfilialfacultad_name()%></td>
                    <td><%=us.getIdeap_name()%></td>
                    <td><%=us.getIdcoordinadoreap_name()%>&nbsp;<%=us.getIdcoordinadoreap_apellipaterno()%>&nbsp;<%=us.getIdcoordinadoreap_apellimaterno()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/UsuarioApoyo?opt=99&idEapFacultad=<%=us.getIdeapfacultad()%>" class="btn btn-small toltipx" title="Editar Este Campo"><div class="icon-edit"></div></a>                        
                        <a href="<%=request.getContextPath()%>/UsuarioApoyo?opt=82&idEapFacultad=<%=us.getIdeapfacultad() %>" class="btn btn-small toltipx" title="Desea Realmente Eliminar porque ya no es reversible"><div class="icon-remove"></div></a>
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

