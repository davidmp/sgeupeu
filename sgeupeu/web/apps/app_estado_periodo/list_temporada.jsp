<%@page import="java.util.List"%>
<%@page import="sge.modelo.Temporada"%>
        <script type="text/javascript">
    $(function() {
      $('table').footable();
    });
    
    $(document).ready(function() {
    $("tbody a").click(function() {
    $("#reportTemporada").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#reportTemporada").empty().append(html);
    }
    });
    return false;
    });
});

// add 


$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#reportTemporada").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#reportTemporada").empty().append(html);
    }
    });
    return false;
    });
});
     </script>
     
     <div class="row-fluid">
         <div class="span3">
        <div id="topmenu">
            <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=26" class="btn" ><div class="icon-plus"></div>&nbsp;Registrar</a>
        </div>
             
         </div>
         <div class="span3">
             
         </div>
         <div class="span3">
             
         </div>
         <div class="span3">
      <input type="search" name="" value="" id="filterTemporada" placeholder="Buscar .." class="pull-right"/>
               
         </div>
     </div>
     <div class="row-fluid">
		<div class="span12">
      <table data-filter="#filterTemporada" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
                #
            </th> 
          <th>
           Fecha Inicio
          </th>
          <th data-hide="phone">
           Fecha Fin
          </th>
          <th data-hide="phone,tablet">
           Descripcion
          </th>
          <th data-hide="phone,tablet">
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
                        List<Temporada> lista=(List<Temporada>) request.getSession().getAttribute("listarTemporada");
                        if (lista != null) {
                            for (Temporada es : lista) {

                    %>
                <tr class="<% if(x%2==1){out.println("success");}%>">
                    <td><%=++x%></td>
                    <td><%=es.getInicio()%></td>
                    <td><%=es.getFin()%></td>
                    <td><%=es.getDescripcion()%></td>
                    <td style="width: 10%;text-align: center;"><%
            int op_estado = Integer.parseInt(es.getEstado());
            if(op_estado==1){
            %> <span class="label label-success">Activado</span>  <%
            }else if(op_estado==0){
            %> <span class="label label-inverse">Desactivo</span><%
            } %></td>
                    <td style="width: 15%;text-align: center;">
                  <%
            int opt = Integer.parseInt(es.getEstado());
            if(opt==1){
            %> <a class="btn btn-small text-info" href="<%=request.getContextPath()%>/GestionEstrategico?opt=20&&idTemporada=<%=es.getIdtemporada()%>"><div class="btn-icon-only icon-ban-circle"></div></a> <%
            }else if(opt==0){
            %> <a class="btn btn-small text-warning" href="<%=request.getContextPath()%>/GestionEstrategico?opt=19&&idTemporada=<%=es.getIdtemporada()%>"><div class="btn-icon-only icon-ok"></div></a> <%
            } %>
                        
            <a class="btn btn-small" href="<%=request.getContextPath()%>/GestionEstrategico?opt=63&idTemporada=<%=es.getIdtemporada()%>"><div class="icon-edit"></div></a>
            <a class="btn btn-small" href="<%=request.getContextPath()%>/GestionEstrategico?opt=65&idTemporada=<%=es.getIdtemporada()%>"><div class="icon-remove"></div></a>
                        
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
     </div>