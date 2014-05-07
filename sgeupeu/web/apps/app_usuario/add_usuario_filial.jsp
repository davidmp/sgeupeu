<%-- 
    Document   : add_usuario_filial
    Created on : 29/09/2013, 01:18:49 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Perspectiva"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="sge.modelo.Tipoarea"%>
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
  alert("Las passwords deben de coincidir");
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


    </head>
      <%
String user = (String)session.getAttribute("SessionCounter");
if(user!=null){
    
Usuario p = null;
p = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
    
%>
    <body>
        <div class="row-fluid">
	  <div class="span12">
	   <form method="POST" action="<%=request.getContextPath()%>/Usuario" id="addUsuario" name="addUsuario" >
            <fieldset>
	    <legend>Datos del Usuario </legend> 
            <div class="row-fluid">
				<div class="span12">
                                    <select name="idpersona" class="toltipx" title="Selecciona la persona registrada" required="">
                            <option></option>
                            <%
                        List<Persona> listaper=(List<Persona>) request.getSession().getAttribute("listaComboPersonasFilial");
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
                                    <label>Email de Acceso:</label><input type="text" name="usuario" value="" required="" />
                                    <label>Password :</label><input type="password" name="" value="" required="" id="passwd"/>
                                    <label>Confirmar Password :</label><input type="password" name="password" value="" required="" id="passwd2"/>
                                </div>
				<div class="span3">
                                    
                                        <label>Fecha de Registro :</label><input type="date" name="fecha_acceso" value="" />
                                        <label>Filial de Acceso :</label><input type="text" name="" value="<%=p.getIdfilial_nombre()%>" readonly=""/>
                                        <input type="text" name="idfilial" value="<%=p.getIdfilial()%>" style="visibility: hidden;display: none;"/>
                                        <label>Perfil de Usuario :</label><select name="idcategoriausuario">
                                    <option></option>
                                    <%
                                    List<Categoriausuario> listacomboperfil=(List<Categoriausuario>) request.getSession().getAttribute("listar_categoria_usuario_filial");
                                    if (listacomboperfil != null) {
                                    for (Categoriausuario cu : listacomboperfil) {
                                    %>
                                    <option value="<%=cu.getIdcategoriausuario()%>" title="<%=cu.getDescripcion()%>"><%=cu.getNombre()%></option>
                                    <%}}%>
                                    </select>
                                    <input type="text" name="estado" value="1" style="visibility: hidden;display: none;"/>                                    
                                    
                                </div>

                                        <div class="span3">
                                        <label>Unidad/&Aacute;rea :</label>
                                        <select name="idareaunidad">
                                            <option></option>
                                            <%
                                            List<Tipoarea> listaAreasUnidadX=(List<Tipoarea>) request.getSession().getAttribute("listar_AreasUnidadSess");
                                            if (listaAreasUnidadX != null) {
                                            for (Tipoarea ta : listaAreasUnidadX) {
                                            %>
                                            <option value="<%=ta.getIdtipoarea() %>"><%=ta.getNombre() %>(<%=ta.getEtiqueta() %>)</option>
                                            <%}}%>
                                        </select>
                                        
                                        <label>Eje Estrat&eacute;gico :</label>
                                        <select name="idejeestrateico">
                                        <option value="0" >Todos</option>
                                        <%
                                        List<Ejeestrategico> listacomboEjesX=(List<Ejeestrategico>) request.getSession().getAttribute("listar_EjeEstrategicoSess");
                                        if (listacomboperfil != null) {
                                        for (Ejeestrategico cu : listacomboEjesX) {
                                        %>
                                        <option value="<%=cu.getIdejeestrategico() %>" title="<%=cu.getDescripcion()%>"><%=cu.getNombre()%> (<%=cu.getTipoarea() %> )</option>
                                        <%}}%>
                                        </select>
                                        <label>Perspectiva :</label>
                                        <select name="idperspectiva">
                                        <option value="0">Todos</option>
                                        <%
                                        List<Perspectiva> listacomboPerspectivaY=(List<Perspectiva>) request.getSession().getAttribute("listar_PerspectivasSess");
                                        if (listacomboperfil != null) {
                                        for (Perspectiva cu : listacomboPerspectivaY) {
                                        %>
                                        <option value="<%=cu.getIdperspectiva()%>" title="<%=cu.getDescripcion()%>"><%=cu.getNombre()%></option>
                                        <%}}%>
                                        </select>
                                        <input type="text" name="estado" value="1" style="visibility: hidden;display: none;"/>
                                </div>                         
                        
            </div>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="opt" value="45"/>   
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=40" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                     
                       
                       	</div>
	</div>
        
    </body>
     <%}else{out.println("Vuelva a iniciar sesion --> Error en linea de internet ..");} %>
</html>


