package Enemies;

import Data.ResourceLoader;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 25.05.2017.
 */
public enum EnemyType {

    Tank(quickLoadEnemy("tankNavyRight"), quickLoadEnemy("tankNavyLeft"),
            quickLoadEnemy("tankNavyUp"), quickLoadEnemy("tankNavyDown"),
            60, 85, 9, true),

    Plane(quickLoadEnemy("planeRight"), quickLoadEnemy("planeLeft"),
            quickLoadEnemy("planeUp"), quickLoadEnemy("planeDown"),
            75, 75, 8, false),

    UFO(quickLoadEnemy("UFO2"), quickLoadEnemy("UFO2"),
            quickLoadEnemy("UFO2"), quickLoadEnemy("UFO2"),
            35, 140, 11, false),

    BigTank(quickLoadEnemy("tank1Right"), quickLoadEnemy("tank1Left"),
            quickLoadEnemy("tank1Up"), quickLoadEnemy("tank1Down"),
            50, 100, 9, true),

    SOLDIER(quickLoadEnemy("soldierRight"), quickLoadEnemy("soldierLeft"),
            quickLoadEnemy("soldierUp"), quickLoadEnemy("soldierDown"),
            80, 21, 3, false);

    private Texture texture, textureLeft, textureUp, textureDown;
    private int speed, health, earnings;
    private boolean rotated;

    EnemyType(Texture texture, Texture textureLeft, Texture textureUp, Texture textureDown,
              int speed, int health, int earnings, boolean rotated) {
        this.texture = texture;
        this.textureLeft = textureLeft;
        this.textureUp = textureUp;
        this.textureDown = textureDown;
        this.speed = speed;
        this.health = health;
        this.earnings = earnings;
        this.rotated = rotated;
    }

    private static Texture quickLoadEnemy(String name) {
        return ResourceLoader.ENEMIES_TEXTURES.get(name);
    }

    public Texture getTexture() {
        return texture;
    }

    public Texture getTextureLeft() {
        return textureLeft;
    }

    public Texture getTextureUp() {
        return textureUp;
    }

    public Texture getTextureDown() {
        return textureDown;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getEarnings() {
        return earnings;
    }

    public boolean isRotated() {
        return rotated;
    }
}
