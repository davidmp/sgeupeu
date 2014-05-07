<%-- 
    Document   : indicadores
    Created on : 03/08/2013, 02:46:32 PM
    Author     : oscdmdz
--%>

<%@page import="sge.modelo.CicloAcademico"%>
<%@page import="sge.modelo.Periodo"%>
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

    
        
        
            <%
     

    Indicador name=(Indicador)request.getSession().getAttribute("indicador");
                  
    List<Ejeestrategico> ejeEs=null;
    ejeEs=(List<Ejeestrategico>)request.getSession().getAttribute("listar_eje"); 
    
    List<Indicador> indicador=null;
    indicador=(List<Indicador>)request.getSession().getAttribute("listar_indicador");
    Usuario w = null;
    w = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
     List<Periodometa> periodoM=null;
     periodoM=(List<Periodometa>)request.getSession().getAttribute("listar_periodo_meta");
     Facultad faU=(Facultad)request.getSession().getAttribute("facultadUsuario");
     Filial fiU=(Filial)request.getSession().getAttribute("filialUsuario");    
     Eap eU=(Eap)request.getSession().getAttribute("eapUsuario");          
     Avance av=(Avance)request.getSession().getAttribute("avanceMeta");
     Avance nrometa=(Avance)request.getSession().getAttribute("nro");   
     Periodometa idperidoM=(Periodometa)request.getSession().getAttribute("idperiodometa");         
     Ejeestrategico eje=(Ejeestrategico)request.getSession().getAttribute("eje");         
    int cont=0;

    %>

    
    
<script type="text/javascript">


function formindicador(numero) 
{
    var nro_indicador = document.getElementById("nro_indicador");
    nro_indicador.value = numero;

}

function formindicador_1(numero) 
{
    var nro_indicador_1 = document.getElementById("nro_indicador_1");
    nro_indicador_1.value = numero;

}

function formindicador_2(numero) 
{
    var nro_indicador_2 = document.getElementById("nro_indicador_2");
    nro_indicador_2.value = numero;

}

function formindicador_3(numero) 
{
    var nro_indicador_3 = document.getElementById("nro_indicador_3");
    nro_indicador_3.value = numero;

}

function formindicador_4(numero) 
{
    var nro_indicador_4 = document.getElementById("nro_indicador_4");
    nro_indicador_4.value = numero;

}


        var clase = $('.claseform');
        clase.submit(function () {
        $.ajax({
        type: clase.attr('method'),
        url: clase.attr('action'),
        data: clase.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        
        document.getElementById("claseform").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
        return false;
        }); 
        
        
        
        var clase1 = $('.claseform1');
        clase1.submit(function () {
        $.ajax({
        type: clase1.attr('method'),
        url: clase1.attr('action'),
        data: clase1.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("claseform1").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
        return false;
        }); 
        
        
 
        var clase2 = $('.claseform2');
        clase2.submit(function () {
        $.ajax({
        type: clase2.attr('method'),
        url: clase2.attr('action'),
        data: clase2.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("claseform2").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
        return false;
        }); 
        


        var clase3 = $('.claseform3');
        clase3.submit(function () {
        $.ajax({
        type: clase3.attr('method'),
        url: clase3.attr('action'),
        data: clase3.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("claseform3").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
        return false;
        }); 
        
        
        
        var clase4 = $('.claseform4');
        clase4.submit(function () {
        $.ajax({
        type: clase4.attr('method'),
        url: clase4.attr('action'),
        data: clase4.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("claseform4").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
        return false;
        }); 
        

         
         
         
         
         
var form = $('#periodo');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#waza').show();
$('#waza').html(data);
document.getElementById("addPersonafilial").reset();
document.getElementById("periodo").reset(); 
}
}); 
return false;
});


var form7 = $('#buscar');
form7.submit(function () {
 
$.ajax({
type: form7.attr('method'),
url: form7.attr('action'),
data: form7.serialize(),
success: function (data) {
$('#waza').show();
$('#waza').html(data);
document.getElementById("waza").reset();
document.getElementById("buscar").reset(); 
}
}); 
return false;
});


var form8 = $('#eje');
form8.submit(function () {
 
$.ajax({
type: form8.attr('method'),
url: form8.attr('action'),
data: form8.serialize(),
success: function (data) {
$('#waza').show();
$('#waza').html(data);

document.getElementById("waza").reset();
document.getElementById("eje").reset(); 
}
}); 
return false;
});





       function getFrameByName(name) {
  for (var i = 0; i < frames.length; i++)
    if (frames[i].name === name)
      return frames[i];
 
  return null;
}
 
/* Same as the one defined in OpenJS */
function uploadDone(name) {
   var frame = getFrameByName(name);
   if (frame) {
     ret = frame.document.getElementsByTagName("body")[0].innerHTML;
 
     /* If we got JSON, try to inspect it and display the result */
     if (ret.length) {
       /* Convert from JSON to Javascript object */  
       var json = eval("("+ret+")");
 
     }
  }
}



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

function validarNumeroMeta(numero){
    
        var validar=false;
    if(isNaN($("#meta"+numero).val())){                    
            alert("Se necesita un numero positivo o el valor cero");
    }else{
        if(($("#meta"+numero).val()).length<10){
        if($("#meta"+numero).val()>=0){ 
            validar=true;
            $("#btnmeta"+numero).attr('disabled', true);            
        }else{ alert("No puede ser un valor negativo!!"); }
        }else{alert("La cantidad de caracteres debe ser menor a 10!!");}
        }
        return validar;
}


function validarNumeroAvance(numero){
    if(isNaN($("#prependedInput"+numero).val())){        
       alert("Se necesita un numero positivo o el valor cero");
    }else{
        if($("#prependedInput"+numero).val()<0){
            alert("No puede ser un valor negativo!!");
        }else{ $("#claseform2"+numero).submit(); 
               $("#btnavance"+numero).attr('disabled', true); 
            }
        }
}

</script>   
 <% 
 Periodometa tempMetaindicador=null;
 tempMetaindicador=(Periodometa)request.getSession().getAttribute("idperiodometa");
 
  Ejeestrategico tempEjeEstrategico=null;
 tempEjeEstrategico=(Ejeestrategico)request.getSession().getAttribute("eje");
 
 %>   
 <div class="row-fluid">
     <div class="span3">
     <form id="periodo" name="periodo"  action="<%=request.getContextPath()%>/Indicador?opt=2&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>&idtemporadaejeestrategico=<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>" > 
             <label>Periodo: <strong> <%if(idperidoM!=null){%><%=idperidoM.getPeriodo()%><%}else{%>-<%}%></strong></label>
          <p>  
          <select name="idperiodometa" class="span7" >
             <option value="0">-- --</option>  
            <%
            if(periodoM!=null){for(Periodometa pm: periodoM){%>
            <option value="<%=pm.getIdperiodometa()%>" <% if(tempMetaindicador!=null){if(pm.getIdperiodometa()==tempMetaindicador.getIdperiodometa()){out.print("selected");}}  %>   ><%=pm.getPeriodo()%></option>
            <%}}%>
           </select>&nbsp;&nbsp;<input type="submit" value="Aceptar" class="btn btn-mini btn-success"/>           
      </form> 
     </div> 
     <div class="span4">
          <%if(idperidoM!=null){%>   
       
                  
       <form id="eje" name="eje"  action="<%=request.getContextPath()%>/Indicador?opt=24&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>&idperiodometa=<%=idperidoM.getIdperiodometa()%>" >
           <label>Eje Estratégico: <strong> <%if(eje!=null){%><%=eje.getNombre()%><%}else{%>-<%}%></strong></label>
          <p>  
          <select name="idtemporadaejeestrategico" >
             <option value="0">-- --</option>                 
            <%
            if(ejeEs!=null){for(Ejeestrategico pm: ejeEs){%>
                  <option value="<%=pm.getIdtemporadaejeestrategico()%>"  <% if(tempEjeEstrategico!=null){if(pm.getIdtemporadaejeestrategico()==tempEjeEstrategico.getIdtemporadaejeestrategico()){out.print("selected");}}  %> ><%=pm.getNombre()%></option>
            <%}}%>
           </select>&nbsp;&nbsp;<input type="submit" value="Aceptar" class="btn btn-mini btn-success"/>           
      </form>               
             
        <%}%> 
     </div> 
        <div class="span4" style="<%if(eje!=null){ out.print("display: normal"); }else{ out.print("display: none");}%> "  >
         <label>Buscar Indicador</label>
         <form id="buscar" name="buscar"  action="<%=request.getContextPath()%>/Indicador" >
                        <input type="hidden"  name="idusuario" value="<%if(w!=null){%><%=w.getIdusuario()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idperiodometa" value="<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" >
                        <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" >
                        <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" > 
                        <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>"> 
                        <input type="hidden"  name="name" value="<%if(name!=null){%><%=name.getNombre()%><%}%>" >
                       
                    <input type="hidden"  name="opt" value="11" >               
                    <input placeholder="Buscar por nombre" name="valor" type="text" class="span7" data-provide="typeahead" data-items="7" 
           data-source='[<%if(indicador!=null){for(Indicador in: indicador){ %><%if(cont==1){%>,<%}%>"<%=in.getNombre()%>"<%cont=1;}}%>]'>&nbsp;&nbsp;<input type="submit" value="Aceptar" class="btn btn-mini btn-success"/>                            
           

        </form>  
     </div> 
    
 </div>    


         

 
    <blockquote>
    <p class="text-success">Reporte de indicadores EAP <strong><%=eU.getNombre()%></strong></p>  
    <small>Facultad <strong><%=faU.getNombre()%></strong> </small>
    <small>Filial <strong><%=fiU.getDescripcion()%></strong> </small>
    </blockquote>                 
       
    
    
    
    
    

        
      <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="100" >
        
      <thead>
          
                <tr>
                    
                    <th rowspan="2">Estado</th>
                    <th rowspan="2">Progreso</th>                    
                    <th rowspan="2">Estratégia</th>
                    <th rowspan="2">C&oacute;digo</th>                    
                    <th rowspan="2">Indicador</th>
                    <th rowspan="2">Instrumento</th>
                    <th rowspan="2">Meta</th>
                    <th colspan="2">Avances</th>
                    <th rowspan="2">POA</th>
                    <th rowspan="2">Evidencia</th>
                </tr>
          
                <tr>
                    <td style="width: 6%;">Ciclo</td>
                    <td>Avance</td>
                </tr>

      </thead>
<tbody>
    

    
            
<%if(indicador!=null){for(Indicador in: indicador){ %>                
                  <tr>
                    
                      <td>   
                      <br>  
                          <center>
                        <p <%if(in.getSemaforo()==0){%>class="rojo" <%}if(in.getSemaforo()==1){%>class="amarillo"<%}if(in.getSemaforo()==2){%>class="verde"<%}if(in.getSemaforo()==3){%>class="azul"<%}%>></p>
                          </center>
                        </td>
                        <td>
                        <br>
                     <center>
                       
                        <%=(int)((((double)in.getProgreso())/((double)in.getMeta()))*100)+"%"%>
                     </center>                
                    <div class="progress progress-striped active">
                        <div class="bar <%if(in.getSemaforo()==0){%> bar-danger <%}if(in.getSemaforo()==1){%> bar-warning <%}if(in.getSemaforo()==2){%> bar-success <%}if(in.getSemaforo()==3){%> bar-info <%}%>" 
                        style="width: <%=(int)((((double)in.getProgreso())/(double)in.getMeta())*100)+"%"%>;"></div>
                    </div>                    
                    </td>
                    
                    <td><%=in.getEstrategia()%></td>
                    <td>
                    <br>
                         <center>
                            <%=in.getCodigo() %>  
                         </center>  
                     </td>                    
                    <td><%=in.getNombre()%></td>
                    <td>
                     <p></p>
                     <br>
                        <center>
                            <button class="btn"  rel="tooltip" title="<%=in.getInstrumento()%>"><i class="icon-comment"></i></button>
                        </center>
                    </td>
                    
                    
                    
                    
  <%-- META inicio--%> 
                   
                  <td>                            
                        <% Periodo pdo=null;                        
                        pdo=(Periodo)request.getSession().getAttribute("SesPeriodoVigente");                       
                        %>
                          <% { %>  
                          <form  class="claseform" id="claseform" onsubmit="return validarNumeroMeta(<%=in.getNro()%>)" name="claseform" action="<%=request.getContextPath()%>/Indicador"  method="POST">
                            <center>
                            <p></p>
                            <p></p>
                            <br/>
                        <input type="hidden"  name="nro_indicador" id="nro_indicador" >
                        <%if(in.getIdtipometa()==2){%>                             
                        <div class=" input-append">
                        <input class="span5" type="text" id="meta<%=in.getNro()%>" name="meta<%=in.getNro()%>" placeholder="Meta" value="<%=in.getMeta()%>" <%if(in.getEstadometa()==0){%>disabled<%}%>>
                        <span class="add-on">%</span>
                        </div>                           
                        <%}else{%>
                        <input   type="text" class="input-mini" id="meta<%=in.getNro()%>" name="meta<%=in.getNro()%>" placeholder="Meta" value="<%=in.getMeta()%>" <%if(in.getEstadometa()==0){%>disabled<%}%>>
                        <%}%>
                        <%if(in.getEstadometa()!=0){%>                                     
                        <input type="hidden"  name="idestrategiaindicador<%=in.getNro()%>" value="<%=in.getIdestrategiaindicador()%>" > 
                        <input type="hidden"  name="idmeta<%=in.getNro()%>" value="<%=in.getIdmeta()%>" >                           
                        <input type="hidden"  name="idusuario" value="<%if(w!=null){%><%=w.getIdusuario()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idperiodometa" value="<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" >
                        <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" >
                        <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" >                            
                        <input type="hidden"  name="opt<%=in.getNro()%>" value="3" >
                        <input type="hidden"  name="idperiodo" value="<%=pdo.getIdperiodo() %>" >
                        <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>">     
                        <input type="hidden"  name="valor" value="<%if(name!=null){%><%=name.getNombre()%><%}%>" >

                        <% } %>

                        <%if(in.getEstadometa()!=0){%>
                        <input type="submit" onmouseover="formindicador(<%=in.getNro()%>)"  name="btnmeta<%=in.getNro()%>" id="btnmeta<%=in.getNro()%>"   value="Aceptar" class="btn btn-mini btn-success"/>
                        <%}%>

                        </center>
                        </form>
                        <%}%> 
                        
                 </td>
                       
         <%-- META FIN--%>   
         
         
         
         
        <%-- INICIO avance--%>
                       
                    <td>
                        <% 
                        CicloAcademico ca=null;
                        ca=(CicloAcademico)request.getSession().getAttribute("cicloAcademicoActivoSess");
                        %>
                        <form class="claseform1" name="claseform1"  action="<%=request.getContextPath()%>/Indicador" method="POST">    
                        <center>         
                            <center><p class="text-info"><%if(nrometa!=null){%><%if(in.getNro()==nrometa.getIdmeta()){%><%=nrometa.getId_ciclo() %><% }else{%>-<%}}else{%>-<%}%></p></center>    
                            <select class="span12" name="nro_avance<%=in.getNro()%>">
                           <option value="<%=ca.getId_ciclo() %>"><%=ca.getEtiqueta() %></option>
                            </select>
                          <input type="hidden"  name="valor" value="<%if(name!=null){%><%=name.getNombre()%><%}%>" > 
                         <input type="hidden"  name="nro_indicador_1" id="nro_indicador_1" >
                        <input type="hidden"  name="nro1<%=in.getNro()%>" value="<%=in.getNro()%>" >
                        <input type="hidden"  name="idmeta<%=in.getNro()%>" value="<%=in.getIdmeta()%>" >
                        <input type="hidden"  name="idestrategiaindicador<%=in.getNro()%>" value="<%=in.getIdestrategiaindicador()%>" >                            
                        <input type="hidden"  name="idusuario" value="<%if(w!=null){%><%=w.getIdusuario()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idperiodometa" value="<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" >
                        <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" >
                        <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" >                            
                        <input type="hidden"  name="opt<%=in.getNro()%>" value="7" >
                       <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>">   
                         <%if(in.getMeta()!=0){%>
                           <%if(in.getEstadoavance()!=0){%>
                           <input type="submit"  onmouseover="formindicador_1(<%=in.getNro()%>)" value="Aceptar" class="btn btn-mini btn-success"/>
                        <%}%>
                        <%}%>
         
                        </center> 
                        </form>   
                    </td>
                    
                       
                        
                    <td >
                        <form class="claseform2" name="claseform2" id="claseform2<%=in.getNro()%>" action="<%=request.getContextPath()%>/Indicador" method="POST">    
                           <center>


                        <center><p class="text-info">Total: <%=in.getTotalavance()%> <%if(in.getIdtipometa()==2){%>%<%}%></p></center>
                        <input type="hidden"  name="nro_indicador_2" id="nro_indicador_2" >
                        <%if(in.getIdtipometa()==2) { %>
                        <div class="input-prepend input-append" >                        
                        <span class="add-on"><%if(nrometa!=null){%><%if(in.getNro()==nrometa.getIdmeta()){%># <%=nrometa.getId_ciclo()%> :<%}}%></span>
                        <input class="span5" type="text" id="prependedInput<%=in.getNro()%>" name="avance<%=in.getNro()%>"  type="text" placeholder="" value="<%if(av!=null) {out.print(in.getTotalavance()); }else{%>0<%}%> ">
                        <span class="add-on">%</span>
                        </div>    
                        <% }else { %>

                        <div class="input-prepend" >
                        
                        <span class="add-on"><%if(nrometa!=null){%><%if(in.getNro()==nrometa.getIdmeta()){%># <%=nrometa.getId_ciclo()%> :<%}}%></span>
                        <input class="span5" type="text" id="prependedInput<%=in.getNro()%>" name="avance<%=in.getNro()%>"  placeholder="" value="<%if(av!=null){ out.print(in.getTotalavance()); }else{%>0<%}%> ">
                        </div>  
                        <%}%> 
                        <input type="hidden"  name="valor" value="<%if(name!=null){%><%=name.getNombre()%><%}%>" >
                         <input type="hidden"  name="nro_avance<%=in.getNro()%>" value="<%if(nrometa!=null){%><%=nrometa.getId_ciclo()%><%}else{%>0<%}%>" >
                           <input type="hidden"  name="idmeta<%=in.getNro()%>" value="<%=in.getIdmeta()%>" >
                           <input type="hidden"  name="idestrategiaindicador<%=in.getNro()%>" value="<%=in.getIdestrategiaindicador()%>" >                          
                           <input type="hidden"  name="idusuario" value="<%if(w!=null){%><%=w.getIdusuario()%><%}else{%>0<%}%>" >    
                           <input type="hidden"  name="idperiodometa" value="<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" >    
                           <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" >
                           <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" >
                           <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" >
                           <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>">
                           <input type="hidden"  name="idavance<%=in.getNro()%>" value="<%if(av!=null){%><%=av.getIdavance()%><%}else{%>0<%}%>" > 
                           <input type="hidden"  name="opt<%=in.getNro()%>" value="9" >
                           <%if(in.getMeta()!=0){%>
                            <%if(in.getEstadoavance()!=0){%>
                            
                            
                            
                            <input type="button" onclick="validarNumeroAvance(<%=in.getNro()%>)" id="btnavance<%=in.getNro()%>" name="btnavance<%=in.getNro()%>" onmouseover="formindicador_2(<%=in.getNro()%>)" value="Aceptar" <%if(nrometa!=null){if(in.getNro()==nrometa.getIdmeta()){out.print("enabled"); }else{out.print("disabled");}}else{out.print("disabled");}%> class="btn btn-mini btn-success"/>
                           <%}%>
                           <%}%>
                            

                               </center> 
                         </form>   
                    </td>
                                            
                        
                        
            <%-- FIN avance--%>                                     
            
                 <td>   
                <p></p>
                 <br>
                    
                <%if(in.getEstadoavance()!=0){%>  
                <%if(in.getMeta()!=0){%> 
                         <form class="claseform3" name="claseform3" action="<%=request.getContextPath()%>/Indicador?opt=12&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>&idperiodometa=<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" method="POST">    
                         <input type="hidden"  name="nro_indicador_3" id="nro_indicador_3" >
                         <input type="hidden"  name="idestrategiaindicador<%=in.getNro()%>" value="<%=in.getIdestrategiaindicador()%>" >     
                         <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>">  
                         <input type="hidden"  name="idmeta<%=in.getNro()%>" value="<%=in.getIdmeta()%>" >   
                          <center>   
                         <button class="btn" onmouseover="formindicador_3(<%=in.getNro()%>);" type="submit"><i class="icon-book"></i></button>                       
                          </center>
                         </form>
                  <%}else{%>
                    <center>  
                        <button class="btn" onmouseover="" type="submit" data-toggle="modal" target="_blank" rel="tooltip" title="Es necesario ingresar la meta"><i class="icon-book"></i></button> 
                                            
                    </center>   
                     <%}%>
                    <%}else{%>
                    <center>   
                         <button class="btn" onmouseover="" type="submit" data-toggle="modal" target="_blank" rel="tooltip" title="Se encuentra inavilitado"><i class="icon-book"></i></button>                          
                    </center>   
                     <%}%> 
                 </td>       
                   
   <% //Inicio Evidencia
   %>
                        <td>
                        <p></p>
                         <br>
                            <%if(in.getEstadoavance()!=0){%>   
                            <%if(in.getTotalavance()!=0){%>  

                                 <form class="claseform4" name="claseform4" action="<%=request.getContextPath()%>/Indicador?opt=21&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>&idperiodometa=<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" method="POST">    
                                 <input type="hidden"  name="nro_indicador_4" id="nro_indicador_4" >
                                 <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>">     
                                 <input type="hidden"  name="idestrategiaindicador<%=in.getNro()%>" value="<%=in.getIdestrategiaindicador()%>" >    
                                 <input type="hidden"  name="idmeta<%=in.getNro()%>" value="<%=in.getIdmeta()%>" >   
                                 <input type="hidden"  name="idavancevalida<%=in.getNro()%>" value="<%=in.getIdavancevalida() %>" >   
                                  <center>   
                                 <button class="btn" onmouseover="formindicador_4(<%=in.getNro()%>)" type="submit"><i class="icon-facetime-video"></i></button>                       
                                  </center>
                                 </form>                 
                           <%}else{%>
                            <center>           
                           <button class="btn"  type="submit" data-toggle="modal" target="_blank" rel="tooltip" title="Es necesario ingresar al menos un avance"><i class="icon-facetime-video"></i></button>                                                              
                             </center>   
                             <%}%>
                          <% }else{%>
                            <center> 
                             <button class="btn"  type="submit" data-toggle="modal" target="_blank" rel="tooltip" title="Se encuentra inavilitado"><i class="icon-facetime-video"></i></button>                                                                     
                          </center>   
                             <%}%>             
                            </td>
          
                </tr>
     <%}}%>
   
            </tbody>
            
       <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="11" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
        </tr>
      </tfoot>  
    </table>      

