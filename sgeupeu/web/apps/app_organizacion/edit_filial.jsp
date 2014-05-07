<%-- 
    Document   : edit_filial
    Created on : 26/08/2013, 10:59:53 PM
    Author     : Edwin
--%>


<%@page import="sge.modelo.Filial"%>
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

        Filial to = null;
        to = (Filial) request.getSession().getAttribute("listaFilialById");

    %> 
        <div class="row-fluid">
		<div class="span12">
	  <form id="addInstitucion" name="addInstitucion" action="<%=request.getContextPath()%>/Organizacion" method="POST">
            <fieldset>
                <legend class="text-success">Editar Datos de la Filial</legend> 
            <div class="row-fluid">
				<div class="span3">
                                    <label>Direccion :</label><input type="text" name="direccion" value="<%=to.getDireccion()%>" required="required"/> 
                                    <label>Telefono :</label><input type="text" name="telefono" value="<%=to.getTelefono()%>" required="required"/>
                                    <label>Celular :</label><input type="text" name="celular" value="<%=to.getCelular()%>" required="required"/> 
				</div>
				<div class="span3">
                                    <label>Email :</label><input type="text" name="email" value="<%=to.getEmail()%>" required="required"/>
                                    <label>Descripcion :</label>
                                    <TEXTAREA NAME="descripcion" WRAP="hard" rows="4" cols="20"><%=to.getDescripcion()%></TEXTAREA>
                                    
                                </div>
				<div class="span3">
                                    <label>Categoria :</label>
                                    <select name="categoria">
                                        <option value="<%=to.getCategoria()%>"><%=to.getCategoria()%></option>
                                        <option value="Filial">Filial</option>
                                        <option value="Sede Central">Sede Central</option>
                                    </select>
                                    <label>Rector:</label><input type="text" name="rector" value="<%=to.getRector()%>" required="required"/>
                                    
				</div>
                        
            </div>
             
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="estado" value="<%=to.getEstado()%>"/>          
                        <input type="hidden" name="idinstitucion" value="<%=to.getIdinstitucion()%>"/>      
                        <input type="hidden" name="idFilial" value="<%=to.getIdfilial()%>"/>
                        <input type="hidden" name="opt" value="21"/>  
                        <button class="btn" type="submit"><div class="icon-edit"></div>&nbsp;Actualizar</button>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=5" class="btn"><div class="icon-print"></div>&nbsp;Imprimir</a>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=5" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                        
                        </div>
            </fieldset>
        </form>
                     
                       
                       	</div>
	</div>
        
    </body>
</html>




