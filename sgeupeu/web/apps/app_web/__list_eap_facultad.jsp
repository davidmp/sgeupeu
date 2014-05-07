<%@page import="sge.modelo.Eapfacultad"%>
<%@page import="java.util.List"%>
<ul class="nav nav-list bs-docs-sidenav">
    <%
        List<Eapfacultad> lista=(List<Eapfacultad>) request.getSession().getAttribute("listar_eap_facultad");
        if (lista != null) {
        for (Eapfacultad us : lista)  { %>
        <li value="<%=us.getIdfilialfacultad()%>" onclick="getidfilialfacultad(this.value);" >
          <a href="#dropdowns" onclick="consolidadoFacultad(<%=us.getIdfilialfacultad()%>); "><i class="icon-chevron-right"></i><%=us.getIdfilialfacultad()%></a></li>
          <%}}else{out.println("No Hay Datos");}%>
                             
</ul>
