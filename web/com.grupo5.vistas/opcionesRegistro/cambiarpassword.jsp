<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
    
    
    <title>Cambiar contraseña</title>
    
     <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet"> 
    <link href="com.grupo5.utilidades/principal.css" rel="stylesheet" type="text/css"/>
    <script src="com.grupo5.utilidades/principal.js" type="text/javascript"></script>
    
</head>
<body>
    
    <c:set var="mensaje" value="${requestScope.mensaje}" /> 
    <c:set var="usuarios" value="${requestScope.usuario}" /> 
    <div id="mensaje"></div>
    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Cambiar Contraseña</h1>
                            </div>
                            <form class="user" accion="UsuariosControlador?accion=clave" onsubmit="return validarFormulario();" id="form" method="POST">
                                <input type="hidden" id="idUsuario" name="idUsuario" value="${usuarios.idUsuario}">                                
                                <input type="hidden" name="claveActual" value="${usuarios.clave}" >
                                <div class="form-group"> 
                                     <span class="text-danger" id="contraVal"></span>
                                    <div class="form-group">
                                       <span class="text-danger" id="contraVal">Contraseña actual</span>
                                       <input type="password" name="Clave"  class="form-control form-control-user" id="clave" placeholder="Ingrese la contraseña actual">                                                
                                    </div>                                    
                                     <div class="form-group">
                                        <span>Nueva contraseña</span>
                                        <input type="password" name="nueva" class="form-control form-control-user" id="nueva" placeholder="Ingrese la nueva contraseña">                                            
                                    </div>
                                    <div class="form-group">
                                        <span>Repetir contraseña</span>
                                        <input type="password" name="confir" class="form-control form-control-user" id="confir" placeholder="Repita la nueva contraseña">                                            
                                    </div>
                                    
                                </div>
                                                                                              
                                <div class="form-group">
                                    <input type="submit" name="clave" value="Cambiar Contraseña" class="btn btn-primary btn-user btn-block">
                                    <a href="#" class="btn btn-secondary btn-user btn-block" onclick="cancelar()">Cancelar</a>
                                    <p class="warnings" id="warnings"></p>                                     
                                </div>
                            <hr>                          
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> 
            <script>
                if(${mensaje} === null){
                    alert(${mensaje});
                }
                function validarFormulario() {
                    // Obtén los valores de los campos del formulario
                    var contrasenaActual = document.getElementById('clave').value;
                    var nuevaContrasena = document.getElementById('nueva').value;
                    var confirmarContrasena = document.getElementById('confir').value;

                    // Realiza las validaciones necesarias
                    if (contrasenaActual === '') {
                      alert('Por favor, ingresa tu contraseña actual');
                      return;
                    } 

                    if (nuevaContrasena === '') {
                      alert('Por favor, ingresa tu nueva contraseña');
                      return;
                    }

                    if (confirmarContrasena === '') {
                      alert('Por favor, confirma tu nueva contraseña');
                      return;
                    }

                    if (nuevaContrasena !== confirmarContrasena) {
                      alert('Las contraseñas no coinciden');
                      return;
                    }

                    // Si todas las validaciones son exitosas, puedes enviar el formulario
                    document.getElementById('form').submit();
                  }
                
                function cancelar() {
                    // Redirigir a la página de inicio de sesión
                    window.location.href = "../../PrincipalControlador?accion=login";
                }
                
            </script>
            
             <!--script src="com.grupo5.utilidades/validarClave.js"></script-->
</body>
</html>