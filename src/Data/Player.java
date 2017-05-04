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
    //private ArrayList<Enemy> enemies;

    public Player(TileGrid grid/*, ArrayList<Enemy> enemies*/) {
        this.grid = grid;
        this.types = new TileType[6];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Dirt;
        this.types[2] = TileType.Water;
        this.types[3] = TileType.Sand;
        this.types[4] = TileType.Bush;
        this.types[5] = TileType.Stones;
        this.index = 0;
        // this.enemies = enemies;
    }

    public void setTile() {
        grid.setTile((int) Math.floor(Mouse.getX() / 64),
                (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64), types[index]);
    }

    public void update() {

        // Mouse Input
        if (Mouse.isButtonDown(0)) {
            setTile();
        }

        // Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                moveIndex();
            }
           /* if (Keyboard.getEventKey() == Keyboard.KEY_T && Keyboard.getEventKeyState()) {
                DrawQuadTextureRotation(quickLoad("cannonGun"), (int) Math.floor(Mouse.getX() / 64), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64), 64, 64, -90);
            }*/
        }
    }

    private void moveIndex() {
        index++;
        if (index > types.length - 1) {
            index = 0;
        }
    }
}
