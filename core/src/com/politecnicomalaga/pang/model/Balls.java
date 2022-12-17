package com.politecnicomalaga.pang.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Balls extends Actor {
    //Atributos
    //Hay que representarlo mediante la textura adecuada y el cuerpo "Circle"

    private final Stage stage;
    private float posX;
    private float posY;
    //velocidades
    private float velX;
    private float velY;
    //aceleración SOLO Y
    private final float acelY;
    //radio
    private float radio;

    private final Texture img;

    private final Circle myEnemy;

    //Constructor
    public Balls(float newPosX, float newPosY, float newRadio, Stage baseStage) {
        super();
        //Hay que crear las bolas (sólo es necesario instanciar una) donde nos digan
        //pero la acelY es siempre 0.2 y la velY es 0 al principio.
        //posiciones
        this.posX = newPosX;
        this.posY = newPosY;
        this.velX = 4f;
        this.velY = 0f;
        this.acelY = 0.225f;
        this.radio = newRadio;
        this.img = new Texture("sprites/circuloAzul.png");
        this.stage = baseStage;
        this.myEnemy = new Circle(posX, posY, radio);
    }

    //Comportamiento - Métodos
    public void Moverse() {
        posX += velX;
        posY += velY;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //Vamos a necesitar como atributos una velX que es constante, pero + o - y cambia
        //al tocar las paredes.
        if (posX <= 0) {
            this.velX = Math.abs(velX);
        }
        if (posX >= Gdx.graphics.getWidth() - (this.radio * 2)) {
            this.velX = -velX;
        }
        //También una velY que empieza siendo 0. Pero que cuando actua el actor, cambia a
        //velY = velY - acelY
        if (posY <= 20) {
//            this.velY = Math.abs(velY + acelY);  // Con Rozamiento
            this.velY = radio * acelY;
        } else {
            this.velY = velY - acelY;
        }
        //Hay que cambiar la velocidad si la bola llega al suelo (20 pixeles en Y). La velocidad
        //debe ser velY=radio * 0.10 - 6
        Moverse();
        this.myEnemy.set(posX, posY, radio);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        //Aquí dibujamos la texture de la bola con respecto a su radio
        batch.draw(img, posX, posY, radio * 2, radio * 2);
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public Circle getMyEnemy() {
        return myEnemy;
    }
}
