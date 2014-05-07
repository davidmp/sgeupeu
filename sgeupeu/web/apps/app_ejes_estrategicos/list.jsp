<%-- 
    Document   : list
    Created on : 30/07/2013, 04:52:46 PM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Ejeestrategico"%>
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
            <input type="search" name="" value="" id="filteredwin2" placeholder="Buscar .." style="margin: 3px 2px;"/>
       <button class="btn btn-small btn-info"><div class="icon-list-alt icon-white"></div></button>
       <button class="btn btn-small btn-success"><div class="icon-plus icon-white"></div></button>
        </blockquote>
        <div id="caja"> 
       <table data-filter="#filteredwin2" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th>
            Nombre
          </th>
          <th data-hide="phone">
            Descripcion
          </th>
          <th data-hide="phone">
            Estado
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
            Objetivo Estrategico
          </th>
          <th data-hide="phone,tablet">
            Mapa
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
                        List<Ejeestrategico> lista=(List<Ejeestrategico>) request.getSession().getAttribute("listar_ejes_estrategicos");
                        if (lista != null) {
                            for (Ejeestrategico es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=es.getNombre()%></td>
                    <td><%=es.getDescripcion() %></td>
                    <td><%=es.getEstado()%></td>
                    <td><%=es.getObjetivoestrategico()%></td>
                    <td><%=es.getMapaestrategico()%></td>
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
        </div>
    </body>
</html>
