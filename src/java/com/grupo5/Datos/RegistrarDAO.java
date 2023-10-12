package com.grupo5.Datos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.grupo5.modelo.Usuarios;

public class RegistrarDAO {
    private Map<String, String> usuarios = new HashMap<>();
    private Map<String, String> codigosConfirmacion = new HashMap();

    public boolean agregarNuevoUsuario(String correo, String contrasena) {
        // Verificar si el correo ya está en uso
        if (usuarios.containsKey(correo)) {
            System.out.println("El correo electrónico ya está en uso.");
            return false;
        }

        // Verificar que la contraseña tenga más de 8 caracteres
        if (contrasena.length() <= 8) {
            System.out.println("La contraseña debe tener más de 8 caracteres.");
            return false;
        }

        // Verificar que no haya campos vacíos
        if (correo.isEmpty() || contrasena.isEmpty()) {
            System.out.println("No pueden quedar campos vacíos.");
            return false;
        }

        // Encriptar la contraseña
        String contrasenaEncriptada = encriptarContrasena(contrasena);

        // Generar un código de confirmación y enviar un correo
        String codigoConfirmacion = generarCodigoConfirmacion();
        enviarCorreoConfirmacion(correo, codigoConfirmacion);

        // Almacenar el usuario en la base de datos
        usuarios.put(correo, contrasenaEncriptada);
        codigosConfirmacion.put(correo, codigoConfirmacion);

        System.out.println("Usuario registrado con éxito. Se ha enviado un correo de confirmación.");

        return true;
    }

    private String encriptarContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] contrasenaBytes = contrasena.getBytes();
            byte[] contrasenaEncriptadaBytes = md.digest(contrasenaBytes);

            StringBuilder sb = new StringBuilder();
            for (byte b : contrasenaEncriptadaBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generarCodigoConfirmacion() {
        Random random = new Random();
        int codigo = random.nextInt(1000000);
        return String.format("%06d", codigo);
    }

    private void enviarCorreoConfirmacion(String correo, String codigo) {
        // Aquí debes implementar el envío de correo de confirmación, ya sea mediante una librería de correo o un servicio de correo.
        System.out.println("Se ha enviado un correo de confirmación a " + correo + " con el código: " + codigo);
    }

    public static void main(String[] args) {
        Usuarios usuarioManager = new Usuarios();
        usuarioManager.agregarNuevoUsuario("usuario@example.com", "contrasena123");
    }
}
