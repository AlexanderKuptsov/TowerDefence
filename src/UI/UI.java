package UI;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.util.ArrayList;

import static Helpers.Artist.*;

/**
 * Created by shurik on 07.05.2017.
 */
public class UI {

    private ArrayList<Button> buttonList;
    private ArrayList<Menu> menuList;
    private TrueTypeFont font;
    private Font awtFont;

    public UI() {
        buttonList = new ArrayList<Button>();
        menuList = new ArrayList<Menu>();
        awtFont = new Font("Algerian", Font.ITALIC, 22);
        font = new TrueTypeFont(awtFont, false);
    }

    public void drawString(int x, int y, String text) {
        font.drawString(x, y, text);
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

    public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
        menuList.add(new Menu(name, x, y, width, height, optionsWidth, optionsHeight));
    }

    public Menu getMenu(String name) {
        for (Menu m : menuList)
            if (name.equals(m.getName())) return m;
        return null;
    }

    public void draw() {
        for (Button b : buttonList) drawQuadTexture(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
        for (Menu m : menuList) m.draw();
    }

    public class Menu {

        String name;
        private ArrayList<Button> menuButtons;
        private int x, y, width, height, numberOfButtons, optionsWidth, optionsHeight, gap;

        public Menu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.optionsWidth = optionsWidth;
            this.optionsHeight = optionsHeight;
            this.gap = (width - (optionsWidth * TILE_SIZE)) / (optionsWidth + 1);
            this.numberOfButtons = 0;
            this.menuButtons = new ArrayList<Button>();
        }

        public void addButton(Button b) {
            setButton(b);
        }

        public void quickAdd(String name, String buttonTextureName) {
            Button b = new Button(name, quickLoad(buttonTextureName), 0, 0);
            setButton(b);
        }

        private void setButton(Button b) {
            final int FIRST_GAP = 70;
            if (optionsWidth != 0)
                b.setY(y + (numberOfButtons / optionsWidth) * (TILE_SIZE + gap) + gap + FIRST_GAP);
            b.setX(x + (numberOfButtons % 2) * (gap + TILE_SIZE) + gap);
            numberOfButtons++;
            menuButtons.add(b);
        }

        public boolean isButtonClicked(String name) {
            Button b = getButton(name);
            float mouseY = HEIGHT - Mouse.getY() - 1;
            return Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
                    mouseY > b.getY() && mouseY < b.getY() + b.getHeight() && Mouse.isButtonDown(0);
        }

        public Button getButton(String name) {
            for (Button b : menuButtons) {
                if (b.getName().equals(name)) {
                    return b;
                }
            }
            return null;
        }

        public void draw() {
            for (Button b : menuButtons) {
                drawQuadTexture(b.getTexture(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }
        }

        public String getName() {
            return name;
        }
    }
}