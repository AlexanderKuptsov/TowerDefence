package Towers;

import Data.ResourceLoader;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Artist.*;

/**
 * Created by shurik on 12.05.2017.
 */
public enum TowerType {

    CannonPurple("Cannon", new Texture[]{quickLoadTower("towerBase"), quickLoadTower("towerPurpleGun")},
            ProjectileType.Rocket, "boom1.wav", TILE_SIZE * 3.15f, 1.52f, 22),

    CannonIce("IceTower", new Texture[]{quickLoadTower("cannonBaseBlue"), quickLoadTower("cannonGunBlue")},
            ProjectileType.IceBall, "spell1.aif", TILE_SIZE * 2.5f, 1.75f, 15),

    FlameThrower("FlameThrower", new Texture[]{quickLoadTower("cannonBase"), quickLoadTower("cannonGun")},
            ProjectileType.Fire, "fire.aif", TILE_SIZE * 2.4f, 0.052f, 40),

    Mortal("Mortal", new Texture[]{quickLoadTower("towerBase"), quickLoadTower("towerMortalGun"),
            ResourceLoader.PROJECTILE_TEXTURES.get("shuriken")},
            ProjectileType.Shuriken, "blade.wav", TILE_SIZE * 3.8f, 3.28f, 50);

    String name, soundName;
    Texture[] textures;
    ProjectileType projectileType;
    int cost;
    float range, firingRate;

    TowerType(String name, Texture[] textures, ProjectileType projectileType,
              String soundName, float range, float firingRate, int cost) {
        this.name = name;
        this.textures = textures;
        this.projectileType = projectileType;
        this.soundName = soundName;
        this.range = range;
        this.firingRate = firingRate;
        this.cost = cost;
    }

    private static Texture quickLoadTower(String name) {
        return ResourceLoader.TOWERS_TEXTURES.get(name);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getFiringRate() {
        return firingRate;
    }

    public void setFiringRate(float firingRate) {
        this.firingRate = firingRate;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }
}
