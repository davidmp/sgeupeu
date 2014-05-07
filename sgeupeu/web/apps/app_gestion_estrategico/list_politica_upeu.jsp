<%-- 
    Document   : list_politica_upeu
    Created on : 30/08/2013, 12:43:48 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Politicaupeu"%>
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
    <table data-filter="#filterestrategia" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
      <thead>
        <tr>
          <th>
            Mision
          </th>
          <th data-hide="phone">
            Vision
          </th>
          <th data-hide="phone,tablet">
           
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        
                        List<Politicaupeu> lista=(List<Politicaupeu>) request.getSession().getAttribute("listar_politica_upeu");
                        if (lista != null) {
                            for (Politicaupeu pu : lista) {

                    %>
                <tr>
                    <td><%=pu.getMision()%></td>
                    <td><%=pu.getVision()%></td>
                    <td style="width: 5%;text-align: center;">
                    <a class="btn btn-small toltipx" href="<%=request.getContextPath()%>/GestionEstrategico?opt=3&idpoliticasupeu=<%=pu.getIdpoliticasupeu()%>" title="Editar Datos"><div class="icon-edit"></div></a>
                    </td>
                </tr>
             <% }} %>
            </tbody>
      <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="8" style="text-align: center;"></td>
        </tr>
      </tfoot>
    </table>
    </body>
</html>

