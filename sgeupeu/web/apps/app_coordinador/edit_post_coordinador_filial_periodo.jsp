<%-- 
    Document   : edit_post_coordinador_filial_periodo
    Created on : 30/09/2013, 06:23:12 PM
    Author     : Edwin
--%>


<%@page import="sge.modelo.Coordinadorfacultad"%>
<%@page import="sge.modelo.Periodo"%>
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
var form = $('#addPersona');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#reportar').show();
$('#reportar').html(data);
document.getElementById("addPersona").reset();
document.getElementById("reportar").reset(); 

}
});
 
return false;
});

$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#reportart").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#reportar").empty().append(html);
    }
    });
    return false;
    });
});

</script>
    </head>
    <body>
        <%

        Coordinadorfacultad to = null;
        to = (Coordinadorfacultad) request.getSession().getAttribute("listaCoordinadorFacultadById");

    %>
        <div class="row-fluid">
		<div class="span12">
	<form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/Usuario" method="POST">
            <fieldset>
	    <legend>Actualizacion de Coordinador Facultad Filial</legend> 
            
            <div class="row-fluid">
				<div class="span3">
                                    <label>Persona :</label>
                                    <select name="idpersona" required="" class="toltipx" title="selecciona una persona registrada">
                                        <option value="<%=to.getIdpersona()%>"><%=to.getIdpersona_nombre()%>&nbsp;<%=to.getIdpersona_apellpaterno()%>&nbsp;<%=to.getIdpersona_apellmaterno()%></option>
                         <%
                        List<Persona> listaper=(List<Persona>) request.getSession().getAttribute("listaPersonasFilial");
                        if (listaper != null) {
                            for (Persona per : listaper) {

                    %>
                    <option value="<%=per.getIdpersona()%>"><%=per.getNombre()%>&nbsp;<%=per.getApellipaterno()%>&nbsp;<%=per.getApellimaterno()%></option>
                         
                         <%}}%>
                         
                     </select>
                                    <label>Periodo :</label><select name="idPeriodo" class="toltipx" title="Selecciona un Periodo para Listar y Registrar los Coordinadores de Facultad" style="width: 50%;">
                                        <option value="<%=to.getIdperiodo()%>"><%=to.getIdperiodo_name()%></option>
                    <% List<Periodo> lista=(List<Periodo>) request.getSession().getAttribute("listar_periodo");
                        if (lista != null) {
                            for (Periodo es : lista) { %>
                  <option value="<%=es.getIdperiodo()%>"><%=es.getPeriodo()%></option>
            <%}}%>
                </select>
        
                                    
				</div>
				<div class="span3">
                                </div>
				<div class="span3">
                                </div>
                        
            </div>
                        <hr>
                        <div id="topmenu">
                            <input type="hidden" name="idCoordinadorFacultad" value="<%=to.getIdcoordinadorfacultad()%>"/>      
                        <input type="hidden" name="opt" value="58"/>  
                        <input type="submit" value="Actualizar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=57" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                       
                       
                       	</div>
	</div>
        
    </body>
</html>
