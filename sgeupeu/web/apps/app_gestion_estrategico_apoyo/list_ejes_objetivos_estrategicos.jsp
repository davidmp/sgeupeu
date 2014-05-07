<%-- 
    Document   : list_ejes_objetivos_estrategicos
    Created on : 30/08/2013, 01:37:51 AM
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
$('.toltipx').tooltip();

     </script>
     
     <script>
$(function(){
$('[rel=popover]').popover({ 
html : true, 
content: function() {
      return $('#popover_content_wrapper').html();
    }



});

});

</script>
    </head>
    <body>
        
        <div class="row-fluid">
            <div class="span12">

                <button class="btn" id="example"  rel="popover" data-content=""  data-original-title="Registro de Ejes y Objetivos Estrategicos">Registrar</button>     

                
<div id="popover_content_wrapper" style="display: none">
 <form class="form">
                    
				<fieldset>
                                    <label class="text-info"><div class=" icon-minus"></div>Nombre de Eje Estrategico</label>
                                         <TEXTAREA COLS="5" ROWS="5" NAME="nombre" required="required" style="width: 40%;"></TEXTAREA>
                                         <label class="text-info">Descripcion</label>
                                         <TEXTAREA COLS="5" ROWS="5" NAME="nombre" required="required" style="width: 40%;"></TEXTAREA>
                                         <label class="text-info">Nombre de Objetivo Estrategico</label>
                                         <TEXTAREA COLS="5" ROWS="5" NAME="nombre" required="required" style="width: 40%;"></TEXTAREA>
                                         <label class="checkbox">
          <input type="checkbox" value="remember-me"> Activar Eje y Objetivo Estrategico
        </label>
                                         <button type="submit" class="btn">Guardar</button>
				</fieldset>
			</form>
</div>
                
                
                
                <hr>
              
                                    
				
		</div>
                
                
                
                
                <hr>
                
                
            </div>
            
        </div>
        
        
        
        <div id="topmenu">
            
            
        </div>
        <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
         
      <table data-filter="#filteredwin2" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true">
            </th> 
          <th>
            Eje Estratégico
          </th>
          <th data-hide="phone">
           <div class="icon-arrow-right"></div>
          </th>
          <th data-hide="phone,tablet" data-type="numeric">
            Objetivo Estratégico
          </th>
          <th data-hide="phone,tablet">
            Mapa
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
                    <td><button class="btn btn-small toltipx" title="<%=es.getDescripcion()%>"><div class="icon-comment"></div></button></td>
                    <td><%=es.getObjetivoestrategico()%></td>
                    <td><button class="btn btn-small toltipx" title="<%=es.getMapaestrategico()%>"><div class="icon-comment"></div></button></td>
                    <td style="width: 10%;text-align: center;">
                        <button class="btn btn-small toltipx" title="Editar"><div class="icon-edit"></div></button>
                        <button class="btn btn-small toltipx" title="Desea Eliminar"><div class="icon-remove"></div></button>
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