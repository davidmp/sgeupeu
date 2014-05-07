<%@page import="sge.modelo.Filialfacultad"%>
<%@page import="java.util.List"%>
<ul class="nav nav-list bs-docs-sidenav">
    <%
        List<Filialfacultad> lista=(List<Filialfacultad>) request.getSession().getAttribute("listar_filial_facultad");
        if (lista != null) {
        for (Filialfacultad us : lista) { %>
   <li value="<%=us.getIdfilialfacultad()%>" onclick="getidfilialfacultad(this.value);" >
              
              <a href="#dropdowns" onclick="consolidadoFacultad(<%=us.getIdfilialfacultad()%>); " ><i class="icon-chevron-right"></i>
                  <%=us.getIdfacultad_nombre()%>
              </a>
          </li>
          <%}}else{out.println("No Hay Datos");}%>
          
</ul>
