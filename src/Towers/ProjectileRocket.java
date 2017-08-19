package Towers;

import Data.ResourceLoader;
import Enemies.Enemy;
import Helpers.Sound;

/**
 * Created by shurik on 16.05.2017.
 */
public class ProjectileRocket extends Projectile {
    private Sound sound;

    public ProjectileRocket(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
        this.sound = ResourceLoader.SOUNDS_PACK.get(super.type.soundName);
    }

    @Override
    public void damage() {
        super.damage();
        Sound.playSound(sound);
    }
}
