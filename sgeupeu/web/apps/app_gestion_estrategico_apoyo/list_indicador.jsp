<%-- 
    Document   : list_indicador
    Created on : 30/08/2013, 02:37:49 AM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Indicador"%>
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
            <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=15" class="btn btn-info btn-small pull-left" ><div class="icon-plus icon-white"></div>&nbsp;Registrarss</a>
        </div>
        <input type="search" name="" value="" id="filterIndicador" placeholder="Buscar .." class="pull-right"/>
         
      <table data-filter="#filterIndicador" class="table table-bordered table-condensed footable table-hover" data-page-size="7" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                
            </th> 
          <th>
            Nombre de Indicador
          </th>
          <th data-hide="phone">
      
          </th>
          <th data-hide="phone,tablet">
            Nro
          </th>
          <th data-hide="phone,tablet">
            Tipo
          </th>
          <th data-hide="phone,tablet">
            Opciones
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Indicador> lista=(List<Indicador>) request.getSession().getAttribute("listar_indicador");
                        if (lista != null) {
                            for (Indicador es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=es.getNombre()%></td>
                    <td><button class="btn btn-small toltipx" title="<%=es.getDescripcion()%>"><div class="icon-comment" ></div></button></td>
                    <td><%=es.getCodigo() %></td>
                    <td><%=es.getIdtipometa_nombre()%></td>
                    <td style="width: 15%;text-align: center;">
                  <%
            if(es.getEstado()==1){
                %> <a class="btn btn-small toltipx" title="Desactivar Indicador" href="<%=request.getContextPath()%>/apps/persona/data.jsp?action=desactivar&id_persona_txt=<%=es.getIdindicador()%>"><div class="btn-icon-only icon-ok"></div></a> <%
            }else if(es.getEstado()==0){
                %> <a class="btn btn-small toltipx" title="Activar Indicador" href="<%=request.getContextPath()%>/apps/persona/data.jsp?action=activar&id_persona_txt=<%=es.getIdindicador()%>"><div class="btn-icon-only icon-ban-circle"></div></a> <%
            } %>
            <button class="btn btn-small toltipx" title="Editar Datos"><div class="icon-edit"></div></button>
            <button class="btn btn-small toltipx" title="Eliminar este Datos"><div class="icon-remove"></div></button>
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

