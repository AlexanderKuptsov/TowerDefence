package Helpers;

import Data.Player;
import Main.Editor;
import Main.Game;
import Main.LevelMenu;
import Main.MainMenu;

/**
 * Created by shurik on 06.05.2017.
 */
public enum StateManager {
    INSTANCE;

    public enum GameState {
        MAINMENU, GAME, EDITOR, LEVELMENU;
    }

    private GameState gameState = GameState.MAINMENU;
    private MainMenu mainMenu;
    private Game game;
    private Editor editor;
    private LevelMenu levelMenu;

    private long nextSecond = System.currentTimeMillis() + 1000;
    private int framesInLastSecond = 0;
    private int framesInCurrentSecond = 0;
    private int startedPlaceX = 0;
    private int startedPlaceY = 5;
    private int startedMoney = 75;
    private int startedLives = 5;
    private String mapName = "newMarvelousMap1";

    public void update() {
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null) mainMenu = new MainMenu();
                game = null;
                mainMenu.update();
                break;
            case GAME:
                if (game == null) game = new Game(mapName, startedPlaceX, startedPlaceY, startedMoney, startedLives);
                game.update();
                break;
            case EDITOR:
                if (editor == null) editor = new Editor();
                editor.update();
                break;
            case LEVELMENU:
                if (levelMenu == null) levelMenu = new LevelMenu();
                levelMenu.update();
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime > nextSecond) {
            nextSecond += 1000;
            framesInLastSecond = framesInCurrentSecond;
            framesInCurrentSecond = 0;
        }
        framesInCurrentSecond++;
    }

    public int getFramesInLastSecond() {
        return framesInLastSecond;
    }

    public void setState(GameState newState) {
        this.gameState = newState;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public void setStartedPlaceX(int startedPlaceX) {
        this.startedPlaceX = startedPlaceX;
    }

    public void setStartedPlaceY(int startedPlaceY) {
        this.startedPlaceY = startedPlaceY;
    }

    public void setStartedMoney(int startedMoney) {
        this.startedMoney = startedMoney;
    }

    public void setStartedLives(int startedLives) {
        this.startedLives = startedLives;
    }
}