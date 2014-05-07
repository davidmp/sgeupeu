<%@page import="java.util.List"%>
<%@page import="sge.modelo.Periodometa"%>

                    
                    <option value=""></option>
                    <% List<Periodometa> lista=(List<Periodometa>) request.getSession().getAttribute("listar_combo_periodo");
                        if (lista != null) {
                            for (Periodometa es : lista) { %>
                  <option value="<%=es.getIdperiodometa()%>">Periodo : <%=es.getPeriodo()%></option>
            <%}}%>
               