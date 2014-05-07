<%-- 
    Document   : get_combo_filial_facultad
    Created on : 27/10/2013, 02:07:37 AM
    Author     : Edwin
--%>

<%@page import="java.util.List"%>
<%@page import="sge.modelo.Filialfacultad"%>
<option value=""></option>
<% List<Filialfacultad> lista=(List<Filialfacultad>) request.getSession().getAttribute("listar_Filial_Facultad_Combo");
                        if (lista != null) {
                            for (Filialfacultad es : lista) { %>
                  <option value="<%=es.getIdfilialfacultad()%>"><%=es.getIdfacultad_nombre()%></option>
            <%}}%>
