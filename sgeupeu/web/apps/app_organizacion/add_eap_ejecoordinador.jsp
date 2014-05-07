<%-- 
    Document   : add_eap_facultad_filial
    Created on : 04/10/2013, 08:48:04 AM
    Author     : Edwin
--%>

<%@page import="sge.modelo.CoordinadorEje"%>
<%@page import="sge.modelo.Coordinadoreap"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Filialfacultad"%>
<%@page import="sge.modelo.Usuario"%>
<%@page import="sge.modelo.Coordinadorfacultad"%>
<%@page import="sge.modelo.Facultad"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<script type="text/javascript">
var form = $('#addPersona');
form.submit(function () {
 
$.ajax({
type: form.attr('method'),
url: form.attr('action'),
data: form.serialize(),
success: function (data) {
$('.tab-content').show();
$('.tab-content').html(data);
document.getElementById("addPersona").reset();
document.getElementById("tab-content").reset(); 

}
});
 
return false;
});

$(document).ready(function() {
    $("#topmenu a").click(function() {
    $(".tab-content").empty().append();
    $("#topmenu a").removeClass('current');
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
        <div class="row-fluid">
	<div class="span12">
	<form id="addPersona" name="addPersona" action="<%=request.getContextPath()%>/Usuario" method="POST">
            <fieldset>
            <legend>Registro de Coordinadores de Ejes de las Escuelas Academicas Profesionales</legend> 
	    <div class="row-fluid">
				<div class="span3">
                                    <label> Coordinadores de Ejes de Filial :</label>
                                    <select name="idCoordinadorEje" required="">
                                        <option></option>
                                            <%
                                             List<CoordinadorEje> listaf=(List<CoordinadorEje>) request.getSession().getAttribute("coordinadoresEjeFilialSess");
                                             if (listaf != null) {
                                             for (CoordinadorEje cf : listaf) {
                                            %> 
                                            <option value="<%=cf.getIdcoordinadoreje() %>"><%=cf.getNombrepersona() %></option>
                                        <%}}%>
                                    </select>
                                    
                        <div id="topmenu">
                            <%
                            int idEapFilial=Integer.parseInt(request.getParameter("idEapFacultad")==null ? "0" : request.getParameter("idEapFacultad"));                            
                            %>
                            <input type="hidden" name="idEapFacultad" value="<%=idEapFilial%>"/>  
                        <input type="hidden" name="opt" value="86"/>  
                        <input type="submit" value="Guardar" class="btn btn-success" />
                        <input type="reset" value="Limpiar" class="btn btn-warning"/>
                        <a href="<%=request.getContextPath()%>/Usuario?opt=79" class="btn">Atras</a>
                        </div>                                    
                               </div>
                            </div>
                        <hr>
                        
            </fieldset>
        </form>
        </div>                                                         
	</div>
        
                        
        <div class="row-fluid">
        <div class="span12">
            <table data-filter="#filteredwin" class="table table-bordered table-condensed footable table-hover" data-page-size="5"  >


              <thead>
                <tr>
                    <th data-class="expand" data-sort-initial="true" style="width: 4%;">#</th> 
                    <th data-hide="phone,tablet" style="width: 20%;" >Nombres/Apellidos</th>                 
                    <th data-hide="phone,tablet" style="width: 10%;" >DNI</th>
                    <th data-hide="phone,tablet" style="width: 20%;" >E-Mail</th>
                    
                </tr>
              </thead>
              <tbody>
                        <%
                                int x=0;     
                                List<CoordinadorEje> lista=(List<CoordinadorEje>) request.getSession().getAttribute("listaCoordinadorEjeEapSessX");
                                if (lista != null) {
                                    for (CoordinadorEje us : lista) {

                            %>
                        <tr class="<% if(x%2==1){out.println("success");}%>">
                            <td><%=++x%></td>
                            <td><%=us.getNombrepersona()%></td>
                            <td><%=us.getDni() %></td>                            
                            <td><%=us.getEmail() %></td>
                           
                        </tr>
                     <% }} %>
                    </tbody>
              <tfoot class="footable-pagination pagination pagination-centered pagination-small">
                <tr>
                    <td colspan="6" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
                </tr>
              </tfoot>
            </table>                           
       </div>                        
       </div>
    </body>
</html>
