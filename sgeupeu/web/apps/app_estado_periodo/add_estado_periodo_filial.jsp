<%-- 
    Document   : add_estado_periodo_filial
    Created on : 13/09/2013, 07:13:13 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Filial"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Periodometa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <script type="text/javascript">
 
var form = $('#add');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('#mensaje').show();
$('#mensaje').html(data);
document.getElementById("add").reset();
document.getElementById("mensaje").reset(); 

}
});
 
return false;
});
$(".alert").alert();

$(document).ready(function() {
    $(".controls a").click(function() {
    $(".tab-content").empty().append();
    $(".controls a").removeClass('current');
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
  
	
            
            <form method="POST" action="<%=request.getContextPath()%>/GestionEstrategico" id="add" name="add">
                
			<div class="row-fluid">
				<div class="span12">
                                    <strong>Periodo:</strong><br/>
                                    <select name="idperiodometa" required="">
                                        <option></option>
                                         <%
                        int x=0;     
                        List<Periodometa> lista=(List<Periodometa>) request.getSession().getAttribute("ListarPeriodoMeta");
                        if (lista != null) {
                            for (Periodometa es : lista) {

                    %>
                    <option value="<%=es.getIdperiodometa()%>"><%=es.getPeriodo()%></option>
                                        <%}}%>
                                    </select><br/>
                                    <table  class="table table-bordered table-hover table-condensed">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th style="width: 3%;"><div class="icon-ok"></div></th>
                                                <th>Filial</th>
                                                <th>Estado Meta</th>
                                                <th>Estado Avance</th>
                                            
                                            </tr>
                                        </thead>
                                        <tbody>
                                             <%
                        int q=0;
                        List<Filial> lista2=(List<Filial>) request.getSession().getAttribute("ListarFilial");
                        if (lista2 != null) {
                            for (Filial f : lista2) {

                    %>
                                            <tr>
                                                <td><%=++q%></td>
                                                <td><span class="badge"><input type="checkbox" name="idFilial" value="<%=f.getIdfilial()%>" /></span></td>
                                                <td><%=f.getDireccion()%></td>
                                                <td><span class="badge badge-info"><input type="checkbox" name="estadometa" value="1" /></span></td>
                                                <td><span class="badge badge-success"><input type="checkbox" name="estadoavance" value="1" /></span></td>
                                            </tr>
                                            <%}}%>
                                        </tbody>
                                    </table>

                                    
                                    
                                    
                               
                    
                    <hr class="divider">
                    <div class="control-group">
                    <div class="controls">
                    <input type="hidden" name="opt" value="31"/>
                    <button type="submit" class="btn btn-small btn-success"><div class="icon-ok icon-white"></div>&nbsp;Guardar</button>
                    &nbsp;
                    <button type="reset" class="btn btn-small btn-warning"><div class="icon-refresh icon-white"></div>&nbsp;Limpiar</button>
                    &nbsp;
                    
                    <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=29" class="btn btn-small btn-danger"><div class="icon-remove icon-white"></div>&nbsp;Cancelar</a>
                    
                    </div>
                    </div>
                    
                     </div>
                          
			</div>
                    
             </form>
            
            

        
       
                                    <div id="mensaje">
                                        
                                    </div>
    </body>
</html>
