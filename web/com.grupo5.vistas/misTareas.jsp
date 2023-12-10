<%-- 
    Document   : misTareas
    Created on : 5 sep. 2023, 09:24:32
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <link href="com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>
    <link href="com.grupo5.utilidades/principal.js" rel="stylesheet" type="text/css"/>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Tareas</title>  
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">


        <link href="com.grupo5.utilidades/stylesProyecto.css" rel="stylesheet" type="text/css"/>

        <!-- Agrega DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
        <link href="com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>

        <!-- agregar sweetAlert2 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> 

    </head>

    <body>
        <h1>Mis Tareas</h1>
        <div id="page-content">
            <div class="header">
                <nav class="navbar-classic navbar navbar-expand-lg navbar navbar-expand navbar-light">
                    <div class="d-flex justify-content-between w-100">
                        </div>
                        <div class="navbar-right-wrap ms-2 d-flex nav-top-wrap navbar-nav">
                            <ul class="navbar-right-wrap ms-auto d-flex nav-top-wrap navbar-nav">
                                <li class="stopevent dropdown">
                                    <a class="btn btn-light btn-icon rounded-circle indicator indicator-primary text-muted  " id="dropdownNotification" aria-expanded="false">
                                        <i class="fe fe-bell"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
            <div class="bg-primary pt-10 pb-21"></div>
            <div class="mt-n22 px-6 container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-12">
                        <div>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="mb-2 mb-lg-0">
                                    <h3 class="mb-0  text-white">Proyectos</h3>
                                </div>
                                <div class="container">
                                    <table class="table" id="example" class="display" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Tarea</th>
                                                <th>FechaFin</th>
                                                <th>Estado</th>
                                                <th>Proyecto</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="tareas" items="${requestScope.listTareas}">
                                            <tr>
                                                <td>${tareas.tarea}</td>
                                                <td>${tareas.fechaFin}</td>
                                                <td>${tareas.estado}</td>
                                                <td>${tareas.proyecto}</td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
        <!-- Script para el diagrama de gantt -->
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        <script src="com.grupo5.utilidades/tablero.js" type="text/javascript"></script>

        <!-- Agrega DataTables JS -->
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
        <script src="com.grupo5.utilidades/dataTableProyectos.js" type="text/javascript"></script>
    </body>
</html>