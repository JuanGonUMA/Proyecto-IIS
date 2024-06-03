package com.rpgumastudios.game;

public abstract class Entidad {

    private float x;
    private float y;
    private float width;
    private float height;
    private String texturePath;

    public Entidad(float x, float y, float width, float height, String texturePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texturePath = texturePath;
    }

    public String getTexturePath() {
        return texturePath;
    }

    // Métodos getter y setter para obtener información sobre la posición y el tamaño de la entidad

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
