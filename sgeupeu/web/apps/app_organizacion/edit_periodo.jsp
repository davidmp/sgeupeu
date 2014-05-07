<%-- 
    Document   : edit_periodo
    Created on : 30/09/2013, 02:01:45 AM
    Author     : Edwin
--%>


<%@page import="sge.modelo.Temporada"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Periodo"%>
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
 <script>
       
        function valorperiodo(periodo){
        $('#text1').val(periodo); 
        }
        
        function valornro(id){
        $('#text2').val(id); 
        }
    
       function sumar() {
        var total;
        total = text1.value+" - "+text2.value;
        text3.value = total;
    }
       
 </script>
 <% List<Temporada> tempoList=null;
 tempoList=(List<Temporada>)request.getSession().getAttribute("listarTemporadaSess");
 %> 
    </head>
    <body>
        
        <%

        Periodo to = null;
        to = (Periodo) request.getSession().getAttribute("listaPeriodoById");

    %>
        <div class="row-fluid">
		<div class="span12">
	               
                    
        <form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/Organizacion" method="POST">
            <fieldset>
	    <legend>Editar Periodo Academico</legend> 
            
            <div class="row-fluid">
				<div class="span3">
                                    <label>Año Periodo :</label><input type="text" name="periodo" value="<%=to.getPeriodo()%>"/>
                                    <label>Fecha Inicio :</label><input type="date" name="fechainicio" value="<%=to.getFechainicio()%>" />
                                    <label>Fecha Fin :</label><input type="date" name="fechafin" value="<%=to.getFechafin()%>"/> 
                                    
				</div>
				<div class="span3">
                                    <label>Temporada:</label>  
                                    <select name="temporada">
                                    <% if(tempoList!=null){
                                    for(Temporada tem: tempoList){    
                                    %>    
                                    <option value="<%=tem.getIdtemporada() %>" <% if(tem.getIdtemporada()==to.getIdtemporada()){out.print("selected");} %>  > <%=tem.getDescripcion() %></option>                                        
                                        
                                    <% }} %>    
                                    </select>
                                </div>
				<div class="span3">
                              
                                </div>
                        
            </div>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="idPeriodo" value="<%=to.getIdperiodo()%>"/>
                        <input type="hidden" name="estado" value="<%=to.getEstado()%>"/>
                        <input type="hidden" name="opt" value="17"/>  
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Organizacion?opt=11" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                  
                       
                       	</div>
	</div>
        
    </body>
</html>
