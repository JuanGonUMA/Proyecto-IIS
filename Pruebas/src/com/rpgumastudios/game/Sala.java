package com.rpgumastudios.game;

import java.util.ArrayList;
import java.util.List;

public class Sala {

    private List<Entidad> entidades;

    public Sala() {
        entidades = new ArrayList<>();
    }

    public void addEntidad(Entidad entidad) {
        entidades.add(entidad);
    }

    public List<Entidad> getEntidades() {
        return entidades;
    }
}
