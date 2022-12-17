package com.politecnicomalaga.pang;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.pang.manager.GameManager;
import com.politecnicomalaga.pang.manager.ScreensManager;
import com.politecnicomalaga.pang.manager.SettingsManager;

public class GdxPang extends Game {
    SpriteBatch batch;
    OrthographicCamera camera;
    Screen myScreen;
    ScreensManager myScreenManager;
    GameManager myGame;

    @Override
    public void create() {
        batch = new SpriteBatch();
        myScreenManager = ScreensManager.getSingleton();
        myGame = GameManager.getSingleton();
        myScreen = myScreenManager.getScreen(this, ScreensManager.SCREENS.START_SCREEN);
        this.setScreen(myScreen);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        this.getScreen().render(myGame.additionGameTime());
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}