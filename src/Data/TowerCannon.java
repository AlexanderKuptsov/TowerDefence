package Data;

import Data.Main.Game;
import Graphics.Tile;
import Graphics.TileGrid;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Helpers.Artist.*;
import static Helpers.Clock.*;
import static Graphics.TileGrid.*;

import Graphics.TileType;

/**
 * Created by shurik on 01.05.2017.
 */
public class TowerCannon {

    private float x, y, timeSinceLastShot, firingRate, angle;
    private int width, height, damage, range;
    private Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Enemy> enemies;
    private Enemy target;
    private boolean targeted;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
        this.baseTexture = baseTexture;
        this.cannonTexture = quickLoad("cannonGun");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = (int) startTile.getWidth();
        this.height = (int) startTile.getHeight();
        this.damage = damage;
        this.range = range;
        this.firingRate = 1;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<Projectile>();
        this.enemies = enemies;
        this.targeted = false;
    }

    private Enemy acquireTarget() {
        Enemy closest = null;
        float closestDistance = 10000;
        for (Enemy e : enemies) {
            if (isInRange(e) && findDistance(e) < closestDistance) {
                closestDistance = findDistance(e);
                closest = e;
            }
        }
        if (closest != null) targeted = true;
        return closest;
    }

    private boolean isInRange(Enemy e) {
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2)) < range;
    }

    private float findDistance(Enemy e) {
        float xDistance = Math.abs(e.getX() - x);
        float yDistance = Math.abs(e.getY() - y);
        return (float) Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    private float calculateAngle() {
        double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
        return (float) Math.toDegrees(angleTemp) - 90;
    }

    private void shoot() {
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(quickLoad("rocket"), target, x + TILE_SIZE / 2 - TILE_SIZE / 4,
                y + TILE_SIZE / 2 - TILE_SIZE / 4, 32, 32, 500, 10));
    }

    public void updateEnemyList(ArrayList<Enemy> newList) {
        enemies = newList;
    }

    public void update() {
        if (!targeted) {
            target = acquireTarget();
        }

        if (target == null || !target.isAlive()) targeted = false;

        if (targeted && isInRange(target)) {
            timeSinceLastShot += delta();
            if (timeSinceLastShot > firingRate) shoot();

            for (Projectile p : projectiles) p.update();

            angle = calculateAngle();
        } else targeted = false;
        draw();
    }

    public void draw() {
        drawQuadTexture(baseTexture, x, y, width, height);
        drawQuadTextureRotation(cannonTexture, x, y, width, height, angle);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}