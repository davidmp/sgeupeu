<%-- 
    Document   : add_cuenta
    Created on : 29/07/2013, 07:55:50 PM
    Author     : Edwin
--%>

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
var form = $('#addUsuario');
form.submit(function () {
 
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



</script>
    </head>
    <body>
       <form method="POST" action="<%=request.getContextPath()%>/Usuario" id="addUsuario" name="addUsuario" >
            <table border="0" cellspacing="3" cellpadding="3">
            <tbody>
                <tr>
                    <td colspan="4">
                     <%
                        int IDPost = (Integer)session.getAttribute("postID");
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
                        <select name="idfilial">
                            <option></option>
                            <%
                        List<Filial> listacombofilial=(List<Filial>) request.getSession().getAttribute("listar_filial");
                        if (listacombofilial != null) {
                            for (Filial fi : listacombofilial) {
                            %>
                            <option value="<%=fi.getIdfilial()%>"><%=fi.getDireccion()%></option>
                            <%}}%>
                        </select>
                    </td>
                    <td>
                        <strong>Perfil para el sistema:</strong><br>
                        <select name="idcategoriausuario">
                            <option></option>
                            <%
                        List<Categoriausuario> listacomboperfil=(List<Categoriausuario>) request.getSession().getAttribute("listar_categoria");
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
                        <input type="hidden" name="opt" value="20"/>  
                        <input type="submit" value="Finalizar" class="btn btn-small btn-success"/>
                        <input type="reset" value="Cancelar" class="btn btn-small btn-danger"/>
                    </td>
                </tr>
            </tbody>
        </table>
        </form>
    </body>
</html>
