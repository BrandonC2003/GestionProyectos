<%-- 
    Document   : proyectos
    Created on : 5 sep. 2023, 09:24:42
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <link href="com.grupo5.utilidades/estilosProyecto.css" rel="stylesheet" type="text/css"/>

        <!-- Agrega DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
        <link href="com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="row">
            <h1>Proyecto 1.</h1>
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
                        <div class="card ms-3 me-3 mt-4 swim-lane">
                            <div class="card-header justify-content-center">
                                <h3 class="card-title">Por Hacer</h3>
                            </div>
                            <div class="card-body justify-content-center swim-lane-content">
                                <div class="card shadow-sm mb-2 task" draggable="true">
                                    <div class="card-body justify-content-center task-content">
                                        Tarea 1
                                    </div>
                                </div>
                                <div class="card shadow-sm mb-2 task" draggable="true">
                                    <div class="card-body justify-content-center task-content">
                                        Tarea 2
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <button type="button"class="btn-agregarTarea text-secondary" data-bs-toggle="modal" data-bs-target="#agregarTareasModal">Agregar Tarea <i class="fa-solid fa-plus"></i></button>
                            </div>
                        </div>

                        <div class="card ms-3 me-3 mt-4 swim-lane">
                            <div class="card-header justify-content-center">
                                <h3 class="card-title">En proceso</h3>
                            </div>
                            <div class="card-body justify-content-center swim-lane-content">
                                <div class="card task shadow-sm mb-2" draggable="true">
                                    <div class="card-body justify-content-center task-content">
                                        Tarea 3
                                    </div>
                                </div>
                                <div class="card task shadow-sm mb-2" draggable="true">
                                    <div class="card-body justify-content-center task-content">
                                        Tarea 4
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <button class="btn-agregarTarea text-secondary" data-bs-toggle="modal" data-bs-target="#agregarTareasModal">Agregar Tarea <i class="fa-solid fa-plus"></i></button>
                            </div>
                        </div>

                        <div class="card ms-3 me-3 mt-4 swim-lane">
                            <div class="card-header justify-content-center">
                                <h3 class="card-title">Realizado</h3>
                            </div>
                            <div class="card-body justify-content-center swim-lane-content">
                                <div class="card task shadow-sm mb-2" draggable="true">
                                    <div class="card-body justify-content-center task-content">
                                        Tarea 5
                                    </div>
                                </div>
                                <div class="card task shadow-sm mb-2" draggable="true">
                                    <div class="card-body justify-content-center task-content">
                                        Tarea 6
                                    </div>
                                </div>
                            </div>
                            <div class="card-footer">
                                <button class="btn-agregarTarea text-secondary" data-bs-toggle="modal" data-bs-target="#agregarTareasModal">Agregar Tarea <i class="fa-solid fa-plus"></i></button>
                            </div>
                        </div>

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
                                        <tr>
                                            <td><input type="checkbox"></td>
                                            <td>Tarea 3</td>
                                            <td>30/9/2023</td>
                                            <td>Usuario 1</td>
                                            <td class="text-bg-danger">Por hacer</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox"></td>
                                            <td>Tarea 2</td>
                                            <td>31/9/2023</td>
                                            <td>Usuario 1</td>
                                            <td class="text-bg-warning">En proceso</td>
                                        </tr>
                                        <tr>
                                            <td><input type="checkbox" checked></td>
                                            <td>Tarea 1</td>
                                            <td>20/9/2023</td>
                                            <td>Usuario 2</td>
                                            <td class="text-bg-success">Realizado</td>
                                        </tr>
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
                        <form id="formAgregarTarea">
                            <input type="reset" id="resetForm-Agregar" hidden>
                            <div class="mb-3">
                                <label for="tarea" class="form-label">Tarea</label>
                                <input type="text" class="form-control" name="tarea" id="tarea">
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descrpcion</label>
                                <textarea class="form-control" name="descripcion" id="descripcion" rows="3"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="fechaInicio" class="form-label">Fecha de inicio</label>
                                <input type="date" class="form-control" name="fechaInicio" id="fechaInicio">
                            </div>
                            <div class="mb-3">
                                <label for="fechaFin" class="form-label">Fecha de finalizacion</label>
                                <input type="date" class="form-control" name="fechaFin" id="fechaFin">
                            </div>
                            <div class="mb-3">
                                <label for="usuario" class="form-label">Usuario</label>
                                <select class="form-select" name="usuario" name="usuario">
                                    <option>Selecciona un usuario</option>
                                    <option value="1">Usuario 1</option>
                                    <option value="2">Usuario 2</option>
                                    <option value="3">Usuario 3</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal" id="cerrarModal-guardar">Cerrar</button>
                        <button type="submit" class="btn btn-dark" form="formAgregarTarea">Guardar Tarea</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal para modificar tareas -->


        <!-- Script para el diagrama de gantt -->
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        <script src="com.grupo5.utilidades/tableroK.js" type="text/javascript"></script>
        <script src="com.grupo5.utilidades/gantt.js" type="text/javascript"></script>

        <!-- Agrega DataTables JS -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
        <script src="com.grupo5.utilidades/dataTableProyectos.js" type="text/javascript"></script>
    </body>
</html>
