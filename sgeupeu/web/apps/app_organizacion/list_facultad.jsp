<%-- 
    Document   : list_facultad
    Created on : 26/08/2013, 11:58:50 PM
    Author     : Edwin
--%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Facultad"%>
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
     </script>
    </head>
    <body>
        <div id="topmenu">
            <a href="<%=request.getContextPath()%>/apps/app_organizacion/add_facultad.jsp" class="btn pull-left" ><div class="icon-plus"></div>&nbsp;Registrar</a>
        </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
         
        <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 4%">
            
            </th> 
            <th style="width: 30%">
            Nombre de Facultades/UPG/Unidad Apoyo
          </th>
          <th data-hide="phone">
            Descripcion
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
                        List<Facultad> lista=(List<Facultad>) request.getSession().getAttribute("listar_facultad");
                        if (lista != null) {
                            for (Facultad fa : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td class="text-info"><%=fa.getNombre()%></td>
                    <td class="text-success"><%=fa.getDescripcion()%></td>
                    <td class="text-success"><%=fa.getTipoarea() %></td>
                    <td>
                        
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=8&idFacultad=<%=fa.getIdfacultad()%>" class="btn btn-small"><div class="icon-edit"></div></a>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=23&idFacultad=<%=fa.getIdfacultad()%>" class="btn btn-small"><div class="icon-remove"></div></a>

                    </td>
                </tr>
             <% }} %>
            </tbody>
      <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="4" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
        </tr>
      </tfoot>
    </table>
    </body>
</html>