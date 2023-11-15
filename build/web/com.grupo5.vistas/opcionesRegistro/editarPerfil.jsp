

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <link href="../../com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>
    <script src="../../com.grupo5.utilidades/principal.js" type="text/javascript"></script>
<head>
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Modificar perfil</title>
    
     <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet"> 
</head>
<body>
    
    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Modificar cuenta</h1>
                            </div>
                            <form class="user">
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" name="txtNombre" class="form-control form-control-user" id="exampleFirstName"
                                            placeholder="Nombres">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" name="txtApellido" class="form-control form-control-user" id="exampleLastName"
                                            placeholder="Apellidos">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="email" name="txtEmail" class="form-control form-control-user" id="exampleInputEmail"
                                        placeholder="Ingrese su Email">
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" name="txtClave" class="form-control form-control-user"
                                            id="exampleInputPacssword" placeholder="contraseña">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" name="txtConfirmacion" class="form-control form-control-user"
                                            id="exampleRepeatPassword" placeholder="Repita la contraseña">
                                    </div>
                                </div>
                                <input type="submit" name="modificar" value="Guardar Cuenta" class="btn btn-primary btn-user btn-block"> 
                                <!--a href="login.html" class="btn btn-primary btn-user btn-block">
                                    Guardar Cuenta
                                </a-->
                            <hr>
                            
                            <div class="text-center">
                                <a class="small" href="http://localhost:8080/GestionProyectos/">Inicie secion aqui!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
