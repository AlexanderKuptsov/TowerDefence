package Towers;

import Enemies.Enemy;
import Helpers.Sound;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 12.05.2017.
 */
public class TowerCannon extends Tower {

    private Sound sound;

    public TowerCannon(TowerType type, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, enemies);
        this.sound = super.getSound();
    }

    @Override
    public void shoot(Enemy target) throws NullPointerException {
        super.projectiles.add(new ProjectileRocket(super.type.projectileType, super.target,
                super.getX() + TILE_SIZE / 2 - TILE_SIZE / 2,
                super.getY() + TILE_SIZE / 2 - TILE_SIZE / 2, TILE_SIZE, TILE_SIZE));

        Sound.playSound(sound).setVolume(0.75f);
    }
}