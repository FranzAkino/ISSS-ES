<%@ page import="com.persistencia.Cirujia" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.persistencia.CirujanoCirujia" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: zaldana
  Date: 04-28-15
  Time: 04:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link type="text/css" rel="stylesheet" href="/css/timeline_style.css" />
  <link type="text/css" rel="stylesheet" href="/css/buscador-style.css" />
  <title></title>
</head>
<div class="container">


  <h2 id="ss-subtitle">isssEQ | Datos Historicos de Cirujias Realizadas | Sala de Operaciones - Hospital Regional ISSS Santa Ana</h2>
  <div id="ss-links" class="ss-links">
    <a href="${pageContext.request.contextPath}/">Inicio</a>

  </div>

  <div id="buscador">
    <form>
      <select id="busquedalista">
        <option value="fecha">Fecha de Cirujia</option>
        <option value="Paciente">Nombre del Paciente</option>
        <option value="Numero">Num. de Afilacion</option>
        <option value="Calidad">Calidad de Asegurado</option>
        <option value="Intervencion">Clase de Intervencion</option>
        <option value="Region">Region</option>
        <option value="Riesgo">Riesgo</option>
        <option value="Diagnostico">Diagnostico</option>
        <option value="emergencia">Emergencia</option>
        <option value="Anestesista">Paciente</option>
        <option value="tipo_anestecia">Tipo de Anestecia</option>
        <option value="Quirofano">Sala de Operacion</option>
        <option value="cirujano">Cirujano</option>
        <option value="Ayudante">Ayudante</option>
      </select>
      <input type="text" id="busquedacampo" value="Buscar..." onfocus="if (this.value == 'Buscar...') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Buscar...';}" />
      <input type="button" id="busquedaboton" value="Ir" />
    </form>
  </div>

  <div id="ss-container" class="ss-container">

  <%

   List<Cirujia> lista1= (List<Cirujia>)request.getAttribute("listita");
    int num=lista1.size();

    Collections.reverse(lista1); //Para invertir el orden la lista primero el ultimo
    Iterator<Cirujia> it=lista1.iterator();
    Cirujia temp;
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-mm-yyyy");
    SimpleDateFormat Horas_cirujia = new SimpleDateFormat("hh:mm");
    int tamano;


  %>
       <%
      while(it.hasNext()){
        temp=it.next();
        tamano=0;
        %>
    <div class="ss-row ss-medium">
      <div class="ss-left">
        <h3 class="" ><%= temp.getIdCirujia()%></h3>

    </div>
      <div class="ss-right">
        <h3>
          <table>
            <tr>
              <td><span>Fecha de Cirujia: <%= DATE_FORMAT.format(temp.getFecha())%></span></td>
              <td><span>Hora de inicio: <%= Horas_cirujia.format(temp.getInicio())%> Hora de fin: <%= Horas_cirujia.format(temp.getFinalizacion())%></span></td>
              <%--<td><span>Inicio: <%= temp.getInicio()%> Fin: <%= temp.getFinalizacion()%></span></td>--%>
            </tr>
            <tr>
              <td> <span>Paciente: <%= temp.getFkPaciente().getNombres()%> <%= temp.getFkPaciente().getApellidos()%></span></td>
              <td> <span>Numero de Afiliacion (DUI): <%=temp.getFkPaciente().getIdPaciente()%></span></td>
            </tr>
          </table>
          <hr>
          <table >
            <tr>
              <td><span>Calidad Asegurado: <%=temp.getFkPaciente().getCalidadAsegurado()%></span>
              <td><span>Riesgo: <%=temp.getFkRiesgo().getNombre()%></span></td>
            </tr>
            <tr>
              <td><span>Clase de intervencion: <%=temp.getFkCie9().getNombre()%></span></td>
              <td><span>Diagnostico post-operatorio: <%= temp.getDiagnosticoPostoperatorio()%></span></td>
            </tr>
            <tr>
              <td><span>Region: <%= temp.getRegion()%></span></td>
              <% if (temp.getEmergencia() == 1)
              {%>
              <td><span>Emergencia: SI</span></td>
              <%
              } else {
              %><td><span>Emergencia: NO</span></td>
              <%
                }
              %>

            </tr>
          </table>
          <hr>
          <table >
            <tr>
            <td><span>Anestesista: <%= temp.getAnestesista()%></span>
              <td><span>Tipo de anestecia: <%= temp.getTipoAnestecia()%></span></td>
            </tr>
            <tr>
              <td><span>Instrumentista: <%= temp.getInstrumentista()%></span>
              <td><span>Circular: <%= temp.getCircular()%></span></td>
            </tr>
            <tr>
              <td><span>Sala de operaciones: <%=temp.getFkQuirofano().getIdQuirofano()%></span></td>
              <% if(temp.getRealizada()==1){%>
              <td><span>Estado: Realizada</span></td>
            <%}else{%>
              <td><span>Estado: Suspendidad - <%=temp.getFkSuspencion().getCausa()%></span></td>
              <%}%>

            </tr>

            <tr>
              <%
                while(tamano<temp.getCirujanoCirujiaList().size()){
                  if(tamano==0){ %>
              <td><span>Cirujano: <%=temp.getCirujanoCirujiaList().get(tamano).getFkidCirujano().getNombres()%> <%=temp.getCirujanoCirujiaList().get(tamano).getFkidCirujano().getApellidos()%></span></td>
            </tr>
            <%} else
            {%>
            <tr>
              <td><span>Ayudante: <%=temp.getCirujanoCirujiaList().get(tamano).getFkidCirujano().getNombres()%> <%=temp.getCirujanoCirujiaList().get(tamano).getFkidCirujano().getApellidos()%></span></td></td>
              <%}
                  tamano=tamano+1;
                }
              %>

            </tr>
          </table>
        </h3>
      </div>

    <%
      }
    %>



    <div class="ss-row ss-medium">
      <div class="ss-left">
        <h3>10</h3>
      </div>
      <div class="ss-right">
        <h3>
          <table>
            <tr>
              <td><span>Noviembre 28, 2011 12:30 pm</span></td>
              <td></td>
            </tr>
            <tr>
              <td> <span>Nombre: Juan Perez</span></td>
              <td> <span>Numero de Afiliacion (DUI): 040501000-5</span></td>
            </tr>
          </table>
          <hr>
          <table >
            <tr>
              <td><span>Calidad Asegurado: Beneficiado</span>
              <td><span>Riesgo: Enfermedad Comun</span></td>
            </tr>
            <tr>
              <td><span>Clase de intervencion: Apendicectomia</span></td>
              <td><span>Diagnostico post-operatorio: Apendicitis Aguda</span></td>
            </tr>
            <tr>
              <td><span>Region: Abdominal</span></td>
              <td><span>Emergencia: NO</span></td></td>
            </tr>
          </table>
          <hr>
          <table >
            <tr>
              <td><span>Anestesista: Sra de Gonzales</span>
              <td><span>Tipo de anestecia: General</span></td>
            </tr>
            <tr>
              <td><span>Sala de operaciones: 4</span></td>
              <td><span>Cirujano: Dr. Anaya</span></td>
            </tr>
            <tr>
              <td><span>Ayudante: Dra. Rodriguez</span></td></td>
              <td></td>
            </tr>
          </table>
        </h3>
      </div>
    </div>
   </div>
</div>
</div>
<script type="text/javascript" src="/css/http_ajax.googleapis.com_ajax_libs_jquery_1.7.1_jquery.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript">
  $(function() {

    var $sidescroll	= (function() {

      // the row elements
      var $rows			= $('#ss-container > div.ss-row'),
      // we will cache the inviewport rows and the outside viewport rows
              $rowsViewport, $rowsOutViewport,
      // navigation menu links
              $links			= $('#ss-links > a'),
      // the window element
              $win			= $(window),
      // we will store the window sizes here
              winSize			= {},
      // used in the scroll setTimeout function
              anim			= false,
      // page scroll speed
              scollPageSpeed	= 2000 ,
      // page scroll easing
              scollPageEasing = 'easeInOutExpo',
      // perspective?
              hasPerspective	= false,

              perspective		= hasPerspective && Modernizr.csstransforms3d,
      // initialize function
              init			= function() {

                // get window sizes
                getWinSize();
                // initialize events
                initEvents();
                // define the inviewport selector
                defineViewport();
                // gets the elements that match the previous selector
                setViewportRows();
                // if perspective add css
                if( perspective ) {
                  $rows.css({
                    '-webkit-perspective'			: 600,
                    '-webkit-perspective-origin'	: '50% 0%'
                  });
                }
                // show the pointers for the inviewport rows
                $rowsViewport.find('a.ss-circle').addClass('ss-circle-deco');
                // set positions for each row
                placeRows();

              },
      // defines a selector that gathers the row elems that are initially visible.
      // the element is visible if its top is less than the window's height.
      // these elements will not be affected when scrolling the page.
              defineViewport	= function() {

                $.extend( $.expr[':'], {

                  inviewport	: function ( el ) {
                    if ( $(el).offset().top < winSize.height ) {
                      return true;
                    }
                    return false;
                  }

                });

              },
      // checks which rows are initially visible
              setViewportRows	= function() {

                $rowsViewport 		= $rows.filter(':inviewport');
                $rowsOutViewport	= $rows.not( $rowsViewport )

              },
      // get window sizes
              getWinSize		= function() {

                winSize.width	= $win.width();
                winSize.height	= $win.height();

              },
      // initialize some events
              initEvents		= function() {

                // navigation menu links.
                // scroll to the respective section.
                $links.on( 'click.Scrolling', function( event ) {

                  // scroll to the element that has id = menu's href
                  $('html, body').stop().animate({
                    scrollTop: $( $(this).attr('href') ).offset().top
                  }, scollPageSpeed, scollPageEasing );

                  return false;

                });

                $(window).on({
                  // on window resize we need to redefine which rows are initially visible (this ones we will not animate).
                  'resize.Scrolling' : function( event ) {

                    // get the window sizes again
                    getWinSize();
                    // redefine which rows are initially visible (:inviewport)
                    setViewportRows();
                    // remove pointers for every row
                    $rows.find('a.ss-circle').removeClass('ss-circle-deco');
                    // show inviewport rows and respective pointers
                    $rowsViewport.each( function() {

                      $(this).find('div.ss-left')
                              .css({ left   : '0%' })
                              .end()
                              .find('div.ss-right')
                              .css({ right  : '0%' })
                              .end()
                              .find('a.ss-circle')
                              .addClass('ss-circle-deco');

                    });

                  },
                  // when scrolling the page change the position of each row
                  'scroll.Scrolling' : function( event ) {

                    // set a timeout to avoid that the
                    // placeRows function gets called on every scroll trigger
                    if( anim ) return false;
                    anim = true;
                    setTimeout( function() {

                      placeRows();
                      anim = false;

                    }, 10 );

                  }
                });

              },
      // sets the position of the rows (left and right row elements).
      // Both of these elements will start with -50% for the left/right (not visible)
      // and this value should be 0% (final position) when the element is on the
      // center of the window.
              placeRows		= function() {

                // how much we scrolled so far
                var winscroll	= $win.scrollTop(),
                // the y value for the center of the screen
                        winCenter	= winSize.height / 2 + winscroll;

                // for every row that is not inviewport
                $rowsOutViewport.each( function(i) {

                  var $row	= $(this),
                  // the left side element
                          $rowL	= $row.find('div.ss-left'),
                  // the right side element
                          $rowR	= $row.find('div.ss-right'),
                  // top value
                          rowT	= $row.offset().top;

                  // hide the row if it is under the viewport
                  if( rowT > winSize.height + winscroll ) {

                    if( perspective ) {

                      $rowL.css({
                        '-webkit-transform'	: 'translate3d(-75%, 0, 0) rotateY(-90deg) translate3d(-75%, 0, 0)',
                        'opacity'			: 0
                      });
                      $rowR.css({
                        '-webkit-transform'	: 'translate3d(75%, 0, 0) rotateY(90deg) translate3d(75%, 0, 0)',
                        'opacity'			: 0
                      });

                    }
                    else {

                      $rowL.css({ left 		: '-50%' });
                      $rowR.css({ right 		: '-50%' });

                    }

                  }
                  // if not, the row should become visible (0% of left/right) as it gets closer to the center of the screen.
                  else {

                    // row's height
                    var rowH	= $row.height(),
                    // the value on each scrolling step will be proporcional to the distance from the center of the screen to its height
                            factor 	= ( ( ( rowT + rowH / 2 ) - winCenter ) / ( winSize.height / 2 + rowH / 2 ) ),
                    // value for the left / right of each side of the row.
                    // 0% is the limit
                            val		= Math.max( factor * 50, 0 );

                    if( val <= 0 ) {

                      // when 0% is reached show the pointer for that row
                      if( !$row.data('pointer') ) {

                        $row.data( 'pointer', true );
                        $row.find('.ss-circle').addClass('ss-circle-deco');

                      }

                    }
                    else {

                      // the pointer should not be shown
                      if( $row.data('pointer') ) {

                        $row.data( 'pointer', false );
                        $row.find('.ss-circle').removeClass('ss-circle-deco');

                      }

                    }

                    // set calculated values
                    if( perspective ) {

                      var	t		= Math.max( factor * 75, 0 ),
                              r		= Math.max( factor * 90, 0 ),
                              o		= Math.min( Math.abs( factor - 1 ), 1 );

                      $rowL.css({
                        '-webkit-transform'	: 'translate3d(-' + t + '%, 0, 0) rotateY(-' + r + 'deg) translate3d(-' + t + '%, 0, 0)',
                        'opacity'			: o
                      });
                      $rowR.css({
                        '-webkit-transform'	: 'translate3d(' + t + '%, 0, 0) rotateY(' + r + 'deg) translate3d(' + t + '%, 0, 0)',
                        'opacity'			: o
                      });

                    }
                    else {

                      $rowL.css({ left 	: - val + '%' });
                      $rowR.css({ right 	: - val + '%' });

                    }

                  }

                });

              };

      return { init : init };

    })();

    $sidescroll.init();

  });
</script>

</html>
