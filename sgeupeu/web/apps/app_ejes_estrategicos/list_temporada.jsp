<%-- 
    Document   : list_temporada
    Created on : 30/07/2013, 06:16:04 PM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Temporada"%>
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
       </script>
     
    </head>
    <body>
        <blockquote> 
            <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." style="margin: 3px 2px;"/>
       <button class="btn btn-small btn-info"><div class="icon-list-alt icon-white"></div></button>
       <button class="btn btn-small btn-success"><div class="icon-plus icon-white"></div></button>
        </blockquote>
        <div id="caja"> 
       <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th>
            Inicio
          </th>
          <th data-hide="phone">
            Descripcion
          </th>
          <th data-hide="phone">
            Fin
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
            Estado
          </th>
          <th data-hide="phone,tablet">
            Opciones
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Temporada> lista=(List<Temporada>) request.getSession().getAttribute("listar_temporada");
                        if (lista != null) {
                            for (Temporada te : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=te.getInicio()%></td>
                    <td><%=te.getDescripcion()%></td>
                    <td><%=te.getFin()%></td>
                    <td><%=te.getEstado()%></td>
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
        </div>
    </body>
</html>

