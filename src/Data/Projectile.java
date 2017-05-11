package Data;

import Data.Main.Game;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Clock.*;
import static Helpers.Artist.*;

/**
 * Created by shurik on 01.05.2017.
 */
public class Projectile implements Entity {

    private Texture texture;
    private float x, y, speed, xVelocity, yVelocity;
    private int damage, width, height;
    private Enemy target;
    private boolean alive;

    public Projectile(Texture texture, Enemy target, float x, float y, int width, int height, float speed, int damage) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.alive = true;
        this.xVelocity = 0f;
        this.yVelocity = 0f;

        calculateDirection();
    }

    private void calculateDirection() {
        float totAllowedMovement = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX() - x - TILE_SIZE / 4 + TILE_SIZE / 2);
        float yDistanceFromTarget = Math.abs(target.getY() - y - TILE_SIZE / 4 + TILE_SIZE / 2);
        float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
        float xPercentOfMovement = xDistanceFromTarget / totalDistanceFromTarget;
        xVelocity = xPercentOfMovement;
        yVelocity = totAllowedMovement - xPercentOfMovement;
        if (target.getX() < x) xVelocity *= -1;
        if (target.getY() < y) yVelocity *= -1;
    }

    private float calculateAngle() {
        double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(angleTemp) - 90;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void update() {
        if (alive) {
            calculateDirection();
            x += xVelocity * speed * delta();
            y += yVelocity * speed * delta();

            if (checkCollision(x, y, width, height,
                    target.getX(), target.getY(), target.getWidth(), target.getHeight())) {
                target.damage(damage);
                alive = false;
            }
            draw();
        }
    }

    public void draw() {
        drawQuadTextureRotation(texture, x, y, 32, 32, calculateAngle());
    }
}
