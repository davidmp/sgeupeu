<%-- 
    Document   : list_pre_coordinador_eap_filial_periodo
    Created on : 01/10/2013, 12:15:07 AM
    Author     : Edwin
--%>


<%@page import="sge.modelo.Periodo"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Temporada"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
    $(function() {
      $('table').footable();
    });
    
    $(document).ready(function() {
    $("tbody a").click(function() {
    $(".tab-content").empty().append();
    $("tbody a").removeClass('current');
    $(this).addClass('current');
    $.ajax({ url: this.href, success: function(html) {
    $(".tab-content").empty().append(html);
    }
    });
    return false;
    });
});

// add 


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
     
            <script>
        function showedwinajax(id){
        $("#reportar").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/Usuario?opt=60',
            type: 'POST',
            async: true,
            data: 'idperi='+id, 
            success: function(data) {
            $('#reportar').fadeIn(1000).html(data);
            //alert("parametro de ingreso ---> "+id);
            }
           
        });}
        
         $('.toltipx').tooltip();
</script>
    </head>
    <body>
         <%
        String orgpre = (String)session.getAttribute("name_coord_pre");
        String datapre = (String)session.getAttribute("data_coord_pre");
        
        %>
       
        <div class="row-fluid">
            <div class="span12">
                <select name="" onchange="showedwinajax(this.value);" class="pull-left toltipx" title="Selecciona un Periodo para Listar y Registrar los Coordinadores de E.A.P" style="width: 10%;">
                    <option value=""></option>
                    <% List<Periodo> lista=(List<Periodo>) request.getSession().getAttribute("listar_periodo");
                        if (lista != null) {
                            for (Periodo es : lista) { %>
                  <option value="<%=es.getIdperiodo()%>"><%=es.getPeriodo()%></option>
            <%}}%>
                </select>
        
                    <blockquote class="pull-right">
            <p class="text-info">
            <%=orgpre%>
            </p> 
            <small><%=datapre%></small>
        </blockquote>
        
       	</div>
        
	</div>
        
       <div class="row-fluid">
		 <div class="span12" id="reportar">
                    
		</div>
	</div>
        
    </body>
</html>