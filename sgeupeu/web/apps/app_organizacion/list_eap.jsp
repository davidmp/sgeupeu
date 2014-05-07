<%-- 
    Document   : list_eap
    Created on : 29/08/2013, 08:38:16 PM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Eap"%>
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
            <a href="<%=request.getContextPath()%>/apps/app_organizacion/add_eap.jsp" class="btn pull-left" ><div class="icon-plus"></div>&nbsp;Registrar</a>
        </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
         
        <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 4%;">
                #
            </th> 
            <th style="width: 30%;">
            Nombre de E.A.P/EPG/&Aacute;reas
          </th>
          <th data-hide="phone">
            Descripcion
          </th>
          <th data-hide="phone">
              C&oacute;digo
          </th>
          <th data-hide="phone">
              Tipo &Aacute;rea
          </th>
          <th data-hide="phone,tablet" style="width: 10%;">
            Opciones
          </th>
        </tr>
      </thead>
    <tbody>
                <%
                        int x=0;     
                        List<Eap> lista=(List<Eap>) request.getSession().getAttribute("listar_eap");
                        if (lista != null) {
                            for (Eap fa : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td class="text-info"><%=fa.getNombre()%></td>
                    <td class="text-success"><%=fa.getDescripcion()%></td>
                    <td class="text-success"><%=fa.getCodigo() %></td>
                    <td class="text-success"><%=fa.getTipoarea() %></td>
                    <td>
                        
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=25&idEap=<%=fa.getIdeap()%>" class="btn btn-small toltipx" title="Desea Editar ?"><div class="icon-edit"></div></a>
                        
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=27&idEap=<%=fa.getIdeap()%>" class="btn btn-small toltipx" title="Eliminar : Se Eliminara de la Base de Datos si este no tiene ninguna relacion con otros modulos"><div class="icon-remove"></div></a>
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
    </body>
</html>