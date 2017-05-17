package Data;

import Graphics.TileGrid;
import Graphics.TileType;
import Helpers.Clock;
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
    private boolean leftMouseButtonDown, rightMouseButtonDown;

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
        Cash = 0;
        Lives = 0;
    }

    public void setup() {
        Cash = 75;
        Lives = 10;
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

        for (Tower t : towerList) {
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemies());
        }

        // Mouse Input
        int mouseX = Mouse.getX() / TILE_SIZE;
        int mouseY = (HEIGHT - Mouse.getY() - 1) / TILE_SIZE;
        // свободна ли клетка
        boolean possibleToBuild = grid.getTile(mouseX, mouseY).getType().isBuildable() && isPlaceFree(mouseX, mouseY);

        if (Mouse.isButtonDown(0) && !leftMouseButtonDown && possibleToBuild) {
            if (modifyCash(-15))
                towerList.add(new TowerCannon(TowerType.CannonBlue, grid.getTile(mouseX, mouseY), waveManager.getCurrentWave().getEnemies()));
        }

        if (Mouse.isButtonDown(1) && !rightMouseButtonDown && possibleToBuild) {
            if (modifyCash(-25))
                towerList.add(new TowerIce(TowerType.CannonIce, grid.getTile(mouseX, mouseY), waveManager.getCurrentWave().getEnemies()));
        }

        leftMouseButtonDown = Mouse.isButtonDown(0);
        rightMouseButtonDown = Mouse.isButtonDown(1);

        // Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                Clock.changeMultiplier(0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                Clock.changeMultiplier(-0.2f);
            }
        }
    }

    private boolean isPlaceFree(int mouseX, int mouseY) {
        for (Tower t : towerList) {
            if (t.getX() / TILE_SIZE == mouseX && t.getY() / TILE_SIZE == mouseY) return false;
        }
        return true;
    }
}
