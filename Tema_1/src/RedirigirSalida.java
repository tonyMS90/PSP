import java.io.File;
import java.io.IOException;

public class RedirigirSalida {
    public static void main(String[] args) {
        try{
            //crea el ProcessBuilder para el bloc de notas(notepar.exe)
            ProcessBuilder pb = new ProcessBuilder("notepad.exe");
            //REDIRIGIR LA SALIDA Y ERRORES A UN ARCHIVO LLAMADO SALIDA.TXT
            pb.redirectOutput(new File("salida.txt")); //salida estandar redirigida a salida.txt
            pb.redirectErrorStream(true); //redirigir tambien los errores al archivo "salida.txt"

            //INICAR AL PROCESO (BLOC DE NOTAS)
            Process process = pb.start();

            //ESPERAR A QUE EL PROCESO TERMINE(CUANDO CIERRAS EL BLOC DE NOTAS)

            process.waitFor();
            System.out.println("proceso terminado. Revisa el archivo de salida.txt");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
