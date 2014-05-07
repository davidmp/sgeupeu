<%-- 
    Document   : add_post_eje_objetivo
    Created on : 24/10/2013, 10:43:51 AM
    Author     : Edwin
--%>

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
    $("#reportar").empty().append();
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
        <div class="row-fluid">
		<div class="span12">
	<form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/GestionEstrategico" method="POST">     <fieldset>
	    <legend>Registro de Ejes y Objetivos Estrategicos</legend> 
            <fieldset>
            <div class="row-fluid">
				<div class="span3">
                                    <label>Eje Estrategico :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="nombre" required="required"></TEXTAREA>
                                </div>
                                <div class="span3">
                                    <label>Descripcion :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="descripcion" required="required"></TEXTAREA>
                               </div>
				<div class="span3">
                                    <label>Objetivo Estrategico :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="objetivoestrategico" required="required"></TEXTAREA>
                                    </div>
                                <div class="span3">
                                    <label>Mapa Estrategico:</label>
                                    <input type="text" name="mapaestrategico" value="" />
                               </div>
            </div>
            </fieldset>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="36"/>
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=35" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
    </body>
    
</html>

