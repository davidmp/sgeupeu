<%-- 
    Document   : formObervacion
    Created on : 15/07/2014, 06:05:31 PM
    Author     : Intel
--%>

<%@page import="sge.service.ReporteService"%>
<%@page import="sge.modelo.Subcuenta"%>
<%@page import="sge.service.IndicadorService"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.TipoSeguimiento"%>
<%@page import="sge.service.SeguimientoService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">            
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../../resources/css/BeatPicker.min.css"/>
<!--        <script src="../../resources/js/jquery-1.11.0.min.js"></script>-->
        <script src="../../resources/js/BeatPicker.min.js"></script>        
        <script language="javascript" type="text/javascript">

            //*** Este Codigo permite Validar que sea un campo Numerico
            function Solo_Numerico(variable){
                 var RE = /^\d*\.?\d*$/;               
                if (RE.test(variable)){
                    return variable;
                }else{return 0;}
            }
            function ValNumero(Control){
                Control.value=Solo_Numerico(Control.value);
            }

        </script>


        <%
        IndicadorService ss;
        ss=new IndicadorService();
        List<Subcuenta> lista=null;
        lista=ss.listarSubCuenta();

        int idactividad=Integer.parseInt(request.getParameter("idactividad")==null ? "0":request.getParameter("idactividad"));
        double presupuesto=Double.parseDouble(request.getParameter("presupuesto")==null ? "0":request.getParameter("presupuesto"));
        String accion=String.valueOf(request.getParameter("accion")==null ? " ":request.getParameter("accion").toString());
        String fechas=String.valueOf(request.getParameter("fechas")==null ? " ":request.getParameter("fechas").toString());
        ReporteService rs;
        rs=new ReporteService();
        double saldo=rs.saldoTareas(idactividad);
        %>
    </head>
    <body>
    <center>
        <div>
            <form id="idseguimientoForm" name="idseguimientoForm" action="<%=request.getContextPath()%>/IndicadorTareaEje"  >
                <table>
                    <tr>
                        <td colspan="4"><center><b>Actividad: </b><% out.print(accion); %></center> </td>
                    </tr>
                    <tr>
                        <td colspan="4"><center><b>Presupesto: </b> <% out.print(presupuesto); %> &nbsp;&nbsp;&nbsp;  <b>Tiene: </b> <% out.print(presupuesto-saldo); %> </center>  </td>
                    </tr>
                    <tr>
                        <td colspan="4"><center><b>Fechas: </b> <% out.print(fechas); %> </center> </td>
                    </tr>
                    <tr>
                        <td colspan="4"><hr/></td>
                    </tr>
                    
                    <tr>
                        <td>
                            Tarea:   
                        </td>
                        <td colspan="3">
                            <textarea id="tarea" name="tarea" style="width: 380px; height: 40xpx"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Fecha:   
                        </td>
                        <td>

                            <p>
                           
                           <input type="text" id="fecha" name="fecha" placeholder="YYYY-MM-DD" data-beatpicker="true"   data-beatpicker-position="['*','*']"  >

                            </p>
                    
                        </td>
                        <td>Entre</td>
                        <td><input type="text"  data-beatpicker="true" placeholder="YYYY-MM-DD"  id="fecha2" name="fecha2" data-beatpicker-position="['*','*']"  ></td>
                    </tr>
                    <tr>
                        <td>
                           Recursos Humanos:   
                        </td>
                        <td>
                            S/.<input type="text" id="rh" onkeyUp="return ValNumero(this);" name="rh" value="0" size="15px" >
                        </td>
                        <td>
                            Materiales:   
                        </td>
                        <td>
                            S/.<input type="text" id="materiales" onkeyUp="return ValNumero(this);" name="materiales" value="0" size="15px" >
                        </td>
                    </tr>                    
                    <tr>
                        <td>
                            Equipos:   
                        </td>
                        <td>
                            S/.<input type="text" id="equipos" onkeyUp="return ValNumero(this);" name="equipos" value="0" size="15px" >
                        </td>
                        <td>
                            Atenci&oacute;n Personal:   
                        </td>
                        <td>
                            S/.<input type="text" id="ap" onkeyUp="return ValNumero(this);" name="ap" value="0" size="15px" >
                        </td>
                    </tr>                    
                    <tr>
                        <td>
                            Movilidad:   
                        </td>
                        <td>
                            S/.<input type="text" id="movilidad"  onkeyUp="return ValNumero(this);" name="movilidad" value="0" size="15px" >
                        </td>
                        <td>
                            Otros:   
                        </td>
                        <td>
                            S/.<input type="text" id="otros" onkeyUp="return ValNumero(this);" name="otros" value="0" size="15px" >
                        </td>
                    </tr>                    
                    <tr>
                        <td colspan="4" >
                            <center>
                                <input type="hidden" id="monto" name="monto" value="0" size="15px" >
                                <input type="hidden" id="idactividad" name="idactividad" value="<%=idactividad%>"/>                                
                                <input type="hidden" id="saldo" name="saldo" value="<%=(presupuesto-saldo) %>"/>                                
                                <input type="button" value="Crear" onclick="registrarObs()" />
                                <input type="button" value="Cancelar" onclick="$.pgwModal('close')" />                                                                                          
                            </center>                                                    
                        </td>
                       
                    </tr>
                </table>
            </form>
        </div>
    </center>
    </body>
    <script type="text/javascript" charset="utf-8">

        function registrarObs(){
          
            var fechaX = new Date(Date.parse($("#fecha").val()));
            var fechaY = new Date(Date.parse($("#fecha2").val()));

            var condicion=true;
            if(fechaX!="Invalid Date" && fechaY!="Invalid Date"){ 
                if(fechaX<=fechaY){condicion=true;}else{condicion=false;}
            }else if(fechaX!="Invalid Date" && fechaY=="Invalid Date"){
                $("#fecha2").val($("#fecha").val());
            }else if(fechaX=="Invalid Date" && fechaY!="Invalid Date"){$("#fecha").val($("#fecha2").val()); }
            //alert(condicion);
            
            var RE = /^\d*\.?\d*$/; 
            monto=0;
            monto=(0)+(Number($.trim($("#rh").val()))) +(Number($.trim($("#materiales").val()))) + Number($.trim($("#equipos").val())) + Number($.trim($("#ap").val())) + Number($.trim($("#movilidad").val())) + Number($.trim($("#otros").val()));
            var variable="&rh="+$.trim($("#rh").val())+"&materiales="+$.trim($("#materiales").val())+"&equipos="+$.trim($("#equipos").val())+"&ap="+$.trim($("#ap").val())+"&movilidad="+$.trim($("#movilidad").val())+"&otros="+$.trim($("#otros").val());
            //alert(monto);
            if($.trim($("#idactividad").val())!="0"){
            if($.trim($("#tarea").val())!=""){              
            if(RE.test(monto)){            
            var  saldo=$.trim($("#saldo").val());                
            if((saldo-monto)>=0){
            if(condicion==true){   
            $.ajax({
            type:"GET",
            url:"<%=request.getContextPath()%>/IndicadorTareaEje?opt=4&idactividad="+ $("#idactividad").val()+"&tarea="+$("#tarea").val()+"&monto="+monto+"&fecha="+$("#fecha").val()+"&fecha2="+$("#fecha2").val()+variable,
            data:"",
            success: function(datos){
               if($.trim(datos)=="1"){
                   $.pgwModal('close');
               } else{
                   alert("Error al Insertar!!");
               }                 
            }               
         });
         }else{  alert("Las fechas deben estar colocadas de forma correcta"); }
         }else{  alert("No puede exceder del presupuesto de su actividad!"); }
         }else{  alert("Debe Colocar monto aunque sea 0!"); }
         }else{  alert("Debe Colocar la Tarea!"); }
         }else{
         alert("No se puede registrar tarea ... porque no tiene ningun actividad!");
         }
           
        }
        

    </script>
</html>