import java.util.ArrayList;
import java.util.List;

public class Propietarios extends Personas {
    private List<Propiedades> listaPropiedades = new ArrayList<>();

    public Propietarios(String nombre, String mail, Integer telefono) {
        super(nombre, mail, telefono);
    }

    protected void ponerPropiedadEnAlquiler(Propiedades propiedad) {
            listaPropiedades.add(propiedad);
    }
    protected List getListaPropiedades() {
        return listaPropiedades;
    }
}
