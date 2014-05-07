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
    $("#topmenu a").click(function() {
    $("#reportTemporada").empty().append();
    $("#topmenu a").removeClass('current');
    $("#reportTemporada").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $("#reportTemporada").empty().append(html);
    }
    });
    return false;
    });
});

</script>             
       
         
            <form method="POST" action="<%=request.getContextPath()%>/GestionEstrategico" id="add" name="add">
                <legend>Registro de Temporadas</legend>
                 <fieldset>
                    <div class="row-fluid">
                		<div class="span3">
                                    <label>Fecha Inicio:</label>
                                    <input type="date" name="inicio" value="" />
                                    <label>Fecha Fin:</label>
                                    <input type="date" name="fin" value="" />
                                    
                                </div>
                            <div class="span3">
                                <label>Descripcion :</label>
                                <TEXTAREA COLS="5" ROWS="5" NAME="descripcion" required="required"></TEXTAREA>
                            </div>
                        <div class="span3"></div>
                        <div class="span3"></div>
                   </div>
                 </fieldset>
		   <hr>
                        <div id="topmenu">
                        <input type="hidden" name="estado" value="0"/>    
                        <input type="hidden" name="opt" value="27"/>
                        <button type="submit" class="btn btn-success"><div class="icon-ok icon-white"></div>&nbsp;Guardar</button>
                        <button type="reset" class="btn btn-warning"><div class="icon-refresh icon-white"></div>&nbsp;Limpiar</button>
                        
                        <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=18" class="btn"><div class="icon-remove"></div>&nbsp;Cancelar</a>
                        </div> 
                    
            </form>
                        
                        
     
		
                                    <div id="mensaje">
                                        
                                    </div>
  