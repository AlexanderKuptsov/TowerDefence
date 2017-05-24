package Helpers;

import org.lwjgl.Sys;

/**
 * Created by shurik on 29.04.2017.
 */
public class Clock {

    private static boolean paused = false;
    private static long lastFrame, totalTime;
    private static float deltaTime = 0, multiplier = 1;

    private static long getTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }

    public static float getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        float maxDelay = 0.05f;
        if (delta * 0.001f > maxDelay) return maxDelay;
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
        short minMulti = -1;
        short maxMulti = 5;
        if (!(multiplier + change < minMulti && multiplier + change > maxMulti)) {
            multiplier += change;
        }
    }

    private static void pause() {
        paused = !paused;
    }
}
