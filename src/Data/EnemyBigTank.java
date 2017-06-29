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

    private float timeSinceLastSpawn, spawnTime;
    private Enemy miniEnemy;
    private Wave miniWave;
    private CopyOnWriteArrayList<Enemy> enemyList;
    private int enemiesPerWave, enemiesSpawned;
    private boolean waveCompleted;

    public EnemyBigTank(EnemyType enemyType, int tileX, int tileY, TileGrid grid) {
        super(enemyType, tileX, tileY, grid);
        this.spawnTime = 0.5f;
        this.enemiesPerWave = 3;
        this.enemiesSpawned = 0;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new CopyOnWriteArrayList<Enemy>();
        this.waveCompleted = false;
    }

    @Override
    void die() {
       // super.die();

       miniUpdate();
    }

    public void miniUpdate() {
        boolean allEnemiesDead = true;
        if (enemiesSpawned < enemiesPerWave) {
            timeSinceLastSpawn += Clock.INSTANCE.delta();
            if (timeSinceLastSpawn > spawnTime) {
                miniSpawn();
                timeSinceLastSpawn = 0;
            }
        }
        for (Enemy enemy : enemyList) {
            if (enemy.isAlive()) {
                allEnemiesDead = false;
                enemy.update();
                enemy.draw();
            } else enemyList.remove(enemy);
        }
        if (allEnemiesDead) {
            enemyList.clear();
            waveCompleted = true;
        }
    }

    private void miniSpawn() {
        miniEnemy = new EnemyPlane(EnemyType.Plane,
                (int) (getX() / TILE_SIZE), (int) ((HEIGHT - getY() - 1) / TILE_SIZE), getGrid());
        enemyList.add(miniEnemy);
    }

    @Override
    public void update() {
        super.update();
       miniUpdate();
    }
}
