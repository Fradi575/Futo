import Models.Tabla;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TablaTest {


    @Test
    void TestMozgat() {
        Tabla tabla = new Tabla();
        tabla.mozgat(1, 1, 0, 2);
        assertEquals(Tabla.FEKETE, tabla.getMezok()[0][1]);
        assertEquals(Tabla.URES, tabla.getMezok()[1][1]);
    }

    @Test
    void TestSikertelenMozgat() {
        Tabla tabla = new Tabla();
        tabla.mozgat(0, 2, 1, 3);
        // Mivel amit akarnek mozgatni ott nincsen babu, ezert ott 0 kell hogy maradjon
        assertEquals(Tabla.URES, tabla.getMezok()[0][2]);
        assertNotEquals(Tabla.FEHER, tabla.getMezok()[1][3]);
    }

    @Test
    void TestSzabade() {
        Tabla tabla = new Tabla();
        assertTrue(tabla.szabade(1, 1, 0, 2));
    }

    @Test
    void TestNemSZabad() {
        Tabla tabla = new Tabla();
        assertFalse(tabla.szabade(1, 2, 2, 3));
    }

    @Test
    void TestMegoldva() {
        Tabla tabla = new Tabla();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                tabla.setMezo(i, j, Tabla.URES);
            }
        }
        for (int k = 0; k < 4; k++) {
            tabla.setMezo(0, k, Tabla.FEKETE);
            tabla.setMezo(4, k, Tabla.FEHER);
        }
        assertTrue(tabla.megoldva());
    }

    @Test
    void TestNincsMegoldva() {
        Tabla tabla = new Tabla();
        assertFalse(tabla.megoldva());
    }
}
