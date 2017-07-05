package Main;

import Data.*;
import Graphics.TileGrid;
import Helpers.Clock;
import Helpers.LevelManager;
import Helpers.Sound;
import Helpers.StateManager;
import Towers.*;
import UI.UI;
import UI.UI.Menu;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 02.05.2017.
 */
public class Game {

    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private UI gameUI;
    private Texture menuBackground;
    private Enemy[] enemyTypes;
    private int startedPlaceX, startedPlaceY, startedMoney, startedLives;
    private Menu towerPickerMenu;
    public static int Cash, Lives;

    private static final int NUMBER_OF_WAVES = 5;

    public static final int TOWER_PICKER_MENU_WIDTH = 3 * TILE_SIZE;
    private static final int TOWER_PICKER_MENU_HEIGHT = HEIGHT;
    private static final int TOWER_PICKER_MENU_X = WIDTH - TOWER_PICKER_MENU_WIDTH;
    private static final int TOWER_PICKER_MENU_Y = HEIGHT - TOWER_PICKER_MENU_HEIGHT;
    private static final int MAX_TOWERS_IN_ROW = 2;

    private static final int QUITE_BUTTON_WIDTH = (int) (TILE_SIZE * 1.25);
    private static final int QUITE_BUTTON_HEIGHT = (int) (TILE_SIZE * 1.25);
    private static final int QUITE_BUTTON_X = WIDTH - TOWER_PICKER_MENU_WIDTH / 2 - QUITE_BUTTON_WIDTH / 2;
    private static final int QUITE_BUTTON_Y = HEIGHT - 2 * TILE_SIZE;

    private static final int CANCEL_WIDTH = TILE_SIZE;
    private static final int CANCEL_HEIGHT = TILE_SIZE;
    private static final int CANCEL_X = WIDTH - TOWER_PICKER_MENU_WIDTH / 2 - CANCEL_WIDTH / 2;
    private static final int CANCEL_Y = TILE_SIZE * 5;

    public Game(String mapName, int startedPlaceX, int startedPlaceY, int startedMoney, int startedLives) {
        this.startedPlaceX = startedPlaceX;
        this.startedPlaceY = startedPlaceY;
        this.startedMoney = startedMoney;
        this.startedLives = startedLives;

        // grid = LevelManager.INSTANCE.loadMap(mapName);
        grid = LevelManager.INSTANCE.setMap(mapName);

        enemyTypes = new Enemy[4];
        enemyTypes[2] = new EnemyTank(EnemyType.Tank, startedPlaceX, startedPlaceY, grid);
        enemyTypes[1] = new EnemyUFO(EnemyType.UFO, startedPlaceX, startedPlaceY, grid);
        enemyTypes[0] = new EnemyBigTank(EnemyType.BigTank, startedPlaceX, startedPlaceY, grid);
        enemyTypes[3] = new EnemyPlane(EnemyType.Plane, startedPlaceX, startedPlaceY, grid);

        switch (mapName) {
            case "res\\maps\\newMarvelousMap1":
                waveManager = new WaveManager(enemyTypes, 3, 8, 1.25f);
                break;
            case "res\\maps\\newMarvelousMap2":
                waveManager = new WaveManager(enemyTypes, 2, 9, 1.3f);
                break;
            case "res\\maps\\newMarvelousMap3":
                waveManager = new WaveManager(enemyTypes, 1.2f, 15, 1.55f);
                break;
            default:
                waveManager = new WaveManager(enemyTypes, 3, 8, 1.25f);
                break;
        }

        player = new Player(grid, waveManager);
        this.menuBackground = quickLoad("menuBackground2");
        setup(startedMoney, startedLives);
        setupUI();
    }

    private void setup(int startedMoney, int startedLives) {
        Cash = startedMoney;
        Lives = startedLives;
    }

    private void setupUI() {
        gameUI = new UI();

        gameUI.createMenu("TowerPicker", TOWER_PICKER_MENU_X, TOWER_PICKER_MENU_Y,
                TOWER_PICKER_MENU_WIDTH, TOWER_PICKER_MENU_HEIGHT, MAX_TOWERS_IN_ROW, 0);
        towerPickerMenu = gameUI.getMenu("TowerPicker");

        towerPickerMenu.quickAdd("TowerIce", "Towers\\towerIceFull");
        towerPickerMenu.quickAdd("FlameThrower", "Towers\\flameThrowerFull");
        towerPickerMenu.quickAdd("TowerCannonPurple", "Towers\\towerPurpleFull");
        towerPickerMenu.quickAdd("Mortal", "Towers\\towerMortalFull");

        gameUI.addButton("Quit", "menu",
                QUITE_BUTTON_X, QUITE_BUTTON_Y, QUITE_BUTTON_WIDTH, QUITE_BUTTON_HEIGHT);

       /* gameUI.addButton("cancel", "cancel",
                CANCEL_X, CANCEL_Y, CANCEL_WIDTH, CANCEL_HEIGHT);*/
        gameUI.addButton("cancelActive", "cancelActive",
                CANCEL_X, CANCEL_Y, CANCEL_WIDTH, CANCEL_HEIGHT);
    }

    private void updateUI() {
        gameUI.draw();
        final int TEXT_X = WIDTH - TOWER_PICKER_MENU_WIDTH + TILE_SIZE / 2;
        final int TEXT_Y = (int) (0.6 * HEIGHT);
        final int TEXT_GAP = (int) (0.8 * TILE_SIZE);
        final int COST_X_DELTA = (int) (TILE_SIZE * 0.2);

        gameUI.drawString(TEXT_X, TEXT_Y, "Lives: " + Lives);
        gameUI.drawString(TEXT_X, TEXT_Y + TEXT_GAP, "Cash: " + Cash + " $");
        gameUI.drawString(TEXT_X, TEXT_Y + TEXT_GAP * 2, "Wave: " + waveManager.getWaveNumber());
        gameUI.drawString(TEXT_X, TEXT_Y + TEXT_GAP * 3, StateManager.INSTANCE.getFramesInLastSecond() + " fps");

        gameUI.drawString(gameUI.getMenu("TowerPicker").getButton("TowerIce").getX() + COST_X_DELTA,
                gameUI.getMenu("TowerPicker").getButton("TowerIce").getY() + (int) (TILE_SIZE * 1.15),
                TowerType.CannonIce.getCost() + " $");
        gameUI.drawString(gameUI.getMenu("TowerPicker").getButton("FlameThrower").getX() + COST_X_DELTA,
                gameUI.getMenu("TowerPicker").getButton("FlameThrower").getY() + (int) (TILE_SIZE * 1.15),
                TowerType.FlameThrower.getCost() + " $");
        gameUI.drawString(gameUI.getMenu("TowerPicker").getButton("TowerCannonPurple").getX() + COST_X_DELTA,
                gameUI.getMenu("TowerPicker").getButton("TowerCannonPurple").getY() + (int) (TILE_SIZE * 1.15),
                TowerType.CannonPurple.getCost() + " $");
        gameUI.drawString(gameUI.getMenu("TowerPicker").getButton("Mortal").getX() + COST_X_DELTA,
                gameUI.getMenu("TowerPicker").getButton("Mortal").getY() + (int) (TILE_SIZE * 1.15),
                TowerType.Mortal.getCost() + " $");

        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (towerPickerMenu.isButtonClicked("TowerIce"))
                    player.pickTower(new TowerIce(TowerType.CannonIce, waveManager.getCurrentWave().getEnemies()));
                if (towerPickerMenu.isButtonClicked("FlameThrower"))
                    player.pickTower(new TowerFlameThrower(TowerType.FlameThrower, waveManager.getCurrentWave().getEnemies()));
                if (towerPickerMenu.isButtonClicked("TowerCannonPurple"))
                    player.pickTower(new TowerCannon(TowerType.CannonPurple, waveManager.getCurrentWave().getEnemies()));
                if (towerPickerMenu.isButtonClicked("Mortal"))
                    player.pickTower(new TowerMortal(TowerType.Mortal, waveManager.getCurrentWave().getEnemies()));

                if (gameUI.isButtonClicked("cancelActive")) {
                    player.setHoldingTower(false);
                    Sound.playSound("res\\sounds\\click1.wav");
                }

                if (gameUI.isButtonClicked("Quit")) {
                    Restart();
                    StateManager.INSTANCE.setState(StateManager.GameState.MAINMENU);
                    Sound.playSound("res\\sounds\\click1.wav");
                }
            }
        }
    }

    private void Restart() {
        StateManager.INSTANCE.setState(StateManager.GameState.MAINMENU);
        setup(startedMoney, startedLives);
        waveManager.restartEnemies();
        player.cleanProjectiles();
        player.getTowerList().clear();
        Clock.INSTANCE.setMultiplier(1);
    }

    public void update() {
        drawQuadTexture(menuBackground, TOWER_PICKER_MENU_X, TOWER_PICKER_MENU_Y,
                TOWER_PICKER_MENU_WIDTH, TOWER_PICKER_MENU_HEIGHT);
        grid.draw();
        player.update();
        waveManager.update();
        updateUI();

        if (waveManager.getWaveNumber() > NUMBER_OF_WAVES) {
            System.out.println("Congratulations! You win!");
            Restart();
        }

        if (Lives <= 0) {
            System.out.println("Noob! You loose!");
            Restart();
        }
    }
}