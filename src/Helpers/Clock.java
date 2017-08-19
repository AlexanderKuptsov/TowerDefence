package Helpers;

import org.lwjgl.Sys;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 29.04.2017.
 */
public enum Clock {
    INSTANCE;

    private boolean paused = false;
    private long lastFrame, totalTime;
    private float deltaTime = 0, multiplier = 1;
    private final float ratio = (float) (TILE_SIZE / 64.0);

    private long getTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }

    public float getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        float maxDelay = 0.05f;
        if (delta * 0.001f > maxDelay) return maxDelay;
        return delta * 0.001f;
    }

    public float delta() {
        if (paused) return 0;
        else return deltaTime * multiplier;
    }

    public float totalTime() {
        return totalTime;
    }

    public float multiplier() {
        return multiplier * ratio;
    }

    public void update() {
        deltaTime = getDelta();
        totalTime += deltaTime;
    }

    public void changeMultiplier(float change) {
        short minMulti = 0;
        short maxMulti = 4;
        if (multiplier + change >= minMulti && multiplier + change <= maxMulti) {
            multiplier += change;
        }
    }

    private void pause() {
        paused = !paused;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }
}
