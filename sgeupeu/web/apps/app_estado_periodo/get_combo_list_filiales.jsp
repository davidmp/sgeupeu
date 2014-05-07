<%-- 
    Document   : get_combo_list_filiales
    Created on : 26/10/2013, 07:17:59 PM
    Author     : Edwin
--%>

<%@page import="sge.modelo.Filial"%>
<%@page import="java.util.List"%>


                    
                    <option value=""></option>
                    <% List<Filial> lista=(List<Filial>) request.getSession().getAttribute("ListarFilialCombo");
                        if (lista != null) {
                            for (Filial es : lista) { %>
                  <option value="<%=es.getIdfilial()%>"><%=es.getDireccion()%></option>
            <%}}%>