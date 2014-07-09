<%@page import="java.util.List"%>
<%@page import="sge.modelo.Temporada"%>
<script type="text/javascript">
    $(function() {
        $('table').footable();
    });
    $(document).ready(function() {
        $("tbody a").click(function() {
            $(".tab-content").empty().append();
            $("tbody a").removeClass('current');
            $(this).addClass('current');
            $.ajax({url: this.href, success: function(html) {
                    $(".tab-content").empty().append(html);
                }
            });
            return false;
        });
    });

// add 
    $(document).ready(function() {
        $("#topmenu a").click(function() {
            $(".tab-content").empty().append();
            $("#topmenu a").removeClass('current');
            $(this).addClass('current');
            $.ajax({url: this.href, success: function(html) {
                    $(".tab-content").empty().append(html);
                }
            });
            return false;
        });
    });
</script>

<script>
    function showedwinajax(id) {
        $("#reportarPeridoMeta").html("<div><center><img src='<%=request.getContextPath()%>/resources/img/loading.gif' width='16' height='11' alt='loading'/></center></div>");
        $.ajax({
            url: '<%=request.getContextPath()%>/GestionEstrategico?opt=22',
            type: 'POST',
            async: true,
            data: 'edwintemporada='+id,
            success: function(data) {
                $('#reportarPeridoMeta').fadeIn(1000).html(data);
//alert("parametro de ingreso ---> "+id);
            }

        });
    }
</script>

<%
    String orgpre = (String) session.getAttribute("name_pre_temporada");
    String datapre = (String) session.getAttribute("data_pre_temporada");
%>

<div class="row-fluid">
    <div class="span12">
        <select name="" onchange="showedwinajax(this.value);" class="pull-left">
            <option value=""></option>
            <% List<Temporada> lista = (List<Temporada>) request.getSession().getAttribute("listarTemporada");
                if (lista != null) {
                    for (Temporada es : lista) {%>
            <option value="<%=es.getIdtemporada()%>"><%=es.getInicio()%>&nbsp;-&nbsp;<%=es.getFin()%></option>
            <%}
                }%>
        </select>

        <blockquote class="pull-right">
            <p class="text-info">
                <%=orgpre%>
            </p> 
            <small><%=datapre%></small>
        </blockquote>
    </div>
</div>
<div class="row-fluid">
    <div class="span12" id="reportarPeridoMeta">
    </div>
</div>