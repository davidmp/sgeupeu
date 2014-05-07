<%-- 
    Document   : list_usuario_periodo
    Created on : 01/08/2013, 01:16:31 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Periodo"%>
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
        <form method="POST" action="<%=request.getContextPath()%>/Usuario" name="dd" >
            
               <blockquote>
         <%
             List<Periodo> listaper=(List<Periodo>) request.getSession().getAttribute("listar_periodo");
                        if (listaper != null) {
                            for (Periodo periodo : listaper) {

                       
                    %>
                    <p class="text-info">
                        El Periodo que se Aperturo desde la Central es .<%=periodo.getPeriodo()%><input style="visibility: hidden;display: none;" type="text" name="idperiodo" value="<%=periodo.getIdperiodo()%>"/>
		    </p> <small>Selecciona a tus Usuarios para un nuevo Periodo&nbsp;<%=periodo.getFechainicio()%>&nbsp;- Hasta -&nbsp;<%=periodo.getFechafin()%></small>
                  
                   <%}}%>
                                
			</blockquote>
        
     <input type="search" name="" value="" id="filteredwinfilialperiodo" placeholder="Buscar Usuario Filial.."/>
     <table data-filter="#filteredwinfilialperiodo" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
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
            Ckeck
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
                    <td><%=us.getFechaAcceso()%><input type="text" name="idfilial" value="<%=us.getIdfilial()%>" style="visibility: hidden;display: none;"/></td>
                    <td style="text-align: center;">
                        <input type="checkbox" name="idpersona" value="<%=us.getIdpersona()%>" />
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
            <div style="text-align: center;width: 100%;">
            <input type="hidden" name="opt" value="28"/> 
            <input type="submit" value="Guardar" class="btn btn-small btn-success" />
            <input type="reset" value="Cancelar" class="btn btn-small btn-warning" />
            <div>
        </form>
    </body>
</html>
