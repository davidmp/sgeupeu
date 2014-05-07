<%-- 
    Document   : list_pre_estrategias
    Created on : 14/10/2013, 01:03:13 PM
    Author     : Edwin
--%>


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
        function ejesobj(id){
        $("#reportarestrategia").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=42',
            type: 'POST',
            async: true,
            data: 'edwintemporadaID='+id, 
            success: function(data) {
            $('#reportarestrategia').fadeIn(1000).html(data);
           //alert("parametro de ingreso ---> "+id);
            }
           
        });}
        
         $('.toltipx').tooltip();
</script>
    </head>
    <body>
         <%
        String orgpre = (String)session.getAttribute("name_pre_eje_objetivo_est");
        String datapre = (String)session.getAttribute("data_pre_eje_objetivo_est");
        %>
       
        <div class="row-fluid">
        <div class="span3">
            
            <label><strong>Temporada :</strong></label>
            <select name="" onchange="ejesobj(this.value);" class="pull-left toltipx" title="Selecciona una Temporada para Listar y Registrar los Ejes y Objetivos Estrategicos por Temporada">
                    <option value=""></option>
           <% List<Temporada> lista=(List<Temporada>) request.getSession().getAttribute("listarTemporada");
                        if (lista != null) {
                            for (Temporada es : lista) { %>
                  <option value="<%=es.getIdtemporada()%>"><%=es.getInicio()%>&nbsp;-&nbsp;<%=es.getFin()%></option>
            <%}}%>
            </select>
        </div>
                <div class="span3" id="reportarestrategia">
                    <label><strong>Eje Estrategico :</strong></label>
                </div>
        <div class="span6">
            <blockquote class="pull-right">
            <p class="text-info">
            <%=orgpre%>
            </p> 
            <small><%=datapre%></small>
        </blockquote>
        </div>
        
	</div>
        
      <div class="row-fluid">
		 <div class="span12" id="estrategias">
               </div>
	</div>
    </body>
</html>


