package Main;

import Data.ResourceLoader;
import Helpers.Sound;
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
    private Sound sound;

    public LevelMenu() {
        background = ResourceLoader.UI_TEXTURES.get("mainMenu");
        menuUI = new UI();
        int BUTTON_SIZE = TILE_SIZE * 4;
        menuUI.addButton("Level 1", "1", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.05f),
                BUTTON_SIZE, BUTTON_SIZE);
        menuUI.addButton("Level 2", "2", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.35f),
                BUTTON_SIZE, BUTTON_SIZE);
        menuUI.addButton("Level 3", "3", WIDTH / 2 - BUTTON_SIZE / 2, (int) (HEIGHT * 0.65f),
                BUTTON_SIZE, BUTTON_SIZE);

        menuUI.addButton("Menu", "menu", WIDTH / 2 + BUTTON_SIZE, (int) (HEIGHT * 0.65f),
                BUTTON_SIZE, BUTTON_SIZE);

        sound = ResourceLoader.SOUNDS_PACK.get("click1.wav");
    }

    private void updateButtons() {
        if (menuUI.isButtonClicked("Level 1")) {
            StateManager.INSTANCE.setMapNum(1);
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
            Sound.playSound(sound);
        }
        if (menuUI.isButtonClicked("Level 2")) {
            StateManager.INSTANCE.setMapNum(2);
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
            Sound.playSound(sound);
        }
        if (menuUI.isButtonClicked("Level 3")) {
            StateManager.INSTANCE.setMapNum(3);
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
            Sound.playSound(sound);
        }
        if (menuUI.isButtonClicked("Menu")) {
            StateManager.INSTANCE.setState(StateManager.GameState.MAINMENU);
            Sound.playSound(sound);
        }
    }

    public void update() {
        drawQuadTexture(background, 0, 0, 2048, 1024);
        menuUI.draw();
        updateButtons();
    }
}
