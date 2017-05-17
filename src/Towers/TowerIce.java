package Towers;

import Data.Enemy;
import Graphics.Tile;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 14.05.2017.
 */
public class TowerIce extends Tower {

    public TowerIce(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, startTile, enemies);
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileIceBall(super.type.projectileType, super.target,
                super.getX() + TILE_SIZE / 2 - TILE_SIZE / 4,
                super.getY() + TILE_SIZE / 2 - TILE_SIZE / 4,
                TILE_SIZE / 2, TILE_SIZE / 2, 2.5f, 0.5f));
    }
}
