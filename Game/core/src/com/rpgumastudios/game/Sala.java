package com.rpgumastudios.game;

import com.badlogic.gdx.utils.Array;

public class Sala {
    private Array<Entidad> entidades;

    public Sala() {
        entidades = new Array<>();
    }

    public void addEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    public Array<Entidad> getEntidades() {
        return entidades;
    }
}
