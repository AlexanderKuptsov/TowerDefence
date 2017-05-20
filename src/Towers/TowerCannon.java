package Towers;

import Data.Enemy;
import Graphics.Tile;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 12.05.2017.
 */
public class TowerCannon extends Tower {

    public TowerCannon(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, startTile, enemies);
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileRocket(super.type.projectileType, super.target,
                super.getX() + TILE_SIZE / 2 - TILE_SIZE / 2,
                super.getY() + TILE_SIZE / 2 - TILE_SIZE / 2, TILE_SIZE, TILE_SIZE));
    }

}