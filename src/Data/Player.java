package Data;

import Graphics.Tile;
import Graphics.TileGrid;
import Graphics.TileType;
import Helpers.Clock;
import Main.Boot;
import Towers.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static Helpers.Artist.*;

/**
 * Created by shurik on 29.04.2017.
 */
public class Player {

    private TileGrid grid;
    private TileType[] types;
    private ArrayList<Enemy> enemies;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
    private Tower tempTower;

    public static int Cash, Lives;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.types = new TileType[6];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.types[3] = TileType.Sand;
        this.types[4] = TileType.Bush;
        this.types[5] = TileType.Stones;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
        this.leftMouseButtonDown = true;
        this.rightMouseButtonDown = false;
        this.holdingTower = false;
        this.tempTower = null;
        Cash = 0;
        Lives = 0;
    }

    public static void setup() {
        Cash = 75;
        Lives = 5;
        System.out.println("$" + Cash);
        System.out.println("Lives: " + Lives);
    }

    public static boolean modifyCash(int amount) {
        if (Cash + amount >= 0) {
            Cash += amount;
            System.out.println("$" + Cash);
            return true;
        }
        return false;
    }

    public static void modifyLives(int amount) {
        Lives += amount;
        System.out.println("Lives: " + Lives);
    }

    public void update() {
        //Update holding tower
        if (holdingTower) {
            tempTower.setX(getMouseTile().getX());
            tempTower.setY(getMouseTile().getY());
            tempTower.draw();
        }

        for (Tower t : towerList) {
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemies());
        }

        // Mouse Input
        // свободна ли клетка
        boolean possibleToBuild = getMouseTile().getType().isBuildable() && isPlaceFree(getMouseTile());
        if (Mouse.isButtonDown(0) && !rightMouseButtonDown && possibleToBuild) {
            placeTower();
        }

        leftMouseButtonDown = Mouse.isButtonDown(1);
        rightMouseButtonDown = Mouse.isButtonDown(0);

        // Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                Clock.changeMultiplier(0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                Clock.changeMultiplier(-0.2f);
            }
        }
        if (waveManager.getCurrentWave().isCompleted()) cleanProjectiles();
    }

    private void placeTower() {
        if (holdingTower)
            if (modifyCash(-tempTower.getCost()))
                towerList.add(tempTower);
        holdingTower = false;
        tempTower = null;
    }

    public void pickTower(Tower t) {
        tempTower = t;
        holdingTower = true;
    }

    private Tile getMouseTile() {
        return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
    }

    private boolean isPlaceFree(Tile mouseTile) {
        for (Tower t : towerList) {
            if (t.getX() / TILE_SIZE == mouseTile.getX() / TILE_SIZE && t.getY() / TILE_SIZE == mouseTile.getY() / TILE_SIZE)
                return false;
        }
        return true;
    }

    public ArrayList<Tower> getTowerList() {
        return towerList;
    }

    public void cleanProjectiles() {
        for (Tower t : getTowerList()) t.projectiles.clear();
    }
}
