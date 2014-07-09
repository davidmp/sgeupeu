<%@page import="sge.modelo.Periodometa"%>
<%@page import="java.util.List"%>
<%@page import="sge.modelo.Temporada"%>
<script type="text/javascript">
    $(function() {
        $('table').footable();
    });
    var form = $('#addPeriodoMeta');
    form.submit(function() {
        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: form.serialize(),
            success: function(data) {
                $('#reportarPeridoMeta').show();
                $('#reportarPeridoMeta').html(data);
                document.getElementById("addPeriodoMeta").reset();
                document.getElementById("reportarPeridoMeta").reset();
            }
        });
        return false;
    });
    $(document).ready(function() {
        $("tbody a").click(function() {
            $("#reportarPeridoMeta").empty().append();
            $("tbody a").removeClass('current');
            $(this).addClass('current');
            $("#reportarPeridoMeta").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");
            $.ajax({url: this.href, success: function(html) {
                    $("#reportarPeridoMeta").empty().append(html);
                }
            });
            return false;
        });
    });
    $(document).ready(function() {
        $("#topmenu a").click(function() {
            $("#reportarPeridoMeta").empty().append();
            $("#topmenu a").removeClass('current');
            $(this).addClass('current');
            $.ajax({url: this.href, success: function(html) {
                    $("#reportarPeridoMeta").empty().append(html);
                }
            });
            return false;
        });
    });
</script>

<script>

    function valorperiodo(periodo) {
        $('#text1').val(periodo);
    }

    function valornro(id) {
        $('#text2').val(id);
    }

    function sumar() {
        var total;
        total = text1.value + " - " + text2.value;
        text3.value = total;
    }

    function alertBootstrap(parametroedwin) {
        var btnconfirm = alert("Desea Eliminar " + parametroedwin + " porque y no es Reversible");
        btnconfirm.Error("Error" + parametroedwin);
    }

</script>


</head>

<%
    String var = (String) session.getAttribute("edwintemporadasession");
%>

<form method="POST" action="<%=request.getContextPath()%>/GestionEstrategico" id="addPeriodoMeta" name="addPeriodoMeta">
    <legend> Registro de Periodos Segun la Temporada Seleccionada</legend>
    <div class="row-fluid">
        <div class="span3">
            <select name="periodo" class="span4">
                <option></option>
                <%
                    int a = 0;
                    List<Temporada> tem = (List<Temporada>) request.getSession().getAttribute("Listar_Rango_Temporada");
                    if (tem != null) {
                        for (Temporada v : tem) {
                            int aa = Integer.parseInt(v.getInicio());
                            int bb = Integer.parseInt(v.getFin());
                            for (a = aa; a <= bb; a++) {
                %>
                <option value="<%out.println(a);%>" ><%out.println(a);%></option>
                <%}
                            }
                        }%>
            </select>
            <input type="text" name="" value="" class="text2 hidden-desktop" id="text2" />         
            <input type="text" name="estado" value="0" class="hidden-desktop"/>
            F. Inicio:
            <input type="date" name="fechainicio" value="" class="span5"/> <!--AÑADIDO PRARA REGISTRAR EN TABLA PERIODO-->               
        </div>
        <div class="span3">                
            F. Fin:
            <input type="date" name="fechafin" value="" class="span5"/><!--AÑADIDO PRARA REGISTRAR EN TABLA PERIODO-->                 
        </div>                
        <div class="span3">
            <div id="topmenu">
                <input type="hidden" name="idtemporada" value="<%=var%>"/>
                <input type="hidden" name="opt" value="25"/>
                <button type="submit" class="btn"><div class="icon-ok"></div>&nbsp;guardar</button>
                <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=67" class="btn"><div class="icon-list-alt"></div>&nbsp;Listar</a>
            </div>
        </div>

        <div class="span3">
            <input type="search" name="" value="" id="filterperiodo" placeholder="Buscar .." class="pull-right"/>
        </div>

    </div>    
</form>



<div class="row-fluid">
    <div class="span12">
        <table data-filter="#filterperiodo" class="table table-bordered table-condensed footable table-hover" data-page-size="5" >

            <thead>


                <tr>
                    <th data-class="expand" data-sort-initial="true" style="width: 3%;">

                    </th> 
                    <th>
                        Periodo
                    </th>
                    <th data-hide="phone">
                        estado
                    </th>
                    <th data-hide="phone">
                        opc
                    </th>
                </tr>
            </thead>
            <tbody>
                <%
                    int x = 0;
                    List<Periodometa> lista = (List<Periodometa>) request.getSession().getAttribute("listar_periodo_meta_temporada");
                    if (lista != null) {
                        for (Periodometa permeta : lista) {
                %>
                <tr class="<% if (x % 2 == 1) {
                        out.println("success");
                    }%>">
                    <td><%=++x%></td>
                    <td><%=permeta.getPeriodo()%></td>
                    <td style="width: 10%;text-align: center;"><%
                        if (permeta.getEstado() == 1) {
                        %> <span class="label label-success">Activado</span>  <%
            } else if (permeta.getEstado() == 0) {
                        %> <span class="label label-inverse">Desactivo</span><%
                } %></td>
                    <td style="width: 15%;text-align: center;">
                        <%
                            if (permeta.getEstado() == 1) {
                        %> <a class="btn btn-small text-info" href="<%=request.getContextPath()%>/GestionEstrategico?opt=24&&idperiodometa=<%=permeta.getIdperiodometa()%>"><div class="btn-icon-only icon-ban-circle"></div></a> <%
                  } else if (permeta.getEstado() == 0) {
                            %> <a class="btn btn-small text-warning" href="<%=request.getContextPath()%>/GestionEstrategico?opt=23&&idperiodometa=<%=permeta.getIdperiodometa()%>"><div class="btn-icon-only icon-ok"></div></a> <%
                }%>
                        <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=66&idperiodometa=<%=permeta.getIdperiodometa()%>" class="btn btn-small"><div class="icon-edit"></div></a>           
                        <a href="<%=request.getContextPath()%>/GestionEstrategico?opt=32&idPeriodoMeta=<%=permeta.getIdperiodometa()%>" class="btn btn-small"><div class="icon-remove"></div></a>           

                    </td>
                </tr>
                <% }
                 }%>
            </tbody>
            <tfoot class="footable-pagination pagination pagination-centered pagination-small">
                <tr>
                    <td colspan="8" style="text-align: center;"><ul id="pagination" class="footable-nav" /></td>
                </tr>
            </tfoot>
        </table>
    </div>

</div>
