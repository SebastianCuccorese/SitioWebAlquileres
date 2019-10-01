package alquileres.entity;

abstract public class Usuario {
    private String nombre;
    private String mail;
    private Integer telefono;

    public Usuario(String nombre, String mail, Integer telefono) {
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
