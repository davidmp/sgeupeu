<%-- 
    Document   : edit_post_estrategias
    Created on : 24/10/2013, 05:21:25 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Estrategia"%>
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
        
         <%
            Estrategia est = null;
            est = (Estrategia) request.getSession().getAttribute("listaEstrategiaById");
        %> 
        <div class="row-fluid">
		<div class="span12">
	<form id="addEstrategias" name="addEstrategias" action="<%=request.getContextPath()%>/GestionEstrategicoApoyo" method="POST">     <fieldset>
	    <legend>Actualizacion de Estrategias</legend> 
            <fieldset>
            <div class="row-fluid">
				<div class="span3">
                                    <label>Nombre de Estrategia :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="nombre" required="required"><%=est.getNombre()%></TEXTAREA>
                                </div>
                                <div class="span3">
                                    <label>Descripcion :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="descripcion" required="required"><%=est.getDescripcion()%></TEXTAREA>
                               </div>
				<div class="span3">
                                    <label>Nro:</label>
                                    <input type="number" name="nro" value="<%=est.getNro()%>" class="span4" />
                                    </div>
                                <div class="span3">
                                    
                               </div>
            </div>
            </fieldset>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="idEstrategia" value="<%=est.getIdestrategia()%>"/>
                        <input type="hidden" name="opt" value="60"/>
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

