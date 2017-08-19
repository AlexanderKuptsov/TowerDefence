package Towers;

import Data.ResourceLoader;
import Enemies.Enemy;
import Helpers.Sound;

/**
 * Created by shurik on 14.05.2017.
 */
public class ProjectileIceBall extends Projectile {
    private float slowDuration, slowEffect;
    private Sound sound;

    public ProjectileIceBall(ProjectileType type, Enemy target, float x, float y, int width, int height,
                             float slowDuration, float slowEffect) {
        super(type, target, x, y, width, height);
        this.slowDuration = slowDuration;
        this.slowEffect = slowEffect;
        this.sound = ResourceLoader.SOUNDS_PACK.get(super.type.soundName);
    }

    @Override
    public void damage() {
        super.damage();
        super.getTarget().slowEffect(super.type, slowDuration, slowEffect);
        Sound.playSound(sound).setVolume(0.69f);
    }
}
