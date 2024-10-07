import java.io.IOException;

public class ProcesoLanzador {
    //Método que lanza el proceso(en este caso, la calculadora)
    public void lanzarProceso(){
        ProcessBuilder proceso = new ProcessBuilder("c:/windows/System32/calc.exe");
        try {
            proceso.start();

        } catch (IOException e) {
            System.out.println("error al lanzar el proceso: " + e.getMessage());
        }
    }
}
