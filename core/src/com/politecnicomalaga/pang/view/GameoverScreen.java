package com.politecnicomalaga.pang.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.pang.manager.AssetsManager;
import com.politecnicomalaga.pang.manager.ScreensManager;
import com.politecnicomalaga.pang.model.wallPaper;

public class GameoverScreen implements Screen {
    private final Stage stage;
    private final Game game;
    private final Music deadMusic;

    public GameoverScreen(final Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        wallPaper fondo = new wallPaper(0, 0, AssetsManager.END_BACKGROUND);
        stage.addActor(fondo);


        //Título
        Label titulo = new Label("Game Over", AssetsManager.getTextSkin(), AssetsManager.BIG_FONT);
        titulo.setAlignment(Align.center);
        titulo.setY(Gdx.graphics.getHeight() - titulo.getHeight() * 2);
        titulo.setWidth(Gdx.graphics.getWidth());
        stage.addActor(titulo);

        //Botón reintentar
        TextButton RetryButton = new TextButton("Vuelve a Empezar", AssetsManager.getTextSkin());
        RetryButton.setWidth(Gdx.graphics.getWidth() / 2f);
        RetryButton.setPosition(Gdx.graphics.getWidth() / 2f - RetryButton.getWidth() / 2, Gdx.graphics.getHeight() - RetryButton.getHeight() * 4);
        RetryButton.addListener(new InputListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.GAME_SCREEN));
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(RetryButton);

        //Botón salir
        TextButton ExitButton = new TextButton("Salir", AssetsManager.getTextSkin());
        ExitButton.setWidth(Gdx.graphics.getWidth() / 2f);
        ExitButton.setPosition(Gdx.graphics.getWidth() / 2f - ExitButton.getWidth() / 2, Gdx.graphics.getHeight() - ExitButton.getHeight() * 6);
        ExitButton.addListener(new InputListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(ExitButton);

        deadMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.DEAD_MUSIC));
        deadMusic.play();
    }

    @Override
    public void show() {

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
        deadMusic.pause();
    }

    @Override
    public void resume() {
        deadMusic.pause();
    }

    @Override
    public void hide() {
        deadMusic.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        deadMusic.dispose();
    }
}
