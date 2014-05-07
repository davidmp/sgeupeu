<%-- 
    Document   : add_facultad
    Created on : 27/08/2013, 12:22:52 AM
    Author     : Edwin
--%>

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
$('#mensaje').show();
$('#mensaje').html(data);
document.getElementById("add").reset();
document.getElementById("mensaje").reset(); 

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
    <body>
            
            <form method="POST" action="<%=request.getContextPath()%>/Organizacion" id="add" name="add">
                
                <legend>Registro de Facultades/UPG/Unidad Apoyo</legend>
                            
			<div class="row-fluid">
                            
				<div class="span3">
                                    <label><strong>Nombre:</strong></label>
                                    <input type="text" name="nombre" value="" required="required"/><br/>
                                    <label><strong>Descripcion:</strong></label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="descripcion" required="required"></TEXTAREA>
                                    
                                    
				</div>
			</div>
                    
                    <hr class="divider">
                    <div class="control-group">
                    <div class="controls">
                    <input type="hidden" name="opt" value="7"/>
                    <button type="submit" class="btn"><div class="icon-ok"></div>&nbsp;Guardar</button>
                    &nbsp;
                    <button type="reset" class="btn"><div class="icon-refresh"></div>&nbsp;Limpiar</button>
                    &nbsp;
                    
                    <a href="<%=request.getContextPath()%>/Organizacion?opt=6" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                    
                    </div>
                    </div>
                    
             
            </form>
            
  
       
                                    <div id="mensaje">
                                        
                                    </div>
    </body>
</html>
