<%-- 
    Document   : principal
    Created on : 5 sep. 2023, 09:22:32
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" rel="stylesheet" type="text/css">
    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-dark sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="PrincipalControlador?accion=inicio" target="myFrame">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fa-solid fa-user"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Menu principal<sup></sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="PrincipalControlador?accion=inicio" target="myFrame">
                        <i class="fa-solid fa-thumbtack"></i>
                        <span>Inicio</span></a>
                    <a class="nav-link" href="PrincipalControlador?accion=misTareas" target="myFrame">
                        <i class="fa-solid fa-list-check"></i>
                        <span>Mis Tareas</span></a>
                </li>
                <!-- Divider -->
                <hr class="sidebar-divider">

                <!-- Nav Item - Pages Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href=""  data-toggle="collapse" data-bs-toggle="collapse" data-bs-target="#collapseTwo"
                       aria-expanded="true" aria-controls="collapseTwo">
                        <i class="fa-solid fa-plus"></i>
                        <span>Proyectos</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionSidebar">
                        <div class="bg-dark py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Proyectos:</h6>
                            <c:set var="proyectos" value="${requestScope.proyectos}" />
                            <c:forEach var="proyecto" items="${proyectos}">
                                        <a class="collapse-item text-white" href="PrincipalControlador?accion=proyectos&idProyecto=${proyecto.idProyecto}" target="myFrame">${proyecto.proyecto}</a>
                            </c:forEach>
                            
                            <a class="collapse-item text-white" href="#" data-bs-toggle="modal" data-bs-target="#insertProyectoModal">Crear proyecto <i class="fa-solid fa-plus"></i></a>
                        </div>
                </li>

                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-bs-toggle="collapse" data-bs-target="#collapseUtilities"
                       aria-expanded="true" aria-controls="collapseUtilities">
                        <i class="fa-regular fa-folder"></i>
                        <span>Reportes</span>
                    </a>
                    <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                         data-bs-parent="#accordionSidebar">
                        <div class="bg-dark py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Informe del proyecto:</h6>
                            <a class="collapse-item text-white" href="PrincipalControlador?accion=informes" target="myFrame">Proyecto 1</a>
                        </div>
                    </div>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">



                <!-- Nav Item - Pages equipos -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="PrincipalControlador?accion=equipos" aria-controls="collapsePages" target="myFrame">
                        <i class="fa-solid fa-people-group"></i>
                        <span>Equipos</span>
                    </a>
                </li>
                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>
            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">

                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small"><!--Nombre del usuario-->usuario</span>
                                    <img class="img-profile rounded-circle"
                                         src="">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/com.grupo5.vistas/opcionesRegistro/editarPerfil.jsp"">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Perfil
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Opciones
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Actividad
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Cerrar Sesion
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="" style="height:83%;" scrolling="no">
                        <iframe name="myFrame" style="height:100%; width: 100%; overflow-y: hidden" frameborder="0" scrolling-x="no"></iframe>
                    </div>
                    <!-- /.container-fluid -->
                </div>
                <!-- Footer -->
                <!--                <footer class="sticky-footer bg-white">
                                    <div class="container my-auto">
                                        <div class="copyright text-center my-auto">
                                            <span>Copyright &copy; Programacion 4 </span>
                                        </div>
                                    </div>
                                </footer>
                                 End of Footer 
                
                            </div>-->
                <!-- End of Content Wrapper -->

            </div>
            <!-- End of Page Wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>

            <!-- Logout Modal-->
            <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                            <a class="btn btn-primary" href="login.html">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <!--Modal para Crear proyectos-->
            <div class="modal fade" id="insertProyectoModal" tabindex="-1" aria-labelledby="insertProyectoModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="insertProyectoModalLabel">Crear Proyecto</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="ProyectosControlador?accion=insertar" id="insertarProyecto" method="POST">
                                <div class="mb-3">
                                    <label class="form-label" for="Proyecto">Proyecto</label>
                                    <input type="text" class="form-control" name="Proyecto" id="Proyecto">
                                    <span class="text-danger" id="proyectoVal"></span>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="Descripcion">Descripcion</label>
                                    <textarea class="form-control" name="Descripcion" id="Descripcion" rows="3"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label" for="Git">Repositorio de git</label>
                                    <input type="text" class="form-control" name="Git" id="Git" placeholder="Ejemplo: https://github.com/user/proyect.git">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-dark" form="insertarProyecto">Crear proyecto</button>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
            <script src="com.grupo5.utilidades/principal.js" type="text/javascript"></script>
            <script src="com.grupo5.utilidades/validacionesPrincipal.js" type="text/javascript"></script>
    </body>
</html>
