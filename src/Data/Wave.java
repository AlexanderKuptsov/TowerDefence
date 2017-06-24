package Data;

import Helpers.Clock;

import java.util.concurrent.CopyOnWriteArrayList;

import static Helpers.Artist.TILE_SIZE;

/**
 * Created by shurik on 29.04.2017.
 */
public class Wave {

    private float timeSinceLastSpawn, spawnTime, difficultyMulti;
    private Enemy[] enemyTypes;
    private CopyOnWriteArrayList<Enemy> enemyList;
    private int enemiesPerWave, enemiesSpawned;
    private boolean waveCompleted;
    private int chosenEnemy;

    public Wave(Enemy[] enemyTypes, float spawnTime, int enemiesPerWave, float difficultyMulti) {
        this.enemyTypes = enemyTypes;
        this.spawnTime = spawnTime;
        this.enemiesPerWave = enemiesPerWave;
        this.enemiesSpawned = 0;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new CopyOnWriteArrayList<Enemy>();
        this.waveCompleted = false;
        this.chosenEnemy = 0;
        this.difficultyMulti = difficultyMulti;
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
            } else enemyList.remove(enemy);
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
        enemy.setHealth(enemy.getHealth() * difficultyMulti);
        enemy.setStartHealth(enemy.getHealth());
        enemyList.add(enemy);
        System.out.println("Enemy health: " + enemy.getHealth());
        chosenEnemy++;
        if (chosenEnemy == 3) chosenEnemy = 0;
        enemiesSpawned++;
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