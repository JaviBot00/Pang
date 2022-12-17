package com.politecnicomalaga.pang.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsManager {
    //Constantes
    public static final String PLAYER_SPRITES = "andando";
    public static final String PLAYERIZQ_SPRITES = "andandoIZQ";
    public static final String PLAYER_SHOT = "disparo";
    public static final String ATLAS_FILE = "pang.atlas";
    public static final String BIG_FONT = "big-black";

    public static final String START_BACKGROUND = "screens/imgStart.png";
    public static final String GAME_BACKGROUND = "screens/imgGame.png";
    public static final String END_BACKGROUND = "screens/imgEnd.png";

    public static final String INTRO_MUSIC = "sounds/intro_pang.wav";
    public static final String GAME_MUSIC = "sounds/jugando1_pang.wav";
    public static final String DEAD_MUSIC = "sounds/morir_pang.wav";
    public static final String EXPLODE_MUSIC = "sounds/explotar.wav";

    private static Skin textSkin;

    public static Skin getTextSkin() {
        if (textSkin == null) {
            textSkin = new Skin(Gdx.files.internal("glassy-ui.json"));
        }
        return textSkin;
    }
}
