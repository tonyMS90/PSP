import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Servidor {
    //uso List para almacenar los libros

    private static List<Libro> libros = new ArrayList<>();

    public static void main(String[] args) {
        // Creo los libros para que los contenga la List

        libros.add(new Libro("A12345678", "El monje que vendio su ferrari", "Robin S. Sharma", 17.50));
        libros.add(new Libro("B12345678", "Muchas vidas, muchos maestros", "Bryan Weis", 14.90));
        libros.add(new Libro("C12345678", "El monstruo de colores", "Anna Llenas", 19.95));
        libros.add(new Libro("D12345678", "La isla", "Isabel Allende", 21.90));
        libros.add(new Libro("E12345678", "Yo mejor, tu mas", "Montse Folgado", 25.10));

        //inicializo serversocket fuera de try para despues poder cerrarlo en finally
        ServerSocket serverSocket = null;

        try {
            //Inicio de conexión del servidor en el puerto 8080
            serverSocket = new ServerSocket(8080);
            System.out.println("Servidor iniciado, esperando al cliente.");

            // El servidor acepta la conexion del cliente
            //Inicializo la lectura mediante el BufferedReader
            //Inicializo la Escritura con PrintWritter con autoflush, para asegurar que el mensaje llega correctamente
            Socket clientSocket = serverSocket.accept();
            BufferedReader leer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter escribir = new PrintWriter(clientSocket.getOutputStream(), true);


            System.out.println("Cliente conectado");

            String mensaje;
            // Le pido al servidor que lea los mensajes  del cliente mientras sea diferente de null
            while ((mensaje = leer.readLine()) != null) {

                //inicializo datos para obtener los datos correspondientes cuando se los pida
                //con split consigo que cuando llegue a la coma deje de leer
                String[] datos = mensaje.split(",");
                String opcion = datos[0];

                switch (opcion) {
                    case "consultar_isbn":
                        consultarPorISBN(datos[1], escribir);
                        break;
                    case "consultar_titulo":
                        consultarPorTitulo(datos[1], escribir);
                        break;
                    case "consultar_autor":
                        consultarPorAutor(datos[1], escribir);
                        break;
                    case "añadir_libro":
                        añadirLibro(datos[1], datos[2], datos[3], Double.parseDouble(datos[4]), escribir);
                        mostrarLibros(new PrintWriter(escribir));
                        break;
                    case "salir":
                        System.out.println("Cliente desconectado");
                        //cierro todas las conexiones

                        clientSocket.close();
                        leer.close();
                        escribir.close();
                        return;
                    default:
                        escribir.println("No puedo reconocer esa opción.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error en la conexion " + e.getMessage());;
        }finally {
            try{
                serverSocket.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    //CREACION DE METODOS ESTATICOS PARA USAR EN EL MENU

    //metodo para mostrar todos los libros cuando creamos uno nuevo

    private static void mostrarLibros(PrintWriter escribir) {
        if (libros.isEmpty()) {
            escribir.println("No hay libros en la lista.");
        } else {
            for (Libro libro : libros) {
                escribir.println(libro);
            }
        }
    }

    //este metodo es el encargado de buscar un libro por su ISBN.
    //se llama al metodo buscarPorISBN y si el libro existe lo escribe

    private static void consultarPorISBN(String isbn, PrintWriter output) {
        Libro libro = buscarPorISBN(isbn);
        if (libro != null) {
            output.println(libro);
        } else {
            output.println("No encuentro el libro con ISBN: " + isbn);
        }
    }

    //este metodo consulta libro por su titulo.
    //le pido que llame al metodo buscarPorTitulo y si lo encuentra le pido una lista con los libros
    //la lista es por que pueden haber dos titulos iguales, pero diferente isbn

    private static void consultarPorTitulo(String titulo, PrintWriter output) {
        List<Libro> encontrados = buscarPorTitulo(titulo);
        if (!encontrados.isEmpty()) {
            for (Libro libro : encontrados) {
                output.println(libro);
            }
        } else {
            output.println("No hay libros con ese titulo: " + titulo);
        }
    }

    //este metodo es igual que el anterior, misma funcionalidad pero consultando por autor

    private static void consultarPorAutor(String autor, PrintWriter output) {
        List<Libro> encontrados = buscarPorAutor(autor);
        if (!encontrados.isEmpty()) {
            for (Libro libro : encontrados) {
                output.println(libro);
            }
        } else {
            output.println("No hay libros de ese autor: " + autor);
        }
    }

    //este metodo es para añadir un libro a la lista

    private static void añadirLibro(String isbn, String titulo, String autor, double precio, PrintWriter output) {
        libros.add(new Libro(isbn, titulo, autor, precio));
        output.println("Libro añadido con exito.");
    }

    //Los siguientes metodos estaticos son para buscar en la lista de libros
    //Hacemos que recorra la lista y si lo encuentra que nos lo devuelva
    //estos metodos se han creado para hacer el codigo mas limpio


    private static Libro buscarPorISBN(String isbn) {
        for (Libro libro : libros) {
            if (libro.getISBN().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    private static List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> encontrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitle().equalsIgnoreCase(titulo)) {
                encontrados.add(libro);
            }
        }
        return encontrados;
    }

    private static List<Libro> buscarPorAutor(String autor) {
        List<Libro> encontrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getAuthor().equalsIgnoreCase(autor)) {
                encontrados.add(libro);
            }
        }
        return encontrados;
    }
}