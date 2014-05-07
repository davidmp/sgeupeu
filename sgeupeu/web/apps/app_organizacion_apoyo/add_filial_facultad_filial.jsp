<%-- 
    Document   : add_filial_facultad_filial
    Created on : 03/10/2013, 04:34:37 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Usuario"%>
<%@page import="sge.modelo.Coordinadorfacultad"%>
<%@page import="sge.modelo.Facultad"%>
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
	<form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/UsuarioApoyo" method="POST">
            <fieldset>
                <legend>Registro de Unidad de Apoyo</legend>
	    <div class="row-fluid">
				<div class="span3">
                                    <label>Coordinador de Unidad Apoyo :</label>
                                    <select name="idcoordinadorfacultad" required="">
                                        <option></option>
                                         <%
              
                        List<Coordinadorfacultad> listacf=(List<Coordinadorfacultad>) request.getSession().getAttribute("listar_Coordinador_facultad");
                        if (listacf != null) {
                            for (Coordinadorfacultad cf : listacf) {

                    %>  
                    <option value="<%=cf.getIdcoordinadorfacultad()%>"><%=cf.getIdperiodo_name()%>&nbsp;<%=cf.getIdpersona_nombre()%>&nbsp;<%=cf.getIdpersona_apellpaterno()%>&nbsp;<%=cf.getIdpersona_apellmaterno()%></option>
                                        <%}}%>
                                    </select>
                                    
                                        <input type="text" name="idfilial" value="<%=p.getIdfilial()%>" required="required" style="visibility: hidden;display: none;"/>
                                     
				</div>
				<div class="span3">
                                    <label>Unidad de Apoyo :</label>
                                    <select name="idfacultad" required="">
                                        <option></option>
                                        <%
              
                        List<Facultad> lista=(List<Facultad>) request.getSession().getAttribute("listar_facultad");
                        if (lista != null) {
                            for (Facultad fa : lista) {

                    %>
                    <option value="<%=fa.getIdfacultad()%>"><%=fa.getNombre()%></option>
                                        <%}}%>
                                    </select>
                                </div>
				<div class="span3">
                                </div>
                        
            </div>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="77"/>  
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/UsuarioApoyo?opt=67" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
    </body>
</html>
