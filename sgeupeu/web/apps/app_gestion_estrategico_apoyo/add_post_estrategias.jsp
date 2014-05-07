<%-- 
    Document   : add_post_estrategias
    Created on : 24/10/2013, 04:44:37 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Perspectiva"%>
<%@page import="sge.modelo.Tipoarea"%>
<%@page import="sge.modelo.Usuario"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<script type="text/javascript">
var form = $('#addEstrategias');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#estrategias').show();
$('#estrategias').html(data);
document.getElementById("addEstrategias").reset();
document.getElementById("estrategias").reset(); 

}
});
 
return false;
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

</script>
    </head>

    <body>
        <div class="row-fluid">
		<div class="span12">
	<form id="addEstrategias" name="addEstrategias" action="<%=request.getContextPath()%>/GestionEstrategicoApoyo" method="POST">     <fieldset>
	    <legend>Registro de Estrategias</legend> 
            <fieldset>
            <div class="row-fluid">
				<div class="span3">
                                     <label>C&oacute;digo:</label>
                                    <input type="number" name="nro" value="" class="span4" />
                                    <label>Nombre de Estrategia :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="nombre" required="required"></TEXTAREA>
                                </div>
                                <div class="span3">
                                    <label>Descripcion :</label>
                                    <TEXTAREA COLS="5" ROWS="8.8" NAME="descripcion" required="required"></TEXTAREA>
                               </div>
				<div class="span3">
                                   <label>Unidad/&Aacute;rea :</label>
                                        <select name="idareaunidad">
                                            <option></option>
                                            <%
                                            List<Tipoarea> listaAreasUnidadX=(List<Tipoarea>) request.getSession().getAttribute("listar_AreasUnidadSessX");
                                            if (listaAreasUnidadX != null) {
                                            for (Tipoarea ta : listaAreasUnidadX) {
                                            %>
                                            <option value="<%=ta.getIdtipoarea() %>"><%=ta.getNombre() %>(<%=ta.getEtiqueta() %>)</option>
                                            <%}}%>
                                        </select>
                                        
                                        <label>Perspectiva :</label>
                                        <select name="idperspectiva">
                                        
                                        <%
                                        List<Perspectiva> listacomboPerspectivaY=(List<Perspectiva>) request.getSession().getAttribute("listar_PerspectivasSessX");
                                        if (listacomboPerspectivaY != null) {
                                        for (Perspectiva cu : listacomboPerspectivaY) {
                                        %>
                                        <option value="<%=cu.getIdperspectiva()%>" title="<%=cu.getDescripcion()%>"><%=cu.getNombre()%></option>
                                        <%}}%>
                                        </select>                                        
                                        <label>Filial :</label>
                                        <select name="idfilial">
                                        
                                        <%
                                        List<Filial> listacomboPerspectivaYX=(List<Filial>) request.getSession().getAttribute("ListarFilialComboX");
                                        if (listacomboPerspectivaY != null) {
                                        for (Filial cu : listacomboPerspectivaYX) {
                                        %>
                                        <option value="<%=cu.getIdfilial() %>" title="<%=cu.getDescripcion()%>"><%=cu.getDireccion() %></option>
                                        <%}}%>
                                        </select>                                        
                                </div>
                                <div class="span3">
                                    
                               </div>
            </div>
            </fieldset>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="58"/>
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=56" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
    </body>
    
</html>

