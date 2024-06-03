package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rpgumastudios.game.NPC;

import java.util.ArrayList;

public class NPCTest {

    private NPC npc;

    @BeforeEach
    public void setUp() {
        ArrayList<String> dialogos = new ArrayList<>();
        dialogos.add("¡Hola, jugador! Bienvenido.");
        dialogos.add("¿Cómo estás hoy?");
        npc = new NPC(100, 100, "npc.png", dialogos);
    }

    @Test
    public void testConstructor() {
        assertNotNull(npc);
    }

    @Test
    public void testGetDialogos() {
        ArrayList<String> dialogos = npc.getDialogos();
        assertEquals(2, dialogos.size());
    }

    @Test
    public void testSiguienteDialogo() {
        assertEquals("¡Hola, jugador! Bienvenido.", npc.getSiguienteDialogo());
        assertEquals("¿Cómo estás hoy?", npc.getSiguienteDialogo());
        assertNull(npc.getSiguienteDialogo());
    }

    @Test
    public void testPosicionInicial() {
        assertEquals(100, npc.getX());
        assertEquals(100, npc.getY());
    }

}
