<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Eapfacultad"%>
<%@page import="sge.modelo.Filialfacultad"%>
<%@page import="java.util.List"%>
<%@page import="java.net.InetAddress"%>



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
        
        function getidfilial(id){
        $("#listfilialfacultad").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");    
        $.ajax({
            url: '<%=request.getContextPath()%>/Usuario?opt=84',
            type: 'POST',
            async: true,
            data: 'getIDFilialx='+id, 
            success: function(data) {
            $('#listfilialfacultad').fadeIn(1000).html(data);
          //  alert("parametro de ingreso ---> "+id);
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
		<div class="span3">
                    <h4>Mis Filiales :</h4>
                    <ul class="nav nav-list bs-docs-sidenav" >
                        <%
                        List<Filial> listafi=(List<Filial>) request.getSession().getAttribute("listaFilialesAdmin");
                        if (listafi != null) {
                            for (Filial us : listafi) {%>
                            <li value="<%=us.getIdfilial()%>" onclick="getidfilial(this.value);"><a href="#" ><i class="icon-chevron-right"></i><%=us.getDireccion()%></a></li>
                     <%}}%>  
                    </ul>

        	</div>
                    
                    
                <div class="span3">
                    
                                       
                    <h4>Mis Facultades</h4>
                    <ul class="nav nav-list bs-docs-sidenav" id="listfilialfacultad" >
                    <%
                        
                        List<Filialfacultad> listafac=(List<Filialfacultad>) request.getSession().getAttribute("listaFilialFacultad");
                        if (listafac != null) {
                            for (Filialfacultad us : listafac) {

                    %>
                    
                    <li value="<%=us.getIdfilialfacultad()%>"  onclick="getidfilialfacultad(this.value);" >
                        <a href="#" onclick="consolidadoFacultad(<%=us.getIdfilialfacultad()%>);">
                            <i class="icon-chevron-right"></i>
                            <%=us.getIdfacultad_nombre()%>
                        </a>
                    </li>
                    <%}}%>  
        </ul>
                </div>
        <div class="span3">
            <h4>Mis E.A.P</h4>
                <ul class="nav nav-list bs-docs-sidenav" id="listgeteapfacultadfilial">
            <%
        List<Eapfacultad> listaeap=(List<Eapfacultad>) request.getSession().getAttribute("listaFacultadeap");
        if (listaeap != null) {
        for (Eapfacultad us : listaeap) { %>
          <li><a href="#dropdowns"><i class="icon-chevron-right"></i><%=us.getIdeap_name()%></a></li>
          <%}}%>

        </ul>

		</div>
         <div class="span3">
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
                    <div class="pull-right">
                        <div class="icon-map-marker"></div>&nbsp;<strong class="text-info">Ubicacion :</strong><%out.println(inetAddress.getHostName());%>&nbsp;
                        <div class="icon-user"></div>&nbsp;<strong class="text-info">Server Host Name :</strong><%out.println(inetAddress.getHostName());%>&nbsp;
                        <div class="icon-resize-small"></div>&nbsp;<strong class="text-info">Server IP Address :</strong><%out.println(ipadmin);%>
                    </div>
                    </blockquote>
                </div>
	</div>
                    
                    
	<div class="row-fluid">
		
            <div id="consolidado">
                
            </div>
 
	</div>                    