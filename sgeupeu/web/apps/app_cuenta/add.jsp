<%-- 
    Document   : add
    Created on : 29/07/2013, 06:22:51 PM
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
	
        
        
        
        <%
                        List<Persona> ProxIdPer=(List<Persona>) request.getSession().getAttribute("Proximo_IDPersona");
                        if (ProxIdPer != null) {
                            for (Persona id : ProxIdPer) {

                    %>
        <form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/Usuario" method="POST">
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
                        <select name="idfilial" required="required">
                            <option></option>
                            <%
                        List<Filial> listafilial=(List<Filial>) request.getSession().getAttribute("listar_filial");
                        if (listafilial != null) {
                            for (Filial fi : listafilial) {

                    %>
                    <option value="<%=fi.getIdfilial()%>"><%=fi.getDireccion()%></option>
                            <%}}%>
                        </select>
                    </td>
                    <td>
                        
                    </td>
                    <td>
                        
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="19"/>  
                        <input type="submit" value="Siguiente" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=1" class="btn">Cancelar</a>
                        </div>
                    </td>
                </tr> 
            </tbody>
            
        </table>
        </form>
                       <%}}%> 
                       
                       	</div>
	</div>
        
    </body>
</html>
