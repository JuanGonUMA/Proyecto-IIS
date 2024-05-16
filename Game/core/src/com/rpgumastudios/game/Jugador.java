package com.rpgumastudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Jugador extends Rectangle {
    private int vidaMaxima = 10;
    private Texture imgJugador;
    
    public Jugador() {
        // Llama al constructor de la superclase (Rectangle)
        super();
        // Configura los atributos del jugador
        this.x = 500;
        this.y = 500;
        this.width = 50;
        this.height = 50;
        // Carga la textura del jugador
        imgJugador = new Texture(Gdx.files.internal("test.png"));
    }
    
    public Texture getImgJugador() {
    	return imgJugador;
    }

	public void actualizarPosicion() {
		if (Gdx.input.isKeyPressed(Keys.A)) {
			this.x -= 200 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			this.x += 200 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			this.y += 200 * Gdx.graphics.getDeltaTime();
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			this.y -= 200 * Gdx.graphics.getDeltaTime();
		}
	}
}
