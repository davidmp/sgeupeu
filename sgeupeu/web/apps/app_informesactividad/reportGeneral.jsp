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
                                <center><%="PLANES OPERATIVOS "+datos.get("periodo") %></center>
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
                
                int mes1=0;
                int mes2=0;
                int periodo=0;
                mes1=Integer.parseInt(request.getParameter("mes1")==null?"0":request.getParameter("mes1") );
                mes2=Integer.parseInt(request.getParameter("mes2")==null?"0":request.getParameter("mes2") );
                periodo=Integer.parseInt(request.getParameter("periodo")==null?"0":request.getParameter("periodo") );
                
                ArrayList listaEjes=null;
                listaEjes = (ArrayList)request.getSession().getAttribute("ejeSeleccionadosPOA");            
                Iterator<Object> inter2=listaEjes.iterator();
                contadorY=listaEjes.size();
                
                while(inter2.hasNext()){
                    Map datos=  (Map)inter2.next(); 
                 %>
                <tr>
                    <td colspan="2" style="width: 100%">                    
                        <table style="width: 100%;"   >
                        <tr>
                            <td style="font-size: medium"><center>
                                <b><%=++y%>. -<%=datos.get("nombreejeestrategico")%></b> </center> 
                            </td>
                        </tr>
                        <tr>
                            <td style="font-size: large"><br/>
                                <center><b>PLAN OPERATIVO</b> </center> 
                            </td>
                        </tr>
                 
                        <tr>
                            <td>
                                <br/>
                            <table style="width: 100%; " >
                                
                                 
               <%  

                rs=new ReporteService();                
                lista3=rs.objetivosEstrategicosPOA(Integer.parseInt(datos.get("ideapfacultad").toString()), Integer.parseInt(datos.get("idejeestrategico").toString()), periodo );
                inter3=lista3.iterator();
                contadorM=lista3.size();
                while(inter3.hasNext()){
                datos3=(Map)inter3.next();
                m=m+1;
                %>
                <tr>
                    <td style="width: 100%">
                        <table border="1"  style="width: 100%; border-collapse:collapse"  >
                            <tr>
                                <td style="width: 40%"> Perspectiva:</td>
                                <td style="width: 60%" colspan="2"><%=datos3.get("perspectiva")%></td>                              
                            </tr>
                            <tr>
                                <td style="width: 40%"> Objetivo:</td>
                                <td style="width: 10%"> <%=datos3.get("estrategiacodigo")%></td>
                                <td style="width: 50%" ><%=datos3.get("estrategia")%></td>                              
                            </tr>
                            <tr>
                                <td style="width: 40%"> Indicador:</td>
                                <td style="width: 10%"> <%=datos3.get("indicadorcodigo")%></td>
                                <td style="width: 50%" ><%=datos3.get("indicador")%></td>                              
                            </tr>
                            <tr>
                                <td style="width: 40%"> Meta:</td>
                                <td style="width: 10%"> <%=datos3.get("periodo")%></td>
                                <td style="width: 50%" ><%=datos3.get("metatexto")%></td>                              
                            </tr>

                            
                        </table>
                        
                    </td>                  
                </tr> 
                
                <tr>
                    <td style="width: 100%">
                        <table border="1" style="width: 100%;border-collapse:collapse">
                            <tr style="background-color: #CCCCCC">
                                <td rowspan="2" >#</td> 
                                <td rowspan="2">ACCIONES</td> 
                                <td rowspan="2">CANT.</td> 
                                <td rowspan="2">RESPONSABLE</td> 
                                <td colspan="12" align="center">CRONOGRAMA</td> 
                                <td rowspan="2">PRESUPUESTO</td>                             
                            </tr>
                            <tr style="background-color: #CCCCCC">
                                <td>E</td> 
                                <td>F</td> 
                                <td>M</td> 
                                <td>A</td> 
                                <td>M</td> 
                                <td>J</td> 
                                <td>J</td> 
                                <td>A</td> 
                                <td>S</td> 
                                <td>O</td> 
                                <td>N</td> 
                                <td>D</td> 
                            
                            </tr>
                            
                            <%
                            
                rs=new ReporteService();                
                lista4=rs.actividadesPOA(Integer.parseInt(datos3.get("idmeta").toString()),mes1, mes2);
                inter4=lista4.iterator();
                x=0;
                contadorX=lista4.size();
                while(inter4.hasNext()){
                datos4=(Map)inter4.next();                            
                            
                            
                            %>
                            
                            <tr>
                                <td  ><%=++x%></td> 
                                <td ><%=datos4.get("accion")%></td> 
                                <td ><%=datos4.get("cantidad")%></td> 
                                <td ><%=datos4.get("responsable")%></td> 
                                
                                <td><% if(Integer.parseInt(datos4.get("enero").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("febrero").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("marzo").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("abril").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("mayo").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("junio").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("julio").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("agosto").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("setiembre").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("octubre").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("noviembre").toString())==1){out.print("X");} %></td> 
                                <td><% if(Integer.parseInt(datos4.get("diciembre").toString())==1){out.print("X");} %></td> 
                                <td ><%=datos4.get("presupuesto")%> </td>                            
                            </tr>                            
                            <% 
                            sumadorX=sumadorX+Double.parseDouble(datos4.get("presupuesto").toString());
                            if(x==contadorX){
                            sumadorM=sumadorM+sumadorX;
                            %>
                            <tr>
                                <td colspan="16" align="right" style="background-color: #93a1a1" >SUB TOTAL</td>
                                <td>S/. <%=sumadorX%> </td>
                            </tr>
                            <% sumadorX=0; %>
                            <%
                            }
                            %>
                        <% } %> 
                        
                        </table>                                               
                        <br/>
                        <br/>
                    </td>                  
                </tr> 
                
                <%
                if(m==contadorM){
                    sumadorY=sumadorY+sumadorM;
                %>
                <tr>
                    <td style="width: 100%" align="right" style="background-color: #93a1a1">
                        <table border="1" style="width: 100%;border-collapse:collapse">
                            <tr>
                                <td style="width: 90%;background-color: lightskyblue" align="right">
                                    <b> Total Eje:</b>
                                </td>                
                                <td style="width: 10%">
                                   <b>S/. <%=sumadorM%></b>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>                 
                <%
                sumadorM=0;
                m=0;
                }                
                %>        
                        
                        
                        
                <%
                }
                %>
                            </table>      
                           </td>
                        </tr>  
                    </table>               
            </td>
            </tr>              
            <% }       
            %>

<%
if(y==contadorY){
%>
<tr>
    <td style="width: 100%;border-collapse:collapse" colspan="2" align="center" style="background-color: #93a1a1">
        <table border="1" style="width: 100%;border-collapse:collapse">
            <tr>
                <td style="width: 80%; background-color: #93a1a1" align="right">
                    <b>   Tatal POA Presupesto:</b>
                </td>                
                <td style="width: 20%">
                  <b> S/. <%=sumadorY%></b>
                </td>
            </tr>
        </table>
    </td>
</tr>                                
<%
 }
%>            
            
        </table>
            
        
    
        
    </body>
</html>
