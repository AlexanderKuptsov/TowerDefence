package Helpers;

import Main.*;

/**
 * Created by shurik on 06.05.2017.
 */
public enum StateManager {
    INSTANCE;

    public enum GameState {
        MAINMENU, GAME, EDITOR, LEVELMENU, VICTORYMENU;
    }

    private GameState gameState = GameState.MAINMENU;
    private MainMenu mainMenu;
    private Game game;
    private Editor editor;
    private LevelMenu levelMenu;
    private GameOverMenu gameOverMenu;

    private long nextSecond = System.currentTimeMillis() + 1000;
    private int framesInLastSecond = 0;
    private int framesInCurrentSecond = 0;

    private int mapNum = 1;
    private boolean win = false;
    //private String mapName = "res\\maps\\newMarvelousMap" + mapNum;

    public void update() {
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null) mainMenu = new MainMenu();
                game = null;
                mainMenu.update();
                break;
            case GAME:
                if (game == null) {
                    game = new Game(mapNum);
                    gameOverMenu = null;
                }
                game.update();
                break;
            case EDITOR:
                if (editor == null) editor = new Editor();
                editor.update();
                break;
            case LEVELMENU:
                if (levelMenu == null) levelMenu = new LevelMenu();
                levelMenu.update();
                break;
            case VICTORYMENU:
                if (gameOverMenu == null) {
                    gameOverMenu = new GameOverMenu(win);
                    game = null;
                }
                gameOverMenu.update();
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

    public String getMapName() {
        return "res\\maps\\newMarvelousMap" + mapNum;
    }

    public int getMapNum() {
        return mapNum;
    }

    public void setMapNum(int mapNum) {
        this.mapNum = mapNum;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}