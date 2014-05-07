<%-- 
    Document   : add_datos_cuenta_filial
    Created on : 02/10/2013, 03:08:04 AM
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
        <%
            Usuario p = null;
            p = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
        %>
        <div class="row-fluid">
		<div class="span12">
	
                   <%
                        List<Persona> ProxIdPer=(List<Persona>) request.getSession().getAttribute("Proximo_IDPersona");
                        if (ProxIdPer != null) {
                            for (Persona id : ProxIdPer) {

                    %>
                    
                    
        <form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/Usuario" method="POST">
            <fieldset>
	    <legend>Datos Personales </legend> 
            
            <div class="row-fluid">
				<div class="span3">
                                    <input type="text" name="idpersona" value="<%=id.getIdpersona()%>" readonly="" style="visibility: hidden;display: none;"/>
                                    <label>Nombres :</label><input type="text" name="nombre" value="" required="required"/> 
                                    <label>Apellido Paterno :</label><input type="text" name="apellipaterno" value="" required="required"/>
                                    <label>Apellido Materno :</label><input type="text" name="apellimaterno" value="" required="required"/> 
				</div>
				<div class="span3">
                                    <label>Documento :</label><input type="text" name="dni" value="" required="required"/>
                                    <label>Sexo :</label><select name="sexo" required="required" style="width: 55px;">
                            <option></option>
                            <option value="M">M</option>
                            <option value="F">F</option>
                        </select>
                                    <label>Telefono / Celular :</label><input type="text" name="telefono" value="" />
				</div>
				<div class="span3">
                                    <input type="text" name="celular" value="" style="visibility: hidden;display: none;" />
                                    <label>Email :</label><input type="text" name="email" value="" required="required"/>
                                    <label>Fecha de Nac. :</label><input type="date" name="fechanacimiento" value="" required="required"/>
                                         <label>Filial de Procedencia :</label><input type="text" name="" value="<%=p.getIdfilial_nombre()%>" required="required" readonly=""/>
                                    <input style="visibility: hidden;display: none;" type="text" name="idfilial" value="<%=p.getIdfilial()%>" required="required"/>
				
                                    
				</div>
                        
            </div>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="73"/>  
                        <input type="submit" value="Siguiente" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=40" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                       <%}}%> 
                       
                       	</div>
	</div>
        
    </body>
</html>
