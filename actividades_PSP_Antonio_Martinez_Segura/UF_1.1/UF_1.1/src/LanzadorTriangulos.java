import java.io.File;
import java.io.IOException;

public class LanzadorTriangulos {
    public static void main(String[] args) {

        try{
            String classpath = System.getProperty("java.class.path");
            //inicio proceso para triangulo 5
            ProcessBuilder p1 = new ProcessBuilder("java", "-cp",classpath,"Triangulo", "5");
            p1.redirectOutput(new File("triangulo5.txt"));
            p1.redirectError(new File("errores5.txt"));
            p1.start();

            //inicio proceso para triangulo 7
            ProcessBuilder p2 = new ProcessBuilder("java", "-cp",classpath,"Triangulo", "7");
            p2.redirectOutput(new File("Triangulo7.txt"));
            p2.redirectError(new File("errores7.txt"));
            p2.start();

            //inicio proceso triangulo 9

            ProcessBuilder p3 = new ProcessBuilder("java", "-cp", classpath, "Triangulo", "9");
            p3.redirectOutput(new File("triangulo9.txt"));
            p3.redirectError(new File("errores9.txt"));
            p3.start();
        } catch (IOException e) {
            System.out.println("se ha producido un error lanzando los procesos" + e.getMessage());
        }
    }
}
