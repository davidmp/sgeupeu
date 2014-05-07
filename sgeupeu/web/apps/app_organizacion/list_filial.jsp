<%-- 
    Document   : list_filial
    Created on : 26/08/2013, 10:59:29 PM
    Author     : Edwin
--%>
<%@page import="sge.modelo.Filial"%>
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
            <a href="<%=request.getContextPath()%>/Organizacion?opt=18" class="btn" ><div class="icon-plus"></div>&nbsp;Crear Filial</a>
            
             </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th data-hide="phone">
            Institucion
          </th>
          <th>
            Filial
          </th>
          <th data-hide="phone">
            Telefono
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
            Celular
          </th>
          <th data-hide="phone,tablet">
            email
          </th>
          <th data-hide="phone,tablet">
            Rector(a)
          </th>
          
          <th data-hide="phone,tablet" style="width: 10%;">
            Opciones
          </th>
        </tr>
      </thead>
    <tbody>
                <%
                        int x=0;     
                        List<Filial> lista=(List<Filial>) request.getSession().getAttribute("listar_filial");
                        if (lista != null) {
                            for (Filial fi : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td class="text-info"><%=fi.getIdinstitucion_name()%></td>
                    <td class="text-success"><%=fi.getDireccion()%></td>
                    <td><%=fi.getTelefono()%></td>
                    <td><%=fi.getCelular()%></td>
                    <td><%=fi.getEmail()%></td>
                    <td><%=fi.getRector()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=20&idFilial=<%=fi.getIdfilial()%>" class="btn btn-small toltipx" title="Editar este campo"><div class="icon-edit"></div></a>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=22&idFilial=<%=fi.getIdfilial()%>" class="btn btn-small toltipx" title="Eliminar Filial | se eliminara en caso de que esta filial no tenga relacion con ningun otro modulo  |"><div class="icon-remove"></div></a>
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

