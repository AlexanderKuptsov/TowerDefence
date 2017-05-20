package Towers;

import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 12.05.2017.
 */
public enum TowerType {

    CannonRed(new Texture[]{quickLoad("Towers/cannonBase"), quickLoad("Towers/cannonGun")},
            ProjectileType.Rocket, TILE_SIZE * 3, 0.3f, 0),

    CannonPurple(new Texture[]{quickLoad("Towers/towerBase"), quickLoad("Towers/towerPurpleGun")},
            ProjectileType.Rocket, TILE_SIZE * 3.5f, 2, 15),

    CannonIce(new Texture[]{quickLoad("Towers/cannonBaseBlue"), quickLoad("Towers/cannonGunBlue")},
            ProjectileType.IceBall, TILE_SIZE * 2.5f, 1.5f, 20),

    FlameThrower(new Texture[]{quickLoad("Towers/cannonBase"), quickLoad("Towers/cannonGun")},
            ProjectileType.Fire, TILE_SIZE * 2.5f, 0.06f, 40);

    Texture[] textures;
    ProjectileType projectileType;
    int cost;
    float range, firingRate;

    TowerType(Texture[] textures, ProjectileType projectileType, float range, float firingRate, int cost) {
        this.textures = textures;
        this.projectileType = projectileType;
        this.range = range;
        this.firingRate = firingRate;
        this.cost = cost;
    }
}
