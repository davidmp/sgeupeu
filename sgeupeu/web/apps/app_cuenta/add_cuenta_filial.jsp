<%-- 
    Document   : add_cuenta_filial
    Created on : 01/08/2013, 10:18:56 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Usuario"%>
<%
String user = (String)session.getAttribute("SessionCounter");
if(user!=null){
    
Usuario p = null;
p = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
    
%>
<%@page import="sge.modelo.Categoriausuario"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
var form = $('#addUsuariofilialfinal');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#addusuariofilial').show();
$('#addusuariofilial').html(data);
document.getElementById("addUsuariofilialfinal").reset();
document.getElementById("addusuariofilial").reset(); 

}
});
 
return false;
});



</script>
    </head>
    <body>
       <form method="POST" action="<%=request.getContextPath()%>/Usuario" id="addUsuariofilialfinal" name="addUsuariofilialfinal" >
            <table border="0" cellspacing="3" cellpadding="3">
            <tbody>
                <tr>
                    <td colspan="4">
                     <%
int IDPost = (Integer)session.getAttribute("postIDFilial");
                     %>
                    </td>
                </tr>
                <tr>
                    <td style="visibility: hidden;display: none;">
                        <strong>ID Persona :</strong><br>
                        <input type="text" name="idpersona" value="<%=IDPost%>" />
                    </td>
                    
                    <td>
                        <strong>Email/ Usuario :</strong><br>
                        <input type="text" name="usuario" value="" />
                    </td>
                    <td>
                        <strong>Password:</strong><br>
                        <input type="password" name="" value="" />
                    </td>
                    <td>
                        <strong>Confir - Password:</strong><br>
                        <input type="password" name="password" value="" />
                    </td>
                    <td>
                        <strong>Fecha de Registro:</strong><br>
                        <input type="date" name="fecha_acceso" value="" />
                    </td>
                 
                </tr>
                <tr>
                    <td>
                        <strong>Filial:</strong><br>
                        <input style="visibility: hidden;display: none;" type="text" name="idfilial" value="<%=p.getIdfilial()%>" required="required" readonly="readonly"/>
                        <input type="text" name="" value="<%=p.getIdfilial_nombre()%>" readonly="readonly"/>
                    
                    </td>
                    <td>
                        <strong>Perfil para el sistema:</strong><br>
                        <select name="idcategoriausuario">
                            <option></option>
                            <%
                        List<Categoriausuario> listacomboperfil=(List<Categoriausuario>) request.getSession().getAttribute("listar_categoria_filial");
                        if (listacomboperfil != null) {
                            for (Categoriausuario cu : listacomboperfil) {
                            %>
                            <option value="<%=cu.getIdcategoriausuario()%>" title="<%=cu.getDescripcion()%>"><%=cu.getNombre()%></option>
                            <%}}%>
                        </select>
                    </td>
                    <td style="visibility: hidden;"> <strong>Estado:</strong><br>
                        <input type="text" name="estado" value="1" /></td>
                    <td style="visibility: hidden;">
                        <strong>Fecha de alta:</strong><br>
                        <input type="date" name="fecha_alta" value="" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <hr>
                        <input type="hidden" name="opt" value="25"/>  
                        <input type="submit" value="Finalizar" class="btn btn-small btn-success"/>
                        <input type="reset" value="Cancelar" class="btn btn-small btn-danger"/>
                    </td>
                </tr>
            </tbody>
        </table>
        </form>
    </body>
</html>
<%}else{out.println("Vuelva a iniciar sesion !!");} %>