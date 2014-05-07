
<%@page import="sge.modelo.Eapfacultad"%>
<%@page import="java.util.List"%>
                    <option value=""></option>
                    <% List<Eapfacultad> lista=(List<Eapfacultad>) request.getSession().getAttribute("comboEAPFacultadSession");
                        if (lista != null) {
                            for (Eapfacultad es : lista) { %>
                  <option value="<%=es.getIdeapfacultad()%>"><%=es.getIdeap_name()%></option>
            <%}}%>
