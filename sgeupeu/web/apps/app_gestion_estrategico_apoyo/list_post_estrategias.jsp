<%-- 
    Document   : list_post_estrategias
    Created on : 14/10/2013, 01:24:30 PM
    Author     : Edwin
--%>
<%@page import="sge.modelo.Estrategia"%>
<%@page import="sge.modelo.EjeEstrategia"%>
<%@page import="sge.modelo.Persona"%>
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
    $("#estrategias").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#estrategias").empty().append(html);
    }
    });
    return false;
    });
});
$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#estrategias").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#estrategias").empty().append(html);
    }
    });
    return false;
    });
});
 $('.toltipx').tooltip();
 
 
 
 var form = $('#addPersonaccc');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#estrategias').show();
$('#estrategias').html(data);
document.getElementById("addPersonaccc").reset();
document.getElementById("estrategias").reset(); 

}
});
 
return false;
});


</script>



    </head>
    <body>
        <%
        String var = (String)session.getAttribute("idtemporadaejeestrategicoSessionID");
        %>
         <div class="row-fluid">
             
             <legend><h4>Registro de <strong class="text-success">Estrategias</strong> segun el <strong class="text-info">Eje Estratégico</strong> Seleccionado</h4></legend>
             
             <form method="POST" action="<%=request.getContextPath()%>/GestionEstrategicoApoyo" name="addPersonaccc" id="addPersonaccc">
             <div class="span4">
                 
                 <input type="text" name="idtemporadaejeestrategico" value="<%=var%>" class="hidden-desktop" />
                 <label><strong>Nro y Estrategias</strong></label>                 
                 <select name="idestrategia" class="toltipx" title="selecciona una Estrategia para Registrar EJE -> ESTRATEGIA" required="">
                     <option></option>
                     <%
                        List<Estrategia> listacombo=(List<Estrategia>) request.getSession().getAttribute("listar_estrategia");
                        if (listacombo != null) {
                            for (Estrategia estra : listacombo) {

                    %>
                    <option value="<%=estra.getIdestrategia()%>"><%=estra.getNombre()%></option>
                     <%}}%>
                 </select>
                 
             </div>
                 <div class="span3" id="topmenu">
                     <label style="visibility: hidden;"><strong>opciones</strong></label>      
                 <input type="hidden" name="opt" value="44" />
                 <input type="submit" value="Guardar" class="btn toltipx" title="Guardar Datos" />
                 <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=56" class="btn toltipx" title="Registrar Nueva Estrategia"><div class="icon-wrench"></div>&nbsp;Configuracion</a>
                 
             </div>
             </form>
             
             <div class="span5">
                      <label style="visibility: hidden;"><strong>opciones</strong></label>      
                
                  <input type="search" name="" value="" id="filteredwin" placeholder="Buscar .." class="pull-right"/>
        
             </div>
         </div>
             
              
        <div class="row-fluid">
		<div class="span12">

         <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="7" >
        
        
      <thead>
          
          
        <tr>
            <th data-class="expand" data-sort-initial="true" style="width: 5%;">
                Nro
            </th>
            <th data-hide="phone" style="color: #3a87ad;width: 30%;">
           Eje Estratégico
          </th>  
          <th style="color: #468847;">
           Estrategias
          </th>
          <th  data-hide="phone" style="width: 3%;">
           
          </th>
          <th data-hide="phone,tablet" style="width: 3%;">
           
          </th>
        </tr>
      </thead>
      <tbody>
                <%
                     
                        List<EjeEstrategia> lista=(List<EjeEstrategia>) request.getSession().getAttribute("Listar_Estrategias_eje");
                        if (lista != null) {
                            for (EjeEstrategia es : lista) {

                    %>
                <tr>
                    <td><%=es.getNro()%></td>
                    <td style="background-color: #3a87ad;color: white;width: 30%;"><%=es.getIdtemporadaejenombre()%></td>
                    <td style="background-color: #468847;color: white;"><%=es.getIdestrategia_nombre()%></td>
                    <td><button class="btn btn-small toltipx" title="Descripcion Estrategia : <%=es.getIdestrategia_descripcion()%>"><div class="icon-comment"></div></button></td>
                    <td>
                        <a class="btn btn-small toltipx" title="Desea Eliminar<%=es.getIdEjeEstrategia()%>" href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=45&idEjeEstrategia=<%=es.getIdEjeEstrategia()%>"><div class="icon-remove"></div></a>
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
    </body>
</html>
