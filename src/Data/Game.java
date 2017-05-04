package Data;

import Graphics.TileGrid;

import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 02.05.2017.
 */
public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;

    //Testing


    public Game(int[][] map) {
        grid = new TileGrid(map);

        waveManager = new WaveManager(new Enemy(quickLoad("tankNavy"),
                grid.getTile(0, 5), grid, 64, 64, 50, 100), 3, 4);
        player = new Player(grid, waveManager);

    }

    public void update() {
        grid.draw();
        waveManager.update();
        player.update();


    }
}
