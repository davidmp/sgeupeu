<%-- 
    Document   : http_401
    Created on : 29/07/2013, 12:26:57 PM
    Author     : Edwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="5;URL=<%=request.getContextPath()%>/index.jsp">
        <title>JSP Page</title>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-responsive.css" rel="stylesheet">
    <style> 
    
        .alert{
            width: 60%;
            margin: 4% auto;
        }
      
    </style>
  
    </head>
    <body>
       
        <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="alert alert-error" >
                            <a href="<%=request.getContextPath()%>/index.jsp"><button type="button" class="close" data-dismiss="alert">×</button></a>
				<h4>
					Error HTTP 401 Unauthorized (No autorizado) !
				</h4> 
			</div>
                    
                        <div class="alert alert-info" >
				<h4>
				</h4> <strong>Warning!</strong> este mensaje de error significa que usted tiene primero que iniciar sesión (ingresar un usuario y contraseña válidos) en algún lugar. Si los acaba de ingresar e inmediatamente ve un error 401, esto quiere decir que el usuario, la contraseña o ambos son inválidos por alguna razón (ingresados incorrectamente, usuario suspendido, etc.).
			</div>
		</div>
	</div>
</div>

    </body>
</html>
