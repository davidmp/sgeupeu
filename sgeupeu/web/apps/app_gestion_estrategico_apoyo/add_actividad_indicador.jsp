
<%@page import="sge.modelo.EstrategiaIndicador"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
var form = $('#addActividadIndicadorAdmin');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#indicadoresestrategias').show();
$('#indicadoresestrategias').html(data);
document.getElementById("addActividadIndicadorAdmin").reset();
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
            EstrategiaIndicador ei = null;
            ei = (EstrategiaIndicador) request.getSession().getAttribute("ActividadGetIDEstrategiaIndicador");
        %> 
 <div class="row-fluid">
		<div class="span6">
                    <form id="addActividadIndicadorAdmin" name="addActividadIndicadorAdmin" method="POST" action="<%=request.getContextPath()%>/GestionEstrategicoApoyo">
                    
                        <legend>Registro de Actividad</legend>
                        <div class="row-fluid">
                            <div class="span12">
                                <p><strong>Indicador</strong>:&nbsp;<%=ei.getIdIndicador_nombre()%></p>
                            </div>
                        </div>
					 
                                    <div class="row-fluid">
                                             <div class="span4">
                                                 <label>Número :</label>
                                                 <input type="number" name="nro" value="" class="span9" required=""/>
                                             </div>
                                             <div class="span4">
                                                 <label>Cantidad :</label>
                                                 <input type="number" name="cantidad" value="" class="span9" required=""/>
                                             </div>
                                             
                                             <div class="span4">
                                                 <label>Presupuesto :</label>
                                                 <input type="number" name="presupuesto" value="" class="span9" required=""/>
                                             </div>
                                         </div>
                                   
                                    <div class="row-fluid">
                                             <div class="span12">
                                                 
                             <table class="table">
                                 <tr>
                                     <td colspan="4"><label>Cronograma :</label></td>
                                 </tr>
                                <tr>
                                    <td>        
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox1" name="enero" value="1">Enero
                                    </label>
                                    </td>
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox2" name="febrero" value="1"> Febrero
                                    </label>      
                                    </td>
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="marzo" value="1"> Marzo
                                    </label>          
                                    </td>
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="abril" value="1"> Abril
                                    </label>         
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="mayo" value="1"> Mayo
                                    </label>      
                                    </td>  
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="junio" value="1"> Junio
                                    </label>        
                                    </td>  
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="julio" value="1"> Julio
                                    </label>         
                                    </td>
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="agosto" value="1"> Agosto
                                    </label>       
                                    </td>
                                </tr>
                                <tr>
                                      
                                      
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="setiembre" value="1"> Setiembre
                                    </label>     
                                    </td>  
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="octubre" value="1"> Octubre
                                    </label>         
                                    </td>
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="noviembre" value="1"> Noviembre
                                    </label>       
                                    </td>  
                                    <td>
                                    <label class="checkbox inline">
                                    <input type="checkbox" id="inlineCheckbox3" name="diciembre" value="1"> Diciembre
                                    </label>      
                                    </td>  
                                </tr>
                                
                            </table>
                                                 
                                             </div>
                                    
                                         </div>
                                    <div class="row-fluid">
                                              <div class="span6">
                                                 <label>Rubro :</label>
                                                 <input type="text" name="rubro" value="" required="" />
                                             </div>
                                             <div class="span6">
                                                 <label>Accion :</label>
                                                 <input type="text" name="accion" value="" required=""/>
                                             </div>
                                         </div>
                               
                            <div class="form-actions" id="topmenu" style="text-align: right;">
                                    <input type="hidden" name="idestrategiaIndicador" value="<%=ei.getIdestrategiaIndicador()%>" />
                                    <input type="hidden" name="opt" value="89" />
                                    <button type="submit" class="btn"><span class="icon-ok"></span>&nbsp;Guardar</button>
                                    <button class="btn" type="reset"><span class="icon-refresh"></span>&nbsp;Limpiar</button>
                                    <a  href="<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=88" class="btn"><div class="icon-remove"></div>&nbsp;Cerra </a>
                                            
                                    
				</div>
			
                        </form>
		</div>
	</div>