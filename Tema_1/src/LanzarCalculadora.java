import javax.imageio.IIOException;
import java.io.IOException;

public class LanzarCalculadora {
    public static void main(String[] args){

        ProcessBuilder proceso = new ProcessBuilder("c:/windows/System32/calc.exe");
        try{
            proceso.start();
            System.out.println("proceso lanzado");
        }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
    }
}
