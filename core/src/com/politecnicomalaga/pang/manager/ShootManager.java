package com.politecnicomalaga.pang.manager;

import com.politecnicomalaga.pang.model.Bullet;

import java.util.ArrayList;

public class ShootManager {

    private final ArrayList<Bullet> listBullet = new ArrayList<>();
    private final ArrayList<Bullet> listBulletDead = new ArrayList<>();
    private static ShootManager singleton;

    public static ShootManager getSingleton() {
        if (singleton == null) {
            singleton = new ShootManager();
        }
        return singleton;
    }

    public Bullet shootCreate(float newPosX, float newPosY) {
        Bullet nuevo;
        saveShoot(newPosX, newPosY);
        nuevo = listBullet.get(listBullet.size() - 1);
        if (listBullet.get(0).getPosY() > SettingsManager.SCREEN_HEIGHT) {
            deadShoot(listBullet.get(0), 0);
        }
        return nuevo;
    }

    public void deadShoot(Bullet shoot, int i) {
        shoot.setPosX(shoot.getPosX());
        shoot.setPosY(shoot.getPosY());
        listBulletDead.add(shoot);
        listBullet.remove(i);
        shoot.remove();
    }

    public void saveShoot(float newPosX, float newPosY) {
        if (listBulletDead.isEmpty()) {
            listBullet.add(new Bullet(newPosX, newPosY));
        } else {
            listBullet.add(listBulletDead.get(0));
            listBulletDead.remove(0);
        }
        listBullet.get(listBullet.size() - 1).setPosX(newPosX);
        listBullet.get(listBullet.size() - 1).setPosY(newPosY);
        listBullet.get(listBullet.size() - 1).getMyShoot().set(newPosX, newPosY, SettingsManager.sizeBulletWIDTH, SettingsManager.sizeBulletHEIGHT);
    }

    public ArrayList<Bullet> getListBullet() {
        return listBullet;
    }

}
