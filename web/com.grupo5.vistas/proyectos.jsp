<%-- 
    Document   : proyectos
    Created on : 5 sep. 2023, 09:24:42
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyectos</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

        <!-- CDN para elaborar diagramas de gantt -->
        <link rel="stylesheet" href="https://cdn.dhtmlx.com/gantt/7.1/dhtmlxgantt.css">
        <script src="https://cdn.dhtmlx.com/gantt/7.1/dhtmlxgantt.js"></script>

        <link href="com.grupo5.utilidades/stylesProyecto.css" rel="stylesheet" type="text/css"/>

        <!-- Agrega DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
        <link href="com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>

        <!-- agregar sweetAlert2 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
         <c:set var="proyecto" value="${requestScope.proyecto}" />
        <div class="row">
            <h1>${proyecto.proyecto}</h1>
        </div>
        <!--Nav para seleccionar la vista-->
        <div class="row">
            <ul class="nav nav-underline justify-content-around" id="list-tab" role="tablist">
                <li class="nav-link">
                    <a href="#tablero" class="nav-link active text-secondary icon-link" id="talero-list" role="tab"  data-bs-toggle="list" aria-controls="tablero"><i class="fa-solid fa-table-columns"></i>Tablero</a>
                </li>
                <li class="nav-link">
                    <a href="#lista" class="nav-link text-secondary icon-link" id="lista-list" role="tab"  data-bs-toggle="list" aria-controls="lista"><i class="fa-solid fa-table"></i>Lista</a>
                </li>
                <li class="nav-link">
                    <a href="#gantt" class="nav-link text-secondary icon-link" id="gantt-list" role="tab"  data-bs-toggle="list" aria-controls="gantt"><i class="fa-solid fa-chart-gantt"></i>Gantt</a>
                </li>
            </ul>
            <hr>
        </div>
        <!--Contenido de las vistas-->
        <div class="row">
            <div class="tab-content" id="nav-tabContent">

                <!--Vista de tablero-->
                <div class="tab-pane fade show active" id="tablero" role="tabpanel" aria-labelledby="tablero-list">
                    <div id="board">
                        <c:set var="estadosProcesados" value="" />
                        <c:forEach var="estado" items="${proyecto.estados}">
                            <c:if test="${not fn:contains(estadosProcesados, estado.estado)}">
                                <c:set var="estadosProcesados" value="${estadosProcesados},${estado.estado}" />
                            </c:if>
                            <div class="card ms-3 me-3 mt-4 swim-lane">
                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h3 class="card-title">${estado.estado}</h3>
                                    <div class="dropdown no-arrow">
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                           data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                             aria-bs-labelledby="dropdownMenuLink">
                                            <div class="dropdown-header">Acciones:</div>
                                            <button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#agregarTareasModal">Agregar tarea</button>
                                            <button class="dropdown-item editarEstado">Editar estado</button>
                                            <div class="dropdown-divider"></div>
                                            <button class="dropdown-item btn-eliminar-estado">Eliminar</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body justify-content-center swim-lane-content" id="${estado.idEstado}">
                                    <c:forEach var="tarea" items="${estado.tareas}">
                                        <c:if test="${not empty tarea.tarea}">
                                            <div class="card shadow-sm mb-2 task" draggable="true" estado= "${estado.idEstado}">
                                                <div class="card-body justify-content-center task-content">
                                                    ${tarea.tarea}
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="card-footer">
                                    <button type="button"class="btn-agregarTarea text-secondary" data-bs-toggle="modal" data-bs-target="#agregarTareasModal">Agregar Tarea <i class="fa-solid fa-plus"></i></button>
                                </div>
                            </div>
                        </c:forEach>

                        <button class="ms-3 me-3 mt-4 justify-content-center align-items-center no-sortable text-secondary" id="btn-agregarEstado">Agregar Estado <i class="fa-solid fa-plus"></i></button>
                    </div>
                </div>

                <!--Vista de lista-->
                <div class="tab-pane fade" id="lista" role="tabpanel" aria-labelledby="lista-list">
                    <div class="container-lg">
                        <div class="card">
                            <div class="card-body">
                                <table class="table" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>Realizada</th>
                                            <th>Tarea</th>
                                            <th>Finalizacion</th>
                                            <th>Usuario</th>
                                            <th>Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="estado" items="${proyecto.estados}">
                                            <c:forEach var="tarea" items="${estado.tareas}">
                                                <tr>
                                                    <td><input type="checkbox" selected="${tarea.realizada}"></td>
                                                    <td>${tarea.tarea}</td>
                                                    <td>${tarea.fechaFin}</td>
                                                    <td>nombreu</td>
                                                    <td class="${estado.color}">${estado.estado}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Vista de Gantt-->
                <div class="tab-pane fade" id="gantt" role="tabpanel" aria-labelledby="gantt-list">
                    <div id="gantt_container" style="width: 100%; height: 400px;"></div>
                </div>
            </div>
        </div>

        <!-- Modales -->

        <!-- Modal para guardar tareas -->
        <div class="modal fade" id="agregarTareasModal" tabindex="-1" aria-labelledby="agregarTareasModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="agregarTareasModalLabel">Agregar tareas</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formAgregarTarea" method="post" action="TareasControlador?accion=insertar">
                            <input type="reset" id="resetForm-Agregar" hidden>
                            <div class="mb-3">
                                <label for="tarea" class="form-label">Tarea</label>
                                <input type="text" class="form-control" name="tarea" id="tarea" required>
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descrpcion</label>
                                <textarea class="form-control" name="descripcion" id="descripcion" rows="3"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="estado" class="form-label">Estado</label>
                                <select class="form-select" name="estado" id="estado">
                                    <option value="0">Selecciona un estado</option>
                                    <c:forEach var="estado" items="${proyecto.estados}">
                                        <option value="${estado.idEstado}">${estado.estado}</option>
                                    </c:forEach>
                                </select>
                                <span class="text-danger" id="validacion-estado"></span>
                            </div>
                            <div class="mb-3">
                                <label for="fechaInicio" class="form-label">Fecha de inicio</label>
                                <input type="date" class="form-control" name="fechaInicio" id="fechaInicio" required>
                            </div>
                            <div class="mb-3">
                                <label for="fechaFin" class="form-label">Fecha de finalizacion</label>
                                <input type="date" class="form-control" name="fechaFin" id="fechaFin" required>
                            </div>
                            <div class="mb-3">
                                <label for="miembros" class="form-label">Miembros</label>
                                <a class="btn btn-secondary btn-circle btn-popover-agregarUsuario" data-bs-toggle="popover"><i class="fa-solid fa-plus"></i></a>
                                <div class="row content-miembros">

                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal" id="cerrarModal-guardarTarea">Cerrar</button>
                        <button type="submit" class="btn btn-dark" form="formAgregarTarea">Guardar Tarea</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal para ver detalles y modificar tareas -->
<!--        <div class="modal fade" id="actualizarTareasModal" tabindex="-1" aria-labelledby="actualizarTareasModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="actuliazarTareasModalLabel">Agregar tareas</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formAgregarTarea">
                            <input type="reset" id="resetForm-Agregar" hidden>
                            <div class="mb-3">
                                <label for="tarea" class="form-label">Tarea</label>
                                <input type="text" class="form-control" name="tarea" id="tarea" required>
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descrpcion</label>
                                <textarea class="form-control" name="descripcion" id="descripcion" rows="3"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="estado" class="form-label">Estado</label>
                                <select class="form-select" name="estado" id="estado">
                                    <option value="0">Selecciona un estado</option>
                                    <option value="1">Por hacer</option>
                                    <option value="2">En proceso</option>
                                    <option value="3">Realizado</option>
                                </select>
                                <span class="text-danger" id="validacion-estado"></span>
                            </div>
                            <div class="mb-3">
                                <label for="fechaInicio" class="form-label">Fecha de inicio</label>
                                <input type="date" class="form-control" name="fechaInicio" id="fechaInicio" required>
                            </div>
                            <div class="mb-3">
                                <label for="fechaFin" class="form-label">Fecha de finalizacion</label>
                                <input type="date" class="form-control" name="fechaFin" id="fechaFin" required>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal" id="cerrarModal-guardar">Cerrar</button>
                        <button type="submit" class="btn btn-dark" form="formAgregarTarea">Guardar Tarea</button>
                    </div>
                </div>
            </div>
        </div>-->

        <!--Modal para agregar estados-->
        <div class="modal fade" id="insertEstadoModal" tabindex="-1" aria-labelledby="insertEstadoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="insertEstadoModalLabel">Agregar Estado</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formInsertarEstado">
                            <input type="reset" hidden id="btnLimpiar-agergarEstado">
                            <input type="hidden" value="${proyecto.idProyecto}" name="idProyecto">
                            <div class="mb-3">
                                <label class="form-label" for="estado">Estado</label>
                                <input type="text" class="form-control" name="estado">
                                <span class="text-danger" id="estadoVal"></span>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="Color">Color</label>
                                <input type="color" class="form-control form-control-color" name="color" title="Escoge un color para tu estado.">
                                <span class="text-danger" id="colorVal"></span>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal" id="cerrarModal-guardarEstado">Cerrar</button>
                        <button type="submit" class="btn btn-dark" form="formInsertarEstado">Guardar Tarea</button>
                    </div>
                </div>
            </div>
        </div>

        <!--Modal para modificar estados-->
        <div class="modal fade" id="modificarEstadoModal" tabindex="-1" aria-labelledby="modificarEstadoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="modificarEstadoModalLabel">Modificar Estado</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="formModificarEstado">
                            <input type="reset" hidden id="btnLimpiar-modificarEstado">
                            <div class="mb-3">
                                <label class="form-label" for="estado">Estado</label>
                                <input type="text" class="form-control" id="estadoEdit" name="estado">
                                <span class="text-danger" id="estadoVal"></span>
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="Color">Color</label>
                                <input type="color" class="form-control form-control-color" id="color" name="Color" title="Escoge un color para tu estado.">
                                <span class="text-danger" id="colorVal"></span>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal" id="cerrarModal-modificarEstado">Cerrar</button>
                        <button type="submit" class="btn btn-dark" form="formModificarEstado">Guardar Tarea</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Popovers -->

        <div id="contenido-popover" style="display:none;">
            <h3 id="titulo">Agregar miembros</h3>
            <div id="contenido">
                <p>Miembros</p>
                Esta opcion estará disponible en futuras versiones
                <!--<ul>
                    <li class = "usuario-select" role="button">Usuario 1</li>
                    <li class = "usuario-select" role="button">Usuario 2</li>
                    <li class = "usuario-select" role="button">Usuario 3</li>
                </ul>-->
            </div>
        </div>

        <div id="contenido-popover" style="display:none;">
            <h3 id="titulo-usuario">Usuario</h3>
            <div id="contenido-usuario">
                <p>Detalles del usuario</p>
                <!--<a class="btn btn-danger btn-quitarUsuario">Quitar</a>-->
            </div>
        </div>

        <!-- Script para el diagrama de gantt -->
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        <script src="com.grupo5.utilidades/tablero.js" type="text/javascript"></script>
        <script src="com.grupo5.utilidades/gantt.js" type="text/javascript"></script>

        <!-- Agrega DataTables JS -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
        <script src="com.grupo5.utilidades/dataTableProyectos.js" type="text/javascript"></script>
        <script>
            //crear el boton de agregar usuario en popover
            const botonPopover = $(".btn-popover-agregarUsuario");
            const titulo = $("#titulo").text();
            const contenido = $('#contenido').html();
            let config = {
                title: titulo,
                content: contenido,
                html: true
            };

            const popover = new bootstrap.Popover(botonPopover, config);

            //evento click para agregar un usario al proyecto
            $(document).on("click", ".usuario-select", function (e) {
                let usuario = $(this).text();

                let btnUsuario = `<a class="btn btn-success btn-circle btn-detalleUsuario" data-bs-toggle="popover">` + usuario[0] + usuario[usuario.length - 1] + `</a>`;

                $(".content-miembros").append(btnUsuario);
                crearPopoverUsuario();
            });

            //crear popover en los usuarios agregados al proyecto
            function crearPopoverUsuario() {
                $(".btn-detalleUsuario").each(function () {
                    const botonPopover_usuario = $(this);
                    const titulo_usuario = $("#titulo-usuario").text();
                    const contenido_usuario = $('#contenido-usuario').html();
                    console.log(contenido_usuario);
                    let config_usuario = {
                        title: titulo_usuario,
                        content: contenido_usuario,
                        html: true
                    };

                    const popover = new bootstrap.Popover(botonPopover_usuario, config_usuario);
                });
            }

            //gestion de evento para quitar usuario
            $(document).on('click', '.btn-quitarUsuario', function () {
                let btnUsuario = $(this).closest('.btn-detalleUsuario');
                console.log(btnUsuario);
                btnUsuario.remove();
            });
            // Agregar un controlador de eventos de clic al documento
            $(document).on('click', function (e) {
                // Verificar si se hizo clic en un popover o en su contenido
                var popover = $(e.target).closest('[data-bs-toggle="popover"]');

                // Si no se hizo clic en un popover, cerrar todos los popovers abiertos
                if (!popover.length) {
                    $('[data-bs-toggle="popover"]').popover('hide');
                }
            });
        </script>
    </body>
</html>
