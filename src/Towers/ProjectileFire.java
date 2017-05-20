package Towers;

import Data.Enemy;


/**
 * Created by shurik on 18.05.2017.
 */
public class ProjectileFire extends Projectile {
    public ProjectileFire(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
