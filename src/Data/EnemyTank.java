package Data;

import Graphics.TileGrid;

/**
 * Created by shurik on 24.05.2017.
 */
public class EnemyTank extends Enemy {

    public EnemyTank(EnemyType enemyType, int tileX, int tileY, TileGrid grid) {
        super(enemyType, tileX, tileY, grid);
    }
}
