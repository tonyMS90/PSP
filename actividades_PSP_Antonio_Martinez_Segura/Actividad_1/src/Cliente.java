import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//Clase cliente para comunicarse con el servidor

public class Cliente {

    public static void main(String[] args) {
        try  {

            //Socket creado para conectarse al servidor usando su misma direccion y puerto
            Socket socket = new Socket("localhost", 8080);

            //Utilizo BefferedReader para leer y PrintWritter para escribir
            //autoflush para asegurar el mensaje
            BufferedReader leer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter escribir = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in) ;


            //menu  para que el usuario elija la opcion a realizar
            //Uso Scanner para elejir la opcion que prefiera del menu
            String opcion;
            while (true) {
                System.out.println("Menú de opciones:");

                System.out.println("1. Consultar libro por ISBN");
                System.out.println("2. Consultar libro por Título");
                System.out.println("3. Consultar libros por Autor");
                System.out.println("4. Añadir libro");
                System.out.println("5. Salir");

                System.out.print("Elige una opción: ");
                opcion = scanner.nextLine();

                //Se procesan las opciones del menu
                //segun la opcion elejida:
                switch (opcion) {
                    case "1": //escribe el isbn y envialo al servidor para que lo consulte
                        System.out.print("Introduce el ISBN: ");
                        String isbn = scanner.nextLine();
                        escribir.println("consultar_isbn," + isbn);
                        System.out.println(leer.readLine());//lee la respuesta del servidor
                        break;
                    case "2"://escribe el titulo y envialo al servidor
                        System.out.print("Introduce el Título: ");
                        String titulo = scanner.nextLine();
                        escribir.println("consultar_titulo," + titulo);
                        System.out.println(leer.readLine());//lee la respuesta
                        break;
                    case "3"://Escribe el autor y envialo al servidor para que lo consulte
                        System.out.print("Introduce el Autor: ");
                        String autor = scanner.nextLine();
                        escribir.println("consultar_autor," + autor);
                        System.out.println(leer.readLine());
                        break;
                    case "4"://Asigna el libro que quieres añadir cons su valores
                        System.out.print("Introduce el ISBN: ");
                        String nuevoIsbn = scanner.nextLine();
                        System.out.print("Introduce el Título: ");
                        String nuevoTitulo = scanner.nextLine();
                        System.out.print("Introduce el Autor: ");
                        String nuevoAutor = scanner.nextLine();
                        System.out.print("Introduce el Precio: ");
                        double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                        //Envio el nuevo libro al servidor para que lo añada a la lista
                        escribir.println("añadir_libro," + nuevoIsbn + "," + nuevoTitulo + "," + nuevoAutor + "," + nuevoPrecio);
                        System.out.println(leer.readLine());

                        break;
                    case "5": //salimos del bucle y cierro los recursos
                        escribir.println("salir");
                        System.out.println("Saliendo, adios.");
                        leer.close();
                        escribir.close();
                        socket.close();
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error en la comunicacion " + e.getMessage());;
        }
    }
}
