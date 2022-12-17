package com.politecnicomalaga.pang.manager;

import com.badlogic.gdx.Gdx;

//No se ha tocado nada en la primera fase
public class GameManager {
    private float gameTime;
    private static GameManager singleton;

    public static GameManager getSingleton() {
        if (singleton == null) {
            singleton = new GameManager();
        }
        return singleton;
    }

    public float additionGameTime() {
        gameTime += Gdx.graphics.getDeltaTime();
        return gameTime;
    }

    public float getGameTime() {
        return gameTime;
    }
}
