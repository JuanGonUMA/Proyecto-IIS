package com.rpgumastudios.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entidad extends Rectangle {
    private Texture textura;
    
    public Entidad(float x, float y, float width, float height, String texturePath) {
        super(x, y, width, height);
        textura = new Texture(Gdx.files.internal(texturePath));
    }
    
    public Texture getTextura() {
        return textura;
    }
}
