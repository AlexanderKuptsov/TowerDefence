package Towers;

import Data.Enemy;
import Data.EnemyType;
import Graphics.Tile;
import Helpers.Sound;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by shurik on 18.05.2017.
 */
public class TowerFlameThrower extends Tower {

    public TowerFlameThrower(TowerType type, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, enemies);
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileFire(super.type.projectileType, super.target,
                super.getX(), super.getY(), 64, 64));

        Sound.playSound(super.getSound()).setVolume(0.75f);
    }
}
