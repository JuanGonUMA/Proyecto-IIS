package com.rpgumastudios.game;

import java.util.ArrayList;
import java.util.List;

public class RPGUMAStudios {

    private Jugador player;
    private List<Sala> salas;
    private Sala salaActual;

    public RPGUMAStudios() {
        inicializarJuego();
    }

    private void inicializarJuego() {
        player = new Jugador(100, 50, 50);
        salas = new ArrayList<>();

        Sala sala1 = new Sala();
        sala1.addEntidad(player);
        sala1.addEntidad(new Piedra(500, 800));

        ArrayList<String> dialogosNPC1 = new ArrayList<>();
        dialogosNPC1.add("¡Hola, jugador! Bienvenido.");
        dialogosNPC1.add("Mientras hables con un NPC, no es posible acceder al menú principal.");
        dialogosNPC1.add("Y una vez un NPC se quede sin más texto en su diálogo, su cuadro de diálogo se cerrará.");
        
        sala1.addEntidad(new NPC(700, 600, "npc.png", dialogosNPC1));

        Sala sala2 = new Sala();
        sala2.addEntidad(new Piedra(400, 400));

        salas.add(sala1);
        salas.add(sala2);

        salaActual = sala1;
    }

    public void reiniciarJuego() {
        inicializarJuego();
    }

    // Métodos getter y setter para obtener información del juego

    public Jugador getPlayer() {
        return player;
    }

    public Sala getSalaActual() {
        return salaActual;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void cambiarSala(Sala otraSala) {
        salaActual = otraSala;
    }

}
