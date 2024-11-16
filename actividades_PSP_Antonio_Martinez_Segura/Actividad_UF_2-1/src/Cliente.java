import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

//Clase cliente de prueba, para probar la correcta conexion servidor-cliente
public class Cliente {
    public static void main(String[] args) {
        try {
            //conexion con el servidor en la IP yu puerto correspondiente
            Socket socketAlServidor = new Socket("192.168.56.1", 2000);
            System.out.println("Conectado al servidor.");

            //Flujos de entrada y salida para la comunicacion
            InputStream entrada = socketAlServidor.getInputStream();
            OutputStream salida = socketAlServidor.getOutputStream();
            //con Scanner leo el input
            Scanner scanner = new Scanner(System.in);

            String mensaje = "";

            //si el mensaje es el codigo, continuamos. Si es FIN, terminamos
            while (!mensaje.equals("FIN")) {
                System.out.print("Introduce el código de producto (o 'FIN' para terminar): ");
                mensaje = scanner.nextLine().trim();

                // Enviar mensaje al servidor
                salida.write(mensaje.getBytes());

                // Recibir respuesta del servidor con el buffer
                byte[] buffer = new byte[256];
                //lectura de la respuesta
                int bytesLeidos = entrada.read(buffer);
                String respuesta = new String(buffer, 0, bytesLeidos);

                System.out.println("Respuesta del servidor: " + respuesta);
            }
            //cerramos las conexiones
            entrada.close();
            salida.close();
            socketAlServidor.close();
            System.out.println("Conexión cerrada.");

            //manejo de excepciones(por si falla la conexion)
        } catch (IOException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
