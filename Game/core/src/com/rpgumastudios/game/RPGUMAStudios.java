package com.rpgumastudios.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

/*
	Casos de uso implementados:
	1. Menú inicial
	2. Menú principal
	3. Moverse.
	4. Interactuar con un objeto del mundo
	5. Interactuar con NPC 
*/

public class RPGUMAStudios extends ApplicationAdapter {
    SpriteBatch batch;
    Texture runningBackground;
    Texture pantallaInicio;
    private Jugador player;
    private static OrthographicCamera camara;
    private static Estado estadoActual;
    private Sala salaActual;
    private Array<Sala> salas;
    private boolean dialogoActivo = false;
    private String textoDialogo = "";
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private int indiceTexto = 0;
    private Array<String> textosDialogo = new Array<>();
    private BitmapFont fontGrande;

    @Override
    public void create() {
        inicializarJuego();
    }

    private void inicializarJuego() {
        if (pantallaInicio == null) {
            pantallaInicio = new Texture("backgroundTest.jpg");
        }
        if (runningBackground == null) {
            runningBackground = new Texture("runningBackground.jpg");
        }

        player = new Jugador();

        camara = new OrthographicCamera();
        camara.setToOrtho(false, 1024, 900);
        batch = new SpriteBatch();

        font = new BitmapFont();
        font.setColor(Color.WHITE);

        shapeRenderer = new ShapeRenderer();

        fontGrande = new BitmapFont();  // Inicializa fontGrande
        fontGrande.getData().setScale(2);  // Ajusta el tamaño de la fuente
        fontGrande.setColor(Color.WHITE);

        estadoActual = Estado.INICIO;

        salas = new Array<>();

        Sala sala1 = new Sala();
        sala1.addEntidad(player);
        sala1.addEntidad(new Piedra(300, 300));
        sala1.addEntidad(new Piedra(100, 100));

        Array<String> dialogosNPC1 = new Array<>();
        dialogosNPC1.add("¡Hola, jugador! Bienvenido. El caso de uso de hablar con los NPC te permite presionar la tecla espacio para pasar al siguiente texto.");
        dialogosNPC1.add("Mientras hables con un NPC, no es posible acceder al menú principal.");
        dialogosNPC1.add("Y una vez un NPC se quede sin más texto en su diálogo, su cuadro de diálogo se cerrará.");
        
        sala1.addEntidad(new NPC(700, 700, "npc.png", dialogosNPC1));
        sala1.addEntidad(new Enemigo(600, 600, "enemigo.png", sala1.getEntidades()));

        Sala sala2 = new Sala();
        sala2.addEntidad(new Piedra(400, 400));

        Puerta puerta1 = new Puerta(500, 500, 50, 50, "puerta.png", sala2);
        sala1.addEntidad(puerta1);

        Puerta puerta2 = new Puerta(200, 200, 50, 50, "puerta.png", sala1);
        sala2.addEntidad(puerta2);
        sala2.addEntidad(new Enemigo(300, 300, "enemigo.png", sala2.getEntidades()));

        salas.add(sala1);
        salas.add(sala2);

        salaActual = sala1;
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);

        if (estadoActual.equals(Estado.INICIO)) {
            dibujarInicio();
        } else if (estadoActual.equals(Estado.MENU_PRINCIPAL)) {
            dibujarMenuPrincipal();
        } else {
            dibujarRunning();
        }
    }

    private void dibujarInicio() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(pantallaInicio, 0, 0);
        batch.end();

        if (Gdx.input.isTouched()) {
            estadoActual = Estado.RUNNING;
        }
    }

    private void dibujarRunning() {
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) && !dialogoActivo) {
            estadoActual = Estado.MENU_PRINCIPAL;
        }
        camara.update();
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(runningBackground, 0, 0);
        for (Entidad entidad : salaActual.getEntidades()) {
            batch.draw(entidad.getTextura(), entidad.x, entidad.y);
        }

        font.draw(batch, "Salud: " + player.getSalud(), 10, 890);
        batch.end();

        if (textoDialogo == null) {
            dialogoActivo = false;
        }

        player.actualizarPosicion(salaActual.getEntidades(), dialogoActivo);

        Array<Entidad> entidadesCopia = new Array<>(salaActual.getEntidades());

        for (Entidad entidad : entidadesCopia) {
            if (entidad instanceof NPC && calcularDistancia(player, entidad) <= 75) {
                if (Gdx.input.isKeyJustPressed(Keys.E) && !dialogoActivo) {
                    NPC npc = (NPC) entidad;
                    dialogoActivo = true;
                    textosDialogo = npc.getDialogos();
                    indiceTexto = 0;
                    textoDialogo = textosDialogo.get(indiceTexto);
                }
            } else if (entidad instanceof Puerta && calcularDistancia(player, entidad) <= 75) {
                if (Gdx.input.isKeyJustPressed(Keys.E)) {
                    Puerta puerta = (Puerta) entidad;
                    cambiarSala(puerta.getSalaDestino());
                    break;
                }
            } else if (entidad instanceof Enemigo) {
                ((Enemigo) entidad).actualizar(Gdx.graphics.getDeltaTime(), player, dialogoActivo);
            }
        }

        if (dialogoActivo) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.rect(50, 50, 924, 200);
            shapeRenderer.end();

            batch.begin();
            if (textoDialogo != null && !textoDialogo.isEmpty()) {
                font.draw(batch, textoDialogo, 100, 200);
            }
            batch.end();

            if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
                indiceTexto++;
                if (indiceTexto >= textosDialogo.size) {
                    dialogoActivo = false;
                    textoDialogo = "";
                } else {
                    textoDialogo = textosDialogo.get(indiceTexto);
                }
            }
        }
    }

    private void dibujarMenuPrincipal() {
        ScreenUtils.clear(Color.BLACK);

        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        String titulo = "Menú Principal";
        String opcion1 = "1. Continuar";
        String opcion2 = "2. Salir";
        String opcion3 = "3. Volver al Menú Inicial";

        float centerX = 1024 / 2;

        // Crear un objeto BitmapFont para el título con tamaño más grande
        BitmapFont fontTitulo = new BitmapFont();
        fontTitulo.getData().setScale(3);  // Escalar la fuente del título a un tamaño mayor
        fontTitulo.setColor(Color.WHITE);

        // Dibujar el título con la fuente más grande
        fontTitulo.draw(batch, titulo, centerX - fontTitulo.getRegion().getRegionWidth() / 2, 600);

        // Dibujar las opciones con la fuente original
        fontGrande.draw(batch, opcion1, centerX - fontGrande.getRegion().getRegionWidth() / 2, 500);
        fontGrande.draw(batch, opcion2, centerX - fontGrande.getRegion().getRegionWidth() / 2, 400);
        fontGrande.draw(batch, opcion3, centerX - fontGrande.getRegion().getRegionWidth() / 2, 300);

        batch.end();

        if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
            estadoActual = Estado.RUNNING;
        } else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
            Gdx.app.exit();
        } else if (Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
            estadoActual = Estado.INICIO;
        }

        // Liberar los recursos del objeto BitmapFont creado para el título
        fontTitulo.dispose();
    }

    private void cambiarSala(Sala nuevaSala) {
        salaActual = nuevaSala;
        if (!salaActual.getEntidades().contains(player, true)) {
            salaActual.addEntidad(player);
        }
    }

    private float calcularDistancia(Entidad entidad1, Entidad entidad2) {
        float centroX1 = entidad1.x + entidad1.width / 2;
        float centroY1 = entidad1.y + entidad1.height / 2;
        float centroX2 = entidad2.x + entidad2.width / 2;
        float centroY2 = entidad2.y + entidad2.height / 2;
        return (float) Math.sqrt(Math.pow(centroX2 - centroX1, 2) + Math.pow(centroY2 - centroY1, 2));
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (pantallaInicio != null) {
            pantallaInicio.dispose();
        }
        if (runningBackground != null) {
            runningBackground.dispose();
        }
        font.dispose();
        shapeRenderer.dispose();
        if (salas != null) {
            for (Sala sala : salas) {
                for (Entidad entidad : sala.getEntidades()) {
                    entidad.getTextura().dispose();
                }
            }
        }
    }

    public static void cambiarEstado(Estado nuevoEstado) {
        estadoActual = nuevoEstado;
    }
}

