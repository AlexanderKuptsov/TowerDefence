package Main;

import Data.ResourceLoader;
import Helpers.Sound;
import Helpers.StateManager;
import UI.UI;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import java.awt.*;

import static Helpers.Artist.*;
import static Helpers.Artist.HEIGHT;
import static Helpers.Artist.WIDTH;

/**
 * Created by shurik on 15.07.2017.
 */
public class GameOverMenu {

    private Texture background;
    private UI menuUI;
    private Sound sound;
    private boolean win;
    private String text;

    public GameOverMenu(boolean win) {
        background = ResourceLoader.UI_TEXTURES.get("mainMenu");
        sound = ResourceLoader.SOUNDS_PACK.get("click1.wav");
        menuUI = new UI();
        menuUI.setFont(new TrueTypeFont(new Font("Algerian", Font.BOLD, 50), true));
        this.win = win;
        this.text = win ? "You Win!" : "You lose!";

        final int BUTTON_SIZE = TILE_SIZE * 4;
        final int CENTER_X = WIDTH / 2 - BUTTON_SIZE / 2;
        final int GAP = BUTTON_SIZE + TILE_SIZE;
        if (this.win) {
            menuUI.addButton("Next", "Next", CENTER_X, (int) (HEIGHT * 0.4f),
                    BUTTON_SIZE, BUTTON_SIZE);
        }
        menuUI.addButton("Restart", "Restart", CENTER_X - GAP, (int) (HEIGHT * 0.4f),
                BUTTON_SIZE, BUTTON_SIZE);
        menuUI.addButton("Levels", "Levels", CENTER_X + GAP, (int) (HEIGHT * 0.4f),
                BUTTON_SIZE, BUTTON_SIZE);
        menuUI.addButton("Menu", "menu", CENTER_X - GAP / 2, (int) (HEIGHT * 0.65f),
                BUTTON_SIZE, BUTTON_SIZE);

        menuUI.addButton("Quit", "quit", CENTER_X + GAP / 2, (int) (HEIGHT * 0.65f),
                BUTTON_SIZE, BUTTON_SIZE);
    }

    private void updateButtons() {
        if (win) {
            if (menuUI.isButtonClicked("Next")) {
                int nextLvl = StateManager.INSTANCE.getMapNum() + 1;
                StateManager.INSTANCE.setMapNum(nextLvl >= 4 ? 1 : nextLvl);
                StateManager.INSTANCE.setState(StateManager.GameState.GAME);
                Sound.playSound(sound);
            }
        }
        if (menuUI.isButtonClicked("Restart")) {
            StateManager.INSTANCE.setState(StateManager.GameState.GAME);
            Sound.playSound(sound);
        }
        if (menuUI.isButtonClicked("Levels")) {
            Sound.playSound(sound);
            StateManager.INSTANCE.setState(StateManager.GameState.LEVELMENU);
        }
        if (menuUI.isButtonClicked("Menu")) {
            StateManager.INSTANCE.setState(StateManager.GameState.MAINMENU);
            Sound.playSound(sound);
        }
        if (menuUI.isButtonClicked("Quit")) {
            Sound.playSound(sound);
            System.exit(0);
        }
    }

    public void update() {
        drawQuadTexture(background, 0, 0, 2048, 1024);
        updateButtons();
        menuUI.drawString(610, 250, text);
        menuUI.draw();
    }
}
