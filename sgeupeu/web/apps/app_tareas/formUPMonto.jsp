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
                        <td colspan="2">
                            Presupesto:   
                        </td>
                        <td colspan="2">
                            S/.<input type="text" id="monto"  onkeyUp="return ValNumero(this);" name="monto" value="<%=presupuesto%>" size="15px" >
                        </td>
                        
                    </tr>                    
                    <tr>
                        <td colspan="4" >
                            <center>                                
                                <input type="hidden" id="idactividad" name="idactividad" value="<%=idactividad%>"/>                                
                                <input type="hidden" id="saldo" name="saldo" value="<%=saldo %>"/>                                
                                <input type="button" value="Modificar" onclick="registrarObs()" />
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
           var  saldo=$.trim($("#saldo").val());  
           var  monto=$.trim($("#monto").val());  
           if(monto>=saldo){
            $.ajax({
            type:"GET",
            url:"<%=request.getContextPath()%>/IndicadorTareaEje?opt=6&idactividad="+ $("#idactividad").val()+"&monto="+monto,
            data:"",
            success: function(datos){
               if($.trim(datos)=="1"){
                   $.pgwModal('close');
               } else{
                   alert("Error al Modificar!!");
               }                 
            }               
            });
           }else{ alert("El monto no puede ser menor, de lo que ya esta en las tareas!");}
        }
        

    </script>
</html>