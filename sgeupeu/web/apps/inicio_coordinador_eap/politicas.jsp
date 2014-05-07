<%-- 
    Document   : politicas
    Created on : 13/09/2013, 01:49:06 AM
    Author     : oscdmdz
--%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Politicaupeu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <table class="table table-striped" >     

      <tbody>
                <%
                        int x=0;     
                        List<Politicaupeu> lista=(List<Politicaupeu>) request.getSession().getAttribute("listar_politica_upeu");
                        if (lista != null) {
                            for (Politicaupeu pu : lista) {

                    %>
   
                <tr >
    <td rowspan="2">
        <img src="../../resources/img/logo-upeu-1.png" width="474" height="508" alt="logo-upeu-1"/>
    </td>

                       <td>
                           <p><strong>Visión :</strong></p>
                           <%=pu.getVision()%>
                        
                        </td>
                </tr>
                <tr><td>
                   <p><strong>Misión :</strong></p> 
                  <%=pu.getMision()%>
                  </td>
                </tr>
          
      </tbody>
      <tfoot class="footable-pagination pagination pagination-centered pagination-small">
        <tr>
            <td colspan="6" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
        </tr>
      </tfoot>
    </table>
      <p></p>
        
     <div class="row-fluid">
         <div class="span12">
             <strong>POLÍTICAS INSTITUCIONALES:</strong>
         </div>
     </div>
      <div class="row-fluid">
          <div class="span12">
                      <img class="pull-left" src="<%=request.getContextPath()%>/public/images/avatar/<%=pu.getImagen()%>" width="723" height="509" alt="politicas" class="img-polaroid"/>
        
          </div>
      </div>
              
         <% }} %>      
