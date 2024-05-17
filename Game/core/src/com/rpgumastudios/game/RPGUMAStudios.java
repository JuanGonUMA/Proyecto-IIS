package com.rpgumastudios.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/*
	Casos de uso que implementaremos:
	1. Moverse
	2. Atacar
	3. Atacar con habilidad
	4. Menú inicial
	5. Abrir menú principal
	6. Interactuar con un objeto del mundo
	7. Interactuar con NPC
	8. Resolver un puzzle
	
	El sketch: luego de que se muestre el menú inicial,aparecemos en una playa y hay un NPC, el cual nos incita explorar una extraña cueva más adelante.
	Podemos abrir el menú principal a modo de pause cuando queramos.
	El jugador seguirá un camino hacia la derecha el cual desemboca en una entrada la cueva.
	El jugador necesitará resolver un puzle en la puerta el cual es un mapa de karnaugh, cuando lo resuelva, podrá pasar a la cueva.
	Una vez en la cueva, se enfrentará a un jefe que supone un pequeño reto, al lograrlo, suelta un cártel con "Gracias por jugar esta pequeña demo" o algo similar.
*/

public class RPGUMAStudios extends ApplicationAdapter {
	SpriteBatch batch;
	Texture runningBackground;
	Texture pantallaInicio;
	private Jugador player;
	private static OrthographicCamera camara;
	private Estado estadoActual;

	@Override
	public void create () {
		pantallaInicio = new Texture("backgroundTest.jpg");
		runningBackground = new Texture("runningBackground.jpg");

		player = new Jugador();
		
		camara = new OrthographicCamera();
		camara.setToOrtho(false, 1024, 900);
		batch = new SpriteBatch();
		
		estadoActual = Estado.INICIO;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		
		if (estadoActual.equals(Estado.INICIO)) {
			dibujarInicio();
		} else {
			dibujarRunning();
		}
		
	}
	
	private void dibujarRunning() {
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			estadoActual = Estado.INICIO;
		}
		camara.update();
		batch.setProjectionMatrix(camara.combined);
		
		batch.begin();
		batch.draw(runningBackground, 0, 0);
		batch.draw(player.getImgJugador(), player.x, player.y);
        batch.end();
        
        player.actualizarPosicion();
		
	}

	private void dibujarInicio() {
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		batch.draw(pantallaInicio, 0, 0);
		batch.end();
		
		if (Gdx.input.isTouched()) {
			estadoActual = Estado.RUNNING;
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
