package Data;

import Graphics.TileGrid;
import Graphics.TileType;
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
    private int index;

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
        this.index = 0;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<TowerCannon>();
        this.leftMouseButtonDown = false;
    }

    public void setTile() {
        grid.setTile((int) Math.floor(Mouse.getX() / 64),
                (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64), types[index]);
    }

    public void update() {

        for (TowerCannon t : towerList) t.update();

        // Mouse Input
        if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
            towerList.add((new TowerCannon(quickLoad("cannonBase"),
                    grid.getTile(Mouse.getX() / 64, (HEIGHT - Mouse.getY() - 1) / 64),
                    10, waveManager.getCurrentWave().getEnemies())));

            //setTile();
        }
        leftMouseButtonDown = Mouse.isButtonDown(0);
        // Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                moveIndex();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()) {
                //towerList.add((new TowerCannon(quickLoad("cannonBase"), grid.getTile(3, 3), 10, waveManager.getCurrentWave().getEnemies())));
            }
        }
    }

    private void moveIndex() {
        index++;
        if (index > types.length - 1) {
            index = 0;
        }
    }
}
