package Data;

import Effects.Blood;
import Enemies.Enemy;
import Enemies.EnemyType;
import Graphics.TileGrid;
import Helpers.Clock;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 29.04.2017.
 */
public class Wave {

    private float timeSinceLastSpawn, spawnTime, difficultyMulti, currentWaveMulti;
    private Enemy[] enemyTypes;
    private CopyOnWriteArrayList<Enemy> enemyList;
    private ArrayList<Blood> bloodList;
    private int enemiesPerWave, enemiesSpawned, numberOfWave, enemiesLeft;
    private boolean waveCompleted, first;
    private int chosenEnemy;

    public Wave(Enemy[] enemyTypes, float spawnTime, int enemiesPerWave, float difficultyMulti, int numberOfWave) {
        this.enemyTypes = enemyTypes;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.enemiesSpawned = 0;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new CopyOnWriteArrayList<Enemy>();
        this.bloodList = new ArrayList<Blood>();
        this.waveCompleted = false;
        this.first = true;
        this.chosenEnemy = 0;
        this.difficultyMulti = difficultyMulti;
        this.numberOfWave = numberOfWave;
        this.enemiesLeft = enemiesPerWave;
        this.currentWaveMulti = (float) Math.pow(difficultyMulti, numberOfWave);
    }

    public void update() {
        if (first) {
            spawn();
            first = false;
        }
        if (enemiesSpawned < enemiesPerWave) {
            timeSinceLastSpawn += Clock.INSTANCE.delta();
            if (timeSinceLastSpawn > spawnTime) {
                spawn();
                timeSinceLastSpawn = 0;
            }
        }

        for (Enemy enemy : enemyList) {
            if (enemy.isAlive()) {
                enemy.update();
                if (enemy.isNewMiniEnemy()) {
                    spawnSpecialUnit(EnemyType.SOLDIER, (int) (enemy.getX() / TILE_SIZE), (int) (enemy.getY() / TILE_SIZE),
                            enemy.getGrid(), enemy.getCurrentDirection());
                    enemy.setMiniEnemiesSpawned(enemy.getMiniEnemiesSpawned() + 1);
                    enemy.setNewMiniEnemy(false);
                }
                if (enemy.getEnemyType() == EnemyType.SOLDIER) bloodList.add(enemy.getBlood());
                enemy.draw();
            } else {
                enemyList.remove(enemy);
                enemiesLeft--;
            }
        }

        for (Blood blood : bloodList) blood.update();

        if (enemiesLeft == 0) {
            enemyList.clear();
            bloodList.clear();
            waveCompleted = true;
        }
    }

    private void spawn() {
        Enemy enemy = new Enemy(enemyTypes[chosenEnemy].getEnemyType(), (int) (enemyTypes[chosenEnemy].getX() / TILE_SIZE),
                (int) (enemyTypes[chosenEnemy].getY() / TILE_SIZE), enemyTypes[chosenEnemy].getGrid());
        enemy.setHealth(enemy.getHealth() * currentWaveMulti);
        enemy.setStartHealth(enemy.getHealth());
        enemyList.add(enemy);
        //System.out.println("Enemy health: " + enemy.getHealth());
        chosenEnemy++;
        if (chosenEnemy == enemyTypes.length) chosenEnemy = 0;
        enemiesSpawned++;
    }

    private void spawnSpecialUnit(EnemyType specialEnemy, int x, int y, TileGrid grid, int[] dir) {
        Enemy enemy = new Enemy(specialEnemy, x, y, grid);
        enemy.setHealth(enemy.getHealth() * currentWaveMulti);
        enemy.setStartHealth(enemy.getHealth());
        enemy.setCurrentDirection(dir);
        enemyList.add(enemy);
        enemiesLeft++;
    }

    public boolean isCompleted() {
        return waveCompleted;
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemyList;
    }

    public int getEnemiesLeft() {
        return enemiesLeft;
    }
}