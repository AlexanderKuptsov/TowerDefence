package Data;

import java.util.ArrayList;

import static Helpers.Clock.*;

/**
 * Created by shurik on 29.04.2017.
 */
public class Wave {

    private float timeSinceLastSpawn, spawnTime;
    private Enemy enemyType;
    private ArrayList<Enemy> enemyList;
    private int numberOfEnemies;
    private boolean waveComleted;

    public Wave(Enemy enemyType, float spawnTime, int numberOfEnemies) {
        this.enemyType = enemyType;
        this.spawnTime = spawnTime;
        this.numberOfEnemies = numberOfEnemies;
        this.timeSinceLastSpawn = 0;
        this.enemyList = new ArrayList<Enemy>();
        this.waveComleted = false;

        spawn();
    }

    public void update() {
        boolean allEnemiesDead = true;
        if (enemyList.size() < numberOfEnemies) {
            timeSinceLastSpawn += delta();
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
            }
        }
        if (allEnemiesDead) waveComleted = true;
    }

    private void spawn() {
        enemyList.add(new Enemy(enemyType.getTexture(), enemyType.getStartTile(), enemyType.getTileGrid(),
                enemyType.getWidth(), enemyType.getHeight(), enemyType.getSpeed(), enemyType.getHealth()));
    }

    public boolean isCompleted() {
        return waveComleted;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemyList;
    }
}
