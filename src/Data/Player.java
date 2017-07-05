package Data;

import Graphics.Tile;
import Graphics.TileGrid;
import Helpers.Clock;
import Helpers.Sound;
import Towers.Tower;
import UI.UI;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.File;
import java.util.ArrayList;

import static Helpers.Artist.*;
import static Main.Game.Cash;
import static Main.Game.TOWER_PICKER_MENU_WIDTH;

/**
 * Created by shurik on 29.04.2017.
 */
public class Player {

    private TileGrid grid;
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private boolean leftMouseButtonDown, rightMouseButtonDown, mouseClicked, holdingTower;
    private Tower tempTower;
    private UI optionsUI;
    private Tile chosenTile;
    private float upgradeMultiCost, upgradeMulti, sellMultiCost;
    private Sound soundSell, soundUpgrade;

    private final int GAME_WIDTH = WIDTH - TOWER_PICKER_MENU_WIDTH;

    public Player(TileGrid grid, WaveManager waveManager) {
        this.grid = grid;
        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
        this.leftMouseButtonDown = true;
        this.rightMouseButtonDown = false;
        this.mouseClicked = true;
        this.holdingTower = false;
        this.tempTower = null;
        this.optionsUI = new UI();
        this.chosenTile = null;
        this.upgradeMultiCost = 0.7f;
        this.upgradeMulti = 1.4f;
        this.sellMultiCost = 0.5f;
        this.soundSell = new Sound(new File("res\\sounds\\money.wav"));
        this.soundUpgrade = new Sound(new File("res\\sounds\\upgrade.wav"));
    }

    public void update() {
        //Update holding tower
        if (holdingTower && Mouse.getX() < GAME_WIDTH) {
            float x = getMouseTile().getX();
            float y = getMouseTile().getY();
            float radius = tempTower.getType().getRange();
            float diameter = 2 * radius;
            float rangeX = x - radius + TILE_SIZE / 2;
            float rangeY = y - radius + TILE_SIZE / 2;
            tempTower.setX(x);
            tempTower.setY(y);
            tempTower.draw();
            drawQuadTexture(quickLoad("range"), rangeX, rangeY, diameter, diameter);
            optionsUI.drawString((int) (x + TILE_SIZE / 2 - 7.6 * (tempTower.getType().getName().length() - 1)), (int) y - TILE_SIZE / 2, tempTower.getType().getName());
        }

        for (Tower t : towerList) {
            t.update();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemies());
        }

        updateUI();

        // Mouse Input
        // свободна ли клетка
        boolean possibleToBuild = getMouseTile().getType().isBuildable() && isPlaceFree(getMouseTile());
        if (Mouse.isButtonDown(0) && !rightMouseButtonDown && possibleToBuild) {
            placeTower();
        }
        boolean rightMouseClick = Mouse.isButtonDown(1) && !leftMouseButtonDown;
        if (rightMouseClick && !isPlaceFree(getMouseTile())) {
            if (optionsUI.getButtonList().isEmpty()) createOptionMenu();
            else deleteOptionMenu();
        } else if (rightMouseClick) deleteOptionMenu();

        leftMouseButtonDown = Mouse.isButtonDown(1);
        rightMouseButtonDown = Mouse.isButtonDown(0);

        // Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                Clock.INSTANCE.changeMultiplier(0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
                Clock.INSTANCE.changeMultiplier(-0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_M && Keyboard.getEventKeyState()) {
                Cash += 5;
            }
        }
        if (waveManager.getCurrentWave().isCompleted()) cleanProjectiles();
    }

    private void updateUI() {
        optionsUI.draw();

        if (!mouseClicked && !optionsUI.isEmpty()) {
            if (optionsUI.isButtonClicked("Upgrade")) {
                upgradeTower(chosenTile);
                deleteOptionMenu();
            } else if (optionsUI.isButtonClicked("Sell")) {
                sellTower(chosenTile);
                deleteOptionMenu();
            }
        }
        mouseClicked = Mouse.isButtonDown(0);
    }

    private void drawTowerRange(Tile tile) {                ///    ///

    }

    private void placeTower() {
        if (holdingTower)
            if (modifyCash(-tempTower.getCost()))
                towerList.add(tempTower);
        holdingTower = false;
        tempTower = null;
    }

    public void pickTower(Tower t) {
        tempTower = t;
        holdingTower = true;
    }

    private void sellTower(Tile mouseTile) {
        Tower t = findTower(mouseTile);
        t.setWorking(false);
        modifyCash((int) (t.getCost() * sellMultiCost));
        Sound.playSound(soundSell);
    }

    private void upgradeTower(Tile mouseTile) {
        Tower t = findTower(mouseTile);
        int upgradeCost = (int) (t.getCost() * upgradeMultiCost + (t.getLevel() - 1) * 0.2 * t.getCost());
        if (modifyCash(-upgradeCost)) {
            float currentLevelUpMulti = (float) Math.pow(upgradeMulti, t.getLevel());
            t.getType().getProjectileType().setDamage((currentLevelUpMulti * t.getType().getProjectileType().getDamage()));
            t.getType().setRange(currentLevelUpMulti * t.getType().getRange());

            t.setLevel(t.getLevel() + 1);
            t.setCost(t.getCost() + upgradeCost);
            Sound.playSound(soundUpgrade);
        }
    }

    private Tower findTower(Tile mouseTile) {
        Tower tower = null;
        for (Tower t : towerList) {
            if (t.getX() / TILE_SIZE == mouseTile.getX() / TILE_SIZE &&
                    t.getY() / TILE_SIZE == mouseTile.getY() / TILE_SIZE && t.isWorking())
                tower = t;
        }
        return tower;
    }

    private void createOptionMenu() {
        chosenTile = getMouseTile();
        Tower t = findTower(chosenTile);
        int towerCost = t.getCost();

        final int OPTION_MENU_SIZE = (int) (0.9 * TILE_SIZE);
        final int OPTION_MENU_Y = getMouseTile().getYPlace() != 0 ?
                (int) (getMouseTile().getY() - TILE_SIZE / 2) : (int) (getMouseTile().getY() + 0.8 * TILE_SIZE);
        final int OPTION_MENU_X1 = (int) getMouseTile().getX() - OPTION_MENU_SIZE / 3;
        final int OPTION_MENU_X2 = (int) getMouseTile().getX() + TILE_SIZE - OPTION_MENU_SIZE / 2;
        final int LEVEL_TEXT_Y = getMouseTile().getYPlace() != 0 ?
                (int) (t.getY() + 0.8 * TILE_SIZE) : (int) t.getY();

        optionsUI.addText((int) t.getX(), LEVEL_TEXT_Y, " Lvl " + t.getLevel());

        optionsUI.addButton("Upgrade", "upgrade",
                OPTION_MENU_X1, OPTION_MENU_Y, OPTION_MENU_SIZE, OPTION_MENU_SIZE);
        optionsUI.addText(OPTION_MENU_X1, OPTION_MENU_Y + TILE_SIZE / 2,
                "-" + (int) (upgradeMultiCost * towerCost + (t.getLevel() - 1) * 0.2 * towerCost) + "$");

        optionsUI.addButton("Sell", "sell",
                OPTION_MENU_X2, OPTION_MENU_Y, OPTION_MENU_SIZE, OPTION_MENU_SIZE);
        optionsUI.addText(OPTION_MENU_X2, OPTION_MENU_Y + TILE_SIZE / 2,
                "+" + (int) (sellMultiCost * towerCost) + "$");
    }

    private void deleteOptionMenu() {
        optionsUI.getButtonList().clear();
        optionsUI.getTextMap().clear();
        chosenTile = null;
    }

    private Tile getMouseTile() {
        return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
    }

    private boolean isPlaceFree(Tile mouseTile) {
        for (Tower t : towerList) {
            if (t.isWorking() && t.getX() / TILE_SIZE == mouseTile.getX() / TILE_SIZE &&
                    t.getY() / TILE_SIZE == mouseTile.getY() / TILE_SIZE)
                return false;
        }
        return true;
    }

    public ArrayList<Tower> getTowerList() {
        return towerList;
    }

    public void cleanProjectiles() {
        for (Tower t : getTowerList()) t.projectiles.clear();
    }

    public boolean isHoldingTower() {
        return holdingTower;
    }

    public void setHoldingTower(boolean holdingTower) {
        this.holdingTower = holdingTower;
    }
}
