import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
    private Queue<String> colaExamenes;



    public BufferExamenes() {

        colaExamenes = new LinkedList<String>();

    }


    //Este es el metodo sincronizado para producir un nuevo examen
    public synchronized void fabricarNuevoExamen(String codigo) {
        colaExamenes.add(codigo);
        //notificar a los hilos de espera
        notify();

// Aquí se fabrica un nuevo examen.

// Un hilo de la clase ProductorExamenes

// se encargará de fabricarlo

// y pasarlo como argumento a este método.



// Añade el código pasado como argumento a la cola

// y libera el hilo que está intentando consumir

// un nuevo examen.

    }


    //metodo sincronizado para consumir un examen
    public synchronized String consumirExamen() {
        while(colaExamenes.isEmpty()){
            try {
                wait();//esperar a que se fabrique un examen
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        //poll devolveria null si la copla esta vacia. Si queremos que lance excepcion se pondria colaExamenes.remove();
        return colaExamenes.poll();

// Este método se encargará de suministrar un examen

// a cada hilo de tipo Examinador que lo solicite.



// Para suministrar el examen habrá antes que esperar

// hasta que haya algún examen para consumir en la cola.



// Haz aquí una pausa hasta que se haya fabricado algún examen.



// Si la cola sigue sin estar vacía, saca un examen y

// entrégalo como retorno de esta función.

    }

}

