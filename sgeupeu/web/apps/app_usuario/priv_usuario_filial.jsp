<%-- 
    Document   : priv_usuario_filial
    Created on : 29/09/2013, 10:26:30 PM
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
            <a href="<%=request.getContextPath()%>/Usuario?opt=21" class="btn" ><div class="icon-plus"></div>&nbsp;Asig Privilegio</a>
            
             </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="7" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 4%;">
                #
            </th> 
            <th data-hide="phone" style="width: 4%;">
            
          </th>
          <th style="width: 27%;">
            Nombres y Apellidos 
          </th>
          <th data-hide="phone,tablet" data-type="numeric" style="width: 20%;">
            Perfil
          </th>
          <th data-hide="phone,tablet">
            Privilegios 
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Usuario> lista=(List<Usuario>) request.getSession().getAttribute("listaUserFilialpriv");
                        if (lista != null) {
                            for (Usuario us : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><div class="btn btn-small toltipx" title="<%=us.getUsuario()%>"><div class="icon-user"></div></div></td>
                    <td><%=us.getIdpersona_nombre()%>&nbsp;<%=us.getIdpersona_apell_paterno()%>&nbsp;<%=us.getIdpersona_apell_materno()%></td>
                    <td><%=us.getIdcategoriausuario_nombre()%></td>
                    <td>
            <%int opt=Integer.parseInt(us.getEstado());
            if(opt==1){
                %> <a class="btn btn-small toltipx" title="Desactivar Cuenta" href="<%=request.getContextPath()%>/Usuario?opt=49&idUsuario=<%=us.getIdusuario()%>"><div class="btn-icon-only icon-ok"></div></a> <%
            }else if(opt==0){
                %> <a class="btn btn-small toltipx" title="Activar Cuenta" href="<%=request.getContextPath()%>/Usuario?opt=29&idUsuario=<%=us.getIdusuario()%>"><div class="btn-icon-only icon-ban-circle"></div></a> <%
            } %> 
                    
                <a class="btn btn-small toltipx" title="Ver Acceso de Escuelas Academicas" href=""><div class="icon-random"></div></a>
                <a class="btn btn-small toltipx" title="Ver Acceso de Escuelas Academicas" href=""><div class=" icon-wrench"></div></a>
                <a class="btn btn-small toltipx" title="Ver Acceso de Escuelas Academicas" href=""><div class=" icon-bullhorn"></div></a>
                <a class="btn btn-small toltipx" title="Ver Acceso de Escuelas Academicas" href=""><div class="icon-download-alt"></div></a>
                <a class="btn btn-small toltipx" title="Ver Acceso de Escuelas Academicas" href=""><div class="icon-lock"></div></a>
                <a class="btn btn-small toltipx" title="Ver Acceso de Escuelas Academicas" href=""><div class="icon-time"></div></a>
                    
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

