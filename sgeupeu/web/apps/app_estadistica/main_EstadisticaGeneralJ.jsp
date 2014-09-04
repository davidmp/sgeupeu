

<%@page import="sge.modelo.Tipoarea"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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
 
<script type="text/javascript">
 $( document ).ready(function() {
$("#form1").attr("style","display: normal");
});   
function cambiarPagina(){
    var selectObjt=$("#selecTipoReporte").val();
   
    if(selectObjt=="1"){
        $("#form1").attr("style","display: normal");
        $("#form2").attr("style","display: none");
        $("#form3").attr("style","display: none");
    }else if(selectObjt=="2"){        
      $("#form2").attr("style","display: normal");
      $("#form1").attr("style","display: none");
      $("#form3").attr("style","display: none");
    }else{
      $("#form2").attr("style","display: none");
      $("#form1").attr("style","display: none");        
      $("#form3").attr("style","display: normal");        
    }
    
}
function validarEjes(eap, ideje){
    var datosv = ($("#"+eap).val()).split("*");
    var dd=datosv[2].substr(1,2);
    var ver=0;    
    if(!isNaN(dd)){
       ver=parseInt(dd);       
    }
    
    $.ajax({
        type: "GET",
        url: "../../EvaluacionGeneral",
        data: "opt=4&idtipoarea=" + datosv[1]+"&codigo="+ver,
        success: function(datos) {
            $("#"+ideje).html(datos);
        }
    });
}


function validarFecha(){   
       var mes1= parseInt($("#mesinicio1").val());
       var mes2= parseInt($("#mesfin1").val());
       if(mes1<=mes2){           
       }else{
           $("#mesfin1").val(mes1);
       }    
}
function validarFecha2(){   
       var mes1= parseInt($("#mesinicio2").val());
       var mes2= parseInt($("#mesfin2").val());
       if(mes1<=mes2){           
       }else{
           $("#mesfin2").val(mes1);
       }    
}

function validarFecha3(){  
       var mes1= parseInt($("#mesinicio3").val());
       var mes2= parseInt($("#mesfin3").val());
       if(mes1<=mes2){           
       }else{
           $("#mesfin3").val(mes1);
       }    
}

</script>   
<div >
<center>
<%
List<Periodometa> periodoM=null;
List<Tipoarea> tipoArea=null;
 List<Ejeestrategico> ejeEs=null;
%>  
<div class="row-fluid">
    <div class="span2">
        Rep.
        <select style="width:130px" id="selecTipoReporte" name="selecTipoReporte" onchange="cambiarPagina()" >
            <option value="1">R. Estadistico</option>             
        </select>
    </div>
 <% 
 ArrayList lista=null;
 Iterator<Object> inter;
 %>
 
 
 
 
 
 
 
 
    <div class="span8">
        <form  id="form1" style="display: none" name="form1" action="../../EstadisticaGeneral" target="viewMain" >        
        Periodo
        <select style="width: 80px" id="perido1" name="perido1">
            <%
   
    periodoM=(List<Periodometa>)request.getSession().getAttribute("listar_periodo_meta");            
           if(periodoM!=null){
            for(Periodometa per: periodoM){
            %>
            <option value="<%=per.getIdperiodometa() %>"><%=per.getPeriodo()%> </option>
            <% } } %>
        </select>
        Filial:
        <select style="width: 80px" id="subf" name="subf">
            <%
   List<Filial> fi=null;
    fi=(List<Filial>)request.getSession().getAttribute("listar_sed");            
           if(fi!=null){
            for(Filial dd: fi){
            %>
            <option value="<%=dd.getIdfilial() %>"><%=dd.getDireccion() %> </option>
            <% } } %>
        </select>
        
        Area
        <select style="width: 80px" id="tipoarea1" name="tipoarea1">
            <%
   
    tipoArea=(List<Tipoarea>)request.getSession().getAttribute("listar_tipoarea");            
           if(tipoArea!=null){
            for(Tipoarea per: tipoArea){
            %>
            <option value="<%=per.getIdtipoarea()%>"><%=per.getNombre()%> </option>
            <% } } %>
        </select>
        Eje        
        <select style="width: 180px" id="ideje1" name="ideje1">            
            <%

            ejeEs=(List<Ejeestrategico>)request.getSession().getAttribute("listar_eje");              
            if(ejeEs!=null){
                for(Ejeestrategico ejeE:ejeEs){
                            
            %>
            <option value="<%=ejeE.getIdejeestrategico() %>*<%=ejeE.getNombre() %>"> <%=ejeE.getNombre() %> </option>
            <% } } %>
        </select>
        
        
        <input type="hidden" id="opt" name="opt" value="31"/>
        <input type="submit" value="Reportar" class="btn btn-mini btn-success"/>
        </form>
     
    </div>
</div>
  
        
        
        
        
    
        
        
        
        
        
        
<div>
    <iframe width="99%" height="400px" style="border-color: #46a546; border: 0px" name="viewMain">
        
    </iframe>
</div>
</center>
</div>