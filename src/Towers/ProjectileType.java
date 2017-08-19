package Towers;

import Data.ResourceLoader;
import Helpers.Clock;
import org.newdawn.slick.opengl.Texture;


import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 16.05.2017.
 */
public enum ProjectileType {

    Rocket(quickLoadProjectile("missile"), "boom2.wav", 7.25f, 525, new float[]{0}),
    IceBall(quickLoadProjectile("iceCube"), "Bottle_Break.wav", 2, 450, new float[]{3.5f, 0.4f}),
    Fire(quickLoadProjectile("fire"), "", 1f, 666, new float[]{0}),
    Shuriken(quickLoadProjectile("shuriken"), "blade.wav", 0.775f * Clock.INSTANCE.multiplier(), 400, new float[]{0});

    Texture texture;
    String soundName;
    private float damage, speed;
    private float[] specialEffects;

    ProjectileType(Texture texture, String soundName, float damage, float speed, float[] specialEffects) {
        this.texture = texture;
        this.soundName = soundName;
        this.damage = damage;
        this.speed = speed;
        this.specialEffects = specialEffects;
    }

    private static Texture quickLoadProjectile(String name) {
        return ResourceLoader.PROJECTILE_TEXTURES.get(name);
    }

    public float getDamage() {
        return damage;
    }

    public float getSpeed() {
        return speed;
    }

    public float[] getSpecialEffects() {
        return specialEffects;
    }
}