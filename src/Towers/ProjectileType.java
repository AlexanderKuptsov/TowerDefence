package Towers;

import Helpers.Clock;
import org.newdawn.slick.opengl.Texture;


import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 16.05.2017.
 */
public enum ProjectileType {

    Rocket(quickLoad("missile"), "boom2.wav", 7.25f, 525),
    IceBall(quickLoad("iceCube"), "Bottle_Break.wav", 2, 450),
    Fire(quickLoad("fire"), "", 1f, 666),
    Shuriken(quickLoad("shuriken"), "", 0.775f * Clock.INSTANCE.multiplier(), 400);

    Texture texture;
    String soundName;
    float damage, speed;

    ProjectileType(Texture texture, String soundName, float damage, float speed) {
        this.texture = texture;
        this.soundName = soundName;
        this.damage = damage;
        this.speed = speed;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}