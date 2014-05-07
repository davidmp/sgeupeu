<%-- 
    Document   : perfil
    Created on : 31/07/2013, 08:15:44 PM
    Author     : Edwin
--%>
 <%@page import="sge.modelo.Usuario"%>
<%
String user = (String)session.getAttribute("SessionCounter");
if(user!=null){
    
Usuario p = null;
p = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<blockquote>
				<p>
					Mi Perfil:
                                </p> 
                                <small class="text-info"><strong class="text-success">Nombres y Apellidos :</strong>&nbsp;<%=p.getIdpersona_nombre()%>&nbsp;<%=p.getIdpersona_apell_paterno()%>&nbsp;<%=p.getIdpersona_apell_materno()%></small>
                                <small class="text-info"><strong class="text-success">Usuario / Correo electronico de Acceso :</strong>&nbsp;<%=p.getUsuario()%>&nbsp;</small>
                                <small class="text-info"><strong class="text-success">Estado :</strong>&nbsp;<%=p.getEstado()%>&nbsp;</small>
                                <small class="text-info"><strong class="text-success">Filial :</strong>&nbsp;<%=p.getIdfilial_nombre()%>&nbsp;</small>
                                <small class="text-info"><strong class="text-success">Perfil de Acceso :</strong>&nbsp;<%=p.getIdcategoriausuario_nombre()%>&nbsp;</small>
                                <small class="text-info"><strong class="text-success">Fecha de Acceso :</strong>&nbsp;<%=p.getFechaAcceso()%>&nbsp;</small>
                                <small class="text-info"><strong class="text-success">Fecha de Alta :</strong>&nbsp;<%=p.getFechaAlta()%>&nbsp;</small>
			</blockquote>
		</div>
	</div>
</div>
    </body>
</html>
<%}else{out.println("Vuelva a i");} %>