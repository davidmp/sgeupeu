<%-- 
    Document   : edit_eap
    Created on : 22/10/2013, 10:15:11 AM
    Author     : Edwin
--%>


<%@page import="sge.modelo.Tipoarea"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Eap"%>
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
document.getElementById("tab-content").reset(); 

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

<%
//listar_AreasUnidadSess
 List<Tipoarea> tipoAreas =null;
 tipoAreas=(List<Tipoarea>)request.getSession().getAttribute("listar_AreasUnidadSess");

%>
    </head>
    <body>
             <%

        Eap eap = null;
        eap = (Eap) request.getSession().getAttribute("DatosEapById");

    %> 
            <form method="POST" action="<%=request.getContextPath()%>/Organizacion" id="add" name="add">
                
                <legend>Editar E.A.P/EPG/&Aacute;rea</legend>
                            
			<div class="row-fluid">
                            
				<div class="span3">
                                    <label><strong>Nombre:</strong></label>
                                    <input type="text" name="nombre" value="<%=eap.getNombre()%>" required="required"/><br/>
                                    <label><strong>Descripcion:</strong></label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="descripcion" required="required"><%=eap.getDescripcion()%></TEXTAREA>
                                    
                                    
				</div>
				<div class="span3">
                                    <label><strong>Tipo &Aacute;rea:</strong></label>                                    
                                    <select name="tipoarea">
                                        <% if(tipoAreas!=null){
                                        for(Tipoarea tipo: tipoAreas){
                                        %>
                                        <option value="<%=tipo.getIdtipoarea() %>"  <% if(eap.getIdtipoarea()==tipo.getIdtipoarea()){out.print("selected");}%> ><%=tipo.getNombre() %></option>     
                                        <%  } } %>
                                    </select>
                                    <br/>
                                    <label><strong>C&oacute;digo:</strong></label>
                                    <input type="text" name="codigo" value="<%=eap.getCodigo()%>" required="required"/>                                                                      
				</div>                                    
			</div>
                    
                    <hr class="divider">
                    <div class="control-group">
                    <div class="controls">
                        <input type="hidden" name="idEap" value="<%=eap.getIdeap()%>"/>    
                    <input type="hidden" name="opt" value="26"/>
                    <button type="submit" class="btn"><div class="icon-ok"></div>&nbsp;Guardar</button>
                    &nbsp;
                    <button type="reset" class="btn"><div class="icon-refresh"></div>&nbsp;Limpiar</button>
                    &nbsp;
                    
                    <a href="<%=request.getContextPath()%>/Organizacion?opt=10" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                    
                    </div>
                    </div>
                    
             
            </form>
            
  
    </body>
</html>
