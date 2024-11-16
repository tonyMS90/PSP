import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.TreeMap;

public class HiloEscuchador implements Runnable{
    private Thread hilo;
    private static int numCliente = 0;
    private Socket enchufeAlCliente;

    //añado TreeMap para modificar el constructor para poder recibir los prodcutos
    private TreeMap<String, Producto> productos;

    //modificación del constructor añadiendo TreeMap
    public HiloEscuchador(Socket cliente, TreeMap<String, Producto> productos) {
        numCliente++;
        hilo = new Thread(this, "Cliente"+numCliente);
        this.enchufeAlCliente = cliente;
        this.productos = productos;
        hilo.start();
    }

    @Override
    public void run() {
        System.out.println("Estableciendo comunicación con " + hilo.getName());
        try {
            InputStream entrada = enchufeAlCliente.getInputStream();
            OutputStream salida = enchufeAlCliente.getOutputStream();

            //modificamos el String de texto y lo llamamos codigo, ya que es lo que se pide
            String codigo = "";
            while (!codigo.trim().equals("FIN")) {
                byte[] mensaje = new byte[2];
                int leido = entrada.read(mensaje);
                if(leido == -1)break;//el cliente cierra la conexion
                codigo = new String(mensaje);
                if (codigo.equals("FIN")) {
                    salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
                    System.out.println(hilo.getName()+" ha cerrado la comunicación");
                } else {
                    //si el codigo es diferente de FIN, añademe el codigo del producto y con if anidado
                    //te especifico que si es diferente de null, me escribes el producto que has encontrado
                    //y si no, me dices que no lo has encontrado
                    Producto producto = productos.get(codigo);
                    if (producto != null){
                        salida.write(("producto encontrado: " + producto.toString()).getBytes());
                    }
                    else{
                        salida.write("el producto no se ha podido encontrar".getBytes());
                    }
                }
            }

            //cerramos todo y creamos la excepcion correspondiente
            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
