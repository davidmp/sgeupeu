<%@page import="sge.modelo.Filialfacultad"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Temporada"%>
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

$('.toltipx').tooltip();
     </script>
     
            <script>
        function getPeriodo(id){
        $("#comboPeridos").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/GestionEstrategico?opt=29',
            type: 'POST',
            async: true,
            data: 'getIDtemporada='+id, 
            success: function(data) {
            $('#comboPeridos').fadeIn(1000).html(data);
            //alert("parametro de ingreso ---> "+id);
            }
           
        });}
        
        function getEstadoPeriodo(id){
        $("#comboFiliales").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/GestionEstrategico?opt=30',
            type: 'POST',
            async: true,
            data: 'getIDPeriodo='+id, 
            success: function(data) {
            $('#comboFiliales').fadeIn(1000).html(data);
            
            //alert("parametro de ingreso ---> "+id);
            }
           
        });}
        function getOptFiFaEa(id){
        $("#ReportarEstadoFilial").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/GestionEstrategico?opt=31',
            type: 'POST',
            async: true,
            data: 'getIDFilial='+id, 
            success: function(data) {
            $('#ReportarEstadoFilial').fadeIn(1000).html(data);
            
            //alert("parametro de ingreso ---> "+id);
            }
           
        });}
</script>

         <%
        String orgpre = (String)session.getAttribute("name_pre_periodo");
        String datapre = (String)session.getAttribute("data_pre_periodo");
        %>
       
        <div class="row-fluid">
            <div class="span3">
                <label><div class="icon-arrow-right"></div>&nbsp;Temporadas</label>
                <select name="" onchange="getPeriodo(this.value);" class="pull-left">
                    
                    <option value=""></option>
           <% List<Temporada> lista=(List<Temporada>) request.getSession().getAttribute("listarTemporada");
                        if (lista != null) {
                            for (Temporada es : lista) { %>
                  <option value="<%=es.getIdtemporada()%>"><%=es.getInicio()%>&nbsp;-&nbsp;<%=es.getFin()%></option>
            <%}}%>
                </select>
        
                   
        
       	</div>
                <div class="span3">
                    <label><div class="icon-arrow-right"></div>&nbsp;Periodos :</label>
                    <select name="" id="comboPeridos" onchange="getEstadoPeriodo(this.value);">
                        
                    </select>
                </div>
                <div class="span3">
                    <label><div class="icon-arrow-right"></div>&nbsp;Filial :</label>
                    <select name="" id="comboFiliales" onchange="getOptFiFaEa(this.value);">
                        
                    </select>
                </div>
                <div class="span3">
                    <blockquote class="pull-right">
            <p class="text-info">
            <%=orgpre%>
            </p> 
            <small><%=datapre%></small>
        </blockquote>
                </div>
        
	</div>
       <div class="row-fluid">
		 <div class="span12" id="ReportarEstadoFilial">
		</div>
	</div>

 