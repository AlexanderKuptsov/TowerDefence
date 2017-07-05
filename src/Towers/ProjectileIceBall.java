package Towers;

import Data.Enemy;
import Helpers.Clock;
import Helpers.Sound;

import java.io.File;

/**
 * Created by shurik on 14.05.2017.
 */
public class ProjectileIceBall extends Projectile {
    private float slowDuration;
    private float slowEffect;
    private Sound sound;

    public ProjectileIceBall(ProjectileType type, Enemy target, float x, float y, int width, int height,
                             float slowDuration, float slowEffect) {
        super(type, target, x, y, width, height);
        this.slowDuration = slowDuration;
        this.slowEffect = slowEffect;
        this.sound = new Sound(new File("res\\sounds\\" + super.type.soundName));
    }

    @Override
    public void damage() {
        super.damage();
        if (slowDuration > 0) super.getTarget().setSpeed(getTarget().getStartSpeed() * slowEffect);
        else super.getTarget().setSpeed(getTarget().getStartSpeed());
        slowDuration = 2.5f;

        Sound.playSound(sound).setVolume(0.69f);
    }

    @Override
    public void update() {
        super.update();
        slowDuration -= Clock.INSTANCE.delta();
    }
}
