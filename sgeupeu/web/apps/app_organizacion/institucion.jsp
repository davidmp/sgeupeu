<%-- 
    Document   : institucion
    Created on : 26/08/2013, 10:33:36 PM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Institucion"%>

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
        
        <%  List<Institucion> lista=(List<Institucion>) request.getSession().getAttribute("listar_datos_institucion");
                        int i = 0;
                        if (lista != null) {
                            for(Institucion to : lista) {
        %>
        <div class="row-fluid">
		<div class="span12">
	     <fieldset>
                 <legend class="text-info">Datos de la Institución</legend> 
            
            <div class="row-fluid">
				<div class="span3">
                                    <label>Razon Social:</label><input type="text" name="" value="<%=to.getRazonsocial()%>" required="required" readonly="readonly"/> 
                                    <label>Ruc :</label><input type="text" name="" value="<%=to.getRuc()%>" required="required" readonly="readonly"/>
                                    <label>Direccion :</label><input type="text" name="" value="<%=to.getDireccion()%>" required="required" readonly="readonly"/> 
				</div>
				<div class="span3">
                                    <label>Telefono :</label><input type="text" name="" value="<%=to.getTelefono()%>" required="required" readonly="readonly"/>
                                    <label>Celular :</label><input type="text" name="" value="<%=to.getCelular()%>" readonly="readonly"/>
                                    <label>Email :</label><input type="text" name="" value="<%=to.getEmail()%>" readonly="readonly"/>
				</div>
				<div class="span3">
                                    <label>Página Web :</label><input type="text" name="" value="<%=to.getPaginaweb()%>" required="required" readonly="readonly"/>
                                    <label>Descripcion :</label>
                                    <TEXTAREA NAME="descripcion" WRAP="hard" rows="4" cols="20" readonly="readonly"><%=to.getDescripcion()%></TEXTAREA>
                                    
                                </div>
                                <div class="span3">
                                    <label>Rector(a) :</label><input type="text" name="" value="<%=to.getRector()%>" readonly="readonly"/>
                                </div>
                        
            </div>
                        <hr>
                        <div id="topmenu">
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=3&idinstitucion=<%=to.getIdinstitucion()%>" class="btn"><div class="icon-edit"></div>&nbsp;Editar</a>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=1" class="btn"><div class="icon-print"></div>&nbsp;Imprimir</a>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=1" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                        
                        </div>
            </fieldset>
    
                    
                  <%}}%>      
                       	</div>
	</div>
        
    </body>
</html>




