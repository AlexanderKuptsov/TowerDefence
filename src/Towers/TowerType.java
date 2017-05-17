package Towers;

import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 12.05.2017.
 */
public enum TowerType {

    CannonRed(new Texture[]{quickLoad("Towers/cannonBase"), quickLoad("Towers/cannonGun")},
            ProjectileType.Rocket, 3, TILE_SIZE * 3, 0.3f),

    CannonBlue(new Texture[]{quickLoad("Towers/towerBase"), quickLoad("Towers/towerPurpleGun")},
            ProjectileType.Rocket, 15, TILE_SIZE * 7, 3),

    CannonIce(new Texture[]{quickLoad("Towers/cannonBaseBlue"),
            quickLoad("Towers/cannonGunBlue")}, ProjectileType.IceBall, 2, TILE_SIZE * 4, 1.5f);

    Texture[] textures;
    ProjectileType projectileType;
    int damage, range;
    float firingRate;

    TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingRate) {
        this.textures = textures;
        this.projectileType = projectileType;
        this.damage = damage;
        this.range = range;
        this.firingRate = firingRate;
    }
}
