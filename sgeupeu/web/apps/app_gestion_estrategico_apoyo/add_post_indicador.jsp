<%@page import="sge.modelo.Tipoarea"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Ejeestrategico"%>
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
int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
%>
        <div class="row-fluid">
		<div class="span12">
	<form id="addIndicador" name="addIndicador" action="<%=request.getContextPath()%>/GestionEstrategicoApoyo" method="POST">     <fieldset>
	    <legend>Registro de Indicadores</legend> 
            <fieldset>
            <div class="row-fluid">
				<div class="span3">
                                    <label>C&oacute;digo:</label>
                                    <input type="text" name="codigo" value="" required="required" class="span4" />                                    
                                    <label>Nombre de Indicador :</label>
                                    <TEXTAREA COLS="5" ROWS="5" NAME="nombre" required="required"></TEXTAREA>
                                </div>
                                <div class="span3">
                                    <label>Descripcion :</label>
                                    <TEXTAREA COLS="5" ROWS="8" NAME="descripcion" required="required"></TEXTAREA>
                               </div>
				<div class="span3">                                    
                                    <label>Tipo Meta:</label>
                                    <select name="idTipoMeta" required="required">
                                        <option></option>
                                         <%
                          
                        List<Tipometa> lista=(List<Tipometa>) request.getSession().getAttribute("listar_tipo_meta");
                        if (lista != null) {
                            for (Tipometa tm : lista) {

                    %>
                    <option value="<%=tm.getIdtipometa()%>"><%=tm.getTipo()%></option>
                                        <%}}%>
                                    </select>
                                    
                                        <label>Eje Estrat&eacute;gico :</label>
                                        <select name="idejeestrategico">                                        
                                        <%
                                        List<Ejeestrategico> listacomboEjesX=(List<Ejeestrategico>) request.getSession().getAttribute("listar_EjeEstrategicoSessXX");
                                        if (listacomboEjesX != null) {
                                        for (Ejeestrategico cu : listacomboEjesX) {
                                        %>
                                        <option value="<%=cu.getIdejeestrategico() %>"  <% if(idEjeX==cu.getIdejeestrategico()){out.print("selected");} %>  title="<%=cu.getDescripcion()%>"><%=cu.getCodigo() %>-<%=cu.getNombre()%></option>
                                        <%}}%>
                                        </select> 
                                        
                                        <label>Unidad/&Aacute;rea :</label>
                                        <select name="idareaunidad">
                                            <option></option>
                                            <%
                                            List<Tipoarea> listaAreasUnidadX=(List<Tipoarea>) request.getSession().getAttribute("listar_AreasUnidadSessX");
                                            if (listaAreasUnidadX != null) {
                                            for (Tipoarea ta : listaAreasUnidadX) {
                                            %>
                                            <option value="<%=ta.getIdtipoarea() %>"><%=ta.getNombre() %>(<%=ta.getEtiqueta() %>)</option>
                                            <%}}%>
                                        </select>                                        
                                </div>
                                <div class="span3">
                                    <label>Filial :</label>
                                        <select name="idfilial">                                        
                                        <%
                                        List<Filial> listacomboEjesXY=(List<Filial>) request.getSession().getAttribute("ListarFilialComboX");
                                        if (listacomboEjesX != null) {
                                        for (Filial cu : listacomboEjesXY) {
                                        %>
                                        <option value="<%=cu.getIdfilial() %>"    title="<%=cu.getDescripcion()%>"><%=cu.getDireccion() %></option>
                                        <%}}%>
                                        </select>  
                               </div>
            </div>
            </fieldset>
                        <hr>
                        <div id="topmenu">
                        <input type="hidden" name="estado" value="1"/>    
                        <input type="hidden" name="opt" value="16"/>
                        <input type="hidden" name="idEjeX" value="<%=idEjeX%>"/>
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=15&idEjeX=<%=idEjeX%>" class="btn">Cancelar</a>
                        </div>
            </fieldset>
        </form>
                    
                       
                       	</div>
	</div>
        
