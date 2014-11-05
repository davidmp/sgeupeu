
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
    }else{        
      $("#form2").attr("style","display: normal");
      $("#form1").attr("style","display: none");
    }
    
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

</script>   
<div >
<center>
<%
List<Periodometa> periodoM=null;
 List<Ejeestrategico> ejeEs=null;
%>  
<div class="row-fluid">
    <div class="span3">
        T. Reporte
        <select style="width: 180px" id="selecTipoReporte" name="selecTipoReporte" onchange="cambiarPagina()" >
            <option value="1">General de Actividades</option>
            
        </select>
    </div>
    
    
    
    
    <div class="span8">
        <form  id="form1" style="display: none" name="form1" action="../../IndicadorTarea" target="viewMain" >
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
        M.Inicio
        <select style="width: 100px" id="mesinicio1" name="mesinicio1" onchange="validarFecha()">
            <option value="1">Enero</option>
            <option value="2">Febrero</option>
            <option value="3">Marzo</option>
            <option value="4">Abril</option>
            <option value="5">Mayo</option>
            <option value="6">Junio</option>
            <option value="7">Julio</option>
            <option value="8">Agosto</option>
            <option value="9">Setiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>
        </select>
        M.Fin
        <select style="width: 100px" id="mesfin1" name="mesfin1" onchange="validarFecha()">
            <option value="1">Enero</option>
            <option value="2">Febrero</option>
            <option value="3">Marzo</option>
            <option value="4">Abril</option>
            <option value="5">Mayo</option>
            <option value="6">Junio</option>
            <option value="7">Julio</option>
            <option value="8">Agosto</option>
            <option value="9">Setiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>            
        </select>
        Eje
        <select style="width: 180px" id="ideje1" name="ideje1">
            <option value="0">Todos</option>
            <%

    ejeEs=(List<Ejeestrategico>)request.getSession().getAttribute("listar_eje");              
            if(ejeEs!=null){
                for(Ejeestrategico ejeE:ejeEs){
                            
            %>
            <option value="<%=ejeE.getIdejeestrategico() %>"> <%=ejeE.getNombre() %> </option>
            <% } } %>
        </select>
        <input type="hidden" id="opt" name="opt" value="3"/>
        <input type="submit" value="Reportar" class="btn btn-mini btn-success"/>
        </form>
        
        
        <form  id="form2" style="display: none" name="form2" action="../../IndicadorTarea" target="viewMain" >
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
        M.Inicio
        <select style="width: 100px" id="mesinicio2" name="mesinicio2" onchange="validarFecha2()">
            <option value="1">Enero</option>
            <option value="2">Febrero</option>
            <option value="3">Marzo</option>
            <option value="4">Abril</option>
            <option value="5">Mayo</option>
            <option value="6">Junio</option>
            <option value="7">Julio</option>
            <option value="8">Agosto</option>
            <option value="9">Setiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>
        </select>
        M.Fin
        <select style="width: 100px" id="mesfin2" name="mesfin2" onchange="validarFecha2()">
            <option value="1">Enero</option>
            <option value="2">Febrero</option>
            <option value="3">Marzo</option>
            <option value="4">Abril</option>
            <option value="5">Mayo</option>
            <option value="6">Junio</option>
            <option value="7">Julio</option>
            <option value="8">Agosto</option>
            <option value="9">Setiembre</option>
            <option value="10">Octubre</option>
            <option value="11">Noviembre</option>
            <option value="12">Diciembre</option>            
        </select>
        Eje
        <select style="width: 180px" id="ideje2" name="ideje2">
            <option value="0">Todos</option>
            <%

    ejeEs=(List<Ejeestrategico>)request.getSession().getAttribute("listar_eje");              
            if(ejeEs!=null){
                for(Ejeestrategico ejeE:ejeEs){
                            
            %>
            <option value="<%=ejeE.getIdejeestrategico() %>"> <%=ejeE.getNombre() %> </option>
            <% } } %>
        </select>
        <input type="hidden" id="opt" name="opt" value="4"/>
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

