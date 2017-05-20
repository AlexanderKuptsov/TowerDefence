package Main;

import Data.Enemy;
import Data.Player;
import Data.WaveManager;
import Graphics.TileGrid;
import Helpers.StateManager;
import Towers.TowerCannon;
import Towers.TowerFlameThrower;
import Towers.TowerIce;
import Towers.TowerType;
import UI.*;
import org.lwjgl.input.Mouse;

import static Data.Player.Lives;
import static Data.Player.setup;
import static Helpers.Artist.*;
import static Helpers.LevelManager.loadMap;

/**
 * Created by shurik on 02.05.2017.
 */
public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private UI towerPickerUI;

    public Game(String map) {
        //grid = new TileGrid(map);
        grid = loadMap(map);

        waveManager = new WaveManager(new Enemy(quickLoad("tankNavy"),
                grid.getTile(0, 5), grid, TILE_SIZE, TILE_SIZE, 55, 80), 3, 6);
        player = new Player(grid, waveManager);
        player.setup();
        setupUI();
    }

    private void setupUI() {
        towerPickerUI = new UI();
        towerPickerUI.createMenu("TowerPicker", 1280, 0, 192, 960, 2, 0);

        towerPickerUI.getMenu("TowerPicker").addButton(
                new Button("TowerIce", quickLoad("Towers/towerIceFull"), 0, 0, TILE_SIZE, TILE_SIZE));
        towerPickerUI.getMenu("TowerPicker").addButton(
                new Button("FlameThrower", quickLoad("Towers/flameThrowerFull"), 0, 0, TILE_SIZE, TILE_SIZE));
        towerPickerUI.getMenu("TowerPicker").addButton(
                new Button("TowerCannonPurple", quickLoad("Towers/towerPurpleFull"), 0, 0, TILE_SIZE, TILE_SIZE));
    }

    private void updateUI() {
        towerPickerUI.draw();

        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (towerPickerUI.getMenu("TowerPicker").isButtonClicked("TowerIce"))
                    player.pickTower(new TowerIce(TowerType.CannonIce, grid.getTile(0, 0),
                            waveManager.getCurrentWave().getEnemies()));
                if (towerPickerUI.getMenu("TowerPicker").isButtonClicked("FlameThrower"))
                    player.pickTower(new TowerFlameThrower(TowerType.FlameThrower, grid.getTile(0,
                            0), waveManager.getCurrentWave().getEnemies()));
                if (towerPickerUI.getMenu("TowerPicker").isButtonClicked("TowerCannonPurple"))
                    player.pickTower(new TowerCannon(TowerType.CannonPurple, grid.getTile(0, 0),
                            waveManager.getCurrentWave().getEnemies()));
            }
        }
    }


    public void Restart() {
        StateManager.setState(StateManager.GameState.MAINMENU);
        setup();
        waveManager.restartEnemies();
        player.cleanProjectiles();
        player.getTowerList().clear();
    }


    public void update() {
        drawQuadTexture(quickLoad("menuBackground"), 1280, 0, 192, 960);
        grid.draw();
        waveManager.update();
        player.update();
        updateUI();

        if (waveManager.getWaveNumber() >= 3) {
            System.out.println("Congratulations! You win!");
            Restart();
        }

        if (Lives <= 0) {
            System.out.println("Noob! You loose!");
            Restart();
        }
    }
}