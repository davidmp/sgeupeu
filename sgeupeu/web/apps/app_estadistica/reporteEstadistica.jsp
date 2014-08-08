<%-- 
    Document   : reportGeneral
    Created on : 18/04/2014, 02:43:05 PM
    Author     : Intel
--%>

<%@page import="sge.modelo.Filial"%>
<%@page import="java.text.DecimalFormat"%>
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
        <link rel="stylesheet" type="text/css" href="rs/pgwjs-rainbow-v202.css">
        <link rel="stylesheet" type="text/css" href="rs/pgwmodal-v202.css">
        <script type="text/javascript" src="rs/jquery-pgwjs-rainbow-v202.js"></script>
        <script type="text/javascript" src="rs/pgwmodal-v202.js"></script>
        
        <link type="text/css" rel="stylesheet" href="../../resources/css/style02.css"/>
        <link type="text/css" rel="stylesheet" href="../../resources/css/style03.css"/>         
        
        <%
                Filial lista=null;
                lista = (Filial)request.getSession().getAttribute("cabeceraInformePOA");

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

                            <table>
                                <tr>                    
                                    <td style="font-size: large" ><center><b><%=lista.getDescripcion() %></b> </center></td>
                                </tr>
                         
                                <tr>
                                <td style="font-size: small">
                                <center><% if(lista.getCategoria().equals("Filial")){ out.print("Vicerrector(a): "+ lista.getRector() ); }else{ out.print("Rector(a): "+ lista.getRector() );} %></center>
                                </td>
                                </tr>
                                <tr>
                                <td style="font-size: x-large"><br/><br/><br/>
                                    <center><%="EVALUACI&Oacute;N DE PLAN ESTRAT&Eacute;GICO "+lista.getEstado() %> </center>
                                </td>
                                </tr>
                            </table>
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
                int cantidadDMP=0;
                DecimalFormat formato= new DecimalFormat("###.##");
                
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
                Map datos=  (Map)interA.next();  
                objCondicionIA=objCondicionIA+1;                     
                
                
                if(idObjGeneralM!=Integer.parseInt(datos.get("idejeestrategico").toString())) {   
                objCondicionM=objCondicionM+1;
                %>
                

              
                <%
                if(idObjGeneralM!=0){
                %>
                            
                <% if(objGeneralM!=Integer.parseInt(objeGeneral[objGeneralM][0].toString()) ){ %>
                <tr>
                <td colspan="10" style="width: 100%">
                    <table border="1" style="width: 100%;border-collapse:collapse">
                        <tr>
                            <td colspan="4" align="center"><b>RESUMEN DE INDICADORES </b></td>
                        </tr>
                        <tr>
                            <td style="width: 50%"><b>Condici&oacute;n</b></td>
                            <td style="width: 10%"><b>Cantidad</b></td>
                            <td style="width: 20%"><b>Porcentaje</b></td>
                            <td style="width: 20%"><b>Color</b></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) no cumplido(s)</td>
                            <td><%=resumenData[0][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[0][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/0.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) en proceso</td>
                            <td><%=resumenData[1][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[1][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/1.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) cumplido(s)</td>
                            <td><%=resumenData[2][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[2][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/2.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) superado(s)</td>
                            <td><%=resumenData[3][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[3][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/3.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) no programado(s)</td>
                            <td><%=resumenData[4][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[4][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/4.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                    </table>
                    </td>
                </tr>                

              <% resumenData=new int[5][1]; cantidadDMP=0;} %> 

              
              
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
                                    <td style="width: 34%" ><b> INDICADORES CLAVES DE DESEMPE&Ntilde;O</b> </td>
                                    <td style="width: 5%" ><b> META</b> </td>
                                    <td style="width: 5%" ><b> AVANCE</b> </td>
                                    <td style="width: 4%" align="center" ><b> COND.</b> </td>
                                    <td style="width: 4%" align="center" ><b> DOC.</b> </td>
                                    <td style="width: 4%" align="center" ><b> OBS.</b> </td>
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
                                            
                                            <a href="javascript:void(0)" onclick="openDialogSeguimiento('<%=datos.get("idavance").toString()%>','<%=datos.get("id_ciclo").toString()%>','(<%out.print(datos.get("codigoin").toString());%>)  <% out.print(datos.get("nombreindicador").toString());%>')">
                                            <img src="../../resources/48/<%=datos.get("tipoavance").toString()%>.png" width="30" height="30" title="<%=datos.get("avancereal").toString()%>"  alt="semaforo"/>
                                            </a>                                            
                                        </center>
                                        </div>
                                    </td>
                                        <td align="center">
                                            <% if(Integer.parseInt(datos.get("archivo").toString())>=1) {  %>
                                            <a href="javascript:void(0)" onclick="openDialogDmp('<%=datos.get("idavance").toString()%>','<%=datos.get("idfilial").toString()%>')">
                                            <img src="../../resources/file/si.png" width="30" height="30" title="<%=datos.get("archivo").toString()%>"  alt="Archivo"/>
                                            </a>
                                            <% }else{ %>
                                            <img src="../../resources/file/no.png" width="30" height="30" title="0"   alt="Archivo"/>
                                            <% } %>                                            
                                        </td>    
                                        <td align="center">
                                           
                                            <a href="javascript:void(0)" onclick="openDialogLista('<%=datos.get("idavance").toString()%>','(<%out.print(datos.get("codigoin").toString());%>)  <% out.print(datos.get("nombreindicador").toString()); %>')">
                                            <img src="../../resources/32/<%=datos.get("obs").toString()%>.png" width="30" height="30" title="<%=datos.get("obs").toString()%>"  alt="Observaciones"/>
                                            </a> 
                                           
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
                                        
                                            <a href="javascript:void(0)" onclick="openDialogSeguimiento('<%=datos.get("idavance").toString()%>','<%=datos.get("id_ciclo").toString()%>','(<%=datos.get("codigoin").toString()%>)  <% out.print(datos.get("nombreindicador").toString());%>')">
                                            <img src="../../resources/48/<%=datos.get("tipoavance").toString()%>.png" width="30" height="30" title="<%=datos.get("avancereal").toString()%>"  alt="semaforo"/>
                                            </a>                                         
                                        </center>
                                    </td>
                                        <td align="center">
                                            <% if(Integer.parseInt(datos.get("archivo").toString())>=1) {  %>
                                            <a href="javascript:void(0)" onclick="openDialogDmp('<%=datos.get("idavance").toString()%>','<%=datos.get("idfilial").toString()%>')">
                                            <img src="../../resources/file/si.png" width="30" height="30" title="<%=datos.get("archivo").toString()%>"  alt="Archivo"/>
                                            </a>
                                            <% }else{ %>
                                            <img src="../../resources/file/no.png" width="30" height="30"  title="0"   alt="Archivo"/>
                                            <% } %>                                            
                                        </td>      
                                        <td align="center">
                                            <a href="javascript:void(0)" onclick="openDialogLista('<%=datos.get("idavance").toString()%>','(<%=datos.get("codigoin").toString()%>)  <% out.print(datos.get("nombreindicador").toString());%>')">
                                            <img src="../../resources/32/<%=datos.get("obs").toString()%>.png" width="30" height="30" title="<%=datos.get("obs").toString()%>"  alt="Observaciones"/>
                                            </a>                                           
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
                                        
                                            <a href="javascript:void(0)" onclick="openDialogSeguimiento('<%=datos.get("idavance").toString()%>','<%=datos.get("id_ciclo").toString()%>','(<%out.print(datos.get("codigoin").toString());%>)<% out.print(datos.get("nombreindicador").toString());%>')">
                                            <img src="../../resources/48/<%=datos.get("tipoavance").toString()%>.png" width="30" height="30" title="<%=datos.get("avancereal").toString()%>"  alt="semaforo"/>
                                            </a>                                         
                                        </center>
                                    </td>
                                        <td align="center">
                                            <% if(Integer.parseInt(datos.get("archivo").toString())>=1) {  %>
                                            <a href="javascript:void(0)" onclick="openDialogDmp('<%=datos.get("idavance").toString()%>','<%=datos.get("idfilial").toString()%>')">
                                            <img src="../../resources/file/si.png" width="30" height="30" title="<%=datos.get("archivo").toString()%>"  alt="Archivo"/>
                                            </a>
                                            <% }else{ %>
                                            <img src="../../resources/file/no.png" width="30" height="30"  title="0"   alt="Archivo"/>
                                            <% } %>                                            
                                        </td>     
                                        <td align="center">
                                            <a href="javascript:void(0)" onclick="openDialogLista('<%=datos.get("idavance").toString()%>','(<%out.print(datos.get("codigoin").toString());%>)  <% out.print(datos.get("nombreindicador").toString());%>')">
                                            <img src="../../resources/32/<%=datos.get("obs").toString()%>.png" width="30" height="30" title="<%=datos.get("obs").toString()%>"  alt="Observaciones"/>
                                            </a>                                           
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
                                            <a href="javascript:void(0)" onclick="openDialogSeguimiento('<%=datos.get("idavance").toString()%>','<%=datos.get("id_ciclo").toString()%>','(<% out.print(datos.get("codigoin").toString());%>) :: <% out.print(datos.get("nombreindicador").toString()); %>')">
                                            <img src="../../resources/48/<%=datos.get("tipoavance").toString()%>.png" width="30" height="30" title="<%=datos.get("avancereal").toString()%>"  alt="semaforo"/>
                                            </a>                                             
                                            
                                        </center>
                                    </td>
                                        <td align="center">
                                            <% if(Integer.parseInt(datos.get("archivo").toString())>=1) {  %>
                                            <a href="javascript:void(0)" onclick="openDialogDmp('<%=datos.get("idavance").toString()%>','<%=datos.get("idfilial").toString()%>')">
                                            <img src="../../resources/file/si.png" width="30" height="30" title="<%=datos.get("archivo").toString()%>"  alt="Archivo"/>
                                            </a>
                                            <% }else{ %>
                                            <img src="../../resources/file/no.png" width="30" height="30" title="0"  alt="Archivo"/>
                                            <% } %>                                            
                                        </td>      
                                        <td align="center">
                                            <a href="javascript:void(0)" onclick="openDialogLista('<%=datos.get("idavance").toString()%>','(<% out.print(datos.get("codigoin").toString()); %>)  <% out.print(datos.get("nombreindicador").toString()); %>')">
                                            <img src="../../resources/32/<%=datos.get("obs").toString()%>.png" width="30" height="30" title="<%=datos.get("obs").toString()%>"  alt="Observaciones"/>
                                            </a>                                          
                                        </td>      
                                </tr>                                                          
                        
                <% } %>
                
                <% if(objCondicionIA==objCondicionI ){ %>
                <%
                switch(Integer.parseInt(datos.get("tipoavance").toString())){                    
                    case 0:{  resumenData[0][0]=(resumenData[0][0])+1; cantidadDMP++; }break;
                    case 1:{  resumenData[1][0]=(resumenData[1][0])+1; cantidadDMP++;}break;
                    case 2:{  resumenData[2][0]=(resumenData[2][0])+1; cantidadDMP++;}break;
                    case 3:{  resumenData[3][0]=(resumenData[3][0])+1; cantidadDMP++;}break;
                    case 4:{  resumenData[4][0]=(resumenData[4][0])+1; cantidadDMP++;}break;
                }                 
                %>
                
                <tr>
                <td colspan="10" style="width: 100%;">
                        <table border="1" style="width: 100%;border-collapse:collapse">
                        <tr>
                            <td colspan="4" align="center"><b>RESUMEN DE INDICADORES </b></td>
                        </tr>
                        <tr>
                            <td style="width: 50%"><b>Condici&oacute;n</b></td>
                            <td style="width: 10%"><b>Cantidad</b></td>
                            <td style="width: 20%"><b>Porcentaje</b></td>
                            <td style="width: 20%"><b>Color</b></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) no cumplido(s)</td>
                            <td><%=resumenData[0][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[0][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/0.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) en proceso</td>
                            <td><%=resumenData[1][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[1][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/1.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) cumplido(s)</td>
                            <td><%=resumenData[2][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[2][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/2.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) superado(s)</td>
                            <td><%=resumenData[3][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[3][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/3.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                        <tr>
                            <td>Indicador(es) no programado(s)</td>
                            <td><%=resumenData[4][0]%> </td>
                            <td><% out.print( formato.format((Math.rint((Double.parseDouble(String.valueOf(resumenData[4][0]))/cantidadDMP)*100)/100)*100)+"%" ); %></td>
                            <td><img src="../../resources/48/4.png" width="30" height="30"   alt="semaforo"/></td>
                        </tr>
                    </table>
                    </td>
                </tr>                

              <% resumenData=new int[5][1]; cantidadDMP=0;} %> 

                <%
                
                switch(Integer.parseInt(datos.get("tipoavance").toString())){                    
                    case 0:{  resumenData[0][0]=(resumenData[0][0])+1; cantidadDMP++; }break;
                    case 1:{  resumenData[1][0]=(resumenData[1][0])+1; cantidadDMP++;}break;
                    case 2:{  resumenData[2][0]=(resumenData[2][0])+1; cantidadDMP++;}break;
                    case 3:{  resumenData[3][0]=(resumenData[3][0])+1; cantidadDMP++;}break;
                    case 4:{  resumenData[4][0]=(resumenData[4][0])+1; cantidadDMP++;}break;
                }                
                idObjGeneralM=Integer.parseInt(datos.get("idejeestrategico").toString());
                }
                %>
                
     
                
        </table>
<script type="text/javascript">


function openDialogDmp(id, idfilial){
 	$.pgwModal({
        url: 'ver.jsp?idavance='+id+"&idfilial="+idfilial,
		title : 'Cuadro para Descargar Archivos',
		loading: '<span style="text-align:center">Loading in progress</span>',
		close: true	});   
}
function openDialogSeguimiento(idavance, idciclo, indicador){
        var demo=indicador.replace("%","Porcentaje");
       
 	$.pgwModal({
        url: 'formObervacion.jsp?idavance='+idavance+"&idciclo="+idciclo+"&indicador="+demo,
		title : 'Realizar Observaciones',
		loading: '<span style="text-align:center">Loading in progress</span>',
		close: true
                });  
}
function openDialogLista(idavance, indicador){
     var demo=indicador.replace("%","Porcentaje");
 	$.pgwModal({
        url: 'listObservacion.jsp?idavance='+idavance+"&indicador="+demo,
		title : 'Listar Observaciones',
		loading: '<span style="text-align:center">Loading in progress</span>',
		close: true, maxWidth: 900	});   
}

</script>                
<div style="display: none;" id="pgwModalWrapper"></div>  


</body>
</html>