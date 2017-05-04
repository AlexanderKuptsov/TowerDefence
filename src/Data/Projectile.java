package Data;

import org.newdawn.slick.opengl.Texture;

import static Helpers.Clock.*;
import static Helpers.Artist.*;

/**
 * Created by shurik on 01.05.2017.
 */
public class Projectile {

    private Texture texture;
    private float x, y, speed;
    private int damage;

    public Projectile(Texture texture, float x, float y, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    public void update() {
        x += delta() * speed;
        draw();
    }

    public void draw() {
        DrawQuadTexture(texture, x, y, 32, 32);
    }
}
