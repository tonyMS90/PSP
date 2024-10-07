import javax.imageio.IIOException;
import java.io.IOException;

public class LanzarCalculadora {
    public static void main(String[] args){


        //proceso 1 (basico)

       /* ProcessBuilder proceso = new ProcessBuilder("c:/windows/System32/calc.exe");
        try{
            proceso.start();
            System.out.println("proceso lanzado");
        }
            catch(IOException e){
                System.out.println(e.getMessage());
            }*/

        //proceso 2 (con clase asociada y metodo)
        //crea una instancia de la clase ProcesoLanzador
        ProcesoLanzador lanzador = new ProcesoLanzador();

        //Lanzar el proceso 3 veces
        for(int i = 1; i <= 3; i++){
            lanzador.lanzarProceso();
            System.out.println("instancia " + i + " del proceso lanzada");
        }

    }
}
