package com.politecnicomalaga.pang.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.politecnicomalaga.pang.manager.AssetsManager;
import com.politecnicomalaga.pang.manager.GameManager;
import com.politecnicomalaga.pang.manager.SettingsManager;

public class Bullet extends Actor {

    private float posX;
    private float posY;
    private final Animation<TextureRegion> skin;
    private final Rectangle myShoot;

    public Bullet(float newPosX, float newPosY) {
        super();
        this.posX = newPosX;
        this.posY = newPosY;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILE));
        skin = new Animation<TextureRegion>(0.25f, atlas.findRegions(AssetsManager.PLAYER_SHOT), Animation.PlayMode.LOOP);
        this.myShoot = new Rectangle(posX, posY, SettingsManager.sizeBulletWIDTH, SettingsManager.sizeBulletHEIGHT);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.myShoot.set(posX, posY, SettingsManager.sizeBulletWIDTH, SettingsManager.sizeBulletHEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        float x = posX + SettingsManager.PLAYER_CENTER - SettingsManager.allyBulletCenter;
        float y = posY + SettingsManager.PLAYER_HEIGHT;
        if (posY < SettingsManager.SCREEN_HEIGHT) {
            this.posY += SettingsManager.speedBullet;
            batch.draw(currentFrame, x, y, SettingsManager.sizeBulletWIDTH, SettingsManager.sizeBulletHEIGHT);
        }
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Rectangle getMyShoot() {
        return myShoot;
    }
}