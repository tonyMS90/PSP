import java.util.LinkedList;
import java.util.Queue;

//Esta clase sirve para almacenar los examenes que se generen en la clase productorExamenes
// //y que asi luego puedan ser utilizados a traves de los hilos por la clase Examinado
public class BufferExamenes {

    //creamos la cola y hacemos una linkedlist con ella para ir añadiendo los examenes
    private Queue<String> colaExamenes;



    public BufferExamenes() {

        colaExamenes = new LinkedList<String>();

    }


    //Este es el metodo sincronizado para producir un nuevo examen
    public synchronized void fabricarNuevoExamen(String codigo) {
        //añadimos examen a la cola por su codigo
        colaExamenes.add(codigo);
        //notificar a los hilos de espera que ya hay un examen creado
        notify();

    }

    //metodo sincronizado para consumir un examen
    public synchronized String consumirExamen() {
        //avisamos de que mientras la cola este vacia hay que esperar
        while(colaExamenes.isEmpty()){
            try {
                wait();//esperar a que se fabrique un examen
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        //poll devolveria null si la cola esta vacia. Si queremos que lance excepcion se pondria colaExamenes.remove();
        return colaExamenes.poll();

    }

}

