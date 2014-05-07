<%-- 
    Document   : edit_institucion
    Created on : 21/10/2013, 07:18:13 PM
    Author     : Edwin
--%>


<%@page import="sge.modelo.Institucion"%>
<%@page import="sge.modelo.Usuario"%>
<%@page import="sge.modelo.Categoriausuario"%>
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
var form = $('#addInstitucion');
form.submit(function () {

$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('.tab-content').show();
$('.tab-content').html(data);
document.getElementById("addInstitucion").reset();
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
$('.toltipx').tooltip();

 
</script>


    </head>
    <body>
         <%

        Institucion to = null;
        to = (Institucion) request.getSession().getAttribute("listaInstitucionById");

    %> 
        <div class="row-fluid">
		<div class="span12">
	  <form id="addInstitucion" name="addInstitucion" action="<%=request.getContextPath()%>/Organizacion" method="POST">
            <fieldset>
                <legend class="text-success">Editar Datos de la Institución</legend> 
            
            <div class="row-fluid">
				<div class="span3">
                                    <label>Razon Social:</label><input type="text" name="razonsocial" value="<%=to.getRazonsocial()%>" required="required"/> 
                                    <label>Ruc :</label><input type="text" name="ruc" value="<%=to.getRuc()%>" required="required"/>
                                    <label>Direccion :</label><input type="text" name="direccion" value="<%=to.getDireccion()%>" required="required"/> 
				</div>
				<div class="span3">
                                    <label>Telefono :</label><input type="text" name="telefono" value="<%=to.getTelefono()%>" required="required"/>
                                    <label>Celular :</label><input type="text" name="celular" value="<%=to.getCelular()%>" />
                                    <label>Email :</label><input type="text" name="email" value="<%=to.getEmail()%>" />
				</div>
				<div class="span3">
                                    <label>Página Web :</label><input type="text" name="paginaweb" value="<%=to.getPaginaweb()%>" required="required"/>
                                    <label>Descripcion :</label>
                                    <TEXTAREA NAME="descripcion" WRAP="hard" rows="4" cols="20"><%=to.getDescripcion()%></TEXTAREA>
                                </div>
                                <div class="span3">
                                    <label>Rector(a) :</label><input type="text" name="rector" value="<%=to.getRector()%>" />
                                </div>
                        
            </div>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="idinstitucion" value="<%=to.getIdinstitucion()%>"/>      
                        <input type="hidden" name="opt" value="2"/>  
                        <button class="btn" type="submit"><div class="icon-edit"></div>&nbsp;Actualizar</button>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=1" class="btn"><div class="icon-print"></div>&nbsp;Imprimir</a>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=1" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                        
                        </div>
            </fieldset>
        </form>
                     
                       
                       	</div>
	</div>
        
    </body>
</html>



