package Towers;

import org.newdawn.slick.opengl.Texture;


import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 16.05.2017.
 */
public enum ProjectileType {

    Rocket(quickLoad("missile"), 7.75f, 500),
    IceBall(quickLoad("iceCube"), 2, 450),
    Fire(quickLoad("fire"), 0.9f, 650),
    Shuriken(quickLoad("shuriken"), 0.8f, 400);

    Texture texture;
    float damage, speed;

    ProjectileType(Texture texture, float damage, float speed) {
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
    }
}