package Main;

import Helpers.Clock;
import Helpers.StateManager;
import org.lwjgl.opengl.Display;

import static Helpers.Artist.BeginSession;

/**
 * Created by shurik on 28.04.2017.
 */
public class Boot {

    public Boot() {
        BeginSession();

        while (!Display.isCloseRequested()) {
            Clock.update();
            StateManager.update();
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