package Main;

import Data.ResourceLoader;
import Helpers.Clock;
import Helpers.MyThread;
import Helpers.StateManager;
import org.lwjgl.opengl.Display;

import static Helpers.Artist.BeginSession;

/**
 * Created by shurik on 28.04.2017.
 */
public class Boot {

    private Boot() {
        // Call static method in Artist class to initialize OpenGL calls
        BeginSession();

        // Texture loading
        MyThread resourceLoader = new MyThread();
        resourceLoader.run();

        // Main game loop
        while (!Display.isCloseRequested()) {
            Clock.INSTANCE.update();
            StateManager.INSTANCE.update();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Boot();
    }
}