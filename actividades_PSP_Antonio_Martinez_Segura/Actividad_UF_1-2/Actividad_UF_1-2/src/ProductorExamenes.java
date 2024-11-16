import java.time.LocalDateTime;


//En esta clase se crean los examenes para ponerlos en la clase BufferExamenes

public class ProductorExamenes implements Runnable{
    private BufferExamenes buffer;
    private static int numeroExamen = 0;
    private Thread hilo;



    public ProductorExamenes(BufferExamenes buffer) {
        this.buffer = buffer;
        // Incrementa el contador de exámenes (variable numeroExamen).
        numeroExamen++;
        //creamos hilo con el nombre "E"
        this.hilo = new Thread(this, "E" + numeroExamen);
        //iniciamos hilo para producir el examen
        hilo.start();

    }

    //Como la clase implementa a Runnable, hay que sobreescribir el metodo run() y define la tarea del hilo
    @Override
    public void run() {
        //obtener año actual
        int aa = LocalDateTime.now().getYear();

        // Generamos del código de examen.
        String codigo = this.hilo.getName() + "-" +aa;

        // Añade el nuevo código al buffer de exámenes.
        buffer.fabricarNuevoExamen(codigo);

        // Muestra un mensaje en consola informando sobre el código del examen que se acaba de producir.
        System.out.println("Examen producido -> " + codigo);

    }
}
