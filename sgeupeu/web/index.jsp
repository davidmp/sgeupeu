<%@page import="sge.modelo.Filial"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Temporada"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.net.InetAddress;" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>sge.upeu</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/css/docs.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/resources/js/google-code-prettify/prettify.css" rel="stylesheet">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="<%=request.getContextPath()%>/resources/ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/ico/favicon.png">
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.8.3.js"></script>
    
    <style>
      @media only screen and (min-width: 180px) and (max-width: 750px) {  
                   #logotiposeria{ visibility: hidden; display:none }
        }
        @media only screen and (min-width: 180px) and (max-width: 750px) {  
                   #logomain{ visibility: hidden; display:none }
        }
   </style>
    
    <div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_ES/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
  
    <style type="text/css">

      /* Sticky footer styles
      -------------------------------------------------- */
      // 
     /* Wrapper for page content to push down footer */
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -60px;
      }

      /* Set the fixed height of the footer here */
      #push,
      #footer {
        height: 60px;
      }
      #footer {
        background-color: #f5f5f5;
      }

      /* Lastly, apply responsive CSS fixes as necessary */
      @media (max-width: 767px) {
        #footer {
          margin-left: -20px;
          margin-right: -20px;
          padding-left: 20px;
          padding-right: 20px;
        }
      }



    </style>
</head>
  <body data-spy="scroll" data-target=".bs-docs-sidebar">
      

    <!-- Navbar  Probando con SVN
    ================================================== -->
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="pull-left brand" href="">SGE - UPEU</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
                <li class="active"><a href="#inicio"><div class="icon-home icon-white"></div>&nbsp;Inicio</a></li>
<!--                <li><a href="#acercade"><div class="icon-map-marker icon-white"></div>&nbsp;Acerca De</a></li>
                <li><a href="#equipoman"><div class="icon-envelope icon-white"></div>&nbsp;Equipo de Trabajo</a></li>
                <li><a href="#equipoman"><div class="icon-camera icon-white"></div>&nbsp;Galería</a></li>-->
            </ul>
          <div class="pull-right">
            <ul class="nav">
              <li>
                  <a href="#"><div class="fb-like" data-href="https://www.facebook.com/edwin.edu.developer" data-width="The pixel width of the plugin" data-height="The pixel height of the plugin" data-colorscheme="light" data-layout="button_count" data-action="like" data-show-faces="true" data-send="false"></div></a>
              </li>  
              <li>
                  <a href="#myModal" role="button" data-toggle="modal"><div class="icon-user icon-white"></div>&nbsp;<strong>Iniciar Sesión</strong></a>
              </li>
              
            </ul>
              
          </div>    
          </div>
          
          
        </div>
      </div>
    </div>

<div class="jumbotron masthead">
    <div class="container">
        
        <h1><img src="<%=request.getContextPath()%>/resources/img/upeu.png" width="600" height="200" alt="upeu"/>
        </h1>
        
    <p>Sistema de Gestión Estratégica.</p>
    <%List<Temporada> ProxIdPer=(List<Temporada>) request.getSession().getAttribute("lista_temporada");
      if (ProxIdPer != null) {for (Temporada tem : ProxIdPer) {%>
      <h3><%=tem.getInicio().substring(0, 4) %>&nbsp;-&nbsp;<%=tem.getFin().substring(0, 4) %></h3> 
      <%}}%>
    <ul class="masthead-links">
      <li>
        Version 1.0
      </li>
    </ul>
    <ul class="masthead-links">
        DIRECCIÓN DE PLANIFICACIÓN Y DESARROLLO INSTITUCIONAL
    </ul>
  </div>
</div>

<div class="bs-docs-social">
  <div class="container">
    <ul class="bs-docs-social-buttons">
      
      <%List<Filial> listr=(List<Filial>) request.getSession().getAttribute("lista_filial");
      if (listr != null) {for (Filial f : listr) {%>
      <li><strong><span class="label label-info"><%=f.getDireccion()%></span></strong></li>
      <%}}%>
      
     
      </ul>
  </div>
</div>

    
<div class="container">
  <div class="marketing">
      
      
      <section id="inicio" style="text-align: left;">
      <div class="row-fluid">
          <div class="span9">
              <table width="100%" cellspacing="10%"><td width="45%"><h4 class="text-info"> Visión Institucional :: </h4>
          <p  align="justify"> “La Universidad Peruana Unión es una institución educativa de la Iglesia Adventista del Séptimo Día que forma integralmente profesionales e investigadores competentes y creativos, capaces de brindar un servicio cristiano a la Iglesia y sociedad para restaurar en el ser humano la imagen de Dios.”</p> 
           <td>&nbsp;</td>
          </td><td width="45%"><h4 class="text-info"> Misión Institucional :: </h4>
              <p  align="justify">“Ser una Universidad modelo, acreditada, reconocida en la Iglesia Adventista del Séptimo Día y la sociedad por la práctica de valores cristianos y su espíritu misionero.” </p>
                  </td></table>
             <br/>
             

                              
              </div>
          <div class="span3">
            <center>
                <br/><br/>
                <img src="resources/img/logo-upeu-color.png" width="166" height="148" alt="logo-upeu-color"/>
            </center>
        </div>
      </div>
      </section>    
      

  </div>

</div>



    <!-- Footer
    ================================================== -->
    
<form class="form-signin" method="POST" action="<%=request.getContextPath()%>/Usuario">
    
  
    
    
  <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4>Ingreso al Sistema</h4>
  </div>
    <div class="modal-body">
        
        <div class="row-fluid">
            <div class="span4" id="logotiposeria">
            <center>
                <img src="<%=request.getContextPath()%>/resources/img/logo-upeu-color.png" width="126" height="108" alt="logo-upeu-color"/>
            </center>
        </div>
            <div class="span6">
                <label><strong>Email address :</strong></label>  
                <input type="email" name="usuario_txt" class="input-block-level" placeholder="usuario@upeu.com"required="required">
                <label><strong>Password :</strong></label>
                <input type="password" name="password_txt" class="input-block-level" placeholder="**********" required="required">
            
        </div>
    </div>
        
  </div>
  <div class="modal-footer">
      <input type="hidden" name="opt" value="4" />
      <button type="submit"class="btn"><div class="icon-ok"></div>&nbsp;Ingresar</button>
      <button class="btn" data-dismiss="modal" aria-hidden="true"><div class="icon-remove"></div>&nbsp;Cancelar</button>
      
  </div>
</div>
</form>

 <div id="footer">
     <div class="container" style="text-align: center;">
        <p class="muted credit">Desarrollo de Software - Universidad Peruana Unión <a href="">upeu.edu.pe@gmail.com</a> and <a href="">Tecnologia Bootstrap</a>.</p>
      </div>
    </div>
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/widgetsEdwinCalsin.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-transition.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-alert.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-modal.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-dropdown.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-scrollspy.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-tab.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-popover.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-button.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-collapse.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-carousel.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-typeahead.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/bootstrap-affix.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/holder/holder.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/google-code-prettify/prettify.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/application.js"></script>

 
  </body>
</html>