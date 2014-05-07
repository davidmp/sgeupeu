<%-- 
    Document   : list_estado_periodo_filial
    Created on : 13/09/2013, 04:10:46 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Estadoperiodofilial"%>
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
            <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=30" class="btn btn-info btn-small pull-left" ><div class="icon-plus icon-white"></div>&nbsp;Aperturar Nuevo</a>
        </div>
        <input type="search" name="" value="" id="filterTemporada" placeholder="Buscar .." class="pull-right"/>
         
      <table data-filter="#filterTemporada" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th>
           Periodo 
          </th>
          <th data-hide="phone" colspan="2">
           Estado de Meta
          </th>
          <th data-hide="phone,tablet" colspan="2">
           Estado de Avance
          </th>
          <th data-hide="phone,tablet">
          Filial
          </th>
          <th data-hide="phone,tablet">
            Opciones
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                        int x=0;     
                        List<Estadoperiodofilial> lista=(List<Estadoperiodofilial>) request.getSession().getAttribute("ListarEstadoPeriodoFilial");
                        if (lista != null) {
                            for (Estadoperiodofilial es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=es.getIdperiodometa()%></td>
                    <td><%
                     int estadometa = Integer.parseInt(es.getEstadometa());
            if(estadometa==1){
            %> <span class="label label-success">Activado</span>  <%
            }else if(estadometa==0){
            %> <span class="label label-inverse">Desactivo</span><%
            } 
                    %>
                    
                    </td>
                    <td style="width: 5%;text-align: center;">
                  <%
            int opt = Integer.parseInt(es.getEstadometa());
            if(opt==1){
            %> <a class="btn btn-small text-info" href="<%=request.getContextPath()%>/GestionEstrategico?opt=20&&idTemporada=<%=es.getIdestadoperiodofilial()%>"><div class="btn-icon-only icon-ban-circle"></div></a> <%
            }else if(opt==0){
            %> <a class="btn btn-small text-warning" href="<%=request.getContextPath()%>/GestionEstrategico?opt=19&&idTemporada=<%=es.getIdestadoperiodofilial()%>"><div class="btn-icon-only icon-ok"></div></a> <%
            } %>
                    </td>
                    <td><%
                     int estadoavance = Integer.parseInt(es.getEstadoavance());
            if(estadoavance==1){
            %> <span class="label label-success">Activado</span>  <%
            }else if(estadoavance==0){
            %> <span class="label label-inverse">Desactivo</span><%
            } 
                    %></td>
                    <td style="width: 5%;text-align: center;">
                  <%
            int optava = Integer.parseInt(es.getEstadoavance());
            if(optava==1){
            %> <a class="btn btn-small text-info" href="<%=request.getContextPath()%>/GestionEstrategico?opt=20&&idTemporada=<%=es.getIdestadoperiodofilial()%>"><div class="btn-icon-only icon-ban-circle"></div></a> <%
            }else if(optava==0){
            %> <a class="btn btn-small text-warning" href="<%=request.getContextPath()%>/GestionEstrategico?opt=19&&idTemporada=<%=es.getIdestadoperiodofilial()%>"><div class="btn-icon-only icon-ok"></div></a> <%
            } %>
                    </td>
                    <td><%=es.getIdFilial()%></td>
                  <td>
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
    </body>
</html>