package Data;

import Graphics.TileGrid;
import Helpers.Clock;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.HEIGHT;
import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 24.06.2017.
 */
public class EnemyBigTank extends Enemy {

  /*  private float timeSinceLastMiniSpawn, miniSpawnTime;
    private Enemy miniEnemy;
    private Wave miniWave;
    private CopyOnWriteArrayList<Enemy> miniEnemyList;
    private int enemiesPerMiniWave, miniEnemiesSpawned;
    private boolean miniWaveCompleted;  */

    public EnemyBigTank(EnemyType enemyType, int tileX, int tileY, TileGrid grid) {
        super(enemyType, tileX, tileY, grid);
       /* this.miniSpawnTime = 0.5f;
        this.enemiesPerMiniWave = 3;
        this.miniEnemiesSpawned = 0;
        this.timeSinceLastMiniSpawn = 0;
        this.miniEnemyList = new CopyOnWriteArrayList<Enemy>();
        this.miniWaveCompleted = false;  */
    }

   /* @Override
    void die() {
        super.die();
    }

    private void miniUpdate() {
        System.out.println("miniUP");
        boolean allEnemiesDead = true;
        if (miniEnemiesSpawned < enemiesPerMiniWave) {
            timeSinceLastMiniSpawn += Clock.INSTANCE.delta();
            if (timeSinceLastMiniSpawn > miniSpawnTime) {
                miniSpawn();
                timeSinceLastMiniSpawn = 0;
            }
        }
        for (Enemy enemy : miniEnemyList) {
            if (enemy.isAlive()) {
                allEnemiesDead = false;
                enemy.update();
                enemy.draw();
            } else miniEnemyList.remove(enemy);
        }
        if (allEnemiesDead) {
            miniEnemyList.clear();
            miniWaveCompleted = true;
        }
    }

    private void miniSpawn() {
        System.out.println("Spawned");
        miniEnemy = new EnemyPlane(EnemyType.Plane,
                (int) (super.getX() / TILE_SIZE), (int) ((HEIGHT - super.getY() - 1) / TILE_SIZE), super.getGrid());
        miniEnemyList.add(miniEnemy);
    }

    @Override
    public void update() {
        super.update();
        System.out.println("UP");
       miniUpdate();
       miniSpawn();
    }*/
}
