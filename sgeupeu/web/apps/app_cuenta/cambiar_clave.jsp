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
var form = $('#changeClave');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('.tab-content').show();
$('.tab-content').html(data);
document.getElementById("changeClave").reset();
document.getElementById("tab-content").reset(); 

}
});
 
return false;
});

function enviarForm(){
   if($("#clave").val().trim()!=""){ 
   if($("#renewclave").val()==$("#newclave").val()){
      $("#changeClave").submit();
    }else{
        alert("Las nuevas Claves no son iguales!!");
    }
    }else{alert("Ud. no ha colocado la Clave Anterior!!");}
}

</script>
<%

sge.modelo.Usuario usuSess=(sge.modelo.Usuario)request.getSession().getAttribute("listaPerfilUsuario");
String smg=null;
smg=request.getParameter("msg")==null?"":request.getParameter("msg") ;
%>
    </head>
    <body>
       <form  action="<%=request.getContextPath()%>/Usuario" id="changeClave" name="changeClave" method="POST">
            <table border="0" cellspacing="3" cellpadding="3">
            <tbody>

                <tr>
                                        
                    <td>
                        <input type="hidden" name="idusuario" value="<%=usuSess.getIdusuario() %>" />
                        <strong>Clave Anterior:</strong><br>
                        <input type="password" name="clave" id="clave" value="" />
                    </td>

                </tr>
                <tr>
                    <td>
                        <strong>Nueva Clave</strong><br>
                        <input type="password" name="newclave" id="newclave" value="" />
                    </td>                  
                </tr>
                <tr>
                    <td>
                        <strong>Confirmar Nueva Clave</strong><br>
                        <input type="password" name="renewclave" id="renewclave" value="" />
                    </td>                    
                </tr>
                <tr>
                    <td colspan="4">
                        <hr>
                        <input type="hidden" name="opt" value="90"/>
                        <input type="button" onclick="enviarForm();" class="btn btn-small btn-success" value="Cambiar Clave"/>                                                                     
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="color: red">
                        <%
                        if(smg!=null){
                        out.print(smg);
                        }
                        %>
                    </td>
                </tr>
             
            </tbody>
        </table>
        </form>
                       
    </body>
</html>
