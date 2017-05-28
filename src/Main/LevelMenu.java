package Main;

import Helpers.StateManager;
import UI.UI;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 22.05.2017.
 */
public class LevelMenu {
    private Texture background;
    private UI menuUI;

    public LevelMenu() {
        background = quickLoad("mainmenu");
        menuUI = new UI();
        int BUTTON_SIZE = 256;
        menuUI.addButton("Level 1", "1", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.05f),
                BUTTON_SIZE, BUTTON_SIZE);
        menuUI.addButton("Level 2", "2", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.35f),
                BUTTON_SIZE, BUTTON_SIZE);
        menuUI.addButton("Level 3", "3", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.65f),
                BUTTON_SIZE, BUTTON_SIZE);

        menuUI.addButton("Menu", "menu", WIDTH / 2 + BUTTON_SIZE, (int) (HEIGHT * 0.65f),
                BUTTON_SIZE, BUTTON_SIZE);
    }

    private void updateButtons() {
        if (menuUI.isButtonClicked("Level 1")) {
            StateManager.INSTANCE.setMapName("newMarvelousMap1");
            StateManager.INSTANCE.setStartedPlaceX(0);
            StateManager.INSTANCE.setStartedPlaceY(5);
            StateManager.INSTANCE.setStartedMoney(75);
            StateManager.INSTANCE.setStartedLives(5);
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
        }
        if (menuUI.isButtonClicked("Level 2")) {
            StateManager.INSTANCE.setMapName("newMarvelousMap2");
            StateManager.INSTANCE.setStartedPlaceX(0);
            StateManager.INSTANCE.setStartedPlaceY(5);
            StateManager.INSTANCE.setStartedMoney(80);
            StateManager.INSTANCE.setStartedLives(5);
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
        }
        if (menuUI.isButtonClicked("Level 3")) {
            StateManager.INSTANCE.setMapName("newMarvelousMap3");
            StateManager.INSTANCE.setStartedPlaceX(0);
            StateManager.INSTANCE.setStartedPlaceY(2);
            StateManager.INSTANCE.setStartedMoney(100);
            StateManager.INSTANCE.setStartedLives(5);
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
        }
        if (menuUI.isButtonClicked("Menu"))
            StateManager.INSTANCE.setState(StateManager.GameState.MAINMENU);
    }

    public void update() {
        drawQuadTexture(background, 0, 0, 2048, 1024);
        menuUI.draw();
        updateButtons();
    }
}
