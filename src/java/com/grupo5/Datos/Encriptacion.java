package com.grupo5.Datos;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Emerson Humberto
 */
public class Encriptacion {
   private static final String ALGORITMO = "AES";
    private static final String TRANSFORMACION = "AES/CBC/PKCS5Padding";

    public static String encriptar(String texto) {
        try {
            SecretKey claveSecreta = generarClaveSecreta();
            Cipher cipher = Cipher.getInstance(TRANSFORMACION);
            IvParameterSpec ivParameterSpec = generarIV();
            cipher.init(Cipher.ENCRYPT_MODE, claveSecreta, ivParameterSpec);

            byte[] textoEnBytes = texto.getBytes("UTF-8");
            byte[] textoEncriptado = cipher.doFinal(textoEnBytes);

            byte[] iv = cipher.getIV();
            byte[] resultado = new byte[iv.length + textoEncriptado.length];
            System.arraycopy(iv, 0, resultado, 0, iv.length);
            System.arraycopy(textoEncriptado, 0, resultado, iv.length, textoEncriptado.length);

            return Base64.getEncoder().encodeToString(resultado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String desencriptar(String textoEncriptado) {
        try {
            byte[] datos = Base64.getDecoder().decode(textoEncriptado);
            byte[] iv = new byte[16];
            byte[] textoEncriptadoBytes = new byte[datos.length - 16];
            System.arraycopy(datos, 0, iv, 0, 16);
            System.arraycopy(datos, 16, textoEncriptadoBytes, 0, textoEncriptadoBytes.length);

            SecretKey claveSecreta = generarClaveSecreta();
            Cipher cipher = Cipher.getInstance(TRANSFORMACION);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, claveSecreta, ivParameterSpec);

            byte[] textoDesencriptadoBytes = cipher.doFinal(textoEncriptadoBytes);
            return new String(textoDesencriptadoBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static SecretKey generarClaveSecreta() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITMO);
        keyGenerator.init(128, new SecureRandom());
        return keyGenerator.generateKey();
    }

    private static IvParameterSpec generarIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
