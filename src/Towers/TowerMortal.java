package Towers;

import Data.ResourceLoader;
import Enemies.Enemy;
import Helpers.Sound;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.*;

/**
 * Created by shurik on 31.05.2017.
 */
public class TowerMortal extends Tower {

    private int rotation;
    private Sound sound;

    private final int POSITION_DELTA = TILE_SIZE / 2 - (int) (TILE_SIZE * 0.75);
    private final int SIZE = (int) (TILE_SIZE * 1.5);

    public TowerMortal(TowerType type, CopyOnWriteArrayList<Enemy> enemies) {
        super(type, enemies);
        this.rotation = 0;
        this.sound = new Sound(TowerType.Mortal.soundName);
        sound.setVolume(0.69f);
        // this.sound = ResourceLoader.SOUNDS_PACK.get(type.soundName);
    }

    @Override
    public void shoot(Enemy target) {
        super.projectiles.add(new ProjectileShuriken(super.type.projectileType, super.target, super.getEnemies(),
                super.getX() + POSITION_DELTA, super.getY() + POSITION_DELTA, SIZE, SIZE));
    }

    @Override
    public void draw() {
        drawQuadTexture(super.getTextures()[0], super.getX(), super.getY(), super.getWidth(), super.getHeight());
        drawQuadTextureRotation(super.getTextures()[1], super.getX(), super.getY(),
                super.getWidth(), super.getHeight(), super.getAngle());
        int ROTATION_DELTA = 12;
        rotation += ROTATION_DELTA;
        drawQuadTextureRotation(super.getTextures()[2], super.getX(), super.getY(),
                super.getWidth(), super.getHeight(), rotation);
        if (rotation % 90 == 0) sound.play();
    }
}
