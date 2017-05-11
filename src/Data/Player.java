package Data;

import Graphics.TileGrid;
import Graphics.TileType;
import Helpers.Clock;
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
    private TowerCannon tower;
    private ArrayList<Enemy> enemies;
    private WaveManager waveManager;
    private ArrayList<TowerCannon> towerList;
    private boolean leftMouseButtonDown;

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
        this.towerList = new ArrayList<TowerCannon>();
        this.leftMouseButtonDown = true;
    }

    public void update() {

        for (TowerCannon t : towerList) {
            t.update();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemies());
        }

        // Mouse Input
        int mouseX = Mouse.getX() / TILE_SIZE;
        int mouseY = (HEIGHT - Mouse.getY() - 1) / TILE_SIZE;

        if (Mouse.isButtonDown(0) && //проверка на то, что ЛКМ нажали
                !leftMouseButtonDown &&    // и отпустили
                grid.getTile(mouseX, mouseY).getType().isBuildable() && // проверка поверхности(можно ли на ней строить)
                isPlaceFree(mouseX, mouseY)) { // свободна ли клетка

            towerList.add((new TowerCannon(quickLoad("cannonBase"),
                    grid.getTile(mouseX, mouseY),
                    10, TILE_SIZE * 4, waveManager.getCurrentWave().getEnemies())));
        }
        leftMouseButtonDown = Mouse.isButtonDown(0);

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
        for (TowerCannon t : towerList) {
            if (t.getX() / TILE_SIZE == mouseX && t.getY() / TILE_SIZE == mouseY) return false;
        }
        return true;
    }
}
