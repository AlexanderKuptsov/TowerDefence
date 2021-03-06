package Towers;

import Enemies.Enemy;
import Data.Entity;
import Helpers.Clock;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 01.05.2017.
 */
public abstract class Projectile implements Entity {

    ProjectileType type;
    private Texture texture;
    private float x, y, speed, damage, xVelocity, yVelocity;
    private int width, height;
    private Enemy target;
    private boolean alive;

    public Projectile(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        this.type = type;
        this.texture = type.texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = type.getSpeed();
        this.damage = type.getDamage();
        this.target = target;
        this.alive = true;
        this.xVelocity = 0f;
        this.yVelocity = 0f;

        calculateDirection();
    }

    private void calculateDirection() {
        float totAllowedMovement = 1.0f;
        float xDistanceFromTarget = Math.abs(target.getX() - x - width / 2 + TILE_SIZE / 2);
        float yDistanceFromTarget = Math.abs(target.getY() - y - height / 2 + TILE_SIZE / 2);
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

    public void damage() {
        target.damage(damage);
        alive = false;
    }

    public void checkingHitting() {
        if (checkCollision(x, y, width, height,
                target.getX(), target.getY(), target.getWidth(), target.getHeight())) {
            damage();
        }
    }

    public void autoAiming() {
        calculateDirection();
    }

    public void update() {
        if (alive) {
            autoAiming();
            x += xVelocity * speed * Clock.INSTANCE.delta();
            y += yVelocity * speed * Clock.INSTANCE.delta();
            checkingHitting();

            draw();
        }
    }

    public void draw() {
        drawQuadTextureRotation(texture, x, y, width, height, calculateAngle());
    }

    public Texture getTexture() {
        return texture;
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

    public float getDamage() {
        return damage;
    }

    public Enemy getTarget() {
        return target;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean status) {
        alive = status;
    }


}
