<%-- 
    Document   : list_estrategia
    Created on : 30/08/2013, 02:05:57 AM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Estrategia"%>
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

// add 


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
        <div id="topmenu">
            <a href="<%=request.getContextPath()%>/apps/app_organizacion/add_facultad.jsp" class="btn btn-info btn-small pull-left" ><div class="icon-plus icon-white"></div>&nbsp;Registrar</a>
        </div>
        <input type="search" name="" value="" id="filterestrategia" placeholder="Buscar .." class="pull-right"/>
         
      <table data-filter="#filterestrategia" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th>
            Nombre Estrategia
          </th>
          <th data-hide="phone">
            Descripcion
          </th>
          <th data-hide="phone,tablet">
            Nro
          </th>
          <th data-hide="phone,tablet">
            Opciones
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Estrategia> lista=(List<Estrategia>) request.getSession().getAttribute("listar_estrategia");
                        if (lista != null) {
                            for (Estrategia es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=es.getNombre()%></td>
                    <td><%=es.getDescripcion() %></td>
                    <td><%=es.getNro()%></td>
                    <td style="width: 10%;text-align: center;">
                        <button class="btn btn-small btn-info"><div class="icon-edit icon-white"></div></button>
                        <button class="btn btn-small btn-danger"><div class="icon-trash icon-white"></div></button>
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
    </body>
</html>
