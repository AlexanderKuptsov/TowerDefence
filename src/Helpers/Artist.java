package Helpers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

import static Main.Game.Cash;
import static Main.Game.Lives;
import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by shurik on 28.04.2017.
 */
public class Artist {

    private static final int DESKTOP_WIDTH = Display.getDesktopDisplayMode().getWidth();
    private static final int DESKTOP_HEIGHT = Display.getDesktopDisplayMode().getHeight();

    private static final double ratioW = DESKTOP_WIDTH / 1472.0;
    private static final double ratioH = DESKTOP_HEIGHT / 960.0;

    private static final int TILES_IN_W = 23, TILES_IN_H = 15;

    public static int TILE_SIZE = ratioW >= 1 && ratioH >= 1 ? 64 : (int) (64 * Math.min(ratioW, ratioH));
    public static int WIDTH = TILES_IN_W * TILE_SIZE;
    public static int HEIGHT = TILES_IN_H * TILE_SIZE;

    public static void BeginSession() {
        Display.setTitle("Uncrackable Defence");

        System.out.println("DesktopDisplayMode: " + DESKTOP_WIDTH + " / " + DESKTOP_HEIGHT);
        System.out.println("ratio: " + ratioW + "  " + ratioH);
        System.out.println("DisplayMode: " + WIDTH + "  " + HEIGHT);
        System.out.println("TileSize: " + TILE_SIZE);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setLocation(DESKTOP_WIDTH / 2 - WIDTH / 2, 0);
            Display.create();
            Display.setVSyncEnabled(true);
            Display.swapBuffers();
            Display.setResizable(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        // setup for 2D
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1); // camera
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);  // enable texture
        glEnable(GL_BLEND); // enable transparency
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public static boolean checkCollision(float x1, float y1, float width1, float height1,
                                         float x2, float y2, float width2, float height2) {

        return x1 + width1 - TILE_SIZE / 4 > x2 && x1 < x2 + width2 - TILE_SIZE / 4 &&
                y1 + height1 - TILE_SIZE / 4 > y2 && y1 < y2 + height2 - TILE_SIZE / 4;
    }

    public static boolean modifyCash(int amount) {
        if (Cash + amount >= 0) {
            Cash += amount;
            return true;
        }
        return false;
    }

    public static void modifyLives(int amount) {
        Lives += amount;
    }

    public static void drawQuadTexture(Texture tex, float x, float y, float width, float height) {
        tex.bind();
        glTranslatef(x, y, 0);

        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);

        glEnd();
        glLoadIdentity();
    }

    public static void drawQuadTextureRotation(Texture tex, float x, float y, float width, float height, float angle) {
        tex.bind();
        glTranslatef(x + width / 2, y + height / 2, 0);
        glRotatef(angle, 0, 0, 1);
        glTranslatef(-width / 2, -height / 2, 0);
        glBegin(GL_QUADS);

        glTexCoord2f(0, 0);
        glVertex2f(0, 0);

        glTexCoord2f(1, 0);
        glVertex2f(width, 0);

        glTexCoord2f(1, 1);
        glVertex2f(width, height);

        glTexCoord2f(0, 1);
        glVertex2f(0, height);

        glEnd();
        glLoadIdentity();
    }

    public static Texture loadTexture(String path, String fileType) {
        Texture tex = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture(fileType, in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tex;
    }

    public static Texture quickLoad(String name) {
        return loadTexture("res\\" + name + ".png", "PNG");
    }
}
