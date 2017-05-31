package Data;

import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 25.05.2017.
 */
public enum EnemyType {

    Tank(quickLoad("tankNavy"), quickLoad("tankNavyLEFT"),
            quickLoad("tankNavyUP"), quickLoad("tankNavyDOWN"),
            60, 90, 6),

    Plane(quickLoad("PlaneRight"), quickLoad("PlaneLeft"),
            quickLoad("PlaneUP"), quickLoad("PlaneDown"),
            75, 80, 5),

    UFO(quickLoad("UFO2"), quickLoad("UFO2"),
            quickLoad("UFO2"), quickLoad("UFO2"),
            35, 135, 8);

    private Texture texture, textureLeft, textureUp, textureDown;
    private int speed, health, earnings;

    EnemyType(Texture texture, Texture textureLeft, Texture textureUp, Texture textureDown, int speed, int health, int earnings) {
        this.texture = texture;
        this.textureLeft = textureLeft;
        this.textureUp = textureUp;
        this.textureDown = textureDown;
        this.speed = speed;
        this.health = health;
        this.earnings = earnings;
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
}
