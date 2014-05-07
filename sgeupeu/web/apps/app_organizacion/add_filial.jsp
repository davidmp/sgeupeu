<%-- 
    Document   : add_filial
    Created on : 26/08/2013, 10:59:43 PM
    Author     : Edwin
--%>

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
$('.tab-content').show();
$('.tab-content').html(data);
document.getElementById("addPersona").reset();
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
        <div class="row-fluid">
		<div class="span12">
	<form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/Organizacion" method="POST">
            <fieldset>
                <legend class="text-info">Registro de Filial</legend> 
            
            <div class="row-fluid">
				<div class="span3">
                                    <label>Direccion :</label><input type="text" name="direccion" value="" required="required"/> 
                                    <label>Telefono :</label><input type="text" name="telefono" value="" required="required"/>
                                    <label>Celular :</label><input type="text" name="celular" value="" required="required"/> 
				</div>
				<div class="span3">
                                    <label>Email :</label><input type="text" name="email" value="" required="required"/>
                                    <label>Descripcion :</label>
                                    <TEXTAREA NAME="descripcion" WRAP="hard" rows="4" cols="20"></TEXTAREA>
                                    
                                </div>
				<div class="span3">
                                    <label>Categoria :</label>
                                    <select name="categoria">
                                        <option></option>
                                        <option value="Filial">Filial</option>
                                        <option value="Sede Central">Sede Central</option>
                                    </select>
                                    <label>Rector:</label><input type="text" name="rector" value="" required="required"/>
                                    
				</div>
                        
            </div>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="estado" value="1"/>
                        <input type="hidden" name="idinstitucion" value="1"/>
                        <input type="hidden" name="opt" value="19"/>  
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=5" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
    </body>
</html>
