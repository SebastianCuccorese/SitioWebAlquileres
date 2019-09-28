public class GeoLoc {
    private String pais;
    private String ciudad;
    private String direccion;

    @Override
    public String toString() {
        return "GeoLoc{" +
                "pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }
}
