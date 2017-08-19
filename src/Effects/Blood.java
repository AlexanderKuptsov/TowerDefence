package Effects;

import Data.ResourceLoader;
import Helpers.Clock;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.TILE_SIZE;
import static Helpers.Artist.drawQuadTexture;

public class Blood {

    private float x, y, time;
    private boolean bleeding;
    private Texture texture;

    public Blood(float x, float y) {
        this.x = x;
        this.y = y;
        bleeding = false;
        this.time = 30f;
        this.texture = ResourceLoader.EFFECTS_TEXTURES.get("Blood");
    }

    public void update() {
        if (bleeding) {
            if (time > 0) {
                draw();
                time -= Clock.INSTANCE.delta();
            } else bleeding = false;
        }
    }

    public void draw() {
        drawQuadTexture(texture, x, y, TILE_SIZE, TILE_SIZE);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isBleeding() {
        return bleeding;
    }

    public void setBleeding(boolean bleeding) {
        this.bleeding = bleeding;
    }
}
