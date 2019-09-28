package alquileres.entity;

public class Persona {
    private String nombre;
    private String mail;
    private Integer telefono;

    public Persona(String nombre, String mail, Integer telefono) {
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public Integer getTelefono() {
        return telefono;
    }
}
