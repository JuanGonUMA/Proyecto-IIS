package com.rpgumastudios.game;

public class Jugador extends Entidad {

    private int vida;
    private final int minX = 0;
    private final int minY = 0;
    private final int maxX = 1024;
    private final int maxY = 900;

    public Jugador(int vidaInicial, float posXInicial, float posYInicial) {
        super(posXInicial, posYInicial, 50, 50, "jugador.png");
        this.vida = vidaInicial;
    }

    public void reducirVida(int cantidad) {
        vida -= cantidad;
        if (vida < 0) {
            vida = 0;
        }
    }

    public void moverX(float cantidad) {
        float nuevaPosX = getX() + cantidad;
        if (nuevaPosX < minX) {
            nuevaPosX = minX;
        } else if (nuevaPosX > maxX - getWidth()) {
            nuevaPosX = maxX - getWidth();
        }
        setX(nuevaPosX);
    }

    public void moverY(float cantidad) {
        float nuevaPosY = getY() + cantidad;
        if (nuevaPosY < minY) {
            nuevaPosY = minY;
        } else if (nuevaPosY > maxY - getHeight()) {
            nuevaPosY = maxY - getHeight();
        }
        setY(nuevaPosY);
    }

    // Getters y setters

    public int getSalud() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
