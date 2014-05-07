<%-- 
    Document   : list_filial
    Created on : 01/08/2013, 10:03:09 AM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Usuario"%>
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
        <input type="search" name="" value="" id="filteredwinfilial" placeholder="Buscar Usuario Filial.."/>
         <table data-filter="#filteredwinfilial" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th data-hide="phone">
            Nombres y Apellidos
          </th>
          <th>
            Email/Usuario
          </th>
          <th data-hide="phone">
            Filial
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
            Perfil
          </th>
          <th data-hide="phone,tablet">
            Fecha Acceso
          </th>
          <th data-hide="phone,tablet">
            Opciones
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Usuario> lista=(List<Usuario>) request.getSession().getAttribute("listar_usuario_filial");
                        if (lista != null) {
                            for (Usuario us : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=us.getIdpersona_nombre()%>&nbsp;<%=us.getIdpersona_apell_paterno()%>&nbsp;<%=us.getIdpersona_apell_materno()%></td>
                    <td><%=us.getUsuario()%></td>
                    <td><%=us.getIdfilial_nombre()%></td>
                    <td><%=us.getIdcategoriausuario_nombre()%></td>
                    <td><%=us.getFechaAcceso()%></td>
                    <td>
                        <button class="btn btn-small btn-info"><div class="icon-edit icon-white"></div></button>
                        <button class="btn btn-small btn-danger"><div class="icon-trash icon-white"></div></button>
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