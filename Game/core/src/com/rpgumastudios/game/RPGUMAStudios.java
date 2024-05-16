package com.rpgumastudios.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
