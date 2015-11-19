<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link type="text/css" rel="stylesheet" href="/css/rep_style.css" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

    <script>
        var id;
        $(function() {
            $("#tabs-min").tabs();
        });
        function setCg(){
            document.getElementById("reporte").value = "cg";
        }

        function setGyo(){
            $("reporte").val("gyo");
            document.getElementById("reporte").value = "gyo";
        }

        function setSe(){
            document.getElementById("reporte").value = "se";
        }

        function setProd(){
        }

        function setDc(){
            document.getElementById("reporte").value = "dc";
            document.getElementById("dcRep").value = "4";
        }
    </script>

    <title></title>
</head>
<body>
<h2>Sala De Operaciones | Hospital Regional ISSS Santa Ana</h2>
<h1>REPORTES</h1>

<div id="tabs-min">
    <ul>
        <li id="cgli"onclick="setCg()"><a class="button" href="#cg">Cirujia General</a></li>
        <li id="gyoli"onclick="setGyo()"><a class="button" href="#gyo">Ginecología y Obtetrícia</a></li>
        <li id="seli"onclick="setSe()"><a class="button" href="#se">SubEspecialidades</a></li>
        <li id="dcli"onclick="setDc()"><a class="button" href="#dc">Detalle Cirujanos</a></li>
    </ul>
    <form id="formulario" class="Row" action="/reportes/generar" style="width: 30%">
        <div id="cg" >
            <div class="Row">
                <div class="Column">
                    <input type="radio" id="cg37" name="hora" value="1"><label for="cg37">7am a 3pm</label>
                </div>
                <div class="Column">
                    <input type="radio" id="cg73" name="hora" value="2"><label for="cg73">3pm a 7pm</label>
                </div>
                <div class="Column">
                    <input type="radio" id="cgas" name="hora" value="0"><label for="cgas">Asistenciales</label>
                </div>
            </div>
        </div>
        <div id="gyo">
            <div class="Row">
                <div class="Column">
                    <input type="radio" id="gyo37" name="hora" value="1"><label for="gyo37">7am a 3pm</label>
                </div>
                <div class="Column">
                    <input type="radio" id="gyo73" name="hora" value="2"><label for="gyo73">3pm a 7pm</label>
                </div>
                <div class="Column">
                    <input type="radio" id="gyoas" name="hora" value="0"><label for="gyoas">Asistenciales</label>
                </div>
            </div>
        </div>
        <div id="se">
            <div class="Row">
                <div class="Column">
                    <input type="radio" id="se37" name="hora" value="1"><label for="se37">7am a 3pm</label>
                </div>
                <div class="Column">
                    <input type="radio" id="se73" name="hora" value="2"><label for="se73">3pm a 7pm</label>
                </div>
                <div class="Column">
                    <input type="radio" id="seas" name="hora" value="0"><label for="seas">Asistenciales</label>
                </div>
            </div>
        </div>
        <div id="dc">
            <div class="Row">
                <div class="Column">
                    <input type="radio" id="dcRep" name="hora" value="4" hidden="hidden">
                    <%--<label for="cdRep" hidden="hidden">7am a 3pm</label>--%>
                </div>
            </div>
        </div>
        <div class="Row">
            <input type="radio" id="pdf" name="tipo" value="pdf"><label for="pdf">PDF</label>
            <input type="radio" id="excel" name="tipo" value="excel"><label for="excel">EXCEL</label>
            <input type="radio" id="html" name="tipo" value="html"><label for="html">HTML</label>
            <input type="number" id="anio" name="anio" min="2000" value="anio" style="width: 60px">
            <input id="reporte" name="reporte" hidden="hidden">
            <div class="Row" >
                <input id="enviar" type="submit" style="padding: 15px; margin: 15px" value="Generar"/>
            </div>
        </div>
    </form>
</div>


</body>
</html>