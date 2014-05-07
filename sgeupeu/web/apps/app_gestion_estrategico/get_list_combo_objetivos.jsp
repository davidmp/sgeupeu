<%-- 
    Document   : get_list_combo_objetivos
    Created on : 15/10/2013, 01:25:58 PM
    Author     : Edwin
--%>


<%@page import="sge.modelo.TemporadaEjeObjEstrategico"%>
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
        function estrategia(id){
        $("#comboestrategias").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/GestionEstrategico?opt=48',
            type: 'POST',
            async: true,
            data: 'idtemporadaejeestrategicoccc='+id, 
            success: function(data) {
            $('#comboestrategias').fadeIn(1000).html(data);
            //alert("parametro de ingreso ---> "+id);
            }
           
        });}
        
         $('.toltipx').tooltip();
</script>
    </head>
    <body>
        <div class="row-fluid">
		 <div class="span12">
           <select name="" onchange="estrategia(this.value);" class="pull-left toltipx" title="Lista de Ejes y Objetivos Estrategicos">
                    <option value=""></option>
                        <%
                        int x=0;     
                        List<TemporadaEjeObjEstrategico> lista=(List<TemporadaEjeObjEstrategico>) request.getSession().getAttribute("Listar_Ejes_Objetivos_Estrategicos_Temporada");
                        if (lista != null) {
                            for (TemporadaEjeObjEstrategico es : lista) {

                    %>
                    <option title="<%=es.getIdEjeEstrategico_Objetivo()%>" value="<%=es.getIdTemporadaEjeEstrategico()%>"><%=++x%>&nbsp;._<%=es.getIdEjeEstrategico_nombre()%></option>
            <%}}%>
                </select>
                 </div>
        </div>
      
        
    </body>
</html>