package Towers;

import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 12.05.2017.
 */
public enum TowerType {

    CannonPurple(new Texture[]{quickLoad("Towers\\towerBase"), quickLoad("Towers\\towerPurpleGun")},
            ProjectileType.Rocket, TILE_SIZE * 3.1f, 1.5f, 22),

    CannonIce(new Texture[]{quickLoad("Towers\\cannonBaseBlue"), quickLoad("Towers\\cannonGunBlue")},
            ProjectileType.IceBall, TILE_SIZE * 2.5f, 1.75f, 18),

    FlameThrower(new Texture[]{quickLoad("Towers\\cannonBase"), quickLoad("Towers\\cannonGun")},
            ProjectileType.Fire, TILE_SIZE * 2.4f, 0.06f, 40),

    Mortal(new Texture[]{quickLoad("Towers\\towerBase"), quickLoad("Towers\\towerMortalGun"),quickLoad("shuriken")},
            ProjectileType.Shuriken, TILE_SIZE * 3.8f, 3.25f, 50);


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

    public int getCost() {
        return cost;
    }

    public float getRange() {
        return range;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }
}
