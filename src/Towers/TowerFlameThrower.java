package Towers;

import Data.Enemy;
import Graphics.Tile;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 18.05.2017.
 */
public class TowerFlameThrower extends Tower {

    public TowerFlameThrower(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, startTile, enemies);
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileFire(super.type.projectileType, super.target,
                super.getX(), super.getY(), 64, 64));
    }

}
