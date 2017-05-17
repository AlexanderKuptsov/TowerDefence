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
        background = quickLoad("mainmenu");
        menuUI = new UI();
        menuUI.addButton("Play", "play", WIDTH / 2 - 128, (int) (HEIGHT * 0.05f), 256, 256);
        menuUI.addButton("Editor", "editor", WIDTH / 2 - 128, (int) (HEIGHT * 0.35f), 256, 256);
        menuUI.addButton("Quit", "quit", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f), 256, 256);
    }

    private void updateButtons() {
        if (menuUI.isButtonClicked("Play"))
            StateManager.setState(StateManager.GameState.GAME);
        if (menuUI.isButtonClicked("Editor"))
            StateManager.setState(StateManager.GameState.EDITOR);
        if (menuUI.isButtonClicked("Quit"))
            System.exit(0);
    }

    public void update() {
        drawQuadTexture(background, 0, 0, 2048, 1024);
        menuUI.draw();
        updateButtons();
    }
}