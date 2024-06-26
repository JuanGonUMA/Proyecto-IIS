package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rpgumastudios.game.Jugador;
import com.rpgumastudios.game.Piedra;
import com.rpgumastudios.game.RPGUMAStudios;
import com.rpgumastudios.game.Sala;

import java.util.List;

public class RPGUMAStudiosTest {
    private RPGUMAStudios juego;

    @BeforeEach
    public void setUp() {
        juego = new RPGUMAStudios();
    }

    @Test
    public void testInicializacionJugador() {
        Jugador jugador = juego.getPlayer();
        assertNotNull(jugador);
        assertEquals(100, jugador.getSalud());
        assertEquals(50, jugador.getX());
        assertEquals(50, jugador.getY());
    }

    @Test
    public void testInicializacionSalas() {
        List<Sala> salas = juego.getSalas();
        assertNotNull(salas);
        assertEquals(2, salas.size());

        Sala salaActual = juego.getSalaActual();
        assertNotNull(salaActual);
        assertTrue(salas.contains(salaActual));
    }

    @Test
    public void testReiniciarJuego() {
        Jugador jugadorInicial = juego.getPlayer();
        Sala salaInicial = juego.getSalaActual();

        juego.reiniciarJuego();

        Jugador jugadorReiniciado = juego.getPlayer();
        Sala salaReiniciada = juego.getSalaActual();

        assertNotSame(jugadorInicial, jugadorReiniciado);
        assertNotSame(salaInicial, salaReiniciada);
    }
    
    @Test
    public void testCambiarSala() {
        Sala salaInicial = juego.getSalaActual();
        List<Sala> salas = juego.getSalas();
        Sala otraSala = null;
        for (Sala sala : salas) {
            if (sala != salaInicial) {
                otraSala = sala;
                break;
            }
        }

        assertNotNull(otraSala);

        juego.cambiarSala(otraSala);
        assertNotSame(salaInicial, juego.getSalaActual());
        assertEquals(otraSala, juego.getSalaActual());
    }

    @Test
    public void testAgregarEntidadSala() {
        Sala sala = juego.getSalaActual();
        int cantidadEntidadesAntes = sala.getEntidades().size();
        Piedra piedra = new Piedra(200, 200);
        sala.addEntidad(piedra);
        assertEquals(cantidadEntidadesAntes + 1, sala.getEntidades().size());
        assertTrue(sala.getEntidades().contains(piedra));
    }
}
