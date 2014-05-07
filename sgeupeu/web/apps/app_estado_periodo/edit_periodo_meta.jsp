<%@page import="sge.modelo.Temporada"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Periodometa"%>
<script type="text/javascript">
var form = $('#editPeriodoMeta');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#reportarPeridoMeta').show();
$('#reportarPeridoMeta').html(data);
document.getElementById("editPeriodoMeta").reset();
document.getElementById("reportarPeridoMeta").reset(); 

}
});
 
return false;
});

$(document).ready(function() {
    $("#topmenu a").click(function() {
    $("#reportarPeridoMeta").empty().append();
    $("#topmenu a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#reportarPeridoMeta").empty().append(html);
    }
    });
    return false;
    });
});

</script>
    </head>
    <%
Periodometa pm = null;
pm = (Periodometa) request.getSession().getAttribute("listaPeriodoMetaById");
    
%>
 
            <form name="editPeriodoMeta" id="editPeriodoMeta" method="POST" action="<%=request.getContextPath()%>/GestionEstrategico">
                <legend>Editar Periodo segun la temporada seleccionada</legend>
             <div class="row-fluid">   
            <div class="span3">
                <select name="" class="span4" onchange="valorperiodo(this.value);sumar();">
                    <option></option>
                    <%
                int a=0;
                List<Temporada> tem=(List<Temporada>) request.getSession().getAttribute("Listar_Rango_Temporada");
                if (tem != null) {
                for (Temporada v : tem) {
                int aa = Integer.parseInt(v.getInicio());
                int bb = Integer.parseInt(v.getFin());
                for (a=aa ; a<=bb; a++) {
                
                %>
                <option value="<%out.println(a);%>" ><%out.println(a);%></option>
                    
                    <%}}}%>
                </select>
                <select name="" class="span3" onchange="valornro(this.value);sumar();">
                    <option></option>
                    <%
                int g=0;
                int y=1;
                int z=4;
                
                for (g=y ; g<=z; g++) {
                
                %>
                <option value="<%out.println(g);%>" ><%out.println(g);%></option>
                    
                    <%}%>
                </select>
                <input type="text" name="" value="" class="text1 hidden-desktop" id="text1" />
                <input type="text" name="" value="" class="text2 hidden-desktop" id="text2" />
                <input type="text" name="periodo" value="<%=pm.getPeriodo()%>" class="text3 span4" id="text3" />
                
                
             </div>
            <div class="span3">
                <div id="topmenu">
                <input type="hidden" name="idperiodometa" value="<%=pm.getIdperiodometa()%>"/>    
                <input type="hidden" name="idtemporada" value="<%=pm.getIdtemporada()%>"/>
                <input type="hidden" name="estado" value="<%=pm.getEstado()%>"/>
                <input type="hidden" name="opt" value="68"/>
                    
                <button type="submit" class="btn"><div class="icon-ok"></div>&nbsp;Actualizar</button>
                
                <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=67" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                </div>
            </div>
            <div class="span3">
                
            </div>
                <div class="span3">
                
                </div>   
                
             </div>
            </form>
               
       
       