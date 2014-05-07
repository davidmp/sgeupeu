<%-- 
    Document   : edit_facultad
    Created on : 28/08/2013, 12:58:07 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Facultad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
 
var form = $('#add');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('.tab-content').show();
$('.tab-content').html(data);
document.getElementById("add").reset();
document.getElementById(".tab-content").reset(); 

}
});
 
return false;
});
$(".alert").alert();

$(document).ready(function() {
    $(".controls a").click(function() {
    $(".tab-content").empty().append();
    $(".controls a").removeClass('current');
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
    <%

        Facultad to = null;
        to = (Facultad) request.getSession().getAttribute("Buscar_Facultad_Id");

    %>
    <body>
     <form method="POST" action="<%=request.getContextPath()%>/Organizacion" id="add" name="add">
                <legend>Editar Facultad/UPG/Unidad Apoyo</legend>
                    
			<div class="row-fluid">
				<div class="span3">
                                    <label><strong>Nombre:</strong></label>
                                    <input type="text" name="nombre" value="<%=to.getNombre()%>" required="required"/><br/>
                                    <label><strong>Descripcion:</strong></label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="descripcion" required="required"><%=to.getDescripcion()%></TEXTAREA>
                                    
				</div>
			</div>
                    
                    <hr class="divider">
                    <div class="control-group">
                    <div class="controls">
                    <input type="hidden" name="idfacultad" value="<%=to.getIdfacultad()%>"/>
                    <input type="hidden" name="opt" value="9"/>
                    <button type="submit" class="btn"><div class="icon-ok"></div>&nbsp;Actualizar</button>
                    &nbsp;
                    <a href="<%=request.getContextPath()%>/Organizacion?opt=6" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                    
                    </div>
                    </div>
                    
               
            </form>
            
     
                                    <div id="mensaje">
                                        
                                    </div>
    </body>
</html>
