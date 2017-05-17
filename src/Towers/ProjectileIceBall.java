package Towers;

import Data.Enemy;
import org.newdawn.slick.opengl.Texture;

import static Helpers.Clock.delta;
import static Helpers.Clock.getDelta;

/**
 * Created by shurik on 14.05.2017.
 */
public class ProjectileIceBall extends Projectile {
    private float slowDuration;
    private float slowEffect;

    public ProjectileIceBall(ProjectileType type, Enemy target, float x, float y, int width, int height,
                             float slowDuration, float slowEffect) {
        super(type, target, x, y, width, height);
        this.slowDuration = slowDuration;
        this.slowEffect = slowEffect;
    }

    @Override
    public void damage() {
        super.damage();
        if (slowDuration > 0) super.getTarget().setSpeed(getTarget().getStartSpeed() * slowEffect);
        else super.getTarget().setSpeed(getTarget().getStartSpeed());
       // slowDuration=2.5f;
    }

    @Override
    public void update() {
        super.update();
        slowDuration -= delta();
    }
}
