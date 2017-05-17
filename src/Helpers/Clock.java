package Helpers;

import org.lwjgl.Sys;

/**
 * Created by shurik on 29.04.2017.
 */
public class Clock {

    private static boolean paused = false;
    public static long lastFrame, totalTime;
    public static float deltaTime = 0, multiplier = 1;

    public static long getTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }

    public static float getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        if (delta * 0.001f > 0.05f) return 0.05f;
        return delta * 0.001f;
    }

    public static float delta() {
        if (paused) return 0;
        else return deltaTime * multiplier;
    }

    public static float totalTime() {
        return totalTime;
    }

    public static float multiplier() {
        return multiplier;
    }

    public static void update() {
        deltaTime = getDelta();
        totalTime += deltaTime;
    }

    public static void changeMultiplier(float change) {
        if (!(multiplier + change < -1 && multiplier + change > 6)) {
            multiplier += change;
        }
    }

    private static void pause() {
        paused = !paused;
    }
}
