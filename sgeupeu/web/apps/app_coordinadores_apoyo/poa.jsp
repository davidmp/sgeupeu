<%-- 
    Document   : poa
    Created on : 05/09/2013, 09:46:52 PM
    Author     : oscdmdz
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="sge.modelo.Facultad"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Variables"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Periodometa"%>
<%@page import="sge.modelo.Periodo"%>
<%@page import="sge.modelo.Meta"%>
<%@page import="sge.modelo.Indicador"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Actividad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        
              
             <%
                Indicador name=(Indicador)request.getSession().getAttribute("indicador");
                List<Actividad> listaActividad=null;
                List<Actividad> listaActividadIndicador=null;
                listaActividad=(List<Actividad>)request.getSession().getAttribute("listaActividad"); 
                listaActividadIndicador=(List<Actividad>)request.getSession().getAttribute("listaActividadIndicador"); 
                Indicador  in =(Indicador)request.getSession().getAttribute("estrategiaIndicador"); 
                Periodometa  p =(Periodometa)request.getSession().getAttribute("periodoMeta"); 
                Facultad faU=(Facultad)request.getSession().getAttribute("facultadUsuario");
                Filial fiU=(Filial)request.getSession().getAttribute("filialUsuario");    
                Eap eU=(Eap)request.getSession().getAttribute("eapUsuario"); 
                Variables vr=(Variables)request.getSession().getAttribute("variable");
                Ejeestrategico eje=(Ejeestrategico)request.getSession().getAttribute("eje"); 
                double suma =0;
                int estadoPoa=Integer.parseInt(request.getSession().getAttribute("estadoPOA").toString());
                sge.modelo.Usuario usuSess=(sge.modelo.Usuario)request.getSession().getAttribute("listaPerfilUsuario");
                int idTipoAreaPri=usuSess.getIdtipoarea();
                int idFilial=Integer.parseInt(usuSess.getIdfilial());
     %> 
     
 <script type="text/javascript">

        var form = $('#addactividad');
        var regex = /^\d+(?:\.\d{0,2})$/;
        var regex2 = /^(?:\+|-)?\d+$/;
        
        form.submit(function () {

           
      
        if (regex.test(($("#presupuesto").val()).trim()) || regex2.test(($("#presupuesto").val()).trim())){
         
        $.ajax({
        type: form.attr('method'),
        url: form.attr('action'),
        data: form.serialize(),
        success: function (data) {
        $("#close").click();
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("addactividad").reset();        
        document.getElementById("waza").reset(); 
        }
        });            
        }else{
            alert("El presupuesto debe ser un valor numerico!");
        }
       
        return false;
        });
        
        
        var form1 = $('#atras');
        form1.submit(function () {
        $.ajax({
        type: form1.attr('method'),
        url: form1.attr('action'),
        data: form1.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("atras").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
        return false;
        });
        
        var form2 = $('.eliminar');
        form2.submit(function () {
        $.ajax({
        type: form2.attr('method'),
        url: form2.attr('action'),
        data: form2.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("eliminar").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
        return false;
        });
        
        var form3 = $(".editar");
        form3.submit(function () {
            
        if (regex.test(($("#presupuestoxx").val()).trim()) || regex2.test(($("#presupuestoxx").val()).trim())){
        $.ajax({
        type: form3.attr('method'),
        url: form3.attr('action'),
        data: form3.serialize(),
        success: function (data) {      
        $("#closeid").click();
        $('#waza').show();
        $('#waza').html(data);        
        document.getElementById("editar").reset();      
        document.getElementById("waza").reset(); 
            }
        });
        }else{            
            alert("El presupuesto debe ser un valor numerico!"); 
        }
        
        return false;
        });
        
                
</script>

           <script type="text/javascript">

function formindicador(numero) 
{
    var nro_indicador = document.getElementById("nro_indicador");
    nro_indicador.value = numero;
}


function editarActividad(nombre, cant, nro, pres , rub, ene, feb, mar, abr, may, jun, jul, ago, set, oct, nov, dic, responsable, idPeriodometa, idMeta, idEstrategiaindicador, idEapfacultad, idActividad, idCuenta, idTipoAreaPri, idFilial){

           $("#accionxx").html(nombre);
           $("#cantidadxx").val(cant);          
           if(ene==1){
                  $("#inlineCheckbox1xx").attr('checked',true);}else{$("#inlineCheckbox1xx").attr('checked',false);}
           if(feb==1){
                  $("#inlineCheckbox2xx").attr('checked',true);
                      }else{$("#inlineCheckbox2xx").attr('checked',false);}
           if(mar==1){
                  $("#inlineCheckbox3xx").attr('checked',true);
                     }else{$("#inlineCheckbox3xx").attr('checked',false);}
           if(abr==1){
                  $("#inlineCheckbox4xx").attr('checked',true);
                        }else{$("#inlineCheckbox4xx").attr('checked',false);}
           if(may==1){
                  $("#inlineCheckbox5xx").attr('checked',true);
                        }else{$("#inlineCheckbox5xx").attr('checked',false);}
           if(jun==1){
                  $("#inlineCheckbox6xx").attr('checked',true);
                        }else{$("#inlineCheckbox6xx").attr('checked',false);}
           if(jul==1){
                  $("#inlineCheckbox7xx").attr('checked',true);
                         }else{$("#inlineCheckbox7xx").attr('checked',false);}
           if(ago==1){
                  $("#inlineCheckbox8xx").attr('checked',true);
                         }else{$("#inlineCheckbox8xx").attr('checked',false);}
           if(set==1){
                   $("#inlineCheckbox9xx").attr('checked',true);
                        }else{$("#inlineCheckbox9xx").attr('checked',false);}
           if(oct==1){
                  $("#inlineCheckbox10xx").attr('checked',true);
                        }else{$("#inlineCheckbox10xx").attr('checked',false);}
           if(nov==1){
                  $("#inlineCheckbox11xx").attr('checked',true);
              }else{$("#inlineCheckbox11xx").attr('checked',false);}
            if(dic==1){
                  $("#inlineCheckbox12xx").attr('checked',true);
              }else{$("#inlineCheckbox12xx").attr('checked',false);}
           $("#presupuestoxx").val(pres);
           
           $("#responsablexx").val(responsable);
           
           $("#nro_indicador_3xx").val(nro);
           $("#idactividadxx").val(idActividad);
           $("#idperiodometaxx").val(idPeriodometa);
           $("#idmetaxx").val(idMeta);
           $("#idestrategiaindicadorxx").val(idEstrategiaindicador);
           $("#ideapfacultadxx").val(idEapfacultad);
           //idCuenta, idTipoAreaPri, idFilial
           $("#rubroxx").val(""); 
           $("#rubronombrexx").val("");
            $.ajax({
                type: "GET",
                url: "../../IndicadorApoyo",
                data: "opt=33&idcuenta="+idCuenta,
                success: function(datos) {
                    $("#rubroxx").val(parseInt(datos.toString())+"*"+rub);  
                    $("#rubronombrexx").val(rub);
                    $.ajax({
                        type: "GET",
                        url: "../../IndicadorApoyo",
                        data: "opt=31&idtipoarea="+idTipoAreaPri+"&idfilial="+idFilial+"&rubro="+parseInt(datos.toString()),
                        success: function(datos) {
                            $("#subrubroxx").html(datos);
                            $("#subrubroxx").val(idCuenta); 
                        }
                    });                     
                }
            });           
    } 
    
function validarNumeroDecimales(){
    if(isNaN($("#meta").val())){                    
            alert("Se necesita un numero positivo o el valor cero");
    }         
}

function subRubros(idTipoArea, idFilial){
    $("#subrubro").html("");    
    var dato=$("#rubro").val();
    var res = dato.split("*"); 
    $("#rubronombre").val(res[1]);
    $.ajax({
        type: "GET",
        url: "../../IndicadorApoyo",
        data: "opt=31&idtipoarea="+idTipoArea+"&idfilial="+idFilial+"&rubro="+res[0],
        success: function(datos) {
            $("#subrubro").html(datos);
        }
    });       
}
function extraeSaldo(idPeriodo, idEapFacultad){
    $("#saldoreal").html("");
   // alert(idPeriodo+" - "+idEapFacultad+"- "+ $("#subrubro").val());
    $.ajax({
        type: "GET",
        url: "../../IndicadorApoyo",
        data: "opt=32&idPeriodo="+idPeriodo+"&idEapFacultad="+idEapFacultad+"&subrubro="+$("#subrubro").val(),
        success: function(datos) {
            $("#saldoreal").html(datos);
        }
    });       
}
function subRubrosxx(idTipoArea, idFilial){
    $("#subrubroxx").html("");    
    var dato=$("#rubroxx").val();
    var res = dato.split("*"); 
    $("#rubronombrexx").val(res[1]);
    $.ajax({
        type: "GET",
        url: "../../IndicadorApoyo",
        data: "opt=31&idtipoarea="+idTipoArea+"&idfilial="+idFilial+"&rubro="+res[0],
        success: function(datos) {
            $("#subrubroxx").html(datos);
        }
    });       
}
function extraeSaldoxx(idPeriodo, idEapFacultad){
    $("#saldorealxx").html("");
   // alert(idPeriodo+" - "+idEapFacultad+"- "+ $("#subrubro").val());
    $.ajax({
        type: "GET",
        url: "../../IndicadorApoyo",
        data: "opt=32&idPeriodo="+idPeriodo+"&idEapFacultad="+idEapFacultad+"&subrubro="+$("#subrubroxx").val(),
        success: function(datos) {
            $("#saldorealxx").html(datos);
        }
    });       
}



 </script>
    </head>
    <body>
        

        <div class="container-fluid">
	<div class="row-fluid">
           
	<div class="span12">
                    
                    
         
         <form id="addactividad" name="addactividad" action="<%=request.getContextPath()%>/IndicadorApoyo"  method="POST">     
         
          <div  id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" id="close" aria-hidden="true">×</button>
                <center><h4 id="myModalLabel"><p>Nueva Actividad</p></h4></center>
          </div>
                <div class="modal-body">
                  
                    <table class="table table-hover">

                        
                        <tr>
                        <td><h6><p >&nbsp;<i class="icon-edit"></i>&nbsp;&nbsp;&nbsp;Acción :</p></h6></td><td>
                            <textarea rows="2"  type="text" name="accion" id="accion" placeholder="Acción"></textarea>
                        </td>
                        </tr>
                        <tr>
                            <td><h6><p >&nbsp;<i class="icon-edit"></i>&nbsp;&nbsp;&nbsp;Cantidad : </p></h6></td><td><input type="text" name="cantidad" placeholder="Cantidad"></td>
                        </tr><tr>   
                        <td><h6><p >&nbsp;<i class="icon-edit"></i>&nbsp;Cronograma </p></h6></td>
                        <td>
                            <table>
                                <tr>
                                    <td>        
                                <label class="checkbox inline">
                                <input type="checkbox" id="inlineCheckbox1" name="enero" value="1">Enero
                                </label></td>
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
      
                        </td>   
                        </tr>
                        
                        <tr>
                            <td><h6><p >&nbsp;<i class="icon-edit"></i>Rubro </p></h6></td>
                            <td>                            
                                <table>
                                    <tr>
                                        <td>
                                            <%
                ArrayList lista=null;
                lista = (ArrayList)request.getSession().getAttribute("sesionNivelFinan");

               
                Iterator<Object> inter=lista.iterator();
                                            %>                                            
                                            <select name="rubro" id="rubro" style="width: 140px" onclick="subRubros('<%=idTipoAreaPri%>', '<%=idFilial%>')">
                                            <%    
                                            while(inter.hasNext()){
                                                Map datos=  (Map)inter.next();                                             
                                            %>
                                            <option value="<%=datos.get("idNivelFinanciero")%>*<%=datos.get("nombre")%>"><%=datos.get("nombre")%></option>
                                            <% } %>
                                        </select>                                                                                  
                                        </td>
                                        <td> 
                                        <input type="hidden" name="rubronombre" id="rubronombre" value="">
                                        <select name="subrubro" id="subrubro" onclick="extraeSaldo('<%=p.getIdperiodometa()%>', '<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>')" style="width: 180px">
                                            
                                        </select>
                                        </td>
                                    </tr>
                                </table>
                                
                            </td> 
                        </tr>
                        
                        <tr>
                        <td><h6><p >&nbsp;<i class="icon-edit"></i>Presupuesto</p></h6></td><td>
                            S/.<input type="text"  name="presupuesto" placeholder="Coloque un monto" id="presupuesto" value=""  > <samp id="saldoreal" ></samp>
             
                        </td> 
                        </tr>

                        <tr>
                            <td><h6><p >&nbsp;<i class="icon-edit"></i>Responsable</p></h6></td><td>
                                <input type="text"  name="responsable" id="responsable" value="" > </td> 
                        </tr>
                    </table>
               
                    
                </div>
                <div class="modal-footer">
                    <input type="hidden"  name="nro_indicador_3" value="<%=vr.getNro()%>" >
                    <input type="hidden"  name="idperiodometa" value="<%=p.getIdperiodometa()%>" >
                    <input type="hidden"  name="idmeta<%=vr.getNro()%>" value="<%=in.getIdmeta()%>" >
                    <input type="hidden"  name="idestrategiaindicador<%=vr.getNro()%>" value="<%=in.getIdestrategiaindicador()%>" >
                    <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" >
                    <input type="hidden"  name="opt" value="13" >
                <button class="btn" data-dismiss="modal" aria-hidden="true">Cerrar</button>
                <input type="submit" value="Aceptar" class="btn btn-primary" />
          
        </div>
    </div>                   
   </form>             
                    
                    
                    
                    
                    

    <form id="editar" class="editar" name="editar" action="<%=request.getContextPath()%>/IndicadorApoyo" method="POST">
          <div  id="myModalX" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
          <div class="modal-header">
            <button type="button" class="close" id="closeid" data-dismiss="modal" aria-hidden="true">×</button>
            <center><h4 id="myModalLabel"><p>Modificar Actividad</p></h4></center>
          </div>
                <div class="modal-body">

                    <table class="table table-hover">


                        <tr>
                        <td><h6><p >&nbsp;&nbsp;<i class="icon-edit"></i>&nbsp;&nbsp;&nbsp;Acción :</p></h6></td><td>
                            <textarea rows="2" type="text" name="accionxx" id="accionxx" placeholder="Acción"></textarea>
                        </td>
                        </tr>
                        <tr>
                            <td><h6><p >&nbsp;&nbsp;<i class="icon-edit"></i>&nbsp;Cantidad : </p></h6></td><td><input type="text" name="cantidadxx" id="cantidadxx"  ></td>
                        </tr><tr>
                        <td><h6><p >&nbsp;<i class="icon-edit"></i>&nbsp;Cronograma</p></h6></td>
                        <td>
                            <table>
                                <tr>
                                    <td>
                                <label class="checkbox inline">
                                <input type="checkbox" id="inlineCheckbox1xx" name="eneroxx" value="1">Enero
                                </label></td>
                                    <td>
                                  <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox2xx" name="febreroxx" value="1"> Febrero
                                </label>

                                    </td>
                                    <td>
                              <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox3xx" name="marzoxx" value="1"> Marzo
                                </label>
                                    </td>
                                    <td>
                               <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox4xx" name="abrilxx" value="1"> Abril
                                </label>
                                    </td>                                    
                                </tr>
                                <tr>

                                    <td>
                                  <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox5xx" name="mayoxx" value="1"> Mayo
                                </label>
                                    </td>
                                    <td>
                                <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox6xx" name="junioxx" value="1"> Junio
                                </label>
                                    </td>
                                    <td>
                               <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox7xx" name="julioxx" value="1"> Julio
                                </label>
                                    </td>
                                    <td>
                                 <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox8xx" name="agostoxx" value="1"> Agosto
                                </label>
                                    </td>                                    
                                </tr>
                                <tr>

                                    <td>
                                   <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox9xx" name="setiembrexx" value="1"> Setiembre
                                </label>
                                    </td>
                                    <td>
                                <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox10xx" name="octubrexx" value="1"> Octubre
                                </label>
                                    </td>
                                    <td>
                                 <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox11xx" name="noviembrexx" value="1"> Noviembre
                                </label>
                                    </td>
                                    <td>
                                   <label class="checkbox inline">
                                  <input type="checkbox" id="inlineCheckbox12xx" name="diciembrexx" value="1"> Diciembre
                                </label>
                                    </td>                                    
                                </tr>
                            </table>

                        </td>
                        </tr>
                        <tr>
                            <td><h6><p >&nbsp;<i class="icon-edit"></i>Rubro</p></h6></td>
                            <td>
                            <table>
                                    <tr>
                                        <td>
                                            <%
                                            lista=null;
                                            lista = (ArrayList)request.getSession().getAttribute("sesionNivelFinan");

               
                                            inter=lista.iterator();
                                            %>                                            
                                            <select name="rubroxx" id="rubroxx" style="width: 140px" onclick="subRubrosxx('<%=idTipoAreaPri%>', '<%=idFilial%>')">
                                            <%    
                                            while(inter.hasNext()){
                                                Map datos=  (Map)inter.next();                                             
                                            %>
                                            <option value="<%=datos.get("idNivelFinanciero")%>*<%=datos.get("nombre")%>"><%=datos.get("nombre")%></option>
                                            <% } %>
                                        </select>                                                                                  
                                        </td>
                                        <td> 
                                        <input type="hidden" name="rubronombrexx" id="rubronombrexx" value="">
                                        <select name="subrubroxx" id="subrubroxx" onclick="extraeSaldoxx('<%=p.getIdperiodometa()%>', '<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>')" style="width: 180px">
                                            
                                        </select>
                                        </td>
                                    </tr>
                                </table>                                
                            </td>
                        </tr>                        
                        <tr>
                        <td><h6><p >&nbsp;<i class="icon-edit"></i>Presupuesto</p></h6></td><td>
                        S/.<input type="text"  name="presupuestoxx" id="presupuestoxx" value="" ><samp id="saldorealxx" ></samp>
                        </td>
                        </tr>
                        <tr>
                            <td><h6><p >&nbsp;<i class="icon-edit"></i>Responsable</p></h6></td><td>
                                <input type="text"  name="responsablexx" id="responsablexx" value="" > </td> 
                        </tr>                        
                    </table>


                </div>
                <div class="modal-footer">
                    <input type="hidden"  name="nro_indicador_3xx" id="nro_indicador_3xx" >
                    <input type="hidden"  name="idactividadxx" id="idactividadxx" >
                    <input type="hidden"  name="idperiodometaxx" id="idperiodometaxx" >
                    <input type="hidden"  name="idmetaxx" id="idmetaxx" >
                    <input type="hidden"  name="idestrategiaindicadorxx"  id="idestrategiaindicadorxx" >
                    <input type="hidden"  name="ideapfacultadxx" id="ideapfacultadxx" >
                    <input type="hidden"  name="opt" id="opt" value="30" >



                <button class="btn" data-dismiss="modal" aria-hidden="true">Cerrar</button>
                <input type="submit" value="Aceptar" class="btn btn-primary" />
               

        </div>
       </div> 
       </form> 
                  
                    
          
          
          
          <center><h4>Lista de Actividades &nbsp;&nbsp;&nbsp;&nbsp;
                  <% if(estadoPoa==1){ %>
                  <a  href="#myModal" role="button"  class="btn" data-toggle="modal" ><i class="icon-plus"></i>
                  </a>
                  <% }else{ %>
                  <button class="btn"  rel="tooltip" title="Desactivado"  ><i class="icon-eye-close "></i></button> 
                  <% } %>
              </h4></center>
               
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <td colspan="2"><i class="icon-fullscreen"></i>&nbsp;&nbsp;<strong>Estratégia :</strong>&nbsp; <%=in.getEstrategia()%></td>                      
                   
                            </tr>
                            <tr >                       
                     <td rowspan="2"><center>
                    <p <%if(in.getSemaforo()==0){%>class="rojo" <%}if(in.getSemaforo()==1){%>class="amarillo"<%}if(in.getSemaforo()==2){%>class="verde"<%}if(in.getSemaforo()==3){%>class="azul"<%}%>></p>                      
                        </center></td>
                        <td><i class="icon-leaf"></i>&nbsp;&nbsp;<strong>Indicador &nbsp; # <%=in.getNro()%> :</strong> &nbsp;<%=in.getNombre()%></td>
                            </tr>       
                <tr>
                    <td><i class="icon-flag"></i>&nbsp;&nbsp;<strong>Periodo : </strong>&nbsp;<%=p.getPeriodo()%> 
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-star"></i>&nbsp;&nbsp;<strong>Meta : </strong>&nbsp; <%=in.getMeta()%> <%if(in.getIdtipometa()==2){%>%<%}%>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-star-empty"></i>&nbsp;&nbsp;<strong>Avance : </strong>&nbsp; <%=in.getTotalavance()%> <%if(in.getIdtipometa()==2){%>%<%}%>
                    </td>
                </tr>
     
                        </thead>
                        
                    </table>
 <table class="table table-bordered table-hover"    >
     <thead>
   <tr>
       <th rowspan="2" width="">#</th>
       <th rowspan="2" width="">Acciones</th>
       <th rowspan="2" width="">Responsable</th>
       <th rowspan="2" width="">Cant.</th>
       <th   colspan="12" width="25%">Cronograma</th>
       <th rowspan="2" width="">Presupuesto</th>
       <th rowspan="2" width="">Rubro</th>
       <th rowspan="2" width="">Opciones</th>  
   </tr>
   <tr>
       <th  width="">E</th>
       <th  width="">F</th>
       <th  width="">M</th>
       <th  width="">A</th>
       <th  width="">M</th>
       <th  width="">J</th>
       <th  width="">J</th>
       <th  width="">A</th>
       <th  width="">S</th>
       <th  width="">O</th>
       <th  width="">N</th>
       <th  width="">D</th>   
   </tr>
   </thead>
   <tbody>
       
       <% if(listaActividadIndicador!=null){for(Actividad me: listaActividadIndicador){%> 
      <tr>
       <td  width=""><%=me.getNro()%></td>
       <td  width=""><%=me.getAccion()%></td> 
       
       <td  width=""><%=me.getCantidad()%></td>
       
       <td  width=""><%if(me.getEnero()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getFebrero()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getMarzo()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getAbril()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getMayo()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getJunio()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getJulio()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getAgosto()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getSetiembre()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getOctubre()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getNoviembre()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getDiciembre()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>  
       <td  width="">s/ <%=me.getPresupuesto()%></td>
       <%suma=me.getPresupuesto()+suma;%>
       <td  width=""><%=me.getRubro()%></td>
        <td  width="">
        <center>   
  
        <button class="btn btn-mini btn-danger" type="submit"><i class="icon-remove"></i></button> 


        </center> 
           <br>
        </td>
   </tr>
   <%}}%>
       <% if(listaActividad!=null){for(Actividad me: listaActividad){%> 
      <tr>
       <td  width=""><%=me.getNro()%></td>
       <td  width=""><%=me.getAccion()%></td>
       <td  width=""><%=me.getResponsable() %></td>       
       <td  width=""><%=me.getCantidad()%></td>
       
       <td  width=""><%if(me.getEnero()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getFebrero()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getMarzo()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getAbril()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getMayo()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getJunio()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getJulio()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getAgosto()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getSetiembre()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getOctubre()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getNoviembre()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>
       <td  width=""><%if(me.getDiciembre()==1){%><i class="icon-ok"></i><%}else{%><%}%></td>  
       <td  width="">s/ <%=me.getPresupuesto()%></td>
       <%suma=me.getPresupuesto()+suma;%>
       <td  width=""><%=me.getRubro()%></td>
       
        <td  width="">

        <% if(estadoPoa==1){ %>
        
        <a  href="#myModalX" role="button"  onclick="editarActividad(
'<%=me.getAccion().toString().trim()%>'
,'<%=me.getCantidad()%>'
,'<%=me.getNro()%>'
,'<%=me.getPresupuesto()%>'
,'<%=me.getRubro()%>'
,'<%=me.getEnero()%>'
,'<%=me.getFebrero()%>'
,'<%=me.getMarzo()%>'
,'<%=me.getAbril()%>'
,'<%=me.getMayo()%>'
,'<%=me.getJunio()%>'
,'<%=me.getJulio()%>'
,'<%=me.getAgosto()%>'
,'<%=me.getSetiembre()%>'
,'<%=me.getOctubre()%>'
,'<%=me.getNoviembre()%>'
,'<%=me.getDiciembre()%>'
,'<%=me.getResponsable()%>'                   
,'<%=p.getIdperiodometa()%>'
,'<%=in.getIdmeta()%>'
,'<%=in.getIdestrategiaindicador()%>'
,'<%=eU.getIdeapfacultad()%>'
,'<%=me.getIdactividad()%>'
,'<%=me.getIdCuenta()%>'  
,'<%=idTipoAreaPri%>'
,'<%=idFilial%>'        
);" 
                 class="btn" data-toggle="modal" ><i class="icon-edit"></i>
        </a>
        <% } else{ %>
        <button class="btn"  rel="tooltip" title="Desactivado"  ><i class="icon-eye-close "></i></button> 
        <% } %>
        </td>   



       <td  width="">
                  <% if(estadoPoa==1){ %>          
                <form class="eliminar" name="eliminar" action="<%=request.getContextPath()%>/IndicadorApoyo" method="POST">  
                <input type="hidden"  name="nro_actividad" id="nro_indicador" />
                <input type="hidden"  name="nro_indicador_3" value="<%=vr.getNro()%>" />
                <input type="hidden"  name="idperiodometa" value="<%=p.getIdperiodometa()%>" />
                <input type="hidden"  name="idmeta<%=vr.getNro()%>" value="<%=in.getIdmeta()%>" />
                <input type="hidden"  name="idestrategiaindicador<%=vr.getNro()%>" value="<%=in.getIdestrategiaindicador()%>" />
                <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" />
                <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" />
                <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" />        
                <input type="hidden"  name="idactividad<%=me.getIdactividad()%>" value="<%=me.getIdactividad()%>" /> 
                <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>"/> 
                <input type="hidden"  name="opt" value="28" > 
                <center>   
                <button class="btn btn-mini btn-danger" onmouseover="formindicador(<%=me.getIdactividad()%>)" type="submit"><i class="icon-remove icon-white"></i></button>                       
                </center>
                </form>      
                <% }else{ %>
                <button class="btn"  rel="tooltip" title="Desactivado"  ><i class="icon-eye-close "></i></button> 
                <% } %>            
       </td>
   </tr>
   <%}}%>
         <tr> 
             
             <td  colspan="15"><p class="text-right"></p></td>
       <td  colspan="3"><strong>Total: s/ <%=suma%></strong></td>
   </tr>
   </tbody>

</table>
                    
                    
                    
			<div class="pagination pagination-right">
				<ul>
					<li>
						<a href="#">Prev</a>
					</li>
					<li>
						<a href="#">1</a>
					</li>
					<li>
						<a href="#">2</a>
					</li>
					<li>
						<a href="#">3</a>
					</li>
					<li>
						<a href="#">4</a>
					</li>
					<li>
						<a href="#">5</a>
					</li>
					<li>
						<a href="#">Next</a>
					</li>
				</ul>
			</div>
 
    
   <form id="atras" name="atras" action="<%=request.getContextPath()%>/IndicadorApoyo" method="POST"> 
       
            <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" >
            <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" >        
            <input type="hidden"  name="idperiodometa" value="<%=p.getIdperiodometa()%>" >     
            <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" >
            <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>">   
            <%if(eje!=null){%>
            <input type="hidden"  name="opt" value="24" > 
            <%}else{%>
            <input type="hidden"  name="opt" value="27" > 
            <%}%>
            <input type="hidden"  name="valor" value="<%if(name!=null){%><%=name.getNombre()%><%}%>" >
             
            
            <p align="right">
            <button class="btn btn-mini btn-primary" type="submit">Atras</button>
            </p>  
   </form>
  

		</div>
	</div>
</div>
    </body>
</html>

