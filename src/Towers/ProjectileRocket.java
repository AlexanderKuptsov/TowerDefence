package Towers;

import Data.Enemy;
import Helpers.Sound;

import java.io.File;

/**
 * Created by shurik on 16.05.2017.
 */
public class ProjectileRocket extends Projectile {
    private Sound sound;

    public ProjectileRocket(ProjectileType type, Enemy target, float x, float y, int width, int height) {
        super(type, target, x, y, width, height);
        this.sound = new Sound(new File("res\\sounds\\" + super.type.soundName));
    }

    @Override
    public void damage() {
        super.damage();
        Sound.playSound(sound);
    }
}
