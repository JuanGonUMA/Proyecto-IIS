package com.rpgumastudios.game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JugadorTest {
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador();
    }

    @Test
    public void testInicializacionJugador() {
        assertEquals(100, jugador.getSalud(), "La salud inicial del jugador debe ser 100.");
    }

    @Test
    public void testReducirSalud() {
        jugador.reducirSalud(30);
        assertEquals(70, jugador.getSalud(), "La salud del jugador debe ser 70 después de reducir 30.");
    }

    @Test
    public void testReducirSaludPorDebajoDeCero() {
        jugador.reducirSalud(150);
        assertTrue(jugador.getSalud() < 0, "La salud del jugador debe ser negativa si se reduce por debajo de cero.");
    }
}