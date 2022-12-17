package com.politecnicomalaga.pang.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.politecnicomalaga.pang.manager.AssetsManager;
import com.politecnicomalaga.pang.manager.GameManager;
import com.politecnicomalaga.pang.manager.SettingsManager;
import com.politecnicomalaga.pang.manager.ShootManager;

import java.util.ArrayList;

public class Player extends Actor {

    private float posX;
    private final float posY;
    private float velX;

    private Animation<TextureRegion> skin;
    private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILE));
    private final Stage stage;


    private final ShootManager shooters;
    private float shootTime = 0;

    private final Rectangle myBody;

    public Player(Stage baseStage) {
        super();
        this.posX = (Gdx.graphics.getWidth() / 2f) - SettingsManager.PLAYER_CENTER;
        this.posY = 20f;
        this.velX = 0.0f;
        this.stage = baseStage;
        this.skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetsManager.PLAYER_SPRITES), Animation.PlayMode.LOOP);
        this.myBody = new Rectangle(posX, posY, SettingsManager.PLAYER_WIDTH, SettingsManager.PLAYER_HEIGHT);
        this.shooters = ShootManager.getSingleton();
    }

    public void Disparar() {
        shootTime += 1;
        if (shootTime >= SettingsManager.timeBtwShoots) {
            shootTime -= SettingsManager.timeBtwShoots;
            stage.addActor(shooters.shootCreate(posX, posY));
        }
    }

    public boolean Morir(Array<Balls> myArray) {
        for (Balls myBa : myArray) {
            return Intersector.overlaps(myBa.getMyEnemy(), this.myBody);
        }
        return false;
    }

    public Balls chocoBolas(Array<Balls> myArray) {
        for (Bullet myBu : shooters.getListBullet()) {
            for (Balls myBa : myArray) {
                if (Intersector.overlaps(myBa.getMyEnemy(), myBu.getMyShoot())) {
                    shooters.deadShoot(myBu, shooters.getListBullet().indexOf(myBu));
                    return myBa;
                }
            }
        }
        return null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.myBody.set(posX, posY, SettingsManager.PLAYER_WIDTH, SettingsManager.PLAYER_HEIGHT);
        if (posX <= 0 || posX >= Gdx.graphics.getWidth() - SettingsManager.PLAYER_WIDTH) {
            this.velX = 0;
        }
        if (Gdx.input.justTouched()) {
            int pixelX = Gdx.input.getX();
            float pantalladivididaEntre3 = Gdx.graphics.getWidth() / 3f;
            if (pixelX > (pantalladivididaEntre3 * 2)) {
                skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetsManager.PLAYER_SPRITES), Animation.PlayMode.LOOP);
                this.velX = 2;
            } else if (pixelX < (pantalladivididaEntre3)) {
                skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetsManager.PLAYERIZQ_SPRITES), Animation.PlayMode.LOOP);
                this.velX = -2;
            } else {
                this.velX = 0;
            }
        }
        posX += velX;
        Disparar();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, posX, posY, SettingsManager.PLAYER_WIDTH, SettingsManager.PLAYER_HEIGHT);
    }

    public Rectangle getMyBody() {
        return myBody;
    }
}
