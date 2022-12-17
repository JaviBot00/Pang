package com.politecnicomalaga.pang.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.politecnicomalaga.pang.manager.SettingsManager;

public class wallPaper extends Actor {
    private final Texture skin;

    public wallPaper(float positionX, float positionY, String wallPaper) {
        super();
        this.setBounds(positionX, positionY, SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);
        this.skin = new Texture(wallPaper);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        float x = 0;
        float y = 0;
        batch.draw(skin, x, y, SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);

    }
}
