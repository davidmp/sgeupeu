 
<%@page import="sge.modelo.Temporada"%>
<%
    Temporada tem = null;
        tem = (Temporada) request.getSession().getAttribute("listaTemporadaById");

    %>
         
            <form method="POST" action="<%=request.getContextPath()%>/GestionEstrategico" id="add" name="add">
                <legend>Registro de Temporadas</legend>
                 <fieldset>
                    <div class="row-fluid">
                		<div class="span3">
                                    <label>Fecha Inicio:</label>
                                    <input type="date" name="inicio" value="<%=tem.getInicio()%>" />
                                    <label>Fecha Fin:</label>
                                    <input type="date" name="fin" value="<%=tem.getFin()%>" />
                                    
                                </div>
                            <div class="span3">
                                <label>Descripcion :</label>
                                <TEXTAREA COLS="5" ROWS="5" NAME="descripcion" required="required"><%=tem.getDescripcion()%></TEXTAREA>
                            </div>
                        <div class="span3"></div>
                        <div class="span3"></div>
                   </div>
                 </fieldset>
		   <hr>
                        <div id="topmenu">
                          
                        <input type="hidden" name="idTemporada" value="<%=tem.getIdtemporada()%>"/>      
                        <input type="hidden" name="estado" value="<%=tem.getEstado()%>"/>    
                        <input type="hidden" name="opt" value="64"/>
                        <button type="submit" class="btn btn-success"><div class="icon-ok icon-white"></div>&nbsp;Guardar</button>
                        <button type="reset" class="btn btn-warning"><div class="icon-refresh icon-white"></div>&nbsp;Limpiar</button>
                        
                        <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=18" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                        </div> 
                    
            </form>

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