package Towers;

import org.newdawn.slick.opengl.Texture;


import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 16.05.2017.
 */
public enum ProjectileType {

    Rocket(quickLoad("rocket"), 10, 500),
    IceBall(quickLoad("bullet"), 6, 450);

    Texture texture;
    int damage;
    float speed;

    ProjectileType(Texture texture, int damage, float speed) {
        this.texture = texture;
        this.damage = damage;
        this.speed = speed;
    }
}
