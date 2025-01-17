package modelo;

public class Ciclos implements java.io.Serializable {

    
    private static final long serialVersionUID = 8746280847929530572L;
    private int id;
    private String nombre;

    public Ciclos() {
    }

    public Ciclos(int id) {
        this.id = id;
    }

    public Ciclos(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "Ciclo{id=" + id + ", nombre='" + nombre + "'}"; // Sin acceso a relaciones.
    }

}