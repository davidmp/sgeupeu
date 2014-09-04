
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
 List<Ejeestrategico> ejeEs=null;
%>  
<div class="row-fluid">
    <div class="span2">
        Rep.
        <select style="width:130px" id="selecTipoReporte" name="selecTipoReporte" onchange="cambiarPagina()" >
            <option value="1">R. Evaluaci&oacute;n</option>       
<!--            <option value="2">Reporte Resumido de Actividades</option>       
            <option value="3">Actividades Vencidas</option>       -->
        </select>
    </div>
 <% 
 ArrayList lista=null;
 Iterator<Object> inter;
 %>
 
 
 
 
 
 
 
 
    <div class="span8">
        <form  id="form1" style="display: none" name="form1" action="../../EvaluacionGeneral" target="viewMain" >
        EAP
        <select style="width: 160px"  id="eap1" name="eap1" onchange="validarEjes('eap1','ideje1')">
            <%
            
            lista = (ArrayList)request.getSession().getAttribute("listarEapTempReporte");                        
            inter=lista.iterator();
         while(inter.hasNext()){
         Map datos=  (Map)inter.next();
            %>
            <option value='<%=datos.get("ideapfacultad")%>*<%=datos.get("idtipoarea")%>*<%=datos.get("codigo")%>'><%=datos.get("nombreeap")%> </option>
            <%  } %>
        </select>
        
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
        
        Eje
        <select style="width: 180px" id="ideje1" name="ideje1">
            <option value="0">Todos</option>
        </select>
        <input type="hidden" id="opt" name="opt" value="3"/>
        <input type="submit" value="Reportar" class="btn btn-mini btn-success"/>
        </form>
        
        
        
        <form  id="form2" style="display: none" name="form2" action="../../InformeActividadGeneral" target="viewMain" >
        EAP
        <select style="width: 160px"  id="eap2" name="eap2" onchange="validarEjes('eap2','ideje2')">
            <%
            lista=null;
            lista = (ArrayList)request.getSession().getAttribute("listarEapTempReporte");                        
            inter=lista.iterator();
         while(inter.hasNext()){
         Map datos=  (Map)inter.next();
            %>
            <option value='<%=datos.get("ideapfacultad")%>*<%=datos.get("idtipoarea")%>*<%=datos.get("codigo")%>'><%=datos.get("nombreeap")%> </option>
            <%  } %>
        </select>
        
        Periodo
        <select style="width: 80px" id="perido2" name="perido2">
            <%
   
    periodoM=(List<Periodometa>)request.getSession().getAttribute("listar_periodo_meta");            
           if(periodoM!=null){
            for(Periodometa per: periodoM){
            %>
            <option value="<%=per.getIdperiodometa() %>"><%=per.getPeriodo()%> </option>
            <% } } %>
        </select>
        
        Eje
        <select style="width: 180px" id="ideje2" name="ideje2">
            <option value="0">Todos</option>
        </select>
        <input type="hidden" id="opt" name="opt" value="5"/>
        <input type="submit" value="Reportar" class="btn btn-mini btn-success"/>
        </form>
        
        
        
        
        
        <form  id="form3" style="display: none" name="form3" action="../../InformeActividadGeneral" target="viewMain" >
        EAP
        <select style="width: 160px"  id="eap3" name="eap3" onchange="validarEjes('eap3','ideje3')">
            <%
            lista=null;
            lista = (ArrayList)request.getSession().getAttribute("listarEapTempReporte");                        
            inter=lista.iterator();
         while(inter.hasNext()){
         Map datos=  (Map)inter.next();
            %>
            <option value='<%=datos.get("ideapfacultad")%>*<%=datos.get("idtipoarea")%>*<%=datos.get("codigo")%>'><%=datos.get("nombreeap")%> </option>
            <%  } %>
        </select>
        
        Periodo
        <select style="width: 80px" id="perido3" name="perido3">
            <%
   
    periodoM=(List<Periodometa>)request.getSession().getAttribute("listar_periodo_meta");            
           if(periodoM!=null){
            for(Periodometa per: periodoM){
            %>
            <option value="<%=per.getIdperiodometa() %>"><%=per.getPeriodo()%> </option>
            <% } } %>
        </select>
        
        Eje
        <select style="width: 180px" id="ideje3" name="ideje3">
            <option value="0">Todos</option>
        </select>
        <input type="hidden" id="opt" name="opt" value="6"/>
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