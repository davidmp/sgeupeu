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
<%@ include file="PictureCharts.jsp"%>
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
        <script type="text/javascript" src="../../resources/PictureCharts/PictureCharts.js"></script>        
        
        <%
                int ideje=Integer.parseInt(request.getParameter("eje")==null?"0":request.getParameter("eje") );                
                String nombreeje=request.getParameter("nombreeje")==null?"X":request.getParameter("nombreeje").toString();                
                int idperiodo=Integer.parseInt(request.getParameter("idperiodo")==null?"0":request.getParameter("idperiodo") );                
                int idtipoarea=Integer.parseInt(request.getParameter("idtipoarea")==null?"0":request.getParameter("idtipoarea"));               
                int idFilialPri=Integer.parseInt(request.getParameter("idFilialPri")==null?"0":request.getParameter("idFilialPri"));               
                ReporteService rs;
                Filial lista=null;
                lista = (Filial)request.getSession().getAttribute("cabeceraInformePOA");                
                String partirNombre[]=(lista.getDescripcion()).split("-");
                
                Object  matrizMadre[][][];

                int xcode=0;
                Object matrizPersonal[][];
                ArrayList listaCarrera=null;
                
                listaCarrera = (ArrayList)request.getSession().getAttribute("carrerasPregradoRE");                        
                Iterator<Object> inter=listaCarrera.iterator();  
                int cantidadCarrera = listaCarrera.size();
                String[] nombrecarreras=new String[cantidadCarrera];
                matrizMadre=new String[cantidadCarrera][0][0];
                int i=0;
                ArrayList listaIndica;
                while(inter.hasNext()){                
                Map datos=  (Map)inter.next();
                rs=new ReporteService();
                listaIndica=null;
                
                listaIndica=rs.evaluacionPlanEstrategico(Integer.parseInt(datos.get("ideapfacultad").toString()), idperiodo, idtipoarea, idFilialPri,ideje);                
                matrizPersonal=new String[listaIndica.size()][4];
                Iterator<Object> interXX=listaIndica.iterator();  
                i=0;
                while(interXX.hasNext()){
                Map datosXX=  (Map)interXX.next();
                matrizPersonal[i][0]=datosXX.get("nombreindicador").toString();
                matrizPersonal[i][1]=datosXX.get("meta").toString();
                matrizPersonal[i][2]=String.valueOf(Math.round(Double.parseDouble(datosXX.get("avance").toString())));
                matrizPersonal[i][3]=datosXX.get("idtipometa").toString();
                i++;
                }
                matrizMadre[xcode]=matrizPersonal;
                nombrecarreras[xcode]=datos.get("nombre").toString();
                xcode++;
                }
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
                                    <td style="font-size: large" ><center><b><%=partirNombre[0] %></b> </center></td>
                                </tr>
                                <tr>                    
                                    <td style="font-size: large" ><center><b><%=partirNombre[1] %></b>                                         
                                        </center></td>
                                </tr>
                                
                                <tr>
                                <td style="font-size: small">
                                    <br/>
                                <center><% if(lista.getCategoria().equals("Filial")){ out.print("Vicerrector(a): "+ lista.getRector() ); }else{ out.print("Rector(a): "+ lista.getRector() );} %></center>
                                </td>
                                </tr>
                                <tr>
                                <td style="font-size: x-large"><br/><br/><br/>
                                    <center><%="ESTADISTICAS DE PLAN ESTRAT&Eacute;GICO "+lista.getEstado() %> </center>
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
            <tr>
                <td colspan="2" style="font-size: large" align="center" >
                <center>
                   
                        <%
                        String color="#";
                        String nombre="";
                        if(ideje==1 || ideje==5){ nombre="Enseñanza y Aprendizaje"; color="19cb4f"; }
                        if(ideje==2 || ideje==6){ nombre="Investigación"; color="23d1f6"; }
                        if(ideje==3){ nombre="Proyección Social y Extensión Universitaria"; color="5bf019"; }
                        if(ideje==4){ nombre="Desarrollo Espiritual"; color="4ca5d3"; }
                        
                        %>
                        Eje: <%=nombre%>                   
                     
                </center> 
                        <br/>
            </td>
            </tr>
            <tr>
            <td colspan="2">
                <center>
                    <div>
                        <table  border="0" style="width: 100%;border-collapse:collapse" >
                            
                          <%
                          String strXML="";
                          String strCategories="";
                          String strDataCurr ="";
                          String strDataPrev="";
                          String chartCode="";
                          String Junt="";
                          int dmpN=0;
                          for(int y=0; y<matrizMadre[0].length;y++){
                         
                          

   	                                    
                          %>  
                            
                            <tr>
                                <td align="center"><br/>    
                                 <b> <%=++dmpN%>:  <%= matrizMadre[0][y][0]%> </b><br/><br/>
                                  <%
	      	strXML = "<chart caption='Cumplimiento del Indicador - "+lista.getEstado()+"' subCaption='Por Escuela Académico Profesional' lineThickness='1' showValues='0' formatNumberScale='0' anchorRadius='2'   divLineAlpha='40' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridColor='CC3300' shadowAlpha='40' numvdivlines='5' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10' labelDisplay='Rotate' slantLabels='1' chartLeftMargin='40' chartTopMargin='10' chartRightMargin='20' chartBottomMargin='5' captionPadding='10' xAxisNamePadding='5' >";
	    	strCategories ="<categories>";	   
	    	strDataCurr = "<dataset seriesName='Avance' color='F6BD0F'>";
   	        strDataPrev = "<dataset seriesName='Meta' color='"+color+"'>";                                  
                                  %>                                 
                                  <% for (int n=0; n<nombrecarreras.length;n++){%>                                   
                                  <%
                                      int metaX=Integer.parseInt((matrizMadre[n][y][1]).toString());
                                      int avanceX=Integer.parseInt((matrizMadre[n][y][2]).toString());
                            %>
                            <%                                      
			strCategories = strCategories + "<category name='"+nombrecarreras[n]+"' />";
                        strDataCurr = strDataCurr+"<set value='"+avanceX+"'  />";
                        strDataPrev = strDataPrev + "<set value='"+metaX+"'  />";
                                                          
                                  
                                  %>
                                  <% } 
                                  
                strDataCurr = strDataCurr + "</dataset>";	    	
	    	strDataPrev = strDataPrev + "</dataset>";
	    	
	        
	    	Junt= strDataPrev+strDataCurr;
	    	strCategories = strCategories + "</categories>";
    		strXML = strXML + strCategories +Junt+"</chart>";
		//chartCode= createChart("../../resources/PictureCharts/Multiserie/MSColumn3D.swf", "", strXML, "productSales", 600, 300, false, false);                                  
		chartCode= createChartHTML("../../resources/PictureCharts/Multiserie/MSColumn3D.swf", "", strXML, "productSales", 800, 300, false);                                  
                                  
                                  %>
                                  <%=chartCode%>                                  
                                  
                                </td>
                            </tr>
                          <%
                          strXML="";
                          strCategories="";
                          strDataCurr ="";
                          strDataPrev="";                          
                          Junt="";
                          chartCode="";
                          }
                          
                          
                          %>                        
                            
                        </table>                         
                    </div>  
                </center>
                        
            </td>
            </tr>
               
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