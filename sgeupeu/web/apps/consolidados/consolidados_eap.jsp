<%-- 
    Document   : consolidados_eap
    Created on : 07/09/2013, 12:36:17 PM
    Author     : oscdmdz
--%>

<%@page import="sge.modelo.Periodometa"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Facultad"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%

        Usuario w = null;
        w = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
        Eap eU=(Eap)request.getSession().getAttribute("eapUsuario");
        Facultad faU=(Facultad)request.getSession().getAttribute("facultadUsuario");
        Filial fiU=(Filial)request.getSession().getAttribute("filialUsuario");

        List<Eap> ea=null;
        ea=(List<Eap>)request.getSession().getAttribute("listaEapConsolidado");
        List<Periodometa> periodoM=null;
        periodoM=(List<Periodometa>)request.getSession().getAttribute("listar_periodo_meta");    
        Periodometa idperidoM=(Periodometa)request.getSession().getAttribute("idperiodometa");  
int i=1;                      
            
%>
<script type="text/javascript">
    
            var form = $('#periodo_1');
        form.submit(function () {
        $.ajax({
        type: form.attr('method'),
        url: form.attr('action'),
        data: form.serialize(),
        success: function (data) {
        $('#eap').show();
        $('#eap').html(data);
        document.getElementById("periodo_1").reset();
        document.getElementById("eap").reset(); 
        }
        }); 
        return false;
        });
</script>
    
    <body>
                  <ul class="thumbnails">
              <li class="span3">                  
       <form id="periodo_1" name="periodo_1"  action="<%=request.getContextPath()%>/Indicador" >
           
          <font color="#267DFF" size="4">Periodo: <strong> <%if(idperidoM!=null){%><%=idperidoM.getPeriodo()%><%}else{%>-<%}%></strong></font>
          <p>  
          <select name="idperiodometa" class="span2" >
             <option value="0">-- --</option>  
            <%
            if(periodoM!=null){for(Periodometa pm: periodoM){%>
            <option value="<%=pm.getIdperiodometa()%>"><%=pm.getPeriodo()%></option>
            <%}}%>
           </select>&nbsp;&nbsp;<input type="submit" value="Aceptar" class="btn btn-mini btn-success"/>           
                           <input type="hidden"  name="idusuario" value="<%if(w!=null){%><%=w.getId()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idperiodometa" value="<%if(idperidoM!=null){%><%=idperidoM.getIdperiodometa()%><%}else{%>0<%}%>" >    
                        <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" >
                        <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" >
                        <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" > 
                   
                   <input type="hidden"  name="opt" value="15" >
       </form>               
             </li>

            </ul>       
           <table class="table table-bordered">
               <thead>
                   <tr>
                       <th>#</th>
                       <th>Progreso</th>
                   </tr>
               </thead>
               <tbody>
                 <%
            if(ea!=null){for(Eap e: ea){%>  
                   <tr>
                       <td><br><%=i++%></td>
                       <td>
                     <center>
                        <%=(e.getMeta())+"%"%>
                     </center>                
                    <%=e.getNombre()%><div class="progress progress-striped active">
                        <div class="bar" 
                        style="width: <%=(e.getMeta())+"%"%>;"></div>
                    </div></td>
                   </tr>
                   <%}}%>
               </tbody>
           </table>
               
               
               
               

    </body>
    
</html>
