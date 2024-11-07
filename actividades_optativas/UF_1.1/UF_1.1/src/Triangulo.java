import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Triangulo {
    public static void main(String[] args) {
        //verificamos si se han pasado argumentos
        if (args.length == 0)
        {
            System.out.println("Se requiere un argumento");
            return;
        }
        //Se declara variable y convertimos argumento (String --> entero)
        int filas = Integer.parseInt(args[0]);

        //fecha de inicio

        LocalDateTime inicio = LocalDateTime.now();
        System.out.println("la fecha de inicio del proceso es: " + inicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //bucle para contar filas en orden decreciente
        for (int i=filas; i>=1; i--)
        {//bucle para contar numeros respecto al numero de filas en orden ascendente
            for (int n=1; n<=i; n++)
            {
                System.out.print(n);
            }
            System.out.println();
        }

        //fecha de fin
        LocalDateTime fin = LocalDateTime.now();
        System.out.println("la fecha de fin del proceso es: " + fin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}


