package Main;

import Graphics.TileGrid;
import Graphics.TileType;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static Helpers.Artist.HEIGHT;
import static Helpers.Artist.TILE_SIZE;
import static Helpers.LevelManager.loadMap;
import static Helpers.LevelManager.saveMap;

/**
 * Created by shurik on 06.05.2017.
 */
public class Editor {

    private TileGrid grid;
    private int index;
    private TileType[] types;

    public Editor() {
        this.grid = new TileGrid();
        this.index = 0;

        this.types = new TileType[6];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.types[3] = TileType.Sand;
        this.types[4] = TileType.Bush;
        this.types[5] = TileType.Stones;
    }

    public void update() {
        grid.draw();

        // Mouse Input
        if (Mouse.isButtonDown(0)) {
            setTile();
        }

        // Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                moveIndex();
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
                saveMap("newMarvelousMap3", grid);
            }
        }
    }

    private void setTile() {
        grid.setTile((int) Math.floor(Mouse.getX() / TILE_SIZE),
                (int) Math.floor((HEIGHT - Mouse.getY() - 1) / TILE_SIZE), types[index]);
    }

    // change TileType
    private void moveIndex() {
        index++;
        if (index > types.length - 1) {
            index = 0;
        }
    }
}
