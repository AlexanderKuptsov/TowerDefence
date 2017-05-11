package Data.Main;

import Data.Enemy;
import Data.Player;
import Data.WaveManager;
import Graphics.TileGrid;

import static Helpers.Artist.quickLoad;
import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 02.05.2017.
 */
public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;

    public Game(int[][] map) {
        grid = new TileGrid(map);

        waveManager = new WaveManager(new Enemy(quickLoad("tankNavy"),
                grid.getTile(0, 5), grid, TILE_SIZE, TILE_SIZE, 50, 80), 3, 4);
        player = new Player(grid, waveManager);
    }

    public void update() {
        grid.draw();
        waveManager.update();
        player.update();
    }
}
