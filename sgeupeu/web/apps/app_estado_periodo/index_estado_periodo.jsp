<%-- 
    Document   : index_estado_periodo
    Created on : 13/09/2013, 06:37:19 AM
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
        
			<div class="tabbable tabs-below">
				<ul class="nav nav-tabs ">
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=29" title="Apertura por Filial" class="informacion"><div class="icon-ok"></div>&nbsp;<strong class="text-info">Filial</strong></a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="" title="Apertura por Facultad" class="informacion"><div class="icon-ok"></div>&nbsp;<strong class="text-info">Facultad</strong></a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="" title="Apertura por EAP" class="informacion"><div class="icon-ok"></div>&nbsp;<strong class="text-info">EAP</strong></a></li>
                
</ul>
				<div class="tab-content">
  
</div>
			</div>
		

    </body>
</html>
