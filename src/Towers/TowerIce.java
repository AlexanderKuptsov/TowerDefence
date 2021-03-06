package Towers;

import Enemies.Enemy;
import Helpers.Sound;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 14.05.2017.
 */
public class TowerIce extends Tower {

    private Sound sound;

    public TowerIce(TowerType type, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, enemies);
        this.sound = super.getSound();
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileIceBall(super.type.projectileType, super.target,
                super.getX() + TILE_SIZE / 2 - TILE_SIZE / 4,
                super.getY() + TILE_SIZE / 2 - TILE_SIZE / 4,
                TILE_SIZE / 2, TILE_SIZE / 2,
                ProjectileType.IceBall.getSpecialEffects()[0],    // slowDuration
                ProjectileType.IceBall.getSpecialEffects()[1]));  // slowEffect

        Sound.playSound(sound);
    }
}
