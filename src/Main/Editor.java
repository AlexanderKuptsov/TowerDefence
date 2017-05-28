package Main;

import Graphics.TileGrid;
import Graphics.TileType;

import Helpers.StateManager;
import UI.*;
import UI.UI.Menu;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;
import static Helpers.LevelManager.saveMap;

/**
 * Created by shurik on 06.05.2017.
 */
public class Editor {

    private Texture background, menuBackground;
    private TileGrid grid;
    private TileType type;
    private UI editorUI;
    private Menu tilePickerMenu;

    private static final int TILE_PICKER_MENU_X = 1280;
    private static final int TILE_PICKER_MENU_Y = 0;
    private static final int TILE_PICKER_MENU_WIDTH = 192;
    private static final int TILE_PICKER_MENU_HEIGHT = 960;
    private static final int MAX_TILES_IN_ROW = 2;

    private static final int QUITE_BUTTON_X = 1334;
    private static final int QUITE_BUTTON_Y = 825;
    private static final int QUITE_BUTTON_WIDTH = 80;
    private static final int QUITE_BUTTON_HEIGHT = 80;

    private static final int TEXT_X = 1345;
    private static final int TEXT_Y = 50;

    private static final int BACKGROUND_WIDTH = 2048;
    private static final int BACKGROUND_HEIGHT = 1024;


    public Editor() {
        this.background = quickLoad("mainmenu");
        this.menuBackground = quickLoad("menuBackgroundEditor");
        this.grid = new TileGrid();
        this.type = TileType.Grass;
        setupUI();
    }

    private void setupUI() {
        editorUI = new UI();
        editorUI.createMenu("TilePicker",
                TILE_PICKER_MENU_X, TILE_PICKER_MENU_Y,
                TILE_PICKER_MENU_WIDTH, TILE_PICKER_MENU_HEIGHT,
                MAX_TILES_IN_ROW, 0);
        tilePickerMenu = editorUI.getMenu("TilePicker");
        tilePickerMenu.quickAdd("Grass", TileType.Grass.getTextureName());
        tilePickerMenu.quickAdd("Dirt", TileType.Dirt.getTextureName());
        tilePickerMenu.quickAdd("Water", TileType.Water.getTextureName());
        tilePickerMenu.quickAdd("Sand", TileType.Sand.getTextureName());
        tilePickerMenu.quickAdd("Bush", TileType.Bush.getTextureName());
        tilePickerMenu.quickAdd("Stones", TileType.Stones.getTextureName());

        editorUI.addButton("Quit", "quit",
                QUITE_BUTTON_X, QUITE_BUTTON_Y, QUITE_BUTTON_WIDTH, QUITE_BUTTON_HEIGHT);

    }

    public void update() {
        draw();

        // Mouse Input
        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (tilePickerMenu.isButtonClicked("Grass")) {
                    type = TileType.Grass;
                } else if (tilePickerMenu.isButtonClicked("Dirt")) {
                    type = TileType.Dirt;
                } else if (tilePickerMenu.isButtonClicked("Water")) {
                    type = TileType.Water;
                } else if (tilePickerMenu.isButtonClicked("Sand")) {
                    type = TileType.Sand;
                } else if (tilePickerMenu.isButtonClicked("Bush")) {
                    type = TileType.Bush;
                } else if (tilePickerMenu.isButtonClicked("Stones")) {
                    type = TileType.Stones;
                } else if (editorUI.isButtonClicked("Quit"))
                    StateManager.INSTANCE.setState(StateManager.GameState.MAINMENU);
                else setTile();
            }
        }

        // Keyboard Input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
                saveMap("newMarvelousMap4", grid);
            }
        }

    }

    private void draw() {
        drawQuadTexture(background, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        drawQuadTexture(menuBackground, TILE_PICKER_MENU_X, TILE_PICKER_MENU_Y,
                TILE_PICKER_MENU_WIDTH, TILE_PICKER_MENU_HEIGHT);
        editorUI.drawString(TEXT_X, TEXT_Y, "Tiles");
        grid.draw();
        editorUI.draw();
    }

    private void setTile() {
        int mouseX = (int) Math.floor(Mouse.getX() / TILE_SIZE);
        int mouseY = (int) Math.floor((HEIGHT - Mouse.getY() - 1) / TILE_SIZE);
        if (mouseX < TILE_PICKER_MENU_X / TILE_SIZE && mouseY < HEIGHT / TILE_SIZE)
            grid.setTile(mouseX, mouseY, type);
    }
}
