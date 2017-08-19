package Main;

import Data.ResourceLoader;
import Helpers.Sound;
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
    private Sound sound;

    public MainMenu() {
        background = ResourceLoader.UI_TEXTURES.get("mainMenu");
        sound = ResourceLoader.SOUNDS_PACK.get("click1.wav");
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
            StateManager.INSTANCE.setMapNum(1);
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
            Sound.playSound(sound);
        }
        if (menuUI.isButtonClicked("Editor")) {
            StateManager.INSTANCE.setState(StateManager.GameState.EDITOR);
            Sound.playSound(sound);
        }
        if (menuUI.isButtonClicked("Quit")) {
            Sound.playSound(sound);
            System.exit(0);
        }
        if (menuUI.isButtonClicked("Levels")) {
            Sound.playSound(sound);
            StateManager.INSTANCE.setState(StateManager.GameState.LEVELMENU);
        }
    }

    public void update() {
        drawQuadTexture(background, 0, 0, 2048, 1024);
        updateButtons();
        menuUI.draw();
    }
}