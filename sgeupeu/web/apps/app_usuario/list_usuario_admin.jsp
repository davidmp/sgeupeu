<%-- 
    Document   : list_usuario_admin
    Created on : 19/08/2013, 07:35:07 PM
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
            <a href="<%=request.getContextPath()%>/Usuario?opt=21" class="btn" ><div class="icon-plus"></div>&nbsp;Crear Cuenta</a>
            <a href="<%=request.getContextPath()%>/Usuario?opt=32" class="btn" ><div class="icon-plus"></div>&nbsp;Reg. Usuario </a>
             </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="7" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th>
            <th data-hide="phone,tablet" style="text-align: center;width: 3%;">
                
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
            <th data-hide="phone,tablet">
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
                        List<Usuario> lista=(List<Usuario>) request.getSession().getAttribute("listar_usuario");
                        if (lista != null) {
                            for (Usuario us : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td>
                        <%int estado=Integer.parseInt(us.getEstado());
            if(estado==1){
                %> <span class="label label-success toltipx" title="Usuario Activo" ><div class="icon-ok icon-white"></div></span><%
            }else if(estado==0){
                %> <span class="label label-inverse toltipx" title="Usuario Desactivo" ><div class="icon-remove icon-white"></div></span><%
            } %>
            
                        
                    
                    </td>
                    <td><%=us.getIdpersona_nombre()%>&nbsp;<%=us.getIdpersona_apell_paterno()%>&nbsp;<%=us.getIdpersona_apell_materno()%></td>
                    <td><%=us.getUsuario()%></td>
                    <td><%=us.getIdfilial_nombre()%></td>
                    <td><%=us.getIdcategoriausuario_nombre()%></td>
                    
                    
                    <td style="text-align: center;">
            <%int opt=Integer.parseInt(us.getEstado());
            if(opt==1){
                %> <a class="btn btn-small toltipx" title="Desactivar Cuenta" href="<%=request.getContextPath()%>/Usuario?opt=30&idUsuario=<%=us.getIdusuario()%>"><div class="btn-icon-only icon-ok"></div></a> <%
            }else if(opt==0){
                %> <a class="btn btn-small toltipx" title="Activar Cuenta" href="<%=request.getContextPath()%>/Usuario?opt=29&idUsuario=<%=us.getIdusuario()%>"><div class="btn-icon-only icon-ban-circle"></div></a> <%
            } %>
            
            <a class="btn btn-small toltipx" title="Editar Datos del Usuario y Acceso al Sistema" href="<%=request.getContextPath()%>/Usuario?opt=35&idUsuario=<%=us.getIdusuario()%>"><div class="icon-user"></div></a>
            <a class="btn btn-small toltipx" title="Editar Datos Personales" href="<%=request.getContextPath()%>/Usuario?opt=37&idPersona=<%=us.getIdpersona()%>"><div class="icon-edit"></div></a>
            <a class="btn btn-small toltipx" title="Desea Realmente Eliminar porque ya no es reversible ??" href="<%=request.getContextPath()%>/Usuario?opt=3&idUsuario=<%=us.getIdusuario()%>"><div class="icon-remove"></div></a>          
            
                    </td>
                </tr>
             <% }} %>
            </tbody>
      <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="7" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
        </tr>
      </tfoot>
    </table>
            </div>
	</div>
    </body>
</html>