package Enemies;

import Graphics.TileGrid;

/**
 * Created by shurik on 24.05.2017.
 */
public class EnemyUFO extends Enemy {

    public EnemyUFO(EnemyType enemyType, int tileX, int tileY, TileGrid grid) {
        super(enemyType, tileX, tileY, grid);
    }

    private final float LOW_HEALTH_SPEED = super.getSpeed() * 2;

    @Override
    public void update() {
        super.update();
        if (super.getHealth() < super.getHealth() * 0.3) super.setSpeed(LOW_HEALTH_SPEED);
    }
}
