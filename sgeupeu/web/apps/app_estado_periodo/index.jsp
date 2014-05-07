        <%
        String org = (String)session.getAttribute("name_temporadas_periodos");
        String data = (String)session.getAttribute("data_temporadas_periodos");
        %>
        
        <blockquote>
            <p class="text-info">
            <%=org%>
            </p> 
            <small><%=data%></small>
        </blockquote>
           
            <ul class="nav nav-tabs">
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=18" title="Datos de la Organizacion" class="informacion"><div class="icon-tags"></div>&nbsp;<strong>Temporada</strong></a></li>
                <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=21" title="Seleccione una Temporada para Ingresar sus Periodos o Fechas" class="informacion"><div class="icon-tags"></div>&nbsp;<strong>Temporada - Periodo</strong></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><div class=" icon-chevron-down"></div>&nbsp;<strong>Aperturar a </strong><b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=28" title="Apertura Filial - S.G.E" class="informacion"><div class=" icon-chevron-right"></div>&nbsp;Filiales</a></li>
                  <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=76" data-toggle="tab"><div class=" icon-chevron-right"></div>&nbsp;Facultades</a></li>
                  <li><a data-target=".tab-content" data-toggle="tab" href="<%=request.getContextPath()%>/GestionEstrategico?opt=92" data-toggle="tab"><div class=" icon-chevron-right"></div>&nbsp;E.A.P</a></li>
                </ul>
              </li>
                
</ul>
                <div class="tab-content" id="reportTemporada">
  
</div>
<script>
 $(function() {
  $(".nav-tabs").tab();
  $(".nav-tabs").bind("show", function(e) {    
    var contentID  = $(e.target).attr("data-target");
    var contentURL = $(e.target).attr("href");
    if (typeof(contentURL) !== 'undefined')
      $(contentID).load(contentURL, function(){ $(".nav-tabs").tab(); });
    else
      $(contentID).tab('show');
  });
  $('.nav-tabs a:first').tab("show");
});

</script>
