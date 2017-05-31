package Data;

import Graphics.Tile;
import Graphics.TileGrid;
import Helpers.Clock;
import Towers.Tower;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static Helpers.Artist.*;
import static Main.Game.Cash;

/**
 * Created by shurik on 29.04.2017.
 */
public class Player {

    private TileGrid grid;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
    private Tower tempTower;


    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
        this.leftMouseButtonDown = true;
        this.rightMouseButtonDown = false;
        this.holdingTower = false;
        this.tempTower = null;
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
            t.updateEnemyList(waveManager.getCurrentWave().getEnemies());
        }

        // Mouse Input
        // свободна ли клетка
        boolean possibleToBuild = getMouseTile().getType().isBuildable() && isPlaceFree(getMouseTile());
        if (Mouse.isButtonDown(0) && !rightMouseButtonDown && possibleToBuild) {
            placeTower();
        }
        if (Mouse.isButtonDown(1) && !leftMouseButtonDown && !isPlaceFree(getMouseTile())) {
            sellTower(getMouseTile());
        }

        leftMouseButtonDown = Mouse.isButtonDown(1);
        rightMouseButtonDown = Mouse.isButtonDown(0);

        // Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                Clock.INSTANCE.changeMultiplier(0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                Clock.INSTANCE.changeMultiplier(-0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_M && Keyboard.getEventKeyState()) {
                Cash += 5;
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

    private void sellTower(Tile mouseTile) {
        for (Tower t : towerList) {
            if (t.getX() / TILE_SIZE == mouseTile.getX() / TILE_SIZE &&
                    t.getY() / TILE_SIZE == mouseTile.getY() / TILE_SIZE)
                t.setWorking(false);
            modifyCash((int) (t.getCost() * 0.5f));
        }
    }

    private Tile getMouseTile() {
        return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
    }

    private boolean isPlaceFree(Tile mouseTile) {
        for (Tower t : towerList) {
            if (t.isWorking() && t.getX() / TILE_SIZE == mouseTile.getX() / TILE_SIZE &&
                    t.getY() / TILE_SIZE == mouseTile.getY() / TILE_SIZE)
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
