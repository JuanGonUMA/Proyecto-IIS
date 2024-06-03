package com.rpgumastudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Jugador extends Entidad {
    private int salud;
    private final int saludMaxima = 100;

    public Jugador() {
        super(500, 500, 50, 50, "jugador.png");
        this.salud = saludMaxima;
    }

    public void actualizarPosicion(Array<Entidad> entidades, boolean dialogoActivo) {
        if (!dialogoActivo) {
            float velocidad = 200 * Gdx.graphics.getDeltaTime();
            float nuevaX = this.x;
            float nuevaY = this.y;
            boolean puedeMoverX = true;
            boolean puedeMoverY = true;

            // Verificar movimiento horizontal
            if (Gdx.input.isKeyPressed(Keys.A)) {
                nuevaX -= velocidad;
            }
            if (Gdx.input.isKeyPressed(Keys.D)) {
                nuevaX += velocidad;
            }

            // Verificar movimiento vertical
            if (Gdx.input.isKeyPressed(Keys.W)) {
                nuevaY += velocidad;
            }
            if (Gdx.input.isKeyPressed(Keys.S)) {
                nuevaY -= velocidad;
            }

            // Verificar límites horizontales
            if (nuevaX < 0) {
                nuevaX = 0;
            }
            if (nuevaX + this.width > 1024) {  // Suponiendo que el ancho del mundo es 1024
                nuevaX = 1024 - this.width;
            }

            // Verificar límites verticales
            if (nuevaY < 0) {
                nuevaY = 0;
            }
            if (nuevaY + this.height > 900) {  // Suponiendo que la altura del mundo es 900
                nuevaY = 900 - this.height;
            }

            // Crear nuevas posiciones para verificar colisiones
            Rectangle nuevaPosicionX = new Rectangle(nuevaX, this.y, this.width, this.height);
            Rectangle nuevaPosicionY = new Rectangle(this.x, nuevaY, this.width, this.height);

            // Verificar colisiones con entidades para movimiento horizontal
            for (Entidad entidad : entidades) {
                if (!(entidad instanceof Puerta) && !entidad.equals(this) && nuevaPosicionX.overlaps(entidad)) {
                    puedeMoverX = false;
                    break;
                }
            }

            // Verificar colisiones con entidades para movimiento vertical
            for (Entidad entidad : entidades) {
                if (!(entidad instanceof Puerta) && !entidad.equals(this) && nuevaPosicionY.overlaps(entidad)) {
                    puedeMoverY = false;
                    break;
                }
            }

            // Actualizar posición basada en las colisiones detectadas
            if (puedeMoverX) {
                this.x = nuevaX;
            }
            if (puedeMoverY) {
                this.y = nuevaY;
            }
        }
    }

    public void reducirSalud(int cantidad) {
        this.salud -= cantidad;
    }

    public int getSalud() {
        return salud;
    }
}
