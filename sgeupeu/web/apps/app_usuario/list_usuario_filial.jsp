<%-- 
    Document   : list_usuario_filial
    Created on : 19/08/2013, 07:35:35 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Usuario"%>
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
            <a href="<%=request.getContextPath()%>/Usuario?opt=72" class="btn" ><div class="icon-plus"></div>&nbsp;Crear. Cuenta</a>
            <a href="<%=request.getContextPath()%>/Usuario?opt=44" class="btn" ><div class="icon-plus"></div>&nbsp;Reg. Usuario </a>
             </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
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
            Opciones
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Usuario> lista=(List<Usuario>) request.getSession().getAttribute("listaUserFilial");
                        if (lista != null) {
                            for (Usuario us : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=us.getIdpersona_nombre()%>&nbsp;<%=us.getIdpersona_apell_paterno()%>&nbsp;<%=us.getIdpersona_apell_materno()%></td>
                    <td><%=us.getUsuario()%></td>
                    <td><%=us.getIdfilial_nombre()%></td>
                    <td><%=us.getIdcategoriausuario_nombre()%></td>
                    <td>
                       
            <a class="btn btn-small toltipx" title="Editar Datos del Usuario y Acceso al Sistema" href="<%=request.getContextPath()%>/Usuario?opt=70&idUsuario=<%=us.getIdusuario()%>"><div class="icon-user"></div></a>
            <a class="btn btn-small toltipx" title="Editar Datos Personales" href="<%=request.getContextPath()%>/Usuario?opt=68&idPersona=<%=us.getIdpersona()%>"><div class="icon-edit"></div></a>
            <a class="btn btn-small toltipx" title="Desea Eliminar a este Usuario Porque ya no es reversible" href="<%=request.getContextPath()%>/Usuario?opt=49&idUsuario=<%=us.getIdusuario()%>"><div class="icon-remove"></div></a>          
                    
                     
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
            </div>
	</div>
    </body>
</html>

