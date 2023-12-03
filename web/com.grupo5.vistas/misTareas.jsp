<%-- 
    Document   : misTareas
    Created on : 5 sep. 2023, 09:24:32
    Author     : brand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>
    <link href="com.grupo5.utilidades/principal.js" rel="stylesheet" type="text/css"/>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Tareas</title>  
         
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet"> 
        
    </head>
    <body>
        <h1>Mis Tareas</h1>
        
        <div id="page-content">
                    <div class="header">
                        <nav class="navbar-classic navbar navbar-expand-lg navbar navbar-expand navbar-light">
                            <div class="d-flex justify-content-between w-100">
                                <div class="d-flex align-items-center">
                                    <a id="nav-toggle" class="nav-icon me-2 icon-xs" href="/#">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="18px" height="18px" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                            <line x1="3" y1="12" x2="21" y2="12"></line>
                                            <line x1="3" y1="6" x2="21" y2="6"></line>
                                            <line x1="3" y1="18" x2="21" y2="18"></line>
                                        </svg>
                                    </a>
                                    <div class="ms-lg-3 d-none d-md-none d-lg-block">
                                        <form class="d-flex align-items-center">
                                            <input placeholder="Search" type="search" class="form-control"/>
                                        </form>
                                    </div>
                                </div>
                                <div class="navbar-right-wrap ms-2 d-flex nav-top-wrap navbar-nav">
                                    <ul class="navbar-right-wrap ms-auto d-flex nav-top-wrap navbar-nav">
                                        <li class="stopevent dropdown">
                                            <a class="btn btn-light btn-icon rounded-circle indicator indicator-primary text-muted  " id="dropdownNotification" aria-expanded="false">
                                                <i class="fe fe-bell"></i>
                                            </a>
                                        </li>
                                        <li class="ms-2 dropdown">
                                            <a class="rounded-circle  " id="dropdownUser" aria-expanded="false">
                                                <div class="avatar avatar-md avatar-indicators avatar-online">
                                                    <img alt="avatar" src="/images/avatar/avatar-1.jpg" class="rounded-circle"/>
                                                </div>
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
                                        <div>
                                            <a class="btn btn-white" href="/#">Create New Project</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-6 col-xl-3 col-lg-6 col-md-12 col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between align-items-center mb-3">
                                            <div>
                                                <h4 class="mb-0">Projects</h4>
                                            </div>
                                            <div class="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18" fill="currentColor">
                                                    <path d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"></path>
                                                </svg>
                                            </div>
                                        </div>
                                        <div>
                                            <h1 class="fw-bold">18</h1>
                                            <p class="mb-0">
                                                <span className="text-dark me-2">2</span>
                                                Completedos
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-6 col-xl-3 col-lg-6 col-md-12 col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between align-items-center mb-3">
                                            <div>
                                                <h4 class="mb-0">Active Task</h4>
                                            </div>
                                            <div class="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18" fill="currentColor">
                                                    <path fill-rule="evenodd" d="M2 2.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5V3a.5.5 0 0 0-.5-.5H2zM3 3H2v1h1V3z"></path>
                                                    <path d="M5 3.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM5.5 7a.5.5 0 0 0 0 1h9a.5.5 0 0 0 0-1h-9zm0 4a.5.5 0 0 0 0 1h9a.5.5 0 0 0 0-1h-9z"></path>
                                                    <path fill-rule="evenodd" d="M1.5 7a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5H2a.5.5 0 0 1-.5-.5V7zM2 7h1v1H2V7zm0 3.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5H2zm1 .5H2v1h1v-1z"></path>
                                                </svg>
                                            </div>
                                        </div>
                                        <div>
                                            <h1 class="fw-bold">132</h1>
                                            <p class="mb-0">
                                                <span className="text-dark me-2">28</span>
                                                Completedos
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-6 col-xl-3 col-lg-6 col-md-12 col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between align-items-center mb-3">
                                            <div>
                                                <h4 class="mb-0">Teams</h4>
                                            </div>
                                            <div class="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18" fill="currentColor">
                                                    <path d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8Zm-7.978-1A.261.261 0 0 1 7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002a.274.274 0 0 1-.014.002H7.022ZM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4Zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0ZM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816ZM4.92 10A5.493 5.493 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275ZM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0Zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4Z"></path>
                                                </svg>
                                            </div>
                                        </div>
                                        <div>
                                            <h1 class="fw-bold">12</h1>
                                            <p class="mb-0">
                                                <span className="text-dark me-2">1</span>
                                                Completed
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-6 col-xl-3 col-lg-6 col-md-12 col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-between align-items-center mb-3">
                                            <div>
                                                <h4 class="mb-0">Productivity</h4>
                                            </div>
                                            <div class="icon-shape icon-md bg-light-primary text-primary rounded-2">
                                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="18" height="18" fill="currentColor">
                                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
                                                    <path d="M8 13A5 5 0 1 1 8 3a5 5 0 0 1 0 10zm0 1A6 6 0 1 0 8 2a6 6 0 0 0 0 12z"></path>
                                                    <path d="M8 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8z"></path>
                                                    <path d="M9.5 8a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"></path>
                                                </svg>
                                            </div>
                                        </div>
                                        <div>
                                            <h1 class="fw-bold">76%</h1>
                                            <p class="mb-0">
                                                <span className="text-dark me-2">5%</span>
                                                Completed
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mt-6 row">
                            <div class="col-md-12 col-12">
                                <div class="card">
                                    <div class="bg-white  py-4 card-header">
                                        <h4 class="mb-0">Active Projects</h4>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="text-nowrap mb-0 table">
                                            <thead class="table-light">
                                                <tr>
                                                    <th>Project name</th>
                                                    <th>Hours</th>
                                                    <th>priority</th>
                                                    <th>Members</th>
                                                    <th>Progress</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td class="align-middle">
                                                        <div class="d-flex align-items-center">
                                                            <div>
                                                                <div class="icon-shape icon-md border p-4 rounded-1 bg-white">
                                                                    <img src="/images/brand/dropbox-logo.svg" alt="" class=""/>
                                                                </div>
                                                            </div>
                                                            <div class="ms-3 lh-1">
                                                                <h5 class=" mb-1">
                                                                    <a class="text-inherit" href="/#">Dropbox Design System</a>
                                                                </h5>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td class="align-middle">34</td>
                                                    <td class="align-middle">
                                                        <span class="badge bg-warning">Medium</span>
                                                    </td>
                                                    <td class="align-middle">
                                                        <div class="avatar-group">
                                                            <span class="avatar avatar-sm">
                                                                <img alt="avatar" src="images/avatar/avatar-1.jpg" class="rounded-circle"/>
                                                            </span>
                                                            <span class="avatar avatar-sm">
                                                                <img alt="avatar" src="images/avatar/avatar-2.jpg" class="rounded-circle"/>
                                                            </span>
                                                            <span class="avatar avatar-sm">
                                                                <img alt="avatar" src="images/avatar/avatar-3.jpg" class="rounded-circle"/>
                                                            </span>
                                                            <span class="avatar avatar-sm avatar-primary">
                                                              </span>
                                                        </div>
                                                    </td> 
                                                </tr>
                                            
                          
                                                <div class="px-6 border-top py-3">
                                                    <div class="row">
                                                        <div class="text-center text-sm-start mb-2 mb-sm-0 col-sm-6">
                                                            <p class="m-0">
                                                                Made by <a href="https://codescandy.com/" target="_blank">Codescandy</a>
                                                            </p>
                                                        </div>
                                                        <div class="text-center text-sm-end col-sm-6">
                                                            <p class="m-0">
                                                                Destributed by <a href="https://themewagon.com/" target="_blank">ThemeWagon</a>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>                       
                            </div>                            
                        </div>                                
                    </div>
        </div>   
    </body>
</html>