package Towers;

import Data.Enemy;
import Graphics.Tile;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;
import static Helpers.Artist.drawQuadTexture;
import static Helpers.Artist.drawQuadTextureRotation;

/**
 * Created by shurik on 31.05.2017.
 */
public class TowerMortal extends Tower {

    private float rotation;

    public TowerMortal(TowerType type, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, enemies);
        this.rotation = rotation;
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileShuriken(super.type.projectileType, super.target, super.getEnemies(),
                super.getX() + TILE_SIZE / 2 - (int) (TILE_SIZE * 0.75),
                super.getY() + TILE_SIZE / 2 - (int) (TILE_SIZE * 0.75),
                (int) (TILE_SIZE * 1.5), (int) (TILE_SIZE * 1.5)));
    }

    @Override
    public void draw() {
        drawQuadTexture(super.getTextures()[0], super.getX(), super.getY(), super.getWidth(), super.getHeight());
        drawQuadTextureRotation(super.getTextures()[1], super.getX(), super.getY(),
                super.getWidth(), super.getHeight(), super.getAngle());
        rotation += 12f;
        drawQuadTextureRotation(super.getTextures()[2], super.getX(), super.getY(),
                super.getWidth() , super.getHeight() , rotation);
    }
}
