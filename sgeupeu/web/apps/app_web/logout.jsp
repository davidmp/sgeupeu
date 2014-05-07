<%-- 
    Document   : logout
    Created on : 19/08/2013, 08:07:18 PM
    Author     : Edwin
--%>

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
			<div class="modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
                                    <a href="" class="close" data-dismiss="modal" aria-hidden="true">x</a>
					 
                                         <h3 id="myModalLabel" class="text-error">
						Cerrando Sesion !!!
					</h3>
				</div>
				<div class="modal-body text-info" >
					<p>
                                                Estas seguro de Cerrar Sesion y terminar cualquier proceso.
					</p>
				</div>
				<div class="modal-footer">
                                    <a href="" class="btn btn-warning" data-dismiss="modal" aria-hidden="true">Cancelar</a>
                                    <a href="<%=request.getContextPath()%>/Logout" class="btn btn-success" > Aceptar</a>
					 
				</div>
			</div>
		</div>
	</div>
</div>
    </body>
</html>
