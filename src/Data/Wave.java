package Data;

import Graphics.TileGrid;
import Helpers.Clock;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static Data.EnemyType.BigTank;
import static Helpers.Artist.HEIGHT;
import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 29.04.2017.
 */
public class Wave {

    private float timeSinceLastSpawn, spawnTime, difficultyMulti;
    private Enemy[] enemyTypes;
    private CopyOnWriteArrayList<Enemy> enemyList;
    private int enemiesPerWave, enemiesSpawned, numberOfWave;
    private boolean waveCompleted;
    private int chosenEnemy;

    public Wave(Enemy[] enemyTypes, float spawnTime, int enemiesPerWave, float difficultyMulti, int numberOfWave) {
        this.enemyTypes = enemyTypes;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.enemiesSpawned = 0;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new CopyOnWriteArrayList<Enemy>();
        this.waveCompleted = false;
        this.chosenEnemy = 0;
        this.difficultyMulti = difficultyMulti;
        this.numberOfWave = numberOfWave;
        spawn();
    }

    public void update() {
        boolean allEnemiesDead = true;
        if (enemiesSpawned < enemiesPerWave) {
            timeSinceLastSpawn += Clock.INSTANCE.delta();
            if (timeSinceLastSpawn > spawnTime) {
                spawn();
                timeSinceLastSpawn = 0;
            }
        }
        for (Enemy enemy : enemyList) {
            if (enemy.isAlive()) {
                allEnemiesDead = false;
                enemy.update();
                enemy.draw();
            } else {
               /* if (enemy.getEnemyType() == EnemyType.BigTank) {
                    spawnSpecialUnit(EnemyType.Plane, (int) (enemy.getX() / TILE_SIZE), (int) (enemy.getY() / TILE_SIZE),
                            enemy.getGrid(), enemy.getCheckpoints().get(enemy.getCurrentCheckpoint()));
                }  */
                enemyList.remove(enemy);
            }
        }
        if (allEnemiesDead) {
            enemyList.clear();
            waveCompleted = true;
        }
    }

    private void spawn() {
        //  enemyList.add(new Enemy(enemyType.getTexture(), enemyType.getStartTile(), enemyType.getTileGrid(),
        //          enemyType.getWidth(), enemyType.getHeight(), enemyType.getSpeed(), enemyType.getHealth()));
        Enemy enemy = new Enemy(enemyTypes[chosenEnemy].getEnemyType(), (int) (enemyTypes[chosenEnemy].getX() / TILE_SIZE),
                (int) (enemyTypes[chosenEnemy].getY() / TILE_SIZE), enemyTypes[chosenEnemy].getGrid());
        enemy.setHealth((float) (enemy.getHealth() * Math.pow(difficultyMulti, numberOfWave)));
        enemy.setStartHealth(enemy.getHealth());
        enemyList.add(enemy);
        System.out.println("Enemy health: " + enemy.getHealth());
        chosenEnemy++;
        if (chosenEnemy == enemyTypes.length) chosenEnemy = 0;
        enemiesSpawned++;
    }

    private void spawnSpecialUnit(EnemyType specialEnemy, int x, int y, TileGrid grid, Checkpoint firstCheckpoint) {
        Enemy enemy = new Enemy(specialEnemy, x, y, grid);
        //enemy.getCheckpoints().set(0, firstCheckpoint);
        enemyList.add(enemy);
        System.out.println("Enemy health: " + enemy.getHealth());
    }

    public boolean isCompleted() {
        return waveCompleted;
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemyList;
    }

    public void setEnemyList(CopyOnWriteArrayList<Enemy> enemyList) {
        this.enemyList = enemyList;
    }
}