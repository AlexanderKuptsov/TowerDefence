package Data;

import org.newdawn.slick.opengl.Texture;

import java.util.Map;

import static Helpers.Clock.*;
import static Helpers.Artist.*;

/**
 * Created by shurik on 01.05.2017.
 */
public class Projectile {

    private Texture texture;
    private float x, y, speed, xVelocity, yVelocity;
    private int damage;
    private Enemy target;

    public Projectile(Texture texture, Enemy target, float x, float y, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.xVelocity = 0f;
        this.yVelocity = 0f;

        calculateDirection();
    }

    private void calculateDirection() {
        float totAllowedMovement = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX() - x);
        float yDistanceFromTarget = Math.abs(target.getY() - y);
        float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
        float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
        xVelocity = xPercentOfMovement;
        yVelocity = totAllowedMovement - xPercentOfMovement;
        if (target.getX() < x) xVelocity *= -1;
        if (target.getY() < y) yVelocity *= -1;
    }

    public void update() {
        x += xVelocity * speed * delta();
        y += yVelocity * speed * delta();
        draw();
    }

    public void draw() {
        DrawQuadTexture(texture, x, y, 32, 32);
    }
}
