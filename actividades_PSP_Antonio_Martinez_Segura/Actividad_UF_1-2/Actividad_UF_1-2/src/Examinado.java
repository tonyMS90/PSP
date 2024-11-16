import java.util.Random;

//Hilo simulando a los estudiantes haciendo un examen. Coge un examen de la cola del buffer
//y con el metodo run generamos respuestas aleatorias

public class Examinado implements Runnable{
    private Thread hilo;
    private BufferExamenes buffer;

    public Thread getHilo() {

        return hilo;

    }

    public Examinado(String alumno, BufferExamenes generador) {

        // Construye el hilo. El nombre será el nombre del alumno.
        this.hilo = new Thread(this, alumno);
        // Establece el valor de la propiedad buffer
        this.buffer = generador;
        // Inicia el hilo.
        hilo.start();

    }

    @Override

    public synchronized void run() {

        String codigoExamen = this.buffer.consumirExamen();

        if (codigoExamen != null) {
            Random random = new Random();
            // Simula aquí un examen de 10 preguntas cuyas respuestas se seleccionarán al azar entre A, B, C, D o – (sin contestar).
            //primero genero el Array con las respuestas
            String[] respuestas = {"A", "B", "C", "D", "-"};
            //recorro el Array
            for(int i = 1; i<= 10; i++){
               String respuesta = respuestas[random.nextInt(respuestas.length)];
                System.out.println(codigoExamen + ";" + this.hilo.getName() + "; pregunta " + i + ";" + respuesta);
            }

        }

        else {

            System.out.println("Agotado tiempo de espera y " + "no hay más exámenes");

        }

    }
}
