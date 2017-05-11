package UI;

import org.lwjgl.input.Mouse;

import java.util.ArrayList;

import static Helpers.Artist.drawQuadTexture;
import static Helpers.Artist.HEIGHT;
import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 07.05.2017.
 */
public class UI {

    private ArrayList<Button> buttonList;

    public UI() {
        buttonList = new ArrayList<Button>();
    }

    public void addButton(String name, String textureName, int x, int y, int width, int height) {
        buttonList.add(new Button(name, quickLoad(textureName), x, y, width, height));
    }

    public boolean isButtonClicked(String name) {
        Button b = getButton(name);
        float mouseY = HEIGHT - Mouse.getY() - 1;
        return Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
                mouseY > b.getY() && mouseY < b.getY() + b.getHeight() && Mouse.isButtonDown(0);
    }

    private Button getButton(String name) {
        for (Button b : buttonList) {
            if (b.getName().equals(name)) {
                return b;
            }
        }
        return null;
    }

    public void draw() {
        for (Button b : buttonList) {
            drawQuadTexture(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
    }
}