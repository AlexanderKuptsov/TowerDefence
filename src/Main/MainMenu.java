package Main;

import Helpers.StateManager;
import UI.UI;

import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 06.05.2017.
 */
public class MainMenu {
    private Texture background;
    private UI menuUI;

    public MainMenu() {
        background = quickLoad("mainMenu");
        menuUI = new UI();
        int BUTTON_SIZE = TILE_SIZE * 4;
        menuUI.addButton("Play", "play", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.05f),
                BUTTON_SIZE, BUTTON_SIZE);
        menuUI.addButton("Editor", "editor", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.35f),
                BUTTON_SIZE, BUTTON_SIZE);
        menuUI.addButton("Quit", "quit", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.65f),
                BUTTON_SIZE, BUTTON_SIZE);

        menuUI.addButton("Levels", "Levels", WIDTH / 2 + BUTTON_SIZE, (int) (HEIGHT * 0.05f),
                BUTTON_SIZE, BUTTON_SIZE);
    }

    private void updateButtons() {
        if (menuUI.isButtonClicked("Play")) {
            StateManager.INSTANCE.setMapName("res\\maps\\newMarvelousMap1");
            StateManager.INSTANCE.setStartedPlaceX(0);
            StateManager.INSTANCE.setStartedPlaceY(5);
            StateManager.INSTANCE.setStartedMoney(75);
            StateManager.INSTANCE.setStartedLives(5);
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
        }
        if (menuUI.isButtonClicked("Editor"))
            StateManager.INSTANCE.setState(StateManager.GameState.EDITOR);
        if (menuUI.isButtonClicked("Quit"))
            System.exit(0);
        if (menuUI.isButtonClicked("Levels"))
            StateManager.INSTANCE.setState(StateManager.GameState.LEVELMENU);
    }

    public void update() {
        drawQuadTexture(background, 0, 0, 2048, 1024);
        updateButtons();
        menuUI.draw();
    }
}