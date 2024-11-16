
//Clase Libro con sus atributos

public class Libro {
    private String ISBN;
    private String title;
    private String author;
    private double price;
//constructor
    public Libro(String ISBN, String title, String author, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.price = price;
    }
//metodos get
    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }
//metodo toString
    @Override
    public String toString() {
        return "Libro{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
