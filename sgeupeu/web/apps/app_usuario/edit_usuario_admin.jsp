<%-- 
    Document   : edit_usuario_admin
    Created on : 29/09/2013, 10:43:57 AM
    Author     : Edwin
--%>


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
var form = $('#addUsuario');
form.submit(function () {
    
var p1 = document.getElementById("passwd").value;
var p2 = document.getElementById("passwd2").value; 
if (p1 !== p2) {
  alert("La Contraseña Anterior no coincide con la que ingresaste");
  return false;
} else {
  //alert("Todo esta correcto");
true;
}
    
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('.tab-content').show();
$('.tab-content').html(data);
document.getElementById("addUsuario").reset();
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

<script type="text/javascript">

function validapassword(){

};

    </script>
    </head>
    <body>
         <%

        Usuario to = null;
        to = (Usuario) request.getSession().getAttribute("listaUsuarioById");

    %> 
        <div class="row-fluid">
		<div class="span12">
	   <form method="POST" action="<%=request.getContextPath()%>/Usuario" id="addUsuario" name="addUsuario" >
            <fieldset>
	    <legend>Editar Datos del Usuario </legend> 
            <div class="row-fluid">
				<div class="span12">
                                    <select name="idpersona" class="toltipx" title="Selecciona la persona registrada" required="">
                                        <option value="<%=to.getIdpersona()%>"><%=to.getIdpersona_nombre()%>&nbsp;<%=to.getIdpersona_apell_paterno()%>&nbsp;<%=to.getIdpersona_apell_materno()%></option>
                            <%
                        List<Persona> listaper=(List<Persona>) request.getSession().getAttribute("listarcombopersona");
                        if (listaper != null) {
                            for (Persona per : listaper) {
                            %>
                            <option value="<%=per.getIdpersona()%>"><%=per.getNombre()%>&nbsp;<%=per.getApellipaterno()%>&nbsp;<%=per.getApellimaterno()%></option>
                            <%}}%>
                        </select>
				</div>
            </div>
            <div class="row-fluid">
				<div class="span3">
                                    <label>Email de Acceso:</label><input type="text" name="usuario" value="<%=to.getUsuario()%>" required="" />
                                    <label>Password Anterior:</label><input type="password" name="" value="<%=to.getPassword()%>" id="passwd" readonly=""/>
                                    <input type="hidden" name="" value="<%=to.getPassword()%>" required="" id="passwd2"/>
                                </div>
				<div class="span3">
                                    <label>Nuevo Password:</label><input type="password" name="password" value="<%=to.getPassword()%>"/>
                                    <label>Fecha de Registro :</label><input type="date" name="fecha_acceso" value="<%=to.getFechaAcceso()%>" />
                                </div>
				<div class="span3">
                                    <label>Filial de Acceso :</label><select name="idfilial">
                                        <option value="<%=to.getIdfilial()%>"><%=to.getIdfilial_nombre()%></option>
                            <%
                        List<Filial> listacombofilial=(List<Filial>) request.getSession().getAttribute("listar_filial");
                        if (listacombofilial != null) {
                            for (Filial fi : listacombofilial) {
                            %>
                            <option value="<%=fi.getIdfilial()%>"><%=fi.getDireccion()%></option>
                            <%}}%>
                        </select>
                        
                        
                        <label>Perfil de Usuario :</label>
                        <select name="idcategoriausuario">
                        
                            <%
                        List<Categoriausuario> listacomboperfil=(List<Categoriausuario>) request.getSession().getAttribute("listar_categoria");
                        if (listacomboperfil != null) {
                            for (Categoriausuario cu : listacomboperfil) {
                            %>
                            <option value="<%=cu.getIdcategoriausuario()%>" <% if(cu.getIdcategoriausuario()==Integer.parseInt(to.getIdcategoriausuario())){out.print("selected");} %> title="<%=cu.getDescripcion()%>" <% if(to.getIdcategoriausuario().equals(String.valueOf(cu.getIdcategoriausuario()))){ out.print("selected");}  %>><%=cu.getNombre()%></option>
                            <%}}%>
                        </select>
                        <input type="text" name="estado" value="1" style="visibility: hidden;display: none;"/>
                        </div>
                        </div>
                        
                        
                        
                        
                        <hr>
                        <div id="topmenu">
                            <input type="hidden" name="idusuario" value="<%=to.getIdusuario()%>"/>       
                        <input type="hidden" name="opt" value="36"/>   
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


