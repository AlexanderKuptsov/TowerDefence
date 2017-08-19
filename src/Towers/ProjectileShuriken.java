package Towers;

import Data.ResourceLoader;
import Enemies.Enemy;
import Helpers.Sound;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.*;

/**
 * Created by shurik on 31.05.2017.
 */
public class ProjectileShuriken extends Projectile {

    private CopyOnWriteArrayList<Enemy> enemies;
    private int angle;
    private Sound sound;

    public ProjectileShuriken(ProjectileType type, Enemy target, CopyOnWriteArrayList<Enemy> enemies,
                              float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
        this.enemies = enemies;
        this.angle = 0;
        //this.sound = ResourceLoader.SOUNDS_PACK.get(type.soundName);
        this.sound = new Sound(TowerType.Mortal.soundName);
        sound.setVolume(0.9f);
    }

    public void damage(Enemy e) {
        e.damage(super.getDamage());
    }

    @Override
    public void checkingHitting() {
        for (Enemy e : enemies) {
            if (checkCollision(super.getX(), super.getY(), super.getWidth(), super.getHeight(),
                    e.getX(), e.getY(), e.getWidth(), e.getHeight())) {
                damage(e);
            }
        }
    }

    @Override
    public void autoAiming() {
        //no autoAimin
    }

    @Override
    public void update() {
        super.update();
        if (super.getX() > WIDTH - 3 * TILE_SIZE || super.getY() > HEIGHT ||
                super.getX() < -TILE_SIZE || super.getY() < -TILE_SIZE)
            super.setAlive(false);
    }

    public void draw() {
        if (angle % 90 == 0) sound.play();
        int ROTATION_DELTA = 12;
        angle += ROTATION_DELTA;
        if (angle >= 3600) angle = 0;
        drawQuadTextureRotation(super.getTexture(), super.getX(), super.getY(),
                super.getWidth(), super.getHeight(), angle);
    }
}
