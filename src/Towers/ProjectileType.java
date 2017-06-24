package Towers;

import Helpers.Clock;
import org.newdawn.slick.opengl.Texture;


import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 16.05.2017.
 */
public enum ProjectileType {

    Rocket(quickLoad("missile"), 7.25f, 525),
    IceBall(quickLoad("iceCube"), 2, 450),
    Fire(quickLoad("fire"), 0.85f, 650),
    Shuriken(quickLoad("shuriken"), 0.775f * Clock.INSTANCE.multiplier(), 400);

    Texture texture;
    float damage, speed;

    ProjectileType(Texture texture, float damage, float speed) {
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
    }
}