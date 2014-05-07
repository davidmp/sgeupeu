<%-- 
    Document   : add_eap_facultad_filial
    Created on : 04/10/2013, 08:48:04 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Coordinadoreap"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Filialfacultad"%>
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
        <div class="row-fluid">
		<div class="span12">
	<form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/Usuario" method="POST">
            <fieldset>
            <legend>Registro de Escuelas Academicas Profesionales</legend> 
	    <div class="row-fluid">
				<div class="span3">
                                    <label> Facultad :</label>
                                    <select name="idfilialfacultad" required="">
                                        <option></option>
                                            <%
                                             List<Filialfacultad> listaf=(List<Filialfacultad>) request.getSession().getAttribute("listaFilialFacultadfilial");
                                             if (listaf != null) {
                                             for (Filialfacultad cf : listaf) {
                                            %> 
                    <option value="<%=cf.getIdfilialfacultad()%>"><%=cf.getIdfacultad_nombre()%></option>
                                        <%}}%>
                                    </select>
                                    
                               </div>
				<div class="span3">
                                    <label>Escuela Academica Profesional :</label>
                                    <select name="ideap" required="">
                                        <option></option>
                                        <%
              
                        List<Eap> lista=(List<Eap>) request.getSession().getAttribute("listar_eap");
                        if (lista != null) {
                            for (Eap eap : lista) {

                    %>
                    <option value="<%=eap.getIdeap()%>"><%=eap.getNombre()%></option>
                                        <%}}%>
                                    </select>
                                </div>
				<div class="span3">
                                    <label>Coordinador Responsable :</label>
                                    <select name="idcoordinadoreap" required="">
                                        <option></option>
                                        <%
              
                        List<Coordinadoreap> listace=(List<Coordinadoreap>) request.getSession().getAttribute("listacoordinadoreapall");
                        if (listace != null) {
                            for (Coordinadoreap ce : listace) {

                    %>
                    <option value="<%=ce.getIdCoordinadorEap()%>"><%=ce.getIdperiodo_name()%>&nbsp;<%=ce.getIdpersona_nombre()%>&nbsp;<%=ce.getIdpersona_apellpaterno()%>&nbsp;<%=ce.getIdpersona_apellmaterno()%></option>
                                        <%}}%>
                                    </select>
                                </div>
                        
            </div>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="81"/>  
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=79" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
    </body>
</html>
