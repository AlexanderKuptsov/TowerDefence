package Helpers;

import Data.Player;
import Main.Editor;
import Main.Game;
import Main.LevelMenu;
import Main.MainMenu;

/**
 * Created by shurik on 06.05.2017.
 */
public class StateManager {

    public static enum GameState {
        MAINMENU, GAME, EDITOR, LEVELMENU;
    }

    private static GameState gameState = GameState.MAINMENU;
    private static MainMenu mainMenu;
    private static Game game;
    private static Editor editor;
    private static LevelMenu levelMenu;

    private static long nextSecond = System.currentTimeMillis() + 1000;
    public static int framesInLastSecond = 0;
    private static int framesInCurrentSecond = 0;

    private static String mapName = "newMarvelousMap1";

    public static void update() {
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null) mainMenu = new MainMenu();
                mainMenu.update();
                break;
            case GAME:
                if (game == null) game = new Game(mapName);
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

    public static void setState(GameState newState) {
        gameState = newState;
    }

    public static void setMapName(String mapName) {
        StateManager.mapName = mapName;
    }
}