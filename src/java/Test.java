import com.grupo5.Datos.Encriptacion;
import static com.grupo5.Datos.Encriptacion.desencriptar;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


import static com.grupo5.Datos.Encriptacion.encriptar;

/**
 *
 * @author Emerson Humberto
 */
public class Test {
    
    public static void main(String[] args){
       
        String textoOriginal = "Hola, esto es un texto de prueba";

        String textoEncriptado = encriptar(textoOriginal);
        System.out.println("Texto Encriptado: " + textoEncriptado);

        String textoDesencriptado = desencriptar(textoEncriptado);
        System.out.println("Texto Desencriptado: " + textoDesencriptado);
}
}
