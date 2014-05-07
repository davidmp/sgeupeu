<%@page import="java.net.InetAddress"%>
<%@page import="sge.modelo.Eapfacultad"%>
<%@page import="sge.modelo.Filialfacultad"%>
<%@page import="java.util.List"%>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.js"></script>
<script>
        function getidfilialfacultad(id){
        $("#listgeteapfacultadfilial").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/Usuario?opt=83',
            type: 'POST',
            async: true,
            data: 'idfilialfacultad='+id, 
            success: function(data) {
            $('#listgeteapfacultadfilial').fadeIn(1000).html(data);
            // alert("parametro de ingreso ---> "+id);
            }
           
        });}
           function consolidadoFacultad(id){
        $("#consolidado").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/Indicador?opt=14',
            type: 'POST',
            async: true,
            data: 'idfilialfacultad='+id, 
            success: function(data) {
            $('#consolidado').fadeIn(1000).html(data);
          //  alert("parametro de ingreso ---> "+id);
            }
           
        });}         
        
        
         $('.toltipx').tooltip();                 
</script>
<div class="row-fluid">
		<div class="span12">
                    <h2 class="text-info">Planificación y Gestión Estratégica</h2>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
                    <blockquote>
                        <p class="text-success">
					Mis Facultades
				</p> <small>Universidad Peruana Union</small>
			</blockquote>
                   
                  
                    <ul class="nav nav-list bs-docs-sidenav" >
             <%
                        int x=0;     
                        List<Filialfacultad> lista=(List<Filialfacultad>) request.getSession().getAttribute("listaFilialFacultadfilial");
                        if (lista != null) {
                            for (Filialfacultad us : lista) {

                    %>
                      <li value="<%=us.getIdfilialfacultad()%>"  onclick="getidfilialfacultad(this.value);" >
                        <a href="#" onclick="consolidadoFacultad(<%=us.getIdfilialfacultad()%>);" class="toltipx" title="eeee">
                            <i class="icon-chevron-right"></i>
                            <%=us.getIdfacultad_nombre()%>
                        </a>
                    </li>
                    
                    <%}}%>  
        </ul>
                 </div>
            
            <div class="span4">
                    <blockquote>
                        <p class="text-warning">
					Mis E.A.P
				</p> <small>Universidad Peruana Union</small>
			</blockquote>
                   
                 
                <ul class="nav nav-list bs-docs-sidenav" id="listgeteapfacultadfilial">
            <%
        List<Eapfacultad> listaee=(List<Eapfacultad>) request.getSession().getAttribute("listaEAPFacultadfilial");
        if (listaee != null) {
        for (Eapfacultad us : listaee) { %>
          <li><a href="#dropdowns"><i class="icon-chevron-right"></i><%=us.getIdeap_name()%></a></li>
          <%}}%>
   
        </ul>

		</div>
            
                  
            <div class="span4">
             <blockquote>
                        <p class="text-warning">
					S.G.E
				</p> <small>Sistema de Gestion Estrategica</small>
			</blockquote>
                    <img src="palette.png" class="img-circle" />
            </div>
                
	</div>


        <div class="row-fluid">
            <hr class="divider">
		<div class="span12">
                    <blockquote class="pull-right"><small>Universidad Peruana Union - Desarrollo de Software | Sistema de Gestion Estrategico </small>
                    <%String ipadmin = ""; InetAddress inetAddress = InetAddress.getLocalHost(); ipadmin = inetAddress.getHostAddress();%>
                      </blockquote>
                </div>
	</div>
                    
                    
	<div class="row-fluid">
		
            <div id="consolidado">
                
            </div>
 
	</div>                    