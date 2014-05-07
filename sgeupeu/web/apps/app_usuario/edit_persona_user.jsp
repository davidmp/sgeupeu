<%-- 
    Document   : edit_persona_user
    Created on : 29/09/2013, 12:04:24 PM
    Author     : Edwin
--%>

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

        Persona per = null;
        per = (Persona) request.getSession().getAttribute("listaPersonaById");

    %> 
        <div class="row-fluid">
		<div class="span12">
	<form id="editPersona" name="editPersona" action="<%=request.getContextPath()%>/Usuario" method="POST">
            <fieldset>
	    <legend>Editar Datos Personales</legend> 
            
            <div class="row-fluid">
				<div class="span3">
                                    <label>Nombres :</label><input type="text" name="nombre" value="<%=per.getNombre()%>" required="required"/> 
                                    <label>Apellido Paterno :</label><input type="text" name="apellipaterno" value="<%=per.getApellipaterno()%>" required="required"/>
                                    <label>Apellido Materno :</label><input type="text" name="apellimaterno" value="<%=per.getApellimaterno()%>" required="required"/> 
				</div>
				<div class="span3">
                                    <label>Documento :</label><input type="text" name="dni" value="<%=per.getDni()%>" required="required"/>
                                    <label>Sexo :</label><select name="sexo" required="required" style="width: 55px;">
                                        <option value="<%=per.getSexo()%>"><%=per.getSexo()%></option>
                            <option value="M">M</option>
                            <option value="F">F</option>
                        </select>
                                    <label>Telefono / Celular :</label><input type="text" name="telefono" value="<%=per.getTelefono()%>" />
				</div>
				<div class="span3">
                                    <input type="text" name="celular" value="<%=per.getCelular()%>" style="visibility: hidden;display: none;" />
                                    <label>Email :</label><input type="text" name="email" value="<%=per.getEmail()%>" required="required"/>
                                    <label>Fecha de Nac. :</label><input type="date" name="fechanacimiento" value="<%=per.getFechanacimiento()%>" required="required"/>
                                    <label>Filial de Procedencia :</label><select name="idfilial" required="required">
                                        <option value="<%=per.getIdfilial()%>"><%=per.getIdfilialname()%></option>
                            <%
                        List<Filial> listafilial=(List<Filial>) request.getSession().getAttribute("listar_filial");
                        if (listafilial != null) {
                            for (Filial fi : listafilial) {

                    %>
                    <option value="<%=fi.getIdfilial()%>"><%=fi.getDireccion()%></option>
                            <%}}%>
                        </select>
				</div>
                        
            </div>
                        <hr>
                        <input type="hidden" name="idpersona" value="<%=per.getIdpersona()%>"/>
                        
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="38"/>
                        <input type="submit" value="Actualizar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=1" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
    </body>
</html>

