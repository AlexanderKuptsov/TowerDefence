package Main;

import Data.Enemy;
import Data.Player;
import Data.WaveManager;
import Graphics.TileGrid;

import static Helpers.Artist.quickLoad;
import static Helpers.Artist.TILE_SIZE;
import static Helpers.LevelManager.loadMap;

/**
 * Created by shurik on 02.05.2017.
 */
public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;

    public Game(int[][] map) {
        //grid = new TileGrid(map);
         grid = loadMap("newMarvelousMap1");

        waveManager = new WaveManager(new Enemy(quickLoad("tankNavy"),
                grid.getTile(0, 5), grid, TILE_SIZE, TILE_SIZE, 55, 80), 3, 10);
        player = new Player(grid, waveManager);
        player.setup();
    }

    public void update() {
        grid.draw();
        waveManager.update();
        player.update();
    }
}
