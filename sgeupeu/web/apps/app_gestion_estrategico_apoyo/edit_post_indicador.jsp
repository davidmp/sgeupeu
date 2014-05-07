<%@page import="sge.modelo.Indicador"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Tipometa"%>
<script type="text/javascript">
var form = $('#addIndicador');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#indicadoresestrategias').show();
$('#indicadoresestrategias').html(data);
document.getElementById("addIndicador").reset();
document.getElementById("indicadoresestrategias").reset(); 

}
});
 
return false;
});

$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#indicadoresestrategias").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#indicadoresestrategias").empty().append(html);
    }
    });
    return false;
    });
});

</script>

<%
    Indicador ind = null;
            ind = (Indicador) request.getSession().getAttribute("listaidIndicadorById");
        %>
        <div class="row-fluid">
		<div class="span12">
	<form id="addIndicador" name="addIndicador" action="<%=request.getContextPath()%>/GestionEstrategicoApoyo" method="POST">     <fieldset>
	    <legend>Actualización de Indicadores</legend> 
            <fieldset>
            <div class="row-fluid">
				<div class="span3">
                                    <label>Nombre de Indicador :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="nombre" required="required"><%=ind.getNombre()%></TEXTAREA>
                                </div>
                                <div class="span3">
                                    <label>Descripcion :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="descripcion" required="required"><%=ind.getDescripcion()%></TEXTAREA>
                               </div>
				<div class="span3">
                                    <label>Nro:</label>
                                    <input type="number" name="nro" value="<%=ind.getNro()%>" class="span4" />
                                    <label>Tipo Meta:</label>
                                    <select name="idTipoMeta" required="required">
                                        <option value="<%=ind.getIdtipometa()%>"><%=ind.getIdtipometa_nombre()%></option>
                                        <option></option>
                                         <%
                          
                        List<Tipometa> lista=(List<Tipometa>) request.getSession().getAttribute("listar_tipo_meta");
                        if (lista != null) {
                            for (Tipometa tm : lista) {

                    %>
                    <option value="<%=tm.getIdtipometa()%>"><%=tm.getTipo()%></option>
                                        <%}}%>
                                    </select>
                                </div>
                                <div class="span3">
                                    
                               </div>
            </div>
            </fieldset>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="estado" value="1"/>
                        <input type="hidden" name="idIndicador" value="<%=ind.getIdindicador()%>"/>
                        <input type="hidden" name="opt" value="5"/>
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=15" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
