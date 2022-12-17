package com.politecnicomalaga.pang.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.politecnicomalaga.pang.view.GameScreen;
import com.politecnicomalaga.pang.view.GameoverScreen;
import com.politecnicomalaga.pang.view.StartScreen;

public class ScreensManager extends ScreenAdapter {

    private static ScreensManager singleton;
    private Game game;

    public static ScreensManager getSingleton() {
        if (singleton == null) {
            singleton = new ScreensManager();
        }
        return singleton;
    }

    public enum SCREENS {
        GAME_SCREEN, START_SCREEN, GAMEOVER_SCREEN
    }

    public Screen getScreen(Game game, SCREENS screenToGet) {
        Screen newScreen = null;
        switch (screenToGet) {
            case GAME_SCREEN:
                newScreen = new GameScreen(game);
                break;
            case START_SCREEN:
                newScreen = new StartScreen(game);
                break;
            case GAMEOVER_SCREEN:
                newScreen = new GameoverScreen(game);
                break;
        }
        return newScreen;
    }
}