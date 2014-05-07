<%@page import="java.net.InetAddress"%>
<%@page import="sge.modelo.Eapfacultad"%>
<%@page import="sge.modelo.Filialfacultad"%>
<%@page import="java.util.List"%>
<script src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.js"></script>
<script>
     
</script>
<div class="row-fluid">
		<div class="span12">
                    <h2 class="text-info">Planificación y Gestión Estratégica</h2>
		</div>
	</div>
	<div class="row-fluid">
		            
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
                    <img src="palette.png" class="img-circle" center />
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