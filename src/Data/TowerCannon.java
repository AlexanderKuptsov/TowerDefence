package Data;

import Graphics.Tile;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Helpers.Artist.*;
import static Helpers.Clock.*;

/**
 * Created by shurik on 01.05.2017.
 */
public class TowerCannon {

    private float x, y, timeSinceLastShot, firingRate;
    private int width, height, damage;
    private Texture baseTexture, cannonTexture;
    private Tile startTile;
    private ArrayList<Projectile> projectiles;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage) {
        this.baseTexture = baseTexture;
        this.cannonTexture = quickLoad("cannonGun");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = (int) startTile.getWidth();
        this.height = (int) startTile.getHeight();
        this.damage = damage;

        this.firingRate = 1;
        this.timeSinceLastShot = 0;
        this.projectiles = new ArrayList<Projectile>();
    }

    private void shoot() {
        timeSinceLastShot = 0;
        projectiles.add(new Projectile(quickLoad("bullet"), x + 32, y + 20, 100, 10));
    }

    public void update() {
        timeSinceLastShot += delta();
        if (timeSinceLastShot > firingRate) shoot();

        for (Projectile p : projectiles) p.update();
        draw();
    }

    public void draw() {
        DrawQuadTexture(baseTexture, x, y, width, height);
        DrawQuadTextureRotation(cannonTexture, x, y, width, height, -90);
    }
}
