package Data;

import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 25.05.2017.
 */
public enum EnemyType {

    Tank(quickLoad("tankNavy"), quickLoad("tankNavyLEFT"), quickLoad("tankNavyUP"), quickLoad("tankNavyDOWN"),
            55, 90),
    Plane(quickLoad("PlaneRight"), quickLoad("PlaneLeft"), quickLoad("PlaneUP"), quickLoad("PlaneDown"),
            70, 80),
    UFO(quickLoad("UFO2"), quickLoad("UFO2"), quickLoad("UFO2"), quickLoad("UFO2"),
            30, 135);

    private Texture texture, textureLeft, textureUp, textureDown;
    private int speed, health;

    EnemyType(Texture texture, Texture textureLeft, Texture textureUp, Texture textureDown, int speed, int health) {
        this.texture = texture;
        this.textureLeft = textureLeft;
        this.textureUp = textureUp;
        this.textureDown = textureDown;
        this.speed = speed;
        this.health = health;
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
}
