package Towers;

import org.newdawn.slick.opengl.Texture;


import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 16.05.2017.
 */
public enum ProjectileType {

    Rocket(quickLoad("missile"), 10, 500),
    IceBall(quickLoad("bullet"), 2, 450),
    Fire(quickLoad("fire"), 1f, 650);

    Texture texture;
    float damage, speed;

    ProjectileType(Texture texture, float damage, float speed) {
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
    }
}
