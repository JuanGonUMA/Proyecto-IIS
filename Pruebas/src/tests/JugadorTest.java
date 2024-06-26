package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.rpgumastudios.game.Jugador;

public class JugadorTest {

    // Se evalúa que el jugador tenga la vida inicial correcta al crearse
    @Test
    public void testVidaInicial() {
        Jugador jugador = new Jugador(100, 500, 500);
        assertEquals(100, jugador.getSalud());
    }

    // Se evalúa que la vida del jugador se reduzca correctamente
    @Test
    public void testReducirVida() {
        Jugador jugador = new Jugador(100, 500, 500);
        jugador.reducirVida(20);
        assertEquals(80, jugador.getSalud());
    }

    // Se evalúa que la posición X del jugador se actualice correctamente al moverse hacia la izquierda
    @Test
    public void testMoverXIzquierda() {
        Jugador jugador = new Jugador(100, 500, 500);
        jugador.moverX(-50);
        assertEquals(450, jugador.getX());
    }

    // Se evalúa que la posición X del jugador se actualice correctamente al moverse hacia la derecha
    @Test
    public void testMoverXDerecha() {
        Jugador jugador = new Jugador(100, 500, 500);
        jugador.moverX(50);
        assertEquals(550, jugador.getX());
    }

    // Se evalúa que la posición Y del jugador se actualice correctamente al moverse hacia arriba
    @Test
    public void testMoverYArriba() {
        Jugador jugador = new Jugador(100, 500, 500);
        jugador.moverY(50);
        assertEquals(550, jugador.getY());
    }

    // Se evalúa que la posición Y del jugador se actualice correctamente al moverse hacia abajo
    @Test
    public void testMoverYAbajo() {
        Jugador jugador = new Jugador(100, 500, 500);
        jugador.moverY(-50);
        assertEquals(450, jugador.getY());
    }
}
