package Data;

import Graphics.Tile;
import Graphics.TileGrid;
import org.newdawn.slick.opengl.Texture;

import java.util.ArrayList;

import static Helpers.Artist.*;
import static Helpers.Clock.*;

/**
 * Created by shurik on 29.04.2017.
 */
public class Enemy implements Entity {
    private int width, height, currentCheckpoint;
    private float x, y, speed, startSpeed, startHealth, health;
    private Texture texture, healthBackGround, healthForeGround, healthBorder;
    private Tile startTile;
    private boolean first, alive;
    private TileGrid grid;
    private int angle;

    private ArrayList<Checkpoint> checkpoints;
    private int[] directions;

    public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height, float speed, float health) {
        this.texture = texture;
        this.healthBackGround = quickLoad("healthBackGround");
        this.healthForeGround = quickLoad("healthForeGround");
        this.healthBorder = quickLoad("healthBorder");
        this.startTile = startTile;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.startSpeed = speed;
        this.startHealth = health;
        this.health = health;
        this.grid = grid;
        this.first = true;
        this.alive = true;
        this.angle = 180;

        this.checkpoints = new ArrayList<Checkpoint>();
        this.directions = new int[2];

        this.directions[0] = 0; // x direction
        this.directions[1] = 0; // y direction
        directions = findNextDirection(startTile);
        this.currentCheckpoint = 0;
        populateCheckpointList();
    }

    public void update() {
        if (first) { // is it the first update of the class
            first = false;
        } else {
            if (checkpointReached()) {
                if (currentCheckpoint == checkpoints.size() - 1)
                    /*if (grid.getTile((int) getX() / TILE_SIZE + checkpoints.get(currentCheckpoint).getXDirection()
                            , (int) getY() / TILE_SIZE + checkpoints.get(currentCheckpoint).getYDirection()).getType() ==
                            TileType.Water)*/ endOfMazeReached();
                else currentCheckpoint++;
            } else {

                if (checkpoints.get(currentCheckpoint).getXDirection() == 1) {
                    texture = quickLoad("tankNavy");
                    angle = 180;
                } else if (checkpoints.get(currentCheckpoint).getXDirection() == -1) {
                    texture = quickLoad("tankNavyLEFT");
                    angle = 180;
                } else if (checkpoints.get(currentCheckpoint).getYDirection() == -1) {
                    texture = quickLoad("tankNavyUP");
                    angle = 90;
                } else if (checkpoints.get(currentCheckpoint).getYDirection() == 1) {
                    texture = quickLoad("tankNavyDOWN");
                    angle = -90;
                }

                x += delta() * checkpoints.get(currentCheckpoint).getXDirection() * speed;
                y += delta() * checkpoints.get(currentCheckpoint).getYDirection() * speed;
            }
        }
    }

    private void endOfMazeReached() {
        die();
        Player.modifyLives(-1);
    }

    private boolean checkpointReached() {
        boolean reached = false;
        Tile t = checkpoints.get(currentCheckpoint).getTile();
        // проверка заранее (не доходя немного)
        if (x > t.getX() - 3 && x < t.getX() + 3 && y > t.getY() - 3 && y < t.getY() + 3) {
            reached = true;
            x = t.getX();
            y = t.getY();
        }
        return reached;
    }

    private void populateCheckpointList() {
        directions = findNextDirection(startTile);
        checkpoints.add(findNextCheckpoint(startTile, directions));

        int counter = 0;
        boolean cont = true; // continue
        while (cont) {
            int[] currentDirection = findNextDirection(checkpoints.get(counter).getTile());

            if (currentDirection[0] == 2 || counter == 20) { // проверка на существование нового направления/checkpoint
                cont = false;
            } else {
                directions = findNextDirection(checkpoints.get(counter).getTile());
                checkpoints.add(findNextCheckpoint(checkpoints.get(counter).getTile(), directions));
            }
            counter++;
        }
    }

    private Checkpoint findNextCheckpoint(Tile start, int[] dir) {
        Tile next = null;
        Checkpoint c;

        boolean found = false; // проверка, найден ли следующий checkpoint
        int counter = 1; // счетчик цикла

        while (!found) {
            if (start.getXPlace() + dir[0] * counter == grid.getTileWide() ||
                    start.getYPlace() + dir[1] * counter == grid.getTileHigh() ||
                    start.getType() != grid.getTile(start.getXPlace() + dir[0] * counter,
                            start.getYPlace() + dir[1] * counter).getType()) {
                found = true;
                counter -= 1; // возвращаемся к последнему tile, который перед новым tileType
                next = grid.getTile(start.getXPlace() + dir[0] * counter,
                        start.getYPlace() + dir[1] * counter);
            }
            counter++;
        }
        c = new Checkpoint(next, dir[0], dir[1]);
        return c;
    }

    private int[] findNextDirection(Tile start) { // AI
        int[] dir = new int[2];

        Tile up = grid.getTile(start.getXPlace(), start.getYPlace() - 1);
        Tile right = grid.getTile(start.getXPlace() + 1, start.getYPlace());
        Tile down = grid.getTile(start.getXPlace(), start.getYPlace() + 1);
        Tile left = grid.getTile(start.getXPlace() - 1, start.getYPlace());

        if (start.getType() == up.getType() && directions[1] != 1) {
            dir[0] = 0;
            dir[1] = -1;
        } else if (start.getType() == right.getType() && directions[0] != -1) {
            dir[0] = 1;
            dir[1] = 0;
        } else if (start.getType() == down.getType() && directions[1] != -1) {
            dir[0] = 0;
            dir[1] = 1;
        } else if (start.getType() == left.getType() && directions[0] != 1) {
            dir[0] = -1;
            dir[1] = 0;
        } else {
            dir[0] = 2;
            dir[1] = 2;
        }
        return dir;
    }

    public void damage(float amountOfDamage) {
        health -= amountOfDamage;
        if (health <= 0) {
            die();
            Player.modifyCash(5);
        }
    }

    private void die() {
        alive = false;
        checkpoints.clear();
        directions = null;
    }

    public void draw() {
        float healthPercent = health / startHealth;
        drawQuadTexture(texture, x, y, width, height);
        drawQuadTexture(healthBackGround, x, y - TILE_SIZE / 4, width, 8);
        drawQuadTexture(healthForeGround, x, y - TILE_SIZE / 4, TILE_SIZE * healthPercent, 8);
        drawQuadTexture(healthBorder, x, y - TILE_SIZE / 4, width, 8);
        if (healthPercent < 0.3) {
            int deltaPos = 0;
            if (angle == 90 || angle == -90) deltaPos = 16;
            drawQuadTextureRotation(quickLoad("fire"), x + TILE_SIZE / 2 - 48, y + TILE_SIZE / 2 - 64 + deltaPos,
                    96, 96, angle);
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

    public void setTexture(Texture texture) {
        this.texture = texture;
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
}