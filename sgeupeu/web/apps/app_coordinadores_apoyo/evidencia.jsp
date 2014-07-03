<%-- 
    Document   : evidencia
    Created on : 23/09/2013, 01:20:54 AM
    Author     : oscdmdz
--%>
<%@page import="sge.directori.FileDirectori"%>
<%@page import="sge.modelo.Ejeestrategico"%>
<%@page import="sge.modelo.Usuario"%>
<%@page import="sge.modelo.Evidencia"%>
<%@page import="sge.modelo.Facultad"%>
<%@page import="sge.modelo.Filial"%>
<%@page import="sge.modelo.Variables"%>
<%@page import="sge.modelo.Eap"%>
<%@page import="sge.modelo.Periodometa"%>
<%@page import="sge.modelo.Periodo"%>
<%@page import="sge.modelo.Meta"%>
<%@page import="sge.modelo.Indicador"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Actividad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        
              
             <%
                 Usuario w = null;
                w = (Usuario) request.getSession().getAttribute("listaPerfilUsuario");
                List<Evidencia> listaEvidencia=null;
                listaEvidencia=(List<Evidencia>)request.getSession().getAttribute("listaEvidencia"); 
                Indicador  in =(Indicador)request.getSession().getAttribute("estrategiaIndicador"); 
                Periodometa  p =(Periodometa)request.getSession().getAttribute("periodoMeta"); 
                Facultad faU=(Facultad)request.getSession().getAttribute("facultadUsuario");
                Filial fiU=(Filial)request.getSession().getAttribute("filialUsuario");    
                Eap eU=(Eap)request.getSession().getAttribute("eapUsuario"); 
                Variables vr=(Variables)request.getSession().getAttribute("variable");
                Ejeestrategico eje=(Ejeestrategico)request.getSession().getAttribute("eje");                   

                int i=1;
                Indicador name=(Indicador)request.getSession().getAttribute("indicador");
       FileDirectori fd=new FileDirectori();
       String carpeta=fd.directorioFilial(fiU.getIdfilial());
     %> 
     


   
     
     
     
    </head>
     <div id="loading">  
           <script type="text/javascript">

function formindicador(numero) 
{
    var nro_indicador = document.getElementById("nro_indicador");
    nro_indicador.value = numero;
}
function validarExtencion(){
    var data=($("#fichero").val()).split(".");
    var tama=data.length;
    var validar=false;
    if(data[tama-1].toLowerCase()=="docx" || data[tama-1].toLowerCase()=="doc" || data[tama-1].toLowerCase()=="pdf" || data[tama-1].toLowerCase()=="zip" || data[tama-1].toLowerCase()=="rar")  
    {
      $('#loadingx').html('<img src="../../resources/gif/loading.gif"> Subiendo...');  
      validar=true; }else{
      alert("Solo las extenciones mencionadas es posible subir al sistema!");
      validar=false;  
    }
    return validar;
}
 </script>
         
    <body>
        <div class="container-fluid">
	<div class="row-fluid">
           
		<div class="span12">
                                   
                    
            <center><h4>Lista de Evidencias</h4></center>
               
                    <table class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <td colspan="2"><i class="icon-fullscreen"></i>&nbsp;&nbsp;<strong>Estratégia :</strong>&nbsp; <%=in.getEstrategia()%></td>                                        
                            </tr>
                            <tr >                       
                     <td rowspan="2"><center>
                    <p <%if(in.getSemaforo()==0){%>class="rojo" <%}if(in.getSemaforo()==1){%>class="amarillo"<%}if(in.getSemaforo()==2){%>class="verde"<%}if(in.getSemaforo()==3){%>class="azul"<%}%>></p>                      
                        </center></td>
                        <td><i class="icon-leaf"></i>&nbsp;&nbsp;<strong>Indicador &nbsp; # <%=in.getNro()%> :</strong> &nbsp;<%=in.getNombre()%></td>
                            </tr>       
                <tr>
                    <td><i class="icon-flag"></i>&nbsp;&nbsp;<strong>Periodo : </strong>&nbsp;<%=p.getPeriodo()%> 
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-star"></i>&nbsp;&nbsp;<strong>Meta : </strong>&nbsp; <%=in.getMeta()%> <%if(in.getIdtipometa()==2){%>%<%}%>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="icon-star-empty"></i>&nbsp;&nbsp;<strong>Avance : </strong>&nbsp; <%=in.getTotalavance()%> <%if(in.getIdtipometa()==2){%>%<%}%>
                    </td>
                </tr>
     

                            
                            
                        </thead>
                        
                    </table>
                    
                    


                    
                    

                    
        <form name="emailForm" class="emailForm" method="POST" onsubmit="return validarExtencion()"  action="<%=request.getContextPath()%>/IndicadorApoyo?opt=5&idtemporadaejeestrategico=<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>&idperiodometa=<%=p.getIdperiodometa()%>&nro_indicador_4=<%=vr.getNro()%>&idmeta<%=vr.getNro()%>=<%=in.getIdmeta()%>&idfilial=<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>&ideapfacultad=<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>&idfilialfacultad=<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>&idusuario=<%if(w!=null){%><%=w.getIdusuario()%><%}else{%>0<%}%>&idestrategiaindicador<%=vr.getNro()%>=<%=in.getIdestrategiaindicador()%>&idavancevalida<%=vr.getNro()%>=<%=in.getIdavancevalida() %>" enctype="multipart/form-data">

        <center>
        35 MB (Maximo) &DoubleRightArrow;  Formatos: DOCX, DOC, PDF, ZIP, RAR             
        <div class="fileupload fileupload-new" data-provides="fileupload">
        <div class="input-append">
        <div class="uneditable-input span4"><i class="icon-file fileupload-exists"></i> 
         <span class="fileupload-preview"></span></div>
         <span class="btn btn-file"><span class="fileupload-new">Buscar</span>
             <span class="fileupload-exists">Cambiar</span><input type="file" name="fichero" id="fichero" /></span>
         <a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Eliminar  </a> 
        </div>
        </div>                                   
        </center>   
        <center>
         <input type="submit" value="Subir" class="btn  btn-success"/>   
        </center>  

        </form>  
        <center><div id="loadingx"></div></center>
    
 <table class="table table-bordered table-hover"    >
     <thead>
   <tr>
       <th rowspan="2" width="">#</th>
       <th rowspan="2" width="">Evidencia</th>
       <th rowspan="2" width="">Fecha de registro</th>
       <th rowspan="2" width="">Opciones</th>
   </tr>
 
   </thead>
   <tbody>
           
       <% if(listaEvidencia!=null){for(Evidencia me: listaEvidencia){%> 
      <tr>
       <td  width=""><%=i++%></td>
       <td  width=""><%=me.getEvidencia()%></td>
       <td  width=""><%=me.getFecha_reg()%></td>
       <td  width="">
            <p align="center"> 
                      

   <form class="eliminar_1" name="eliminar_1" action="<%=request.getContextPath()%>/IndicadorApoyo" method="POST">  

    <input type="hidden"  name="nro_evidencia" id="nro_indicador" />
    <input type="hidden"  name="nro_indicador_4" value="<%=vr.getNro()%>" />
    <input type="hidden"  name="idperiodometa" value="<%=p.getIdperiodometa()%>" />
    <input type="hidden"  name="idmeta<%=vr.getNro()%>" value="<%=in.getIdmeta()%>" />
    <input type="hidden"  name="idavancevalida<%=vr.getNro()%>" value="<%=in.getIdavancevalida() %>" />
    <input type="hidden"  name="idestrategiaindicador<%=vr.getNro()%>" value="<%=in.getIdestrategiaindicador()%>" />
    <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" />
    <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" />
    <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" />        
    <input type="hidden"  name="idevidencia<%=me.getIdevidencia()%>" value="<%=me.getIdevidencia()%>" />     
    <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>"/> 
    <input type="hidden"  name="valor" value="<%if(name!=null){%><%=name.getNombre()%><%}%>" >
    <input type="hidden"  name="opt" value="29" > 

    <center> 

    <a href="<%= request.getContextPath()%>/public/evidencias/<%=me.getUrl()%>" role="button" class="btn btn-success" data-toggle="modal" target="_blank" rel="tooltip" title="<%=me.getEvidencia()%>"><i class="icon-download-alt icon-white"></i></a>      
    <a href="ftp://sger1:lectura@192.168.13.38/<%=carpeta%><%=me.getUrl()%>" role="button" class="btn btn-success" data-toggle="modal" target="_blank" rel="tooltip" title="<%=me.getEvidencia()%>"><i class="icon-download-alt icon-white"></i></a>                                    
    <button class="btn btn-danger" onmouseover="formindicador(<%=me.getIdevidencia()%>)" type="submit"><i class="icon-remove icon-white"></i></button>                       
    </center>

    </form>  
            
            </p>
            
       
       </td>
   </tr>
   <%}}%>     
   </tbody>

</table>
      
                    
                    
			<div class="pagination pagination-right">
				<ul>
					<li>
						<a href="#">Prev</a>
					</li>
					<li>
						<a href="#">1</a>
					</li>
					<li>
						<a href="#">2</a>
					</li>
					<li>
						<a href="#">3</a>
					</li>
					<li>
						<a href="#">4</a>
					</li>
					<li>
						<a href="#">5</a>
					</li>
					<li>
						<a href="#">Next</a>
					</li>
				</ul>
			</div>
 
    
   <form id="atras_1" name="atras_1" action="<%=request.getContextPath()%>/IndicadorApoyo" method="POST"> 
       
   
             <input type="hidden"  name="idfilialfacultad" value="<%if(faU!=null){%><%=faU.getIdfilialfacultad()%><%}else{%>0<%}%>" >
             <input type="hidden"  name="idfilial" value="<%if(fiU!=null){%><%=fiU.getIdfilial()%><%}else{%>0<%}%>" >        
            <input type="hidden"  name="idperiodometa" value="<%=p.getIdperiodometa()%>" >     
            <input type="hidden"  name="ideapfacultad" value="<%if(eU!=null){%><%=eU.getIdeapfacultad()%><%}else{%>0<%}%>" >
            <input type="hidden"  name="idtemporadaejeestrategico" value="<%if(eje!=null){%><%=eje.getIdtemporadaejeestrategico()%><%}else{%>0<%}%>">  
            <%if(eje!=null){%>
            <input type="hidden"  name="opt" value="24" > 
            <%}else{%>
            <input type="hidden"  name="opt" value="27" > 
            <%}%> 
                <input type="hidden"  name="valor" value="<%if(name!=null){%><%=name.getNombre()%><%}%>" >
            <p align="right">
            <button class="btn btn-mini btn-primary" type="submit">Atras</button>
            </p>  
   </form>
  

		</div>
	</div>
</div>
    </body>
     <script type="text/javascript">

   function evidencia(numero) 
{
    var evidencia = document.getElementById("evidencia");
    envidencia.value = numero;
}
   
        var form1 = $('#atras_1');
        form1.submit(function () {
        $.ajax({
        type: form1.attr('method'),
        url: form1.attr('action'),
        data: form1.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("atras_1").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
        return false;
        });
        
        
        
        var form12 = $('.eliminar_1');
        form12.submit(function () {
        $.ajax({
        type: form12.attr('method'),
        url: form12.attr('action'),
        data: form12.serialize(),
        success: function (data) {
        $('#waza').show();
        $('#waza').html(data);
        document.getElementById("eliminar_1").reset();
        document.getElementById("waza").reset(); 
        }
        }); 
 return false;
        });
        
        
        
        
               function getFrameByName(name) {
  for (var i = 0; i < frames.length; i++)
    if (frames[i].name == name)
      return frames[i];
 
  return null;
}
 
/* Same as the one defined in OpenJS */
function uploadDone(name) {
   var frame = getFrameByName(name);
   if (frame) {
     ret = frame.document.getElementsByTagName("body")[0].innerHTML;
 
     /* If we got JSON, try to inspect it and display the result */
     if (ret.length) {
       /* Convert from JSON to Javascript object */
  
       var json = eval("("+ret+")");
 
     }
  }
}

       $(function () {
        $("[rel='tooltip']").tooltip();
    });     
        
       
       
       

        
        
</script>


<script type="text/javascript">
	$(document).ready(function(){
		$('.emailForm').ajaxForm({
			success: function(data, statusText, xhr, form) {
                              $('#loading').show();
                              $('#loading').html(data);
                        document.getElementById("emailForm").reset();
                        document.getElementById("waza").reset();
			}
                        
		});
		 return false;
	});
	
</script>        
    </div>                 
</html>

