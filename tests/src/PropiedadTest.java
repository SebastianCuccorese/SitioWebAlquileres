import alquileres.web.Propiedad;
import alquileres.web.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropiedadTest {
    Usuario juan;
    Usuario roberto;
    Propiedad depto;
    Propiedad casa;
    @BeforeEach
    void setUp() {
        juan = new Usuario("Juan", "Juan.@gmail.com", 344755412);
        roberto = new Usuario("Roberto", "Roberto@Outlook.com", 4450012);
        depto = new Propiedad("Departamento", "Mar del Plata", "Agua, Luz, Gas, Internet", 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
        casa = new Propiedad("Casa", "Ciudad del cabo", "Agua, Luz, Gas, Internet", 6, LocalTime.of(7,20), LocalTime.of(22,30), 2000, roberto);

    }

    @org.junit.jupiter.api.Test
    void getTipo() {
        Assertions.assertEquals(depto.getTipo(), "Departamento");
    }

    @org.junit.jupiter.api.Test
    void getCiudad() {
        Assertions.assertEquals(depto.getCiudad(), "Mar del Plata");
    }

    @org.junit.jupiter.api.Test
    void getServicios() {
        Assertions.assertEquals(casa.getServicios(), "Agua, Luz, Gas, Internet");
    }

    @org.junit.jupiter.api.Test
    void getCapacidad() {
    	int capacidad = casa.getCapacidad();
        Assertions.assertEquals(capacidad, 6);
    }

    @org.junit.jupiter.api.Test
    void getHorarioEntrada() {
        Assertions.assertEquals(depto.getHorarioEntrada(),LocalTime.of(7, 20));
    }

    @org.junit.jupiter.api.Test
    void getHorarioSalida() {
        Assertions.assertEquals(casa.getHorarioSalida(), LocalTime.of(22, 30));
    }

    @org.junit.jupiter.api.Test
    void getPrecio() {
    	int precio = casa.getPrecio();
        Assertions.assertEquals(precio, 2000);
    }

    @org.junit.jupiter.api.Test
    void getPropietario() {
        Assertions.assertEquals(depto.getPropietario(), juan);
    }
}