package Helpers;

import Data.Player;
import Main.Editor;
import Main.Game;
import Main.MainMenu;

/**
 * Created by shurik on 06.05.2017.
 */
public class StateManager {

    public static enum GameState {
        MAINMENU, GAME, EDITOR;
    }

    public static GameState gameState = GameState.MAINMENU;
    public static MainMenu mainMenu;
    public static Game game;
    public static Editor editor;

    public static long nextSecond = System.currentTimeMillis() + 1000;
    public static int framesInLastSecond = 0;
    public static int framesInCurrentSecond = 0;

    public static void update() {
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null) mainMenu = new MainMenu();
                mainMenu.update();
                break;
            case GAME:
                if (game == null) game = new Game("newMarvelousMap1");
                game.update();

                break;
            case EDITOR:
                if (editor == null) editor = new Editor();
                editor.update();
                break;
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
}
