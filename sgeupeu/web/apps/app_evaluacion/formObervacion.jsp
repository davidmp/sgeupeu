<%-- 
    Document   : formObervacion
    Created on : 15/07/2014, 06:05:31 PM
    Author     : Intel
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.TipoSeguimiento"%>
<%@page import="sge.service.SeguimientoService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
        SeguimientoService ss;
        ss=new SeguimientoService();
        List<TipoSeguimiento> lista=null;
        lista=ss.listaPeriodoMeta();
        
        int idavance=Integer.parseInt(request.getParameter("idavance")==null ? "0":request.getParameter("idavance"));
        int idciclo=Integer.parseInt(request.getParameter("idciclo")==null ? "0":request.getParameter("idciclo"));
        String indicador=String.valueOf(request.getParameter("indicador")==null ? " ":request.getParameter("indicador").toString());
        
        %>
    </head>
    <body>
    <center>
        <div>
            <form id="idseguimientoForm" name="idseguimientoForm" action="<%=request.getContextPath()%>/Seguimiento"  >
                <table>
                    <tr>
                        <td colspan="2"><center><b><% out.print(indicador); %></b></center> </td>
                    </tr>
                    <tr>
                        <td colspan="2"><hr/></td>
                    </tr>
                    <tr>
                        <td>
                            Tipo Observaci&oacute;n:   
                        </td>
                        <td>
                            <select id="tiposeguimiento" name="tiposeguimiento">
                                <% if(lista!=null){ 
                                for(TipoSeguimiento ts: lista){    
                                %>
                                <option value="<%=ts.getIdTipoSeguim()%>" > <%=ts.getNombre() %> </option>
                                <% } 
                                 }
                                %>                                
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Observaci&oacute;n:   
                        </td>
                        <td>
                            <textarea id="observacion" name="observacion" style="width: 300px; height: 100px"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" >
                            <center>
                                <input type="hidden" id="idavance" name="idavance" value="<%=idavance%>"/>
                                <input type="hidden" id="idciclo" name="idciclo" value="<%=idciclo%>"/>
                                <input type="button" value="Crear" onclick="registrarObs()" />
                                <input type="button" value="Cancelar" onclick="$.pgwModal('close')" />
                                <input type="button" value="Ver Observaciones"/>                                                          
                            </center>                                                    
                        </td>
                       
                    </tr>
                </table>
            </form>
        </div>
    </center>
    </body>
    <script type="text/javascript">
        function registrarObs(){
            if($.trim($("#idavance").val())!="0"){
            if($.trim($("#observacion").val())!=""){
            $.ajax({
            type:"GET",
            url:"<%=request.getContextPath()%>/Seguimiento?opt=1&idavance="+$("#idavance").val()+"&idciclo="+$("#idciclo").val()+"&tiposeguimiento="+$("#tiposeguimiento").val()+"&observacion="+$("#observacion").val(),
            data:"",
            success: function(datos){
               if($.trim(datos)=="1"){
                   $.pgwModal('close');
               } else{
                   alert("Error al Insertar!!");
               }                 
            }               
         });
         }else{  alert("Debe Colocar la Observacion!"); }
         }else{
         alert("No se puede hacer observaciones ... porque no tiene ningun avance!");
         }
           
        }
        

    </script>
</html>
