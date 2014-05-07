<%-- 
    Document   : consolidados_filial
    Created on : 12/09/2013, 01:16:42 AM
    Author     : oscdmdz
--%>

<%@page import="sge.modelo.Avance"%>
<%@page import="sge.modelo.Usuario"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Facultad"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Periodometa"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Indicador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Indicadores</title>
        
    
        
        
            <%
            
    List<Ejeestrategico> ejeEs=null;
    ejeEs=(List<Ejeestrategico>)request.getSession().getAttribute("listar_eje");  
Ejeestrategico eje=(Ejeestrategico)request.getSession().getAttribute("eje");                            
    List<Indicador> indicador=null;
    indicador=(List<Indicador>)request.getSession().getAttribute("listaFilialConsolidado");
    
    Usuario w = null;
    w = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
    
     List<Periodometa> periodoM=null;
     periodoM=(List<Periodometa>)request.getSession().getAttribute("listar_periodo_meta");
        
     Avance av=(Avance)request.getSession().getAttribute("avanceMeta");
     Avance nrometa=(Avance)request.getSession().getAttribute("nro");   
     Periodometa idperidoM=(Periodometa)request.getSession().getAttribute("idperiodometa");         
    int cont=0;
    String valor="rojo";
    String valor1="bar-danger";
    int pr=0;   
    
    Filial filial=(Filial)request.getSession().getAttribute("sessionFilial");

    
    %>

    
    
<script type="text/javascript">
    
            var form9 = $('#periodo_3');
        form9.submit(function () {
        $.ajax({
        type: form9.attr('method'),
        url: form9.attr('action'),
        data: form9.serialize(),
        success: function (data) {
        $('#filial').show();
        $('#filial').html(data);
        document.getElementById("periodo_3").reset();
        document.getElementById("filial").reset(); 
        }
        }); 
        return false;
        });
        
        
                var form7 = $('#buscar_2');
        form7.submit(function () {

        $.ajax({
        type: form7.attr('method'),
        url: form7.attr('action'),
        data: form7.serialize(),
        success: function (data) {
        $('#filial').show();
        $('#filial').html(data);
        document.getElementById("filial").reset();
        document.getElementById("buscar_2").reset(); 
        }
        }); 
        return false;
        });
        
        
                var form8 = $('#eje_2');
        form8.submit(function () {

        $.ajax({
        type: form8.attr('method'),
        url: form8.attr('action'),
        data: form8.serialize(),
        success: function (data) {
        $('#filial').show();
        $('#filial').html(data);
        document.getElementById("filial").reset();
        document.getElementById("eje_2").reset(); 
        }
        }); 
        return false;
        });
        
        
            $(function () {
        $("[rel='tooltip']").tooltip();
    });
    
    
            $(function() {
      $('table').footable();
    });
    
    $(document).ready(function() {
    
    $("tbody a").click(function() {
    $(".tab-content").empty().append();
    $("tbody a").removeClass('current');
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
 
            
            
             <div class="row-fluid">
                 <div class="span3">
                     <form id="periodo_3" name="periodo_3"  action="<%=request.getContextPath()%>/Indicador" >
           
         <label>Periodo: <strong> <%if(idperidoM!=null){%><%=idperidoM.getPeriodo()%><%}else{%>-<%}%></strong></label>
          <p>  
          <select name="idperiodometa" class="span5" >
             <option value="0">-- --</option>  
            <%
            if(periodoM!=null){for(Periodometa pm: periodoM){%>
            <option value="<%=pm.getIdperiodometa()%>"><%=pm.getPeriodo()%></option>
            <%}}%>
           </select>&nbsp;&nbsp;<input type="submit" value="Aceptar" class="btn btn-mini btn-success"/>            
             
           <input type="hidden"  name="idusuario" value="<%if(w!=null){%><%=w.getId()%><%}else{%>0<%}%>" >    
           <input type="hidden"  name="idperiodometa" value="<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" >    
           <input type="hidden"  name="idfilial" value="<%=filial.getIdfilial()%>" >                 
           <input type="hidden"  name="opt" value="17" >
       </form> 
                 </div>

                        
           <div class="span4">                    
         <%if(idperidoM!=null){%>   

                  
       <form id="eje_2" name="eje_2"  action="<%=request.getContextPath()%>/Indicador?opt=26&idfilial=<%=filial.getIdfilial()%>&idperiodometa=<%=idperidoM.getIdperiodometa()%>" >
          <label>Eje Estratégico: <strong> <%if(eje!=null){%><%=eje.getNombre()%><%}else{%>-<%}%></strong></label>
          <p>  
          <select name="idtemporadaejeestrategico"  >
             <option value="0">-- --</option>                 
            <%
            if(ejeEs!=null){for(Ejeestrategico pm: ejeEs){%>
                  <option value="<%=pm.getIdtemporadaejeestrategico()%>"><%=pm.getNombre()%></option>
            <%}}%>
           </select>&nbsp;&nbsp;<input type="submit" value="Aceptar" class="btn btn-mini btn-success"/>           
      </form>               
        
        <%}%>                         
                </div>              
                        
                        
            <div class="span4">

 <form id="buscar_2" name="buscar_2"  action="<%=request.getContextPath()%>/Indicador" >
      <label>Buscar </label>

               <input placeholder="Buscar por nombre" name="valor" type="text" class="span9" style="margin: 0 auto;" data-provide="typeahead" data-items="7" 
           data-source='[<%if(indicador!=null){for(Indicador in: indicador){ %><%if(cont==1){%>,<%}%>"<%=in.getNombre()%>"<%cont=1;}}%>]'>&nbsp;&nbsp;<input type="submit" value="Aceptar" class="btn btn-mini btn-success"/>                           
                        <input type="hidden"  name="idusuario" value="<%if(w!=null){%><%=w.getId()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idperiodometa" value="<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idfilial" value="<%=filial.getIdfilial()%>" > 
                   
                   <input type="hidden"  name="opt" value="23" >


     </form>                 
                

              </div>
            </div>



 
               

<blockquote>
     <p class="text-success">Reporte de indicadores filial <strong><%=filial.getDescripcion()%></strong></p>      
</blockquote>       

<table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="100" >
      
        
      <thead>
          
                <tr>
                    <th>Nro</th>
                    <th>Estado</th>
                    <th>Progreso</th>
                    <th>Estratégia</th>
                    <th>Indicador</th>
                    <th>Instrumento</th>
                    <th>&nbsp;&nbsp;Meta&nbsp;&nbsp; </th>
                    <th>&nbsp;&nbsp;Avance&nbsp;&nbsp; </th>
                </tr>

      </thead>
<tbody>
    
    
 
                <%if(indicador!=null){for(Indicador in: indicador){ %>  
                           <%
         pr=(int)((((double)in.getTotalavance())/((double)in.getMeta()))*100);
        if(pr<100){if(pr>0){valor1="bar-warning";valor="amarillo";}else{valor1="bar-danger";valor="rojo";}}else{if(pr==100){valor1="bar-success";valor="verde";}else{valor1="bar-info";valor="azul";}}     
                           %>
                  <tr>
                    <td>
                    <br>
                      <p></p>   
                         <center>
                            <%=in.getNro()%>  
                         </center>  
                     </td>
                      <td> 

                 <center>
                     <p class="<%=valor%>" ></p>
                   </center>
                       </td>
                    <td>
                     <center>
                        <%=(int)((((double)in.getTotalavance())/((double)in.getMeta()))*100)+"%"%>
                     </center>                
                    <div class="progress progress-striped active">
                        <div class="bar <%=valor1%>" 
                        style="width: <%=(int)((((double)in.getTotalavance())/(double)in.getMeta())*100)+"%"%>;"></div>
                    </div>  
                    </td>
                    
                    <td><%=in.getEstrategia()%></td>
                    <td><%=in.getNombre()%></td>
                    <td>
                     <p></p>
                        <center>
                <a href="#" class="btn"  rel="tooltip" title="<%=in.getInstrumento()%>"><i class="icon-comment"></i></a>
                        </center>
                    </td>
                <%-- META inicio--%> 
                   
                        <td>
                             <p></p>
                        <center>                           
                        <%=in.getMeta()%> <%if(in.getIdtipometa()==2){%>%<%}%>
                        </center>
                        </td>
                       
                         <td>
                              <p></p>
                        <center>
                     <%=in.getTotalavance()%> <%if(in.getIdtipometa()==2){%>%<%}%>
                        </center>
                         </td>
                                            
                        
                        
            <%-- FIN avance--%>                                     
          
                </tr>
     <%}}%>
   
            </tbody>
            
            
        <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="11" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
        </tr>
      </tfoot>            
            

    </table>      
           
    </body>
</html>
