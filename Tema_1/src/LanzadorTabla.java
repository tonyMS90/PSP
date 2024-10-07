import java.io.File;
import java.io.IOException;

public class LanzadorTabla {
    public static void main(String[] args) {

        //ProcessBuilder proceso = new ProcessBuilder("java", "Tabla");//queremos ejecutar la tabla llamando a java tabla(es decir, a la clase tabla que hemos hecho previamente para las tablas de multiplicar
        String classpath = System.getProperty("java.class.path");//antes de poner la ruta instanciada, hay que indicar a java esto para que funcione (es la ruta del claspath).
        ProcessBuilder proceso = new ProcessBuilder("java", "-cp", classpath, "Tabla");//se pone asi, no como arriba (Ejecuta en la clase donde estas, el fichero o la ruta .Tabla)
        proceso.redirectError(new File("errores.txt"));//captura el error y nos lo guarda en errores.txt
        proceso.redirectOutput(new File("salida.txt"));//redirige la salida al fichero salida.txt que debe existir previamente
        proceso.redirectInput(new File("entrada.txt"));//esto sirve para cuando usamos el escanner, que en lugar de leer por el teclado, lee lo del fichero que le decimos (entrada.txt)recoge los valores de entrada del fichero entrada.txt
        try {
            proceso.start().waitFor();
            System.out.println("el rpceso ha sido lanzado con exito");
            System.out.println("examina los archivos errrores.txt y salida.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
