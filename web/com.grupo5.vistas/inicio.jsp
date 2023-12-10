<%-- 
    Document   : inicio
    Created on : 5 sep. 2023, 09:24:06
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

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!-- comment -->
        <title>Inicio</title>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">


        <link href="com.grupo5.utilidades/stylesProyecto.css" rel="stylesheet" type="text/css"/>

        <!-- Agrega DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
        <link href="com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>

        <!-- agregar sweetAlert2 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    </head>

    <body >
        <section>
            <div class="container mt-5">
                <h1>Tus proyectos</h1>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-2 justify-content-center">

                    <c:set var="proyectos" value="${requestScope.proyectos}" />
                    <c:forEach var="proyecto" items="${proyectos}">
                        <a href="PrincipalControlador?accion=proyectos&idProyecto=${proyecto.idProyecto}" target="myFrame">
                            <div class="col mb-5">
                                <div class="card h-100">
                                    <!-- Product image-->
                                    <img class="card-img-top" src="https://th.bing.com/th/id/R.fdca197814bf261e5a1c2956b5f2b262?rik=3EMYzFwcaHhPXg&riu=http%3a%2f%2fwww.solofondos.com%2fwp-content%2fuploads%2f2015%2f11%2ffondo_001.jpg&ehk=FuptxqWbECsXf%2fWwKzrA99hHUEXkn0ib4%2fqjNJlTTMs%3d&risl=&pid=ImgRaw&r=0" alt="..." />
                                    <!-- Product details-->
                                    <div class="card-body p-4">
                                        <div class="text-center">
                                            <!-- Product name-->
                                            <h5 class="fw-bolder">${proyecto.proyecto}</h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </c:forEach>

                </div>
            </div>
        </section>
    </body>
</html>
