package Towers;

import Enemies.Enemy;
import Helpers.Sound;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 18.05.2017.
 */
public class TowerFlameThrower extends Tower {

    private Sound sound;

    public TowerFlameThrower(TowerType type, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, enemies);
        this.sound = super.getSound();
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileFire(super.type.projectileType, super.target,
                super.getX(), super.getY(), TILE_SIZE, TILE_SIZE));

        /*if (!super.getSound().isPlaying())*/ Sound.playSound(sound).setVolume(0.75f);
    }
}
