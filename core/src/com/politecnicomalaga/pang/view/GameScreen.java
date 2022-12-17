package com.politecnicomalaga.pang.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.pang.manager.AssetsManager;
import com.politecnicomalaga.pang.manager.ScreensManager;
import com.politecnicomalaga.pang.model.Balls;
import com.politecnicomalaga.pang.model.Player;

import java.util.ArrayList;


/**
 * GameScreen Class. Where we play the game and we have the main battle
 * Created by Andrés Alcaraz Rey on 5/11/2022.
 */
public class GameScreen implements Screen {
    private final Stage stage;
    private final Game game;
    private final Balls myBall;
    private final Array<Balls> myBalls;
    private final Player playerOne;
    private final Music gameMusic, explodeMusic;
    private final Image myFondo;

    public GameScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

//        wallPaper fondo = new wallPaper(0, 0, AssetsManager.GAME_BACKGROUND);
        myFondo = new Image(new Texture(AssetsManager.GAME_BACKGROUND));
//        stage.addActor(fondo);
        stage.addActor(myFondo);

        playerOne = new Player(stage);
        stage.addActor(playerOne);

        myBall = new Balls(500, 500, 75, stage);
        myBalls = new Array<>();
        myBalls.add(myBall);
        stage.addActor(myBall);

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.GAME_MUSIC));
        explodeMusic = Gdx.audio.newMusic(Gdx.files.internal(AssetsManager.EXPLODE_MUSIC));
        gameMusic.setLooping(true);
        gameMusic.play();
    }

    @Override
    public void show() {
        Gdx.app.log("MainScreen", "show");
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();

        if (playerOne.Morir(myBalls)) {
            game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.GAMEOVER_SCREEN));
        }

        Balls rota = playerOne.chocoBolas(myBalls);
        if (rota != null) {
            explodeMusic.play();
            if (rota.getRadio() > 15) {
                Balls aux = new Balls(rota.getPosX(), rota.getPosY(), rota.getRadio() / 2, stage);
                aux.setVelX(-rota.getVelX());
                aux.setVelY(rota.getVelY());

                rota.setRadio(rota.getRadio() / 2);
                this.stage.addActor(aux);
                this.myBalls.add(aux);
            } else {
                rota.remove();
                myBalls.removeValue(rota, true);
            }
        }

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        gameMusic.pause();
    }

    @Override
    public void resume() {
        gameMusic.play();
    }

    @Override
    public void hide() {
        gameMusic.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        gameMusic.dispose();
        explodeMusic.dispose();
    }

}
