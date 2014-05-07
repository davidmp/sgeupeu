<%-- 
    Document   : list_filial_facultad
    Created on : 01/10/2013, 06:38:28 PM
    Author     : Edwin
--%>

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
            <a href="<%=request.getContextPath()%>/Usuario?opt=76" class="btn" ><div class="icon-plus"></div>&nbsp; Crear Facultad</a>
            
             </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
       <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 4%;">
                #
            </th> 
           <th>
            Nombre de Facultad
          </th>
          <th data-hide="phone">
            Coordinador Responsable
          </th>
          <th data-hide="phone">
            Filial Facultad
          </th>
          <th data-hide="phone,tablet" style="width: 10%;">
            Opc
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Filialfacultad> lista=(List<Filialfacultad>) request.getSession().getAttribute("listaFilialFacultadfilial");
                        if (lista != null) {
                            for (Filialfacultad us : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=us.getIdfacultad_nombre()%></td>
                    <td><%=us.getIdcoordinadorfacultad_nombre()%>&nbsp;<%=us.getIdcoordinadorfacultad_apellipaterno()%>&nbsp;<%=us.getIdcoordinadorfacultad_apellimaterno()%></td>
                    <td><%=us.getIdfilial_direccion()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=99&idFilialfacultad=<%=us.getIdfilialfacultad()%>" class="btn btn-small toltipx" title="Editar Este Campo"><div class="icon-edit"></div></a>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=78&idFilialfacultad=<%=us.getIdfilialfacultad()%>" class="btn btn-small toltipx" title="Desea Realmente Eliminar porque ya no es reversible ; Para eliminar este campo deber de eliminar como reponsable de EAP"><div class="icon-remove"></div></a>
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

