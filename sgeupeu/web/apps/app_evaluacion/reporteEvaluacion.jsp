<%-- 
    Document   : reportGeneral
    Created on : 18/04/2014, 02:43:05 PM
    Author     : Intel
--%>

<%@page import="sge.service.ReporteService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="../../resources/css/style02.css"/>
        <link type="text/css" rel="stylesheet" href="../../resources/css/style03.css"/>         
        
        <%
                ArrayList lista=null;
                lista = (ArrayList)request.getSession().getAttribute("cabeceraInformePOA");
                        
                Iterator<Object> inter=lista.iterator();

        %>
        
    </head>
    <body>
   
        
        <table style="width: 100%">
            <tr>
                <td   style="padding-left: 1px;  width: 1%"> <center>
                    <img src="../../resources/img/logo-upeu-1.png" width="90" height="98" alt="logo-upeu-color"/>

                        </center> </td>
                <td style=" width: 99%">
                <center>    
                <div id="cabecera">
                            <%
                            while(inter.hasNext()){
                                Map datos=  (Map)inter.next();
                            %>
                            <table>
                                <tr>                    
                                    <td style="font-size: large" ><center><b><%=datos.get("razonsocial")%></b></center></td>
                                </tr>
                                <tr>
                                <td style="font-size: small">
                                <center><% if(datos.get("categoria").equals("Filial")){ out.print("Filial - "+ datos.get("direccion").toString() ); }else{ out.print("Sede - "+ datos.get("direccion").toString() );} %> </center>
                                </td>
                                </tr>
                                <tr>
                                    <td style="font-size: medium">
                                <center><b><%=datos.get("nombrefacultad") %></b> </center>
                                </td>
                                </tr>
                                <tr>
                                <td style="font-size: small"><br/>
                                <center><b><%="E.A.P de "+datos.get("nombreeap") %></b> </center>
                                </td>
                                </tr>
                                <tr>
                                <td style="font-size: small">
                                <center><% if(datos.get("categoria").equals("Filial")){ out.print("Coordinador(a) de Escuela: "+ datos.get("coordinador") ); }else{ out.print("Director de Escuela: "+ datos.get("coordinador") );} %></center>
                                </td>
                                </tr>
                                <tr>
                                <td style="font-size: x-large"><br/><br/><br/>
                                    <center><%="EVALUACI&Oacute;N DE PLAN ESTRAT&Eacute;GICO "+datos.get("periodo") %> </center>
                                </td>
                                </tr>
                            </table>
                            <% } %>
                        </div>
                        </center>
                </td>
            </tr> 
            <tr>
            <td colspan="2">
            <center><hr/></center>                                    
            </td>
            </tr>
            
            

                        
                        
                        
                        
                        
                        
                        
            <%
                ReporteService rs;
                ArrayList lista3;
                Iterator<Object> inter3;
                Map datos3;
                
                ArrayList lista4;
                Iterator<Object> inter4;
                Map datos4;
                int x;
                int y=0;
                int m=0;
                int contadorX=0;
                int contadorY=0;
                int contadorM=0;
                double sumadorX=0;
                double sumadorY=0;
                double sumadorM=0;
                int idtipoarea=0;
                idtipoarea=Integer.parseInt(request.getParameter("idtipoarea")==null?"0":request.getParameter("idtipoarea") );
                ArrayList listaEjes=null;
                listaEjes = (ArrayList)request.getSession().getAttribute("ejeSeleccionadosPOA");            
                Iterator<Object> inter2=listaEjes.iterator();
                contadorY=listaEjes.size();
                
                Object[][] objeGeneral;
                objeGeneral=new Object[listaEjes.size()][3];
                int objGX=0;
                int objGY= -1;
                while(inter2.hasNext()){
                    Map datos=  (Map)inter2.next(); 
                    objeGeneral[objGX][++objGY]=datos.get("idejeestrategico");
                    objeGeneral[objGX][++objGY]=datos.get("nombreejeestrategico");
                    objeGeneral[objGX][++objGY]=datos.get("objetivogeneral");
                    objGX=objGX+1;
                    objGY=-1;                    
                }
                
                int objGeneralM=0;
                int objGeneralM1=0;
                int objPerspectivaM=0;
                int objEstrategicoM=0;
                
                int idObjGeneralM=0;
                int idObjGeneralM1=0;
                int idObjPerspectivaM=0;
                int idObjEstrategicoM=0;
                
                int objCondicionM=0;
                int objCondicionI=0;
                int objCondicionIA=0;
                
                ArrayList listaAvance=null;
                listaAvance = (ArrayList)request.getSession().getAttribute("avanceTodoSemaforo");            
                Iterator<Object> interA=listaAvance.iterator();                
                objCondicionI=listaAvance.size();
                
                int [][] resumenData;                
                resumenData=new int[5][1];           
                while(interA.hasNext()){
                objPerspectivaM=0;
                objCondicionIA=objCondicionIA+1;    
                Map datos=  (Map)interA.next();    
              
                switch(Integer.parseInt(datos.get("tipoavance").toString())){
                    case 0:{  resumenData[0][0]=resumenData[0][0]+1; }break;
                    case 1:{  resumenData[1][0]=resumenData[1][0]+1; }break;
                    case 2:{  resumenData[2][0]=resumenData[2][0]+1;}break;
                    case 3:{  resumenData[3][0]=resumenData[3][0]+1; }break;
                    case 4:{  resumenData[4][0]=resumenData[4][0]+1;}break;
                }
                
                
                if(idObjGeneralM!=Integer.parseInt(datos.get("idejeestrategico").toString())) {   
                objCondicionM=objCondicionM+1;
                %>
                

              
                <%
                if(idObjGeneralM!=0){
                %>
                            
                <% if(objGeneralM!=Integer.parseInt(objeGeneral[objGeneralM][0].toString()) ){ %>
                <tr>
                <td colspan="9" style="width: 100%">
                    <table border="1" style="width: 100%;border-collapse:collapse">
                        <tr>
                            <td colspan="4" align="center"><b>RESUMEN DE INDICADORES</b></td>
                        </tr>
                        <tr>
                            <td style="width: 50%"><b>Condici&oacute;n</b></td>
                            <td style="width: 10%"><b>Cantidad</b></td>
                            <td style="width: 20%"><b>Porcentaje</b></td>
                            <td style="width: 20%"><b>Color</b></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) no cumplido</td>
                            <td><%=resumenData[0][0]%> </td>
                            <td><%=resumenData[0][0]%></td>
                            <td><img src="../../resources/48/0.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) en proceso</td>
                            <td><%=resumenData[1][0]%> </td>
                            <td><%=resumenData[1][0]%></td>
                            <td><img src="../../resources/48/1.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) cumplidos</td>
                            <td><%=resumenData[2][0]%> </td>
                            <td><%=resumenData[2][0]%></td>
                            <td><img src="../../resources/48/2.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) superados</td>
                            <td><%=resumenData[3][0]%> </td>
                            <td><%=resumenData[3][0]%></td>
                            <td><img src="../../resources/48/3.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) no programados</td>
                            <td><%=resumenData[4][0]%> </td>
                            <td><%=resumenData[4][0]%></td>
                            <td><img src="../../resources/48/4.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                    </table>
                    </td>
                </tr>                

              <% resumenData=new int[5][1]; } %> 

              
              
                            </table> 
                         </td>
                        </tr>
                       
                    </table>                     
                  </td>                    
                </tr> 
               
                <%
                }
                %>
                 
                <%
                if(objGeneralM < objeGeneral.length){
                %>
                <tr>
                    <td  colspan="2" style="width: 100%">
                        <table style="width: 100%;" >
                            <tr>
                                <td style="font-size: medium">
                                    <center><b><%=++y%>. -<%=(objeGeneral[objGeneralM][1].toString()) %> </b></center>
                                </td>                                
                            </tr>
                            <tr>
                                <td style="font-size: medium"><br/>
                                    <center><b>MATRIZ DE DESPLIEGUE DE <%= ((objeGeneral[objGeneralM][1])).toString().toUpperCase() %> </b> </center> 
                                </td>
                            </tr>
                          
                          <tr>
                            <td style="width: 100%; ">
                            <br/>
                            <table  border="1" style="width: 100%;border-collapse:collapse" >
                                <tr>
                                    <td style="width: 10%" ><b> OBJETIVO</b> </td>
                                    <td style="width: 10%" ><b> PERSPECTIVA</b> </td>
                                    <td style="width: 20%" ><b> ESTRATEGIA</b> </td>
                                    <td style="width: 4%" ><b> ID</b> </td>
                                    <td style="width: 38%" ><b> INDICADORES CLAVES DE DESEMPE&Ntilde;O</b> </td>
                                    <td style="width: 5%" ><b> META</b> </td>
                                    <td style="width: 5%" ><b> AVANCE</b> </td>
                                    <td style="width: 4%" align="center" ><b> COND.</b> </td>
                                    <td style="width: 4%" align="center" ><b> DOC.</b> </td>
                                </tr>   
                                
                <%       }
                
                 objGeneralM=objGeneralM+1;
                 }
                %>
                
                <% if(idObjGeneralM1!=Integer.parseInt(datos.get("idejeestrategico").toString())) {  %>
                
                                <tr>
                                    <td rowspan="<%=Integer.parseInt(datos.get("cantidadeje").toString()) %>"> <b><%=datos.get("idejeestrategico").toString()%></b>:  <%=(objeGeneral[objGeneralM-1][2]).toString()%> </td>                                    
                                    <td rowspan="<%=Integer.parseInt(datos.get("cantidadperspectiva").toString()) %>"><b><%=datos.get("nombreperspectiva").toString()%></b> </td>
                                    <% idObjPerspectivaM = Integer.parseInt(datos.get("idperspectiva").toString()); %>                                    
                                    <td rowspan="<%=Integer.parseInt(datos.get("cantidadestrategia").toString()) %>"> <b><%=datos.get("codigo").toString()%></b>:  <%=datos.get("nombre").toString()%> </td>
                                    <%  idObjEstrategicoM = Integer.parseInt(datos.get("idejeestrategia").toString()); %>
                                    <td><b><%=datos.get("codigoin").toString()%></b></td>
                                    <td><%=datos.get("nombreindicador").toString()%></td>
                                    <td align="center" >
                                    <% if(Integer.parseInt(datos.get("idtipometa").toString())==1) { %>
                                        #<b><%=datos.get("meta")%></b>
                                    <% }else{ 
                                     %>
                                     <b><%=datos.get("meta")%></b>%
                                   <% } %>
                                    </td>
                                    <td align="center" >
                                    <% if(Integer.parseInt(datos.get("idtipometa").toString())==1) { %>
                                    #<b><%= Math.round(Double.parseDouble(datos.get("avance").toString())) %></b>
                                    <% }else{ 
                                     %>
                                     <b><%=Math.round(Double.parseDouble(datos.get("avance").toString()))%></b>%
                                   <% } %>
                                    </td>

                                    <td  >
                                        <div>
                                        <center>                                        
                                            <img src="../../resources/48/<%=datos.get("tipoavance").toString()%>.png" width="30" height="30" title="<%=datos.get("avancereal").toString()%>"  alt="semaforo"/>
                                        </center>
                                        </div>
                                    </td>
                                        <td align="center">
                                            <% if(Integer.parseInt(datos.get("archivo").toString())>=1) {  %>
                                            <img src="../../resources/file/si.png" width="30" height="30" title="<%=datos.get("archivo").toString()%>"  alt="Archivo"/>
                                            <% }else{ %>
                                            <img src="../../resources/file/no.png" width="30" height="30" title="0"   alt="Archivo"/>
                                            <% } %>                                            
                                        </td>    
                                </tr>
                <% idObjGeneralM1=Integer.parseInt(datos.get("idejeestrategico").toString()); %>                    
                
                
                <% } else if(idObjPerspectivaM!=Integer.parseInt(datos.get("idperspectiva").toString())) {  %>  
                                <tr>
                                    
                                    <td rowspan="<%=Integer.parseInt(datos.get("cantidadperspectiva").toString()) %>"> <b><%=datos.get("nombreperspectiva").toString()%></b> </td>
                                    <td rowspan="<%=Integer.parseInt(datos.get("cantidadestrategia").toString()) %>"> <b><%=datos.get("codigo").toString()%></b>:  <%=datos.get("nombre").toString()%> </td>
                                    <%  idObjEstrategicoM = Integer.parseInt(datos.get("idejeestrategia").toString()); %>
                                    <td><b><%=datos.get("codigoin").toString()%></b></td>
                                    <td><%=datos.get("nombreindicador").toString()%></td>
                                    <td align="center" >
                                    <% if(Integer.parseInt(datos.get("idtipometa").toString())==1) { %>
                                        #<b><%=datos.get("meta")%></b>
                                    <% }else{ 
                                     %>
                                     <b><%=datos.get("meta")%></b>%
                                   <% } %>
                                    </td>
                                    <td align="center" >
                                    <% if(Integer.parseInt(datos.get("idtipometa").toString())==1) { %>
                                    #<b><%= Math.round(Double.parseDouble(datos.get("avance").toString())) %></b>
                                    <% }else{ 
                                     %>
                                     <b><%=Math.round(Double.parseDouble(datos.get("avance").toString()))%></b>%
                                   <% } %>
                                    </td>
                                    <td  >
                                        <center>                                        
                                        <img src="../../resources/48/<%=datos.get("tipoavance").toString()%>.png" width="30" height="30" title="<%=datos.get("avancereal").toString()%>"  alt="semaforo"/>
                                        </center>
                                    </td>
                                        <td align="center">
                                            <% if(Integer.parseInt(datos.get("archivo").toString())>=1) {  %>
                                            <img src="../../resources/file/si.png" width="30" height="30" title="<%=datos.get("archivo").toString()%>"  alt="Archivo"/>
                                            <% }else{ %>
                                            <img src="../../resources/file/no.png" width="30" height="30"  title="0"   alt="Archivo"/>
                                            <% } %>                                            
                                        </td>      
                                </tr>  
                <%  idObjPerspectivaM = Integer.parseInt(datos.get("idperspectiva").toString()); %>                  
               <% } else if(idObjEstrategicoM!=Integer.parseInt(datos.get("idejeestrategia").toString())){  %>
                
                                <tr>

                                    <td rowspan="<%=Integer.parseInt(datos.get("cantidadestrategia").toString()) %>"> <b><%=datos.get("codigo").toString()%></b>:  <%=datos.get("nombre").toString()%> </td>
                                    <td><b><%=datos.get("codigoin").toString()%></b></td>
                                    <td><%=datos.get("nombreindicador").toString()%></td>
                                    <td align="center" >
                                    <% if(Integer.parseInt(datos.get("idtipometa").toString())==1) { %>
                                        #<b><%=datos.get("meta")%></b>
                                    <% }else{ 
                                     %>
                                     <b><%=datos.get("meta")%></b>%
                                   <% } %>
                                    </td>
                                    <td align="center" >
                                    <% if(Integer.parseInt(datos.get("idtipometa").toString())==1) { %>
                                    #<b><%= Math.round(Double.parseDouble(datos.get("avance").toString())) %></b>
                                    <% }else{ 
                                     %>
                                     <b><%=Math.round(Double.parseDouble(datos.get("avance").toString()))%></b>%
                                   <% } %>
                                    </td>
                                    <td  >
                                        <center>                                        
                                        <img src="../../resources/48/<%=datos.get("tipoavance").toString()%>.png" width="30" height="30" title="<%=datos.get("avancereal").toString()%>"  alt="semaforo"/>
                                        </center>
                                    </td>
                                        <td align="center">
                                            <% if(Integer.parseInt(datos.get("archivo").toString())>=1) {  %>
                                            <img src="../../resources/file/si.png" width="30" height="30" title="<%=datos.get("archivo").toString()%>"  alt="Archivo"/>
                                            <% }else{ %>
                                            <img src="../../resources/file/no.png" width="30" height="30"  title="0"   alt="Archivo"/>
                                            <% } %>                                            
                                        </td>     
                                </tr>                
                <%  idObjEstrategicoM = Integer.parseInt(datos.get("idejeestrategia").toString()); %> 
                <% } else {%>
                                <tr>                                    
                                    <td><b><%=datos.get("codigoin").toString()%></b></td>
                                    <td><%=datos.get("nombreindicador").toString()%></td>
                                    <td align="center" >
                                    <% if(Integer.parseInt(datos.get("idtipometa").toString())==1) { %>
                                        #<b><%=datos.get("meta")%></b>
                                    <% }else{ 
                                     %>
                                     <b><%=datos.get("meta")%></b>%
                                   <% } %>
                                    </td>
                                    <td align="center" >
                                    <% if(Integer.parseInt(datos.get("idtipometa").toString())==1) { %>
                                    #<b><%= Math.round(Double.parseDouble(datos.get("avance").toString())) %></b>
                                    <% }else{ 
                                     %>
                                     <b><%=Math.round(Double.parseDouble(datos.get("avance").toString()))%></b>%
                                   <% } %>
                                    </td>
                                    <td  >
                                        <center>                                        
                                            <img src="../../resources/48/<%=datos.get("tipoavance").toString()%>.png" width="30" height="30" title="<%=datos.get("avancereal").toString()%>"  alt="semaforo"/>
                                        </center>
                                    </td>
                                        <td align="center">
                                            <% if(Integer.parseInt(datos.get("archivo").toString())>=1) {  %>
                                            <img src="../../resources/file/si.png" width="30" height="30" title="<%=datos.get("archivo").toString()%>"  alt="Archivo"/>
                                            <% }else{ %>
                                            <img src="../../resources/file/no.png" width="30" height="30" title="0"  alt="Archivo"/>
                                            <% } %>                                            
                                        </td>      
                                </tr>                                                          
                        
                <% } %>
                
                <% if(objCondicionIA==objCondicionI ){ %>
                <tr>
                <td colspan="9" style="width: 100%;">
                        <table border="1" style="width: 100%;border-collapse:collapse">
                        <tr>
                            <td colspan="4" align="center"><b>RESUMEN DE INDICADORES</b></td>
                        </tr>
                        <tr>
                            <td style="width: 50%"><b>Condici&oacute;n</b></td>
                            <td style="width: 10%"><b>Cantidad</b></td>
                            <td style="width: 20%"><b>Porcentaje</b></td>
                            <td style="width: 20%"><b>Color</b></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) no cumplido</td>
                            <td><%=resumenData[0][0]%> </td>
                            <td><%=resumenData[0][0]%></td>
                            <td><img src="../../resources/48/0.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) en proceso</td>
                            <td><%=resumenData[1][0]%> </td>
                            <td><%=resumenData[1][0]%></td>
                            <td><img src="../../resources/48/1.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) cumplidos</td>
                            <td><%=resumenData[2][0]%> </td>
                            <td><%=resumenData[2][0]%></td>
                            <td><img src="../../resources/48/2.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) superados</td>
                            <td><%=resumenData[3][0]%> </td>
                            <td><%=resumenData[3][0]%></td>
                            <td><img src="../../resources/48/3.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) no programados</td>
                            <td><%=resumenData[4][0]%> </td>
                            <td><%=resumenData[4][0]%></td>
                            <td><img src="../../resources/48/4.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                    </table>
                    </td>
                </tr>                

              <% resumenData=new int[5][1]; } %> 

                <%
                idObjGeneralM=Integer.parseInt(datos.get("idejeestrategico").toString());
                }
                %>
                
                
                
        </table>
    </body>
</html>