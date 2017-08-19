package Enemies;

import Data.Entity;
import Data.ResourceLoader;
import Effects.Blood;
import Graphics.Tile;
import Graphics.TileGrid;
import Helpers.Clock;
import Helpers.Sound;
import Towers.ProjectileType;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 29.04.2017.
 */
public class Enemy implements Entity {
    private int width, height, earnings, angle;
    private float x, y, speed, startSpeed, startHealth, health, slowDuration, slowEffect;
    private Texture texture, healthBackGround, healthForeGround, healthBorder, lowHpTexture;
    private Tile startTile, currentTile;
    private boolean first, unrealDirection, alive, slowed;
    private TileGrid grid;
    private ProjectileType projectileType;

    private EnemyType enemyType;
    private Texture enemyRight;
    private Texture enemyLeft;
    private Texture enemyUp;
    private Texture enemyDown;

    private Sound sound;
    private Blood blood;
    private int[] currentDirection;

    private float timeSinceLastMiniSpawn, miniSpawnTime;
    private int enemiesPerMiniWave, miniEnemiesSpawned;
    private boolean miniWaveStarted, newMiniEnemy;

    private final int HEALTH_LABEL_HEIGHT = TILE_SIZE / 8;
    private final int PRE_GAP = TILE_SIZE / 8;
    private static final float LOW_HEALTH = 0.3f;

    public Enemy(EnemyType enemyType, int tileX, int tileY, TileGrid grid) {
        this.enemyType = enemyType;
        this.texture = enemyType.getTexture();
        this.healthBackGround = ResourceLoader.UI_TEXTURES.get("healthBackGround");
        this.healthForeGround = ResourceLoader.UI_TEXTURES.get("healthForeGround");
        this.healthBorder = ResourceLoader.UI_TEXTURES.get("healthBorder");
        this.lowHpTexture = ResourceLoader.PROJECTILE_TEXTURES.get("fire");
        this.startTile = grid.getTile(tileX, tileY);
        this.currentTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = TILE_SIZE;
        this.height = TILE_SIZE;
        this.speed = enemyType.getSpeed();
        this.startSpeed = speed;
        this.health = enemyType.getHealth();
        this.startHealth = health;
        this.grid = grid;
        this.first = true;
        this.alive = true;
        this.angle = 180;
        this.earnings = enemyType.getEarnings();
        this.slowDuration = 0;
        this.slowed = false;
        this.newMiniEnemy = false;

        // miniEnemy
        this.miniSpawnTime = 1.3f;
        this.enemiesPerMiniWave = 3;
        this.miniEnemiesSpawned = 0;
        this.timeSinceLastMiniSpawn = miniSpawnTime;
        this.miniWaveStarted = false;
        this.newMiniEnemy = false;

        this.enemyRight = enemyType.getTexture();
        this.enemyLeft = enemyType.getTextureLeft();
        this.enemyUp = enemyType.getTextureUp();
        this.enemyDown = enemyType.getTextureDown();

        this.sound = ResourceLoader.SOUNDS_PACK.get("lose1.wav");
        this.blood = new Blood(x, y);
        this.currentDirection = new int[2];

        this.currentDirection[0] = 0; // x direction
        this.currentDirection[1] = 0; // y direction

        unrealDirection = false;
        currentDirection = findNextDirection(startTile);
    }

    public void update() {
        if (newRouteRequired()) {
            currentDirection = findNextDirection(currentTile);
            if (unrealDirection) endOfMazeReached();
            else {
                x = currentTile.getX();
                y = currentTile.getY();
            }
        } else {
            if (currentDirection[0] == 1) {
                texture = enemyRight;
                if (enemyType.isRotated()) angle = 180;
            } else if (currentDirection[0] == -1) {
                texture = enemyLeft;
                if (enemyType.isRotated()) angle = 180;
            } else if (currentDirection[1] == -1) {
                texture = enemyUp;
                if (enemyType.isRotated()) angle = 90;
            } else if (currentDirection[1] == 1) {
                texture = enemyDown;
                if (enemyType.isRotated()) angle = -90;
            }
            x += Clock.INSTANCE.delta() * currentDirection[0] * speed;
            y += Clock.INSTANCE.delta() * currentDirection[1] * speed;
        }

        if (slowed) {
            if (slowDuration > 0) {
                speed = startSpeed * slowEffect;
                slowDuration -= Clock.INSTANCE.delta();
            } else {
                speed = startSpeed;
                slowDuration = projectileType.getSpecialEffects()[0];
                slowed = false;
            }
        }

        if (getHealthPercent() < 0.25f && enemyType == EnemyType.BigTank) {
            miniWaveStarted = true;
            speed = startSpeed * 0.3f;
        }
        if (miniWaveStarted) {
            if (miniEnemiesSpawned < enemiesPerMiniWave) {
                timeSinceLastMiniSpawn += Clock.INSTANCE.delta();
                if (timeSinceLastMiniSpawn > miniSpawnTime) {
                    newMiniEnemy = true;
                    timeSinceLastMiniSpawn = 0;
                }
            } else miniWaveStarted = false;
        }
    }

    public void slowEffect(ProjectileType type, float slowDuration, float slowEffect) {
        this.projectileType = type;
        this.slowed = true;
        this.slowDuration = slowDuration;
        this.slowEffect = slowEffect;
    }

    private void endOfMazeReached() {
        die();
        modifyLives(-1);
        Sound.playSound(sound);
    }

    private boolean newRouteRequired() { //AI
        currentTile = getCurrentTile();
        Tile nextTile = grid.getTile(currentTile.getXPlace() + currentDirection[0],
                currentTile.getYPlace() + currentDirection[1]);
        int delta = TILE_SIZE + PRE_GAP;
        int deltaXY = 0;
        if (currentDirection[0] == -1) deltaXY = (int) Math.abs(nextTile.getX() - x);
        if (currentDirection[1] == -1) deltaXY = (int) Math.abs(nextTile.getY() - y);
        return currentTile.getType() != nextTile.getType() && deltaXY <= delta;
    }

    private int[] findNextDirection(Tile start) { // AI
        int[] dir = new int[2];
        Tile up = grid.getTile(start.getXPlace(), start.getYPlace() - 1);
        Tile right = grid.getTile(start.getXPlace() + 1, start.getYPlace());
        Tile down = grid.getTile(start.getXPlace(), start.getYPlace() + 1);
        Tile left = grid.getTile(start.getXPlace() - 1, start.getYPlace());

        if (start.getType() == up.getType() && currentDirection[1] != 1) {
            dir[0] = 0;
            dir[1] = -1;
            //x += PRE_GAP;
        } else if (start.getType() == right.getType() && currentDirection[0] != -1) {
            dir[0] = 1;
            dir[1] = 0;
        } else if (start.getType() == down.getType() && currentDirection[1] != -1) {
            dir[0] = 0;
            dir[1] = 1;
        } else if (start.getType() == left.getType() && currentDirection[0] != 1) {
            dir[0] = -1;
            dir[1] = 0;
            // y += PRE_GAP;
        } else {
            dir[0] = 2;
            dir[1] = 2;
            unrealDirection = true;
        }
        return dir;
    }

    private Tile getCurrentTile() {
        return grid.getTile((int) (x / TILE_SIZE), (int) (y / TILE_SIZE));
    }

    public void damage(float amountOfDamage) {
        health -= amountOfDamage;
        if (health <= 0) {
            die();
            modifyCash(earnings);
        }
    }

    private void die() {
        if (enemyType == EnemyType.SOLDIER) {
            blood.setX(x);
            blood.setY(y);
            blood.setBleeding(true);
        } else {
            if (enemyType != EnemyType.BigTank) currentDirection = null;
        }
        alive = false;
    }

    private float getHealthPercent() {
        return health / startHealth;
    }

    public void draw() {
        float healthPercent = getHealthPercent();
        drawQuadTexture(texture, x, y, width, height);
        drawQuadTexture(healthBackGround, x, y - TILE_SIZE / 4, width, HEALTH_LABEL_HEIGHT);
        drawQuadTexture(healthForeGround, x, y - TILE_SIZE / 4, TILE_SIZE * healthPercent, HEALTH_LABEL_HEIGHT);
        drawQuadTexture(healthBorder, x, y - TILE_SIZE / 4, width, HEALTH_LABEL_HEIGHT);

        if (enemyType != EnemyType.SOLDIER)
            if (healthPercent < LOW_HEALTH) {
                final int size = (int) (TILE_SIZE * 1.5);
                drawQuadTextureRotation(lowHpTexture,
                        x + TILE_SIZE / 2 - size / 2, y + TILE_SIZE / 2 - size / 2, size, size, angle);

            }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getStartHealth() {
        return startHealth;
    }

    public void setStartHealth(float startHealth) {
        this.startHealth = startHealth;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getStartSpeed() {
        return startSpeed;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(String textureName) {
        this.texture = quickLoad(textureName);
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public TileGrid getTileGrid() {
        return grid;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isNewMiniEnemy() {
        return newMiniEnemy;
    }

    public void setNewMiniEnemy(boolean newMiniEnemy) {
        this.newMiniEnemy = newMiniEnemy;
    }

    public int getMiniEnemiesSpawned() {
        return miniEnemiesSpawned;
    }

    public void setMiniEnemiesSpawned(int miniEnemiesSpawned) {
        this.miniEnemiesSpawned = miniEnemiesSpawned;
    }

    public void setEnemyRight(String textureName) {
        this.enemyRight = quickLoad(textureName);
    }

    public void setEnemyLeft(String textureName) {
        this.enemyLeft = quickLoad(textureName);
    }

    public void setEnemyUp(String textureName) {
        this.enemyUp = quickLoad(textureName);
    }

    public void setEnemyDown(String textureName) {
        this.enemyDown = quickLoad(textureName);
    }

    public TileGrid getGrid() {
        return grid;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public int[] getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int[] currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Blood getBlood() {
        return blood;
    }
}