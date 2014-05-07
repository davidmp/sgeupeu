<%-- 
    Document   : add_filial
    Created on : 01/08/2013, 10:13:11 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Usuario"%>
<%
String user = (String)session.getAttribute("SessionCounter");
if(user!=null){
    
Usuario p = null;
p = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
    
%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<script type="text/javascript">
var form = $('#addPersonafilial');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#addusuariofilial').show();
$('#addusuariofilial').html(data);
document.getElementById("addPersonafilial").reset();
document.getElementById("addusuariofilial").reset(); 

}
});
 
return false;
});



</script>
    </head>
    <body>
        <%
                        List<Persona> ProxIdPer=(List<Persona>) request.getSession().getAttribute("Proximo_IDPersona_filial");
                        if (ProxIdPer != null) {
                            for (Persona id : ProxIdPer) {

                    %>
        <form id="addPersonafilial" name="addPersonafilial" action="<%=request.getContextPath()%>/Usuario" method="POST">
            <table>
           <tbody>
                <tr style="visibility: hidden;display: none;">
                    <td colspan="4">
                        <strong>Codigo ID de la Persona</strong><br/>
                        
                    <input type="text" name="idpersona" value="<%=id.getIdpersona()%>" readonly=""/>
                       
                    </td>
                </tr>
                <tr>
                    
                    <td>
                        <strong>Nombres</strong><br/>
                        <input type="text" name="nombre" value="" required="required"/>
                    </td>
                    <td>
                        <strong>Apellido Paterno</strong><br/>
                        <input type="text" name="apellipaterno" value="" required="required"/>
                    </td>
                    <td>
                        <strong>Apellido Materno</strong><br/>
                        <input type="text" name="apellimaterno" value="" required="required"/>
                    </td>
                    <td>
                        <strong>DNI</strong><br/>
                        <input type="text" name="dni" value="" required="required"/>
                    </td>
                </tr>
                
                 <tr>
                    <td>
                        <strong>Sexo</strong><br/>
                        <select name="sexo" required="required">
                            <option></option>
                            <option value="M">M</option>
                            <option value="F">F</option>
                        </select>
                    </td>
                    <td>
                        <strong>Telefono</strong><br/>
                        <input type="text" name="telefono" value="" />
                    </td>
                    <td>
                        <strong>Celular</strong><br/>
                        <input type="text" name="celular" value="" />
                    </td>
                    <td>
                        <strong>Correo Electronico</strong><br/>
                        <input type="text" name="email" value="" required="required"/>
                    </td>
                </tr>
                 <tr>
                    <td>
                        <strong>Fecha Nacimiento</strong><br/>
                        <input type="date" name="fechanacimiento" value="" required="required"/>
                    </td>
                    <td>
                        <strong>Filial</strong><br/>
                        <input style="visibility: hidden;display: none;" type="text" name="idfilial" value="<%=p.getIdfilial()%>" required="required" readonly="readonly"/>
                        <input type="text" name="" value="<%=p.getIdfilial_nombre()%>" readonly="readonly"/>
                    </td>
                    <td>
                        
                    </td>
                    <td>
                        
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <hr>
                        <input type="hidden" name="opt" value="24"/>  
                        <input type="submit" value="Siguiente" class="btn btn-small btn-info" />
                        <input type="reset" value="Cancelar" class="btn btn-small btn-warning"/>
                    </td>
                </tr> 
            </tbody>
            
        </table>
        </form>
                       <%}}%> 
    </body>
</html>
<%}else{out.println("Vuelva a iniciar session");} %>