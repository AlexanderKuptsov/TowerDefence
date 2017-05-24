package Data;

import Graphics.TileGrid;

import static Helpers.Artist.quickLoad;

/**
 * Created by shurik on 24.05.2017.
 */
public class EnemyUFO extends Enemy {

    public EnemyUFO(int tileX, int tileY, TileGrid grid) {
        super(tileX, tileY, grid);
        this.setTexture(quickLoad("UFO"));
        this.setEnemyRight(quickLoad("UFO"));
        this.setEnemyLeft(quickLoad("UFO"));
        this.setEnemyUp(quickLoad("UFO"));
        this.setEnemyDown(quickLoad("UFO"));
    }
}
