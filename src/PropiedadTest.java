import org.junit.jupiter.api.BeforeEach;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropiedadTest {
    Propietario juan;
    Propietario roberto;
    Propiedad depto;
    Propiedad casa;
    @BeforeEach
    void setUp() {
        juan = new Propietario("Juan", "Juan.@gmail.com", 344755412);
        roberto = new Propietario("Roberto", "Roberto@Outlook.com", 4450012);
        depto = new Propiedad("Departamento", "Mar del Plata", "Agua, Luz, Gas, Internet", 2, LocalTime.of(7,20), LocalTime.of(22,30), 500, juan);
        casa = new Propiedad("Casa", "Ciudad del cabo", "Agua, Luz, Gas, Internet", 6, LocalTime.of(7,20), LocalTime.of(22,30), 2000, roberto);

    }

    @org.junit.jupiter.api.Test
    void getTipo() {
        assertEquals(depto.getTipo(), "Departamento");
    }

    @org.junit.jupiter.api.Test
    void getCiudad() {
        assertEquals(depto.getCiudad(), "Mar del Plata");
    }

    @org.junit.jupiter.api.Test
    void getServicios() {
        assertEquals(casa.getServicios(), "Agua, Luz, Gas, Internet");
    }

    @org.junit.jupiter.api.Test
    void getCapacidad() {
        assertEquals(casa.getCapacidad(), 6);
    }

    @org.junit.jupiter.api.Test
    void getHorarioEntrada() {
        assertEquals(depto.getHorarioEntrada(),LocalTime.of(7, 20));
    }

    @org.junit.jupiter.api.Test
    void getHorarioSalida() {
        assertEquals(casa.getHorarioSalida(), LocalTime.of(22, 30));
    }

    @org.junit.jupiter.api.Test
    void getPrecio() {
        assertEquals(casa.getPrecio(), 2000);
    }

    @org.junit.jupiter.api.Test
    void getPropietario() {
        assertEquals(depto.getPropietario(), juan);
    }
}