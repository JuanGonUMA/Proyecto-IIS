package com.rpgumastudios.game;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Array;

public class Enemigo extends Entidad implements Steerable<Vector2> {
    private int vida;
    private float velocidadMaxima;
    private float velocidadActual;
    private boolean tagged;
    private float maxLinearAcceleration;
    private float maxAngularAcceleration;
    private float maxAngularSpeed;
    private float maxLinearSpeed;
    private Vector2 position;
    private Vector2 linearVelocity;
    private float angularVelocity;
    private float boundingRadius;
    private float zeroLinearSpeedThreshold;
    private Array<Entidad> entidades;

    private static final float TIME_BETWEEN_ATTACKS = 2.0f; // Tiempo entre ataques en segundos
    private float timeSinceLastAttack = 0;

    public Enemigo(float x, float y, String texturePath) {
        super(x, y, 50, 50, texturePath);
        this.vida = 100;
        this.position = new Vector2(x, y);
        this.linearVelocity = new Vector2();
        this.maxLinearSpeed = 100;
        this.maxLinearAcceleration = 200;
        this.boundingRadius = 25;
        this.zeroLinearSpeedThreshold = 0.1f;
    }

    private float tiempoQuietoDespuesAtaque = 0f;

    public Enemigo(float x, float y, String texturePath, Array<Entidad> entidades) {
        super(x, y, 50, 50, texturePath);
        this.vida = 100;
        this.position = new Vector2(x, y);
        this.linearVelocity = new Vector2();
        this.maxLinearSpeed = 100;
        this.maxLinearAcceleration = 200;
        this.boundingRadius = 25;
        this.zeroLinearSpeedThreshold = 0.1f;
        this.entidades = entidades;
    }

    public void actualizar(float delta, Jugador jugador, boolean dialogoActivo) {
    	if (!dialogoActivo) {
	        if (tiempoQuietoDespuesAtaque > 0) {
	            tiempoQuietoDespuesAtaque -= delta;
	            return; // El enemigo está quieto después del ataque
	        }
	
	        timeSinceLastAttack += delta;
	
	        if (calcularDistancia(jugador) <= 75 && timeSinceLastAttack >= TIME_BETWEEN_ATTACKS) {
	            atacar(jugador);
	            timeSinceLastAttack = 0;
	            tiempoQuietoDespuesAtaque = 1f; // El enemigo se quedará quieto por 1 segundo después del ataque
	        } else {
	            perseguirJugador(jugador, delta);
	        }
	
	        // Actualizar posición del enemigo
	        setPosition(position.x, position.y);
    	}
    }

    private void atacar(Jugador jugador) {
        jugador.reducirSalud(35);
    }

    private void perseguirJugador(Jugador jugador, float delta) {
        Vector2 direccion = new Vector2(jugador.x - this.x, jugador.y - this.y).nor();
        float velocidad = maxLinearSpeed * delta;

        float nuevaX = this.x + direccion.x * velocidad;
        float nuevaY = this.y + direccion.y * velocidad;
        boolean puedeMoverX = true;
        boolean puedeMoverY = true;

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

        // Actualizar la posición en el vector de posición
        this.position.set(this.x, this.y);
    }

    private float calcularDistancia(Jugador jugador) {
        return Vector2.dst(this.x, this.y, jugador.x, jugador.y);
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public float getOrientation() {
        return 0;
    }

    @Override
    public void setOrientation(float orientation) {}

    @Override
    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    @Override
    public float getAngularVelocity() {
        return angularVelocity;
    }

    @Override
    public float getBoundingRadius() {
        return boundingRadius;
    }

    @Override
    public boolean isTagged() {
        return tagged;
    }

    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    public Vector2 newVector() {
        return new Vector2();
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float) Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -MathUtils.sin(angle);
        outVector.y = MathUtils.cos(angle);
        return outVector;
    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return zeroLinearSpeedThreshold;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float zeroLinearSpeedThreshold) {
        this.zeroLinearSpeedThreshold = zeroLinearSpeedThreshold;
    }

	@Override
	public Location<Vector2> newLocation() {
		return null;
	}
}
