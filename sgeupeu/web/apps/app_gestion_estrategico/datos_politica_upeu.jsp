<%-- 
    Document   : datos_politica_upeu
    Created on : 14/10/2013, 09:32:47 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Politicaupeu"%>
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
var form = $('#editPersona');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('.tab-content').show();
$('.tab-content').html(data);
document.getElementById("editPersona").reset();
document.getElementById("tab-content").reset(); 

}
});
 
return false;
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

</script>
    </head>
    <body>
         <%

        Politicaupeu po = null;
        po = (Politicaupeu) request.getSession().getAttribute("listaPoliticasUPeUById");

    %> 
        <div class="row-fluid">
		<div class="span12">
	<form id="editPersona" name="editPersona" action="<%=request.getContextPath()%>/GestionEstrategico" method="POST">
            <fieldset>
	    <legend>Editar Datos de Mision y Vision</legend> 
            
            <div class="row-fluid">
				<div class="span3">
                                    <label>Mision :</label>
                                    <textarea name="mision" rows="4" cols="20"><%=po.getMision()%></textarea>
                                </div>
				<div class="span3">
                                    <label>Vision:</label>
                                    <textarea name="vision" rows="4" cols="20"><%=po.getVision()%></textarea>
                                </div>
				<div class="span3">
                                </div>
                        
            </div>
                        <hr>
                        <input type="hidden" name="idpoliticasupeu" value="<%=po.getIdpoliticasupeu()%>"/>
                        <input type="hidden" name="estado" value="1"/>
                        
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="2"/>
                        <input type="submit" value="Actualizar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=1" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
    </body>
</html>

