package com.politecnicomalaga.pang.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.pang.manager.AssetsManager;
import com.politecnicomalaga.pang.manager.ScreensManager;
import com.politecnicomalaga.pang.model.wallPaper;


public class StartScreen implements Screen {

    private final Stage stage;
    private final Game game;
    private final Music introMusic;

    public StartScreen(final Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        wallPaper fondo = new wallPaper(0, 0, AssetsManager.START_BACKGROUND);
        stage.addActor(fondo);

        TextButton StartButton = new TextButton("Start", AssetsManager.getTextSkin());
        StartButton.setWidth(180);
        StartButton.setPosition(400, 10);
        StartButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.GAME_SCREEN));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(StartButton);
        introMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.INTRO_MUSIC));
        introMusic.setLooping(true);
        introMusic.play();
    }

    @Override
    public void show() {
        Gdx.app.log("StartScreen", "show");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        introMusic.stop();
    }

    @Override
    public void resume() {
        introMusic.play();
    }

    @Override
    public void hide() {
        introMusic.stop();
    }

    @Override
    public void dispose() {
        stage.dispose();
        introMusic.dispose();
    }

}