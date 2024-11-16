//Creo la clase producto con el fin de almacenar y devolver los detalles del producto
//Para esto necesito los metodos necesarios

public class Producto {
    //datos de la clase producto
    private String nombre;
    private int stock;
    private double precio;

    //constructor
    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }
    //metodos get
    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }
    //toString
    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }
}
