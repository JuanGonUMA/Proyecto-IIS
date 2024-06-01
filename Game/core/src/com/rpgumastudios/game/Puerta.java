package com.rpgumastudios.game;

public class Puerta extends Entidad {
    private Sala salaDestino;

    public Puerta(float x, float y, float width, float height, String texturePath, Sala salaDestino) {
        super(x, y, width, height, texturePath);
        this.salaDestino = salaDestino;
    }

    public Sala getSalaDestino() {
        return salaDestino;
    }
}