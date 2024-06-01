package com.rpgumastudios.game;

import com.badlogic.gdx.utils.Array;

public class NPC extends Entidad {
    private Array<String> dialogos;
    private int indiceDialogoActual;

    public NPC(float x, float y, String texturePath, Array<String> dialogos) {
        super(x, y, 50, 50, texturePath);  // Suponiendo que el NPC tiene un tamaño de 50x50
        this.dialogos = dialogos;
        this.indiceDialogoActual = 0;
    }

    public Array<String> getDialogos() {
        return dialogos;
    }

    public String getSiguienteDialogo() {
        if (indiceDialogoActual < dialogos.size) {
            String dialogo = dialogos.get(indiceDialogoActual);
            indiceDialogoActual++;
            return dialogo;
        } else {
            return null; // No hay más diálogo
        }
    }
}
