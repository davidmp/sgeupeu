<%-- 
    Document   : get_list_combo_estrategias
    Created on : 15/10/2013, 01:38:08 PM
    Author     : Edwin
--%>
<%@page import="sge.modelo.EjeEstrategia"%>
<%@page import="java.util.List"%>
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
        function indicador(id, idEjeX){
        $("#indicadoresestrategias").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/GestionEstrategicoApoyo?opt=49&idEjeX='+idEjeX,
            type: 'POST',
            async: true,
            data: 'idEjeEstrategia='+id, 
            success: function(data) {
            $('#indicadoresestrategias').fadeIn(1000).html(data);
            //alert("parametro de ingreso ---> "+id);
            }
           
        });}
        
         $('.toltipx').tooltip();
</script>
    </head>
    <body>
        <%
        
        int idEjeX=Integer.parseInt(request.getParameter("idEjeX")==null?"0":request.getParameter("idEjeX"));
        
        
        %>
        
        <div class="row-fluid">
		 <div class="span12">
        
            <select name="" onchange="indicador(this.value, <%=idEjeX%>);" class="pull-left toltipx" title="Lista de Ejes y Objetivos Estrategicos">
                    <option value=""></option>
                        <%
                        int x=0;     
                        List<EjeEstrategia> lista=(List<EjeEstrategia>) request.getSession().getAttribute("Listar_Estrategias_eje");
                        if (lista != null) {
                            for (EjeEstrategia es : lista) {

                    %>
                    <option title="<%=es.getIdEjeEstrategia()%>" value="<%=es.getIdEjeEstrategia()%>"><%=es.getIdestrategia_nombre()%></option>
                    <%}}%>
                </select>
                 </div>
        </div>
      
        
    </body>
</html>